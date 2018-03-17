package org.puzzles.coding.snippet.models.TreesAndGraph;

import java.util.ArrayList;

public class TreeNode {
	public int value;
	public TreeNode leftChild;
	public TreeNode rightChild;
	public TreeNode parentNode;

	public TreeNode(TreeNode leftChild, TreeNode rightChild,
			TreeNode parentNode, int value) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parentNode = parentNode;
		this.setValue(value);
	}

	public TreeNode(int value) {
		this.value = value;
	}

	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static TreeNode generateUnbalancedTree(int level) {
		int i = 0;
		int count = (int) (Math.pow(2, level)) - 1;
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		// list.add(null);
		while (i < count) {
			list.add(new TreeNode(null, null, null, i++));
		}
		for (i = 0; i < (list.size()) / 2; i++) {
			list.get(i).leftChild = list.get(2 * i + 1);
			list.get(i).rightChild = list.get(2 * i + 2);
		}

		TreeNode n1 = new TreeNode(null, null, null, count + 1);
		list.get(count - 1).leftChild = n1;
		TreeNode n2 = new TreeNode(null, null, null, count + 2);
		n1.leftChild = n2;
		TreeNode n3 = new TreeNode(null, null, null, count + 3);
		n2.rightChild = n3;
		return list.get(0);

	}

	public static TreeNode generateBalancedTree(int level) {

		int i = 0;
		int count = (int) (Math.pow(2, level)) - 1;
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		// list.add(null);
		while (i < count) {
			list.add(new TreeNode(null, null, null, i++));
		}
		for (i = 0; i < (list.size()) / 2; i++) {
			list.get(i).leftChild = list.get(2 * i + 1);
			list.get(i).rightChild = list.get(2 * i + 2);
		}
		return list.get(0);

	}

}
