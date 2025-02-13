Here is the link to the problem on LeetCode 242:
[242. Valid Anagram](https://leetcode.com/problems/valid-anagram/description/)


## Given two strings s and t, return true if t is anagram of s, and false otherwise.


Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

`````Java

public class Main {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t)); // Output: true

        s = "rat";
        t = "car";
        System.out.println(isAnagram(s, t)); // Output: false
    }

    public static boolean isAnagram(String s, String t) {
        // If lengths are different, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Convert strings to char arrays
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // Sort the char arrays
        java.util.Arrays.sort(sArray);
        java.util.Arrays.sort(tArray);

        // Compare sorted arrays
        return java.util.Arrays.equals(sArray, tArray);
    }
}
