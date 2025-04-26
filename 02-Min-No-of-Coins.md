## Minimum number of Coins - Greedy

Here is the link to the problem on GEEKS FOR GEEKS: [Minimum number of Coins](https://www.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1)

### Problem Description

Given an infinite supply of coins of different denominations and a total amount of money `N`. The task is to find the minimum number of coins required to make the given amount. You may assume all coin denominations are available in infinite quantity.

### Example 1:

- **Input**: `N = 43`
- **Output**: `[20, 20, 2, 1]`
- **Explanation**: 
  - 20 + 20 + 2 + 1 = 43

### Example 2:

- **Input**: `N = 7`
- **Output**: `[5, 2]`
- **Explanation**:
  - 5 + 2 = 7

### Solution

Here is a Java solution for the problem:

```java
import java.util.*;

class Solution {
    static List<Integer> minPartition(int N) {
        // array of available coins
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
        
        List<Integer> coinList = new ArrayList<>();
        for (int coin : coins) {
            coinList.add(coin);
        }

        // Reverse the list to start with the largest coin
        Collections.reverse(coinList);
        
        List<Integer> res = new ArrayList<>();
        
        // Choose coins greedily
        for (int coin : coinList) {
            while (N >= coin) {
                N -= coin;
                res.add(coin);
            }
        }
        return res;
    }
}
```

### Explanation

1. **Coin List Preparation**:
   - Store all the available coin denominations in a list.
   - `Reverse the list so that we start checking from the highest denomination.`

2. **Greedy Approach**:
   - Iterate through the list and pick the largest coin possible until the remaining amount becomes zero.
   - Each time a coin is picked, subtract its value from the total amount `N`.
   - Continue until `N` becomes zero.

This greedy strategy ensures that the minimum number of coins are used to reach the desired amount.
