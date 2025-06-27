

---

##  **Prints all subsequences of the array** whose elements sum up to **exactly `k`**,
* and it **counts** how many such subsequences exist.

**That** is the classic *subsequence sum equals k* recursion problem — exactly as taught by Striver in his “Recursion 1” playlist.

---

### Let’s summarize what it does

* uses **pick/don’t-pick**:

  * pick the current element → add it to `subsequence` and increment the sum
  * don’t pick the current element → leave subsequence unchanged
* when `index == nums.length`, check:

  * if `sum == k`, print the subsequence and return 1 to count
  * otherwise return 0

---

### Example:

If you run

```java
nums = [1,2,1], k=2
```

it will print:

```
[1, 1]
[2]
```

and return `2` because there are two subsequences with sum 2.

---

✅ **This is correct** for a *print and count all subsequences whose sum equals k* problem.
It does **not** solve LeetCode 1498 or 2099 directly, but it is perfect for:

* *“print all subsequences with sum k”*
* *“count them”*

---

If you want, I can also help you **extend** this pattern:

* to *print only one* subsequence
* to *just count* without printing
* to *list all* subsequences in a list of lists

— just say

> *“please extend this for X”*

and I’ll help right away! 👍

```java
class Solution {
    private static int subsequenceSum(int[] nums, int k, int index, List<Integer> subsequence, int sum) {
        if (index == nums.length) {
            if (sum == k) {
                System.out.println(subsequence); // print the subsequence
                return 1; // count it
            }
            return 0;
        }
        
        // pick
        subsequence.add(nums[index]);
        int count1 = subsequenceSum(nums, k, index+1, subsequence, sum + nums[index]);
        
        // don’t pick
        subsequence.remove(subsequence.size() - 1);
        int count2 = subsequenceSum(nums, k, index+1, subsequence, sum);
        
        return count1 + count2;
    }
    
    public int numSubseq(int[] nums, int target) {
        return subsequenceSum(nums, target, 0, new ArrayList<>(), 0);
    }
}
```

---

👍
