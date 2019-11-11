
import StackAndQueue.LinkQueue;

public class Main {
    public static void main(String[] args){
        LinkQueue<String> queue=new LinkQueue<>();
        queue.enQueue("0");
        queue.enQueue("1");
        queue.enQueue("2");
        System.out.println(queue);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}