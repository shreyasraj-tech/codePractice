

---

## ✅ Problem: [Leetcode 78 – Subsets](https://leetcode.com/problems/subsets/)

**Given** an integer array `nums`, return **all possible subsets (the power set)**.
The solution must **not contain duplicates**, and each subset must be in **non-descending order of elements**.

### ✨ Example:

```text
Input: nums = [1,2,3]
Output: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
```

---

## ✅ Method 1: Recursive (Backtracking)

### 📌 Logic:

At each element, you have **two choices**:

* Include the element in the current subset
* Exclude the element and move on

### 🔁 Recursion tree expands like:

```
                []
             /     \
          [1]       []
         /   \     /  \
     [1,2] [1]  [2]   []
     ...
```

### 💡 Backtracking builds and removes subsets on the fly.

---

### ✅ Java Code – Recursive:

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void generateSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include current element
        current.add(nums[index]);
        generateSubsets(index + 1, nums, current, result);

        // Backtrack and exclude current element
        current.remove(current.size() - 1);
        generateSubsets(index + 1, nums, current, result);
    }
}
```

---

## ✅ Method 2: Iterative (Optimized)

### 📌 Logic:

Start with `[]`, and for each number in `nums`, **append it to all existing subsets** to form new subsets.

### 💡 Why it's efficient:

No recursion, uses simple iteration. Easy to understand and implement.

---

### ✅ Java Code – Iterative:

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty set

        for (int num : nums) {
            int n = result.size();
            for (int i = 0; i < n; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }

        return result;
    }
}
```

---

## 🧠 Notes on Both Approaches

| Feature             | Recursive (Backtracking)             | Iterative (Optimized)                |
| ------------------- | ------------------------------------ | ------------------------------------ |
| Style               | DFS-style recursive traversal        | Builds up subsets iteratively        |
| Intuition           | Decision tree with include/exclude   | Start with `[]` and add new items    |
| Time Complexity     | $O(2^n \cdot n)$                     | $O(2^n \cdot n)$                     |
| Space Complexity    | $O(n)$ recursion + result space      | Only result list                     |
| Extra memory used   | Recursive stack                      | Less stack overhead                  |
| Easy to understand? | Medium                               | Very Easy                            |
| Supports filtering? | Yes (easy to modify for constraints) | Less flexible for complex conditions |

---

## 🔥 Which is better?

* **Recursive** is great for understanding and easy to modify (e.g. for **Subsets II**, or size constraints).
* **Iterative** is better in **interview settings** where you want a **simple, clear solution** quickly.
* Both have the same time complexity, but iterative avoids function call overhead.

---

## ✅ Bonus: Sorting for Consistent Order

```java
Arrays.sort(nums); // Optional but ensures subsets are in lexicographical order
```

---

## 📘 Summary Notes (Cheat Sheet)

```markdown
# Subsets – Leetcode 78

## Problem:
Generate all subsets (power set) of a given array of unique integers.

---

## Approaches:

### 1. Recursive (Backtracking):
- DFS decision tree
- Include or exclude each element
- Modify path in-place and backtrack

### 2. Iterative:
- Start from []
- For each num, add it to all existing subsets to form new ones

---

## Time & Space:
- Time: O(2^n * n)
- Space: O(2^n * n) (output size), + O(n) for recursion

---

## When to Use:
- Use recursion for customization and flexibility
- Use iteration for speed and simplicity
```

---
