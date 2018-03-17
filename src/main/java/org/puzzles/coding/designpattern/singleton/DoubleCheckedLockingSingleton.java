package org.puzzles.coding.designpattern.singleton;

public class DoubleCheckedLockingSingleton {
	private static DoubleCheckedLockingSingleton singleton;

	public static DoubleCheckedLockingSingleton getInstance() {
		  if(singleton == null) {
		     synchronized(DoubleCheckedLockingSingleton.class) {
		       if(singleton == null) {
		         singleton = new DoubleCheckedLockingSingleton();
		       }
		    }
		  }
		  return singleton;
		}
}
