
import Tree.HuffBaseType;
import Tree.HuffmanTree;

import java.util.LinkedList;


public class Main {
    public static void main(String[] args){
        LinkedList<HuffBaseType> datas=new LinkedList<>();
        datas.add(new HuffBaseType("A",9));
        datas.add(new HuffBaseType("B",4));
        datas.add(new HuffBaseType("C",5));
        datas.add(new HuffBaseType("D",2));
        HuffmanTree<HuffBaseType> huffmanTree=new HuffmanTree<>(datas);
        huffmanTree.print(3);
    }
}