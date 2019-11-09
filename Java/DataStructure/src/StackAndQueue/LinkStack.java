package StackAndQueue;


import LinkList.LinkList;

public class LinkStack<T> extends LinkList<T> {
    public boolean isEmpty(){
        return length==0;
    }

    /**
     * 弹栈
     * @return
     */
    public T pop(){
        if (this.head!=null){
            return remove(0);
        }
        return null;
    }

    /**
     * 重写pushback方法，使用头插法，每次将节点插入到链表的头部
     * @param data
     */
    @Override
    public void pushBack(T data) {
        insert(0,data);
    }

    /**
     * 重写toString方法，从后往前输出
     * @return
     */
    @Override
    public String toString() {
        String result="";
        Node node=head;
        while (node!=null){
            result=node.getData()+","+result;
            node=node.getNext();
        }
        return result;
    }
}
