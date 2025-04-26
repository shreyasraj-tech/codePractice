## 322. Coin Change

Here is the link to the problem on LeetCode: [322. Coin Change](https://leetcode.com/problems/coin-change/)

### Problem Description

You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return `-1`.

You may assume that you have an infinite number of each kind of coin.

### Example 1:

- **Input**: `coins = [1,2,5]`, `amount = 11`
- **Output**: `3`
- **Explanation**: 
  - 11 = 5 + 5 + 1
  - So, the minimum coins needed are 3.

### Example 2:

- **Input**: `coins = [2]`, `amount = 3`
- **Output**: `-1`
- **Explanation**: 
  - It is not possible to make the amount 3 with only coin of denomination 2.

### Example 3:

- **Input**: `coins = [1]`, `amount = 0`
- **Output**: `0`
- **Explanation**: 
  - Zero amount requires zero coins.

### Solution

Here is a Java solution for the problem:

```java
import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, amount + 1);    
        minCoins[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i - coin]);
            }
        }

        if (minCoins[amount] <= amount) {
            return minCoins[amount];
        } else {
            return -1;
        }
    }
}
```

### Explanation

1. **Initialization**:
   - Create an array `minCoins` where `minCoins[i]` represents the minimum number of coins needed to make up the amount `i`.
   - Initialize all values to `amount + 1` (an impossible high value), except `minCoins[0] = 0` since zero coins are needed to make zero amount.

2. **Filling the DP Array**:
   - For every coin, iterate from `coin` to `amount`.
   - Update `minCoins[i]` by comparing its current value with `1 + minCoins[i - coin]`.
   - The idea is: if you pick the current coin, you add 1 to the result of the subproblem `amount - coin`.

3. **Final Result**:
   - If `minCoins[amount]` is still greater than `amount`, it means it's impossible to form that amount with given coins, so return `-1`.
   - Otherwise, return `minCoins[amount]` as the minimum number of coins needed.

This dynamic programming approach ensures that all subproblems are solved optimally and used to build the final solution.
