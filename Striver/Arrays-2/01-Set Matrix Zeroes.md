# Set Matrix Zeroes [Leetcodelink](https://leetcode.com/problems/set-matrix-zeroes/)
---

**Problem Statement**
Given an `m x n` integer matrix, if an element is `0`, set its entire **row** and **column** to `0`.
You must do it **in-place**.

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
All the 3 methods of the solution;
---
### ✅ Method 1: Extra Space using Row & Column Arrays

**(Approach Explanation – Logic Only)**

---

### 🔍 Core Idea

Instead of modifying the matrix immediately (which can corrupt future decisions), we **separate detection from modification**.

We do this by:

* **First pass → only observe**
* **Second pass → only update**

To remember which rows and columns must become zero, we use **two helper arrays**.

---

### 🧠 Intuition Behind the Approach

If a cell `(i, j)` is `0`, then:

* Entire **row `i`** must be zero
* Entire **column `j`** must be zero

So instead of changing the matrix directly:

* Just **record** this information somewhere safe.

That “somewhere” is:

* `row[i] = true` → row `i` should be zeroed
* `col[j] = true` → column `j` should be zeroed

---

### 🧩 Step-by-Step Thinking

#### Step 1: Create Marker Arrays

* `row[]` → size = number of rows
* `col[]` → size = number of columns

These arrays act as **memory**.

---

#### Step 2: First Traversal (Detection Phase)

* Traverse every cell in the matrix
* If `matrix[i][j] == 0`:

  * Mark `row[i] = true`
  * Mark `col[j] = true`

🚫 **Important**
No changes are made to the matrix yet.

This avoids the cascade problem.

---

#### Step 3: Second Traversal (Modification Phase)

* Traverse the matrix again
* For each cell `(i, j)`:

  * If `row[i] == true` **OR** `col[j] == true`

    * Set `matrix[i][j] = 0`

Now the matrix is safely updated using previously stored information.

---

### 🧪 Example Walkthrough

**Input**

```
1  1  1
1  0  1
1  1  1
```

**After First Pass**

```
row = [false, true, false]
col = [false, true, false]
```

**After Second Pass**

```
1  0  1
0  0  0
1  0  1
```

✔️ Correct output.

---


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
### ❓ Why This Works

* We **never modify** the matrix until we have **complete information**
* The original zero positions are preserved
* No accidental cascading of zeroes

---

### ⚠️ Limitation of This Approach

* Uses extra space:

  * `O(m + n)`
* Not optimal when the problem explicitly demands **O(1)** space


---

### ✅ Method 2: Using Temporary Marker Value (Conceptual / Not Recommended)

> This method is **not recommended for interviews**, but it is extremely useful to understand **why naive in-place updates fail** and **how data corruption happens**.

---

## 🔍 Core Idea

When you find a `0` at position `(i, j)`:

* The entire **row `i`**
* The entire **column `j`**

must become `0`.

The problem is:

> If you immediately change them to `0`, you may create **new zeroes** that were **not originally present**, which breaks correctness.

### So the workaround is:

* Do **in-place marking**, but
* Use a **temporary value** (like `-1`) instead of `0`.

This helps distinguish:

* **Original zeroes**
* **Zeroes that are supposed to be set later**

---

## 🧠 Why a Temporary Marker is Needed

Consider:

```
1  1  1
1  0  1
1  1  1
```

If you update immediately:

```
1  0  1
0  0  0
1  0  1
```

Now the newly created `0`s will trigger **additional row/column zeroing**, which is wrong.

### Solution:

Use a marker value (`-1`) to say:

> “This cell must be zero later, but it was NOT originally zero.”

---

## 🧩 Step-by-Step Logic

### Step 1: Traverse the Matrix

* When `matrix[i][j] == 0`:

  * Traverse the entire **row `i`**

    * Change non-zero values to `-1`
  * Traverse the entire **column `j`**

    * Change non-zero values to `-1`

🚫 Do **not** touch existing zeroes.

---

### Step 2: Final Cleanup

* Traverse the matrix again
* Convert all `-1` → `0`

---

## 🧪 Example Walkthrough

### Input

```
1  1  1
1  0  1
1  1  1
```

### After Marking Phase

```
1  -1  1
-1  0  -1
1  -1  1
```

### After Cleanup Phase

```
1  0  1
0  0  0
1  0  1
```

✔️ Correct result **in this case**.

---

## ❌ Why This Method Is Dangerous

### 1️⃣ Data Assumption Problem

* Assumes `-1` does **not** exist in input
* Real problems do not guarantee this


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

## 🧠 What This Method Teaches You

Even though it’s flawed, it teaches **critical lessons**:

* ❌ Immediate in-place updates cause corruption
* ✅ You must preserve original state
* ⚠️ Temporary markers are risky
* 🎯 Leads naturally to:

  * Extra space solution (Method 1)
  * In-place marker optimization (Method 3)

---

### ✅ Method 3: **Optimal In-Place Solution (Best Method)**

Below is a **logic-first explanation of Method 3 (Optimal In-Place)** followed by the **annotated Java code**.
Read the logic first → the code will feel obvious.
---

## 💡 Core Observation

We only need to remember:

* **Which rows** contain a zero
* **Which columns** contain a zero

Instead of extra arrays, we reuse:

* **First row** → column markers
* **First column** → row markers

⚠️ But before reusing them, we must **save their original state**.

---

## 🔐 Step 1 & 2 — Protect First Row and First Column

If we overwrite them without checking, we lose information.

```java
boolean firstRowZero = false;
boolean firstColZero = false;

// Check first row
for (int j = 0; j < matrix[0].length; j++) {
    if (matrix[0][j] == 0) {
        firstRowZero = true;
        break;
    }
}

// Check first column
for (int i = 0; i < matrix.length; i++) {
    if (matrix[i][0] == 0) {
        firstColZero = true;
        break;
    }
}
```

## 🧠 Step 3 — Use First Row & Column as Markers

Now we safely record decisions.

```java
for (int i = 1; i < matrix.length; i++) {
    for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
            matrix[i][0] = 0; // mark row
            matrix[0][j] = 0; // mark column
        }
    }
}
```

🧠 **Logic**

* We **do not zero anything yet**
* We only say:

  * “Row `i` needs to be zero”
  * “Column `j` needs to be zero”

---

## 🧠 Step 4 — Apply Markers to Inner Matrix

Now all decisions are known.

```java
for (int i = 1; i < matrix.length; i++) {
    for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
            matrix[i][j] = 0;
        }
    }
}
```

🧠 **Logic**

* If row OR column is marked → cell must be zero
* Safe because markers are stable

---

## 🧠 Step 5 — Handle First Row Last

```java
if (firstRowZero) {
    for (int j = 0; j < matrix[0].length; j++) {
        matrix[0][j] = 0;
    }
}
```

🧠 **Logic**

* We delayed this so column markers weren’t destroyed early

---

## 🧠 Step 6 — Handle First Column Last

```java
if (firstColZero) {
    for (int i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
    }
}
```

🧠 **Logic**

* Same reasoning as first row

---

## ✅ Complete Java Code (Method 3)

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

        // Step 3: Mark rows and columns
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 4: Apply zeroes using markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Zero first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

---

## 🧠 One-Line Memory Hook

> **“Save the truth, mark intent, apply decisions, fix boundaries.”**

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
