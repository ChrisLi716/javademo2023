package com.chris.demo.linkedlist;

/**
 * @Auther Chris Lee
 * @Date 2/4/2019 12:19
 * @Description
 */
public class MainTest {
	public static void main(String[] args) {
		doublePointLinkedList();
	}
	
	private static void singleLinkedListTest() {
		SingleLinkedList singleList = new SingleLinkedList();
		singleList.addHead("A");
		singleList.addHead("B");
		singleList.addHead("C");
		singleList.addHead("D");
		
		// 打印当前链表信息
		singleList.display();
		
		// 删除C
		singleList.delete("C");
		singleList.display();
		
		// 查找B
		System.out.println(singleList.findNode("B"));
	}
	
	private static void doublePointLinkedList() {
		DoublePointLinkedList doublePointLinkedList = new DoublePointLinkedList();
        // 在最后添加数据
        doublePointLinkedList.addTail("0");

        // 打印当前链表信息
        doublePointLinkedList.display();
		doublePointLinkedList.addHead("A");
		doublePointLinkedList.addHead("B");
		doublePointLinkedList.addHead("C");
		doublePointLinkedList.addHead("D");
		
		// 打印当前链表信息
		doublePointLinkedList.display();
		

		
		// 删除C
		doublePointLinkedList.delete("C");
		doublePointLinkedList.display();
		
		// 查找B
		System.out.println(doublePointLinkedList.findNode("B"));
	}
}
