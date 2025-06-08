class Solution {
    public boolean isSymmetric(TreeNode root) {    
        if(root == null) return false;
        return Sym(root.left, root.right);
    }
    private boolean Sym(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null) return false;
        if(r1.val!= r2.val) return false;
        return Sym(r1.left , r2.right) && Sym(r1.right , r2.left);
    }

}


   
