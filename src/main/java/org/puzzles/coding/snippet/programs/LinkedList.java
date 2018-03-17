package org.puzzles.coding.snippet.programs;

import org.puzzles.coding.snippet.models.LinkedList.ListNode;
import org.puzzles.coding.snippet.models.LinkedList.PalindromResultNode;
import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;
import org.puzzles.coding.snippet.models.TreesAndGraph.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;


public class LinkedList implements ITemplate {

	/**
	 * Number are represented in reverse order as a linked list. Add the numbers
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 *  1->2->3
	 * +9->9->9
	 * =0->2->3->1
	 * </pre>
	 * 
	 * @param head1
	 * @param head2
	 * @return
	 */
	public ListNode addNumbers(ListNode head1, ListNode head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		ListNode fakeHead = new ListNode();
		ListNode runner = fakeHead;
		int carry = 0;
		int sum;
		while (head1 != null || head2 != null || carry != 0) {
			int value1 = head1 == null ? 0 : head1.val;
			int value2 = head2 == null ? 0 : head2.val;
			sum = value1 + value2 + carry;
			carry = sum / 10;
			runner.next = new ListNode(sum % 10);
			runner = runner.next;
			head1 = head1 == null ? null : head1.next;
			head2 = head2 == null ? null : head2.next;
		}

		return fakeHead.next;
	}

	public boolean isPalindromeIterative(ListNode head) {
		ListNode fastPointer = head, slowPointer = head;
		Stack<Integer> stack = new Stack<Integer>();
		while (fastPointer != null && fastPointer.next != null) {
			stack.push(slowPointer.val);
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		if (fastPointer != null)
			slowPointer = slowPointer.next;
		while (slowPointer != null) {
			if (stack.pop() != slowPointer.val)
				return false;
			slowPointer = slowPointer.next;
		}
		return true;

	}

	public boolean isPalindromeRecursive(ListNode head) {
		if (null == head || null == head.next)
			return true;
		ListNode clone = head;
		int length = 0;
		while (clone != null) {
			clone = clone.next;
			length++;
		}
		return isPalindromRecursiveHelper(head, length).isPalindrome();
	}

	private PalindromResultNode isPalindromRecursiveHelper(ListNode head,
														   int length) {
		if (length == 1)
			return new PalindromResultNode(head.next, true);
		if (length == 2)
			return new PalindromResultNode(head.next.next,
					head.val == head.next.val);
		PalindromResultNode result = isPalindromRecursiveHelper(head.next,
				length - 2);
		if (!result.isPalindrome())
			return result;
		result.isPalindrome = head.val == result.node.val;
		result.node = result.node.next;
		return result;
	}

	/**
	 * Given two connected singly-linked lists (giving a T-shaped structure),
	 * find the node where they connect.
	 * 
	 * @param head1
	 * @param head2
	 * @return
	 */
	public ListNode findJunctionInTLists(ListNode head1, ListNode head2) {
		ListNode temp = head1;
		while(temp.next!=null)
			temp = temp.next;
		temp.next = head1;
		return findLoopInLinkedList(head2);
	}

	/*
	 * Write a program to find the node at which the intersection of two singly
	 * linked lists begins.
	 * 
	 * 
	 * For example, the following two linked lists:
	 * 
	 * <pre> A: a1 → a2 
	 * 					↘ c1 → c2 → c3 
	 * B: b1 → b2 → b3 ↗ 
	 * 
	 * <pre> begin to intersect at node c1.
	 * 
	 * 
	 * Notes:
	 * 
	 * If the two linked lists have no intersection at all, return null. The
	 * linked lists must retain their original structure after the function
	 * returns. You may assume there are no cycles anywhere in the entire linked
	 * structure. Your code should preferably run in O(n) time and use only O(1)
	 * memory.
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		ListNode a = headA, b = headB;
		int l1 = getLength(headA), l2 = getLength(headB);
		a = headA;
		b = headB;
		if (l1 > l2) {
			a = adjustLongerList(l1 - l2, a);
		}
		if (l2 > l1) {
			b = adjustLongerList(l2 - l1, b);
		}
		while (a != b) {
			a = a.next;
			b = b.next;
		}
		return a;
	}

	int getLength(ListNode a) {
		int l = 0;
		while (a != null) {
			a = a.next;
			l++;
		}
		return l;
	}

	ListNode adjustLongerList(int d, ListNode a) {
		while (d-- > 0) {
			a = a.next;
		}
		return a;
	}
	public ListNode findKthNode(ListNode head, int k) {
		if (head == null)
			return null;
		ListNode slow, fast;
		slow = head;
		fast = head;
		int count = 0;
		while (fast != null && count < k) {
			fast = fast.next;
			count++;
		}
		if (count != k)
			return null;
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow;
	}

	public ListNode findLoopInLinkedList(ListNode head) {
		if (head == null || head.next == null)
			return null;

		ListNode fastPointer = head, slowPointer = head;
		while (fastPointer != null && fastPointer.next != null) {
			fastPointer = fastPointer.next.next;
			slowPointer = slowPointer.next;
			if (fastPointer == slowPointer)
				break;
		}
		if (fastPointer != slowPointer)
			return null;
		slowPointer = head;
		while (fastPointer != slowPointer) {
			fastPointer = fastPointer.next;
			slowPointer = slowPointer.next;
		}
		return fastPointer;
	}

	/**
	 * Return middle element of the linked list if the length is odd, else
	 * return null<br>
	 * 
	 * <br>
	 * Examples: <blockquote>
	 * 
	 * <pre>
	 * 1->2->3 ... return 2
	 * 1->2->3->4 ..  return null
	 * null .. return null
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param head
	 *            of the linked list
	 * @return middle element of the reversed linked list
	 */
	public ListNode findMidElemOfLinkedList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode slowPointer = head, fastPointer = head;
		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		if (fastPointer == null)
			return null;
		return slowPointer;

	}

	/**
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode fakeHead = new ListNode(1);
		ListNode runner = fakeHead;
		ListNode head1 = l1;
		ListNode head2 = l2;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				runner.next = head1;
				head1 = head1.next;
			} else {
				runner.next = head2;
				head2 = head2.next;
			}
			runner = runner.next;
			// runner.next = null;
		}
		// If the reminder of the any of the list is not empty,
		// just add the head to the runner. DOnt use while loop coz, rest is
		// alrady in order
		if (head1 != null || head2 != null) {
			runner.next = head1 != null? head1:head2;
		}
		return fakeHead.next;
	}

	/**
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity. <br>
	 * With priority q, the run time is O(log k)*n,k lists and n total elements
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;
		// PriorityQueue is implemented using priority heap.
		// Add and remove complexities are O(log n) and O(n) respectively
		// throws illegalArgumentException if initialized to size 0. So add null and 0 check 
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {
					public int compare(ListNode o1, ListNode o2) {
						return o1.val - o2.val;
					}
				});
		// add all heads to q
		for (ListNode node : lists) {
			if (node != null)
				q.add(node);
		}
		ListNode fakeHead = new ListNode(1);
		ListNode runner = fakeHead;
		// until q is empty, poll and remove the head and add it to the combined
		// linkedlist
		while (!q.isEmpty()) {
			ListNode temp = q.poll();
			runner.next = temp;
			// if the added node contains a next elem, add it to q
			if (temp.next != null)
				q.add(temp.next);
			runner = runner.next;
		}
		return fakeHead.next;
	}

	public void printDuplicates(ListNode head) {
		HashMap<Integer, Boolean> hash = new HashMap<Integer, Boolean>();
		ListNode temp = head;
		System.out.println("Duplicates");
		while (temp != null) {
			if (hash.containsKey(temp.val)) {
				if (!hash.get(temp.val)) { // checkif already printed the
											// duplicate
					System.out.print(temp.val + " ");
					hash.put(temp.val, true);
				}
			} else
				hash.put(temp.val, false);
			temp = temp.next;
		}

	}

	public ListNode removeDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		HashSet<Integer> hash = new HashSet<Integer>();
		ListNode fakeHead = new ListNode();
		ListNode runner = fakeHead;
		while (head != null) {
			if (!hash.contains(head.val)) {
				hash.add(head.val);
				runner.next = head;
				runner = runner.next;
			}
			head = head.next;
		}
		// make sure to remove the last duplicates
		runner.next = null;
		return fakeHead.next;
	}

	/**
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once. <b><br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * Given 1->1->2, return 1->2.
	 * Given 1->1->2->3->3, return 1->2->3.
	 * </pre>
	 * 
	 * @param head
	 * @return
	 */
	public ListNode removeDuplicatesFromSortedList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fakeHead = new ListNode();
		ListNode runner = fakeHead;
		while (head != null) {
			if (head.val != runner.val) {
				runner.next = head;
				runner = runner.next;
			}
			head = head.next;
		}
		// make sure to remove the last duplicates
		runner.next = null;
		return fakeHead.next;
	}

	/**
	 * incomplete
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k == 1)
			return head;
		ListNode fakeHead = new ListNode(1);
		ListNode prev = fakeHead;
		fakeHead.next = head;
		int count = 0;
		while (head != null) {
			count++;
			if (count % k == 0) {
				prev = reverse(prev, head.next, count, k, fakeHead);
				head = prev.next;
			} else {
				head = head.next;
			}
		}

		return fakeHead.next;
	}

	private ListNode reverse(ListNode prev, ListNode next, int count, int k,
			ListNode fakeHead) {
		ListNode temp = prev.next;
		ListNode one = null;
		ListNode two = prev.next;
		ListNode three = null;

		while (true) {
			three = two.next;
			two.next = one;
			one = two;
			if (three == next)
				break;
			two = three;
		}

		temp.next = two;
		if (count == k)
			fakeHead.next = two;
		return two;

	}

	/**
	 * Given a linked list, remove the nth node from the end of list and return
	 * its head. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Input: 1->2->3->4->5, and n = 2.
	 * Ouptut:  1->2->3->5
	 * </pre>
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n <= 0)
			return head;
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode slow = fakeHead;
		ListNode fast = head;
		// Create a distance such that when fast is at last pos, slow.next is
		// the target that is to be removed
		while (n-- > 0)
			fast = fast.next;
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return fakeHead.next;
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given 1->2->3->3->4->4->5, return 1->2->5.
	 * Given 1->1->1->2->3, return 2->3.
	 * </pre>
	 * 
	 * @param head
	 * @return
	 */
	public ListNode returnUniqueElementsFromSortedList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fakeHead = new ListNode(0);
		ListNode runner = fakeHead;
		ListNode prev = null;
		ListNode next = head.next;
		// if the current value is not equal to previous and next, then its uniq
		while (head != null) {
			next = head.next;
			if ((prev == null || prev.val != head.val)
					&& (next == null || next.val != head.val)) {
				runner.next = head;
				runner = runner.next;
			}
			prev = head;
			head = head.next;
		}
		runner.next = null;
		return fakeHead.next;
	}

	/**
	 * Reverse a linked list from position m to n. Do it in-place and in
	 * one-pass. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4, 
	 * return 1->4->3->2->5->NULL. 
	 * Note: Given m, n satisfy the following condition: 
	 * 1 <= m <= n <= length of list.
	 * </pre>
	 * 
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null || m == n) return head;
        ListNode copyHead = head;
        ListNode prev = null, first = null,last = null, next = null;
        for(int i=1;i<=n;i++){
            if( i == m)
                first = head;
            if(i == n){
                last = head;
                next = last.next;
                last.next = null;
            }
            if(i == m-1)
                prev = head;
            head = head.next;
        }
        if(prev!=null)prev.next = null;
        ListNode reverseHead = reverseIteratively(first);
        if(prev!=null)prev.next = reverseHead;
        first.next=next;
        return m!=1? copyHead:reverseHead;
	}

	/**
	 * Reverse a given linked list using iteration technique<br>
	 * <br>
	 * Examples: <blockquote>
	 * 
	 * <pre>
	 * 1 .. return 1
	 * 1->2->3 ... return 3->2->1
	 * 1->2->3->4 ..  return 4->3->2->1
	 * null .. return null
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param head
	 *            of the linked list
	 * @return head of the reverse linked list
	 */
	public ListNode reverseIteratively(ListNode head) {
		ListNode prev = null, next = null;
		while (head != null) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		
		return prev;
	}

	/**
	 * Reverse a given linked list using recursion technique<br>
	 * <br>
	 * Examples: <blockquote>
	 * 
	 * <pre>
	 * 1 .. return 1
	 * 1->2->3 ... return 3->2->1
	 * 1->2->3->4 ..  return 4->3->2->1
	 * null .. return null
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param head
	 *            of the linked list
	 * @return head of the reverse linked list
	 */
	public ListNode reverseRecursively(ListNode head) {

		if (head == null || head.next == null)
			return head;
		ListNode secondElement = head.next;
		head.next = null;
		ListNode result = reverseRecursively(secondElement);
		secondElement.next = head;
		return result;
	}

	public void reversePrintLinkedListIterative(ListNode head) {
		Stack<ListNode> s = new Stack<ListNode>();
		while (head != null) {
			s.push(head);
			head = head.next;
		}
		System.out.println("Data in Reversed Linked List: ");
		while (!s.isEmpty())
			System.out.print(s.pop().val + " ");

	}

	public void reversePrintLinkedListRecursive(ListNode head) {
		if (head == null) {
			System.out.println("Data in Reversed Linked List: empty");
			return;
		}
		if (head.next != null)
			reversePrintLinkedListRecursive(head.next);
		else
			System.out.println("Data in Reversed Linked List: ");
		System.out.print(head.val + " ");

	}

	/**
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given 1->2->3->4->5->NULL and k = 2,
	 * return 4->5->1->2->3->NULL.
	 * </pre>
	 * 
	 * @param head
	 * @param rotations
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int rotations) {
		// Other method is to use fast and slow pointers and form a circular
		// linkedlist
		if (head == null || head.next == null)
			return head;
		ListNode tail = head;
		int length = 0;
		while (tail.next != null) {
			tail = tail.next;
			length++;
		}
		length++;
		rotations = rotations % length;
		// Rotating clockwise requires the prev node to be tracked.
		// SO change the rotation to anticlockwise and adjust k
		rotations = length - rotations;
		while (rotations > 0) {
			tail.next = head;
			head = head.next;
			tail = tail.next;
			tail.next = null;
			rotations--;
		}
		return head;
	}

	/**
	 * Given a linked list and a value x, partition it such that all nodes less
	 * than x come before nodes greater than or equal to x. You should preserve
	 * the original relative order of the nodes in each of the two partitions.
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given 1->4->3->2->5->2 and x = 3
	 * return 1->2->2->4->3->5.
	 * </pre>
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode fakeHead1 = new ListNode(0);
		ListNode fakeHead2 = new ListNode(0);
		ListNode runner1 = fakeHead1;
		ListNode runner2 = fakeHead2;
		while (head != null) {
			if (head.val < x) {
				runner1.next = head;
				runner1 = runner1.next;
			} else {
				runner2.next = head;
				runner2 = runner2.next;
			}
			head = head.next;
		}
		runner1.next = fakeHead2.next;
		runner2.next = null;
		return fakeHead1.next;
	}

	public ListNode sortLinkedList(ListNode head) {
		for (ListNode temp = head; temp != null; temp = temp.next)
			for (ListNode temp2 = temp.next; temp2 != null; temp2 = temp2.next) {
				if (temp2.val < temp.val) {
					// int swapper;
					// swapper = temp.value;
					// temp.value = temp2.value;
					// temp2.value = swapper;
					temp.val += temp2.val;
					temp2.val = temp.val - temp2.val;
					temp.val = temp.val - temp2.val;
				}

			}

		return head;
	}

	public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        
        ListNode node = head;
        while(node != null){
            ListNode next = node.next;
            ListNode prev = dummy;
            while(prev.next != null && node.val > prev.next.val){
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            node = next;
        }
        return dummy.next;
    }
	
	/**
	 * Given a singly linked list where elements are sorted in ascending order,
	 * convert it to a height balanced BST.
	 */
	public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode copyHead = head, slow = head, fast = head;
        ListNode prev = null;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        if(prev!=null)prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.leftChild = copyHead == slow?null:sortedListToBST(copyHead);
        root.rightChild = sortedListToBST(slow.next);
        return root;
    }
	
	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * </pre>
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fakeHead = new ListNode(0);
        ListNode runner = fakeHead;
        fakeHead.next = head;
        while(head!=null && head.next!=null){
            ListNode one = head;
            ListNode two = head.next;
            runner.next = two;
            one.next = two.next;
            two.next = one;
            runner = one;
            head = runner.next;
        }
        return fakeHead.next;
		
	}

}
