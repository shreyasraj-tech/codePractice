# Set Matrix Zeroes [Leetcodelink](https://leetcode.com/problems/set-matrix-zeroes/)
---

**Problem Statement**
Given an `m x n` integer matrix, if an element is `0`, set its entire **row** and **column** to `0`.
You must do it **in-place**.

🔗 **Problem Link**:


---

## 2. Similar Problems (Same Pattern)

These problems follow the **“marker / in-place optimization”** pattern:

* Game of Life
* Rotate Image
* Zero Matrix (Cracking the Coding Interview)
* Product of Array Except Self
* First Missing Positive

👉 **Common Theme**:
Using **existing space cleverly** to avoid extra memory.

---

## 3. Core Logic Behind the Problem & Challenge

### Core Requirement

* Any `0` affects **entire row + entire column**
* Multiple zeroes may overlap
* Must modify **in-place**

### Main Challenge

* If you set zeroes immediately, you **lose original information**
* Once a row/column is zeroed, you can’t tell whether it was **originally zero**

⚠️ **Key Difficulty**
Avoid cascading zeroes while keeping **O(1) extra space**.

---

## 4. Brute Force Solution & Why It’s Not Acceptable

### Idea

* When a `0` is found:

  * Mark its entire row and column as `0` immediately

### Problem with This

```
Original:
1 1 1
1 0 1
1 1 1

After first update:
1 0 1
0 0 0
1 0 1
```

Now new zeroes appear, causing **incorrect cascading updates**.

### Fix Attempt

* Mark affected cells as `-1` temporarily
* Then convert all `-1` to `0`

### Why It’s Not Ideal

* Modifies original data assumptions
* Fails if matrix contains `-1`
* Not clean or safe

---

## 5. All Methods & Logic Explanation

---

### 🔹 Method 1: Extra Space (Row & Column Arrays)

#### Logic

* Create two arrays:

  * `row[m]`
  * `col[n]`
* First pass → record which rows & columns have zero
* Second pass → update matrix
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        // Step 1: Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Step 2: Update matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

```


#### Pros

* Easy to understand

#### Cons

* Uses extra space

---

### 🔹 Method 2: Using Temporary Marker Value (Conceptual / Not Recommended)

#### Logic

* Store row indices and column indices containing zero
* In second pass, zero them out
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Step 1: Mark affected cells with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // Mark row
                    for (int k = 0; k < n; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = -1;
                        }
                    }
                    // Mark column
                    for (int k = 0; k < m; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = -1;
                        }
                    }
                }
            }
        }

        // Step 2: Convert -1 to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}


```


#### Drawback

* Space complexity is not optimal

---

### 🔹 Method 3: **Optimal In-Place Solution (Best Method)**

#### Core Idea

Use **first row and first column as markers**

#### Steps

1. **Check if first row has zero**
2. **Check if first column has zero**
3. Traverse matrix (excluding first row & column):

   * If `matrix[i][j] == 0`

     * Mark → `matrix[i][0] = 0`
     * Mark → `matrix[0][j] = 0`
4. Traverse again:

   * If row marker or column marker is `0`, set cell to `0`
5. Finally handle:

   * First row
   * First column

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Check first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 2: Check first column
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 3: Use first row & column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 4: Set zeroes based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Handle first row
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Handle first column
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}


```
#### Why This Works

* Uses matrix itself as memory
* No information loss
* Order of operations prevents corruption

---

## 6. Why the Best Method Works

✅ Uses **matrix as its own marker**
✅ Avoids extra space
✅ No cascading errors
✅ Clean, deterministic update order

🎯 **Key Insight**

> First row & first column are enough to remember everything.

---

## 7. Comparison Summary

| Method                     | Extra Space | In-Place | Safe | Recommended |
| -------------------------- | ----------- | -------- | ---- | ----------- |
| Brute Force                | ❌           | ❌        | ❌    | ❌           |
| Row & Col Arrays           | ✅ O(m+n)    | ❌        | ✅    | ⚠️          |
| Set / Hashing              | ✅           | ❌        | ✅    | ⚠️          |
| **First Row & Col Marker** | ✅ O(1)      | ✅        | ✅    | ✅🔥         |

---

## 8. Time & Space Complexity

### Optimal Solution

* **Time Complexity**:
  `O(m × n)`

* **Space Complexity**:
  `O(1)` (constant extra space)

---

## 9. Key Takeaways (Must Remember)

* Never modify immediately when future logic depends on original data
* First row & first column can act as **implicit memory**
* Handle first row & column **separately**
* Order of traversal matters
* Classic **in-place optimization problem**

---

✅ **This problem is a blueprint for solving many in-place matrix problems.**
If you master this pattern, multiple matrix questions become straightforward.

If you want, I can also:

* Add **dry-run diagrams**
* Convert this into **interview-ready revision notes**
* Provide **Java implementation with comments**
