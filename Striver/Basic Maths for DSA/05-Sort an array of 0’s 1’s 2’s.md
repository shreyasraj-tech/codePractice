# 🎨 Sort Colors (Dutch National Flag Problem)

### 🔗 Problem Link  
- LeetCode 75: https://leetcode.com/problems/sort-colors/

---

## 1️⃣ Explain the Problem

### 📌 Problem Statement  
You are given an array `nums` of length `n` containing only `0`, `1`, and `2`.

- `0` → Red  
- `1` → White  
- `2` → Blue  

You must **sort the array in-place** so that:
```

0s come first → then 1s → then 2s

```

### ❗ Constraints
- Do **NOT** use any library sorting function
- Must modify the array in-place


### ✏️ Example
```

Input : [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

````

---

## 2️⃣ Similar Problems (Same Pattern)

- Dutch National Flag Problem
- Segregate 0s and 1s
- Sort Array of 0s, 1s, and 2s
- Partition Array based on pivot
- Three-way partitioning

These problems are all about **partitioning**, not traditional sorting.

---

## 3️⃣ Core Logic Behind the Problem & Challenge

### 🎯 What we want
- Group identical values together
- Maintain order: `0 → 1 → 2`
- Do it **in-place** with **minimum passes**

### ⚠️ Main Challenge
- Cannot rely on built-in sort
- Must control where elements go
- Must avoid extra space

---

## 4️⃣ Brute Force Solution & Why It’s Not Acceptable

### 🧠 Brute Force Idea
- Try all permutations
- Or use nested loops to sort

### ❌ Why not acceptable?
- Time complexity goes to `O(n²)`
- Misses the core idea of partitioning
- Interviewers expect better

---

## 5️⃣ Methods to Solve the Problem

We will discuss **ONLY two approaches**, as requested.

---

## 🟢 Method 1: Counting Approach (Two Pass)

### 🔍 Idea
Since the array contains **only three values**, we:
1. Count number of `0`s, `1`s, and `2`s
2. Overwrite the array accordingly

### ✅ Code
```java
class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        int idx = 0;
        while (count0-- > 0) nums[idx++] = 0;
        while (count1-- > 0) nums[idx++] = 1;
        while (count2-- > 0) nums[idx++] = 2;
    }
}
````

### 🧠 Explanation

* First loop → counts occurrences
* Second phase → rebuilds array in sorted order

### 👍 Pros

* Very easy to understand
* No pointer confusion

### 👎 Cons

* Requires **two passes**
* Not optimal when one-pass is expected

---

## 🔵 Method 2: Dutch National Flag Algorithm (One Pass – Best)

### 🔍 Core Idea

Partition the array into **three regions** using pointers.

### Pointer Meaning (VERY IMPORTANT)

| Pointer | Meaning                           |
| ------- | --------------------------------- |
| `l`     | position where next `0` should go |
| `m`     | current element being checked     |
| `h`     | position where next `2` should go |

At any moment:

```
[ 0s | 1s | unknown | 2s ]
     l     m          h
```

---

### ✅ Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int l = 0;
        int m = 0;
        int h = nums.length - 1;

        while (m <= h) {
            if (nums[m] == 0) {
                swap(nums, l, m);
                l++;
                m++;
            } else if (nums[m] == 1) {
                m++;
            } else { // nums[m] == 2
                swap(nums, m, h);
                h--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

---

### 🧩 Step-by-Step Example

#### Input

```
nums = [2,0,2,1,1,0]
l=0, m=0, h=5
```

#### Step-by-step evolution

```
[2,0,2,1,1,0] → swap m & h
[0,0,2,1,1,2]

[0,0,2,1,1,2] → swap m & l
[0,0,2,1,1,2]

[0,0,2,1,1,2] → swap m & l
[0,0,2,1,1,2]

[0,0,1,1,2,2] → swap m & h
STOP
```

---

### 🔑 Why pointer movement is critical

* `nums[m] == 0`
  → correct place is left
  → swap with `l`, move both

* `nums[m] == 1`
  → already correct
  → just move `m`

* `nums[m] == 2`
  → correct place is right
  → swap with `h`, move `h` only
  → **do not move `m`** (new value needs checking)

---

## 6️⃣ Why the Best Method Works (Dutch National Flag)

* Maintains **clear invariants**
* Every swap fixes at least one element
* Elements are never revisited unnecessarily
* Array is partitioned correctly in **one scan**

---

## 7️⃣ Comparison Summary

| Approach   | Passes | Extra Space | Difficulty    |
| ---------- | ------ | ----------- | ------------- |
| Counting   | 2      | O(1)        | Easy          |
| Dutch Flag | 1      | O(1)        | Medium (Best) |

---

## 8️⃣ Time & Space Complexity

### ⏱ Time

* Counting Method: `O(n)`
* Dutch Flag: `O(n)`

### 📦 Space

* Both use `O(1)` extra space

---

## 9️⃣ Key Takeaways (Must Remember)

* This is a **partitioning problem**, not sorting
* Counting works because values are limited
* Dutch National Flag uses **3 pointers**
* Never increment `m` when swapping with `h`
* `while (m <= h)` is mandatory
* Dutch Flag is the **interview-standard solution**

---

### 🧠 Final One-Liner

> **Pull 0s to the left, push 2s to the right, let 1s stay in the middle.**

