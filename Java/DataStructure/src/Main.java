
import Graph.*;

public class Main {
    public static void main(String[] args){
        int[][] testData={{0,1,1},{0,2,5},{1,0,1},{1,2,3},{1,3,7},{1,4,5}
                ,{2,0,5},{2,1,3},{2,4,1},{2,5,7},{3,1,7},{3,4,2},{3,6,3}
                ,{4,1,5},{4,2,1},{4,3,2},{4,5,3},{4,6,6},{4,7,9},{5,2,7},{5,4,3},{5,7,5}
                ,{6,3,3},{6,4,6},{6,7,2},{6,8,7},{7,4,9},{7,5,5},{7,6,2},{7,8,4}
                ,{8,6,7},{8,7,4},
        };
        String[] vertexData={"V0","V1","V2","V3","V4","V5","V6","V7","V8"};
        AdjMatrixGraph graph=new AdjMatrixGraph(testData,vertexData);
        System.out.print(graph);
        graph.traverse(false);
        System.out.println();
        graph.traverse(true);
        graph.findMiniPath(0,4);
    }
}