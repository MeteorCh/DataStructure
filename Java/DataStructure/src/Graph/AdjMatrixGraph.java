package Graph;

import java.util.LinkedList;

/**
 * 图的邻接矩阵实现
 * 使用示例：
 AdjMatrixGraph graph=AdjMatrixGraph.getTestInstance();
 System.out.print(graph);
 graph.traverse(false);
 System.out.println();
 graph.traverse(true);
 */
public class AdjMatrixGraph extends Graph{
    public static int INFINITY=Integer.MAX_VALUE/2;
    protected int[][] matrix;//邻接矩阵
    protected String[] vertexNames;//顶点名

    AdjMatrixGraph(int vertexNum){
        super(vertexNum);
        vertexNames=new String[vertexNum];
        matrix=new int[vertexNum][vertexNum];
        //将邻接矩阵中的值初始化为INFINITY
        for (int i=0;i<vertexNum;i++)
            for (int j=0;j<vertexNum;j++)
                matrix[i][j]=INFINITY;
    }

    /**
     * 构造测试数据图
     * @return
     */
    public static AdjMatrixGraph getTestInstance(){
        AdjMatrixGraph graph=new AdjMatrixGraph(6);
        //顶点名称赋值
        String[] vertexes={"V1","V2","V3","V4","V5","V6"};
        for (int i=0;i<graph.vertexNum;i++)
            graph.vertexNames[i]=vertexes[i];
        //边赋值
        int[][] testData={{0,1,5},{0,3,7},{1,2,4},{2,0,8},
                {2,5,9},{3,2,5},{3,5,6},{4,3,5},{5,0,3},{5,4,1}};
        for (int i=0;i<testData.length;i++){
            graph.matrix[testData[i][0]][testData[i][1]]=testData[i][2];
        }
        return graph;
    }

    @Override
    protected void dfsTraverse(int curNode,boolean[] flags){
        System.out.print(vertexNames[curNode]+",");
        flags[curNode]=true;
        //寻找curNode相邻的顶点访问
        for (int i=0;i<vertexNum;i++){
            if (matrix[curNode][i]<INFINITY&&!flags[i])
                dfsTraverse(i,flags);
        }
    }

    @Override
    protected void bfsTraverse(int curNode,boolean[] flags){
        LinkedList<Integer> queue=new LinkedList<>();
        LinkedList<Integer> queue2=new LinkedList<>();
        queue.add(curNode);
        flags[curNode]=true;
        while (!queue.isEmpty()){
            while (!queue.isEmpty()){
                int nodeIndex=queue.pop();
                System.out.print(vertexNames[nodeIndex]+",");
                for (int i=0;i<vertexNum;i++){
                    if (matrix[nodeIndex][i]<INFINITY&&!flags[i])
                    {
                        queue2.add(i);
                        flags[i]=true;
                    }
                }
            }
            LinkedList tmp=queue;
            queue=queue2;
            queue2=tmp;
        }
    }

    @Override
    public String toString() {
        String result="邻接矩阵为：\n          ";
        for (int i=0;i<vertexNum;i++)
            result+=String.format("%8s",vertexNames[i])+"  ";
        result+="\n";
        for (int i=0;i<vertexNum;++i)
        {
            result+=String.format("%8s",vertexNames[i])+"  ";
            for (int j=0;j<vertexNum;++j) {
                if (matrix[i][j]<INFINITY)
                    result+=String.format("% 8d",matrix[i][j])+"  ";
                else
                    result+="INFINITY  ";
            }
            result+="\n";
        }
        return result;
    }
}
