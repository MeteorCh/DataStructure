
import Graph.AdjListGraph;
import Graph.AdjMatrixGraph;
import Graph.AdjMultipleListGraph;
import Graph.CrossLinkedListGraph;

public class Main {
    public static void main(String[] args){
        AdjMultipleListGraph graph=AdjMultipleListGraph.getInstance();
        System.out.println(graph);
        graph.traverse(false);
        graph.traverse(true);
    }
}