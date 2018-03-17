package org.puzzles.coding.snippet.programs;

import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;

import java.util.Random;


public class Sorts implements ITemplate {

	public void bubbleSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = 0; j < a.length - 1 - i; j++)
				if (a[j] > a[j + 1])
					swap(a, j, j + 1);
	}

	public static void heapSort(int[] a) {
		int lastIndex = a.length - 1;
		for (int i = lastIndex / 2; i >= 0; i--)
			heapify(a, i, lastIndex);
		for (int i = lastIndex; i >= 0; i--) {
			swap(a, i, 0);
			heapify(a, 0, --lastIndex);
		}
	}

	private static void heapify(int[] a, int rootIndex, int lastIndex) {
		int leftChildIndex = 2 * rootIndex;
		int rightChildIndex = 2 * rootIndex + 1;
		int largest = rootIndex;
		if (leftChildIndex <= lastIndex && a[leftChildIndex] > a[rootIndex])
			largest = leftChildIndex;
		if (rightChildIndex <= lastIndex && a[rightChildIndex] > a[largest])
			largest = rightChildIndex;
		if (largest != rootIndex) {
			swap(a, rootIndex, largest);
			heapify(a, largest, lastIndex);
		}
	}

	public static void insertionSort(int[] a) {
		if (a == null) {
			System.out.println("null");
			return;
		}
		for (int i = 1; i < a.length; i++) {
			int key = a[i];
			int j;
			for (j = i - 1; j >= 0 && key < a[j]; j--)
				a[j + 1] = a[j];
			a[j + 1] = key;
		}
	}

	public static void selectionSort(int a[]) {
		System.out.println();
		System.out.println("Selection sort output: ");
		if (a == null) {
			System.out.println("null");
			return;
		}
		for (int i = 0; i < a.length; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] > a[j]) {
					swap(a, i, j);
				}
		for (int k : a)
			System.out.print(k + " ");

	}

	public static void swap(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void mergeSort(int[] a) {
		mergeSortHelper(a, 0, a.length - 1);
	}

	private static void mergeSortHelper(int[] a, int start, int end) {
		if (start < end) {
			int middle = start + (end - start) / 2;
			mergeSortHelper(a, start, middle);
			mergeSortHelper(a, middle + 1, end);
			merge(a, start, middle, end);
		}

	}

	private static void merge(int[] a, int start, int middle, int end) {
		int helper[] = new int[a.length];
		for (int i = start; i <= end; i++)
			helper[i] = a[i];
		int i = start, j = middle + 1;
		int counter = start;
		while (i <= middle || j <= end) {
			if (j > end || (i <= middle && helper[i] <= helper[j]))
				a[counter++] = helper[i++];
			else
				a[counter++] = helper[j++];
		}
	}

	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort(int[] a, int low, int high) {
		int i = low;
		int j = high;
		int pivot = a[(low + high) / 2];
		if (i < j) {
			while (a[i] < pivot)
				i++;
			while (a[j] > pivot)
				j--;
			if (i == j)
				return;
			if (i < j) {
				swap(a, i, j);
				i++;
				j--;
			}
			if (low < j)
				quickSort(a, low, j);
			if (high > i)
				quickSort(a, i, high);
		}

	}

	public static int[] randomNumberedArrayGenerator(int size, int maxValue) {
		int a[] = new int[size];
		long seed = System.currentTimeMillis();
		Random generator = new Random(seed);
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextInt(maxValue);
		return a;
	}

}
