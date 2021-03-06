package Graph;

public abstract class Graph<E extends Number> {
    protected int vertexNum;

    Graph(int vertexNum){
        this.vertexNum=vertexNum;
    }

    /**
     * 遍历
     * @param choice true为广度优先，false为深度优先
     */
    public void traverse(boolean choice){
        boolean[] flags = new boolean[vertexNum];
        if (choice) {
            System.out.print("广度优先遍历结果为：");
            for (int i=0;i<vertexNum;++i){
                if (!flags[i]){//如果当前节点没有访问过，则从当前节点开始深度优先遍历
                    bfsTraverse(i,flags);
                }
            }
        }else{
            System.out.print("深度优先遍历结果为：");
            for (int i=0;i<vertexNum;++i){
                if (!flags[i]){//如果当前节点没有访问过，则从当前节点开始深度优先遍历
                    dfsTraverse(i,flags);
                }
            }
        }
    }

    /**
     * 给图中增加一条边
     * @param headIndex 起点索引
     * @param tailIndex 终点索引
     * @param weight 权重值
     */
    protected void addEdge(int headIndex,int tailIndex,E weight){}

    /**
     * 深度优先遍历
     * @param curNode 当前访问节点
     * @param flags 访问标记数组
     */
    protected abstract void dfsTraverse(int curNode,boolean[] flags);
    /**
     * 广度优先遍历
     * @param curNode 当前访问节点
     * @param flags 访问标记数组
     */
    protected abstract void bfsTraverse(int curNode,boolean[] flags);
}
