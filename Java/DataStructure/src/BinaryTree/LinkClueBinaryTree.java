package BinaryTree;

import StackAndQueue.LinkQueue;
import java.util.Scanner;

/**
 * 链式线索二叉树
 * 使用示例：
 LinkClueBinaryTree<String> binaryTree=new LinkClueBinaryTree<>();
 String[] s=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
 LinkQueue<String> data=new LinkQueue<>(s);
 binaryTree.creStringTree(data);
 System.out.print("前序遍历结果为：");
 binaryTree.print(0);
 System.out.println();
 System.out.print("中序遍历结果为：");
 binaryTree.print(1);
 System.out.println();
 System.out.print("后序遍历结果为：");
 binaryTree.print(2);
 System.out.println();
 System.out.print("层次遍历结果为：");
 binaryTree.print(3);
 System.out.println();
 System.out.print("中序线索遍历结果为：");
 binaryTree.print(4);
 System.out.println();
 System.out.print("树的高度为："+binaryTree.getHeight());
 System.out.println();
 System.out.print("树中结点个数为："+binaryTree.getSize());
 System.out.println();
 */
public class LinkClueBinaryTree<T> {

    public class Node<T> {
        protected T data;
        protected Node lChild;
        protected Node rChild;
        protected boolean lFlag=false;
        protected boolean rFlag=false;
        public Node(T data){
            this.data=data;
            lChild=null;
            rChild=null;
        }

        public T getData() {
            return data;
        }

        public void setrChild(Node rChild) {
            this.rChild = rChild;
        }

        public void setlChild(Node lChild) {
            this.lChild = lChild;
        }

        public boolean isLeafNode(){
            return (this.lChild==null&&this.rFlag)
                    ||(this.lFlag&&this.rChild==null)
                    ||(this.lFlag&&this.rFlag);
        }

        public Node getlChild() {
            return lChild;
        }

        public Node getrChild() {
            return rChild;
        }
    }

    protected Node<T> root;//根节点
    private Node<T> pre=null;//记录前节点的全局变量


    public void creStringTree(){
        Scanner scanner=new Scanner(System.in);
        root=createStringNode(scanner,null);
        midTraverseClue(root);
    }

    public void creStringTree(LinkQueue<String> data){
        root=createStringNode(null,data);
        //扫描二叉树创建线索
        midTraverseClue(root);
    }

    protected Node createStringNode( Scanner scanner,LinkQueue<String> data){
        String inputStr=null;
        if (data==null&&scanner!=null)
        {
            System.out.print("请输入数据（输入#结束）：");
            inputStr=scanner.next();
        }else if (data!=null&&scanner==null)
            inputStr=data.deQueue();
        Node node;
        if (inputStr.equals("#")){
            return null;
        }else {
            node=new Node(inputStr);
            node.lChild=createStringNode(scanner,data);
            node.rChild=createStringNode(scanner,data);
        }
        return node;
    }

    /**
     * 中序遍历线索化
     * @param node
     */
    protected void midTraverseClue(Node node){
        if (node!=null){
            midTraverseClue(node.lChild);
            if (node.lChild==null){
                node.lChild=pre;
                node.lFlag=true;
            }
            if (pre!=null&&pre.rChild==null){
                pre.rFlag=true;
                pre.rChild=node;
            }
            pre=node;
            midTraverseClue(node.rChild);
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    protected void preTraverse(Node node){
        if (node!=null)
        {
            System.out.print(node.data+",");
            if (!node.lFlag)
                preTraverse(node.lChild);
            if (!node.rFlag)
            preTraverse(node.rChild);
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    protected void midTraverse(Node node){
        if (node!=null){
            if (!node.lFlag)
                midTraverse(node.lChild);
            System.out.print(node.data+",");
            if (!node.rFlag)
                midTraverse(node.rChild);
        }
    }

    protected Node first(Node node){
        Node curNode=node;
        while(curNode!=null&&!curNode.lFlag)
            curNode=node.lChild;
        return curNode;
    }

    /**
     * 在线索化中序遍历过程中，查找node的下一个节点
     * @param node
     */
    protected Node getNextNode(Node node){
        if (!node.rFlag){
            return first(node.rChild);
        }else {
            return node.rChild;
        }
    }

    /**
     * 线索化中序遍历
     * @param node
     */
    public void clueMidTraverse(Node node){
        Node curNode=first(node);//首先找到第一个节点，中序遍历中，为最左下角的一个节点
        while (curNode!=null){
            System.out.print(curNode.data+",");
            curNode=getNextNode(curNode);
        }
    }

    /**
     * 后续遍历
     * @param node
     */
    protected void postTraverse(Node node){
        if (node!=null){
            if (!node.lFlag)
                postTraverse(node.lChild);
            if (!node.rFlag)
                postTraverse(node.rChild);
            System.out.print(node.data+",");
        }
    }

    /**
     * 层次遍历
     */
    protected void levelTraverse(){
        if (root!=null){
            LinkQueue<Node> queue=new LinkQueue<>();
            queue.enQueue(root);
            LinkQueue<Node> queue1=new LinkQueue<>();
            do {
                while (!queue.isEmpty()){
                    Node node=queue.deQueue();
                    System.out.print(node.data+",");
                    if (node.lChild!=null&&!node.lFlag)
                        queue1.enQueue(node.lChild);
                    if (node.rChild!=null&&!node.rFlag)
                        queue1.enQueue(node.rChild);
                }
                LinkQueue temp=queue;
                queue=queue1;
                queue1=temp;
            }while (!queue.isEmpty());
        }
    }

    /**
     * 按照选择输出树
     * @param choice：0为前序遍历，1为中序遍历，2为后续遍历，3为层次遍历
     */
    public void print(int choice){
        switch (choice){
            case 0:
                System.out.println("前序遍历结果为：");
                preTraverse(root);
                break;
            case 1:
                System.out.println("中序遍历结果为：");
                midTraverse(root);
                break;
            case 2:
                System.out.println("后序遍历结果为：");
                postTraverse(root);
                break;
            case 3:
                System.out.println("层次遍历结果为：");
                levelTraverse();
                break;
            case 4:
                System.out.println("线索化中序遍历结果为：");
                clueMidTraverse(root);
                break;
            default:
                System.out.println("输入参数有误");
        }
    }

    /**
     * 获取树的高度
     * @return
     */
    public int getHeight(){//获取树的高度
        return getHeight(root);
    }

    protected int getHeight(Node node){
        if (node==null)
            return 0;
        else {
            int lHeight=0,rHeight=0;
            if (!node.lFlag)
                lHeight=1+getHeight(node.lChild);
            if (!node.rFlag)
                rHeight=1+getHeight(node.rChild);
            return lHeight>rHeight?lHeight:rHeight;
        }
    }

    /**
     * 获取树中的节点个数
     * @return
     */
    public int getSize(){//获取树的节点总数
        return getSize(root);
    }

    protected int getSize(Node node){
        if (node==null||(node.lChild==null&&node.rChild==null))
            return 0;
        else {
            int lSize=0,rSize=0;
            if (!node.lFlag)
                lSize=1+getSize(node.lChild);
            if (!node.rFlag)
                rSize=1+getSize(node.rChild);
            return lSize+rSize;
        }
    }
}
