package 二叉树;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class _700_二叉搜索树中的搜索 {

    /**
     * 迭代
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val > root.val ? root.right : root.left;
        }
        return root;
    }

    /**
     * 递归
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return val > root.val ? searchBST2(root.right, val) : searchBST2(root.left, val);
    }
}
