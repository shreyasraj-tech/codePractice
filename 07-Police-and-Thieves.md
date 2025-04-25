## Police and Thieves

Here is the link to the problem on GeeksforGeeks: [Police and Thieves](https://www.geeksforgeeks.org/problems/police-and-thieves--141631/0)

### Problem Description

Given an array of size `n` such that each element contains either a `'P'` (denoting police) or a `'T'` (denoting thief). The police can catch the thief if the distance between them is at most `k`. The task is to find the maximum number of thieves that can be caught.


### Example 1:

- **Input**: `arr = ['P', 'T', 'T', 'P', 'T']`, `k = 1`
- **Output**: `2`
- **Explanation**: 
  - Police at index 0 can catch thief at index 1.
  - Police at index 3 can catch thief at index 4.

### Example 2:

- **Input**: `arr = ['T', 'T', 'P', 'P', 'T', 'P']`, `k = 2`
- **Output**: `3`
- **Explanation**: 
  - Police at index 2 can catch thief at index 0.
  - Police at index 3 can catch thief at index 1.
  - Police at index 5 can catch thief at index 4.

### Solution

Here is a Java solution for the problem:

```java
class Solution {
    public int catchThieves(char[] arr, int k) {
        // code here
        int n = arr.length;
        
        int i = 0, j = 0;
        int cnt = 0;
        
        while(i < n && j < n) {
            while(i < n && arr[i] != 'P') i++;
            
            while(j < n && arr[j] != 'T') j++;
            
            if(i < n && j < n && Math.abs(i-j) <= k) {
                cnt++;
                i++;
                j++;
            } else if (i < n && i < j) {
                i++;
            } else if (j < n && j < i) {
                j++;
            }
        }
        
        return cnt;
    }
}

