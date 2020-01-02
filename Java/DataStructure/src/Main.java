
import Searching.BinarySortingTree.AVLTree;

public class Main {
    public static void main(String[] args){
        int[] data={20,10,0,30,40,50,60,80};
        AVLTree tree=new AVLTree(data);
        tree.deleteNode(40);
        tree.insertData(75);
        tree.deleteNode(50);
    }
}