

import StackAndQueue.LinkQueue;
import Tree.LinkBinaryTree;


public class Main {
    public static void main(String[] args){
        LinkBinaryTree<String> binaryTree=new LinkBinaryTree<>();
        String[] s=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
        LinkQueue<String> data=new LinkQueue<>(s);
        binaryTree.creStringTree(data);
        System.out.print("前序遍历结果为：");
        binaryTree.print(0);
        System.out.print("中序遍历结果为：");
        binaryTree.print(1);
        System.out.print("后序遍历结果为：");
        binaryTree.print(2);
        System.out.print("层次遍历结果为：");
        binaryTree.print(3);
        System.out.print("树的高度为："+binaryTree.getHeight());

    }
}