package Graph;

import java.util.LinkedList;

/**
 * 十字链表存储的图
 * @param <T> 数据泛型
 * @param <E> 权重泛型
 * 使用示例：
CrossLinkedListGraph graph=CrossLinkedListGraph.getTestInstance();
System.out.println(graph);
graph.traverse(false);
graph.traverse(true);
 */
public class CrossLinkedListGraph<T,E extends Number> extends Graph {
    /**
     * 边类
     * @param <T> 权重泛型
     */
    public static class CArcNode<T extends Number>{
        int headVertex,tailVertex;//边的起点和终点在数组中的下标
        CArcNode<T> tailNext,headNext;//与该边共尾和共头的下一条边
        T weight;

        public CArcNode(int headVertex,int tailVertex,T weight){
            this.headVertex=headVertex;
            this.tailVertex=tailVertex;
            this.weight=weight;
            tailNext=headNext=null;
        }
    }

    /**
     * 顶点类
     * @param <T> 顶点数据泛型
     * @param <E> 权重泛型
     */
    public static class CVertexNode<T,E extends Number>{
        T data;
        CArcNode<E> firstIn;
        CArcNode<E> firstOut;

        CVertexNode(T data){
            this.data=data;
            firstIn=firstOut=null;
        }
    }

    protected CVertexNode<T,E>[] vertexes;//顶点数组

    CrossLinkedListGraph(int vertexNum) {
        super(vertexNum);
        vertexes=new CVertexNode[vertexNum];
    }

    /**
     * 增加一条边
     * @param headIndex 边的起点顶点在数组中的下标
     * @param tailIndex 边的终点顶点在数组中的下标
     * @param weight 边的权重
     */
    public void addEdge(int headIndex,int tailIndex,E weight){
        CArcNode<E> arcNode=new CArcNode<E>(headIndex,tailIndex,weight);
        //使用头插法，将其插入到顶点的firstOut后面
        CVertexNode<T,E> headNode=vertexes[headIndex];
        arcNode.headNext=headNode.firstOut;
        headNode.firstOut=arcNode;
        //使用头插法，将其插入到顶点的firstIn后面
        CVertexNode<T,E> tailNode=vertexes[tailIndex];
        arcNode.tailNext=tailNode.firstIn;
        tailNode.firstIn=arcNode;
    }

    /**
     * 创建测试数据实例
     * @return
     */
    public static CrossLinkedListGraph getTestInstance(){
        CrossLinkedListGraph<String,Integer> graph=new CrossLinkedListGraph<String, Integer>(4);
        //生成顶点数据
        for (int i=0;i<4;++i){
            CVertexNode<String, Integer> node=new CVertexNode<String, Integer>("V"+(i+1));
            graph.vertexes[i]=node;
        }
        //生成边数据
        int[][] testData={{0,1,1},{0,2,6},{2,0,7},{2,3,4},
                {3,0,5},{3,1,2},{3,2,3}};
        for (int i=0;i<testData.length;i++){
            graph.addEdge(testData[i][0],testData[i][1],testData[i][2]);
        }
        return graph;
    }

    @Override
    protected void dfsTraverse(int curNode, boolean[] flags) {
        System.out.print(vertexes[curNode].data+",");
        flags[curNode]=true;
        //寻找curNode相连的节点深度优先搜索
        CArcNode  node=vertexes[curNode].firstOut;
        while(node!=null){
            if (!flags[node.tailVertex])
                dfsTraverse(node.tailVertex,flags);
            node=node.headNext;
        }
    }

    @Override
    protected void bfsTraverse(int curNode, boolean[] flags) {
        LinkedList<Integer> queue=new LinkedList<>();
        LinkedList<Integer> queue2=new LinkedList<>();
        queue.add(curNode);
        flags[curNode]=true;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                int nodeIndex=queue.pop();
                System.out.print(vertexes[nodeIndex].data+",");
                //将与curNode相连的未访问过的节点压栈
                CArcNode curArc=vertexes[nodeIndex].firstOut;
                while (curArc!=null){
                    if (!flags[curArc.tailVertex])
                    {
                        queue2.add(curArc.tailVertex);
                        flags[curArc.tailVertex]=true;
                    }
                    curArc=curArc.headNext;
                }
            }
            LinkedList tmp=queue;
            queue=queue2;
            queue2=tmp;
        }
    }

    @Override
    public String toString() {
        //输出时，先输出邻接表，再输出邻接表
        String result="邻接表为：\n";
        String inverseRes="逆邻接表为：\n";
        for (CVertexNode node:vertexes){
            //邻接表遍历
            result+=node.data;
            result+=":";
            CArcNode curNode=node.firstOut;
            while (curNode!=null){
                result+=node.data+vertexes[curNode.tailVertex].data.toString()
                        +"(weight:"+curNode.weight+"),";
                curNode=curNode.headNext;
            }
            result+="\n";
            //逆邻接表遍历
            inverseRes+=node.data;
            inverseRes+=":";
            curNode=node.firstIn;
            while (curNode!=null){
                inverseRes+=vertexes[curNode.headVertex].data.toString()+node.data
                        +"(weight:"+curNode.weight+"),";
                curNode=curNode.tailNext;
            }
            inverseRes+="\n";
        }
        return result+inverseRes;
    }
}
