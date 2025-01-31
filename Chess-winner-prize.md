# World Chess Championship

Here is the link to the problem on AtCoder:
[ABC288 Task A - AtCoder](https://www.codechef.com/practice/course/strings/STRINGS/problems/WCC?tab=statement)


## Problem Statement
14 Classical games will be played between Chef and Carlsen in the championship, where each game has one of three outcomes. It can be won by 
- Carlsen 
- Chef 
- can be draw.

- winner - 2 points,
- loser - 0 points. 
- draw, both players get 1 point each. 

The total prize pool of the championship is 100 • X. <br>At end of the 14 Classical games, if one player has strictly more points than the other, &nbsp; he is declared the champion and gets 60 • X as his prize money, and the loser gets 40 • X.

```java
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        
        while(t-->0){
        int count=0;
        int Nount=0;
        int X = sc.nextInt();
        String str=sc.next();
        
        
        for (int i=0; i<14;i++ ){ 
        char ch = str.charAt(i);
        
        if (ch == 'C'){
            count+=2;
        }
        else if (ch== 'N'){
            Nount+=2;
        }
        else{
            count+=1;
            Nount+=1;
        }
        }
        
        if(count>Nount){
            System.out.println(X*60);
        }
        else if (count==Nount){
            System.out.println(X*55);
        }
        else if(count<Nount) {
            System.out.println(X*40);
        }else{
            
        }
        
        
        }
         
    }
    }


