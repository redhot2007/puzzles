package org.puzzles.coding.snippet.programs;

import org.junit.Test;

public class GeneralConcepts {

	@Test
	public void testInstanceOfLogic(){
		Integer i = null;
		Integer i2 = 1;
		Object o = i;
		if(o instanceof Integer){
			System.out.println("i : yes");
		}
		else{
			System.out.println("i: null");
		}
		o = i2;
		if(o instanceof Integer){
			System.out.println("i2: yes, an integer");
		}
		else{
			System.out.println("i2: null");
		}
	}
}
