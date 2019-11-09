package StackAndQueue;

/**
 * 栈的应用举例
 */
public class StackExamples {

    public static void main(String[] args) {
        FullArrange arrange=new FullArrange();
        arrange.test(3);
    }

    public static class FullArrange{//全排列

        /**
         * 将list中，下标为i和下标为j的两个元素交换
         * @param list
         * @param i
         * @param j
         */
        public void swap(int[] list,int i,int j){
            int tmp=list[i];
            list[i]=list[j];
            list[j]=tmp;
        }

        public void perm(int[] list,int start,int end){
            if (start==end-1){
                String tmp="";
                for (int i=0;i<end;i++)
                   tmp+=list[i]+",";
                System.out.println(tmp);
            }else {
                for (int i=start;i<end;i++){
                    swap(list,start,i);
                    perm(list,start+1,end);
                    swap(list,start,i);
                }
            }
        }

        /**
         * 测试函数，输出0——n的全排列
         * @param n
         */
        public void test(int n){
            int[] list=new int[n];
            for (int i=0;i<n;i++)
                list[i]=i;
            perm(list,0,n);
        }
    }
}
