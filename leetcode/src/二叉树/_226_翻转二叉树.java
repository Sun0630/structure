package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 思路： 遍历二叉树，拿到节点之后交换left和right
 */
public class _226_翻转二叉树 {
    /**
     * 先使用前序遍历 递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        //... 交换左右节点元素
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        //... 交换左右节点元素
        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }


    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        //... 交换左右节点元素
        invertTree(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 注意这里交换了一次，应该继续遍历left
        invertTree(root.left);
        return root;
    }

    /**
     * 使用层序遍历翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            final TreeNode node = queue.poll();
            // 取出元素交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left!= null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
