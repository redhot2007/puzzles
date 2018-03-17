package org.puzzles.coding.snippet.programs;

import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class StringsAndArrays implements ITemplate {

	/**
	 * 
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * Let there be N words and each word has maximum M characters
	 * Time to sort each word - MLogM
	 * Time to sort N words - NMLogM
	 * HashMap retrieval and storing - O(1)
	 * ArrayList addition - O(1)
	 * Total Time - O(NMLogM)
	 * Space - O(n)
	 * </pre>
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public List<List<String>> anagramList(String s[]) {
		HashMap<String, List<String>> hash = new HashMap<String, List<String>>();
		List<List<String>> result = new ArrayList<List<String>>();
		for (String a : s) {
			char c[] = a.toCharArray();
			Arrays.sort(c);
			String key = new String(c);
			if (!hash.containsKey(key))
				hash.put(key, new ArrayList<String>());
			List<String> list = hash.get(key);
			list.add(a);
		}
		for (String key : hash.keySet()) {
			List<String> list = hash.get(key);
			if (list.size() > 1) {
				result.add(list);
			}
		}
		return result;
	}

	/**
	 * 
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - length of string
	 * Total Time - O(N)
	 * Space - O(1)
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public boolean areCharactersUnique(String s) {
		if (s == null || s.length() <= 1)
			return true;
		boolean a[] = new boolean[256]; // or use hashset
		for (int i = 0; i < s.length(); i++) {
			if (a[(int) s.charAt(i)])
				return false;
			a[(int) s.charAt(i)] = true;
		}
		return true;
	}

	/**
	 * 
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - length of array
	 * LogN comparisons are needed to find the element
	 * Time - O(LogN)
	 * Space - O(1)
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public int binarySearchRecursive(int a[], int key) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int index = binarySearchRecursive(a, 0, a.length - 1, key);
		return index;
	}

	private int binarySearchRecursive(int a[], int start, int end, int key) {
		if (start > end)
			return -1;
		int mid = start + (end - start) / 2;
		if (a[mid] == key)
			return mid;
		if (a[mid] < key)
			start = mid + 1;
		else
			end = mid - 1;
		return binarySearchRecursive(a, start, end, key);
	}

	public int binarySearchIterative(int a[], int key) {
		int result = -1;
		if (a == null)
			return result;
		int start = 0, end = a.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (a[mid] == key)
				return mid;
			if (key > a[mid])
				start = mid + 1;
			else
				end = mid - 1;
		}
		return result;
	}

	/**
	 * 
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - length of array
	 * If no duplicates LogN comparisons are needed to find the element
	 * In case of duplicates, N comparisons
	 * Time - O(N)
	 * Space - O(1)
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public int binarySearchRotatedArrayRecursive(int a[], int key) {
		if (a == null || a.length == 0)
			return -1;
		int index = binarySearchRotatedArrayRecursiveHelper(a, 0, a.length - 1,
				key);
		return index;
	}

	private int binarySearchRotatedArrayRecursiveHelper(int a[], int start,
			int end, int key) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (key == a[mid])
			return mid;
		// no equal sign in condition
		if (a[start] < a[mid]) {
			// equal for start / end
			if (a[start] <= key && key < a[mid])
				return binarySearchRotatedArrayRecursiveHelper(a, start,
						mid - 1, key);
			else
				return binarySearchRotatedArrayRecursiveHelper(a, mid + 1, end,
						key);
		} else if (a[end] > a[mid]) {
			// equal for start / end
			if (a[mid] < key && key <= a[end])
				return binarySearchRotatedArrayRecursiveHelper(a, mid + 1, end,
						key);
			else
				return binarySearchRotatedArrayRecursiveHelper(a, start,
						mid - 1, key);
		} else {
			int result = binarySearchRotatedArrayRecursiveHelper(a, start,
					mid - 1, key);
			if (result == -1)
				result = binarySearchRotatedArrayRecursiveHelper(a, mid + 1,
						end, key);
			return result;
		}

	}

	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Search a
	 * target value and return its index, if not found,return -1. You may assume
	 * no duplicate exists in the array.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int binarySearchRotatedArrayIterative(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int mid = left + (right - left) / 2;
		if (nums[mid] == target)
			return mid;
		// Note: conditions that involve left or right index comes with =
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target)
				return mid;
			// Search for ordered side of the array. Makes a binary search
			// possible
			// Note = in condition
			if (nums[left] <= nums[mid]) {
				// Note = in condition
				if (nums[left] <= target && nums[mid] > target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				// Note = in condition
				if (nums[mid] < target && nums[right] >= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

		}
		return -1;
	}

	public void commonElements(int a[], int b[]) {
		HashSet<Integer> hash = new HashSet<Integer>();
		for (int i : a)
			hash.add(i);
		for (int i : b)
			if (hash.contains(i))
				System.out.println(i);
	}

	public void commonElementsWithoutExtraSpace(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int[] result = intersectionOfSortedSets(a, b);
		for (int i : result)
			System.out.println(i);

	}

	/**
	 * Look at the sequence below:<br>
	 * 1, 11, 21, 1211, 111221, 312211 <br>
	 * Write a code that receives n and returns the nth element of the sequence. <br>
	 * If it gets 4 as input of the method it should return 1211.
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - nth sequence
	 * M - length of each sequence
	 * to compress each string O(M)
	 * Overall Time - O(MN)
	 * Space - O(MN)
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public String compressedSequence(int n) {
		String compressed = "1";
		for (int i = 0; i < n - 1; i++) {
			compressed = compressString(compressed);
		}
		return compressed;

	}

	/**
	 * Count adjacent similarities and compress a given string input 1->11 ->
	 * 21-> 1211 -> 111221
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - length of String
	 * Time - O(N)
	 * Space - O(N)
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public String compressString(String s) {
		if (s == null || s.length() == 0)
			return s;
		int curLength = 1;
		char prev = s.charAt(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == prev)
				curLength++;
			else {
				sb.append(curLength);
				sb.append(prev);
				prev = s.charAt(i);
				curLength = 1;
			}
		}
		sb.append("" + curLength + prev);
		return sb.toString();
	}
	/**
	 * Find difference of two given sets. 
	 * 
	 * <b><br> Example:</b>
	 * <pre>
	 * Given, a={1,2,3,4,5,6} b ={2,4,6,8}
	 * a-b = {1,3,5}
	 * </pre>
	 * @param a
	 * @param b
	 * @return
	 */
	public List<Integer> differenceOfSortedSets(Integer a[], Integer b[]){
		// not tested
		if( a == null) return new ArrayList<Integer>(Arrays.asList(b));
		if( b == null) return new ArrayList<Integer>(Arrays.asList(a));
		int i1 = 0, i2=0;
		List<Integer> result = new ArrayList<Integer>();
		while(i1 < a.length && i2 < b.length){
			if(a[i1] == a[i2]){
				i1++;
				i2++;
			}
			else if( a[i1] < a[i2]){
				result.add(a[i1++]);
			}
			else{
				i2++;
			}
		}
		while(i1 < a.length){
			result.add(a[i1++]);
		}
		return result;
	}
	/**
	 * Given an unsorted integer array, find the first missing positive integer.
	 * Your algorithm should run in O(n) time and uses constant space. <b><br>
	 * <br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * [1,2,0] return 3,
	 * [3,4,-1,1] return 2.
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		for (int i = 0; i < nums.length;) {
			if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1
					&& nums[i] != nums[nums[i] - 1]) {
				Sorts.swap(nums,i,nums[i]-1);
			} else
				i++;
		}
		int i = 0;
		while (i < nums.length && nums[i] == i + 1)
			i++;
		return i + 1;
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S
	 * such that a + b + c + d = target? Find all unique quadruplets in the
	 * array which gives the sum of target. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:
	 *     (-1,  0, 0, 1),(-2, -1, 1, 2), (-2,  0, 0, 2)
	 * </pre>
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - length of nums
	 * Sort - > NLogN
	 * Time - O(NLogN + N^3) = O(N^3)
	 * Space - O(1)
	 * </pre>
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashSet<List<Integer>> hash = new HashSet<List<Integer>>();
		if (nums == null || nums.length < 4)
			return result;
		Arrays.sort(nums);
		for (int i = 0; i <= nums.length - 4; i++) {
			for (int j = i + 1; j <= nums.length - 3; j++) {
				int start = j + 1;
				int end = nums.length - 1;
				while (start < end) {
					int sum = nums[i] + nums[j] + nums[start] + nums[end];
					if (sum < target)
						start++;
					else if (sum > target)
						end--;
					else {
						List<Integer> temp = new ArrayList<Integer>();
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[start]);
						temp.add(nums[end]);
						start++;
						end--;
						if (!hash.contains(temp)) {
							hash.add(temp);
							result.add(temp);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find intersection of two sorted sets
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N - longer of the two arrays, M the shorter of the array
	 * Time - O(N)
	 * Space - O(M+N)
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int[] intersectionOfSortedSets(int[] a, int b[]) {
		if (a == null || b == null)
			return null;
		List<Integer> result = new ArrayList<Integer>();
		int i1 = 0, i2 = 0;
		while (i1 < a.length && i2 < b.length) {
			if (a[i1] == b[i2]) {
				result.add(a[i1++]);
				i2++;
			} else if (a[i1] < b[i2]) {
				// increase the lesser of the two
				i1++;
			} else
				i2++;
		}
		int[] res = new int[result.size()];
		int i = 0;
		for (Integer j : result)
			res[i++] = j;
		return res;

	}

	/**
	 * Find if two strings are anagrams
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N length of longer string, M - length of shorter string
	 * Sort - NLogN,MLogM
	 * Time - O(NLogN+MLogM) = O(NLogN)
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isAnagram(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		return stringSort(s1).equals(stringSort(s2));
	}

	/**
	 * Find if two strings are anagrams
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * Time - O(N)
	 * Space - O(1)
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isAnagram2(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		int[] map = new int[256];
		for (int i = 0; i < s1.length(); i++) {
			map[s1.charAt(i)]++;
		}
		for (int i = 0; i < s2.length(); i++) {
			if (--map[(int) s2.charAt(i)] < 0)
				return false;
		}
		return true;
	}

	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and
	 * ']', determine if the input string is valid. The brackets must close in
	 * the correct order <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * Valid expressions: "()" and "()[]{}" 
	 * Invalid expressions: "(]" and "([)]"
	 * </pre>
	 * 
	 * Complexity Analysis:
	 * 
	 * <pre>
	 * N length of string
	 * Time - O(N)
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValidExpression(String s) {
		if (s == null || s.length() == 0)
			return true;
		HashMap<Character, Character> hash = new HashMap<Character, Character>();
		hash.put('(', ')');
		hash.put('[', ']');
		hash.put('{', '}');
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (hash.containsKey(c)) {
				stack.push(c);
			} else if (hash.values().contains(c)) {
				if (!stack.isEmpty() && hash.get(stack.peek()) == c)
					stack.pop();
				else
					return false;
			}

		}
		return stack.isEmpty();

	}

	/**
	 * Given a digit string, return all possible letter combinations that the
	 * number could represent in phone/mobile keypad. A mapping of digit to
	 * letters (just like on the telephone buttons) is given below.
	 * 
	 * <pre>
	 * 1 => ""		2 => "abc" 	3 => "def"
	 * 4 => "ghi"	5 => "jkl" 	6 => "mno" 
	 * 7 => "pqrs"	8 => "tuv" 	9 => "wxyz"
	 * 				0 => " "
	 * </pre>
	 * 
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * 	Input:Digit string "23"
	 * 	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 * 
	 * </pre>
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0)
			return result;
		// Make a mapping
		String[] dict = new String[] { " ", "", "abc", "def", "ghi", "jkl",
				"mno", "pqrs", "tuv", "wxyz" };
		StringBuilder sb = new StringBuilder();
		letterCombinationsHelper(digits, dict, sb, result, 0);
		return result;
	}

	private void letterCombinationsHelper(String digits, String[] dict,
			StringBuilder sb, List<String> result, int start) {
		// recurse from 0 to digitsLength and append mapped-char at currentIndex
		if (start == digits.length()) {
			result.add(sb.toString());
		} else {
			String keyPadCombination = dict[digits.charAt(start) - '0'];
			for (int i = 0; i < keyPadCombination.length(); i++) {
				char currentCharacter = keyPadCombination.charAt(i);
				sb.append(currentCharacter);
				letterCombinationsHelper(digits, dict, sb, result, start + 1);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	
	/**
	 * Longest Substring Without Repeating Characters
	 * 
	 * Given a string, find the length of the longest substring without
	 * repeating characters.
	 * 
	 * Examples:
	 * 
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 * 
	 * Given "bbbbb", the answer is "b", with the length of 1.
	 * 
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
	 * answer must be a substring, "pwke" is a subsequence and not a substring.
	 * 
	 * 
	 * @param s
	 * @return
	 */
    public int lengthOfLongestSubstring(String s) {
        int max =0, start = 0, end=0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        while(end < s.length()){
        	// why second condition is >= instead of > - example "aa"
            if(map.containsKey((int)s.charAt(end)) && map.get((int)s.charAt(end)) >= start){
                max = max > end- start ? max: end -start;
                start = map.get((int)s.charAt(end))+1;
            }else{
                map.put((int)s.charAt(end),end++);
            }
        }
        max = max > end- start ? max: end -start;
        return max;
    }
    
	/**
	 * Median of Two Sorted Arrays
	 * 
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * 
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)). 
	 * 
	 * 
	 * Example 1: nums1 = [1, 3] nums2 = [2]. The median is 2.0 
	 * Example 2: nums1 = [1, 2] nums2 = [3, 4]. The median is (2 + 3)/2 = 2.5
	 * 
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	// note the run time shud be log(m+n). for o(m+n), we can just do merge and find the middle element
        Integer l1=0;
        Integer l2=0;
        Integer r=0;
        Integer mLength= nums1.length+nums2.length;
        boolean odd = mLength%2 == 1;
        Integer medianIndex = odd? mLength/2: (mLength/2)-1;
        Integer mid;
        while(r <= medianIndex ){
            if(l1 < nums1.length && l2 < nums2.length){
                mid = nums1[l1] < nums2[l2]?nums1[l1++]:nums2[l2++];
            }else if(l1 < nums1.length){
                 mid = nums1[l1++];
            }else{
                 mid = nums2[l2++];
            }
            if( r== medianIndex){
                if(odd){
                    return mid;
                }
                else{
                    Integer temp;
                    if(l1 < nums1.length && l2 < nums2.length){
                        temp = nums1[l1] < nums2[l2]?nums1[l1++]:nums2[l2++];
                    }else if(l1 < nums1.length){
                        temp = nums1[l1++];
                    }else{
                        temp = nums2[l2++];
                    }
                    return (temp+mid)/2.0;
                }
            }
            r++;
            
        }
        // dummy return statement
        return 0;
    }
    
    private void findnextSmallest(Integer l1,Integer l2, Integer mid, int[] nums1, int[] nums2){
        if(l1 < nums1.length && l2 < nums2.length){
            mid = nums1[l1] < nums2[l2]?nums1[l1++]:nums2[l2++];
        }else if(l1 < nums1.length){
             mid = nums1[l1++];
        }else{
             mid = nums2[l2++];
        }

    }
    
	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int length = s.length();
		int lastIndex = length - 1;
		char[] cString = s.toCharArray();
		while (lastIndex >= 0 && cString[lastIndex] == ' ')
			lastIndex--;
		int lastLength = 0;
		while (lastIndex >= 0 && cString[lastIndex] != ' ') {
			lastLength++;
			lastIndex--;
		}
		return lastLength;
	}

	/**
	 * Find the longest common prefix string amongst an array of strings.
	 * Complexity - O(nm), n strings and longest string length m
	 * 
	 * @param strs
	 * @return
	 */

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		int i = 0;
		while (true) {
			if (i == strs[0].length())
				return strs[0].substring(0, i);
			char currentCompare = strs[0].charAt(i);
			for (String eachString : strs) {
				if (i == eachString.length()
						|| eachString.charAt(i) != currentCompare)
					return eachString.substring(0, i);
			}
			i++;
		}
	}

	/**
	 * A longest run is a longest substring which contains same characters
	 * 
	 * @param s
	 * @return
	 */
	public String longestRun(String s) {
		if (s == null || s.length() <= 1)
			return s;
		String longestRun = "";
		StringBuilder sb = new StringBuilder();
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(start)) {
				sb.append(s.charAt(i));
			} else {
				if (sb.length() > longestRun.length()) {
					longestRun = sb.toString();
				}
				// Stringbuiler delete deletes from start index to end index-1
				sb.delete(0, sb.length());
				sb.append(s.charAt(i));
				start = i;
			}
		}
		if (sb.length() > longestRun.length()) {
			longestRun = sb.toString();
		}
		return longestRun;
	}

	/**
	 * magic index is index that satisfies a[index] = index. Given sorted array,
	 * find such index
	 * 
	 * @param a
	 * @return
	 */
	public int magicIndex(int[] a) {
		// does not handle duplicates
		int start = 0, end = a.length - 1, result = -1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (a[mid] == mid)
				return mid;
			if (a[mid] > mid)
				end = mid - 1;
			else
				start = mid + 1;
		}
		return result;
	}

	public int magicIndex2(int[] a) {
		// handle duplicates
		return magicIndexHandle2Helper(a, 0, a.length - 1);
	}

	private int magicIndexHandle2Helper(int[] a, int start, int end) {
		if (start > end || start < 0 || end >= a.length)
			return -1;
		int mid = start + (end - start) / 2;
		if (a[mid] == mid)
			return mid;
		int left = magicIndexHandle2Helper(a, start, Math.min(a[mid], mid - 1));
		if (left >= 0)
			return left;
		int right = magicIndexHandle2Helper(a, Math.max(a[mid], mid + 1), end);
		return right;
	}

	/**
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1
	 * as one sorted array. You may assume that nums1 has enough space (size
	 * that is greater or equal to m + n) to hold additional elements from
	 * nums2. The number of elements initialized in nums1 and nums2 are m and n
	 * respectively.
	 * 
	 * @param a
	 * @param b
	 * @param l_a
	 *            , length of a
	 * @param l_b
	 *            , length of b
	 */
	public void mergeTwoSortedArrays(int a[], int b[], int l_a, int l_b) {
		if (a == null || b == null || a.length < l_a + l_b)
			return;
		int runner = l_a + l_b - 1;
		l_a--;
		l_b--;
		while (l_b >= 0) {
			if (l_a < 0 || a[l_a] < b[l_b]) {
				a[runner--] = b[l_b--];
			} else {
				a[runner--] = a[l_a--];
			}
		}
	}

	/**
	 * i18n ( 18 is number of letters between i and n in the word
	 * "internationalization" Generate all such possible seq. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * careercup =>
	 * c7p,ca6p,c6up,car5p,ca5up,care4p,car4up,caree3p,care3up...till the count is 0
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public String[] numeronyms(String s) {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(s);
		for (int i = 1; i < s.length() - 1; i++) {
			numeronymsHelper(i, s, ret);
		}
		return ret.toArray(new String[ret.size()]);

	}

	private ArrayList<String> numeronymsHelper(int num, String s,
			ArrayList<String> ret) {
		for (int i = 1; i < s.length() - num; i = i + 1) {
			String t = s.substring(0, i) + num + s.substring(i + num);
			ret.add(t);
		}
		return ret;
	}

	/**
	 * Given a collection of numbers, return all possible permutations. Check
	 * n00tcod3r for iterative solution. <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * [1,2,3] have the following permutations:
	 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		return permuteHelper(nums, 0);
	}

	private List<List<Integer>> permuteHelper(int[] nums, int start) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums.length == start) {
			result.add(new ArrayList<Integer>());
			return result;
		}
		int cur = nums[start];
		List<List<Integer>> subList = permuteHelper(nums, start + 1);
		for (List<Integer> s : subList) {
			// mainList.addAll(insertCurrent(r,cur));
			for (int i = 0; i <= s.size(); i++) {
				s.add(i, cur);
				result.add(new ArrayList<Integer>(s));
				s.remove(i);
			}
		}
		return result;
	}

	/**
	 * This permutation includes all sizes, from 0 to stringlength
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<String> permuteString(String s) {
		ArrayList<String> mainList = new ArrayList<String>();
		if (s == null)
			return null;
		if (s.length() == 0)
			mainList.add("");
		else {
			char c = s.charAt(0);
			ArrayList<String> newList = permuteString(s.substring(1));
			// this is the extra step when compared to previous question
			mainList.addAll(newList);
			for (String word : newList) {
				for (int i = 0; i <= word.length(); i++) {
					String newWord = permuteStringHelper(word, c, i);
					mainList.add(newWord);
				}
			}
		}

		return mainList;
	}

	/**
	 * Inserts character at specified index and returns new word
	 * 
	 * @param word
	 * @param c
	 * @param index
	 * @return
	 */
	private String permuteStringHelper(String word, char c, int index) {
		String first = word.substring(0, index);
		String last = word.substring(index, word.length());
		return first + c + last;
	}

	/**
	 * Find the index of pivot in a rotated sorted array. Complexity -O(logn).
	 * This algorithm does not handle duplicates. To handle we need O(n) <b><br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * {12,33,44,55,11} -> return 4
	 * {33,44,55,11,12} -> return 3
	 * {44,55,11,12,33} -> return 2
	 * {55,11,12,33,44} -> return 1
	 * {11,12,33,44,55} -> return 0
	 * </pre>
	 * 
	 * @param a
	 * @return
	 */
	public int pivotOfRotatedArray(int a[]) {
		if (a == null || a.length <= 1)
			return 0;
		int low = 0, high = a.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			// Check for no rotation
			if (a[low] < a[mid] && a[mid] < a[high])
				return low;
			// check if middle element is pivot
			// why mid>low? mid could be index 0
			if (mid > low && a[mid] < a[mid - 1])
				return mid;
			// check if middle+1 element is pivot
			if (mid < high && a[mid + 1] < a[mid])
				return mid + 1;
			// Check which side is ordered and search in the other side
			if (a[low] < a[mid])
				low = mid + 1;
			else if (a[mid] < a[high])
				high = mid - 1;
		}
		return low;
	}

	/**
	 * Given an array of numbers, print the duplicates
	 * 
	 * @param arr
	 * @return
	 */
	public void printDuplicatesWithExtraSpace(int arr[]) {
		if (arr == null || arr.length == 1)
			return;
		// Use hashmap and track count if duplicates shud be printed only once
		HashSet<Integer> hash = new HashSet<Integer>();
		for (int i = 0; i <= arr.length - 1; i++) {
			if (hash.contains(arr[i])) {
				System.out.println(arr[i]);
			} else
				hash.add(arr[i]);

		}

	}

	/**
	 * Given an array of numbers, print the duplicates
	 * 
	 * @param arr
	 * @return
	 */
	public void printDuplicatesWithoutExtraSpace(int arr[]) {
		if (arr == null || arr.length == 1)
			return;
		// Use merge sort in interview
		Arrays.sort(arr);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1])
				System.out.println(arr[i]);
		}
	}

	/**
	 * Implement regular expression matching with support for '.' and '*'.<br>
	 * '.' Matches any single character.<br>
	 * '*' Matches zero or more of the preceding element.<br>
	 * The matching should cover the entire input string (not partial). <b><br>
	 * <br>
	 * Examples:</b>
	 *
	 * <pre>
	 * isMatch("aa","a") return false
	 * isMatch("aa","aa") return true
	 * isMatch("aaa","aa") return false
	 * isMatch("aa", "a*") return true
	 * isMatch("aa", ".*") return true
	 * isMatch("ab", ".*") return true
	 * isMatch("aab", "c*a*b") return true
	 * </pre>
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean regularExpressionMatching(String s1, String s2) {
		return false;
	}

	public String removeConsecutiveDuplicates(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		char c[] = s.toCharArray();
		int currentIndex = 1;
		for (int i = 1; i < s.length(); i++)
			if (c[i] != c[i - 1])
				c[currentIndex++] = c[i];
		String returnString = new String(c, 0, currentIndex);
		return returnString;
	}

	// public String removeDuplicateCharacters(String s){
	// if(s == null || s.length()<=1)
	// return s;
	// char a[] = s.toCharArray();
	// int tail = 1, j;
	// for (int i = 1; i < a.length; i++) {
	// for (j = 0; j < tail; j++) {
	// if (a[j] == a[i])
	// break;
	// }
	// if (j == tail)
	// a[tail++] = a[i];
	// }
	// return new String(a, 0, tail);
	// }

	public String removeDuplicateCharacters(String s) {
		if (s == null || s.length() <= 1)
			return s;
		char c[] = s.toCharArray();
		HashSet<Character> hash = new HashSet<Character>();
		int runner = 1;
		hash.add(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			if (!hash.contains(s.charAt(i))) {
				hash.add(s.charAt(i));
				c[runner++] = s.charAt(i);
			}
		}

		return new String(c, 0, runner);
	}

	/**
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length. Do not allocate extra
	 * space for another array, you must do this in place with constant memory.
	 * <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * nums = [1,1,2],
	 * function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
	 * It doesn't matter what you leave beyond the new length.
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length <= 1)
			return nums.length;
		int runner = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1])
				nums[runner++] = nums[i];
		}
		return runner;
	}

	/**
	 * Given an array and a value, remove all instances of that value in place
	 * and return the new length. The order of elements can be changed. It
	 * doesn't matter what you leave beyond the new length.
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;
		int runner = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val)
				nums[runner++] = nums[i];
		}
		return runner;
	}

	public String removeExtraSpaces(String s) {
		if (s == null || s.length() == 0)
			return s;
		char c[] = s.toCharArray();
		int runner = 0;
		int i = 0;
		while (i < c.length && c[i] == ' ')
			i++;
		if (i == 0)
			i++;
		while (i < s.length()) {
			if (c[i] == ' ' && c[i - 1] == ' ') {
				i++;
			} else {
				c[runner++] = c[i++];
			}
		}
		if (runner > 0 && c[runner - 1] == ' ')
			runner--;
		return new String(c, 0, runner);
	}

	public void replaceSpaceWith(char[] s, int length) {
		int spaceCount = 0;
		for (int i = 0; i < length; i++) {
			if (s[i] == ' ')
				spaceCount++;
		}
		int runner = length + spaceCount * 2 - 1;
		length--;
		while (length >= 0) {
			if (s[length] != ' ') {
				s[runner--] = s[length--];
			} else {
				s[runner--] = '0';
				s[runner--] = '2';
				s[runner--] = '%';
				length--;
			}
		}
	}

	// Works only even occurrence of repeating numbers
	public int returnTheUniqueNumber(int a[]) {
		if (a.length == 0) {
			return -1;
		}
		int n = 0;
		for (int i : a)
			n ^= i;
		return n;

	}

	/**
	 * 
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * I am big lord -> lord big am I
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public String reverseOrderOfWordsInSentence_inPlace(String s) {
		if (s == null || s.length() == 0)
			return s;
		char c[] = s.toCharArray();
		reversal(c, 0, c.length - 1);
		int i = 0;
		while (i < s.length()) {
			while (i < s.length() && c[i] == ' ') {
				i++;
			}
			int j = i + 1;
			while (j < s.length() && c[j] != ' ') {
				j++;
			}
			reversal(c, i, j - 1);
			i = j;
		}

		return new String(c);
	}

	private void reversal(char c[], int i, int j) {
		while (j >= 0 && i < c.length && i < j) {
			char temp = c[i];
			c[i++] = c[j];
			c[j--] = temp;

		}
	}

	public String reverseOrderOfWordsInSentence_javaStyle(String s) {
		if (s == null || s.length() == 0)
			return s;
		String[] tokens = s.split(" ");
		if (tokens.length == 0)
			return s;
		StringBuffer buffer = new StringBuffer();
		for (int i = tokens.length - 1; i >= 0; i--) {
			buffer.append(tokens[i]);
			if (i > 0 && tokens[i - 1].length() > 0)
				buffer.append(" ");
		}
		return buffer.toString();
	}

	// example input "Hello World" output "olleH dlrow"
	public String reverseSentenceInPlace(String s) {
		String reversed = "";
		String[] splits = s.split(" ");
		for (String temp : splits) {
			reversed = reversed + stringRevarsal_recursive(temp) + " ";
		}
		reversed = reversed.substring(0, reversed.length() - 1);
		// System.out.println(reversed + ";");
		return reversed;
	}

	/**
	 * You are given an n x n 2D matrix representing an image. Rotate the image
	 * by 90 degrees (clockwise).
	 * 
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		int x = matrix.length;
		int y = matrix[0].length;
		int[][] result = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				result[j][x - i - 1] = matrix[i][j];
			}
		}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = result[i][j];
			}
		}
	}

	/**
	 * You are given an n x n 2D matrix representing an image. Rotate the image
	 * by 90 degrees (clockwise) in place.
	 * 
	 * @param matrix
	 */
	public void rotateInPlace(int[][] matrix) {
		for (int level = 0, len = matrix.length; level < len; ++level, --len) {
			int end = len - 1;
			for (int pos = level; pos < end; ++pos) {
				int tail = matrix.length - pos - 1;
				int tmp = matrix[level][pos];
				// left -> top
				matrix[level][pos] = matrix[tail][level];
				// bottom -> left
				matrix[tail][level] = matrix[end][tail];
				// right -> bottom
				matrix[end][tail] = matrix[pos][end];
				// top -> right
				matrix[pos][end] = tmp;
			}
		}
	}

	// Assumed to contain empty strings as list entries
	public int searchInSortedList(String s[], String key) {
		if (s == null || key == null || key == "")
			return -1;
		int index = searchInSortedListHelper(s, key, 0, s.length - 1);
		System.out.println(index);
		return index;
	}

	private int searchInSortedListHelper(String[] s, String key, int start,
			int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (s[mid].isEmpty()) {
			int left = mid - 1;
			int right = mid + 1;
			while (true) {
				if (left < start && right > end)
					return -1;
				if (!(left < start) && s[left--] != null) {
					mid = ++left;
					break;
				}
				if (!(right > end) && s[right++] != null) {
					mid = --right;
					break;
				}
			}
		}
		if (key.equals(s[mid]))
			return mid;
		else if (key.compareTo(s[mid]) > 0)
			return searchInSortedListHelper(s, key, mid + 1, end);
		else
			return searchInSortedListHelper(s, key, start, mid - 1);

	}

	/**
	 * Given a sorted array and a target value, return the index if the target
	 * is found. If not, return the index where it would be if it were inserted
	 * in order. You may assume no duplicates in the array. <b><br>
	 * <br>
	 * Examples:</b>
	 * 
	 * <pre>
	 * [1,3,5,6], 5 -> 2
	 * [1,3,5,6], 2 -> 1
	 * [1,3,5,6], 7 -> 4
	 * [1,3,5,6], 0 -> 0
	 * </pre>
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int left = 0;
		int right = nums.length - 1;
		int mid = left + (right - left) / 2;
		if (nums[mid] == target)
			return mid;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target)
				return mid;
			else if (nums[left] <= target && target < nums[mid])
				right = mid - 1;
			else
				left = mid + 1;
		}
		if (left == nums.length)
			left--;
		while (left >= 0 && nums[left] > target)
			left--;
		return left + 1;
	}

	/**
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix. This matrix has the following properties: Integers in each row
	 * are sorted from left to right. The first integer of each row is greater
	 * than the last integer of the previous row. <b><br>
	 * Example</b>
	 * 
	 * <pre>
	 * [
	 * 	[1,   3,  5,  7],
	 * 	[10, 11, 16, 20],
	 * 	[23, 30, 34, 50]
	 * ]
	 * Given target = 3, return true.
	 * </pre>
	 * 
	 * Note: other search can be done considering the entire 2D matrix as iD
	 * array and do a single binary search
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0)
			return false;
		int low = 0;
		int high = matrix.length - 1;
		// binary search the column to find which element might be
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (matrix[mid][0] == target) {
				return true;
				//check if its not there in in bottom section
			} else if (matrix[mid][0] > target) {
				high = mid - 1;
			} else if (matrix[mid + 1][0] > target) {
				// note this case which makes this modified binary search
				low = mid;
				break;
			} else
				low = mid + 1;
		}
		// binary search the row
		int row = low;
		low = 0;
		high = matrix[row].length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (matrix[row][mid] == target) {
				return true;
			}
			if (matrix[row][mid] < target)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return false;
	}

	/**
	 * Given a sorted array of integers, find the starting and ending position
	 * of a given target value. complexity must be in the order of O(log n). If
	 * the target is not found in the array, return [-1, -1]. <b><br>
	 * Example:<br>
	 * </b>
	 * 
	 * <pre>
	 * Given [5, 7, 7, 8, 8, 10] and target value 8,
	 * return [3, 4].
	 * </pre>
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return new int[] { -1, -1 };
		int left = 0;
		int right = nums.length - 1;
		int index = binarySearchIterative(nums, target);
		if (index == -1)
			return new int[] { -1, -1 };
		left = index - 1;
		right = index + 1;
		while (left >= 0 && nums[left] == target)
			left--;
		while (right < nums.length && nums[right] == target)
			right++;
		return new int[] { left + 1, right - 1 };
	}

	private Map<String, Integer> setUpDictionary(String[] words) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (String word : words) {
			word = word.toLowerCase();
			if (!word.trim().isEmpty()) {
				if (result.containsKey(word)) {
					result.put(word, result.get(word) + 1);
				} else {
					result.put(word, 0);
				}
			}
		}
		return result;
	}

	public int getFrequency(String words[], String key) {
		Map<String, Integer> dict = setUpDictionary(words);
		if (dict.containsKey(key.toLowerCase())) {
			return dict.get(key);
		}
		return 0;
	}

	public String stringRevarsal_iterative(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		int start = 0, end = str.length() - 1;
		char cString[] = str.toCharArray();
		while (start < end) {
			char temp = cString[start];
			cString[start++] = cString[end];
			cString[end--] = temp;
		}
		return new String(cString);
	}

	public String stringRevarsal_recursive(String str) {
		if (str == null || str.length() == 0)
			return str;
		return stringRevarsal_recursive(str.substring(1)) + str.charAt(0);

	}

	public String stringSort(String s) {
		if (s == null || s.length() <= 1)
			return s;
		char a[] = s.toCharArray();
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if ((int) a[i] > (int) a[j]) {
					char temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		return new String(a);
	}


	/**
	 * Given a set of distinct integers, nums, return all possible subsets.
	 * Note:Elements in a subset must be in non-descending order. The solution
	 * set must not contain duplicate subsets. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * If nums = [1,2,3], a solution is:
	 * [
	 * 	[3],
	 * 	[1],
	 * 	[2],
	 * 	[1,2,3],
	 * 	[1,3],
	 * 	[2,3],
	 * 	[1,2],
	 * 	[]
	 * ]
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsIterative(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		if (nums == null || nums.length == 0)
			return result;
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < nums.length; i++) {
			int size = result.size();
			while (size-- > 0) {
				List<Integer> temp = new ArrayList<Integer>(result.get(size));
				temp.add(nums[i]);
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * Given a set of distinct integers, nums, return all possible subsets.
	 * Note:Elements in a subset must be in non-descending order. The solution
	 * set must not contain duplicate subsets. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * If nums = [1,2,3], a solution is:
	 * [
	 * 	[3],
	 * 	[1],
	 * 	[2],
	 * 	[1,2,3],
	 * 	[1,3],
	 * 	[2,3],
	 * 	[1,2],
	 * 	[]
	 * ]
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsRecursive(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		Arrays.sort(nums);
		int startingIndex = 0;
		return subsetsRecursiveHelper(startingIndex, nums);
	}

	private List<List<Integer>> subsetsRecursiveHelper(int start, int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (start == nums.length) {
			result.add(new ArrayList<Integer>());
			return result;
		}

		List<List<Integer>> tempList = subsetsRecursiveHelper(start + 1, nums);
		for (List<Integer> eachTemp : tempList) {
			result.add(eachTemp);
			// make a clone and add, else concurrentmodification exception
			List<Integer> clone = new ArrayList<Integer>(eachTemp);
			clone.add(0, nums[start]);
			result.add(clone);
		}
		return result;
	}

	/**
	 * Given a collection of integers that might contain duplicates, nums,
	 * return all possible subsets. <b><br>
	 * Example:</b>
	 * 
	 * <pre>
	 * If nums = [1,2,2], subsets are 
	 * [
	 * 	[2],
	 * 	[1],
	 * 	[1,2,2],
	 * 	[2,2],
	 * 	[1,2],
	 * 	[]
	 * ]
	 * </pre>
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDuplicates(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		if (nums == null || nums.length == 0)
			return result;
		result.add(new ArrayList<Integer>());
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			// count duplicates
			if (i > 0 && nums[i] == nums[i - 1])
				count++;
			else
				count = 0;
			int size = result.size();
			while (size-- > 0) {
				List<Integer> temp = new ArrayList<Integer>();
				List<Integer> pre = result.get(size);
				// check for three conditions to elimitante duplicates
				if (count > 0
						&& (pre.size() < count || pre.get(pre.size() - count) != nums[i]))
					continue;
				temp.addAll(pre);
				temp.add(nums[i]);
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * Given an array of numbers, find a pair whose sum is closest to zero.
	 * 
	 * @param a
	 * @return
	 */
	public int[] sumCloseToZero(int a[]) {
		if (a.length < 2)
			return a;
		List<Integer> list = new ArrayList<Integer>();
		for (int i : a)
			list.add(i);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {

				return Math.abs(o1) - Math.abs(o2);
			}

		});
		return new int[] { list.get(0), list.get(1) };

	}

	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that
	 * a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero. Note: Elements in a triplet (a,b,c) must be in non-descending
	 * order. (ie, a <= b <= c) The solution set must not contain duplicate
	 * triplets.
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// Sort the array
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int one = -nums[i];
			// do avoid duplicates
			if (i == 0 || nums[i] > nums[i - 1]) {
				int start = i + 1;
				int end = nums.length - 1;
				// two sum logic
				while (start < end) {
					if (nums[start] + nums[end] == one) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.add(nums[i]);
						temp.add(nums[start]);
						temp.add(nums[end]);
						result.add(temp);
						start++;
						end--;
						// to avoid duplicates
						while (start < end && nums[start] == nums[start - 1])
							start++;
						while (start < end && nums[end] == nums[end + 1])
							end--;
					} else {

						if (nums[start] + nums[end] < one)
							start++;
						else
							end--;
					}

				}

			}
		}

		return result;
	}

	/**
	 * Given an array S of n integers, find three integers in S such that the
	 * sum is closest to a given number, target. Return the sum of the three
	 * integers. You may assume that each input would have exactly one solution.
	 * <b><br>
	 * <br>
	 * Example:</b>
	 * 
	 * <pre>
	 * S = {-1 2 1 -4}, and target = 1
	 * Result is 2. (-1 + 2 + 1 = 2)
	 * </pre>
	 * 
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		// if arraylength < 3, stop
		if (nums == null || nums.length < 3)
			return Integer.MIN_VALUE;
		// sort the array
		Arrays.sort(nums);
		int minDifference = Integer.MAX_VALUE;
		// use Integer -> to handle Integer.Max_vlua
		// int result = Integer.MIN_VALUE;
		Integer result = null;
		for (int i = 0; i < nums.length - 2; i++) {
			int start = i + 1;
			int end = nums.length - 1;
			// two sum logic
			while (start < end) {
				int sum = nums[i] + nums[start] + nums[end];
				if (sum == target)
					return sum;
				int diff = Math.abs(target - sum);
				// if current sum is closer to target update result
				// if (diff < minDifference) {
				if (result == null || diff < minDifference) {
					minDifference = diff;
					result = sum;
				}
				if (sum <= target)
					start++;
				else
					end--;

			}
		}
		return result;
	}

	/***************************************************************
	 * Find pairs of numbers that equal to given sum 1. use two loops -> o(n^2)
	 * 2. sort the array -> O(nlogn) take each element x, binary search for sum
	 * - x in array -> logn comparisons for n elements in array -> o(nlogn) 3.
	 * store all elements in hashMap -> O(n) take each element x, search for sum
	 * - x in hashMap -> 1 comparison for n elements 4. following method sort ->
	 * nlogn find sum of numbers picked from start and end of array -> o(n)
	 */

	public List<List<Integer>> twoSum(int a[], int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(a);
		int first = 0;
		int last = a.length - 1;
		while (first < last) {
			if ((a[first] + a[last]) == sum) {
				System.out.println(a[first] + " " + a[last]);
				List<Integer> temp = new ArrayList<Integer>();
				temp.add(a[first++]);
				temp.add(a[last--]);
				result.add(temp);
				// eliminate duplicates
				while (first < last && a[first] == a[first - 1])
					first++;
				while (first < last && a[last] == a[last + 1])
					last--;
			} else if ((a[first] + a[last]) < sum)
				first++;
			else
				last--;
		}
		return result;
	}

	/**
	 * Find union of two given sets. Note: sets dont contain repetitions.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int[] unionOfSortedSets(int[] a, int[] b) {
		if (a == null && b == null)
			return null;
		if (a == null)
			return b;
		if (b == null)
			return a;
		List<Integer> result = new ArrayList<Integer>();
		int i1 = 0, i2 = 0;
		while (i1 < a.length || i2 < b.length) {
			if (i2 == b.length || (i1 < a.length && a[i1] < b[i2])) {
				result.add(a[i1++]);
			} else if (i1 == a.length || (i2 < b.length && a[i1] > b[i2]))
				result.add(b[i2++]);
			else {
				// to eliminate duplicates in result
				result.add(a[i1++]);
				i2++;
			}
		}
		int[] res = new int[result.size()];
		int i = 0;
		for (Integer j : result)
			res[i++] = j;
		return res;
	}

	/**
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility)
	 * 
	 * <pre>
	 * 	P   A   H   N
	 * 	A P L S I I G
	 * 	Y   I   R
	 * </pre>
	 * 
	 * And then read line by line: "PAHNAPLSIIGYIR" Write the code that will
	 * take a string and make this conversion given a number of rows:<br>
	 * 
	 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3)
	 * should return "PAHNAPLSIIGYIR"
	 * 
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String zigZagConvert(String s, int numRows) {
		if (s == null || s.length() == 0 || numRows <= 1)
			return s;
		int rows = numRows - 1;
		int delta = 2 * rows;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			if (i == 0 || i == numRows - 1) {
				for (int j = i; j < s.length(); j += delta)
					sb.append(s.charAt(j));
			} else {
				rows--;
				for (int j = i; j < s.length(); j += delta) {
					sb.append(s.charAt(j));
					if (j + 2 * rows < s.length())
						sb.append(s.charAt(j + 2 * rows));
				}
			}
		}
		return sb.toString();
	}

	public static StringsAndArrays getInstance() {
		return new StringsAndArrays();
	}

}
