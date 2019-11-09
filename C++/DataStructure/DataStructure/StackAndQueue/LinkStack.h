#pragma once
#include "../LinkList/LinkedList.h"
template<class T>
class LinkStack:LinkList<T>
{
public:
	/*
	 * 重写插入方法，使用头插法
	 */
	void pushBack(T data)
	{
		insert(0, data);
	}

	T pop()
	{
		if(this->head)
		{
			return remove(0);
		}
		return NULL;
	}
};

