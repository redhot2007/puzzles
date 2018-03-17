package org.puzzles.coding.snippet.design.producerConsumer;

import java.util.Vector;


public class Design_2 {
	public static void main(String args[]){
		Design_2 impl = new Design_2();
        Vector<Integer> sharedQueue = new Vector<Integer>();
        int size = 4;
        Thread prodThread = new Thread(impl.new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(impl.new Consumer(sharedQueue, size), "Consumer");
        prodThread.start();
        consThread.start();
	}
	class Producer implements Runnable{
	    private final Vector<Integer> sharedQueue;
	    private final int SIZE;

	    public Producer(Vector<Integer> sharedQueue, int size) {
	        this.sharedQueue = sharedQueue;
	        this.SIZE = size;
	    }
		@Override
		public void run() {
			for (int i = 0; i < 7; i++) {
	            System.out.println("Produced: " + i);
	            try {
	                produce(i);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }

	        }
			
		}
	    private void produce(int i) throws InterruptedException {

	        //wait if queue is full
	        while (sharedQueue.size() == SIZE) {
	            synchronized (sharedQueue) {
	                System.out.println("Queue is full " + Thread.currentThread().getName()
	                                    + " is waiting , size: " + sharedQueue.size());

	                sharedQueue.wait();
	            }
	        }

	        //producing element and notify consumers
	        synchronized (sharedQueue) {
	            sharedQueue.add(i);
	            sharedQueue.notifyAll();
	        }
	    }
		
	}
	class Consumer implements Runnable {

	    private final Vector<Integer> sharedQueue;
	    private final int SIZE;

	    public Consumer(Vector<Integer> sharedQueue, int size) {
	        this.sharedQueue = sharedQueue;
	        this.SIZE = size;
	    }

	    @Override
	    public void run() {
	        while (true) {
	            try {
	                System.out.println("Consumed: " + consume());
	                Thread.sleep(50);
	            } catch (InterruptedException ex) {
	            	ex.printStackTrace();
	            }

	        }
	    }

	    private int consume() throws InterruptedException {
	        //wait if queue is empty
	        while (sharedQueue.isEmpty()) {
	            synchronized (sharedQueue) {
	                System.out.println("Queue is empty " + Thread.currentThread().getName()
	                                    + " is waiting , size: " + sharedQueue.size());

	                sharedQueue.wait();
	            }
	        }

	        //Otherwise consume element and notify waiting producer
	        synchronized (sharedQueue) {
	            sharedQueue.notifyAll();
	            return sharedQueue.remove(0);
	        }
	    }
	}
}

