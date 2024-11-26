
# Task Many A+B Problems

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://atcoder.jp/contests/abc380/tasks/abc380_a)

##Problem Statement
You are given a 6-digit positive integer N.
Determine whether N satisfies all of the following conditions.
• Among the digits of N, the digit 1 appears exactly once.
• Among the digits of N, the digit 2 appears exactly twice.
• Among the digits of N, the digit 3 appears exactly three times.

Sample Input 1
```copy
123233

Sample Output 1
```Copy
Yes


```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String N = scanner.next();

        int c1 = 0, c2 = 0, c3 = 0;

        for (int i = 0; i < N.length(); i++) {
            char c = N.charAt(i);
            if (c == '1') c1++;
            if (c == '2') c2++;
            if (c == '3') c3++;
        }

        if (c1 == 1 && c2 == 2 && c3 == 3) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}