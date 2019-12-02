
import Graph.AdjMatrixGraphy;

public class Main {
    public static void main(String[] args){
        AdjMatrixGraphy graphy=new AdjMatrixGraphy();
        System.out.print(graphy);
        graphy.traverse(false);
        System.out.println();
        graphy.traverse(true);
    }
}