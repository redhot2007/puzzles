package org.puzzles.coding.snippet.design.exception;
import java.util.ArrayList;
import java.util.Iterator;

public class ExceptionImplementation{
	public static void testMethod(int i)throws ExceptionExample{
		
		if(i<0)
			throw new ExceptionExample();
		
	}
	
	public static void concurrentModificationForAL(){
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<20;i++)
			al.add(i);
		for(int i:al)
			al.add(i);
		System.out.println(al.size());
	}
	
	public static void concurrentModificationForALWithIterator(){
		ArrayList<Integer> al = new ArrayList<Integer>();

		for(int i=0;i<20;i++)
			al.add(i);
		Iterator<Integer> it = al.iterator();
		
		System.out.println(al.size());
	}
}
