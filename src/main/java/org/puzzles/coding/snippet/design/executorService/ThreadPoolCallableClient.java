package org.puzzles.coding.snippet.design.exception;

import java.util.concurrent.Callable;

public class ThreadPoolCallableClient implements Callable<String> {

	String name;
	public ThreadPoolCallableClient(String name){
		this.name = name;
	}
	@Override
	public String call() throws Exception {
		System.out.println(name+" is running");
		return name+" completed";
	}

}
