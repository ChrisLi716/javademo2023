package com.chris.demo.linkedlist;

/**
 * @Auther Chris Lee
 * @Date 2/4/2019 15:37
 * @Description
 */
public class DoublePointLinkedList {
	
	private Node head;
	
	private Node tail;
	
	private int size;
	
	DoublePointLinkedList()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	private class Node {
		private Object data;
		
		private Node next;
		
		Node(Object data)
		{
			this.data = data;
		}
	}
	
	void addHead(Object obj) {
		Node newNode = new Node(obj);
		if (this.size != 0) {
			newNode.next = head;
			head = newNode;
		}
		else {
			head = newNode;
			tail = newNode;
		}
		size++;
	}
	
	void addTail(Object obj) {
		Node newNode = new Node(obj);
		if (this.size != 0) {
			tail.next = newNode;
			tail = newNode;
		}
		else {
			head = newNode;
			tail = newNode;
		}
		size++;
	}
	
	Object deleteHead() {
		if (size != 0) {
			Object data = head.data;
			head = head.next;
			size--;
			return data;
		}
		else {
			return null;
		}
	}
	
	Object findNode(Object obj) {
		Node currentNode = head;
		int tempSize = size;
		if (size != 0) {
			while (tempSize-- > 0) {
				if (currentNode.data.equals(obj)) {
					return currentNode.data;
				}
				else {
					currentNode = currentNode.next;
				}
			}
		}
		return null;
	}
	
	boolean delete(Object obj) {
		if (size == 0) {
			return false;
		}
		Node previousNode = head;
		Node currentNode = head;
		int tempSize = size;
		
		while (tempSize-- > 0) {
			if (currentNode.data.equals(obj)) {
				break;
			}
			else {
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
		}
		
		if (head == currentNode) {
			head = head.next;
			
		}
		else {
			previousNode.next = currentNode.next;
		}
		size--;
		
		return true;
	}
	
	boolean isEmpty() {
		return size == 0;
	}
	
	void display() {
		int tempSize = size;
		Node currentNode = head;
		if (tempSize == 1) {
			System.out.println(head.data);
		}
		else if (tempSize == 0) {
			System.out.println("[]");
		}
		else {
			while (tempSize-- > 0) {
				if (currentNode.equals(head)) {
					System.out.print(currentNode.data + "->");
				}
				else if (currentNode.next == null) {
					System.out.println(currentNode.data);
				}
				else {
					System.out.print(currentNode.data + "->");
				}
				currentNode = currentNode.next;
			}
		}
	}
	
}
