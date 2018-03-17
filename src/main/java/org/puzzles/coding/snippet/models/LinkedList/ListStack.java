package org.puzzles.coding.snippet.models.LinkedList;


public class ListStack {
	public ListStack() {
		head = null;
	}
	
	public ListNode head;

	public ListNode push(int number) {
		ListNode tempNode = new ListNode(number);
		if (head == null)
			head = tempNode;
		else {
			tempNode.next = head;
			head = tempNode;
		}
		return head;
	}

	public int pop() {
		int value = -1;
		try {
			value = head.val;
			head = head.next;
		} catch (NullPointerException e) {
			System.out.println("Stack is empty");
			return Integer.MIN_VALUE;
		}
		return value;
	}

	public ListNode createLinkedList(int size) {

		
		for (int i = 0; i < size; i++){
			push(i);
				
		}

		return head;

	}
	
	public ListNode createLinkedList(int[] values){
		for (int i = 0; i < values.length; i++){
			push(values[i]);		
		}
		return head;
	}

	public void printStack() {
		printStack(this.head);
	}

	public static void printStack(ListNode head) {
		ListNode temp = head;
		if(temp == null)
			System.out.print(temp);
		while (temp != null) {
			System.out.print(temp.val+" ");
			temp = temp.next;
		}
		System.out.println();
	}
}