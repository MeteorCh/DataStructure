package Tree;

/**
 * 哈夫曼树中的数据的基类
 */
public class HuffBaseType{
    protected String id;//节点ID
    protected int weight;//权重

    public HuffBaseType(String data,int weight){
        this.id =data;
        this.weight=weight;
    }

    /**
     * 用于进行同类两个对象的比较
     * @param type
     * @return 返回结果为1表示this大于type，返回结果为0表示相等，-1表示this小于type
     */
    public int compare(HuffBaseType type){
        if (this.weight>type.weight)
            return 1;
        else if (this.id ==type.id)
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "id:"+id+"|weight:"+weight;
    }
}
