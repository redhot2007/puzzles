package org.puzzles.coding.designpattern.observer;

import java.util.Random;

public class ObserverPatternMain {

	public static void main(String args[]){
		Observable1 observable = new Observable1();
		Observable2 observable2 = new Observable2();
		ObserverImpl observer = new ObserverImpl();
		observable.addObserver(observer);
		observable2.addObserver(observer);
		Random random = new Random();
			observable.setSleep(Math.abs(random.nextInt(1)));
			observable2.setSleep(Math.abs(random.nextInt(1)));
		new Thread(observable).start();;
		new Thread(observable2).start();;
		
	}
}
