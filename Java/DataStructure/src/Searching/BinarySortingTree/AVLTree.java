package Searching.BinarySortingTree;

/**
 * 平衡二叉树的实现
 * 使用示例：
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         AVLTree tree=new AVLTree(data);
 *         int findKey=30;
 *         System.out.println("键"+findKey+"在平衡二叉树中"+(tree.find(findKey)==null?"不存在":"存在"));
 * 删除的各类情况示例：
 *      1.右子树中LL型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.deleteNode(70);
 *         tree.deleteNode(90);
 *         tree.deleteNode(80);
 *      2.左子树中LL型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.insertData(-1);
 *         tree.deleteNode(20);
 *      3.右子树中RR型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.deleteNode(70);
 *         tree.deleteNode(90);
 *         tree.deleteNode(80);
 *      4.左子树中RR型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.insertData(25);
 *         tree.deleteNode(0);
 *      5.右子树中LR型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.deleteNode(40);
 *         tree.deleteNode(50);
 *         tree.deleteNode(90);
 *      6.左子树中LR型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.insertData(5);
 *         tree.deleteNode(20);
 *      7.右子树中RL型删除
 *         int[] data={20,10,0,30,40,50,60,80};
 *         tree.deleteNode(40);
 *         tree.insertData(55);
 *         tree.deleteNode(80);
 *      8.左子树中RL型删除
 *         int[] data={20,10,0,30,40,50,60,90,80,70};
 *         tree.insertData(15);
 *         tree.deleteNode(0);
 */
public class AVLTree {
    public static class TreeNode{
        protected int data;//数据
        protected TreeNode lChild,rChild,parent;//左孩子、右孩子、父节点
        protected int height;//树的高度

        public TreeNode(int data){
            this.data=data;
            lChild=rChild=parent=null;
            height=1;
        }

        public int getData() {
            return data;
        }
    }

    TreeNode root;

    public AVLTree(int[] data){
        for (int item:data)
            insertData(item);
    }

    public TreeNode find(int key){//利用非递归的方式实现查找
        TreeNode curNode=root;
        while (curNode!=null){
            if (key==curNode.data){
                return curNode;
            }else if (key<curNode.data){
                curNode=curNode.lChild;
            }else
                curNode=curNode.rChild;
        }
        return null;
    }

    public int getHeight(TreeNode node){
        return node==null?0:node.height;
    }

    public int getBalanceFactor(TreeNode node){
        if (node==null)
            return 0;
        return getHeight(node.lChild)-getHeight(node.rChild);
    }

    protected TreeNode llRotate(TreeNode node){//LL型旋转
        TreeNode xNode=node.lChild;
        node.lChild=xNode.rChild;
        if (xNode.rChild!=null)
            xNode.rChild.parent=node;//父节点调整
        xNode.rChild=node;
        if (xNode.rChild!=null)
            xNode.rChild.parent=xNode;//父节点调整
        node.height=Math.max(getHeight(node.lChild),getHeight(node.rChild))+1;
        xNode.height=Math.max(getHeight(xNode.lChild),getHeight(xNode.lChild))+1;
        return xNode;
    }

    protected TreeNode rrRotate(TreeNode node){//RR型旋转
        TreeNode xNode=node.rChild;
        node.rChild=xNode.lChild;
        if (xNode.lChild!=null)
            xNode.lChild.parent=node;//父节点调整
        xNode.lChild=node;
        if (xNode.lChild!=null)
            xNode.lChild.parent=xNode;//父节点调整
        node.height=Math.max(getHeight(node.lChild),getHeight(node.rChild))+1;
        xNode.height=Math.max(getHeight(xNode.lChild),getHeight(xNode.lChild))+1;
        return xNode;
    }

    public void insertData(int data){
        root=insertData(root,data);
        root.parent=null;
    }

    /**
     * 插入数据
     * @param data
     */
    protected TreeNode insertData(TreeNode node,int data) {
        if (node==null) {
            return new TreeNode(data);
        }
        else {
            if (data<node.data) {
                node.lChild=insertData(node.lChild,data);
                node.lChild.parent=node;
            }
            else if (data>node.data) {
                node.rChild=insertData(node.rChild,data);
                node.rChild.parent=node;
            }
            //更新树中节点的高度
            node.height=1+Math.max(getHeight(node.lChild),getHeight(node.rChild));
            int balance=getBalanceFactor(node);
            if (balance>1&&data<node.lChild.data)//LL型
                return llRotate(node);
            if (balance<-1&&data>node.rChild.data)//RR型
                return rrRotate(node);
            if (balance>1&&data>node.lChild.data){//LR型
                node.lChild=rrRotate(node.lChild);
                node.lChild.parent=node;
                return llRotate(node);
            }
            if (balance<-1&&data<node.rChild.data){//RL型
                node.rChild=llRotate(node.rChild);
                node.rChild.parent=node;
                return rrRotate(node);
            }
        }
        return node;
    }

    public void deleteNode(int data){//提供给外部操作的删除节点接口
        deleteNode(root,data);
    }

    public void deleteNode(TreeNode node,int key){
        if (node==null)
            System.out.println("数据表中不存在"+key);
        else {
            if (key==node.data)
                deleteNode(node);
            else if (key<node.data)
                deleteNode(node.lChild,key);
            else
                deleteNode(node.rChild,key);
            node.height=1+Math.max(getHeight(node.lChild),getHeight(node.rChild));
            int balance=getBalanceFactor(node);
            TreeNode tmp=node.parent;//保存变量，因为node.parent在llRotate时会变化
            boolean flag=checkChild(tmp,node);//flag为false代表在左子树上，true代表在右子树上
            if (balance>1&&getBalanceFactor(node.lChild)>=0) {//LL型
                System.out.println("LL型删除");
                if (flag){//右子树
                    tmp.rChild=llRotate(node);
                    tmp.rChild.parent=tmp;
                }else {//左子树
                    tmp.lChild=llRotate(node);
                    tmp.lChild.parent=tmp;
                }
            }
            if (balance > 1 && getBalanceFactor(node.lChild) < 0){ //LR型
                System.out.println("LR型删除");
                node.lChild =  rrRotate(node.lChild);
                node.lChild.parent=node;
                if (flag){
                    tmp.rChild=llRotate(node);
                    tmp.rChild.parent=tmp;
                }else {
                    tmp.lChild=llRotate(node);
                    tmp.lChild.parent=tmp;
                }
            }

            if (balance < -1 && getBalanceFactor(node.rChild) <= 0) {//RR型
                System.out.println("RR型删除");
                if (flag){//右子树
                    tmp.rChild=rrRotate(node);
                    tmp.rChild.parent=tmp;
                }else {//左子树
                    tmp.lChild=rrRotate(node);
                    tmp.lChild.parent=tmp;
                }
            }
            if (balance < -1 && getBalanceFactor(node.rChild) > 0){  //Rl型
                System.out.println("RL型删除");
                node.rChild = llRotate(node.rChild);
                node.rChild.parent=node;
                if (flag){
                    tmp.rChild=rrRotate(node);
                    tmp.rChild.parent=tmp;
                }else {
                    tmp.lChild=rrRotate(node);
                    tmp.lChild.parent=tmp;
                }
            }
        }
    }

    protected boolean checkChild(TreeNode parent,TreeNode child){
        //这里需要判断是接到左子树上还是右子树上
        if (parent!=null){
            if (parent.lChild==child)
                return false;
            else
                return true;
        }
        return false;
    }

    protected void deleteNode(TreeNode node){//节点的移除操作，分为节点有左子树或右子树为空，左右子树均不为空两种情况
        if (node.rChild==null||node.lChild==null){//右子树或右子树为空
            TreeNode replace=node.rChild==null?node.lChild:node.rChild;
            if (node.parent!=null){
                if (node==node.parent.lChild)
                    node.parent.lChild=replace;
                else if (node==node.parent.rChild)
                    node.parent.rChild=replace;
                if (replace!=null)
                    replace.parent=node.parent;
            }else { //如果node为根节点
                root=replace;
                replace.parent=null;
            }
            //清空node释放内存
            node.parent=node.lChild=node.rChild=null;
        }else {
            //找到node的左子树中最右边的叶节点
            TreeNode curNode=node.lChild;
            while (curNode.rChild!=null){
                curNode=curNode.rChild;
            }
            //将curNode的值直接赋值给删除节点
            node.data=curNode.data;
            deleteNode(curNode);
        }
    }
}
