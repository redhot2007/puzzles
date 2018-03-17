package org.puzzles.coding.snippet.programs;

import org.puzzles.coding.snippet.models.TreesAndGraph.HeadAndTail;
import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;
import org.puzzles.coding.snippet.models.TreesAndGraph.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class TreesAndGraphs implements ITemplate {

	public List<Integer> allPaths(TreeNode root) {
		if (root == null) {
			System.out.println("null");
			return null;
		}
		return allPathsHelper(root, 1, new Stack<TreeNode>());

	}

	private List<Integer> allPathsHelper(TreeNode root, int level, Stack<TreeNode> stack) {
		stack.push(root);
		if (root.leftChild == null && root.rightChild == null) {
			List<Integer> result = new ArrayList<Integer>();
			for (TreeNode r : stack) {
				System.out.print(r.getValue() + " ");
				result.add(r.value);
			}
			System.out.println();
			return result;
		}
		if (root.leftChild != null)
			allPathsHelper(root.leftChild, level + 1, stack);
		while (stack.size() != level)
			stack.pop();
		if (root.rightChild != null)
			allPathsHelper(root.rightChild, level + 1, stack);
		while (stack.size() != level)
			stack.pop();
		return null;
	}

	public void bstToDoublyLinkedList(TreeNode root) {
		if (root == null)
			return;
		HeadAndTail helper = bstToListHelper(root);
		TreeNode head = helper.head;
		while (head != null) {
			System.out.print(head.getValue() + ", ");
			head = head.rightChild;
		}
	}

	private void bstToLinkedListConcat(TreeNode x, TreeNode y) {
		x.rightChild = y;
		y.leftChild = x;
	}

	private HeadAndTail bstToListHelper(TreeNode root) {
		if (root == null) {
			return null;
		}
		HeadAndTail left = bstToListHelper(root.leftChild);
		HeadAndTail right = bstToListHelper(root.rightChild);
		if (left != null)
			bstToLinkedListConcat(left.tail, root);
		if (right != null)
			bstToLinkedListConcat(root, right.head);
		return new HeadAndTail(left == null ? root : left.head, right == null ? root : right.tail);

	}

	public TreeNode breadthFirstSearch(TreeNode root, int searchValue) {
		if (root == null)
			return root;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode currentNode = q.remove();
			if (currentNode.value == searchValue)
				return currentNode;
			if (currentNode.leftChild != null)
				q.add(currentNode.leftChild);
			if (currentNode.rightChild != null)
				q.add(currentNode.rightChild);
		}
		return null;

	}

	/**
	 * Given preorder and inorder traversal of a tree, construct the binary
	 * tree.
	 * 
	 * Note: You may assume that duplicates do not exist in the tree.
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTreeFromPreOrder(int[] preorder, int[] inorder) {
		int len = inorder.length;
		if (len < 1)
			return null;

		Map<Integer, Integer> inOrderMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			inOrderMap.put(inorder[i], i);
		}
		int start = 0, end = preorder.length - 1, cursor = 0;
		return buildTreeFromPreorderHelper(inOrderMap, preorder, start, end, cursor);
	}

	private TreeNode buildTreeFromPreorderHelper(Map<Integer, Integer> inOrderMap, int[] preorder, int start, int end,
			int cursor) {
		TreeNode root = new TreeNode(preorder[cursor]);
		int mid = inOrderMap.get(preorder[cursor]);
		if (start < end) {
			if (mid > start) {
				root.leftChild = buildTreeFromPreorderHelper(inOrderMap, preorder, start, mid - 1, cursor + 1);
			}
			if (mid < end) {
				root.rightChild = buildTreeFromPreorderHelper(inOrderMap, preorder, mid + 1, end,
						cursor + mid - start + 1);
			}
		}
		return root;
	}

	/**
	 * Given inorder and postorder traversal of a tree, construct the binary
	 * tree.
	 * 
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public TreeNode buildTreeFromPostorder(int[] inorder, int[] postorder) {
		int len = inorder.length;
		if (len == 0 || len != postorder.length)
			return null;
		// map inorder values to their indices
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; ++i) {
			map.put(inorder[i], i);
		}
		// build the tree
		// read postorder values backwards
		return buildTreeFromPostorderHelper(postorder, 0, len - 1, len - 1, map);
	}

	private TreeNode buildTreeFromPostorderHelper(int[] postorder, int start, int end, int cur,
			HashMap<Integer, Integer> inorder) {
		if (start > end)
			return null; // Base case: start > end, i.e. invalid index, return
							// null

		int val = postorder[cur];
		TreeNode root = new TreeNode(val);
		int mid = inorder.get(val);
		// read postorder values backwards
		root.leftChild = buildTreeFromPostorderHelper(postorder, start, mid - 1, cur - (end - mid) - 1, inorder);
		root.rightChild = buildTreeFromPostorderHelper(postorder, mid + 1, end, cur - 1, inorder);

		return root;
	}

	public TreeNode clone(TreeNode root) {
		if (root == null)
			return null;
		TreeNode cRoot = new TreeNode();
		cRoot.setValue(root.getValue());
		cRoot.leftChild = clone(root.leftChild);
		cRoot.rightChild = clone(root.rightChild);
		return cRoot;
	}

	public TreeNode depthFirstSearch(TreeNode root, int searchValue) {
		if (root == null)
			return root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode currentNode = stack.pop();
			if (currentNode.value == searchValue)
				return currentNode;
			if (currentNode.leftChild != null)
				stack.push(currentNode.leftChild);
			if (currentNode.rightChild != null)
				stack.push(currentNode.rightChild);
		}
		return null;

	}

	public TreeNode firstCommonAncestor(TreeNode root, int a, int b) {
		if (root == null)
			return null;
		if (!isNodeCovered(root, a) || !isNodeCovered(root, b))
			return null;
		return firstCommonAncestorHelper(root, a, b);
	}

	/**
	 * Time complexity 2n+2n/2+2n/4+.....===>O(n)
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	private TreeNode firstCommonAncestorHelper(TreeNode root, int a, int b) {
		// what if one of the nodes is ancestor? consider that case here
		if (root == null || root.getValue() == a || root.getValue() == b)
			return root;
		boolean a_is_on_left = isNodeCovered(root.leftChild, a);
		boolean b_is_on_left = isNodeCovered(root.leftChild, b);
		if (a_is_on_left != b_is_on_left)
			return root;
		return firstCommonAncestorHelper(a_is_on_left ? root.leftChild : root.rightChild, a, b);
	}

	/**
	 * Complexity - O(n)
	 * 
	 * @param root
	 * @param a
	 * @return
	 */
	private boolean isNodeCovered(TreeNode root, int a) {
		if (root == null)
			return false;
		if (root.getValue() == a)
			return true;
		return (isNodeCovered(root.leftChild, a) || isNodeCovered(root.rightChild, a));
	}

	public void inOrderTraversalRecursive(TreeNode root) {

		if (root == null)
			return;
		inOrderTraversalRecursive(root.leftChild);
		System.out.print(root.getValue() + " ");
		inOrderTraversalRecursive(root.rightChild);
	}

	/**
	 * 
	 * Complexity Analysis: Time - O(n) Space - O(n)
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inOrderTraversalIterative(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode runner = root;
		// find left most node
		while (runner != null) {
			stack.push(runner);
			runner = runner.leftChild;
		}

		while (!stack.isEmpty()) {
			runner = stack.pop();
			result.add(runner.value);
			runner = runner.rightChild;
			while (runner != null) {
				stack.push(runner);
				runner = runner.leftChild;
			}
		}

		return result;
	}

	/**
	 * First modify the tree to a partial Threaded Binary Tree where all right
	 * child pointers that were null in the original tree are pointed to their
	 * inorder successor. During the traversal, Morris algorithm then fix the
	 * modified right pointers and set them back to null. Complexity Analysis:
	 * Time - O(n) Space - O(1)
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inOrderTraversalMorris(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		TreeNode cur = root;

		while (cur != null) {
			if (cur.leftChild != null) {
				TreeNode pre = cur.leftChild;
				while (pre.rightChild != null && pre.rightChild != cur) {
					pre = pre.rightChild;
				}
				if (pre.rightChild == null) { // set right to successor
					pre.rightChild = cur;
					cur = cur.leftChild;
				} else { // visit and revert the change
					pre.rightChild = null;
					result.add(cur.value);
					cur = cur.rightChild;
				}
			} else { // visit and move to successor
				result.add(cur.value);
				cur = cur.rightChild;
			}
		}
		return result;
	}

	public TreeNode inOrderSuccessor(TreeNode node) {
		if (null == node)
			return null;
		TreeNode result = null;
		if (node.rightChild != null) {
			// find left most child of the right child
			result = node.rightChild;
			while (result.leftChild != null)
				result = result.leftChild;
		} else {
			// Go up until we're on left instead of right
			while (node.parentNode != null && node.parentNode.leftChild != node) {
				node = node.parentNode;
			}
			result = node.parentNode;
		}
		return result;
	}

	public boolean isBalancedBad(TreeNode root) {
		if (root == null)
			return true;
		if (Math.abs(heightOfTreeBad(root.leftChild) - heightOfTreeBad(root.rightChild)) > 1)
			return false;
		return isBalancedBad(root.leftChild) && isBalancedBad(root.rightChild);
	}

	// Bad implementation for finding balanced trees - O(NlogN)
	private int heightOfTreeBad(TreeNode root) {
		if (root == null)
			return 0;
		return (Math.max(heightOfTreeBad(root.leftChild), heightOfTreeBad(root.rightChild))) + 1;
	}

	public boolean isBalancedOpt(TreeNode root) {
		if (heightOfTreeOpt(root) == -1)
			return false;
		return true;
	}

	private int heightOfTreeOpt(TreeNode root) {
		if (root == null)
			return 0;
		int leftHeight = heightOfTreeOpt(root.leftChild);
		if (leftHeight == -1)
			return -1;
		int rightHeight = heightOfTreeOpt(root.rightChild);
		if (rightHeight == -1)
			return -1;
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		return Math.max(leftHeight, rightHeight) + 1;

	}

	/**
	 * Given two binary trees, write a function to check if they are equal or
	 * not.
	 * 
	 * Two binary trees are considered equal if they are structurally identical
	 * and the nodes have the same value.
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (null == p && null == q)
			return true;
		if (null == p || null == q || p.value != q.value)
			return false;
		return isSameTree(p.leftChild, q.leftChild) && isSameTree(p.rightChild, q.rightChild);
	}

	public boolean isSubTree(TreeNode t1, TreeNode t2) {
		if (t2 == null)
			return true;
		boolean flag = isSubTreeHelper(t1, t2);
		System.out.println(flag);
		return flag;
	}

	private boolean isSubTreeHelper(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return false;
		if (t1.getValue() == t2.getValue())
			if (isSameTree(t1, t2))
				return true;
		return isSubTreeHelper(t1.leftChild, t2) || isSubTreeHelper(t1.rightChild, t2);
	}

	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 *		     1
	 * 		    / \
	 *		   2   2
	 * 		  / \ / \
	 *		 3  4 4  3
	 * </pre>
	 * 
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		return null == root || isSymmetricHelper(root.leftChild, root.rightChild);
	}

	private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
		if (null == left && null == right)
			return true;
		if (null == left || null == right || left.value != right.value)
			return false;
		return isSymmetricHelper(left.leftChild, right.rightChild)
				&& isSymmetricHelper(left.rightChild, right.leftChild);
	}

	/**
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * Assume a BST is defined as follows:
	 * 
	 * <pre>
	 * The left subtree of a node contains only nodes with keys less than the node's key. 
	 * The right subtree of a node contains only nodes with keys greater than the node's key. 
	 * Both the left and right subtrees must also be binary search trees.
	 * </pre>
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBSTWithoutDuplicate(TreeNode root) {
		// an in-order traverse should be a sorted array
		return isValidBSTHelperWithoutDuplicate(root, null);
	}

	private boolean isValidBSTHelperWithoutDuplicate(TreeNode root, Integer previous) {
		if (root == null)
			return true;
		if (!isValidBSTHelperWithoutDuplicate(root.leftChild, previous) || (previous != null && previous >= root.value))
			return false;
		previous = root.value;
		return isValidBSTHelperWithoutDuplicate(root.rightChild, previous);
	}

	public boolean isValidBSTWithDuplicates(TreeNode root) {
		// an in-order traverse should be a sorted array
		if (root == null)
			return true;
		Integer min = null;
		Integer max = null;
		return isValidBSTWithoutDuplicatesHelper(root, min, max);
	}

	private boolean isValidBSTWithoutDuplicatesHelper(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		// note: for valid bst - left.data <= current.data < right.data
		if ((min != null && root.value <= min) || (max != null && root.value > max))
			return false;
		return isValidBSTWithoutDuplicatesHelper(root.leftChild, min, root.value)
				&& isValidBSTWithoutDuplicatesHelper(root.rightChild, root.value, max);
	}

	public int[] leftView(TreeNode root) {
		System.out.print("Left View of a binary tree ");
		int[] result = new int[heightOfTreeBad(root)];
		leftViewHelper(root, 1, 0, result);
		return result;
	}

	private int leftViewHelper(TreeNode root, int currentLevel, int maxLevelCovered, int[] result) {
		if (root != null) {
			if (currentLevel > maxLevelCovered) {
				System.out.print(root.getValue() + " ");
				result[maxLevelCovered] = root.getValue();
				maxLevelCovered++;
			}
			maxLevelCovered = leftViewHelper(root.leftChild, currentLevel + 1, maxLevelCovered, result);
			maxLevelCovered = leftViewHelper(root.rightChild, currentLevel + 1, maxLevelCovered, result);
		}
		return maxLevelCovered;

	}

	/**
	 * Convert a Binary tree to List of linkedlists where each linked list
	 * connects nodes in same level
	 * 
	 * @param root
	 * @return
	 */
	public List<LinkedList<TreeNode>> levelOrderRecursive(TreeNode root) {
		return levelOrderRecursiveHelper(new ArrayList<LinkedList<TreeNode>>(), root, 0);
	}

	private List<LinkedList<TreeNode>> levelOrderRecursiveHelper(List<LinkedList<TreeNode>> result, TreeNode root,
			int level) {
		if (result.size() == level) {
			result.add(new LinkedList<TreeNode>());
		}
		LinkedList<TreeNode> list = result.get(level);
		list.add(root);
		if (root.leftChild != null)
			levelOrderRecursiveHelper(result, root.leftChild, level + 1);
		if (root.rightChild != null)
			levelOrderRecursiveHelper(result, root.rightChild, level + 1);
		return result;
	}

	public List<List<TreeNode>> levelOrderIterative(TreeNode root) {
		if (null == root)
			return null;
		ArrayList<List<TreeNode>> result = new ArrayList<List<TreeNode>>();
		List<TreeNode> current = new LinkedList<TreeNode>();
		current.add(root);
		while (current.size() > 0) {
			result.add(current);
			LinkedList<TreeNode> next = new LinkedList<TreeNode>();
			for (TreeNode temp : current) {
				if (temp.leftChild != null)
					next.add(temp.leftChild);
				if (temp.rightChild != null)
					next.add(temp.rightChild);
			}
			current = next;
		}
		return result;

	}

	/**
	 * Given a binary tree, return the level order traversal of its nodes'
	 * values. (ie, from left to right, level by level). <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given:
	 *	   3
	 *	  / \
	 * 	 9  20
	 *	   /  \
	 * 	   5   7
	 * return 
	 * [
	 *  [3]
	 *  [9,20],
	 *  [5,7]
	 * ]
	 * </pre>
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderTraversal(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root != null) {
			q.add(root);
			q.add(null); // mark end of level
		}
		while (!q.isEmpty()) {
			TreeNode cursor = q.remove();
			List<Integer> temp = new ArrayList<Integer>();
			// iterate until end of level is reached
			while (cursor != null) {
				temp.add(cursor.value);
				if (cursor.leftChild != null)
					q.add(cursor.leftChild);
				if (cursor.rightChild != null)
					q.add(cursor.rightChild);
				cursor = q.remove();
			}
			result.add(temp);
			if (!q.isEmpty())
				q.add(null);
		}
		return result;
	}

	/**
	 * Given a binary tree, return the bottom-up level order traversal of its
	 * nodes' values. (ie, from left to right, level by level from leaf to
	 * root).
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given:
	 *	   3
	 *	  / \
	 * 	 9  20
	 *	   /  \
	 * 	   5   7
	 * return 
	 * [
	 *  [5,7],
	 *  [9,20],
	 *  [3]
	 * ]
	 * </pre>
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = levelOrderTraversal(root);
		Collections.reverse(result);
		return result;
	}

	public int maximumAmplitude(TreeNode root, int level) {
		if (root == null)
			return 0;
		return Math.max(maximumAmplitude(root.leftChild, level + 1), maximumAmplitude(root.rightChild, level + 1)) + 1;

	}

	/**
	 * print all pahts that sum to a certain value
	 * 
	 * @param root
	 * @param sum
	 */
	public void pathSum(TreeNode root, int sum) {
		int maxHeight = heightOfTreeBad(root);
		int path[] = new int[maxHeight];
		pathSumHelper(root, sum, path, 0);
	}

	private void pathSumHelper(TreeNode root, int sum, int[] path, int level) {
		if (null == root)
			return;
		path[level] = root.value;
		int tempSum = 0;
		for (int i = level; i >= 0; i--) {
			tempSum += path[i];
			if (tempSum == sum) {
				printPath(path, i, level);
			}
		}
		pathSumHelper(root.leftChild, sum, path, level + 1);
		pathSumHelper(root.rightChild, sum, path, level + 1);
		path[level] = Integer.MIN_VALUE;
	}

	private void printPath(int[] path, int start, int end) {
		for (int p = end; p >= start; p--)
			System.out.print(path[p] + " ");
		System.out.println();

	}

	public int maximumSumFromRootToLeaf(TreeNode root) {
		return maximumSumFromRootToLeafHelper(root, 0, 0);
	}

	private int maximumSumFromRootToLeafHelper(TreeNode root, int sum, int maxSum) {
		if (root == null)
			return Math.max(sum, maxSum);
		sum += root.getValue();
		return Math.max(maximumSumFromRootToLeafHelper(root.leftChild, sum, maxSum),
				maximumSumFromRootToLeafHelper(root.rightChild, sum, maxSum));
	}

	public void mirror(TreeNode root) {
		if (root == null)
			return;
		TreeNode temp;
		temp = root.rightChild;
		root.rightChild = root.leftChild;
		root.leftChild = temp;
		if (root.leftChild != null)
			mirror(root.leftChild);
		if (root.rightChild != null)
			mirror(root.rightChild);

	}

	// public int numTrees(int n) {
	// if (n == 0)
	// return 0;
	// int[] count = new int[n + 1];
	// count[0] = 1;
	// count[1] = 1;
	//
	// for (int i = 2; i <= n; ++i) {
	// for (int j = 0; j < i; ++j) {
	// count[i] += (count[j] * count[i - 1 - j]);
	// }
	// }
	//
	// return count[n];
	// }
	// HashMap<Integer,Integer> numTreeHelper = new HashMap<Integer,Integer>();

	public void postOrderTraversalRecursive(TreeNode root) {

		if (root == null)
			return;
		postOrderTraversalRecursive(root.leftChild);
		postOrderTraversalRecursive(root.rightChild);
		System.out.println(root.getValue());

	}

	public ArrayList<Integer> postOrderTraversalIterative(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;
		Stack<TreeNode> stackBack = new Stack<TreeNode>();
		Stack<Integer> stack = new Stack<Integer>();
		TreeNode cur = root;
		// find the right-most node
		while (cur != null) {
			stack.push(cur.value);
			stackBack.push(cur);
			cur = cur.rightChild;
		}
		while (!stackBack.empty()) {
			cur = stackBack.pop();
			// add the left child
			cur = cur.leftChild;
			// find the right-most node
			while (cur != null) {
				stack.push(cur.value);
				stackBack.push(cur);
				cur = cur.rightChild;
			}
		} // while-stackBack-empty
			// pop values to an array
		while (!stack.empty()) {
			result.add(stack.pop());
		}
		return result;
	}

	public String preOrderTraversal(TreeNode root) {
		if (root == null)
			return null;
		System.out.print(root.getValue());
		String ret = String.valueOf(root.getValue());
		ret = ret + preOrderTraversal(root.leftChild);
		ret = ret + preOrderTraversal(root.rightChild);
		return ret;
	}

	// Assume root is at level 1
	public void printAllNodesAtGivenLevel(TreeNode root, int inputLevel) {
		printAllNodesAtGivenLevelHelper(root, inputLevel, 1);
	}

	public void printAllNodesAtGivenLevelHelper(TreeNode root, int inputLevel, int currentLevel) {
		if (root == null || currentLevel > inputLevel)
			return;
		if (inputLevel == currentLevel)
			System.out.print(root.getValue() + " ");
		printAllNodesAtGivenLevelHelper(root.leftChild, inputLevel, currentLevel + 1);
		printAllNodesAtGivenLevelHelper(root.rightChild, inputLevel, currentLevel + 1);
	}

	/**
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * 
	 * Recover the tree without changing its structure.
	 * 
	 * Note: A solution using O(n) space is pretty straight forward. Could you
	 * devise a constant space solution?
	 * 
	 * @param root
	 */
	public void recoverTree(TreeNode root) {
		TreeNode pre = null, cur = root, n1 = null, n2 = null;
		while (cur != null) {
			if (cur.leftChild != null) {
				TreeNode p = cur.leftChild;
				while (p.rightChild != null && p.rightChild != cur) {
					p = p.rightChild;
				}
				if (p.rightChild == null) { // set right to successor
					p.rightChild = cur;
					cur = cur.leftChild;
				} else { // visit and revert the change
					p.rightChild = null;
					if (pre.value > cur.value) {
						n2 = cur;
						if (n1 == null)
							n1 = pre;
					}
					pre = cur;
					cur = cur.rightChild;
				}
			} else { // visit
				if (pre != null && pre.value > cur.value) {
					n2 = cur;
					if (n1 == null)
						n1 = pre;
				}
				pre = cur;
				cur = cur.rightChild;
			}
		}
		swap(n1, n2);
	}

	private void swap(TreeNode a, TreeNode b) {
		if (a == null || b == null)
			return;
		int tmp = a.value;
		a.value = b.value;
		b.value = tmp;
	}

	public void rightView(TreeNode root) {
		System.out.println("Right View of a binary tree");
		rightViewHelper(root, 1, 0);

	}

	private int rightViewHelper(TreeNode root, int level, int maxRightViewLevel) {
		if (root != null) {
			if (level > maxRightViewLevel) {
				System.out.println(root.getValue());
				maxRightViewLevel++;
			}
			maxRightViewLevel = rightViewHelper(root.rightChild, level + 1, maxRightViewLevel);
			maxRightViewLevel = rightViewHelper(root.leftChild, level + 1, maxRightViewLevel);
		}

		return maxRightViewLevel;
	}

	public TreeNode sortedArrayToBST(int nums[]) {
		if (null == nums)
			return null;
		return sortedArrayToBSTHelper(0, nums.length - 1, nums);

	}

	private TreeNode sortedArrayToBSTHelper(int start, int end, int a[]) {
		if (end < start || a == null)
			return null;
		TreeNode root = new TreeNode();
		int mid = (start + end) / 2;
		root.setValue(a[mid]);
		root.leftChild = sortedArrayToBSTHelper(start, mid - 1, a);
		root.rightChild = sortedArrayToBSTHelper(mid + 1, end, a);
		return root;
	}

	/**
	 * Given a binary tree, return the zigzag level order traversal of its
	 * nodes' values. (ie, from left to right, then right to left for the next
	 * level and alternate between).
	 * 
	 * <br>
	 * <b> Example:</b>
	 * 
	 * <pre>
	 *	  3
	 * 	 / \
	 * 	9  20
	 * 	   / \
	 *	  15  7
	 * 
	 * Result should be 
	 * [
	 *  [3],
	 *  [20,9],
	 *  [15,7]
	 * ]
	 * </pre>
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root != null)
			stack.push(root);
		boolean isOdd = true;
		while (!stack.isEmpty()) {
			List<Integer> currentLevel = new ArrayList<Integer>();
			Stack<TreeNode> stacktemp = new Stack<TreeNode>();
			while (!stack.isEmpty()) {
				TreeNode temp = stack.pop();
				currentLevel.add(temp.value);
				if (isOdd) {
					if (temp.leftChild != null)
						stacktemp.push(temp.leftChild);
					if (temp.rightChild != null)
						stacktemp.push(temp.rightChild);
				} else {
					if (temp.rightChild != null)
						stacktemp.push(temp.rightChild);
					if (temp.leftChild != null)
						stacktemp.push(temp.leftChild);
				}
			}
			result.add(currentLevel);
			isOdd = !isOdd;
			stack = stacktemp;
		}

		return result;
	}
}