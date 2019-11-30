package Tree;

import BinaryTree.LinkClueBinaryTree;

import java.util.Comparator;
import java.util.LinkedList;

public class HuffmanTree<T extends HuffBaseType> extends LinkClueBinaryTree<T> {

    /**
     * 构造函数
     * @param data
     */
    public HuffmanTree(LinkedList<T> data){
        //首先对data中的数据进行降序排序
        data.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {//比较函数，正数表示o2大于o1
                if (o2.compare(o1)<0)
                    return 1;
                else
                    return -1;
            }
        });
        //创建一个linkList来存储节点
        LinkedList<Node<T>> nodeList=new LinkedList<>();
        for (HuffBaseType type:data)
        {
            Node<T> node=new Node(type);
            nodeList.add(node);
        }
        while (nodeList.size()>1){
            //弹出两个节点
            Node<T> minNode1=nodeList.pop();
            Node<T> minNode2=nodeList.pop();
            HuffBaseType newData=new HuffBaseType(minNode1.getData().id +minNode2.getData().id,
                    minNode1.getData().weight+minNode2.getData().weight);
            Node<T> newRoot=new Node(newData);
            newRoot.setlChild(minNode1);
            newRoot.setrChild(minNode2);
            //将newRoot插入到data中,因为data已经是升序排列的，所以从前往后遍历找到插入位置
            int i=0;
            for (;i<nodeList.size();++i){
                Node<T> element=nodeList.get(i);
                if (element.getData().compare(newData)>0){
                    nodeList.add(i,newRoot);
                    break;
                }
            }
            if (i==nodeList.size())
                nodeList.add(newRoot);
            this.root=newRoot;
        }
        //构建完成后，进行中序线索化
        midTraverseClue(root);
    }

    protected void printHuffCode(Node node,String code){
        if (node!=null){
            //如果节点是叶子节点
            if (node.isLeafNode())
                System.out.println(node.getData()+"的Huffman编码为："+code);
            else {
                if (node.getlChild()!=null)
                    printHuffCode(node.getlChild(),code+"0");
                if (node.getrChild()!=null)
                    printHuffCode(node.getrChild(),code+"1");
            }
        }
    }

    /**
     * 输出叶节点的Huffman编码
     */
    public void printHuffCode(){
        printHuffCode(root,"");
    }
}
