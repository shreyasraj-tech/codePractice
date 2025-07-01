
✅ **Problem Name:** Max Sum without Adjacent
✅ **Link:** [GeeksforGeeks problem link](https://www.geeksforgeeks.org/problems/geeks-training/1)

📝  *Geeks Training Problem — Notes & Approach*
---

## 📌 **Problem Understanding**

* Geek attends a training program for **n days**.

* Each day, he can choose **one of three activities**:

  * Running
  * Fighting
  * Learning Practice

* Each activity gives him **merit points**, given in a 2D array `arr[i][j]`:

  * `arr[i][0]` = points for Running on day *i*
  * `arr[i][1]` = points for Fighting on day *i*
  * `arr[i][2]` = points for Learning on day *i*

* **Constraint**:
  Geek cannot do the same activity on two consecutive days.

* **Objective**:
  Maximize the total merit points collected over `n` days.

---

## ✅ **Key Observations**

* There are **three choices each day**, but you cannot pick the same activity as the previous day.

* This is a classic **DP on choices** problem, with overlapping subproblems:

  * “What is the best I can do on day `i`, if I did activity `last` on day `i+1`?”

* State definition:

  * `f(day, last)` → maximum points achievable from day `0` to `day`, if on day `day + 1` you did activity `last`
  * We use `last = 3` to represent “no activity” on the first call, since no previous activity was done on day -1.

* **Recurrence**:

  * For each activity `t` different from `last`,

    * calculate `arr[day][t] + f(day-1, t)`
    * take maximum among those

* **Base case**:

  * On day `0`, pick the best of any activity except `last`.

---

## 🟢 **Approach to Solve (Top-Down with Memoization)**

1. **State**:
   `dp[day][last]` = maximum points achievable on days `0..day` if previous activity was `last`.

2. **Transition**:
   for each possible current activity `t` different from `last`,

   ```
   points = arr[day][t] + dp[day-1][t]
   ```

   take the maximum of these.

3. **Base Case**:
   on day `0`, you can do anything except the previous activity.

4. **Initialization**:
   use `dp` table with `-1` to mark uncomputed states.

5. **Memoization**:
   store results in `dp[day][last]` to avoid recalculating.

6. Finally, call the recursive function with:

   ```
   maxi(n - 1, 3, arr, dp)
   ```

   where `3` represents “no activity” the day before the first day.

---


Final Code 
```java
// User function Template for Java

class Solution {
    static int maxi(int day,int last, int arr[][], int[][] dp){
        if (dp[day][last] != -1) return dp[day][last];
        if(day==0){
            int maX=0;
            for(int t=0;t<3; t++){
                if(t!= last){
                    maX= Math.max(maX, arr[0][t]);
                }
            }
            return maX;
        }
        int maX=0;
        for(int t=0;t<3; t++){
            if(t!= last){
                int point= arr[day][t] +maxi(day-1,t,arr,dp);
                maX = Math.max(maX,point);
            }
        }
        return dp[day][last]=maX;
    }
    public int maximumPoints(int arr[][]) {
        // code here
        int n = arr.length;
        int[][] dp=new int[arr.length][4];
        for(int[] row:dp)Arrays.fill(row,-1);
        return maxi(n-1,3, arr,dp );
    }
}
```


## ⚙️ **Complexity**

* **Time**: O(N \* 4 \* 3)
  because:

  * 4 choices for `last` (0,1,2,3)
  * 3 activities to consider
  * N days
* **Space**: O(N \* 4) for the DP table

---

# 🟢 **Tabulation Explanation (Iterative DP)**

* We build up the solution **bottom-up**, tracking the best possible points on each day, **for every possible previous-task state**.
* There are 4 possibilities for `last`:

  * `0, 1, 2` = previous day you did task 0, 1, or 2
  * `3` = *no task restriction* (starting condition)
* So we maintain `dp[last]` for each `last`, to remember the best achievable score when the previous task was `last`.

---

## 🟢 **Key Initialization**

```java
prev[0] = max(arr[0][1], arr[0][2])  // if previous was task 0, you cannot do task 0 again on day 0
prev[1] = max(arr[0][0], arr[0][2])
prev[2] = max(arr[0][0], arr[0][1])
prev[3] = max(arr[0][0], arr[0][1], arr[0][2])  // no task restriction on first day
```

This sets up the best values for **day 0** depending on which previous task you had chosen.

---

## 🟢 **DP Transition**

For every day from 1 to n-1:

```java
for (last = 0..3)
    for (task = 0..2)
        if (task != last)
            temp[last] = max(temp[last], arr[day][task] + prev[task])
```

👉 Means:

* for each possible “previous” task (`last`)
* try each possible current `task` that is *different* from `last`
* add its points to the best from the previous day
* keep track of the maximum

After finishing the day, set:

```java
prev = temp;
```

→ This rolls forward the dp table day by day, with only O(4) space.

---

# 🟢 **Final Answer**

`prev[3]` holds the answer:

* means: *best points achievable* with no restriction on previous-day’s task on the last day


```java

class Solution {
    public int maximumPoints(int arr[][]) {
       int n = arr.length;
       int[] prev = new int[4];
       Arrays.fill(prev,-1);
       
       prev[0] = Math.max(arr[0][1],arr[0][2]);
       prev[1] = Math.max(arr[0][0],arr[0][2]);
       prev[2] = Math.max(arr[0][0],arr[0][1]);
       prev[3] = Math.max(arr[0][0],Math.max(arr[0][1],arr[0][2]));
       
       for(int day = 1;day<n;day++){
           int[] temp= new int[4];
           for(int last = 0;last<4;last++){
               temp[last] =0;
               
               for(int t = 0;t<3;t++){
               if(t!=last){
                //   int point= ;
                    temp[last] = Math.max(temp[last] , arr[day][t] + prev[t]);
                    }
               }
           }
           prev=temp;
       }
       return prev[3];
        
    }
}

```
---

# 🟢 **Summary of features**

✅ **Time Complexity**: O(n \* 4 \* 3) = O(12n) \~ O(n)
✅ **Space Complexity**: O(4)
✅ Uses a rolling 1D array to reduce memory
✅ Follows the classic *tabulation* bottom-up pattern:

* initialize base
* build the answer iteratively
* return at the end

---




## ⭐ **Tips to Remember for Similar DP Problems**

* Always clearly define:

  * State (parameters)
  * Choice (what you are deciding each day)
  * Base case
  * Transition
* When constraints forbid consecutive repeats, carry **information about previous choice**.
* Use `last` as an indicator to track previous day’s activity.

---

**If you want, I can also write up a tabulation (bottom-up) version for you — just let me know!**
