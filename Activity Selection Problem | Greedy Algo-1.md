# Activity Selection Problem | Greedy Algo-1

##
Here is the link to the problem on Activity Selection Problem from gfg:
[Activity Selection Problem | Greedy Algo-1]([https://www.geeksforgeeks.org/problems/activity-selection-1587115620/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card])

```java
import java.util.*;

class Solution {
    public int activitySelection(int[] start, int[] finish) {
        int n = start.length;

        // Step 1: Create a list of indices [0, 1, ..., n-1]
        List<Integer> indexArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexArr.add(i);
        }

        // Step 2: Sort the indices based on finish times
        Collections.sort(indexArr, (a, b) -> finish[a] - finish[b]);

        // Step 3: Greedily select non-overlapping activities
        int count = 1;  // First activity is always selected
        int lastEnd = finish[indexArr.get(0)];

        for (int i = 1; i < n; i++) {
            int index = indexArr.get(i);
            if (start[index] >= lastEnd) {
                count++;
                lastEnd = finish[index];
            }
        }

        return count;
    }
}
