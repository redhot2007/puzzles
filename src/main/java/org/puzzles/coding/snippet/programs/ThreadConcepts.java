package org.puzzles.coding.snippet.programs;
import org.puzzles.coding.snippet.models.TreesAndGraph.ThreadExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadConcepts {

	public static void arrayListWithoutSynchronized() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		deployThreads(al);

	}

	public static void vectorWithoutSynchronized() {
		Vector<Integer> al = new Vector<Integer>();
		deployThreads(al);

	}

	public static void arrayListWithSynchronized() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		ThreadExample.setSync("Synchronize");
		deployThreads(al);

	}

	public static void arrayListWithReentrantLock() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		deployThreads(al,new ReentrantLock());

	}
	
	public static void deployThreads(List<Integer> al) {
		ThreadExample t1 = new ThreadExample(al);
		ThreadExample t2 = new ThreadExample();
		ThreadExample t3 = new ThreadExample();
		t1.start(); 
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After join: " + al.size());
	}
	
	public static void deployThreads(List<Integer> al,Lock lock) {
		ThreadExample t1 = new ThreadExample(al,lock);
		ThreadExample t2 = new ThreadExample();
		ThreadExample t3 = new ThreadExample();
		t1.start(); 
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After join: " + al.size());
	}

	public static void printAB(){
		Thread t1 =new Thread(){
			public void run(){
				printABHelper('A');
			}
		};
		t1.start();
		Thread t2 =new Thread(){
			public void run(){
				printABHelper('B');
			}
		};
		t2.start();
	}
	
	private static Integer synchronizer=0;
	
	private  static void printABHelper(char alpha){
		int i=0;
		while(i<2){
			synchronized(synchronizer){
				if((synchronizer == 0 && alpha =='A')||(synchronizer == 1 && alpha =='B')){
					System.out.println(alpha);
					synchronizer^=1;
					i++;
				} 
			}
			
		}
			
	}

}

