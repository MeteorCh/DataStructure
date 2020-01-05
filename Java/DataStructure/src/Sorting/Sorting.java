package Sorting;

public class Sorting {
    /**
     * 交换data中i和j下标的两个元素
     * @param data
     * @param i
     * @param j
     */
    protected static void swap(int[] data,int i,int j){
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }

    /**
     * 冒泡排序
     * @param data
     */
    public static void bubbleSort(int[] data){
        boolean flag=true;
        for (int i=0;i<data.length&&flag;++i){
            flag=false;
            for (int j=data.length-1;j>i;j--){
                if (data[j]<data[j-1])
                {
                    swap(data,j,j-1);
                    flag=true;
                }
            }
        }
    }

    /**
     * 选择排序
     * @param data
     */
    public static void selectSort(int[] data){
        for (int i=0;i<data.length;++i){
            int min=i;
            for (int j=i+1;j<data.length;++j){
                if (data[min]>data[j])
                    min=j;
            }
            if (min!=i)
                swap(data,i,min);
        }
    }

    /**
     * 插入排序
     * @param data
     */
    public static void insertSort(int[] data){
        int i,j,k;
        for (i=1;i<data.length;++i){//0——i-1之间的数据是有序的
            for (j=i-1;j>=0;j--) {//找到data[i]的插入位置：j+1
                if (data[j]<data[i])
                    break;
            }
            //将data[i]插入到有序部分
            if (j!=i-1){//如果j==i-1，说明是加了data[i]后，仍然是有序的
                int tmp=data[i];
                for (k=i-1;k>j;k--)//j+1——i-1之间的数据整体后移
                    data[k+1]=data[k];
                data[k+1]=tmp;//此时k=j
            }
        }
    }

    /**
     * 希尔排序
     * @param data
     */
    public static void shellSort(int[] data){
        int step=data.length;
        int i,j,k;
        do {
            step/=2;
            for (i=step;i<data.length;i++){//i为0+step，第一个元素为data[0]，组与组之间是轮询的
                if (data[i]<data[i-step]){
                    k=data[i];
                    for (j=i-step;j>=0&&k<data[j];j-=step){//j
                        data[j+step]=data[j];
                    }
                    data[j+step]=k;
                }
            }
        }while (step>0);
    }

    /**
     * 归并排序对外接口
     * @param data
     */
    public static void mergeSort(int[] data){
        mergeSort(data,0,data.length-1);
    }

    protected static void mergeSort(int[] data,int left,int right){
        if (left<right){
            int center=(left+right)/2;
            mergeSort(data,left,center);
            mergeSort(data,center+1,right);
            merge(data,left,center,right);
        }
    }

    protected static void merge(int[] data,int left,int center,int right){
        int i=left,j=center+1,pointer=0;
        int[] tmp=new int[right-left+1];
        while (i<=center&&j<=right)
            tmp[pointer++]=data[i]<data[j]?data[i++]:data[j++];
        while (i<=center)
            tmp[pointer++]=data[i++];
        while (j<=right)
            tmp[pointer++]=data[j++];
        //将最终排序的结果赋值给原数组
        for (i=0;i<tmp.length;++i)
            data[left+i]=tmp[i];
    }

    /**
     * 堆的上浮操作
     * @param data 堆数据
     * @param insertIndex 要插入的元素
     */
    protected static void floatUp(int[] data,int insertIndex){
        int parent=insertIndex/2;
        if (parent>=0){
            if (data[parent]<data[insertIndex]){
                swap(data,parent,insertIndex);
                floatUp(data,parent);
            }
        }
    }

    /**
     * 堆的下沉操作
     * @param data
     * @param deleteIndex
     */
    protected static void sink(int[] data,int deleteIndex,int length){
        int lChild=deleteIndex*2+1;
        int rChild=lChild+1;
        int max=data[deleteIndex];
        int maxIndex=deleteIndex;
        if (lChild<length&&data[lChild]>max) {
            max=data[lChild];
            maxIndex=lChild;
        }
        if (rChild<length&&data[rChild]>max)
            maxIndex=rChild;
        if (maxIndex<length&&maxIndex!=deleteIndex){
            swap(data,maxIndex,deleteIndex);
            sink(data,maxIndex,length);
        }
    }

    /**
     * 堆排序
     * @param data
     */
    public static void heapSort(int[] data){
        //先建堆tmp
        int[] tmp=new int[data.length];
        tmp[0]=data[0];
        for (int i=1;i<data.length;++i){
            tmp[i]=data[i];
            floatUp(tmp,i);
        }
        //不断删除根节点，并将删除后的节点放入到数组的最后
        int lastIndex=data.length-1;
        for (int i=0;i<data.length;++i){
            //首先将第0个和第length-1-i个数据交换
            swap(tmp,0,lastIndex-i);
            //然后对第一个元素下沉
            sink(tmp,0,lastIndex-i);
        }
        //将tmp直接拷贝给data，让data有序
        System.arraycopy(tmp,0,data,0,data.length);
    }

    /**
     * 快速排序的对外接口
     * @param data
     */
    public static void quickSort(int[] data){//快速排序
        quickSort(data,0,data.length-1);
    }

    /**
     * 快速排序的实现
     * @param data
     * @param left
     * @param right
     */
    protected static void quickSort(int[] data,int left,int right){
        if (left<right){
            int axis=data[left];//以最左边的数作为轴
            int i=left,j=right;
            while (i<j){
                //从最右边开始，找到比axis大的数
                while (i<j){
                    if (data[j]<axis)
                        break;
                    j--;
                }
                while (i<j){
                    if (data[i]>axis)
                        break;
                    i++;
                }
                swap(data,i,j);
            }
            swap(data,i,left);
            //递归对左右分而治之
            quickSort(data,left,i-1);
            quickSort(data,i+1,right);
        }
    }
}
