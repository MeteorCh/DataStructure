package Graph;

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

public class AdjListGraph<T,E extends Number> extends Graph{
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

        public void insertNode(ArcNode node){
            this.next=node;
        }
    }

    protected VertexNode<T,E>[] vertexes;//顶点数组

    protected AdjListGraph(int vertexNum){//分配空间
        this.vertexNum=vertexNum;
        vertexes=new VertexNode[vertexNum];
    }

    public static AdjListGraph getTestInstance(){//构造测试数据实例
        AdjListGraph<String,Integer> graph= new AdjListGraph<String, Integer>(5);
        VertexNode<String,Integer> node1=new VertexNode("V1");
        node1.insertArc(new ArcNode(1,3));
        node1.insertArc(new ArcNode(3,4));
        node1.insertArc(new ArcNode(2,6));
        graph.vertexes[0]= node1;
        VertexNode<String,Integer> node2=new VertexNode("V2");
        node2.insertArc(new ArcNode(0,3));
        node2.insertArc(new ArcNode(3,9));
        graph.vertexes[1]= node2;
        VertexNode<String,Integer> node3=new VertexNode("V3");
        node3.insertArc(new ArcNode(0,6));
        node3.insertArc(new ArcNode(4,5));
        graph.vertexes[2]= node3;
        VertexNode<String,Integer> node4=new VertexNode("V4");
        node4.insertArc(new ArcNode(0,4));
        node4.insertArc(new ArcNode(1,9));
        graph.vertexes[3]= node4;
        VertexNode<String,Integer> node5=new VertexNode("V5");
        node5.insertArc(new ArcNode(2,5));
        graph.vertexes[4]=node5;
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
}
