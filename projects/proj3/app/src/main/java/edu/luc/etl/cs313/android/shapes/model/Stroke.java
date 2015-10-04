package edu.luc.etl.cs313.android.shapes.model;

/**
 * A decorator for specifying the stroke (foreground) color for drawing the
 * shape.
 */
public class Stroke implements Shape {

	// TODO entirely your job
	private int color; //added private variable
	private Shape shape; //added private variable

	public Stroke(final int color, final Shape shape)
	{
		this.color = color; // assigned color to private variable
		this.shape = shape; // assigned shape to private variable
	}

	public int getColor() {
		return this.color;
	}

	public Shape getShape() {
		return this.shape;
	}

	@Override
	public <Result> Result accept(Visitor<Result> v) {
		return null;
	}
}
