
import Searching.BinarySortingTree.AVLTree;

public class Main {
    public static void main(String[] args){
        int[] data={20,10,0,30,40,50,60,90,80,70};
        AVLTree tree=new AVLTree(data);
        /**
         * 右子树中LR型删除
         *         int[] data={20,10,0,30,40,50,60,90,80,70};
         *         tree.deleteNode(40);
         *         tree.deleteNode(50);
         *         tree.deleteNode(90);
         */
        /**
         * 右子树中RL型删除
         *         int[] data={20,10,0,30,40,50,60,80};
         *         tree.deleteNode(40);
         *         tree.insertData(55);
         *         tree.deleteNode(80);
         */
        /**左子树中LR型删除
         *         int[] data={20,10,0,30,40,50,60,90,80,70};
         *         tree.insertData(5);
         *         tree.deleteNode(20);
         */
        /**左子树中RL型删除
         *         int[] data={20,10,0,30,40,50,60,90,80,70};
         *         tree.insertData(15);
         *         tree.deleteNode(0);
         */
        tree.insertData(15);
        tree.deleteNode(0);
    }
}