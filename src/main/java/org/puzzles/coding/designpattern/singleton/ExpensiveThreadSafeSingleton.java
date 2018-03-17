package org.puzzles.coding.designpattern.singleton;

public class ExpensiveThreadSafeSingleton {
	private static ExpensiveThreadSafeSingleton singleton;

	/**
	 * method only needs to be synchronized the first time it is called. Because synchronization is very expensive performance-wise (synchronized methods can run up to 100 times slower than unsynchronized methods) 
	 * @return
	 */
	public synchronized static ExpensiveThreadSafeSingleton getInstance() {
		   if(singleton == null) {
		      singleton = new ExpensiveThreadSafeSingleton();
		   }
		   return singleton;
		}
}
