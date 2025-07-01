---
✅ **Problem Name:** Max Sum without Adjacent
✅ **Link:** [GeeksforGeeks problem link](https://www.geeksforgeeks.org/problems/geeks-training/1)
# 📝 Geeks Training Problem — Notes & Approach


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

## ⚙️ **Complexity**

* **Time**: O(N \* 4 \* 3)
  because:

  * 4 choices for `last` (0,1,2,3)
  * 3 activities to consider
  * N days
* **Space**: O(N \* 4) for the DP table

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
