package org.puzzles.coding.designpattern.singleton;

public class SimpleThreadSafeSingleton {
		   public final static SimpleThreadSafeSingleton INSTANCE = new SimpleThreadSafeSingleton();
		   private SimpleThreadSafeSingleton() {
		         // Exists only to defeat instantiation.
		      
		}
}
