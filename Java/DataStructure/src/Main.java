
import Graph.AdjListGraph;
import Graph.AdjMatrixGraph;

public class Main {
    public static void main(String[] args){
        AdjMatrixGraph graph=AdjMatrixGraph.getTestInstance();
        System.out.print(graph);
        graph.traverse(false);
        System.out.println();
        graph.traverse(true);
    }
}