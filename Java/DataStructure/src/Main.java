
import Searching.BinarySortingTree.AVLTree;

public class Main {
    public static void main(String[] args){
        int[] data={20,10,0,30,40,50,60,90,80,70};
        AVLTree tree=new AVLTree(data);
        int findKey=30;
        System.out.println("键"+findKey+"在平衡二叉树中"+(tree.find(findKey)==null?"不存在":"存在"));

    }
}