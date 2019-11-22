#pragma once
#include <vector>
#include <string>
#include <iostream>
#include "../StackAndQueue/LinkQueue.h"
#include <queue>

template<class T>
class LinkBinaryTree
{
	struct Node
	{
		T data;
		Node* lChild;
		Node* rChild;
		Node(T data)
		{
			this->data = data;
			lChild = rChild = NULL;
		}
	};
protected:
	Node* root;
	Node* createStrNode(LinkQueue<std::string>* datas,bool isInput = false)
	{
		std::string inputStr;
		if (isInput)
		{
			std::cout << "请输入数据（#结束）:";
			std::getline(std::cin, inputStr);
		}
		else
		{
			inputStr = datas->deQueue();
		}
		Node *node;
		if (inputStr == "#")
			return NULL;
		else
		{
			node = new Node(inputStr);
			node->lChild = createStrNode(datas,isInput);
			node->rChild = createStrNode(datas,isInput);
			return node;
		}
	}

	void preTraverse(Node* node)//前序遍历
	{
		if (node)
		{
			std::cout << node->data << ",";
			preTraverse(node->lChild);
			preTraverse(node->rChild);
		}
	}

	void midTraverse(Node* node)//中序遍历
	{
		if (node)
		{
			midTraverse(node->lChild);
			std::cout << node->data << ",";
			midTraverse(node->rChild);
		}
	}

	void postTraverse(Node* node)//后续遍历
	{
		if (node)
		{
			postTraverse(node->lChild);
			postTraverse(node->rChild);
			std::cout << node->data << ",";
		}
	}

	void levelTraverse()//层次遍历
	{
		LinkQueue<Node*>* queue1=new LinkQueue<Node*>();
		LinkQueue<Node*>* queue2 = new LinkQueue<Node*>();
		if (root)
		{
			queue1->enQueue(root);
			while (!queue1->isEmpty())
			{
				do
				{
					Node* node = queue1->deQueue();
					std::cout << node->data<<",";
					if (node->lChild)
						queue2->enQueue(node->lChild);
					if (node->rChild)
						queue2->enQueue(node->rChild);
				}
				while (!queue1->isEmpty());
				//交换queue1和queue2
				LinkQueue<Node*>* tmp = queue1;
				queue1 = queue2;
				queue2 = tmp;
			}
		}
		delete queue1, queue2;
	}

	void releaseTree(Node* node)//通过后续遍历逐步删除树
	{
		if (node)
		{
			releaseTree(node->lChild);
			releaseTree(node->rChild);
			delete node;
		}
	}
public:
	~LinkBinaryTree()//析构函数，删除二叉树释放空间
	{
		releaseTree(root);
	}
	void createTestStrTree(LinkQueue<std::string>* datas)//通过字符来创建
	{
		this->root=createStrNode(datas);
	}

	void createInputStrTree()//通过用户输入来创建树
	{
		this->root=createStrNode(NULL,true);
	}

	void print(int choice)
	{
		switch (choice) {
		case 0:
			preTraverse(root);
			break;
		case 1:
			midTraverse(root);
			break;
		case 2:
			postTraverse(root);
			break;
		case 3:
			levelTraverse();
			break;
		default:
			std::cout << "输入参数有误";
		}
	}
};

