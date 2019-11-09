// DataStructure.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <stdexcept>
#include <string>
#include <iostream>
#include "LinkList/LinkedList.h"
#include "StackAndQueue/LinkStack.h"
using namespace std;

int main()
{
	LinkStack<string> link_stack;
	link_stack.pushBack("0");
	link_stack.pushBack("1");
	link_stack.pushBack("2");
	cout << link_stack.pop();
	cout << link_stack.pop();
	cout << link_stack.pop();
    return 0;
}

