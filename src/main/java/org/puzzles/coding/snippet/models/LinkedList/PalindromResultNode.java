package org.puzzles.coding.snippet.models.LinkedList;

public class PalindromResultNode {
	public ListNode node;
	public boolean isPalindrome;
	public PalindromResultNode(ListNode node, boolean b) {
		this.setNode(node);
		isPalindrome = b;
	}
	public boolean isPalindrome() {
		return isPalindrome;
	}
	public void setPalindrome(boolean isPalindrome) {
		this.isPalindrome = isPalindrome;
	}
	public ListNode getNode() {
		return node;
	}
	public void setNode(ListNode node) {
		this.node = node;
	}
}
