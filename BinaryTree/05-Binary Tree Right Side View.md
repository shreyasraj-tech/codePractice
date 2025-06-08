## Leetcode-199: Binary Tree Right Side View

Here is the link to the problem on [Leetcode](https://leetcode.com/problems/binary-tree-right-side-view/)

### Problem Description

Given the `root` of a binary tree, imagine yourself standing on the **right side** of it. Return the values of the nodes you can see ordered from top to bottom.

In simpler terms, for each level of the tree, you need to return the **rightmost** node that is visible when the tree is viewed from the right side.

### Constraints

- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

### Example 1:

- **Input**: `root = [1,2,3,null,5,null,4]`
- **Output**: `[1,3,4]`
- **Explanation**:
  - Level 0: Rightmost = 1
  - Level 1: Rightmost = 3
  - Level 2: Rightmost = 4

### Example 2:

- **Input**: `root = [1,null,3]`
- **Output**: `[1,3]`

### Example 3:

- **Input**: `root = []`
- **Output**: `[]`

---

### Solution

Here is a Java solution for the problem:

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int levels(TreeNode root){
        if(root == null) return 0;
        return 1 + Math.max(levels(root.left), levels(root.right));
    }

    public void preorder(TreeNode root, int level, List<Integer> ans){
        if(root == null) return;
        preorder(root.left, level + 1, ans);
        preorder(root.right, level + 1, ans);
        ans.set(level, root.val);
    }

    public List<Integer> rightSideView(TreeNode root) {
        int n = levels(root);
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ans.add(0);
        }

        preorder(root, 0, ans);
        return ans;
    }
}
