import LinkList.DoublyLinkedList;
import LinkList.LinkList;
import StackAndQueue.LinkStack;

public class Main {
    public static void main(String[] args){
        LinkStack<String> stack=new LinkStack<>();
        stack.pushBack("0");
        stack.pushBack("1");
        stack.pushBack("2");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}