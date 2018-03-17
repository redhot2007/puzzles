package org.puzzles.coding.designpattern.visitor;

class Necessity implements IVisitable {
	
	private double price;

	Necessity(double item) {
		price = item;
	}

	public double accept(IVisitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}