
import Graph.*;

public class Main {
    public static void main(String[] args){
        int[][] testData={{0,1,3},{0,3,4},{0,2,6},{1,0,3},
                {1,3,9},{2,0,6},{2,4,5},{3,0,4},{3,1,9},{4,2,5}};
        String[] vertexData={"V1","V2","V3","V4","V5","V6"};

        AdjMatrixGraph graph=new AdjMatrixGraph(testData,vertexData);
        System.out.print(graph);
        graph.traverse(false);
        System.out.println();
        graph.traverse(true);
        graph.prim();
    }
}