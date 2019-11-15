

import StackAndQueue.LinkQueue;
import Tree.LinkClueBinaryTree;


public class Main {
    public static void main(String[] args){
        LinkClueBinaryTree<String> binaryTree=new LinkClueBinaryTree<>();
        String[] s=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
        LinkQueue<String> data=new LinkQueue<>(s);
        binaryTree.creStringTree(data);
        System.out.print("前序遍历结果为：");
        binaryTree.print(0);
        System.out.println();
        System.out.print("中序遍历结果为：");
        binaryTree.print(1);
        System.out.println();
        System.out.print("后序遍历结果为：");
        binaryTree.print(2);
        System.out.println();
        System.out.print("层次遍历结果为：");
        binaryTree.print(3);
        System.out.println();
        System.out.print("中序线索遍历结果为：");
        binaryTree.print(4);
        System.out.println();
        System.out.print("树的高度为："+binaryTree.getHeight());
        System.out.println();
        System.out.print("树中结点个数为："+binaryTree.getSize());
        System.out.println();
    }
}