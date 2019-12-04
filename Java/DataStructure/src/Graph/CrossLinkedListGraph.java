package Graph;

public class CrossLinkedListGraph<T,E extends Number> extends Graph {

    CrossLinkedListGraph(int vertexNum) {
        super(vertexNum);
    }

    /**
     * 边类
     * @param <T> 权重泛型
     */
    public static class CArcNode<T extends Number>{
        int headVertex,tailVertex;//边的起点和终点在数组中的下标
        CArcNode<T> tailNext,headNext;//与该边共尾和共头的下一条边

        public CArcNode(int headVertex,int tailVertex){
            this.headVertex=headVertex;
            this.tailVertex=tailVertex;
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







    @Override
    protected void dfsTraverse(int curNode, boolean[] flags) {
    }

    @Override
    protected void bfsTraverse(int curNode, boolean[] flags) {

    }
}
