package org.puzzles.coding.datastructure;

public class Iterator implements java.util.Iterator<Integer>{

	private int[] array;
	private int currentIndex = 0;

	public Iterator(int a[]){
		this.array = a;
	}
	

	public boolean hasNext() {	
		return currentIndex<array.length;
	}


	public Integer next() {
		return array[currentIndex++];
	}

	public void remove() {

	}

}

