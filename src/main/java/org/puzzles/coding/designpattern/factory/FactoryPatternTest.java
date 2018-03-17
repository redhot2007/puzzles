package com.designPatterns.factory;

import org.junit.Test;
import org.puzzles.coding.designpattern.factory.IShape;
import org.puzzles.coding.designpattern.factory.ShapeFactory;

/**
 * In Factory pattern, we create object without exposing the creation logic to
 * the client and refer to newly created object using a common interface .Factory
 * allows the consumer to create new objects without having to know the details
 * of how they're created, or what their dependencies are
 * 
 * @author Karthik
 *
 */
public class FactoryPatternTest {

	@Test
	public void testFactoryPattern() {
		ShapeFactory shapeFactory = new ShapeFactory();

		// get an object of Circle and call its draw method.
		IShape shape1 = shapeFactory.getShape("CIRCLE");

		// call draw method of Circle
		shape1.draw();

		// get an object of Rectangle and call its draw method.
		IShape shape2 = shapeFactory.getShape("RECTANGLE");

		// call draw method of Rectangle
		shape2.draw();

		// get an object of Square and call its draw method.
		IShape shape3 = shapeFactory.getShape("SQUARE");

		// call draw method of circle
		shape3.draw();
	}
}
