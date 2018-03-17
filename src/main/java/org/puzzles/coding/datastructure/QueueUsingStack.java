package com.dataStructures;

import java.util.Stack;

public class QueueUsingStack {
	private  Stack<Integer> s1;
	private  Stack<Integer> s2;

	public  void enQueue(int item) {
		if (s1 == null)
			s1 = new Stack<Integer>();
		s1.push(item);

	}
	public  int deQueue() {
		if (s2 == null)
			s2 = new Stack<Integer>();
		if (s2.isEmpty())
			while (!s1.isEmpty())
				s2.push(s1.pop());
		if (!s2.isEmpty()) {
			System.out.println(s2.peek());
			return s2.pop();
		}
		return -1;
	}
}
