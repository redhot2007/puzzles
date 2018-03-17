package org.puzzles.coding.snippet.void_main;
import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;
import org.puzzles.coding.snippet.programs.Puzzles;
import org.puzzles.coding.snippet.programs.ThreadConcepts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;


import java.lang.reflect.*;
//import java.net.MalformedURLException;
//
//import com.sun.jndi.toolkit.url.Uri;
//import com.sun.xml.internal.fastinfoset.util.StringArray;

public class MainFunction {

	public static void main(String args[]) {
		
/*		ITemplate template = new RedefinedHashing();
		ITemplate template = new Snippets_StringsAndArrays();
		ITemplate template = new Snippets_TreesAndGraphs();
		
		ITemplate template = new Snippets_BitManipulation();
		ITemplate template = new Snippets_LinkedList();
		ITemplate template = new Snippets_Sorts();*/
		ITemplate template = new Puzzles();
		


	} // end main

	public static void threads() {
		//      ThreadImplementation.arrayListWithoutSynchronized();
		//		ThreadImplementation.vectorWithoutSynchronized();
		//		ThreadImplementation.arrayListWithSynchronized();
		//		ThreadImplementation.arrayListWithReentrantLock();
		ThreadConcepts.printAB();
		//		ProducerConsumerSolution.deployProducerComsumerThreads();
	}

	private static void demoException() {
		//		try {
		//			ExceptionImplementation.testMethod(-1);
		//		} catch (ExceptionExample e) {
		//			e.printStackTrace();
		//		}

//		ExceptionImplementation.concurrentModificationForAL();
//		ExceptionImplementation.concurrentModificationForALWithIterator();

	}

	public static void reflection(String className) {
		try {
			Class c = Class.forName(className);
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++)
				System.out.println(m[i].toString());
		} catch (Throwable e) {
			System.err.println(e);
		}
	}


	public static void puzzleFunctionCalls() {
		/**************************
		 * Puzzles
		 * 
		 */
		// int arr[] = { 0, 0, 2, 3, 3, 10, 11, 14, 10 };
		// Puzzles.printDuplicates(arr);
		// for (int i = 0; i < 10; i++)
		// Puzzles.fibonacciRecursive(10);
		// System.out.println(Puzzles.checkPalindromeNumber(122));
		// System.out.println(Puzzles.findMaxSumInWindow(arr, 10));
		// System.out.println(Puzzles.printExcelColumnToAlphaAndReverse(300));
		// System.out.println(Puzzles.reverseTheNumber(001));
		// System.out.println(Puzzles.isPrime(4));
		// Puzzles.printNPrimes(1000);
		// System.out.println(Puzzles.isArmstrongNumber(37));
		// for(int i = 0; i < 10;i++)
		// System.out.println(Puzzles.decimalToBinary(i));

		// Puzzles.nQueens(5);

		// Puzzles.findAnagrams(new
		// String[]{"apple","pplea","orange","rangeo","kat"});
		// Puzzles.tenLargestNumbersInArray(new int[]
		// {1,2,3,4,2,5,6,0,5,6,7,8,9,10});
		// Puzzles.listFactors(10);
		// Puzzles.listCommonFactors(25, 0);
		// Puzzles.listCommonElementsWithoutUsingDS(new int[]
		// {1,2,3,4,5,6,7,8,9,10},new int[] {0,1,2,6,3,4,5});
		// Puzzles.reverseSentence("What the bloody hell is your problem?");
		// char[][] s = new char[][]{
		// {'x','o','o'},
		// {'x','x',' '},
		// {'o', 'x','o'}
		// };
		// System.out.println(Puzzles.ticTacToeWinner(s));
		// Puzzles.numberOfTrailingZeroesInFactorial(100);
		// Puzzles.findPairOfNumbersWithSum(
		// new int[] { 10,9,8,7,6,5,4,3,2,1 }, 10);

		// for(int i=1;i<20;i++)
		// Puzzles.checkForFibonacci(i);
		// Puzzles.printNumbersLexicographicallly(25, 1);
		// for(int i=1;i<20;i++)
		// Puzzles.enQueue(i);
		// for(int i=1;i<30;i++)
		// Puzzles.deQueue();
		// Puzzles.lcmOfTwoNumbers(7,0);
	}

	public static void sortFunctionCalls() {

		/****************
		 * Sort
		 * 
		 */


	}

	public static void bitManipulationFunctionCalls() {
		/***********
		 * Bit Manipulation
		 */
		// System.out.println(Integer.toBinaryString(6));
		// System.out.println((BitManipulation
		// .countSetBits(11)));
		// BitManipulation.maxOfTwoNumbers(Integer.MAX_VALUE,Integer.MAX_VALUE);
		// System.out.println(BitManipulation.subtractWithoutMinusOperator(2,3));
		// BitManipulation.getSingle(new int[]{1, 2,3,4,5,5,6},2);
		// BitManipulation.checkForOppositeSigns(1, 2);
		//		for (int i = 0; i < 10; i++)
		//			BitManipulation.isPowerOfTwo(i);
//		BitManipulation.bigEndianToLittleEndian(0x0A0B0C0D);
	}
} // end test class

