
import Graph.AdjListGraph;

public class Main {
    public static void main(String[] args){
        AdjListGraph graph=AdjListGraph.getTestInstance();
        graph.traverse(false);
        graph.traverse(true);
        System.out.println();
        System.out.println(graph);
    }
}