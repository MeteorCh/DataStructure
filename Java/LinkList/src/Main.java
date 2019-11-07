import LinkList.LinkList;

public class Main {
    public static void main(String[] args){
        LinkList<String> linkList= new LinkList<String>();
        linkList.pushBack("0");
        linkList.insert(0,"1");
        linkList.insert(2,"2");
        System.out.println(linkList);
        System.out.println(linkList.indexOf("2"));
    }
}