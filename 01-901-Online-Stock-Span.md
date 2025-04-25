## 901. Online Stock Span

Here is the link to the problem on LeetCode: [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/)

### Problem Description

Design an algorithm that collects daily stock prices and returns the span of the stock’s price for the current day.

The span of the stock’s price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.

- For example, if the price of a stock over the next 7 days were `[100, 80, 60, 70, 60, 75, 85]`, then the stock spans would be `[1, 1, 1, 2, 1, 4, 6]`.

Implement the `StockSpanner` class:
- `StockSpanner()` Initializes the object of the class.
- `int next(int price)` Returns the span of the stock's price given that today's price is `price`.

### Example:

- **Input**:
  ```
  ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
  [[], [100], [80], [60], [70], [60], [75], [85]]
  ```
- **Output**:
  ```
  [null, 1, 1, 1, 2, 1, 4, 6]
  ```

- **Explanation**:
  ```
  StockSpanner stockSpanner = new StockSpanner();
  stockSpanner.next(100); // return 1
  stockSpanner.next(80);  // return 1
  stockSpanner.next(60);  // return 1
  stockSpanner.next(70);  // return 2
  stockSpanner.next(60);  // return 1
  stockSpanner.next(75);  // return 4
  stockSpanner.next(85);  // return 6
  ```

### Solution

Here is a Java solution for the problem:

```java
import java.util.Stack;

class StockSpanner {

    // Stack stores: [price, span]
    private Stack<int[]> stack;

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;

        // Pop while the price at the top of the stack is less than or equal to current price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];  // Add their spans to current span
        }

        // Push current price and its span onto the stack
        stack.push(new int[]{price, span});

        return span;
    }
}
```

### Explanation

1. **Use of Stack**:
   - The stack keeps track of prices and their corresponding span.
   - Each element is an array of `[price, span]`.

2. **Pop and Accumulate**:
   - When a new price comes in, it checks how many consecutive past prices were less than or equal to the current price.
   - These are popped from the stack and their spans are added up to compute the total span for the current day.

3. **Push to Stack**:
   - After calculating the span, push the current price and its span onto the stack for future comparisons.

This efficient approach avoids rechecking older elements repeatedly and ensures optimal performance even for large inputs.
