package Graph;

import java.util.LinkedList;

/**
 * 图的邻接多重表表示
 * @param <T> 节点数据泛型
 * @param <E> 权重泛型
 */
public class AdjMultipleListGraph<T,E extends Number> extends Graph<E>{

    /**
     * 邻接多重表中的顶点
     * @param <T> 节点数据泛型
     * @param <E> 权重泛型
     */
    public static class AMVertex<T,E extends Number>{
        T data;
        CrossLinkedListGraph.CArcNode<E> firstEdge;
        AMVertex(T data){
            this.data=data;
            firstEdge=null;
        }
    }

    protected AMVertex<T,E>[] vertices;//顶点数组


    public AdjMultipleListGraph(int vertexNum) {
        super(vertexNum);
        vertices=new AMVertex[vertexNum];
    }

    @Override
    public void addEdge(int headIndex, int tailIndex, E weight){
        CrossLinkedListGraph.CArcNode<E> arc=new CrossLinkedListGraph.CArcNode<E>(headIndex,tailIndex,weight);
        //采用头插法插入边
        AMVertex headVertex=vertices[headIndex];
        arc.headNext=headVertex.firstEdge;
        headVertex.firstEdge=arc;

        AMVertex tailVertex=vertices[tailIndex];
        arc.tailNext=tailVertex.firstEdge;
        tailVertex.firstEdge=arc;
    }

    public static AdjMultipleListGraph getInstance(){
        AdjMultipleListGraph<String, Integer> graph=new AdjMultipleListGraph<String, Integer>(5);
        for (int i=0;i<5;i++)
            graph.vertices[i]=new AMVertex<>("V"+(i+1));
        int[][] testData={{0,1,1},{0,3,2},{2,1,3},{2,3,4},
                {4,1,5},{2,4,6}};
        for (int i=0;i<testData.length;++i)
            graph.addEdge(testData[i][0],testData[i][1],testData[i][2]);
        return graph;
    }

    @Override
    protected void dfsTraverse(int curNode, boolean[] flags) {
        System.out.print(vertices[curNode].data+",");
        flags[curNode]=true;
        //寻找curNode相连的节点深度优先搜索
        CrossLinkedListGraph.CArcNode node=vertices[curNode].firstEdge;
        while(node!=null){
            int nextIndex=-1;
            if (curNode==node.headVertex){
                nextIndex=node.tailVertex;
                node=node.headNext;
            }else if (curNode==node.tailVertex){
                nextIndex=node.headVertex;
                node=node.tailNext;
            }
            if (!flags[nextIndex])
                dfsTraverse(nextIndex,flags);
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
                System.out.print(vertices[nodeIndex].data+",");
                //将与curNode相连的未访问过的节点压栈
                CrossLinkedListGraph.CArcNode curArc=vertices[nodeIndex].firstEdge;
                while (curArc!=null){
                    int nextIndex=-1;
                    if (curArc.headVertex==nodeIndex) {
                        nextIndex=curArc.tailVertex;
                        curArc=curArc.headNext;
                    }
                    else if (curArc.tailVertex==nodeIndex){
                        nextIndex=curArc.headVertex;
                        curArc=curArc.tailNext;
                    }
                    if (!flags[nextIndex])
                    {
                        queue2.add(nextIndex);
                        flags[nextIndex]=true;
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
        String result="各顶点依附的弧为：\n";
        for (int i=0;i<vertexNum;i++){
            result+=vertices[i].data+": ";
            CrossLinkedListGraph.CArcNode curArc=vertices[i].firstEdge;
            while (curArc!=null)
            {
                if (curArc.headVertex==i)
                {
                    result+=vertices[curArc.headVertex].data.toString()+vertices[curArc.tailVertex].data.toString()
                    +"(weight:"+curArc.weight+"),";
                    curArc=curArc.headNext;
                }else if (curArc.tailVertex==i){
                    result+=vertices[curArc.tailVertex].data.toString()+vertices[curArc.headVertex].data.toString()
                            +"(weight:"+curArc.weight+"),";
                    curArc=curArc.tailNext;
                }
            }
            result+="\n";
        }
        return result;
    }
}
