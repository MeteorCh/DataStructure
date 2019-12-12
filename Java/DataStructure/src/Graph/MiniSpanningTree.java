package Graph;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * 最小生成树类
 */
public class MiniSpanningTree<E extends Number,T> {

    public static class Edge<E extends Number> {
        protected int start;
        protected int end;
        protected E weight;

        public Edge(int start, int end, E weight){
            this.start=start;
            this.end=end;
            this.weight=weight;
        }

        public int compare(Edge<E> edge){
            if (weight instanceof Integer){
                Integer edgeWeight=(Integer)edge.weight;
                Integer thisWeight=(Integer)weight;
                if (edgeWeight>thisWeight)
                    return 1;
                else if (edgeWeight<thisWeight)
                    return -1;
                else
                    return 0;
            }else if (weight instanceof Float){
                Float edgeWeight=(Float)edge.weight;
                Float thisWeight=(Float)weight;
                if (edgeWeight>thisWeight)
                    return 1;
                else if (edgeWeight<thisWeight)
                    return -1;
                else
                    return 0;
            }else if (weight instanceof Double){
                Double edgeWeight=(Double)edge.weight;
                Double thisWeight=(Double)weight;
                if (edgeWeight>thisWeight)
                    return 1;
                else if (edgeWeight<thisWeight)
                    return -1;
                else
                    return 0;
            }
            return Integer.MIN_VALUE;
        }
    }

    /**
     * kruskal算法
     * @param allEdges
     * @param vertexData
     */
    public void kruskal(LinkedList<Edge<E>> allEdges, T[] vertexData){
        //首先按权重对边排序
        allEdges.sort(new Comparator<Edge>() {//比较函数，正数表示o2大于o1
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.compare(o2)>0)
                    return -1;
                else
                    return 1;
            }
        });
        int[] parents=new int[vertexData.length];
        for (int i=0;i<vertexData.length;i++)
            parents[i]=-1;
        for (Edge edge:allEdges){
            int sParent=findParent(parents,edge.start);
            int eParent=findParent(parents,edge.end);
            if (sParent!=eParent){
                System.out.println("顶点"+vertexData[edge.start]+"和"+vertexData[edge.end]+"相连");
                parents[eParent]=sParent;
            }
        }
    }

    /**
     * 利用并查集寻找节点的根节点
     * @param parents
     * @param index
     * @return
     */
    protected int findParent(int[] parents,int index){
        if (parents[index]==-1)return index;
        while (parents[index]!=-1)
            index=parents[index];
        return index;
    }

    /**
     * 最小生成树的prim算法
     * @param allEdges 所有的边，样例数据int[][] testData={{0,1,5},{0,3,7},{1,2,4},{2,0,8},
     *                 {2,5,9},{3,2,5},{3,5,6},{4,3,5},{5,0,3},{5,4,1}};
     * @param vertexData 所有顶点数据（顶点名称）
     */
    public void prim(int[][] allEdges, String[] vertexData){
        //直接调用邻接矩阵中的prim算法
        AdjMatrixGraph graph=new AdjMatrixGraph(allEdges,vertexData);
        graph.prim();
    }
}
