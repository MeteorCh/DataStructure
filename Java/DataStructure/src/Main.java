
import Graph.*;

public class Main {
    public static void main(String[] args){
        AdjListGraph graph=AdjListGraph.getTestInstance();
        System.out.println(graph.toString());
        graph.keyPath();
    }
}