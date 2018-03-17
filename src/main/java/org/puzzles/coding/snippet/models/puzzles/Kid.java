package org.puzzles.coding.snippet.models.puzzles;

import java.util.ArrayList;
import java.util.List;

/**
 * If a canoe can hold 2 kids and a max weight of 150 lbs, write a function that returns the minimum number of canoes needed, given a list of kids and their weights.
 * @author Karthik
 *
 */
public class Kid{
	public Kid(String name, int weight){
		this.name = name;
		this.weight = weight;
	}
	public String name;
	public int weight;
	
	public static List<Kid> getKids(int[] weight){
		List<Kid> result = new ArrayList<Kid>(weight.length);
		for(int i=0;i<weight.length;i++){
			result.add(new Kid(""+i,weight[i]));
		}
		return result;
	}
}
