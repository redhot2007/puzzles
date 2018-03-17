package org.puzzles.coding.snippet.programs;


import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;

public class BitManipulation implements ITemplate {
	public int setBit(int n, int position) {
		return (n | (1 << (position - 1)));
	}

	public int clearBit(int n, int position) {
		return (n & (~(1 << (position - 1))));
	}

	public int getBit(int n, int position) {
		return (n >> (position - 1) & 1);
		/*
		 * if ((n & (1 << (position-1))) != 0) return 1; else return 0;
		 */
	}

	/**
	 * Given an array of size n with sequential numbers, there is only one
	 * number is duplicate. Find that number.
	 * 
	 * @param a
	 * @return
	 */
	public int getDuplicate(int[] a) {
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result = result ^ a[i];
		}
		return result;
	}

	public int toggleBit(int n, int position) {
		return (n ^ (1 << (position - 1)));
	}

	public int clearMsbToPosition(int n, int position) {
		return (n & (((1 << position - 1) - 1)));
	}

	public int clearPositionToLsb(int n, int position) {
		return n & ((~0) << position);
		// return (n & ~((1 << position) - 1));
	}

	public int countSetBits(int n) {
		int count = 0;
		if (n < 0)
			return -1;
		for (int i = n; i != 0; i = i >> 1)
			count += i & 1;
		return count;
	}

	/**
	 * A smarter solution is called the Brian Kernighan's Algorithm. It's a neat
	 * trick to avoid unnecessary iterations - Eg. iterating through all 32 bits
	 * of a 4 byte int when only the most significant bit is set. This solution
	 * has runtime complexity of O(m) where m is the number of set bits.
	 * 
	 * @param n
	 * @return
	 */
	public int countSetBitskernighansAlgorithm(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public int numberOfBitChangeForA2B(int a, int b) {
		int mask = a ^ b;
		return countSetBits(mask);
	}

	public void swapTwoNumbers(int a, int b) {
		System.out.println("Before swapping ... a = " + a + " b = " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("After swapping ... a = " + a + " b = " + b);
	}

	private int signOfNumber(int n) {
		return n >> Integer.SIZE - 1 & 1;
	}

	private int flipLsb(int n) {
		return n ^ 1;
	}

	public int maxOfTwoNumbers(int a, int b) {

		int c = a - b;
		int sa = signOfNumber(a);
		int sb = signOfNumber(b);
		int sc = signOfNumber(c);

		int useSign_a = sa ^ sb;
		int useSign_c = flipLsb(sa ^ sb);

		int resultSign = sa * useSign_a + sc * useSign_c;
		int flippedResultSign = flipLsb(resultSign);

		int max = resultSign * a + flippedResultSign * b;
		return max;
	}

	public int addWithoutPlusOperator(int a, int b) {
		if (b == 0)
			return a;
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return addWithoutPlusOperator(sum, carry);

	}

	public int subtractWithoutMinusOperator(int a, int b) {
		b = ~b + 1; // 2s complement -> 1's cimpolement plus 1
		return a + b;
	}

	public int doubleTheInteger(int i) {
		// to 2^i the given value left shift by i
		int returnValue = i << 1;
		// System.out.println("BitManipulation.doubleTheInteger: " +
		// returnValue);
		return returnValue;
	}

	/****************
	 * Find if given number is power of two: 1. count number of 1's in given
	 * number -> count == 1 2. (x & (x - 1)) == 0 3. (x & (~x + 1)) == x
	 */

	public boolean isPowerOfTwo(int x) {
		// boolean returnValue = (x != 0) && ((x & (~x + 1)) == x);
		boolean returnValue = (x & (x - 1)) == 0;
		// System.out.println("BitManipulation.isPowerOfTwo: " + returnValue);
		return (returnValue);
	}

	// Try to understanc when bored

	public int getSingle(int arr[], int n) {
		int ones = 0, twos = 0;
		int common_bit_mask;
		for (int i = 0; i < n; i++) {
			twos = twos | (ones & arr[i]);
			ones = ones ^ arr[i];
			common_bit_mask = ~(ones & twos);
			ones &= common_bit_mask;
			twos &= common_bit_mask;
		}
		System.out.println(ones);
		return ones;
	}

	public boolean checkForOppositeSigns(int a, int b) {
		// System.out.println((a ^ b) < 0);
		return (a ^ b) < 0;
	}

	// convert big endian to little endian data

	public int bigEndianToLittleEndian(int num) {

		int b0 = (num & 0xff) << 24;
		int b1 = (num & 0xff00) << 8;
		int b2 = (num & 0xff0000) >> 8;
		int b3 = (num & 0xff000000) >> 24;
		num = b0 | b1 | b3 | b2;
		System.out.println(num);
		System.out.println(Integer.toHexString(num));
		return -1;
	}
}
