package org.puzzles.coding.snippet.programs;

import org.puzzles.coding.snippet.models.TreesAndGraph.TreeNode;
import org.puzzles.coding.snippet.models.puzzles.MaxSubArrayResultNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


public class DynamicProgramming {
	/**
	 * There is a river that runs horizontally through an area. There are a set
	 * of cities on both sides of the river. Each city above the river is matched
	 * with a city below the river, and you are given this matching as a set of
	 * pairs.
	 * 
	 * You are interested in building a set of bridges across the river to
	 * connect the largest number of the matching pairs of cities, but you must
	 * do so in a way that no two bridges intersect one another.
	 * 
	 * Devise an algorithm to solve this problem as efficiently as possible.
	 * 
	 * @param cityPairs
	 * @return
	 */
	public int buildingBridges(List<int[]> cityPairs) {
		Collections.sort(cityPairs, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int b[]) {
				return a[0] - b[0];
			}
		});
		int[] inputForLongestIncreasingSubseq = new int[cityPairs.size()];
		for (int i = 0; i < cityPairs.size(); i++) {
			inputForLongestIncreasingSubseq[i] = cityPairs.get(i)[1];
		}
		return longestIncreasingSubsequence(inputForLongestIncreasingSubseq);
	}

	/**
	 * You are climbing a stair case. It takes n steps to reach to the top. Each
	 * time you can either climb 1 or 2 steps. In how many distinct ways can you
	 * climb to the top?
	 * 
	 * stair case solution for 1 or 2 or 3 steps is in CTCI running time of the
	 * recursion grows up exponentially
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairsRecursion(int n) {
		if (n <= 0)
			return 0;
		if (n == 1 || n == 2)
			return n;
		return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
	}

	// use O(n) space to store previous values O(n) time
	// this is very similar to fibonacci series
	public int climbStairsIterative(int n) {
		if (n <= 0)
			return 0;
		if (n == 1 || n == 2)
			return n;
		int[] ways = new int[n];
		ways[0] = 1;
		ways[1] = 2;
		for (int i = 2; i < n; ++i) {
			ways[i] = ways[i - 1] + ways[i - 2];
		}
		return ways[n - 1];
	}

	// need only 2 spaces to store previous values O(n) time
	public int climbStairsOptimalIterative(int n) {
		if (n <= 1)
			return n;
		int one = 1, two = 2;
		for (int i = 3; i <= n; ++i) {
			int three = one + two;
			one = two;
			two = three;
		}
		return two;
	}

	/**
	 * Given two words word1 and word2, find the minimum number of steps
	 * required to convert word1 to word2. (each operation is counted as 1
	 * step.) You have the following 3 operations permitted on a word:
	 * 
	 * a) Insert a character b) Delete a character c) Replace a character
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public int minDistance(String word1, String word2) {
		int l1 = word1.length(), l2 = word2.length();
		if (l1 == 0)
			return l2;
		if (l2 == 0)
			return l1;

		int[][] distTable = new int[l1][2];
		distTable[0][0] = (word1.charAt(0) == word2.charAt(0) ? 0 : 1);
		for (int i = 1; i < l1; ++i) {
			distTable[i][0] = Math.min(distTable[i - 1][0] + 1,
					(word1.charAt(i) == word2.charAt(0) ? i : i + 1));
		}
		for (int j = 1; j < l2; ++j) {
			int last = (j - 1) & 1, cur = j & 1;
			distTable[0][cur] = Math.min(distTable[0][last] + 1,
					(word1.charAt(0) == word2.charAt(j) ? j : j + 1));
			for (int i = 1; i < l1; ++i) {
				distTable[i][cur] = Math.min(Math.min(distTable[i][last] + 1,
						distTable[i - 1][cur] + 1), distTable[i - 1][last]
						+ (word1.charAt(i) == word2.charAt(j) ? 0 : 1));
			}
		}
		return distTable[l1 - 1][(l2 - 1) & 1];
	}

	/**
	 * 
	 * @param weight
	 * @param value
	 * @param maxWeight
	 * @return
	 */
	public List<Integer> knapSack(int[] weight, int[] value, int maxWeight) {
		int itemCount = weight.length;
		int[][] dp = new int[itemCount + 1][maxWeight + 1];
		List<Integer> result = new ArrayList<Integer>();
		boolean[][] sol = new boolean[itemCount + 1][maxWeight + 1];
		for (int item = 1; item <= itemCount; item++) {
			for (int w = 1; w <= maxWeight; w++) {
				int opt1 = dp[item - 1][w];
				int opt2 = Integer.MIN_VALUE;
				if (weight[item - 1] <= w)
					opt2 = value[item - 1] + dp[item - 1][w - weight[item - 1]];
				dp[item][w] = Math.max(opt1, opt2);
				sol[item][w] = (opt2 > opt1);
			}
		}
		// do this only if asked to retrieve the values
		for (int n = itemCount, w = maxWeight; n > 0; n--) {
			if (sol[n][w]) {
				result.add(n - 1);
				w = w - weight[n - 1];
			}
		}
		return result;
	}

	/**
	 * Given n, generate all structurally unique BST's (binary search trees)
	 * that store values 1...n.
	 * 
	 * <br>
	 * <b>Example:</b> Given n = 3, your program should return all 5 unique
	 * BST's shown below.
	 * 
	 * <pre>
	 *   1         3     3      2      1
	 *    \       /     /      / \      \
	 *     3     2     1      1   3      2
	 *    /     /       \                 \
	 *   2     1         2                 3
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public List<TreeNode> generateTrees(int n) {
		// left is 1 because, the bst start with 1, see questions
		return generateTreesHelper(1, n);
	}

	public List<TreeNode> generateTreesHelper(int left, int right) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		if (left > right) {
			result.add(null);
			return result;
		}
		for (int i = left; i <= right; i++) {
			List<TreeNode> leftList = generateTreesHelper(left, i - 1);
			List<TreeNode> rightList = generateTreesHelper(i + 1, right);
			for (TreeNode l : leftList) {
				for (TreeNode r : rightList) {
					TreeNode root = new TreeNode(i);
					root.leftChild = l;
					root.rightChild = r;
					result.add(root);
				}
			}
		}
		return result;
	}

	/**
	 * A subsequence is a sequence that can be derived from another sequence by
	 * deleting some elements without changing the order of the remaining
	 * elements. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * [A,B,D] is a subsequence of [A,B,C,D,E,F].
	 * They should not be confused with substring which is [A,B,C,D]
	 * </pre>
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public String longesCommonSubsequence(String s1, String s2) {
		if (s1 == null || s2 == null)
			return null;
		int l1 = s1.length();
		int l2 = s2.length();
		int dp[][] = new int[l1 + 1][l2 + 1];
		for (int i = l1 - 1; i >= 0; i--) {
			for (int j = l2 - 1; j >= 0; j--) {
				if (s1.charAt(i) == s2.charAt(j)) {
					// increase the diagonal element and store it
					dp[i][j] = dp[i + 1][j + 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		int i = 0, j = 0;
		StringBuilder result = new StringBuilder();
		while (i < l1 && j < l2) {
			if (s1.charAt(i) == s2.charAt(j)) {
				result.append(s1.charAt(i));
				i++;
				j++;
			} else {
				if (dp[i][j] == dp[i + 1][j])
					i++;
				else
					j++;
			}
		}
		return result.toString();

	}

	/**
	 * Find the longest increasing subsequence
	 * 
	 * <br>
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * Given [3, 4, -1, 0, 6, 2, 3]
	 * return - 4(-1,0,2,3)
	 * </pre>
	 * 
	 * Complexity Analysis: O(n) + O(n^2) + O(n)
	 * 
	 * @param a
	 * @return
	 */
	public int longestIncreasingSubsequence(int a[]) {
		int dp[] = new int[a.length];
		int[] actualSolution = new int[a.length];
		int result = -1;
		for (int i = 0; i < a.length; i++) {
			dp[i] = 1;
		}
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i]) {
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
						// set the actualSolution to point to guy before me
						actualSolution[i] = j;
					}
				}
			}
		}
		int maxIndex = 0;
		for (int i = 0; i < a.length; i++) {
			if (dp[i] > result) {
				maxIndex = i;
				result = dp[i];
			}
		}
		int t = maxIndex;
		int newT = maxIndex;
		// printing soln has some problem fix it
		do {
			t = newT;
			System.out.print(a[t] + " ");
			newT = actualSolution[t];
		} while (t != newT);
		System.out.println();
		return result;
	}

	/**
	 * Given a string containing just the characters '(' and ')', find the
	 * length of the longest valid (well-formed) parentheses substring.
	 * 
	 * <b><br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * "(()"  -longest valid parentheses substring is "()" with length = 2.
	 * ")()())" -longest valid parentheses substring is "()()" with length = 4.
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		if (s == null || s.length() <= 1)
			return 0;
		Stack<int[]> stack = new Stack<int[]>();
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			// two case - char can be '(' or ')'
			if (s.charAt(i) == '(') {
				// push the position and 0 for '(' and 1 for ')'
				stack.push(new int[] { i, 0 });
			} else {
				// two case for push - 1. empty stack
				// 2. ')' is at the top of stack
				if (stack.isEmpty() || stack.peek()[1] == 1) {
					stack.push(new int[] { i, 1 });
				} else {
					// if top of stack is '('
					stack.pop();
					int currentLength = 0;
					// two case - empty stack means entire string so far is
					// valid
					// else it is valid until the index stored in top of stack
					if (stack.isEmpty()) {
						// example full string is valid
						currentLength = i + 1;
					} else {
						currentLength = i - stack.peek()[0];
					}
					result = Math.max(result, currentLength);
				}
			}

		}
		return result;
	}

	/**
	 * Given an array of integers, find the sub array that adds up to maximum
	 * sum Implemented using Kadane's algorithm. Complexity - O(n) <b><br>
	 * Example</b>
	 * 
	 * <pre>
	 * {-1,-2,3,4,-5,6} -> start: 2 end: 5 maxValue: 8
	 * {-1,9,-2,3,4,-5,6} -> start: 1 end: 6 maxValue: 15
	 * {-1,-2,-3,1,-5,-6} -> start: 3 end: 3 maxValue: 1
	 * </pre>
	 * 
	 * @param a
	 * @return
	 */
	public MaxSubArrayResultNode maxSubArray(int a[]) {
		if (a == null || a.length == 0)
			return null;
		MaxSubArrayResultNode currentMax = new MaxSubArrayResultNode();
		currentMax.max_result = Integer.MIN_VALUE;
		MaxSubArrayResultNode overallMax = new MaxSubArrayResultNode();
		overallMax.max_result = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (currentMax.max_result <= 0) {
				currentMax.max_result = a[i];
				currentMax.start = i;
				currentMax.end = i;
			} else {
				currentMax.max_result += a[i];
				currentMax.end = i;
			}
			if (currentMax.max_result > overallMax.max_result) {
				overallMax.start = currentMax.start;
				overallMax.end = currentMax.end;
				overallMax.max_result = currentMax.max_result;
			}

		}
		return overallMax;

	}

	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its
	 * path. Note: You can only move either down or right at any point in time.
	 * 
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		if (grid[0].length == 0)
			return 0;
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// Create a sum array which contains two columns to track prev top and
		// left values
		// switch back and forth so we wouldnt need a full sized array to store
		// all prev and top
		int sum[][] = new int[rowCount][2];
		// fill first value of sum first value of grid
		sum[0][0] = grid[0][0];
		// index used to track which column of sum array is currently used
		int currentColumn = 0;
		// fill the first column of sum with first column of grid
		for (int i = 1; i < rowCount; i++) {
			sum[i][currentColumn] = sum[i - 1][currentColumn] + grid[i][0];
		}
		// fill the sum for rest of the columns in grid
		for (int i = 1; i < columnCount; i++) {
			// switch column of the sum Array
			currentColumn = 1 - currentColumn;
			// fill the sum's current column's top with sum of prev top and
			// grid's current column top
			sum[0][currentColumn] = grid[0][i] + sum[0][1 - currentColumn];
			// fill the sum array's current column for the remainder of rows in
			// grid
			for (int j = 1; j < rowCount; j++) {
				// find the min value - left or the top
				sum[j][currentColumn] = Math.min(sum[j][1 - currentColumn],
						sum[j - 1][currentColumn]);
				// add new grid value to the min value
				sum[j][currentColumn] += grid[j][i];
			}
		}
		// return sum's currentColumn's bottom
		return sum[rowCount - 1][currentColumn];
	}

	/**
	 * A message containing letters from A-Z is being encoded to numbers using
	 * the following mapping: 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded
	 * message containing digits, determine the total number of ways to decode
	 * it.
	 * 
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	 * The number of ways decoding "12" is 2.
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
		int result = 0;
		int[] count = new int[s.length() + 1];
		if (s == null || s.length() == 0)
			return result;
		count[0] = 1;
		// If A[i] is '0' and A[i-1] is neither '1' nor '2', invalid message;
		// If A[i] is '0' and A[i-1] is '1' or '2', the number of ways is the
		// number of ways of A[1..i-2];
		// If A[i] can be combined with prior letter, the number of ways is the
		// number of ways of A[1..i-1] + A[1..i-2];
		// Otherwise, it is equal to the number of ways of A[1..i-1].
		for (int i = 1; i <= s.length(); i++) {
			char c = s.charAt(i - 1);
			if (c < '0' || c > '9')
				return 0; // invalid: non-digit
			// if i-1 char is 0, valid case are 10,20... previous char(i-2)
			// should be 1 or 2
			if (c == '0') {
				// if char[i-1] is 0, it cannot the first of the input string
				if (i - 1 == 0
						|| (s.charAt(i - 2) != '1' && s.charAt(i - 2) != '2'))
					return 0;
				count[i] = count[i - 2];
			} else if (i > 1 && isValidTwoDigitCode(s.charAt(i - 2), c)) {
				count[i] = count[i - 1] + count[i - 2];
			} else {
				count[i] = count[i - 1];
			}
		}
		return count[s.length()];
	}

	private boolean isValidTwoDigitCode(char iMinus2, char iMinus1) {
		// if iMinus2 is 1, it is always true. Only if it is 2, check additional
		// cases... for letters from 20 to 26
		return (iMinus2 == '1' || (iMinus2 == '2' && iMinus1 >= '0' && iMinus1 <= '6'));
	}

	/**
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1...n? <br>
	 * <b>Example:</b> Given n = 3, your program should return all 5 unique
	 * BST's shown below.
	 * 
	 * <pre>
	 *    1         3     3      2      1
	 *     \       /     /      / \      \
	 *      3     2     1      1   3      2
	 *     /     /       \                 \
	 *    2     1         2                 3
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public int numTrees(int n) {
		return numTreesHelper(n, new HashMap<Integer, Integer>());
	}

	private int numTreesHelper(int n, HashMap<Integer, Integer> hash) {
		if (n == 0 || n == 1)
			return 1;
		int sum = 0;
		int left, right;
		for (int i = 1; i <= n; i++) {
			if (!hash.containsKey(i - 1)) {
				left = numTreesHelper(i - 1, hash);
				hash.put(i - 1, left);
			}
			left = hash.get(i - 1);
			if (!hash.containsKey(n - i)) {
				right = numTreesHelper(n - i, hash);
				hash.put(n - i, right);
			}
			right = hash.get(n - i);

			sum += left * right;
		}
		return sum;
	}

	/**
	 * A robot is located at the top-left corner of a m x n grid (marked 'S' in
	 * the diagram below). The robot can only move either down or right at any
	 * point in time. The robot is trying to reach the bottom-right corner of
	 * the grid (marked 'F' in the diagram below). How many possible unique
	 * paths are there?
	 * 
	 * <pre>
	 * s _ _ _ _ _ _ _ _ _
	 * _ _ _ _ _ _ _ _ _ _
	 * _ _ _ _ _ _ _ _ _ _
	 * _ _ _ _ _ _ _ _ _ F
	 * </pre>
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		int[][] dp = new int[m][n];
		for (int i = 0; i < n; i++)
			dp[0][i] = 1;
		for (int i = 0; i < m; i++)
			dp[i][0] = 1;
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}

	// uses less space
	public int uniquePathsOptimal(int m, int n) {
		// PathNum(i, j) = PathNum(i-1, j) + PathNum(i, j-1)
		// PathNum(i, j) represent the number of unique path from (0, 0) to (i,
		// j).

		if (m == 0 || n == 0)
			return 0;
		int x = Math.max(m, n);
		int y = Math.min(m, n);
		int[] dp = new int[y];
		dp[0] = 1;
		for (int i = 0; i < x; i++) {
			for (int j = 1; j < y; j++) {
				dp[j] += dp[j - 1];
			}
		}
		return dp[y - 1];

	}

	/**
	 * Now consider if some obstacles are added to the grids. How many unique
	 * paths would there be? An obstacle and empty space is marked as 1 and 0
	 * respectively in the grid. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
	 * [
	 * 	[0,0,0],
	 * 	[0,1,0],
	 * 	[0,0,0]
	 * ]
	 * </pre>
	 * 
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		if (n == 0)
			return 0;
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 0; i < m; i++) {
			int total = 0;
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1)
					dp[j] = 0;
				else if (j > 0)
					dp[j] += dp[j - 1];
				total += dp[j];
			}
			// if total is 0, its safe to return that no paths exist
			if (total == 0)
				return 0;
		}
		return dp[n - 1];
	}

}
