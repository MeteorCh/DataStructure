package LinkList;

/**
 * 双向循环链表
 * 用法示例：
 LinkList.DoublyLinkedList<String> list=new DoublyLinkedList<String>();
 list.insert(0,"0");
 list.insert(0,"1");
 list.insert(0,"2");
 list.insert(1,"3");
 list.insert(4,"4");
 list.remove(4);
 System.out.println(list);
 */
public class DoublyLinkedList<T> {

    protected class Node{
        T data;//数据域
        Node pre;//前驱节点
        Node next;//后继节点

        Node(T data){
            this.data=data;
            pre=next=null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node head=null;//头结点
    protected int length=0;//链表长度

    /**
     * 获取position位置的节点
     * @param position
     * @return
     */
    protected Node getNode(int position)
    {
        if (head==null||position<0||position>=length)
            throw new IndexOutOfBoundsException();
        else {
            Node curNode=head;
            int tmp=0;
            while (tmp<position){
                curNode=curNode.next;
                tmp++;
            }
            return curNode;
        }
    }

    /**
     * 在position位置插入数据
     * @param position
     * @param data
     */
    public void insert(int position,T data){
        Node newNode=new Node(data);
        if (position==0){
            Node oldFirst=head;
            head=newNode;
            //头结点的前驱后继
            head.next=oldFirst;
            if (oldFirst!=null) {
                head.pre=oldFirst.pre;
                //让末尾节点的next指向头结点
                oldFirst.pre.next=head;
                //以前头结点的前驱
                oldFirst.pre=head;
            }else {
                newNode.pre=newNode;
                newNode.next=newNode;
            }
        }else {
            Node node= getNode(position-1);
            newNode.pre=node;
            if (node.next==null){//插入位置为空，说明是插入到链表的末尾
                newNode.next=head;
                head.pre=newNode;
            }else {//说明插入到两个结点的中间
                newNode.next=node.next;
                node.next.pre=newNode;
            }
            node.next=newNode;
        }
        this.length++;
    }

    /**
     * 移除position位置处的元素
     * @param position
     * @return
     */
    public T remove(int position){
        T data=null;
        if (position==0){//移除的是头结点
            data=head.data;
            if (this.length==1)//如果只有一个元素，直接置空
                this.head=null;
            else {
                Node oldHead=head;
                head=head.next;
                //头结点的next
                head.pre=oldHead.pre;
                oldHead.pre.next=head;
            }
        }else {//移除是非头结点
            Node node= getNode(position-1);
            if (node.next==null){//移除的节点是最后一个节点
                node.next=head;
                head.pre=node;
            }else {//移除的节点是中间节点
                Node removeNode=node.next;
                node.next=removeNode.next;
                removeNode.next.pre=node;
            }
        }
        this.length--;
        return data;
    }

    @Override
    public String toString() {
        Node curNode=head;
        String outString="";
        if (curNode!=null){
            do {
                outString+=curNode.toString()+",";
                curNode=curNode.next;
            }while (curNode!=head);
        }
        return outString;
    }


}
