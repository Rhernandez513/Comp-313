package edu.luc.etl.cs313.android.shapes.model;

/**
 * A decorator for specifying the stroke (foreground) color for drawing the
 * shape.
 */
public class Stroke implements Shape {

	protected final int color;
	protected final Shape shape;

	public Stroke(final int color, final Shape shape)
	{
		this.color = color;
		this.shape = shape;
	}

	public int getColor() {
		return this.color;
	}

	public Shape getShape() {
		return this.shape;
	}

	@Override
	public <Result> Result accept(Visitor<Result> v) {
		return v.onStroke(this);
	}
}
