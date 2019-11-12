package MyString;

import java.util.ArrayList;
import java.util.List;

public class MyString {

    public static void main(String[] args) {
        MyString string=new MyString();
        string.getNext1("abababca");
        System.out.println(string.kmpMatch("abcdeabcabc","bca")?"匹配成功":"匹配失败");
    }


    public boolean kmpMatch(String string,String pattern){
        List<Integer> next=getNext1(pattern);
        int i=0,j=0;
        while (i<string.length()&&j<pattern.length()){
            if (string.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else {
                j=next.get(j);
                if (j==-1){
                    j++;
                    i++;
                }
            }
        }
        if(j==pattern.length())
            return true;
        return false;
    }


    List<Integer> getNext1(String p)
    {
        List<Integer> next=new ArrayList<>();
        for (int i=0;i<p.length();i++)
            next.add(i,0);
        next.set(0,-1);
        int i = 0, j = -1;
        while (i < p.length()-1)
        {
            if (j == -1 || p.charAt(i) == p.charAt(j))
            {
                ++i;
                ++j;
                next.set(i,j);
            }
            else
                j = next.get(j);
        }
        return next;
    }

    List<Integer> getNext2(String string){
        List<Integer> next=new ArrayList<>();
        for (int i=0;i<string.length();i++)
            next.add(i,0);
        next.set(0,-1);
        for (int i=2;i<string.length();i++){
            int max_len=i-1;
            int len;
            for (len=max_len;len>=1;len--){
                int j=0;
                for (;j<len;j++){
                    if (string.charAt(j)!=string.charAt(j+i-len))
                        break;
                }
                if (j==len)
                {
                    next.set(i,len);
                    break;
                }
            }
            if (len<1)
                next.set(i,0);
        }
        return next;
    }
}
