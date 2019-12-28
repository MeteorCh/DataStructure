package Searching.BinarySortingTree;

/**
 * 二叉树节点的基类
 */
public class TreeNode {
    protected int data;//数据
    protected TreeNode lChild,rChild,parent;//左孩子、右孩子、父节点

    public TreeNode(int data){
        this.data=data;
        lChild=rChild=parent=null;
    }
    public int getData() {
        return data;
    }
}
