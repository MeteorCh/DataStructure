package Searching;

import java.util.LinkedList;

/**
 * 有序表查找
 */
public class OrderListSearching {

    /**
     * 二分查找
     * @param data 有序表
     * @param key 待查找的键值
     * @return 查找到的下标，找不到返回-1
     */
    public static int binarySearch(int[] data,int key){
        int low=0,high=data.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (data[mid]>key)//前半部分
                high=mid-1;
            else if (data[mid]<key)
                low=mid+1;
            else
                return mid;
        }
        return -1;
    }

    /**
     * 插值查找
     * @param data 有序表
     * @param key 待查找的键值
     * @return 查找到的下标，找不到返回-1
     */
    public static int interpolationSearch(int[] data,int key){
        int low=0,high=data.length-1;
        while (low<=high){
            int mid=low+(key-data[low])/(data[high]-data[low])*(high-low);
            if (data[mid]>key)//前半部分
                high=mid-1;
            else if (data[mid]<key)
                low=mid+1;
            else
                return mid;
        }
        return -1;
    }

    /**
     * 斐波拉契查找
     * @param data 有序表
     * @param key 待查找的键值
     * @return 查找到的下标，找不到返回-1
     */
    public static int fbSearch(int[] data,int key){
        int dataSize=data.length;
        //构造斐波那契数列
        LinkedList<Integer> fb=new LinkedList<>();//存储斐波拉契数列
        fb.add(0);
        fb.add(1);
        while (fb.getLast()-1<dataSize)
            fb.add(fb.getLast()+fb.get(fb.size()-2));
        //将data中的元素补足
        int newDataSize=fb.getLast()-1;
        int[] newData=new int[newDataSize];
        System.arraycopy(data,0,newData,0,dataSize);//将data中数据赋值到newData中
        for (int i=dataSize;i<newDataSize;i++)
            newData[i]=data[dataSize-1];
        //开始斐波那契查找
        int low=0,high=dataSize;
        int k=fb.size()-1;
        while (low<=high){
            int mid=low+fb.get(k-1)-1;
            if (key<newData[mid]){//左分割
                high=mid-1;
                k=k-1;
            }else if (key>newData[mid]){//右分割
                low=mid+1;
                k=k-2;
            }else {
                if (mid<=dataSize)//查找位置不在补齐位置
                    return mid;
                else//查找位置在补齐位置
                    return dataSize-1;
            }
        }
        return -1;
    }
}
