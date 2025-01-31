# Problem: Determine the Order of Rice and Miso Soup Plates

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://atcoder.jp/contests/abc360/tasks/abc360_a)

## Problem Statement

We are given a string `S` of length 3, where each character represents a type of plate in a row. The string consists of:
- `R` for rice
- `M` for miso soup
- `S` for salad

You need to determine whether the plate of rice (`R`) is to the left of the plate of miso soup (`M`).

### Input
- A string `S` of length 3, consisting of the characters `R`, `M`, and `S` in any order.

### Output
- Print `"Yes"` if the plate of rice is to the left of the plate of miso soup, otherwise print `"No"`.

***

### Example 1

**Input:**
RSM

**Output:**
Yes

---

### Java Code Solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Read input string
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        
        // Find the indices of 'R' (rice) and 'M' (miso soup)
        int riceIndex = N.indexOf('R');
        int misoIndex = N.indexOf('M');
        
        // Check if rice is to the left of miso soup
        if (riceIndex < misoIndex) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}



```
***

### Explanation of the Structure:
- **Problem Statement**: Describes the task of determining if rice is to the left of miso soup in a 3-character string.
- **Input/Output**: The format for both input and expected output, along with example cases.
- **Java Code**: The Java implementation that solves the problem.
- **Explanation**: Provides a detailed walkthrough of how the solution works and how edge cases are handled.
- **Time Complexity**: A brief note on the time complexity of the solution.

### How to Use:
- This markdown file can be saved with a `.md` extension and used as documentation for the problem. It will render properly in markdown viewers (such as GitHub, Visual Studio Code, or other markdown editors).


