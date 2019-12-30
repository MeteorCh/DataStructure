package Searching.BinarySortingTree;

/**
 * 二叉排序树
 * 使用示例：
 *          int[] data={62,88,58,47,35,73,51,99,37,93};
 *         BinarySortingTree tree=new BinarySortingTree(data);
 *         tree.find(35);
 *         tree.deleteData(62);
 */
public class BinarySortingTree{

    public static class TreeNode {
        protected int data;//数据
        protected TreeNode lChild,rChild,parent;//左孩子、右孩子、父节点
        public TreeNode(int data){
            this.data=data;
            lChild=rChild=parent=null;
        }
    }

    protected TreeNode root;//根节点
    public BinarySortingTree(){
    }

    public BinarySortingTree(int[] data){//根据传入的数据构建二叉排序树
        for (int i=0;i<data.length;i++){
            insertData(data[i]);
        }
    }

    /**
     * 插入数据
     * @param data
     */
    private void insertData(int data){
        if (root==null){
            //如果根节点为空，给根节点开辟空间
            root=new TreeNode(data);
        }else {
            //如果不是根节点，遍历树得到根节点
            boolean[] flag=new boolean[1];
            flag[0]=false;
            TreeNode insertPos=findInsertPosition(root,data,flag);
            if (flag[0])
                System.out.println("输入的键值重复！");
            else{
                if (insertPos.lChild==null&&insertPos.data>data) {
                    insertPos.lChild=new TreeNode(data);
                    insertPos.lChild.parent=insertPos;
                }
                else if (insertPos.rChild==null&&insertPos.data<data) {
                    insertPos.rChild=new TreeNode(data);
                    insertPos.rChild.parent=insertPos;
                }
            }
        }
    }

    /**
     * 查找data
     * @param data
     * @return
     */
    public void find(int data){
        if (root!=null){
            boolean[] flag=new boolean[1];
            flag[0]=false;
            findInsertPosition(root,data,flag);
            if (flag[0])
                System.out.println("查找的元素"+data+"在数据表中存在");
            else
                System.out.println("查找的元素"+data+"在数据表中不存在");
        }
    }

    /**
     * 查找data的插入位置
     * @param data
     * @return
     */
    protected TreeNode findInsertPosition(TreeNode node,int data,boolean[] flag){
        if (data>node.data)
        {
            if (node.rChild==null)
                return node;
            else
                return findInsertPosition(node.rChild,data,flag);
        }
        else if (data<node.data)
        {
            if (node.lChild==null)
                return node;
            else
                return findInsertPosition(node.lChild,data,flag);
        }
        else{
            flag[0]=true;
            return node;
        }


    }

    /**
     * 提供的对外操作接口
     * @param key
     */
    public void deleteData(int key){
        deleteData(root,key);
    }

    /**
     * 在二叉排序树中删除data元素
     * @param key
     */
    protected void deleteData(TreeNode node,int key){
        if (node==null)
            System.out.println("数据表中不存在"+key);
        else {
            if (key==node.data)
                deleteData(node);
            else if (key<node.data)
                deleteData(node.lChild,key);
            else
                deleteData(node.rChild,key);
        }
    }

    /**
     * 根据节点的左右子树情况来删除节点
     * @param node
     */
    protected void deleteData(TreeNode node){
        if (node.rChild==null||node.lChild==null){//右子树或右子树为空
            TreeNode replacement=node.rChild==null?node.lChild:node.rChild;
            if (node.parent!=null){
                if (node==node.parent.lChild)
                    node.parent.lChild=replacement;
                else if (node==node.parent.rChild)
                    node.parent.rChild=replacement;
                if (replacement!=null)
                    replacement.parent=node.parent;
            }else { //如果node为根节点
                root=replacement;
                replacement.parent=null;
            }
            //清空node释放内存
            node.parent=node.lChild=node.rChild=null;
        }else {//左右子树都不为空
            //找到node的左子树中最右边的叶节点
            TreeNode curNode=node.lChild;
            while (curNode.rChild!=null){
                curNode=curNode.rChild;
            }
            //将curNode的值直接赋值给删除节点
            node.data=curNode.data;
            deleteData(curNode);
        }
    }
}
