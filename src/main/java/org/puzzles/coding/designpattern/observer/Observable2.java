package org.puzzles.coding.designpattern.observer;

import java.util.Observable;
import java.util.Random;

public class Observable2 extends Observable implements Runnable{

	private int counter = 0;
	private int sleep;
	
	public void setSleep(int sleep){
		this.sleep= sleep;
	}

	public void run() {
		counter++;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers(counter);
		
	}

}
