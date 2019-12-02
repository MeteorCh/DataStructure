package Graph;

import java.util.LinkedList;

/**
 * 图的邻接矩阵实现
 * 使用示例：
 AdjMatrixGraphy graphy=new AdjMatrixGraphy();
 System.out.print(graphy);
 graphy.traverse(false);
 System.out.println();
 graphy.traverse(true);
 */
public class AdjMatrixGraphy {
    public static int INFINITY=Integer.MAX_VALUE/2;
    protected int vertexNum=0;//图中顶点的数量
    protected int[][] matrix;//邻接矩阵
    protected String[] vertexNames;//顶点名

    AdjMatrixGraphy(int vertexNum){
        this.vertexNum=vertexNum;
        vertexNames=new String[vertexNum];
        matrix=new int[vertexNum][vertexNum];
        //将邻接矩阵中的值初始化为INFINITY
        for (int i=0;i<vertexNum;i++)
            for (int j=0;j<vertexNum;j++)
                matrix[i][j]=INFINITY;
    }

    public AdjMatrixGraphy(){//默认构造函数
        this(6);
        makeTestData();
    }

    /**
     * 生成测试数据
     */
    protected void makeTestData(){
        //顶点名称赋值
        String[] vertexes={"V1","V2","V3","V4","V5","V6"};
        for (int i=0;i<vertexNum;i++)
            vertexNames[i]=vertexes[i];
        //边赋值
        matrix[0][1]=5;matrix[0][3]=7;
        matrix[1][2]=4;
        matrix[2][0]=8;matrix[2][5]=9;
        matrix[3][2]=5;matrix[3][5]=6;
        matrix[4][3]=5;
        matrix[5][0]=3;matrix[5][4]=1;
    }

    /**
     * 遍历图
     * @param choice false为深度优先遍历，1为广度优先遍历
     */
    public void traverse(boolean choice){
        if (choice)
        {
            System.out.print("广度优先遍历结果为：");
            boolean[] flags=new boolean[vertexNum];
            for (int i=0;i<vertexNum;++i){
                if (!flags[i]){//如果当前节点没有访问过，则从当前节点开始深度优先遍历
                    bfsTraverse(0,flags);
                }
            }
        }
        else
        {
            System.out.print("深度优先遍历结果为：");
            boolean[] flags=new boolean[vertexNum];
            for (int i=0;i<vertexNum;++i){
                if (!flags[i]){//如果当前节点没有访问过，则从当前节点开始深度优先遍历
                    dfsTraverse(0,flags);
                }
            }
        }
    }

    /**
     * 深度优先遍历图
     */
    protected void dfsTraverse(int curNode,boolean[] flags){
        System.out.print(vertexNames[curNode]+",");
        flags[curNode]=true;
        //寻找curNode相邻的顶点访问
        for (int i=0;i<vertexNum;i++){
            if (matrix[curNode][i]<INFINITY&&!flags[i])
                dfsTraverse(i,flags);
        }
    }

    /**
     * 广度优先遍历图
     */
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
