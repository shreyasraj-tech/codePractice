# 🎯 2035. Partition Array Into Two Arrays to Minimize Sum Difference [LeetCode Link](https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/)
---

### 📘 Problem Reminder

You are given an array `nums` of **even length** `n` (where `n` is up to 30).

You need to split `nums` into **two arrays** `A` and `B` such that:

- Both `A` and `B` have exactly `n/2` elements
- Every element of `nums` must go to **exactly one** of the arrays
- Let `sum(A)` and `sum(B)` be their sums

Your goal → **Minimize**:  
```text
| sum(A) - sum(B) |
````

Return this **minimum possible** absolute difference.

---

### 🧩 Key Points

* `n` is **up to 30** → `2^n` brute-force is too big.

* Each subset must contain exactly `n/2` elements.

* If `total = sum(nums)`, and `sum(A) = s`, then:

  ```text
  sum(B) = total - s
  diff = |sum(A) - sum(B)| = |total - 2*s|
  ```

* So the core idea:

  > **Find a subset of size `n/2` whose sum `s` makes `|total - 2*s|` minimum.**

* Direct `O(n * total)` DP (like normal subset sum) often **fails** here because:

  * Values can be large ⇒ `total` can be very big ⇒ DP table too large.

✅ Instead, we use a **Meet-in-the-Middle** idea (split array into half).

---

## 🔍 Big Picture Strategy (Meet-in-the-Middle)

1. Split `nums` into:

   * `left` = first `n/2` elements
   * `right` = last `n/2` elements

2. For each half:

   * Generate **all subset sums**, grouped by **subset size** (how many elements chosen):

     * `leftSums[k]` = list of sums when picking `k` elements from `left`
     * `rightSums[k]` = list of sums when picking `k` elements from `right`

3. To form a final subset `A` of size `n/2`:

   * Pick `k` elements from `left`
   * Pick `n/2 - k` elements from `right`
   * Total sum of `A` = `x + y` where:

     * `x` ∈ `leftSums[k]`
     * `y` ∈ `rightSums[n/2 - k]`

4. For each `k`, and each `x` in `leftSums[k]`:

   * We want `x + y` as close as possible to `total / 2`
   * So needed `y ≈ total/2 - x`
   * Since `rightSums[...]` are sorted, we use **binary search** to find the best `y`.

5. Track minimal `|total - 2*(x + y)|`.

This reduces complexity from `O(2^n)` to about `O(2^(n/2) * n * log(2^(n/2)))`, which is OK for `n ≤ 30`.

---

# **0️⃣ Conceptual Brute Force (Recursion)**

> This is mostly for understanding; it’s too slow for constraints.

---

### 🎯 Intuition

Try to build subset `A` with exactly `n/2` elements.

At each index `i`, you decide:

* **pick** `nums[i]` into `A`
* **notPick** `nums[i]` → goes to `B`

Keep track of:

* `pickedCount` = how many chosen into `A` so far
* `sumA` = sum of chosen elements

At the end:

```text
sumB = total - sumA
diff = |sumA - sumB|
minimize diff
```

---

### 🧠 Recursive State

```text
f(i, pickedCount, sumA)
= minimum difference achievable
  using elements from index i onwards
  when we have picked pickedCount elements so far
  and current sum of A is sumA
```

---

### 🧮 Base Case

```text
If i == n:
    If pickedCount == n/2:
        sumB = total - sumA
        return |sumA - sumB|
    else:
        return +∞ (invalid partition)
```

---

### 🔁 Transition

At index `i`, value = `nums[i]`:

1. ❌ notPick:

   ```text
   diff1 = f(i+1, pickedCount, sumA)
   ```

2. ✅ pick (only if we still can pick):

   ```text
   diff2 = f(i+1, pickedCount+1, sumA + nums[i])
   ```

Final:

```text
f(i, c, s) = min(diff1, diff2)
```

---

### ⚠️ Problem with Pure Recursion

* 3 parameters `(i, pickedCount, sumA)` → huge state space.
* `sumA` can be large (values big), recursion is **exponential**.
* Even memoization on `sumA` is too heavy.

→ We need a **different idea**: Meet-in-the-Middle.

---

# **1️⃣ Meet-in-the-Middle – Core Idea**

Instead of DP over sum directly, we:

1. Split array into two halves of size `n/2`:

   ```text
   left  = nums[0 .. n/2 - 1]
   right = nums[n/2 .. n-1]
   ```

2. For each half:

   * Enumerate all subsets via recursion / bitmask.
   * Group sums by how many numbers were picked.

Example: suppose `left = [l0, l1, l2]`:

* Subsets of size 0 → sum = 0
* Subsets of size 1 → sums like `[l0, l1, l2]`
* Subsets of size 2 → sums like `[l0+l1, l0+l2, l1+l2]`
* Subsets of size 3 → sum = `[l0+l1+l2]`

Store them as:

```text
leftSums[0] = [0]
leftSums[1] = [...]
leftSums[2] = [...]
leftSums[3] = [...]
```

Similarly for right side.

3. Finally:
   For each `k` from `0` to `n/2`:

   * We pick `k` elements from left, and `n/2 - k` from right.
   * For each `x` in `leftSums[k]`:

     * We need `y` from `rightSums[n/2 - k]` such that `x + y` is as close as possible to `total / 2`.

   That is:

   ```text
   targetSumA = total / 2
   ideal y ≈ targetSumA - x
   ```

   Since `rightSums[...]` is sorted, use **binary search** to find the closest `y`.

   Then compute:

   ```text
   sumA = x + y
   sumB = total - sumA
   diff = |sumA - sumB| = |total - 2 * sumA|
   ```

   Keep track of minimum diff.

---

# **2️⃣ Generating Subset Sums (Visualization with Example)**

Let’s take a **small but non-trivial example** for understanding:

```text
nums = [3, 9, 7, 3]
n = 4
n/2 = 2
total = 3 + 9 + 7 + 3 = 22
We want to split into two subsets of size 2 each, minimize |sum1 - sum2|
```

### ✂️ Split into two halves

```text
left  = [3, 9]   (indices 0,1)
right = [7, 3]   (indices 2,3)
```

---

### 🔹 All subset sums of left, grouped by size

For `left = [3, 9]`:

All subsets:

| Subset | Size | Sum |
| ------ | ---- | --- |
| {}     | 0    | 0   |
| {3}    | 1    | 3   |
| {9}    | 1    | 9   |
| {3, 9} | 2    | 12  |

Group by size:

```text
leftSums[0] = [0]
leftSums[1] = [3, 9]
leftSums[2] = [12]
```

---

### 🔹 All subset sums of right, grouped by size

For `right = [7, 3]`:

Subsets:

| Subset | Size | Sum |
| ------ | ---- | --- |
| {}     | 0    | 0   |
| {7}    | 1    | 7   |
| {3}    | 1    | 3   |
| {7, 3} | 2    | 10  |

Group:

```text
rightSums[0] = [0]
rightSums[1] = [7, 3]
rightSums[2] = [10]
```

Sort each list for binary search:

```text
leftSums[1]  = [3, 9]      (already sorted)
rightSums[1] = [3, 7]      (sorted)
```

---

### 🎯 Target

We want:

```text
sumA ≈ total/2 = 11
diff = |22 - 2 * sumA|
```

Remember `sumA` must be formed by picking exactly 2 elements in total:

* `k` from left
* `2 - k` from right

---

### Case 1️⃣: Pick 0 from left, 2 from right

```text
k = 0 → from leftSums[0] = [0]
need 2 from right → rightSums[2] = [10]
```

For `x = 0`, `y` can be only 10:

```text
sumA = 0 + 10 = 10
diff = |22 - 2*10| = |22 - 20| = 2
```

Current best = 2

---

### Case 2️⃣: Pick 1 from left, 1 from right

```text
k = 1 → from leftSums[1] = [3, 9]
need 1 from right → rightSums[1] = [3, 7] (sorted)
targetSumA = 11
For each x, find y closest to 11 - x
```

#### For x = 3:

```text
We want y ≈ 11 - 3 = 8
rightSums[1] = [3, 7]
Closest y is 7
sumA = 3 + 7 = 10
diff = |22 - 2*10| = 2
```

#### For x = 9:

```text
We want y ≈ 11 - 9 = 2
rightSums[1] = [3, 7]
Closest y is 3
sumA = 9 + 3 = 12
diff = |22 - 2*12| = |22 - 24| = 2
```

Best still = 2

---

### Case 3️⃣: Pick 2 from left, 0 from right

```text
k = 2 → leftSums[2] = [12]
need 0 from right → rightSums[0] = [0]
```

```text
sumA = 12 + 0 = 12
diff = |22 - 2*12| = 2
```

🔚 Final answer = **2**, which matches LeetCode.

---

# **3️⃣ Implementation Plan**

We’ll implement:

1. Function to generate subset sums per half:

   ```java
   void gen(int[] arr, int start, int end, List<List<Integer>> bucket)
   ```

   where `bucket[k]` holds all sums of subsets of size `k`.

2. For each half:

   * Create `List<List<Integer>> leftSums` and `rightSums`.
   * Size: `n/2 + 1` buckets (0..n/2 elements)

3. Sort each `rightSums[k]` for binary search.

4. Loop over `k` from 0 to `n/2`, combine `k` from left with `n/2 - k` from right.

5. For each `x` in `leftSums[k]`, binary search best `y`.

---

# **4️⃣ Java Code (Meet-in-the-Middle – Efficient Solution)**

```java
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int half = n / 2;

        int total = 0;
        for (int x : nums) total += x;

        // left half = nums[0 .. half-1]
        // right half = nums[half .. n-1]
        int[] left = new int[half];
        int[] right = new int[half];

        for (int i = 0; i < half; i++) {
            left[i] = nums[i];
            right[i] = nums[half + i];
        }

        // For each half, bucket sums by subset size
        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();
        for (int i = 0; i <= half; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        // Generate sums for left & right halves
        genSubsets(left, 0, left.length, 0, 0, leftSums);
        genSubsets(right, 0, right.length, 0, 0, rightSums);

        // Sort right buckets for binary search
        for (int k = 0; k <= half; k++) {
            Collections.sort(rightSums.get(k));
        }

        int ans = Integer.MAX_VALUE;
        int target = total / 2;

        // Combine k from left and (half - k) from right
        for (int k = 0; k <= half; k++) {
            List<Integer> listL = leftSums.get(k);
            List<Integer> listR = rightSums.get(half - k);

            for (int x : listL) {
                int need = target - x;

                // Find closest y in listR to "need"
                int y = closest(listR, need);

                int sumA = x + y;
                int diff = Math.abs(total - 2 * sumA);
                if (diff < ans) ans = diff;
            }
        }

        return ans;
    }

    // Generate all subset sums, track size of subset (cnt)
    private void genSubsets(int[] arr, int idx, int n,
                            int cnt, int currSum,
                            List<List<Integer>> bucket) {
        if (idx == n) {
            bucket.get(cnt).add(currSum);
            return;
        }

        // notPick current
        genSubsets(arr, idx + 1, n, cnt, currSum, bucket);

        // pick current
        genSubsets(arr, idx + 1, n, cnt + 1, currSum + arr[idx], bucket);
    }

    // Binary search: find value in sorted list closest to 'target'
    private int closest(List<Integer> list, int target) {
        int lo = 0, hi = list.size() - 1;
        int best = list.get(0);

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = list.get(mid);

            if (Math.abs(val - target) < Math.abs(best - target)) {
                best = val;
            }

            if (val < target) {
                lo = mid + 1;
            } else if (val > target) {
                hi = mid - 1;
            } else {
                // exact match
                return val;
            }
        }

        return best;
    }
}
```

---

# ⏱️ Time & Space Complexity

* Number of subsets in each half: `2^(n/2)`
* For each half we generate all subset sums ⇒ `O(2^(n/2))`
* Combining:

  * For each subset in left, we binary search in right
  * Total complexity roughly:

    ```text
    O( 2^(n/2) * log(2^(n/2)) * n )   (constants omitted)
    ```
* This is **fast enough for n = 30**.

Space:

* Storing all subset sums: `O(2^(n/2))`

---

# 🧠 Quick Revision Bullets

* Goal: **minimize |sum(A) - sum(B)|** with both subsets of size `n/2`.
* Transform to:

  ```text
  minimize |total - 2*sumA|
  ```
* Where `sumA` is the sum of one subset of size `n/2`.
* Use **Meet-in-the-Middle**:

  * Split array into two halves
  * Generate all subset sums for each half, grouped by subset size
  * For each `k`:

    * choose `k` from left, `n/2 - k` from right
    * find best pair `(x, y)` with binary search so `x + y` is near `total/2`.
* This avoids huge DP over sum and works within limits.

---

# 📝 One-Line Memory Hook

> “For 2035, normal DP is too big.
> Split array into two halves, precompute all subset sums by size, sort one side, and **binary search** sums that get total as close as possible to half of the overall sum.”

---

```

If you want, next we can:
- Do a **full table-style trace** for a slightly bigger example with the lists `leftSums` and `rightSums`
- Or convert this into a **compact revision sheet** with only formulas + patterns.
::contentReference[oaicite:0]{index=0}
```
