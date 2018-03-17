package org.puzzles.coding.snippet.models.puzzles;

public class Fraction {
	public int numerator;
	public int denominator;
	
	public Fraction(){
		
	}
	public Fraction(int numerator,int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public boolean equals(Object o){
		Fraction f = (Fraction) o;
		return f.numerator==this.numerator&& f.denominator==this.denominator;
		
	}
	
}
