## Job Sequencing Problem

Here is the link to the problem on  GEEKS FOR GEEKS  [Job Sequencing Problem](https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1)

### Problem Description

Given `N` jobs where every job has a deadline and associated profit if the job is finished before the deadline. It is also given that every job takes a single unit of time, so the minimum possible deadline for any job is `1`. Maximize the total profit if only one job can be scheduled at a time.

### Example 1:

- **Input**: 
  - `N = 4`
  - `JobID[] = {1, 2, 3, 4}`
  - `Deadline[] = {4, 1, 1, 1}`
  - `Profit[] = {20, 10, 40, 30}`
- **Output**: `[2, 70]`
- **Explanation**: 
  - Jobs 3 and 4 can be done with a total profit of 70.

### Example 2:

- **Input**:
  - `N = 5`
  - `JobID[] = {1, 2, 3, 4, 5}`
  - `Deadline[] = {2, 1, 2, 1, 3}`
  - `Profit[] = {100, 19, 27, 25, 15}`
- **Output**: `[2, 127]`
- **Explanation**: 
  - Jobs 1 and 3 done for a total profit of 127.

### Solution

```java
import java.util.*;

class Solution {

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
      int len = profit.length;

        // Step 1: Find maximum deadline
        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }

      
        ArrayList<Integer> indexArr = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indexArr.add(i);
        }

        int[] slot = new int[maxDeadline + 1];
        Arrays.fill(slot, -1);


        Collections.sort(indexArr, (a, b) -> profit[b] - profit[a]);


        
        int jobCount = 0;
        int totalProfit = 0;


        for (int i = 0; i < len; i++) {
            int idx = indexArr.get(i); 
            

            for (int t = deadline[idx]; t > 0; t--) {
                if (slot[t] == -1) {
                    slot[t] = idx; // mark job scheduled
                    jobCount++;
                    totalProfit += profit[idx];
                    break;
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(jobCount);
        result.add(totalProfit);
        return result;

    }
}
