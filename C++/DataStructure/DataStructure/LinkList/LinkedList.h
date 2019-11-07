#pragma once
/**单链表，使用示例
 *
    LinkList<std::string> link_list;
	link_list.pushBack("0");
	link_list.pushBack("1");
	link_list.pushBack("2");
	link_list.insert(0,"3");
	link_list.remove(0);
	link_list.remove(1);
	std::cout <<"元素0所在的下标为"<< link_list.indexOf("0")<<std::endl;
	for (int i=0;i<link_list.getLength();i++)
	{
		std::cout << link_list.get(i)<<",";
	}
 */
template<class T>
class LinkList
{
	struct Node//节点定义
	{
		T data;
		Node* next;
		Node(T data) {
			this->data = data;
			next = NULL;
		}
	};
private:
	Node* head;
	int length;
protected:
	/**
	* 获取某位置的节点
	*/
	Node* getNode(int position)
	{
		if (!head || position < 0 || position >= length)
			throw std::out_of_range("position is out of range");
		else
		{
			Node* curNode = head;
			int tmp = 0;
			while (tmp<position)
			{
				curNode = curNode->next;
				tmp++;
			}
			return curNode;
		}
	}
public:
	LinkList()
	{
		this->head = NULL;
		this->length = 0;
	}
	/**
	* 析构函数，此处需要释放链表
	*/
	~LinkList()
	{
		Node* curNode = head;
		while (curNode)
		{
			Node* tmp = curNode->next;
			delete curNode;
			curNode = tmp;
		}
	}

	/**
	* 获取链表的长度
	*/
	int getLength()
	{
		return length;
	}

	/**
	* 获取position位置下的数据
	*/
	T get(int position)
	{
		return getNode(position)->data;
	}

	/**
	* 在链表末尾追加元素
	*/
	void pushBack(T data)
	{
		Node* newNode = new Node(data);
		if (head == NULL)
			head = newNode;
		else {
			getNode(length - 1)->next = newNode;
		}
		this->length++;
	}

	/**
	* 在position位置增加data数据
	*/
	void insert(int position, T data)
	{
		Node* newNode = new Node(data);
		if (position == 0) {
			Node* oldFirst = head;
			head = newNode;
			head->next = oldFirst;
		}
		else {
			Node* node = getNode(position - 1);
			Node* nextNode = node->next;
			node->next = newNode;
			newNode->next = nextNode;
		}
		this->length++;
	}

	/*
	*移除链表中的position下标的元素，并返回元素值
	*/
	T remove(int position)
	{
		T data;
		if (position == 0) {
			Node* removeNode;
			removeNode = head;
			data = head->data;
			head = head->next;
			delete removeNode;
		}
		else if (position<length)
		{
			Node* node = getNode(position - 1);
			Node* positionNode = node->next;
			node->next = node->next->next;
			data = positionNode->data;
			delete positionNode;
		}
		length--;
		return data;
	}

	/**
	*找到第一个出现data的索引位置
	*/
	int indexOf(T data) {
		Node* curNode = head;
		int position = 0;
		while (curNode != NULL)
		{
			if (curNode->data == data)
				return position;
			curNode = curNode->next;
			position++;
		}
		return -1;
	}
};

