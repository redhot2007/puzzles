package org.puzzles.coding.snippet.design.executorService;

import org.puzzles.coding.snippet.design.exception.ThreadPoolCallableClient;
import org.puzzles.coding.snippet.design.exception.ThreadPoolRunnableClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceMain {
	
	public static void main(String args[]){
		ExecutorService pool  = Executors.newFixedThreadPool(5); 
		List<ThreadPoolRunnableClient> runnables = new ArrayList<ThreadPoolRunnableClient>();
		List<ThreadPoolCallableClient> callables = new ArrayList<ThreadPoolCallableClient>();
		List<Future> results = new ArrayList<Future>();
		for(int i=0;i<100;i++){
			runnables.add(new ThreadPoolRunnableClient("Runnable:"+i));
			callables.add(new ThreadPoolCallableClient("Callable:"+i));
		}
		for(int i=0; i<runnables.size() ; i++){
			results.add(pool.submit(runnables.get(i)));		
			results.add(pool.submit(callables.get(i)));		
		}
		
		for(Future result: results){
			try {
				System.out.println("Runnable returns null vs. callable returns the return value - "+result.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();
		
	}
}
