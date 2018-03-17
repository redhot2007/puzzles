package org.puzzles.coding.snippet.models.TreesAndGraph;


import java.util.List;
import java.util.concurrent.locks.Lock;

public class ThreadExample extends Thread {
	private static List<Integer> list;
	private static boolean f_sync;
	private static boolean f_lock;
	private static Lock lock;

	//	private static Vector<Integer> vect = new ArrayList<Integer>();
	public ThreadExample(List<Integer> list) {
		ThreadExample.list = list;
	}

	public ThreadExample(List<Integer> list,Lock lock) {
		ThreadExample.list = list;
		ThreadExample.lock = lock;
		f_lock = true;
	}
	
	public ThreadExample() {

	}

	
	public static void setSync(String s) {
		f_sync = (s.equalsIgnoreCase("Synchronize"));
	}

	@Override
	public void run() {
		modifyList();
		//		for (int i : list)
		//		System.out.println(list.size());
	}

	private void modifyList() {
		if (f_sync) {
			System.out.println("In sync mode");
			for (int i = 0; i < 50; i++) {
				synchronized (list) {
					list.add(i);
				}
			}
		} else if (f_lock) {
			System.out.println("Using locks");
			for (int i = 0; i < 50; i++) {
				lock.lock();
				list.add(i);
				lock.unlock();
			}

		} else {
			for (int i = 0; i < 5; i++){
//				System.out.println("i = " + i);
				list.add(i);
//				System.out.println("After join: " + list.size());
			}
		}
	}
}
