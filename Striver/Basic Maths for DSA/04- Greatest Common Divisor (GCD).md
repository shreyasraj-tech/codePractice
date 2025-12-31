# Greatest Common Divisor [(GCD)](https://www.geeksforgeeks.org/problems/gcd/1)
---

## 1️⃣ Explain the Problem

### 📌 Problem Statement  
Given **two integers `a` and `b`**, find the **Greatest Common Divisor (GCD)** —  
the **largest number that divides both `a` and `b` perfectly**.

### 🔗 Problem Link  
- GeeksforGeeks: https://www.geeksforgeeks.org/problems/gcd-of-two-numbers3459/1  
- LeetCode (Related): https://leetcode.com/problems/find-greatest-common-divisor-of-array/

### ✏️ Example
```

Input: a = 6, b = 9
Output: 3
Explanation: Common divisors → {1, 3}, greatest = 3

```

---

## 2️⃣ Similar Problems (Same Pattern)

- LCM of Two Numbers
- GCD of an Array
- Reduce Array Using GCD
- Coprime Numbers Check
- Simplifying Fractions
- Euclidean Algorithm Variants

All rely on **divisibility & remainder reduction**.

---

## 3️⃣ Core Logic Behind the Problem & Main Challenge

### 🎯 Goal  
Find a number `x` such that:
```

a % x == 0
b % x == 0

````

### ⚠️ Challenge
- Naively checking all numbers is slow
- Need a way to **reduce the search space**
- Must handle cases where `b > a`

---

## 4️⃣ Brute Force Solution & Why It’s Not Acceptable

### 🧠 Idea  
Check all numbers from `1` to `min(a, b)` and track the largest divisor.

### 💻 Code
```java
int gcd(int a, int b) {
    int res = 1;
    for (int i = 1; i <= Math.min(a, b); i++) {
        if (a % i == 0 && b % i == 0)
            res = i;
    }
    return res;
}
````

### ❌ Problems

* Time Complexity: **O(min(a, b))**
* Very slow for large numbers (e.g., 10⁹)
* Not interview-acceptable for optimized solutions

---

## 5️⃣ All Methods & Logic Behind Each

---

### ✅ Method 1: Reverse Brute Force (Slight Improvement)

**Logic:**
Start from `min(a, b)` and go downward — first match is GCD.

```java
int gcd(int a, int b) {
    for (int i = Math.min(a, b); i >= 1; i--) {
        if (a % i == 0 && b % i == 0)
            return i;
    }
    return 1;
}
```

* Faster in practice
* Still worst-case **O(min(a, b))**

---

### ✅ Method 2: Subtraction-Based Euclidean Idea

**Logic:**
If `x` divides both `a` and `b`, it also divides `(a - b)`.

So:

```
gcd(a, b) = gcd(b, a - b)
```

Repeat until numbers become equal.

```java
int gcd(int a, int b) {
    while (a != b) {
        if (a > b) a -= b;
        else b -= a;
    }
    return a;
}
```

* Correct
* But slow due to repeated subtraction

---

### ✅ Method 3: Euclidean Algorithm (Optimal)

**Key Insight:**

```
gcd(a, b) = gcd(b, a % b)
```

Modulo performs **multiple subtractions at once**.

```java
int gcd(int a, int b) {
    while (b != 0) {
        int rem = a % b;
        a = b;
        b = rem;
    }
    return a;
}
```

Handles all cases automatically:

* `b > a`
* Large numbers
* Stops fast

  
## The ONE rule you must remember

> **Always keep the smaller value in `b`.
> Always replace `b` with the remainder.**

That’s it.

---

## Why this rule exists (very important intuition)

* The **remainder is always smaller than `b`**
* We want numbers to **shrink**
* So:

  * `b` ← remainder (smaller)
  * `a` ← old `b`

---

## Fixed 3-step pattern (never change this order)

```java
rem = a % b;   // 1️⃣ compute remainder
a   = b;       // 2️⃣ move b into a
b   = rem;     // 3️⃣ put remainder into b
```

📌 **This order is NOT negotiable**

---

## The code we are visualizing

```java
int gcd(int a, int b) {
    while (b != 0) {
        int rem = a % b;
        a = b;
        b = rem;
    }
    return a;
}
```

---

# 🔍 Visualization with BIG & CLEAR examples

---

## Example 1️⃣

### `a = 48 , b = 18`

### Goal

Find the **largest number that divides both 48 and 18**

---

## STEP-BY-STEP TABLE (MOST IMPORTANT)

| Step  | a  | b  | a % b (rem) | Meaning                        |
| ----- | -- | -- | ----------- | ------------------------------ |
| Start | 48 | 18 | —           | Initial numbers                |
| 1     | 48 | 18 | 12          | Remove multiples of 18 from 48 |
| →     | 18 | 12 | —           | Replace (a, b)                 |
| 2     | 18 | 12 | 6           | Remove multiples of 12 from 18 |
| →     | 12 | 6  | —           | Replace (a, b)                 |
| 3     | 12 | 6  | 0           | Perfect division               |
| →     | 6  | 0  | —           | STOP                           |

### ✅ Answer = **6**

---

## What is actually happening?

### Step 1

```
48 = 18 × 2 + 12
```

* Anything that divides **both 48 & 18**
* Must also divide **12**
  ➡️ Reduce problem to `(18, 12)`

---

### Step 2

```
18 = 12 × 1 + 6
```

➡️ Reduce to `(12, 6)`

---

### Step 3

```
12 = 6 × 2 + 0
```

Once remainder becomes `0`
➡️ **GCD = last non-zero number = 6**

---

# 🔍 Example 2️⃣ (b > a case — very important)

### `a = 24 , b = 96`

---

### Table

| Step  | a  | b  | rem  |
| ----- | -- | -- | ---- |
| Start | 24 | 96 | —    |
| 1     | 24 | 96 | 24   |
| →     | 96 | 24 | —    |
| 2     | 96 | 24 | 0    |
| →     | 24 | 0  | STOP |

### ✅ Answer = **24**

### Key insight

When `b > a`:

```
a % b = a
```

So numbers **automatically swap**
No special handling needed.

---

# 🔍 Example 3️⃣ (Very large numbers)

### `a = 270 , b = 192`

---

### Table

| Step  | a   | b   | rem  |
| ----- | --- | --- | ---- |
| Start | 270 | 192 | —    |
| 1     | 270 | 192 | 78   |
| →     | 192 | 78  | —    |
| 2     | 192 | 78  | 36   |
| →     | 78  | 36  | —    |
| 3     | 78  | 36  | 6    |
| →     | 36  | 6   | —    |
| 4     | 36  | 6   | 0    |
| →     | 6   | 0   | STOP |

### ✅ Answer = **6**

---

# 🧠 Think of it like a number-shrinking machine

Each loop does this:

```
(a, b)
↓
(b, a % b)
↓
(b gets smaller every time)
↓
STOP when b == 0
```

The **GCD never disappears** — only noise disappears.

---

# 🧩 Why remainder becomes the key

Remainder is:

```
a % b = what is left after removing common chunks
```

Anything that divides **both numbers**
➡️ must divide what’s left

So checking only the remainder is **safe**

---

# 🎯 Ultra-clear mental picture

Imagine you have sticks of length `a` and `b`
You keep cutting the bigger one using the smaller

The **last length that fits perfectly**
➡️ **GCD**

---

# 🔑 One-line lock-in rule

> **GCD is the last non-zero remainder when repeatedly dividing**


## 6️⃣ Why the Best Method Works (Euclidean Algorithm)

### 🔑 Mathematical Truth

If:

```
a = x * m
b = x * n
```

Then:

```
a % b = x * (m - n * k)
```

So any number dividing both `a` and `b` also divides `a % b`.

➡️ GCD is **preserved**, numbers keep shrinking.

When `b == 0`:

```
gcd(a, 0) = a
```

That `a` is the answer.

---



## 7️⃣ Comparison Summary

| Method        | Idea                | Time         | Accepted |
| ------------- | ------------------- | ------------ | -------- |
| Brute Force   | Check all divisors  | O(min)       | ❌        |
| Reverse Loop  | Early stop          | O(min)       | ❌        |
| Subtraction   | Repeated difference | O(max)       | ❌        |
| Euclidean (%) | Remainder reduction | **O(log n)** | ✅        |

---

## 8️⃣ Time & Space Complexity

### ⏱ Time

* Brute Force: `O(min(a, b))`
* Euclidean Algorithm: **O(log min(a, b))**

### 📦 Space

* Iterative Euclid: `O(1)`
* Recursive Euclid: `O(log n)` (stack)

---

## 9️⃣ Key Takeaways (Must Remember)

* GCD = **largest number dividing both**
* Common divisors **survive subtraction**
* `%` is fast repeated subtraction
* `a % b` auto-handles `b > a`
* **Last non-zero remainder = GCD**
* Euclidean Algorithm is **standard & optimal**

---

### ✅ Final One-Liner

> **We don’t search for the divisor — we reduce the numbers until only the divisor remains.**

```
```
