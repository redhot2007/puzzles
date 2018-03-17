package org.puzzles.coding.designpattern.visitor;

class Liquor implements IVisitable {
	
	private double price;

	Liquor(double item) {
		price = item;
	}

	public double accept(IVisitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}