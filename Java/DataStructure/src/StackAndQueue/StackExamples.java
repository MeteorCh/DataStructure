package StackAndQueue;


import java.util.ArrayList;
import java.util.List;

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

    public static class CalculateMathExpression{//四则运算表达式求解

        /**
         * 将输入的中缀表达式转换成后缀表达式
         * @param inString 中缀表达式的字符串
         * @return 分离得到的后缀表达式
         */
        public static List<String> inTransPost(String inString) throws Exception {
            ArrayList<String> result=new ArrayList<>();
            LinkStack<String> stack=new LinkStack<>();
            for (int i=0;i<inString.length();i++){
                char item=inString.charAt(i);
                if (item>='0'&&item<='9'){//如果是数字加入到输出队列
                    //此处进行两位数的处理
                    String tmp=String.valueOf(item);
                    int j=i+1;
                    while (j<inString.length()){
                        char item2=inString.charAt(j);
                        if (item2>='0'&&item2<='9')
                        {
                            tmp+=String.valueOf(item2);
                            j++;
                        }
                        else
                            break;
                    }
                    result.add(tmp);
                    if (j!=i+1)//数字是一位数
                        i=j-1;
                    continue;
                }
                else if (item=='(')
                {
                    stack.pushBack(String.valueOf('('));
                    continue;
                }
                else if (item=='+'||item=='-'){
                    while (!stack.isEmpty()&&!stack.peek().equals("("))
                        result.add(stack.pop());
                    stack.pushBack(String.valueOf(item));
                    continue;
                }
                else if (item=='*'||item=='/'){
                    while (!stack.isEmpty()&&(stack.peek().equals("*")||stack.peek().equals("/")))
                        result.add(stack.pop());
                    stack.pushBack(String.valueOf(item));
                    continue;
                }
                else if (item==')'){
                    while (!stack.isEmpty()&&!stack.peek().equals("("))
                        result.add(stack.pop());
                    stack.pop();
                    continue;
                }else
                    throw new Exception("遇到未知操作符");
            }
            while (!stack.isEmpty())
                result.add(stack.pop());
            return result;
        }

        /**
         * 计算中缀表达式的值
         * @param inString
         * @return
         */
        public static float calcExpressionValue(String inString){
            List<String> postStr=null;
            try {
                postStr=inTransPost(inString);
            }catch (Exception e){
                System.out.println("输入数据中有未知字符！");
                return Float.NaN;
            }
            if (postStr!=null){
                String outStr="输入的中缀表达式转换成后缀表达式为：";
                LinkStack<Float> stack=new LinkStack<>();
                for (String str:postStr)
                {
                    outStr+=str+" ";
                    try {
                        if (str.equals("+")||str.equals("-")||str.equals("*")||str.equals("/")){//如果遇到操作符，则弹出两个操作数，将计算结果压栈
                            Float val2=stack.pop();
                            Float val1=stack.pop();
                            float result=operate(val1,val2,str);
                            stack.pushBack(result);

                        }else {//如果遇到数字直接压栈
                            Float val=Float.valueOf(str);
                            stack.pushBack(val);
                        }
                    }
                    catch (NumberFormatException e){//捕获字符串转数字时出现异常
                        System.out.println("字符串转换成数字出错！");
                        return Float.NaN;
                    }
                }
                System.out.println(outStr);
                return stack.pop();
            }
            return Float.NaN;
        }

        /**
         * 根据操作符计算两个数的值
         * @param val1 第一个操作数
         * @param val2 第二个操作数
         * @param operator 操作符，+ - * /中的一个
         * @return
         */
        private static float operate(Float val1,Float val2,String operator){
            if (operator.equals("+"))
                return val1+val2;
            else if (operator.equals("-"))
                return val1-val2;
            else if (operator.equals("*"))
                return val1*val2;
            else
                return (float) (val1*1.0/val2);
        }
    }
}
