package Searching.BinarySortingTree;

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
    }

    TreeNode root;

    public AVLTree(int[] data){
        for (int item:data)
            insertData(item);
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

    public void deleteNode(int data){//提供给外部操作的接口
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
            if (balance>1&&getBalanceFactor(node.lChild)>=0) {//LL型
                System.out.println("LL型删除");
                tmp.lChild=llRotate(node);
                tmp.lChild.parent=tmp;
            }
            if (balance > 1 && getBalanceFactor(node.lChild) < 0){ //LR型
                System.out.println("LR型删除");
                //这里需要判断是接到左子树上还是右子树上
                boolean flag=checkChild(tmp,node);//flag为false代表在左子树上，true代表在右子树上
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
                tmp.rChild=rrRotate(node);
                tmp.rChild.parent=tmp;
            }

            if (balance < -1 && getBalanceFactor(node.rChild) > 0){  //Rl型
                System.out.println("RL型删除");
                //这里需要判断是接到左子树上还是右子树上
                boolean flag=checkChild(tmp,node);//flag为false代表在左子树上，true代表在右子树上
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
        if (parent.lChild==child)
            return false;
        else
            return true;
    }

    protected void deleteNode(TreeNode node){
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
