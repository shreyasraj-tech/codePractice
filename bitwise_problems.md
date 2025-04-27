
# Simple Bitwise Manipulation Problems

### 1. Check if a number is even or odd
- Use `(n & 1)`

### 2. Swap two numbers without using a temporary variable
- Use XOR:
```java
a = a ^ b  
b = a ^ b  
a = a ^ b
```

### 3. Check if a number is a power of 2
- Use `(n & (n - 1)) == 0`

### 4. Get the ith bit of a number
- Use `(n >> i) & 1`

### 5. Set the ith bit of a number
- Use `n | (1 << i)`

### 6. Clear the ith bit of a number
- Use `n & ~(1 << i)`

### 7. Toggle the ith bit of a number
- Use `n ^ (1 << i)`

### 8. Count the number of set bits (1s) in a number
- Use `n = n & (n-1)` in a loop.

### 9. Find the only non-repeating element in an array where every other element repeats twice
- Use XOR of all elements.

### 10. Find the position of the rightmost set bit
- Use `n & (-n)`
