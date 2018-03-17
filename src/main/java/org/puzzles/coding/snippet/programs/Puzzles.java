package org.puzzles.coding.snippet.programs;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;
import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;
import org.puzzles.coding.snippet.models.puzzles.Fraction;
import org.puzzles.coding.snippet.models.puzzles.Interval;
import org.puzzles.coding.snippet.models.puzzles.Kid;


public class Puzzles implements ITemplate {
	@Test		
	public void test(){		
		List<String> list = new ArrayList<String>();		
		StringBuilder sb = new StringBuilder();		
		helper(list, sb);		
		System.out.println(list.get(0));		
		System.out.println(sb.toString());		
	}		
	private void helper(List<String> list, StringBuilder sb) {		
		list.add("abc");		
		sb.append("abc");		
				
	}
	/**
	 * Given two binary strings, return their sum (also a binary string).
	 * 
	 * <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * a = "11", b = "1"
	 * return "100".
	 * </pre>
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * Total Time - O(N)
	 * Space - O(N)
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
		if (a == null || a.length() == 0)
			return b;
		if (b == null || b.length() == 0)
			return a;
		int a1 = a.length() - 1;
		int b1 = b.length() - 1;
		StringBuilder sb = new StringBuilder();
		char carry = '0';
		while (a1 >= 0 || b1 >= 0 || carry == '1') {
			char one = a1 >= 0 ? a.charAt(a1) : '0';
			char two = b1 >= 0 ? b.charAt(b1) : '0';
			if (one == two) {
				sb.append(carry);
				carry = one;
			} else {
				sb.append(carry == '1' ? '0' : '1');
			}
			a1--;
			b1--;
		}
		return sb.reverse().toString();

	}

	/**
	 * Find the GCD of number.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	/**
	 * Given two fractions, add the numbers and reduce it to lowest form
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public Fraction addFraction(Fraction n1, Fraction n2) {
		Fraction result = new Fraction();
		result.numerator = n1.numerator * n2.denominator + n2.numerator
				* n1.denominator;
		result.denominator = n2.denominator * n1.denominator;
		int gcd = gcd(result.numerator, result.denominator);
		result.numerator /= gcd;
		result.denominator /= gcd;
		return result;
	}

	public int binaryToDecimal(int num) {
		return 0;
	}

	public boolean checkForFibonacci(int n) {
		int sum = 5 * n * n + 4;
		int diff = 5 * n * n - 4;
		if (isPerfectSquare(sum) || isPerfectSquare(diff)) {
			return true;
		} else {
			// System.out.println(n+" is not fibonacci");
			return false;
		}

	}

	public boolean isPerfectSquare(int n) {
		int i = 1;
		while (true) {
			int square = i * i;
			if (square == n)
				return true;
			if (square > n)
				return false;
			i++;
		}
		// int i = (int) Math.sqrt(n);
		// return i * i == n;
	}

	/**
	 * Given two integers n and k, return all possible combinations of k numbers
	 * out of 1 ... n. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * If n = 4 and k = 2, a solution is:
	 * [
	 *   [2,4],
	 *   [3,4],
	 *   [2,3],
	 *   [1,2],
	 *   [1,3],
	 *   [1,4],
	 * ]
	 * </pre>
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * 
	 * 
	 * </pre>
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (n == 0)
			return result;
		List<Integer> path = new ArrayList<Integer>();
		int startingNumber = 1;
		combineHelper(startingNumber, n, k, result, path);
		return result;
	}

	private void combineHelper(int startingNumber, int n, int k,
			List<List<Integer>> result, List<Integer> path) {
		if (k == 0) {
			result.add(new ArrayList<Integer>(path));
			return;
		}
		for (int i = startingNumber; i <= n; i++) {
			path.add(i);
			combineHelper(i + 1, n, k - 1, result, path);
			path.remove(path.size() - 1);
		}
	}

	/**
	 * Write a method that combines an array of iterators into a new one, such
	 * that, e.g. for input [A B C] where: A.next() returns a1, a2,
	 * respectively; B.next() returns b1; C.next() returns c1, c2, c3,
	 * respectively;
	 */
	public static class CombinedIterator implements Iterator<String> {
		private Queue<Iterator<String>> q;

		public CombinedIterator(Iterator<String>[] i) {
			q = new LinkedList<Iterator<String>>();
			for (Iterator<String> each : i) {
				if (each.hasNext())
					q.add(each);
			}
		}

		public boolean hasNext() {
			return !q.isEmpty();
		}

		public String next() {
			String result = null;
			if (hasNext()) {
				Iterator<String> current = q.remove();
				result = current.next();
				if (current.hasNext())
					q.add(current);

			}
			return result;
		}

		public void remove() {

		}

	}

	public Iterator<String> combineIterators(Iterator<String>[] i) {
		List<String> l = new ArrayList<String>();
		while (true) {
			boolean isEmpty = true;
			for (int j = 0; j < i.length; j++) {
				isEmpty = isEmpty && !i[j].hasNext();
			}
			if (isEmpty)
				break;
			for (int j = 0; j < i.length; j++) {
				if (i[j].hasNext())
					l.add(i[j].next());
			}

		}
		return l.iterator();

	}

	/**
	 * Given a set of candidate numbers (C) and a target number (T), find all
	 * unique combinations in C where the candidate numbers sums to T. The same
	 * repeated number may be chosen from C unlimited number of times. The
	 * solution set must not contain duplicate combinations.
	 * 
	 * <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * given candidate set 2,3,6,7 and target 7, 
	 * A solution set is:
	 * [7] 
	 * [2, 2, 3]
	 * </pre>
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0)
			return null;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		Arrays.sort(candidates);
		combinationSumHelper(candidates, path, result, target, 0);
		return result;
	}

	private void combinationSumHelper(int[] candidates, List<Integer> path,
			List<List<Integer>> result, int target, int start) {
		if (target < 0)
			return;
		if (target == 0) {
			result.add(new ArrayList<Integer>(path));
			return;
		}

		for (int i = start; i < candidates.length; i++) {
			path.add(candidates[i]);
			combinationSumHelper(candidates, path, result, target
					- candidates[i], i);
			path.remove(path.size() - 1);
		}
	}

	/**
	 * using numbers 1,2,3....9 combine numbers using + or - or append and match
	 * the given sum use all numbers from 1 to 9. <br>
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * 1 + 2 + 3 - 4 + 56 + 678 - 9 == sum
	 * </pre>
	 * 
	 * @return
	 */
	public List<Integer> combinationSUm2(int sum) {

		return null;
	}

	public String decimalToBinary(int num) {
		StringBuilder sb = new StringBuilder();
		int rem = 0;
		while (num > 1) {
			rem = num % 2;
			sb.append(rem);
			num = num / 2;
		}
		sb.append(num);
		while (sb.length() < Integer.SIZE)
			sb.append(0);
		return sb.reverse().toString();
	}

	/**
	 * Inputs in streams are in reverse order 2048-1024 = 1024 8 4 0 2 4 2 0 1
	 * ------- 4 2 0 1
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 * @throws IOException
	 */
	public int difference(BufferedReader b1, BufferedReader b2)
			throws IOException {
		String i1, i2;
		StringBuilder sb = new StringBuilder();
		int c = 0;
		while ((i1 = b1.readLine()) != null) {
			i2 = b2.readLine();
			int a = Integer.parseInt(i1);
			int b = i2 != null ? Integer.parseInt(i2) : 0;
			int diff = a - c - b;
			if (diff < 0) {
				sb.append(10 + diff);
				c = 1;
			} else {
				sb.append(diff);
				c = 0;
			}
		}

		return Integer.parseInt(sb.reverse().toString());

	}

	/**
	 * Get a string input and check if it can be arranged such that no adjacent
	 * characters are the same
	 * 
	 * @param s
	 * @return
	 */
	public boolean fancyShuffle(String s) {
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			int count = 0;
			if (hash.containsKey(s.charAt(i)))
				count = hash.get(s.charAt(i));
			hash.put(s.charAt(i), ++count);
			if (max < count)
				max = count;
		}
		// if there are n same characters, we need n-1 characters to seperate
		// those
		// eg aaabb,aaaabbb
		return max + (max - 1) <= s.length();

	}

	public int factorialRecursive(int n) {
		if (n == 0 || n == 1)
			return 1;
		return factorialRecursive(n - 1) * n;
	}

	public int factorialIterative(int n) {
		if (n == 0 || n == 1)
			return 1;
		int result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public int fibonacciRecursiveWithoutExtraSpace(int n) {
		if (n == 0)
			return 0;
		if (n <= 2)
			return 1;
		return fibonacciRecursiveWithoutExtraSpace(n - 2)
				+ fibonacciRecursiveWithoutExtraSpace(n - 1);
	}

	List<Integer> fibonnacciList = new ArrayList<Integer>();

	/**
	 * fix it..doesnt use the space at all. have to add it to list
	 * 
	 * @param number
	 * @return
	 */
	public int fibonacciRecursiveWithExtraSpace(int number) {
		if (number == 0)
			return 0;
		if (number <= 2)
			return 1;
		if (fibonnacciList.size() > number)
			return fibonnacciList.get(number - 2)
					+ fibonnacciList.get(number - 1);
		else
			return fibonacciRecursiveWithExtraSpace(number - 2)
					+ fibonacciRecursiveWithExtraSpace(number - 1);
	}

	public int fibonacciIterative(int number) {
		if (number == 0)
			return 0;
		if (number <= 2)
			return 1;
		int one = 1;
		int two = 1;
		for (int i = 3; i <= number; i++) {
			int three = one + two;
			one = two;
			two = three;
		}
		return two;
	}

	/**
	 * A canoe can hold upto two kids and a max weight. Given list of kids with
	 * their weights, find no of canoes needed to accommodate all the kids
	 * 
	 * @param kids
	 * @param max
	 * @return
	 */
	public int findNumberOfCanoes(List<Kid> kids, int max) {
		Collections.sort(kids, new Comparator<Kid>() {
			@Override
			public int compare(Kid one, Kid two) {
				return one.weight - two.weight;
			}

		});

		int numberOfCanoes = 0;
		while (kids.size() > 0) {
			int start = 0;
			int end = kids.size() - 1;
			while (start < end
					&& kids.get(start).weight + kids.get(end).weight > max)
				end--;
			numberOfCanoes++;
			if (kids.size() > 0)
				kids.remove(end);

			if (start == end) {
				numberOfCanoes += kids.size();
				break;
			}
			kids.remove(0);

		}

		return numberOfCanoes;

	}

	/**
	 * 
	 * @param arr
	 * @param windowSize
	 * @return
	 */

	public int findMaxSumInWindow(int arr[], int windowSize) {

		int max = 0, prevSum = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < windowSize; i++) {
			prevSum = prevSum + arr[i];
			queue.add(arr[i]);
		}

		max = prevSum;
		// i = windowSIze-1 or 1? check when time
		for (int i = windowSize - 1; i < arr.length; i++) {
			int sum = prevSum + arr[i] - queue.remove();
			if (sum > max)
				max = sum;
			prevSum = sum;
			queue.add(arr[i]);

		}
		return max;

		//
	}

	/**
	 * You are given a string, s, and a list of words, words, that are all of
	 * the same length. Find all starting indices of substring(s) in s that is a
	 * concatenation of each word in words exactly once and without any
	 * intervening characters.
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * s: "barfoothefoobarman"
	 * words: ["foo", "bar"]
	 * You should return the indices: [0,9].
	 * (order does not matter).
	 * </pre>
	 * 
	 * @param inputString
	 * @param list
	 * @return
	 */
	public List<Integer> findSubstring(String inputString, String[] list) {
		if (list == null || list.length == 0)
			return null;
		int eachWordLength = list[0].length();
		int givenWordLength = inputString.length();
		int listLength = list.length;
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		List<Integer> result = new ArrayList<Integer>();
		for (String s : list) {
			if (!hash.containsKey(s)) {
				hash.put(s, 1);
			} else {
				hash.put(s, hash.get(s) + 1);
			}
		}

		int i = 0;
		while (givenWordLength - i >= eachWordLength * listLength) {
			HashMap<String, Integer> temp = new HashMap<String, Integer>(hash);
			for (int j = 0; j < list.length; j++) {
				String test = inputString.substring(i + j * eachWordLength, i
						+ (j + 1) * eachWordLength);
				if (temp.containsKey(test)) {
					if (temp.get(test) - 1 == 0)
						temp.remove(test);
					else
						temp.put(test, temp.get(test) - 1);
				} else {
					break;
				}
			}
			if (temp.size() == 0)
				result.add(i);
			i++;
		}
		return result;
	}

	/**
	 * Write a function which, given two integers (a numerator and a
	 * denominator), print the first N digits of a rational number. <b><br>
	 * <br>
	 * Example</b>
	 * 
	 * <pre>
	 * n/d format 
	 * 5/3 -> the result should be 1.66666 
	 * 2/3 -> the result should be 0.66
	 * </pre>
	 * 
	 * @param n
	 * @param d
	 * @return
	 */
	public String formattedDivision(int n, int d) {
		if (d == 0)
			return null;
		String result = "";
		int quotient = n / d;
		result = result + quotient + ".";
		int remainder = n % d;
		if (remainder == 0)
			return result + 0;
		int runner = 0;
		while (remainder > 0 && runner < n) {
			int q1 = (remainder * 10 / d);
			result = result + q1;
			remainder = (remainder * 10) % d;
			runner++;
		}
		return result;
	}

	/**
	 * Given an integer n, generate a square matrix filled with elements from 1
	 * to nRaised2 in spiral order. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given n = 3,generate
	 * [
	 * 	[ 1, 2, 3 ]
	 * 	[ 8, 9, 4 ]
	 * 	[ 7, 6, 5 ]
	 * ]
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public int[][] generateSpiralMatrix(int n) {
		int[][] result = new int[n][n];
		int value = 1;
		for (int level = 0, length = n; level < length; level++, length--) {
			int end = length - 1;
			// top row
			for (int i = level; i <= end; i++) {
				result[level][i] = value++;
			}
			// right column
			for (int i = level + 1; i <= end; i++) {
				result[i][end] = value++;
			}
			// bottom row
			for (int i = end - 1; i >= level; i--) {
				result[end][i] = value++;
			}
			// left column
			for (int i = end - 1; i > level; i--) {
				result[i][level] = value++;
			}
		}
		return result;
	}

	/**
	 * Given n pairs of parenthesis, write a function to generate all
	 * combinations of well-formed parenthesis. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * For example, given n = 3, a solution set is:
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<String>();
		generateParenthesisHelper(result, new StringBuilder(), n, n);
		return result;
	}

	private void generateParenthesisHelper(ArrayList<String> result,
			StringBuilder sb, int left, int right) {
		// key condition - whenever the remaining left brace count is < than
		// right, it is invalid combination
		if (left > right)
			return;
		if (left == 0 && right == 0) {
			result.add(sb.toString());
			return;
		}
		int currentLength = sb.length();
		if (left > 0) {
			generateParenthesisHelper(result, sb.append("("), left - 1, right);
		}
		sb.delete(currentLength, sb.length());
		if (right > 0) {
			generateParenthesisHelper(result, sb.append(")"), left, right - 1);
		}
		// not needed. but to be neat
		sb.delete(currentLength, sb.length());
	}

	public long getAllocatedMemory() {
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		return total;
	}

	/**
	 * Dumbo! dint write the question! Analyse and write it during freetime
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		if (n <= 0 || k <= 0)
			return "";

		// factorials of n
		int[] fact = new int[n];
		// an array of n numbers
		StringBuilder nums = new StringBuilder();
		// pre-compute factorials
		for (int i = 1; i <= n; ++i) {
			nums.append(i);
			if (i == 1)
				fact[i - 1] = 1;
			else
				fact[i - 1] = fact[i - 2] * i;
		}

		// normalize k so that it is within range [0 .. n!]
		while (k > fact[n - 1])
			k -= fact[n - 1];
		k -= 1; // convert to 0-based

		// compute the permutation
		for (int i = 0; i < n - 1; ++i) {
			int factorial = fact[n - 2 - i]; // (n-1-i)!
			int id = k / factorial + i;
			// shift the numbers
			char num = nums.charAt(id);
			for (int j = id; j > i; --j)
				nums.setCharAt(j, nums.charAt(j - 1));
			nums.setCharAt(i, num);
			while (k >= factorial)
				k -= factorial;
		}

		// convert to string
		return nums.toString();
	}

	/**
	 * The gray code is a binary numeral system where two successive values
	 * differ in only one bit. Given a non-negative integer n representing the
	 * total number of bits in the code, print the sequence of gray code. A gray
	 * code sequence must begin with 0.
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * For example, given n = 2, return [0,1,3,2]. 
	 * Its gray code sequence is:
	 * 00 - 0
	 * 01 - 1
	 * 11 - 3
	 * 10 - 2
	 * </pre>
	 * 
	 * For a given n, a gray code sequence is not uniquely defined.
	 * 
	 * @param n
	 * @return
	 */
	public List<Integer> grayCode(int n) {
		ArrayList<Integer> results = new ArrayList<Integer>(1 << n);
		results.add(0);
		// iterate from 0 to n
		for (int i = 0; i < n; ++i) {
			int flipper = 1 << i;
			// use flipper on all entries in result and add it to list
			for (int j = results.size() - 1; j >= 0; --j) {
				results.add(results.get(j) | flipper);
			}
		}
		return results;
	}

	// 371 = 3^3+7^3+1^3
	public boolean isArmstrongNumber(int num) {
		int temp = num;
		int sum = 0;
		while (temp != 0) {
			sum = sum + (int) Math.pow(temp % 10, 3);
			temp = temp / 10;
		}
		return sum == num;
	}

	/**
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary). You may assume that the intervals were
	 * initially sorted according to their start times. <b><br>
	 * <br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * Given intervals [1,3],[6,9] 
	 * 	insert and merge [2,5] in as [1,5],[6,9]
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16]
	 * 	insert and merge [4,9] in as [1,2],[3,10],[12,16].
	 * </pre>
	 * 
	 * COmplexity Analysis: O(n) time O(n) space
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public List<Interval> insertInterval(List<Interval> intervals,
			Interval newInterval) {
		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null) {
			result.add(newInterval);
			return result;
		}
		if (intervals.size() == 0) {
			result.add(newInterval);
			return result;
		}

		int index = 0;
		// add all intervals whose end are less than new interval's start
		while (index < intervals.size()
				&& intervals.get(index).end < newInterval.start) {
			result.add(intervals.get(index++));
		}
		// merge if needed
		while (index < intervals.size()
				&& intervals.get(index).start <= newInterval.end) {
			newInterval.start = Math.min(intervals.get(index).start,
					newInterval.start);
			newInterval.end = Math.max(intervals.get(index).end,
					newInterval.end);
			index++;
		}
		// add new interval
		result.add(newInterval);
		// add the remainder of the list
		while (index < intervals.size()) {
			result.add(intervals.get(index++));
		}
		return result;
	}

	public List<Interval> insertIntervalInPlace(List<Interval> intervals,
			Interval newInterval) {
		if (intervals == null || intervals.size() == 0) {
			List<Interval> result = new ArrayList<Interval>();
			result.add(newInterval);
			return result;
		}

		int index = 0;
		// add all intervals whose end are less than new interval's start
		while (index < intervals.size()
				&& intervals.get(index).end < newInterval.start) {
			index++;
		}
		// merge if needed
		int mergeStart = index;
		while (index < intervals.size()
				&& intervals.get(index).start <= newInterval.end) {
			newInterval.start = Math.min(intervals.get(index).start,
					newInterval.start);
			newInterval.end = Math.max(intervals.get(index).end,
					newInterval.end);
			index++;
		}
		// remove merged intervals from list
		while (index > mergeStart)
			intervals.remove(--index);
		intervals.add(mergeStart, newInterval);
		return intervals;
	}

	/**
	 * Determine whether an integer is a palindrome. Can use extra space
	 * 
	 * @param number
	 * @return
	 */
	public boolean isPalindrome(int number) {
		int copy = number;
		int reverse = 0;
		while (copy > 0) {
			reverse = reverse * 10 + (copy % 10);
			copy = copy / 10;

		}
		return reverse == number;
	}

	/**
	 * Determine whether an integer is a palindrome. No extra space
	 * 
	 * @param
	 * @return
	 */
	public boolean isPalindromeWithoutExtraSpace(int x) {
		if (x < 0)
			return false;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}
		while (x != 0) {
			int left = x / div;
			int right = x % 10;

			if (left != right)
				return false;

			x = (x % div) / 10;
			div /= 100;
		}
		return true;
	}

	public boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * enum to store states of the state machine
	 * 
	 * @author Karthik
	 *
	 */
	private enum isValidNumberType {
		SPACE(0), SIGN(1), DIGIT(2), DOT(3), EXP(4), NULL(-1);
		private isValidNumberType(int x) {
			type = x;
		}

		private int type;

		public int getType() {
			return type;
		}
	}

	/**
	 * Check if given number is a valid number Check the state machine and table
	 * for this problem <b><br>
	 * <br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * [+/-][digit][.]digit[e/E]digit ->valid
	 * Square brace entries are optional
	 * </pre>
	 * 
	 * @param num
	 * @return
	 */
	public boolean isValidNumber(String num) {
		int[][] states = { { 0, 8, -1, -1, 8, -1, -1, 8, 8 },
				{ 2, -1, -1, -1, -1, 6, -1, -1, -1 },
				{ 1, 1, 1, 4, 4, 7, 7, 7, -1 },
				{ 3, 4, 3, -1, -1, -1, -1, -1, -1 },
				{ -1, 5, -1, -1, 5, -1, -1, -1, -1 } };
		// initialize state to 0
		int state = 0;
		for (char c : num.toCharArray()) {
			isValidNumberType type = isValidNumberType.NULL;
			if (c == ' ') {
				type = isValidNumberType.SPACE;
			} else if (c == '+' || c == '-') {
				type = isValidNumberType.SIGN;
			} else if (c >= '0' && c <= '9') {
				type = isValidNumberType.DIGIT;
			} else if (c == '.') {
				type = isValidNumberType.DOT;
			} else if (c == 'e' || c == 'E') {
				type = isValidNumberType.EXP;
			} else
				return false;
			state = states[type.getType()][state];
			if (state < 0)
				return false;

		}
		return state == 1 || state == 4 || state == 7 || state == 8;
	}

	/**
	 * Determine if a Sudoku is valid. The Sudoku board could be partially
	 * filled, where empty cells are filled with the character '.'. A valid
	 * Sudoku board (partially filled) is not necessarily solvable. Only the
	 * filled cells need to be validated.
	 * 
	 * @param board
	 * @return
	 */
	public boolean isValidSudoku(char[][] board) {
		if (board == null)
			return false;
		int boardSize = board[0].length;
		// use three boolean arrays to check if the number is already taken for
		// a given row, column and block
		boolean isTakenInRow[][] = new boolean[boardSize][boardSize];
		boolean isTakenInColumn[][] = new boolean[boardSize][boardSize];
		boolean isTakenInBlock[][] = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				// Empty spot - ignore
				if (board[i][j] == '.')
					continue;
				int c = board[i][j] - '1';
				// The block logic is tricky. adding Quotient of both row
				// indices wont
				// help due to duplicates. So expand either row or column
				// Quotient by 3
				if (isTakenInRow[i][c] || isTakenInColumn[j][c]
						|| isTakenInBlock[(i / 3) * 3 + j / 3][c])
					return false;
				else {
					isTakenInRow[i][c] = true;
					isTakenInColumn[j][c] = true;
					isTakenInBlock[(i / 3) * 3 + j / 3][c] = true;
				}
			}
		}
		return true;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array. Each element in the array represents your
	 * maximum jump length at that position. Your goal is to reach the last
	 * index in the minimum number of jumps.
	 * 
	 * <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given array A = [2,3,1,1,4]
	 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public int jump(int[] nums) {
		if (nums == null || nums.length <= 1)
			return 0;
		int steps = 0;
		for (int currentIndex = 0, currentMaxIndex = 0, jumpedIndex = 0; currentIndex < nums.length - 1; currentIndex++) {
			currentMaxIndex = Math.max(currentMaxIndex, currentIndex
					+ nums[currentIndex]);
			if (currentIndex == jumpedIndex) {
				// for inputs like [2,3,0,0,0,0,1], its not possible to reach
				// the end
				if (currentMaxIndex == jumpedIndex)
					return -1;
				jumpedIndex = currentMaxIndex;
				steps++;
			}
		}
		return steps;
	}

	/**
	 * Given a list of non negative integers, arrange them such that they form
	 * the largest number.
	 * 
	 * <b><br>
	 * Example: </b>
	 * 
	 * <pre>
	 * given [3, 30, 34, 5, 9], 
	 * the largest formed number is 9534330.
	 * </pre>
	 * 
	 * Note: The result may be very large, so you need to return a string
	 * instead of an integer.
	 * 
	 * @param nums
	 * @return
	 */
	public String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(strs, new Comparator<String>() {
			public int compare(String s1, String s2) {
				String leftRight = s1 + s2;
				String rightLeft = s2 + s1;
				return -leftRight.compareTo(rightLeft);

			}
		});

		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s);
		}
		// for inputs like [0,0,0 ,0, 0,0 ,0 ,0 ]
		while (sb.charAt(0) == '0' && sb.length() > 1) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	public int lcmOfTwoNumbers(int a, int b) {
		if (a == 0 || b == 0) {
			System.out.println("LCM " + -1);
			return -1;
		}
		int max = a >= b ? a : b;
		int min = a < b ? a : b;
		for (int i = 1;; i++) {
			if ((max * i) % min == 0) {
				System.out.println("LCM " + max * i);
				return max * i;
			}
		}

	}

	public void listFactors(int a) {
		for (int i = 1; i <= a / 2; i++)
			if (a % i == 0)
				System.out.println(i);
	}

	public void listCommonFactors(int a, int b) {
		int min = Math.min(a, b);
		for (int i = 1; i <= min / 2; i++) {
			if (a % i == 0)
				if (b % i == 0)
					System.out.println(i);
		}
		if (a % min == 0 && b % min == 0)
			System.out.println(min);
	}

	/**
	 * Given n non-negative integers a1, a2, ..., an where each represents a
	 * point at coordinate (i, ai). <br>
	 * n vertical lines are drawn such that the two endpoints of line i is at
	 * (i, ai) and (i, 0). Find two lines, which together with x-axis forms a
	 * container, such that the container contains the most water.<br>
	 * Note: You may not slant the container.
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		if (height == null || height.length < 2)
			return 0;
		int left = 0;
		int right = height.length - 1;
		int result = Integer.MIN_VALUE;
		while (left < right) {
			int newArea = (right - left)
					* Math.min(height[left], height[right]);
			result = Math.max(result, newArea);
			if (height[left] < height[right])
				left++;
			else
				right--;

		}
		return result;
	}

	/**
	 * Given a collection of intervals, merge all overlapping intervals. <b><br>
	 * </b> Example: </b>
	 * 
	 * <pre>
	 * Given [1,3],[2,6],[8,10],[15,18],
	 * return [1,6],[8,10],[15,18].
	 * </pre>
	 * 
	 * @param intervals
	 * @return
	 */
	public List<Interval> mergeIntervals(List<Interval> intervals) {
		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0)
			return result;
		// sort the given intervals
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});

		Interval cursor = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start <= cursor.end) {
				cursor.start = Math.min(intervals.get(i).start, cursor.start);
				cursor.end = Math.max(intervals.get(i).end, cursor.end);
			} else {
				result.add(cursor);
				cursor = intervals.get(i);
			}
		}
		if (cursor != null)
			result.add(cursor);
		return result;
	}

	/**
	 * Given two numbers represented as strings, return multiplication of the
	 * numbers as a string. Note: The numbers can be arbitrarily large and are
	 * non-negative.
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		// check for 0 input
		if (num1 == null || num2 == null || num1.equals("0")
				|| num2.equals("0"))
			return "0";
		int l1 = num1.length();
		int l2 = num2.length();
		int[] n1 = new int[l1];
		// store the string number in array in reverse order
		for (int i = 0; i < l1; i++) {
			n1[l1 - i - 1] = num1.charAt(i) - '0';
		}

		int[] n2 = new int[l2];
		// store the string number in array in reverse order
		for (int i = 0; i < l2; i++) {
			n2[l2 - i - 1] = num2.charAt(i) - '0';
		}
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		// the result can be of sum of of lengths of both number. 9*9 = 81
		// vertical implementaion of multiplication
		for (int i = 0; i < l1 + l2 - 1; i++) {
			for (int j = 0; j <= i; j++) {
				if (j < l1 && (i - j) < l2) {
					sum += n1[j] * n2[i - j];
				}
			}
			sb.append((char) (sum % 10 + '0'));
			sum /= 10;
		}
		if (sum > 0)
			sb.append(sum);
		String ret = sb.reverse().toString();
		return (ret.length() == 0) ? "0" : ret;
	}

	public int myAtoi(String str) {
		if (str == null || str.length() == 0)
			return 0;
		str = trimSpaces(str);
		int overflowCheck = Integer.MAX_VALUE / 10;
		int sign = 1;
		int i = 0;
		char char0 = str.charAt(0);
		if (char0 == '-' || char0 == '+') {
			if (char0 == '-')
				sign = -1;
			i++;
		}
		int start = (int) '0';
		int end = (int) '9';
		int result = 0;
		for (; i < str.length(); i++) {
			// check if result might cause overflow when trying to left shift
			if (result > overflowCheck)
				return boundaryValues(sign);
			char c = str.charAt(i);
			int asci = (int) c;
			if (asci >= start && asci <= end) {
				int diff = asci - start;
				result = result * 10;
				// check if new number when added to left shifted result causes
				// overflow
				if (Integer.MAX_VALUE - diff < result)
					return boundaryValues(sign);
				result = result + diff;

			} else
				return sign * result;
		}
		return sign * result;
	}

	private int boundaryValues(int sign) {
		if (sign > 0)
			return Integer.MAX_VALUE;
		return Integer.MIN_VALUE;
	}

	private String trimSpaces(String str) {
		int r = 0;
		while (str.charAt(r) == ' ')
			r++;
		return str.substring(r);
	}

	/**
	 * Divide two integers without using multiplication, division and mod
	 * operator. If it is overflow, return MAX_INT.
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int myDivide(int dividend, int divisor) {
		if (divisor == 0)
			return -1;
		// fails for -1,1...if(dividend < divisor) return 0;
		if (dividend == Integer.MIN_VALUE && divisor == -1)
			return Integer.MAX_VALUE;
		long p = (long) dividend;
		long q = (long) divisor;
		p = Math.abs(p);
		q = Math.abs(q);
		int result = 0;
		while (p >= q) {
			int count = 0;
			while (p >= (q << count)) {
				count++;
			}
			result = result + (1 << (count - 1)); // put 1<< xx in paranthesis
			p = p - (q << (count - 1));// put q<< xx in paranthesis
		}
		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
			return result;
		else
			return -result;

	}

	/**
	 * Implement pow(x, n).
	 * 
	 * <b><br>
	 * <br>
	 * Logic:</b>
	 * 
	 * <pre>
	 * return 1, if n == 0;
	 * return 1/pow(x, -n), if n < 0;
	 * return x^(n/2) * x^(n/2), if n is even;
	 * return x * x^(n/2) * x^(n/2), if n is odd.
	 * </pre>
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if (n == 0)
			return 1;
		int power = Math.abs(n);
		double half = myPow(x, power >> 1);
		double result = half * half;
		// find if power is even or odd
		if ((power & 1) == 1)
			result = x * result;
		if (n < 0)
			result = 1 / result;
		return result;
	}

	/**
	 * Implement int sqrt(int x).Compute and return the square root of x.
	 * 
	 * Complexity Analysis: Time - O(logn) Space - O(1)
	 * 
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		// doesnt work for x = 2
		if (x < 2)
			return x;
		int start = 0;
		int end = x;
		while (start < end) {
			int mid = start + (end - start) / 2;
			int div = x / mid;
			if (mid == div)
				return mid;
			if (mid > div)
				end = mid;
			else
				start = mid;
		}
		return start;
	}

	/**
	 * Implement strStr(). Returns the index of the first occurrence of needle
	 * in haystack, or -1 if needle is not part of haystack.
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int myStrStr(String haystack, String needle) {
		if (haystack == null || needle == null
				|| haystack.length() < needle.length())
			return -1;
		if (needle.length() == 0)
			return 0;
		for (int i = 0; i < haystack.length()
				&& haystack.length() - i >= needle.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				for (int j = 0; j < needle.length(); j++) {
					if (haystack.charAt(i + j) != needle.charAt(j))
						break;
					else if (j == needle.length() - 1)
						return i;
				}
			}
		}
		return -1;

	}

	/**
	 * Implement next permutation, which rearranges numbers into the
	 * lexicographically next greater permutation of numbers. If such
	 * arrangement is not possible, it must rearrange it as the lowest possible
	 * order (ie, sorted in ascending order). The replacement must be in-place,
	 * do not allocate extra memory.
	 * 
	 * <b><br>
	 * <br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * 1,2,3 -> 1,3,2
	 * 3,2,1 -> 1,2,3
	 * 1,1,5 -> 1,5,1
	 * </pre>
	 * 
	 * @param nums
	 */
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int i, j = 0;
		// find a # from right that is greater than one in left
		// Find a replacement position that is close to LSB
		// eg 42002321 -> choose index 4&5 instead of 3&6
		for (i = nums.length - 2; i >= 0; i--) {
			int a = nums[i];
			int b = nums[i + 1];
			if (a >= b)
				continue;
			for (j = nums.length - 1; j > i; j--) {
				if (nums[j] > nums[i])
					break;
			}
			break;
		}
		// if above search is success, swaap those values
		if (i >= 0) {
			Sorts.swap(nums, i, j);
		}
		// After swapping, reverse the rest of the array
		for (i = i + 1, j = nums.length - 1; i < j; i++, j--) {
			Sorts.swap(nums, i, j);
		}

	}

	/**************** N queens puzzle ****************************/

	/***********************************************************************
	 * Try all permutations using backtracking
	 ***********************************************************************/
	public void nQueens(int N) {
		nQueens(new int[N], 0);
	}

	private void nQueens(int[] q, int n) {
		int N = q.length;
		if (n == N)
			constructQueenMatrix(q);
		else {
			for (int i = 0; i < N; i++) {
				q[n] = i;
				if (isConsistent(q, n))
					nQueens(q, n + 1);
			}
		}
	}

	/***********************************************************************
	 * 
	 * Return true if queen placement q[n] does not conflict with other queens
	 * q[0] through q[n-1]
	 ***********************************************************************/

	private boolean isConsistent(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n])
				return false; // same column
			if ((q[i] - q[n]) == (n - i))
				return false; // same major diagonal
			if ((q[n] - q[i]) == (n - i))
				return false; // same minor diagonal
		}
		return true;
	}

	/***********************************************************************
	 * Print out N-by-N placement of queens from permutation q in ASCII.
	 ***********************************************************************/
	private void constructQueenMatrix(int[] q) {
		int N = q.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (q[i] == j)
					System.out.print("Q ");
				else
					System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public int numberOfTrailingZeroesInFactorial(int n) {
		int count = 0;
		for (int i = 5; n / i > 0; i = i * 5)
			count += n / i;
		System.out.println(count);
		return count;
	}

	// Incomplete

	public String numberToString(int num) {
		if (num == 0)
			return "zero";
		if (num < 0)
			return "Negative " + numberToString(-1 * num);
		String str = "";
		String[] bigs = { " ", " thousand ", " million " };
		int count = 0;
		while (num > 0) {
			str = numberToString100(num % 1000) + bigs[count++] + str;

		}

		return str;
	}

	private String numberToString100(int num) {
		String str = "";
		String[] digits = { "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine" };
		String[] teens = { "eleven", "twelve", "thirteen", "fourteen",
				"fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String[] tens = { "ten", "twnety", "thirty", "forty", "fifty", "sixty",
				"seventy", "eighty", "ninety" };
		if (num == 0)
			return "";
		// if (num > 100) {
		// str = digits[num / 100 - 1] + " hundred";
		// num %= 100;
		// }
		if (num >= 11 && num < 20)
			str = str + teens[num - 11];
		else if (num % 10 == 10 || num >= 20) {
			str = str + tens[num / 10 - 1];
			num %= 10;
		}
		if (num >= 1 && num <= 9)
			str = str + digits[num - 1] + " ";

		return str;
	}

	/**
	 * Given an bunch of airline tickets with [from, to], for example [MUC,
	 * LHR], [CDG, MUC], [SFO, SJC], [LHR, SFO] please reconstruct the itinerary
	 * in order, for example: [ CDG, MUC, LHR, SFO, SJC ].
	 * 
	 * @param locationPairs
	 * @return
	 */
	public String[] orderItinerary(String[][] locationPairs) {
		int numberOfTickets = locationPairs.length;
		String[] result = new String[numberOfTickets + 1];
		int runner = 0;
		HashMap<String, String> hashMap = new HashMap<String, String>();
		HashSet<String> hashSet = new HashSet<String>();
		String currentOrigin = null;
		for (int i = 0; i < numberOfTickets; i++) {
			hashMap.put(locationPairs[i][0], locationPairs[i][1]);
			hashSet.add(locationPairs[i][1]);
		}

		for (int i = 0; i < numberOfTickets; i++) {
			currentOrigin = locationPairs[i][0];
			if (!hashSet.contains(currentOrigin))
				break;
		}
		result[runner++] = currentOrigin;
		while (hashMap.containsKey(currentOrigin)) {
			String destination = hashMap.get(currentOrigin);
			result[runner++] = destination;
			currentOrigin = destination;
		}

		return result;
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number. The digits are stored such that the most significant digit
	 * is at the head of the list.
	 * 
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			// unless digit is 9, increase the number by 1 and return
			if (digits[i] < 9) {
				digits[i] += 1;
				return digits;
			}
			digits[i] = 0;
		}
		if (digits[0] == 0) {
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			return result;
		}
		return digits;
	}

	public String printExcelColumnToAlphaAndReverse(int col) {
		int quotient = 0, remainder = 0;
		quotient = col / 26;
		remainder = col % 26;
		StringBuffer b = new StringBuffer();
		b.append((char) ((int) 'a' + remainder));
		if (quotient >= 1)
			b.append((char) ((int) 'a' + quotient - 1));
		return (b.toString());
	}

	public void printNPrimes(int n) {
		int primes = 2;
		for (int count = 0; count < n; count++) {
			while (!isPrime(primes++)) {

			}
			System.out.println(primes - 1);
		}
	}

	public void printNumbersLexicographicallly(int startingNumber,
			int endingNumber) {
		while (startingNumber <= endingNumber) {
			System.out.println(startingNumber);
			printNumbersLexicographicallly(startingNumber * 10, endingNumber);
			if (++startingNumber % 10 == 0)
				return;
		}
	}

	public int osType() {
		long temp = 1;
		int counter = 1;
		while ((temp = temp << 1) != 0)
			counter++;
		return counter;
	}

	public int rand7() {
		while (true) {
			int num = 5 * rand5() + rand5();
			if (num < 21)
				return num % 7;
		}
	}

	private int rand5() {
		return (int) (Math.random() * 5);
	}

	public int removeAllOcuurenceOfANumber(int a[], int key) {
		int left = 0;
		int right = a.length - 1;

		while (left < right) {
			while (a[left] != key)
				left++;
			while (a[right] == key)
				right--;
			int temp = a[left];
			a[left++] = a[right];
			a[right--] = temp;
		}
		for (int i : a)
			System.out.println(i);
		return right;
	}

	public int reverseTheNumber(int x) {
		int isNegative = x < 0 ? -1 : 1;
		x = Math.abs(x);
		int overflowCheck = Integer.MAX_VALUE / 10;
		int reverse = 0;
		while (x > 0) {
			if (reverse > overflowCheck)
				return 0;
			reverse = 10 * reverse + x % 10;
			x = x / 10;
		}
		return isNegative * reverse;
	}
	public int reverseTheNumber_2(int x) {		
		long result = 0;		
		boolean isNegative = x < 0;		
		x = isNegative ? x * -1 : x;		
		int divider = 10;		
		while (x > 0) {		
			result = result * 10 + x % divider;		
			x = x / divider;		
		}		
		result = result > Integer.MAX_VALUE ? 0 : result;		
		result = isNegative ? result * -1 : result;		
		return (int) result;		
	}
	/**
	 * Given a number, represent it in sum of squares <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * 101 -> 10,1<br>
	 * 198293 -> 445,16,3,1,1,1
	 * </pre>
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<Integer> representNumberInsumOfSquares(int input) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (input > 0) {
			int number = (int) Math.sqrt(input);
			result.add(number);
			System.out.print(number + " ");
			input -= (number * number);
		}

		return result;
	}

	/**
	 * Given a string containing only digits, restore it by returning all
	 * possible valid IP address combinations. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given "25525511135",
	 * return ["255.255.11.135", "255.255.111.35"]
	 * </pre>
	 * 
	 * Partition the string into two, one is 1-3 char long and the other is the
	 * rest. Recursively partition the rest part until we get all four
	 * substrings or it is not valid. A valid field of an IP address is 1-3
	 * digits (as stated above), between 0 and 255, inclusively not prefixed
	 * with "00"
	 * 
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<String>();
		int initialOctets = 4;
		restoreIpAddressesHelper(result, new StringBuilder(), s, initialOctets);
		return result;
	}

	private void restoreIpAddressesHelper(List<String> result,
			StringBuilder sb, String s, int numberOfOctets) {
		// start with 4 octets, when the recursion reaches 1, return
		if (numberOfOctets == 1) {
			if (isValidOctet(s))
				result.add(sb.toString() + s);
			return;
		}
		// start with length of 1 for each octet, when the length reaches 3
		// finish iteration
		for (int i = 1, initialLength = sb.length(); i < s.length() && i <= 3; i++) {
			String temp = s.substring(0, i);
			if (isValidOctet(temp)) {
				sb.append(temp).append(".");
				restoreIpAddressesHelper(result, sb, s.substring(i),
						numberOfOctets - 1);
			}
			sb.delete(initialLength, sb.length());
		}

	}

	/**
	 * valid octet is between 0 and 255 inclusive
	 * 
	 * @param s
	 * @return
	 */
	private boolean isValidOctet(String s) {
		// check if length is 1 for case like "0.0.0.0"
		if (s.length() == 1
				|| (s.length() > 0 && s.length() <= 3 && !s.startsWith("0"))) {
			int num = Integer.parseInt(s);
			if (num >= 0 && num <= 255)
				return true;
		}
		return false;
	}

	/**
	 * You are given an n x n 2D matrix representing an image. Rotate the image
	 * by 90 degrees (clockwise). Follow up:Could you do this in-place?
	 * 
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		printMatrix(matrix);
		for (int level = 0, end = matrix.length - 1; level < end; level++, end--) {
			for (int pos = level; pos < end; pos++) {
				// remember that the changing co-ordinate must involve pos/tail
				// always
				int tail = matrix.length - pos - 1;
				// first two involves level
				int temp = matrix[level][pos];
				matrix[level][pos] = matrix[tail][level];
				// involves both level and end
				matrix[tail][level] = matrix[end][tail];
				// last two involves end
				matrix[end][tail] = matrix[pos][end];
				matrix[pos][end] = temp;
				printMatrix(matrix);
			}

		}
	}

	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column
	 * to 0. Do it in place.
	 * 
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		boolean isFirstRowZero = false;
		boolean isFirstColumnZero = false;
		// check if first column entries contain 0
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				isFirstColumnZero = true;
				break;
			}
		}
		// check if first row entries contain 0
		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				isFirstRowZero = true;
				break;
			}
		}
		// check if 0 entries in the rest of the matrix and use first row and
		// column to keep of track of it
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		// zero the rows that contain 0 in first column
		// zero the columns that contain 0 in first row
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[0][j] == 0 || matrix[i][0] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// if first column had 0 originally, zero it
		if (isFirstColumnZero) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
		// if first row had 0 originally, zero it
		if (isFirstRowZero) {
			for (int i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}
	}

	/**
	 * Given an absolute path for a file (Unix-style), simplify it. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * path = "/home/", => "/home"
	 * path = "/a/./b/../../c/", => "/c"
	 * </pre>
	 * 
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		if (path == null)
			return null;
		String[] tokens = path.split("/");
		Stack<String> stack = new Stack<String>();
		for (String each : tokens) {
			if (each.equals("..")) {
				if (!stack.isEmpty())
					stack.pop();
			} else if (!each.isEmpty() && !each.equals(".")) {
				stack.push(each);
			}
		}
		StringBuilder sb = new StringBuilder();
		if (stack.isEmpty())
			return "/";
		while (!stack.isEmpty()) {
			sb.insert(0, "/" + stack.pop());
		}

		return sb.toString();
	}

	private static final int RED = 0;
	private static final int WHITE = 1;
	private static final int BLUE = 2;

	/**
	 * Given an array with n objects colored red, white or blue, sort them so
	 * that objects of the same color are adjacent, with the colors in the order
	 * red, white and blue. Here, we will use the integers 0, 1, and 2 to
	 * represent the color red, white, and blue respectively.
	 * 
	 * @param colors
	 */
	public void sortColors(int[] colors) {
		// one-pass for counting
		int[] count = new int[3];
		for (int color : colors) {
			count[color] = count[color] + 1;
		}

		// fill up the array
		int i = 0;
		for (; i < count[RED]; ++i)
			colors[i] = RED;
		int newWhiteBoundary = count[WHITE] + count[RED];
		for (; i < newWhiteBoundary; ++i)
			colors[i] = WHITE;
		for (; i < colors.length; ++i)
			colors[i] = BLUE;
	}

	/**
	 * Same as sortColors, do it in one pass
	 * 
	 * @param colors
	 */
	public void sortColorsOnePass(int[] colors) {
		int currentIndex = 0;
		int redBoundary = 0;
		int blueBoundary = colors.length - 1;
		while (currentIndex <= blueBoundary) {
			if (colors[currentIndex] == RED)
				Sorts.swap(colors, currentIndex++, redBoundary++);
			else if (colors[currentIndex] == BLUE) {
				// the new color could be red, so dont increment current
				Sorts.swap(colors, currentIndex, blueBoundary--);
			} else
				currentIndex++;

		}
	}

	/**
	 * Given a matrix of m x n elements (m rows, n columns), return all elements
	 * of the matrix in spiral order. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given
	 * 	[ 1, 2, 3 ]
	 * 	[ 4, 5, 6 ]
	 * 	[ 7, 8, 9 ]
	 * return [1,2,3,6,9,8,7,4,5]
	 * </pre>
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0)
			return result;
		// unlike rotate, we have to go the middle row. So declare these
		// variables outside and
		// use thier variants inside the loop viz. bottom and right
		int n = matrix[0].length;
		int m = matrix.length;

		for (int level = 0; level < m && level < n; level++, m--, n--) {
			int bottom = m - 1;
			int right = n - 1;
			// top row
			for (int i = level; i <= right; i++) {
				result.add(matrix[level][i]);
			}
			// check this condition for next loop
			if (bottom == level)
				return result;
			// right column
			for (int i = level + 1; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			// check this condition for next loop
			if (right == level)
				return result;
			// bottom row
			for (int i = right - 1; i >= level; i--) {
				result.add(matrix[bottom][i]);
			}
			// left column
			for (int i = bottom - 1; i > level; i--) {
				result.add(matrix[i][level]);
			}
		}
		return result;
	}

	public int[] tenLargestNumbersInArray(int a[]) {
		if (a.length <= 10)
			return a;
		int b[] = new int[10];
		int counter;
		for (counter = 0; counter < 10; counter++)
			b[counter] = a[counter];
		Sorts.heapSort(b);

		for (int i = counter; i < a.length; i++)
			if (b[0] < a[i]) {
				b[0] = a[i];
				Sorts.heapSort(b);
			}
		for (int i : b)
			System.out.print(i + " ");
		return b;
	}

	public char ticTacToeWinner(char[][] board) {

		int n = board.length;
		// Check for rows
		int row = 0, col = 0;
		for (row = 0; row < n; row++) {
			if (board[row][0] != ' ') {
				for (col = 1; col < n; col++) {
					if (board[row][col] != board[row][col - 1]) {
						break;
					}
				}
			}
			if (col == n)
				return board[row][0];
		}

		// Check for columns
		for (col = 0; col < n; col++) {
			if (board[0][col] != ' ') {
				for (row = 1; row < n; row++) {
					if (board[row][col] != board[row - 1][col]) {
						break;
					}
				}
			}
			if (row == n)
				return board[0][col];
		}

		// Check diagonal 1
		if (board[0][0] != ' ') {
			for (row = 1; row < n; row++) {
				if (board[row][row] != board[row - 1][row - 1])
					break;
			}
			if (row == n)
				return board[0][0];
		}

		// Check diagonal 2
		if (board[0][n - 1] != ' ') {
			for (row = 1; row < n; row++) {
				if (board[row][n - 1 - row] != board[row - 1][n - row])
					break;
			}
			if (row == n)
				return board[0][n - 1];
		}

		return ' ';

	}

	/**
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 * https://leetcode.com/problems/trapping-rain-water/
	 * </pre>
	 * 
	 * @param height
	 * @return
	 */
	public int trappedWaterVolume(int[] height) {
		if (height == null || height.length <= 1)
			return 0;
		// boundary is used for inputs like [2, 1, 0, 1] where returned volume
		// will be 0 instead of 1, thats because first current (2) will be
		// larger than the last i
		// value(1). that will get ignored.So iterate backwards to get correct
		// volume
		int[] boundary = new int[] { 0 };
		// forward iteration
		int vol = getVolume(height, height.length, boundary, true);
		// backward iteration
		vol += getVolume(height, boundary[0] - 1, boundary, false);
		return vol;
	}
	public int trappedWaterVolume_2(int[] h) {		
        if( null == h || h.length < 3) return 0;		
        int leftMax =0;		
        int rightMax[] = new int[h.length];		
        int max = 0;		
        for( int i=h.length-1;i>=0;i--){		
            max = Math.max(max,h[i]);		
            rightMax[i] = max;		
        }		
        int result=0;		
        for( int i=0;i < h.length; i++){		
            leftMax = Math.max(leftMax,h[i]);		
            result = result + Math.max(Math.min(leftMax,rightMax[i])-h[i],0);		
        }		
        return result;		
    }
	private int getVolume(int[] a, int end, int[] boundary, boolean isForward) {
		// if it is forward, the start point should be 0, else it should be end
		// of array
		int current = isForward ? 0 : a.length - 1;
		int iteratorIncrement = isForward ? 1 : -1;
		// ignore 0's at start for forward iteration
		// ignore 0's at the end for reverse iteartion
		while (current != end && a[current] == 0) {
			current += iteratorIncrement;
		}
		int vol = 0;
		// tempSum holds the volume from last visited largest value
		for (int i = current, tempSum = 0; i != end; i += iteratorIncrement) {
			if (a[i] < a[current]) {
				tempSum += a[current] - a[i];
			} else {
				vol += tempSum;
				tempSum = 0;
				current = i;
			}
		}
		// update boundary so it can be used for reverse iteartion
		boundary[0] = current;
		return vol;
	}

	// Incomplete

	/**
	 * 0 1 ? <br>
	 * ? wildcard for 0 or 1 <b><br>
	 * Example: </b>
	 * 
	 * <pre>
	 * "01?" produces 010, 011 
	 * "01?0?" produce 01000, 01100, 01001,01101
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<String> wildcard(String s) {
		return wildcardHelper(new StringBuilder(s));
	}

	private ArrayList<String> wildcardHelper(StringBuilder s) {
		ArrayList<String> list = new ArrayList<String>();
		int indexOf = s.indexOf("?");
		if (indexOf >= 0) {
			// s.replaceFirst produces another string
			// To improve performance, use charArray and pass the replaced index
			// Thus use the same char array to make new combinations
			list.addAll(wildcardHelper(s.replace(indexOf, indexOf + 1, "0")));
			list.addAll(wildcardHelper(s.replace(indexOf, indexOf + 1, "1")));
			s.replace(indexOf, indexOf + 1, "?");
		} else {
			list.add(s.toString());
			System.out.println(s);
		}
		return list;
	}
}
