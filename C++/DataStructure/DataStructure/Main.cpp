// DataStructure.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <stdexcept>
#include "BinaryTree/LinkBinaryTree.h"
using namespace std;

int main()
{
	string strDatas[] = { "A","B","D","#","#","E","#","#","C","#","F","#","#" };
	LinkQueue<string> *datas=new LinkQueue<string>(strDatas,13);
	LinkBinaryTree<string> binaryTree;
	binaryTree.createTestStrTree(datas);
	delete datas;
	//binaryTree.createInputStrTree();
	cout << "前序遍历结果为：";
	binaryTree.print(0);
	cout << endl;
	cout << "中序遍历结果为：";
	binaryTree.print(1);
	cout << endl;
	cout << "后序遍历结果为：";
	binaryTree.print(2);
	cout << endl;
	cout << "层次遍历结果为：";
	binaryTree.print(3);
	cout << endl;
	cout << "树的高度为："<<binaryTree.getHeight()<<endl;
	cout << "树中节点的个数为：" << binaryTree.getNodeSize() << endl;
    return 0;
}

