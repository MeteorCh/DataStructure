package LinkList;

/**
 * 单链表的实现
 * [使用示例]
 LinkList<String> linkList= new LinkList<String>();
 linkList.pushBack("0");
 linkList.insert(0,"1");
 linkList.insert(2,"2");
 System.out.println(linkList);
 System.out.println(linkList.indexOf("2"));
 */
public class LinkList<T> {

    protected Node head=null;//头结点
    protected int length=0;//链表的长度


    /**
     * 获取链表长度
     * @return
     */
    public int getLength(){
        return length;
    }

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
     * 获取position下标下的数据内容
     * @param position
     * @return
     */
    public T get(int position){
        return getNode(position).data;
    }

    /**
     * 在链表末尾插入节点
     * @param data
     */
    public void pushBack(T data){
       insert(this.length,data);
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
            head.next=oldFirst;
        }else {
            Node node= getNode(position-1);
            Node nextNode=node.next;
            node.next=newNode;
            newNode.next=nextNode;
        }
        this.length++;
    }

    /**
     * 移除链表中的position下标的元素，并返回元素值
     * @param position
     * @return
     */
    public T remove(int position){
        T data=null;
        if (position==0){
            data=head.data;
            head=head.next;
        }else if(position<length){
            Node node= getNode(position-1);
            Node positionNode=node.next;
            node.next=node.next.next;
            data=positionNode.data;
        }
        length--;
        return data;
    }

    /**
     * 找到第一个出现data的索引位置
     * @param data
     * @return
     */
    public int indexOf(T data){
        Node curNode=head;
        int position=0;
        while (curNode!=null)
        {
            if (curNode.data.equals(data))
                return position;
            curNode=curNode.next;
            position++;
        }
        return -1;
    }

    @Override
    public String toString() {
        Node curNode=head;
        String outString="";
        while (curNode!=null)
        {
            outString+=curNode.toString()+",";
            curNode=curNode.next;
        }
        return outString;
    }

    public class Node{
        T data;//数据域
        Node next;//后继

        public Node(T data){
            this.data=data;
            next=null;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
