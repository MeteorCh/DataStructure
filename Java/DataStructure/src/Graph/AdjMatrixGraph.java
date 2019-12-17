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
public class AdjMatrixGraph<T> extends Graph<Integer>{
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

    public AdjMatrixGraph(int[][] edges, String[] vertexNames){
        this(vertexNames.length);
        this.vertexNames=vertexNames;
        for (int i=0;i<edges.length;i++)
            addEdge(edges[i][0],edges[i][1],edges[i][2]);
    }

    @Override
    protected void addEdge(int headIndex, int tailIndex, Integer weight) {
        super.addEdge(headIndex, tailIndex, weight);
        this.matrix[headIndex][tailIndex]=(Integer) weight;
    }

    /**
     * 利用prim算法求图的最小生成树
     */
    public void prim(){
        System.out.println();
        //设置标记数组
        int[] lowCost=new int[vertexNum];
        int[] mst=new int[vertexNum];
        //从第一个顶点开始，设置lowCost
        lowCost[0]=0;
        mst[0]=0;
        for (int i=1;i<vertexNum;i++)
        {
            lowCost[i]=matrix[0][i];
            mst[i]=0;
        }
        //从第一个点开始，每次寻找权重最小的边
        int min;
        for (int i=1;i<vertexNum;i++){
            //每次找到minCost中的最小值
            int k=0;//记录每次最小权下标顶点
            min=INFINITY;
            for (int j=0;j<vertexNum;j++){
                if (lowCost[j]<min&&lowCost[j]!=0){
                    min=lowCost[j];
                    k=j;
                }
            }
            if (k==0)
                break;
            System.out.println("顶点"+vertexNames[mst[k]]+"和顶点"+vertexNames[k]+"相连");
            lowCost[k]=0;//表示k节点已加入最小生成树
            for (int j=1;j<vertexNum;j++){
                //判断通过k节点中转，其他点的距离是否会变小
                if (lowCost[j]!=0&&matrix[k][j]<lowCost[j]){
                    lowCost[j]=matrix[k][j];
                    mst[j]=k;
                }
            }
        }
    }

    /**
     * 寻找start到end的最短路径
     * @param start 起点下标
     * @param end 终点下标
     * @param method 方法标志，true为Dijkstra算法，false为Floyd算法
     */
    public void findMiniPath(int start,int end,boolean method){
        if (method)
            dijkstraMethod(start,end);
        else
            floydMethod(start,end);
    }

    /**
     * 利用dijkstra算法计算start到end的最短路径
     * @param start 起点下标
     * @param end 终点下标
     */
    protected void dijkstraMethod(int start,int end){
        int[] miniCost=new int[vertexNum];//记录最短权值
        int[] pathIndex=new int[vertexNum];//记录最短路径经过的节点
        for (int i=0;i<vertexNum;++i) {
            miniCost[i]=matrix[start][i];
            pathIndex[i]=-1;
        }
        miniCost[start]=0;
        pathIndex[start]=0;
        int miniStart=start;
        for (int i=0;i<vertexNum;i++){
            //找到miniCost中的最小值
            int mini=INFINITY;
            int k=-1;
            for (int j=0;j<vertexNum;j++){
                if (mini>miniCost[j]&&pathIndex[j]==-1&&matrix[miniStart][j]<INFINITY)
                {
                    mini=miniCost[j];
                    k=j;
                }
            }
            pathIndex[k]=miniStart;
            if (k==-1||k==end)
            {
                break;
            }
            else{//k为最小点
                for (int j=0;j<vertexNum;++j){
                    if (miniCost[k]+matrix[k][j]<miniCost[j]&&pathIndex[j]==-1)
                    {
                        miniCost[j]=miniCost[k]+matrix[k][j];
                        miniStart=k;
                    }
                }
            }
        }
        //输出最短路径
        System.out.println();
        int pre=end;
        String pathResult=vertexNames[start]+"到"+vertexNames[end]+"的最短路径为:"+vertexNames[end];
        while (pre!=start){
            pathResult+="<-"+vertexNames[pathIndex[pre]];
            pre=pathIndex[pre];
        }
        System.out.println(pathResult+"（路径权重和为"+miniCost[end]+"）");
        for (int i=0;i<vertexNum;i++){
            if (pathIndex[i]!=-1)
                System.out.println("顶点"+vertexNames[start]+"到顶点"+vertexNames[i]+"的最短距离为："+miniCost[i]);
        }
    }

    /**
     * 利用floyd算法计算start到end的最短路径
     * @param start 起点下标
     * @param end 终点下标
     */
    protected void floydMethod(int start,int end){
        int[][] weightMatrix=new int[vertexNum][vertexNum];
        int[][] path=new int[vertexNum][vertexNum];
        for (int i=0;i<vertexNum;i++){
            for (int j=0;j<vertexNum;j++)
            {
                weightMatrix[i][j]=matrix[i][j];
                path[i][j]=j;
            }
        }
        for (int k=0;k<vertexNum;k++){
            for (int i=0;i<vertexNum;i++)
                for (int j=0;j<vertexNum;j++){
                    int newWeight=weightMatrix[i][k]+weightMatrix[k][j];
                    if (weightMatrix[i][j]>newWeight)
                    {
                        weightMatrix[i][j]=newWeight;
                        path[i][j]=path[i][k];
                    }
                }
        }
        System.out.println();
        String result="顶点"+vertexNames[start]+"到顶点"+vertexNames[end]+"的最短路径为："+vertexNames[start];
        int k=path[start][end];
        while (k!=end){
            result+="->"+vertexNames[k];
            k=path[k][end];
        }
        result+="->"+vertexNames[end];
        System.out.println(result);
    }

    /**
     * 构造测试数据图
     * @return
     */
    public static AdjMatrixGraph getTestInstance(){

        //顶点名称赋值
        String[] vertexes={"V1","V2","V3","V4","V5","V6"};
        //边赋值
        int[][] testData={{0,1,5},{0,3,7},{1,2,4},{2,0,8},
                {2,5,9},{3,2,5},{3,5,6},{4,3,5},{5,0,3},{5,4,1}};
        AdjMatrixGraph graph=new AdjMatrixGraph(testData,vertexes);
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
