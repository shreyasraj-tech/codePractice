## Leetcode-124: Binary Tree Maximum Path Sum

Here is the link to the problem on [Leetcode](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

### Problem Description

A **path** in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has a parent-child relationship. A node can only appear once in the sequence. The path does not need to pass through the root.

The **path sum** of a path is the sum of the node's values in the path.

Given the `root` of a binary tree, return the **maximum path sum** of any non-empty path.

### Constraints

- The number of nodes in the tree is in the range `[1, 3 * 10⁴]`.
- `-1000 <= Node.val <= 1000`

### Example 1:

- **Input**: root = `[-10,9,20,null,null,15,7]`
- **Output**: `42`
- **Explanation**: The optimal path is `[15 → 20 → 7]` with a sum of `42`.

### Example 2:

- **Input**: root = `[1,2,3]`
- **Output**: `6`
- **Explanation**: The optimal path is `[2 → 1 → 3]`.

---

### Solution

Here is a Java solution for the problem:

```java
class Solution {
    public int maxPathSum(TreeNode root) {
        int maxValue[] = new int[1];
        maxValue[0]= Integer.MIN_VALUE;
        maxPath(root, maxValue);
        return maxValue[0];
    }

    private int maxPath(TreeNode root, int maxValue[]){
        if(root == null) return 0;
        int left = Math.max(0, maxPath(root.left, maxValue));
        int right = Math.max(0, maxPath(root.right, maxValue));
        maxValue[0] = Math.max(maxValue[0], left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
