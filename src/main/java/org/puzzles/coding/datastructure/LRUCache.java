package org.puzzles.coding.datastructure;

import java.util.HashMap;

public class LRUCache{
	
	Node head;
	Node tail;
	private HashMap<String, Node> hash = new HashMap<String, Node>();
	private int capacity;
	
	/**
	 * capacity of cache
	 * @param capacity
	 */
	public LRUCache(int capacity){
		this.capacity = capacity;
	}
	
	public int get(String key){
		if(hash.containsKey(key)){
			Node mru = hash.get(key);
			markMRU(mru);
			return mru.value;
		}
		else
			return -1;
	}
	
	private void markMRU(Node currentNode){
		Node left = currentNode.left;
		Node right = currentNode.right;
		if(null!=left){
			left.right = right;
		}
		if(null!=right)
			right.left = left;
		if(head!=null){
			currentNode.right = head;
			head.left = currentNode;
			head = currentNode;
			hash.put(currentNode.key,currentNode);
			if(hash.size() == 1) 
				tail = currentNode;
		}
		else{
			head = currentNode;
			tail = currentNode;
		}
	}
	
	public void set(String key, int value){
		if(hash.containsKey(key)){
			Node currentNode = hash.get(key);
			currentNode.value = value;
			markMRU(currentNode);
		}
		else{
			Node currentNode = new Node(key, value);
			if( hash.size() == capacity){
				removeLRU();
			}
			markMRU(currentNode);
		}
	}
	private void removeLRU(){
		hash.remove(tail.key);
		Node prev = tail.left;
		prev.right = null;
		tail.left = null;
		tail = prev;
	}
	class Node {
		public String key;
		public int value;
		public Node left;
		public Node right;
		public Node(String key, int value){
			this.key = key;
			this.value = value;
		}
	}
	
	public int[] getCachedValues(){
		int[] cache = new int[this.hash.size()];
		Node copy = head;
		for(int i=0;i<cache.length;i++){
			cache[i] = copy.value;
			copy = copy.right;
			System.out.println(cache[i]);
		}
		return cache;
	}
}
