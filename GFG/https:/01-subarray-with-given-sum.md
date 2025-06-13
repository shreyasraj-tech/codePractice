## Find Subarray With Given Sum (1-Indexed)

### Problem Statement

You are given:
- An integer `N` representing the number of elements in an array.
- An integer `S` representing a target sum.
- An array of `N` **positive integers** using **1-based indexing** (i.e., elements are indexed from 1 to N).

Your task is to find the **starting and ending index positions** (1-based) of a **continuous subarray** whose elements add up **exactly** to the target sum `S`.

- If **multiple** such subarrays exist, return the **leftmost** one.
- If **no such subarray** exists, print `"No subarray found"`.
- If the **total sum** of the array is **less than `S`**, print `"Invalid"`.

---

### Input Format

- **First Line**: Two integers `N` and `S`
- **Second Line**: `N` space-separated positive integers (1-indexed)

---

### Output Format

- If a subarray is found: print two integers `start` and `end` representing the 1-based indices of the subarray.
- If no subarray sums to `S`: print `"No subarray found"`
- If the sum of the entire array is less than `S`: print `"Invalid"`

---

### Example

**Input:**
