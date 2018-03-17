package org.puzzles.coding.snippet.design.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Design_1 {
	@Test
	public void testProducerConsumer() {
		MessageQueue c = new MessageQueue();
		Producer p1 = new Producer(c, 1);
		Consumer c1 = new Consumer(c, 1);
		p1.start();
		c1.start();
	}

	public static void main(String[] args) {
		Design_1 impl = new Design_1();
		MessageQueue c = impl.new MessageQueue();
		Producer p1 = impl.new Producer(c, 1);
		Consumer c1 = impl.new Consumer(c, 1);
		p1.start();
		c1.start();
	}
	class Producer extends Thread {
		private MessageQueue messageQueue;
		private int number;

		public Producer(MessageQueue c, int number) {
			messageQueue = c;
			this.number = number;
		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				messageQueue.put(i);

				// try {
				// sleep((int) (Math.random() * 100));
				// } catch (InterruptedException e) {
				// }
			}
		}
	}

	class Consumer extends Thread {
		private MessageQueue cubbyhole;
		private int number;

		public Consumer(MessageQueue c, int number) {
			cubbyhole = c;
			this.number = number;
		}

		public void run() {
			int value = 0;
			for (int i = 0; i < 10; i++) {
				value = cubbyhole.get();
				// System.out.println("Consumer #" + this.number + " got: " +
				// value);
			}
		}
	}

	class MessageQueue {
		private Queue<Integer> queue = new LinkedList<Integer>();
		private int contents;
		private boolean available = false;

		public synchronized int get() {
			// return queueGet();
			return contentGet();
		}

		public synchronized void put(int value) {
			// queuePut(value);
			contentsPut(value);
		}

		private int contentGet() {
			while (!available) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			available = false;
			notifyAll();
			System.out.println("Consumer  #" + " get: " + contents);
			return contents;
		}

		private void contentsPut(int val) {
			while (available) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			available = true;
			notifyAll();
			contents = val;
			System.out.println("Producer #" + " put: " + contents);
			return;
		}

		private int queueGet() {
			while (queue.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			// available = false;
			notifyAll();
			System.out.println("Consumer  #" + " get: " + queue.peek());
			return queue.remove();
		}

		private void queuePut(int value) {
			while (queue.size() > 5) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Producer #" + " put: " + value);
			queue.add(value);
			// available = true;
			notifyAll();
		}
	}
}

