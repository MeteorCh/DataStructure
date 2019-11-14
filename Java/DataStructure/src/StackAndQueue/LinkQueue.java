package StackAndQueue;

import LinkList.DoublyLinkedList;

public class LinkQueue<T> extends DoublyLinkedList<T> {

    public LinkQueue(){//默认构造函数
        super();
    }

    public LinkQueue(T[] data){//从数组构造队列
        super();
        for (T item:data){
            enQueue(item);
        }
    }
    /**
     * 入队操作
     * @param data
     */
    public void enQueue(T data){
        insert(this.length,data);
    }

    /**
     * 出队操作
     * @return
     */
    public T deQueue(){
        if (!isEmpty())
            return remove(0);
        return null;
    }

    /**
     * 判断队列是否为空
     * @return 空返回true，非空返回false
     */
    public boolean isEmpty(){
        return this.length==0;
    }
}
