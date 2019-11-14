package Tree;

import StackAndQueue.LinkQueue;
import java.util.Scanner;

/**
 * 二叉树的实现
 * 使用示例：
 LinkBinaryTree<String> binaryTree=new LinkBinaryTree<>();
 String[] s=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
 LinkQueue<String> data=new LinkQueue<>(s);
 binaryTree.creStringTree(data);
 System.out.print("前序遍历结果为：");
 binaryTree.print(0);
 System.out.print("中序遍历结果为：");
 binaryTree.print(1);
 System.out.print("后序遍历结果为：");
 binaryTree.print(2);
 System.out.print("层次遍历结果为：");
 binaryTree.print(3);
 System.out.print("树的高度为："+binaryTree.getHeight());
 */
public class LinkBinaryTree<T> {
    public class Node<T>{//二叉树中的节点类
        T data;
        Node lChild;
        Node rChild;
        public Node(T data){
            this.data=data;
            lChild=null;
            rChild=null;
        }
    }

    protected Node root;//根节点


    public void creStringTree(){
        Scanner scanner=new Scanner(System.in);
        root=createStringNode(scanner,null);
    }

    public void creStringTree(LinkQueue<String> data){
        root=createStringNode(null,data);
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
     * 前序遍历
     * @param node
     */
    protected void preTraverse(Node node){
        if (node!=null)
        {
            System.out.print(node.data+",");
            preTraverse(node.lChild);
            preTraverse(node.rChild);
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    protected void midTraverse(Node node){
        if (node!=null){
            midTraverse(node.lChild);
            System.out.print(node.data+",");
            midTraverse(node.rChild);
        }
    }

    /**
     * 后续遍历
     * @param node
     */
    protected void postTraverse(Node node){
        if (node!=null){
            postTraverse(node.lChild);
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
                    if (node.lChild!=null)
                        queue1.enQueue(node.lChild);
                    if (node.rChild!=null)
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
                preTraverse(root);
                break;
            case 1:
                midTraverse(root);
                break;
            case 2:
                postTraverse(root);
                break;
            case 3:
                levelTraverse();
                break;
            default:
                System.out.println("输入参数有误");
        }
    }

    public int getHeight(){//获取树的高度
        return getHeight(root);
    }

    protected int getHeight(Node node){
        if (node==null)
            return 0;
        else {
            int lHeight=1+getHeight(node.lChild);
            int rHeight=1+getHeight(node.rChild);
            return lHeight>rHeight?lHeight:rHeight;
        }
    }
}
