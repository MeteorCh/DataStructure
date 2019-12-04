
import Graph.CrossLinkedListGraph;

public class Main {
    public static void main(String[] args){
        CrossLinkedListGraph graph=CrossLinkedListGraph.getTestInstance();
        System.out.println(graph);
        graph.traverse(false);
        graph.traverse(true);
    }
}