
import Searching.BinarySortingTree.BinarySortingTree;

public class Main {
    public static void main(String[] args){
        int[] data={62,88,58,47,35,73,51,99,37,93};
        BinarySortingTree tree=new BinarySortingTree(data);
        tree.find(35);
        tree.deleteData(62);
    }
}