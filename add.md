
# Task Many A+B Problems

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://atcoder.jp/contests/abc288/tasks/abc288_a)


## Problem Statement
You are given N pairs of integers: (A1, Bl), (.42, B2), ... , N, BN). For each i
1, 2, , N, print Ai + Bi.


### Input
The input is given from Standard Input in the following format:

N
Ai+ Bi
1
2 + 3

Output:
5


```java

import java.util.*;

public class Main {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        for(int x=0;x<n;x++){
            long a=sc.nextInt();
            long b=sc.nextInt();
            System.out.println(a+b);
        }
        
    }
}
