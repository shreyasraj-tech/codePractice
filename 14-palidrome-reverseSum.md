## Palindrome Generator

### Problem Statement

You are given a positive integer as input. Your task is to determine if the input is a **palindrome**. If it is not, follow a specific reverse-and-add process until a palindrome is obtained.

### Instructions

1. **Input**: A single positive integer.
2. **Check**: If the number is a palindrome:
   - Print `Already a palindrome`
3. **If not a palindrome**:
   - Reverse the digits of the number.
   - Add the reversed number to the original number.
   - Check if the result is a palindrome.
   - If not, repeat the reverse-and-add process until a palindrome is formed.
   - Print each intermediate step in the format: `original + reversed = sum`
   - Print the final palindrome number.

### Sample Inputs and Outputs

**Sample Input 1**

```
1221
```

**Sample Output 1**

```
Already a palindrome
```

**Sample Input 2**

```
12345
```

**Sample Output 2**

```
12345 + 54321 = 66666
66666
```

**Sample Input 3**

```
4691937
```

**Sample Output 3**

```
4691937 + 7391964 = 12083901
12083901 + 10938021 = 23021922
...
... (until a palindrome is obtained)
```

### Java Implementation

```java
import java.util.*;

public class Main {

    // Function to check if the string is a palindrome
    static boolean pdrom(String s){
        int n = s.length();
        for (int i = 0; i < n / 2; i++){
            if(s.charAt(i) != s.charAt(n - 1 - i)) return false;
        }
        return true;
    }

    // Function to reverse a string and return the sum of original and reversed number
    static int reverse(String s){
        int n = s.length();
        String rev = "";
        for (int i = n - 1; i >= 0 ; i-- ) {
            rev += s.charAt(i);
        }
        int orgSum = Integer.parseInt(s);
        int revNum = Integer.parseInt(rev);
        System.out.println(orgSum + " + " + revNum + " = " + (orgSum + revNum));
        return orgSum + revNum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String s = Integer.toString(N);

        if(pdrom(s)){
            System.out.println("Already palindrome");
        } else {
            int result = reverse(s);
            s = Integer.toString(result);
            while (!pdrom(s)) {
                result = reverse(s);
                s = Integer.toString(result);
            }
            System.out.println(s);
        }
    }
}
```

### Explanation

- **Palindrome Check (**``**)**: Compares characters from both ends towards the center.
- **Reverse & Add (**``**)**: Reverses the string, converts both to integers, and adds them.
- **Main Loop**: If input is not a palindrome, repeatedly reverse and add until a palindrome is found.

This process is useful in exploring the concept of **Lychrel numbers** and understanding string/number manipulations.


