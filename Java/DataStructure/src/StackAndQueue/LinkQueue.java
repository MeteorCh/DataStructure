package StackAndQueue;

import LinkList.DoublyLinkedList;

public class LinkQueue<T> extends DoublyLinkedList<T> {
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
