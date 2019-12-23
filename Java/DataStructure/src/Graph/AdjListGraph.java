package Graph;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedList;

/**
 * 邻接表存储的图
 * @param <T> 数据泛型
 * @param <E> 权重泛型
 * 使用示例：
AdjListGraph graph=AdjListGraph.getTestInstance();
graph.traverse(false);
graph.traverse(true);
System.out.println();
System.out.println(graph);
 */

public class AdjListGraph<T,E extends Number> extends Graph<E>{
    public static class VertexNode<T,E extends Number>{//顶点类
        protected T data;//数据
        protected ArcNode<E> firstArc;//第一条边
        public VertexNode(T data){
            this.data=data;
            this.firstArc=null;
        }
        /**
         * 插入弧
         * @param arc
         */
        public void insertArc(ArcNode arc){
            if (this.firstArc==null)
                this.firstArc=arc;
            else {
                //使用头插法插入边
                arc.next=this.firstArc;
                this.firstArc=arc;
            }
        }
    }

    public static class ArcNode<T extends Number> {//边类
        protected int nodeIndex;//该边终点在数组中的下标
        protected T weight;//权重
        protected ArcNode next;//指向下一条边的指针

        public ArcNode(int nodeIndex,T weight){
            this.nodeIndex=nodeIndex;
            this.weight=weight;
            next=null;
        }
    }

    protected VertexNode<T,E>[] vertexes;//顶点数组

    public AdjListGraph(int vertexNum){//分配空间
        super(vertexNum);
        vertexes=new VertexNode[vertexNum];
    }

    @Override
    protected void addEdge(int headIndex, int tailIndex, E weight) {
        super.addEdge(headIndex, tailIndex, weight);
        if (headIndex>=0&&headIndex<vertexNum&&tailIndex>=0&&tailIndex<vertexNum&&headIndex!=tailIndex)
            vertexes[headIndex].insertArc(new ArcNode(tailIndex,weight));
        else
            throw new IndexOutOfBoundsException("输入参数错误！");
    }

    public static AdjListGraph getTestInstance() {//构造测试数据实例
        AdjListGraph<String,Integer> graph= new AdjListGraph<String, Integer>(5);
        for (int i=0;i<5;i++){
            VertexNode<String,Integer> node=new VertexNode("V"+i);
            graph.vertexes[i]=node;
        }
        try{
            int[][] testData={{0,1,1},{0,2,2},{1,2,3},{1,3,4},
                    {2,3,5},{2,4,6},{3,4,7}};
            for (int i=0;i<testData.length;i++){
                graph.addEdge(testData[i][0],testData[i][1],testData[i][2]);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("输入参数有误");
        }
        return graph;
    }

    @Override
    protected void dfsTraverse(int curNode,boolean[] flags){
        System.out.print(vertexes[curNode].data+",");
        flags[curNode]=true;
        //寻找curNode相连的节点深度优先搜索
        ArcNode  node=vertexes[curNode].firstArc;
        while(node!=null){
            if (!flags[node.nodeIndex])
                dfsTraverse(node.nodeIndex,flags);
            node=node.next;
        }
    }

    @Override
    protected void bfsTraverse(int curNode,boolean[] flags) {
        LinkedList<Integer> queue=new LinkedList<>();
        LinkedList<Integer> queue2=new LinkedList<>();
        queue.add(curNode);
        flags[curNode]=true;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                int nodeIndex=queue.pop();
                System.out.print(vertexes[nodeIndex].data+",");
                //将与curNode相连的未访问过的节点压栈
                ArcNode curArc=vertexes[nodeIndex].firstArc;
                while (curArc!=null){
                    if (!flags[curArc.nodeIndex])
                    {
                        queue2.add(curArc.nodeIndex);
                        flags[curArc.nodeIndex]=true;
                    }
                    curArc=curArc.next;
                }
            }
            LinkedList tmp=queue;
            queue=queue2;
            queue2=tmp;
        }
    }



    @Override
    public String toString() {
        String result="图的邻接表表示为：\n";
        for (VertexNode node:vertexes){
            result+=node.data;
            result+=":";
            ArcNode curNode=node.firstArc;
            while (curNode!=null){
                result+=node.data+vertexes[curNode.nodeIndex].data.toString()
                +"(weight:"+curNode.weight+"),";
                curNode=curNode.next;
            }
            result+="\n";
        }
        return result;
    }

    /**
     * 拓扑排序
     */
    public void topoSort(){
        try {
            LinkedList<Integer> result=topoSort(null);
            if (result.size()==vertexNum)
            {
                System.out.print("该图的拓扑排序结果为：");
                for (int i:result)
                    System.out.print(vertexes[i].data+",");
                System.out.println();
            }
            else
                System.out.println("图中存在环，不存在拓扑排序");
        }catch (Exception e){
            System.out.println("目前拓扑排序只支持整形权重");
        }

    }

    /**
     * 拓扑排序
     * @param earlistStartTime 事件的最早开始时间
     * @return 拓扑排序结果
     */
    protected LinkedList<Integer> topoSort(int[] earlistStartTime) throws Exception {
        int[] in=new int[vertexNum];//统计每个节点的入度的数组
        boolean[] visitFlag=new boolean[vertexNum];//标记节点是否已输出
        //初始化
        if (earlistStartTime!=null){
            for (int i=0;i<vertexNum;i++)
                earlistStartTime[i]=0;
        }
        for (int i=0;i<vertexNum;i++){
            visitFlag[i]=false;
            VertexNode node=vertexes[i];
            ArcNode arcNode=node.firstArc;
            while (arcNode!=null){
                in[arcNode.nodeIndex]++;
                arcNode=arcNode.next;
            }
        }
        LinkedList<Integer> stack=new LinkedList<>();
        LinkedList<Integer> result=new LinkedList<>();
        //将入度为0的下标入栈
        for (int i=0;i<vertexNum;i++)
            if (in[i]==0)
            {
                stack.add(in[i]);
                visitFlag[i]=true;
            }
        //开始拓扑排序
        while (!stack.isEmpty()){
            //弹栈
            int curIndex=stack.pop();
            result.push(curIndex);
            visitFlag[curIndex]=true;
            //将curIndex相连的节点的入度减1
            ArcNode curArc=vertexes[curIndex].firstArc;
            while (curArc!=null){
                if (!visitFlag[curArc.nodeIndex])
                {
                    in[curArc.nodeIndex]--;
                    //需要计算事件的最早开始时间
                    if (earlistStartTime!=null){
                        if (curArc.weight instanceof Integer){
                            Integer weight=(Integer)curArc.weight;
                            if (earlistStartTime[curIndex]+weight>earlistStartTime[curArc.nodeIndex])
                                earlistStartTime[curArc.nodeIndex]=earlistStartTime[curIndex]+weight;
                        }else
                            throw new Exception("暂时只支撑整形权重");

                    }
                    //如果入度变为1,则入栈
                    if (in[curArc.nodeIndex]==0){
                        stack.add(curArc.nodeIndex);
                        visitFlag[curArc.nodeIndex]=true;
                    }
                }
                curArc=curArc.next;
            }
        }
        return result;
    }

    public void keyPath(){
        try{
            int[] etv=new int[vertexNum];//事件的最早发生时间
            LinkedList<Integer> topoResult=topoSort(etv);//拓扑排序结果
            if (topoResult.size()==vertexNum){
                int[] ltv=new int[vertexNum];//事件的最晚发生时间
                //初始化ltv
                for (int i=0;i<vertexNum;i++)
                    ltv[i]=etv[vertexNum-1];
                //求解ltv
                while (!topoResult.isEmpty()){
                    int curIndex=topoResult.pop();
                    ArcNode curArc=vertexes[curIndex].firstArc;
                    while (curArc!=null){
                        Integer weight=(Integer)curArc.weight;
                        if (ltv[curArc.nodeIndex]-weight<ltv[curIndex])
                            ltv[curIndex]=ltv[curArc.nodeIndex]-weight;
                        curArc=curArc.next;
                    }
                }
                //求解每个活动的最早开始时间和最晚开始时间
                System.out.print("AOE网中的关键活动为：");
                for (int i=0;i<vertexNum;i++){
                    ArcNode curArc=vertexes[i].firstArc;
                    while (curArc!=null){
                        int ete=etv[i];//活动的最早开始时间
                        int lte=ltv[curArc.nodeIndex]-(Integer) curArc.weight;//活动的最晚开始时间
                        if (ete==lte)
                            System.out.print(vertexes[i].data.toString()+vertexes[curArc.nodeIndex].data+",");
                        curArc=curArc.next;
                    }
                }
                System.out.println();

            }else {
                System.out.println("图中存在环！");
            }

        }catch (Exception e){
            System.out.println("目前关键路径只支持整形权重");
        }

    }
}
