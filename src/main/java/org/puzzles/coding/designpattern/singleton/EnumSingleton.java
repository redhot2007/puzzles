package org.puzzles.coding.designpattern.singleton;

public class EnumSingleton {

	public static void main(String args[]){
		Foo a = Foo.INSTANCE;
		Foo b = Foo.INSTANCE;
		System.out.println(a.equals(b));
	}

	enum Foo{
		INSTANCE;
	}
}
