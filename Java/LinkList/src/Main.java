import LinkList.DoublyLinkedList;

public class Main {
    public static void main(String[] args){
        LinkList.DoublyLinkedList<String> list=new DoublyLinkedList<String>();
        list.insert(0,"0");
        list.insert(0,"1");
        list.insert(0,"2");
        list.insert(1,"3");
        list.insert(4,"4");
        list.remove(4);
        System.out.println(list);
    }
}