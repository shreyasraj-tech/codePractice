# Bitwise Operations

## Check if a number is even or odd

### What does it mean to "Check if a number is even or odd"?
Determine whether a number is divisible by 2 (even) or not (odd).

### The Expression:
```
n & 1
```

### Example:
```c
int n = 5;
if (n & 1)
    printf("Odd\n");
else
    printf("Even\n");
```

### Summary of the Operation:
If the least significant bit (LSB) is 1, the number is odd; otherwise, it is even.

---

## Swap two numbers without using a temporary variable

### What does it mean to "Swap two numbers without using a temporary variable"?
Exchange values of two variables without using extra memory for a temporary variable.

### The Expression:
```
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

### Example:
```c
int a = 3, b = 5;
a = a ^ b;
b = a ^ b;
a = a ^ b;
// Now a = 5, b = 3
```

### Summary of the Operation:
XOR swapping avoids needing an extra variable by exploiting properties of XOR.

---

## Check if a number is a power of 2

### What does it mean to "Check if a number is a power of 2"?
Verify if a number is an exact power of 2.

### The Expression:
```
(n & (n - 1)) == 0
```

### Example:
```c
int n = 8;
if (n > 0 && (n & (n - 1)) == 0)
    printf("Power of 2\n");
else
    printf("Not a power of 2\n");
```

### Summary of the Operation:
A power of 2 has only one set bit; this trick checks for it efficiently.

---

## Get the ith bit of a number

### What does it mean to "Get the ith bit of a number"?
Extract the value of the ith bit (either 0 or 1) from a number.

### The Expression:
```
(n >> i) & 1
```

### Example:
```c
int n = 5; // 101
int i = 1;
int bit = (n >> i) & 1; // bit = 0
```

### Summary of the Operation:
Right shift and mask with 1 to isolate the ith bit.

---

## Set the ith bit of a number

### What does it mean to "Set the ith bit of a number"?
Force the ith bit of a number to become 1.

### The Expression:
```
n | (1 << i)
```

### Example:
```c
int n = 5; // 101
int i = 1;
n = n | (1 << i); // n becomes 7 (111)
```

### Summary of the Operation:
OR the number with a mask to set the ith bit.

---

## Clear the ith bit of a number

### What does it mean to "Clear the ith bit of a number"?
Reset the ith bit of a number to 0.

### The Expression:
```
n & ~(1 << i)
```

### Example:
```c
int n = 5; // 101
int i = 2;
n = n & ~(1 << i); // n becomes 1 (001)
```

### Summary of the Operation:
Use AND with the negation of the mask to clear the ith bit.

---

## Toggle the ith bit of a number

### What does it mean to "Toggle the ith bit of a number"?
Flip the ith bit of a number (1 to 0, or 0 to 1).

### The Expression:
```
n ^ (1 << i)
```

### Example:
```c
int n = 5; // 101
int i = 0;
n = n ^ (1 << i); // n becomes 4 (100)
```

### Summary of the Operation:
Use XOR with a mask to toggle the specific bit.

---

## Count the number of set bits (1s) in a number

### What does it mean to "Count the number of set bits (1s) in a number"?
Count how many bits are set to 1 in a number's binary form.

### The Expression:
```
while (n) {
    n = n & (n - 1);
    count++;
}
```

### Example:
```c
int n = 5; // 101
int count = 0;
while (n) {
    n = n & (n - 1);
    count++;
}
// count = 2
```

### Summary of the Operation:
Each iteration clears the rightmost set bit, efficiently counting all set bits.

---

## Find the only non-repeating element in an array where every other element repeats twice

### What does it mean to "Find the only non-repeating element"?
Identify the unique element in an array where others appear exactly twice.

### The Expression:
```
result = 0;
for each element e in array:
    result ^= e;
```

### Example:
```c
int arr[] = {2, 3, 2, 4, 4};
int result = 0;
for (int i = 0; i < 5; i++) {
    result ^= arr[i];
}
// result = 3
```

### Summary of the Operation:
Pairs cancel out using XOR, leaving the unique element.

---

## Find the position of the rightmost set bit

### What does it mean to "Find the position of the rightmost set bit"?
Locate the value of the least significant set bit.

### The Expression:
```
n & (-n)
```

### Example:
```c
int n = 18; // 10010
int result = n & -n; // result = 2
```

### Summary of the Operation:
Using two's complement and AND operation isolates the rightmost set bit.
