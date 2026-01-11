# **LeetCode – Merge Intervals**
[https://leetcode.com/problems/merge-intervals/](https://leetcode.com/problems/merge-intervals/)

### 🔹 Problem Statement

Given a collection of intervals where each interval is represented as `[start, end]`, merge all **overlapping intervals** and return the resulting set of **non-overlapping intervals** that cover all original intervals.

### 🔹 Example

**Input**

```
[[1,3], [2,6], [8,10], [15,18]]
```

**Output**

```
[[1,6], [8,10], [15,18]]
```

---

## 2️⃣ Similar Problems (Same Pattern)

All problems below follow the **interval sorting + merging pattern**:

* Insert Interval
* Meeting Rooms / Meeting Rooms II
* Minimum Number of Platforms
* Non-overlapping Intervals
* Interval List Intersections
* Employee Free Time

👉 **Key Pattern:**

> Sort → Compare with previous → Merge or push

---

## 3️⃣ Core Logic Behind the Problem & Main Challenge

### 🔹 Core Idea

Two intervals overlap **if and only if**:

```
current.start <= previous.end
```

### 🔹 Main Challenge

* Intervals are **unsorted**
* Overlaps can be **partial or complete**
* Must merge **in-place logically** without losing data

### 🔹 Key Insight

> Once intervals are sorted by start time,
> **only the last merged interval needs to be checked**

---

## 4️⃣ Brute Force Solution & Why It’s Not Acceptable

### 🔹 Brute Force Idea

* Compare every interval with every other interval
* Merge overlapping ones
* Repeat until no changes occur

### 🔹 Time Complexity

```
O(n²)
```

### 🔹 Why It’s Not Acceptable

* Inefficient for large inputs
* Redundant comparisons
* Fails performance constraints in interviews and online judges

---

## 5️⃣ All Methods & Logic Behind Each

---

### 🔹 Method 1: Brute Force (Rejected)

**Logic**

* Nested loops
* Repeated merging
* Hard to maintain

❌ **Not interview-safe**

---

### 🔹 Method 2: Sorting + Extra Nested Scan (Sub-Optimal)

**Logic**

* Sort intervals
* For each interval, scan ahead to merge

**Issue**

* Still uses inner loop
* Time complexity remains `O(n²)` in worst case

⚠️ **Better, but still avoid**

---

### 🔹 Method 3: Optimal Method (Best & Accepted)

**Logic**

1. Sort intervals by start time
2. Maintain a result list
3. Compare current interval with last merged interval
4. Merge if overlapping, else add new interval

✔️ Single pass after sorting
✔️ Clean and readable
✔️ Interview standard

---

## 6️⃣ Why the Best Method Works

### 🔹 Key Reason

After sorting:

* Any overlapping interval **must appear next to each other**
* No need to check all previous intervals

### 🔹 Mathematical Guarantee

If intervals are sorted by `start`:

```
Only last merged interval can overlap with current
```

This reduces unnecessary comparisons and guarantees correctness.

---

## 7️⃣ Comparison Summary

| Approach             | Sorting | Loops  | Time       | Interview Safe |
| -------------------- | ------- | ------ | ---------- | -------------- |
| Brute Force          | ❌       | Nested | O(n²)      | ❌              |
| Partial Optimization | ✅       | Nested | O(n²)      | ⚠️             |
| Optimal Method       | ✅       | Single | O(n log n) | ✅              |

---

## 8️⃣ Time & Space Complexity

### 🔹 Time Complexity

```
O(n log n)
```

* Sorting dominates

### 🔹 Space Complexity

```
O(n)
```

* For storing merged intervals

---

## 9️⃣ Key Takeaways (Must Remember)

✔ Always **sort intervals by start time first**
✔ Overlap condition:

```
current.start <= last.end
```

✔ Only compare with **last merged interval**
✔ Nested loops are **red flags** in interval problems
✔ This pattern appears frequently in interviews

---

### 🔥 One-Line Interview Summary

> “Sort the intervals, then merge by comparing each interval with the last merged one.”

---

If you want next:

* Same notes but **array-only (no ArrayList)**
* Dry run with tricky edge cases
* Convert this into **revision flash notes**
* Visual explanation (timeline-style)

Just say 👍
