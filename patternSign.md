# Task Many A+B Problems

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://atcoder.jp/contests/abc288/tasks/abc288_a)


## Problem Statement


```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        
        int count =0;
        for(int i =0;i<N.length();i++){
        char c =N.charAt(i);
         
         if(c == '-') count++;
          else if( c== '|') {
              if(i>0) System.out.print(count+" ");
            count=0;
          }
        }
        
        if (count > 0) {
            System.out.print(count);
        }
            
        
    }
}


````