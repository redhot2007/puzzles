package org.puzzles.coding.snippet.models.LinkedList;


public class ListNode {
	
	public ListNode(){
		
	}
	public ListNode(int value) {
		this.val = value;
		this.next = null;
	}

	public int val;
	public int getValue() {
		return val;
	}
	public ListNode getNext() {
		return next;
	}

	public ListNode next;
}