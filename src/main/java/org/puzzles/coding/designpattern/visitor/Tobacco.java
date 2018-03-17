package org.puzzles.coding.designpattern.visitor;

class Tobacco implements IVisitable {
	
	private double price;

	Tobacco(double item) {
		price = item;
	}

	public double accept(IVisitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}
	
}