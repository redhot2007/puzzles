package org.puzzles.coding.datastructure;


import java.util.Iterator;


public class CircularArray<T> implements Iterable<T>{

	private T list[];
	private int counter =0;
	private int head;
	public CircularArray(int size){
		list = (T[]) new Object[size]; 
	}
	
	public void add(T element){
		list[counter++] = element;
	}
	
	public int convert(int size){
		if(size < 0) size+=list.length+1;
		size%=list.length;
		return head+size;
	}
	
	public void rotate(int size){

		head = convert(size);
	}
	
	public T get(int index){
		return (T) list[head+index];
		
	}
	
	public void set(int index,T update){
		list[convert(index)]=update;
	}


	public Iterator<T> iterator() {
		return new CircularArrayIterator<T>(this);
	}
	private class CircularArrayIterator<T> implements Iterator<T>{
		private T[] _items;
		private int current = -1;
		CircularArrayIterator(CircularArray<T> array){
			_items = array.list;
		}

		public boolean hasNext() {
			return current<_items.length-1;
		}


		public T next() {
			return _items[convert(++current)];
		}

		public void remove() {

		}


	}
	
}


