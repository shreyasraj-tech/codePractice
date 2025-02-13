Here is the link to the problem on LeetCode 49:
[49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)


## Given an array of strings strs, group the  anagrams together. You can return the answer in any order.

 


#### Example 1:

- Input: s = ["eat","tea","tan","ate","nat","bat"] 
- ##### Output: [["bat"],["nat","tan"],["ate","eat","tea"]]


````Java

/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs1)); 

        // String[] strs2 = {""};
        // System.out.println(groupAnagrams(strs2));

        String[] strs3 = {"ama", "aam"," "};
        System.out.println(groupAnagrams(strs3));
    }
	
	public static List<List<String>> groupAnagrams(String[] strs) {
	Map<String,List<String>> list = new HashMap<>();
	
	for (String ca :strs ){
	    char[] charArray= ca.toCharArray();
	    Arrays.sort(charArray);
	    
	    String keys = String.valueOf(charArray);
	    if (!list.containsKey(keys)) list.put(keys,new ArrayList<>());
	    list.get(keys).add(ca);
	} 
      return new ArrayList<>(list.values());
    }
}
