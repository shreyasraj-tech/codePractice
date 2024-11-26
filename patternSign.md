# Task Many A+B Problems

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://atcoder.jp/contests/abc288/tasks/abc288_a)

## Problem Statement

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();

        int count =0;
        for(int i =0;i<c.length();i++){
        // char c =N.charAt(i);

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


```

### Second code

```java


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

/*split("\\|") method divides the string into parts using the | character as the delimiter.
Why \\|?:
The | character has a special meaning in regular expressions (it denotes "OR").
To use it as a literal, you need to escape it with \\.

*/


        String[] parts = s.split("\\|");
        List<Integer> result = new ArrayList<>();

        for (String part : parts) {
            if (!part.isEmpty()) {
                result.add(part.length()); // '-' の数を追加
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(result.get(i));
        }
        System.out.println();

        scanner.close();
    }
}
```
