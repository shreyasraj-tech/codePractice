# 🔁 Reverse Integer (LeetCode 7)
🔗 Problem Link: https://leetcode.com/problems/reverse-integer/

---

## 📘 Explain the Problem

You are given a **32-bit signed integer `x`**.

Your task is to **reverse its digits** and return the reversed number.

### Rules:
- If reversing `x` causes the value to go **outside the signed 32-bit integer range**
```

[-2^31, 2^31 − 1]  →  [-2147483648, 2147483647]

````
then **return `0`**.
- The solution **must not use strings** (math-based approach expected).

---

### Examples

| Input | Output |
|------|--------|
| 123 | 321 |
| -123 | -321 |
| 120 | 21 |
| 1534236469 | 0 (overflow) |

---

## 🔍 Similar Problems (Same Pattern)

This problem belongs to the **Digit Manipulation** pattern.

Common techniques used:
- `% 10` → extract last digit
- `/ 10` → remove last digit
- Loop until number becomes `0`

### Related Problems:
- Palindrome Number
- Add Digits
- Sum of Digits
- Armstrong Number
- Count Digits
- Integer to Roman / Roman to Integer

👉 Once you master this pattern, many number-based problems become easy.

---

## 🧠 Core Logic Behind the Problem

### Digit Extraction Logic

For any integer `x`:
```java
lastDigit = x % 10;
x = x / 10;
````

This works for:

* Positive numbers
* Negative numbers (Java preserves sign)

### Building the Reverse

To append a digit:

```java
rev = rev * 10 + lastDigit;
```

### Loop Condition

```java
while (x != 0)
```

Why?

* Works for **both positive and negative numbers**
* Stops only when all digits are processed

❌ `x > 0` would fail for negative inputs

---

## ⚠️ The Real Challenge: Overflow

Reversing may exceed `int` range.

### Example:

```text
x = 1534236469
reverse → 9646324351 ❌ (overflow)
```

Java does **not throw an error** on overflow — it silently wraps.
So we must **check before multiplying**.

---

## 🚀 Optimized & Accepted Java Solution

### ✅ Overflow-safe, clean, interview-grade

```java
class Solution {
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int digit = x % 10;

            // Overflow check (positive)
            if (rev > Integer.MAX_VALUE / 10 ||
               (rev == Integer.MAX_VALUE / 10 && digit > 7))
                return 0;

            // Overflow check (negative)
            if (rev < Integer.MIN_VALUE / 10 ||
               (rev == Integer.MIN_VALUE / 10 && digit < -8))
                return 0;

            rev = rev * 10 + digit;
            x /= 10;
        }

        return rev;
    }
}
```

---

## 🧠 Why This Logic Is Optimal

* No extra space
* No strings
* Sign handled naturally
* Overflow predicted **before it happens**
* Passes **all LeetCode test cases**

---



---

## 🚀 Method 2 — Using `long` (Simplest & Cleanest)

### Core Idea

Use a `long` to safely store intermediate results and check bounds **only once at the end**.

### Java Code

```java
class Solution {
    public int reverse(int x) {
        long rev = 0;

        while (x != 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }

        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
            return 0;

        return (int) rev;
    }
}
```

---

## ✅ Why Method 2 Works Well

* `long` prevents intermediate overflow
* Sign is handled automatically
* Very readable and easy to explain
* Passes **all LeetCode test cases**

---

## ❌ When Method 2 is NOT Allowed

Do **NOT** use this approach when the problem explicitly states:

* 🚫 “No extra space”
* 🚫 “Do not use larger data types than required”
* 🚫 “Do not use 64-bit integers”
* 🚫 Embedded / low-level / system programming constraints

### Why?

* `long` is a **64-bit data type**
* It violates the **constraint discipline** of the problem
* Interviewers want to test **manual overflow prediction**

---

## ⚖️ Comparison Summary

| Aspect            | Method 1 (int only) | Method 2 (long) |
| ----------------- | ------------------- | --------------- |
| LeetCode Accepted | ✅                   | ✅               |
| Code Simplicity   | ❌                   | ⭐⭐⭐⭐            |
| Overflow Safety   | Manual              | Automatic       |
| Interview Depth   | ⭐⭐⭐⭐                | ⭐⭐              |
| Constraint-Safe   | ✅                   | ❌ (sometimes)   |

---



## ⏱️ Time & Space Complexity

### Time Complexity

```
O(log₁₀ n)
```

* One iteration per digit

### Space Complexity

```
O(1)
```

* Only constant extra variables used

---

## 🧩 Key Takeaways (Must Remember)

* Use `x != 0`, not `x > 0`
* `% 10` and `/ 10` work for negatives in Java
* Never use `Math.abs(x)` (breaks for `Integer.MIN_VALUE`)
* Overflow must be checked **before** updating `rev`
* This is a **fundamental interview pattern**




```
```
