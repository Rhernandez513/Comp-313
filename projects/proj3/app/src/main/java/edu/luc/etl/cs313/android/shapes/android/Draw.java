package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import java.util.List;

import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

	// TODO entirely your job (except onCircle)

	private final Canvas canvas;

	private final Paint paint;

	public Draw(final Canvas canvas, final Paint paint) {
		this.canvas = canvas; // Done
		this.paint = paint; // Done
		paint.setStyle(Style.STROKE);
	}

	@Override
	public Void onCircle(final Circle c) {
		canvas.drawCircle(0, 0, c.getRadius(), paint);
		return null;
	}

	@Override
	public Void onStroke(final Stroke c) {
		//specifying the stroke color to draw the shape
		paint.setColor(c.getColor());
		// visitor moves through the decorators
		c.getShape().accept(this);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
		//set the style of paint to fill the shape
		paint.setStyle(Style.FILL);
		//visitor moves through the decorators
		f.getShape().accept(this);
		return null;
	}

	@Override
	public Void onGroup(final Group g) {
		// for all the shapes in g, have a recursive call to the draw functions
		for (Shape s: g.getShapes())
		{ s.accept(this); }
		return null;
	}

	@Override
	public Void onLocation(final Location l) {
		canvas.save();
		canvas.translate(l.getX(), l.getY());
		Shape s = l.getShape();
		s.accept(this);
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
		canvas.drawRect(0,0,r.getHeight(), r.getWidth(), paint);
		return null;
	}

	@Override
	public Void onOutline(Outline o) {
		paint.setStyle(Style.STROKE);
		o.getShape().accept(this);
		return null;
	}

	@Override
	public Void onPolygon(final Polygon s) {
		//Returning the list of points from s
		List<? extends Point> p = s.getPoints();
		//Draw a series of lines. Each pair of points is start to finish,
		//Need to draw four lines to make the polygon
		final float[] pts = {
				p.get(0).getX(), p.get(0).getY(),
				p.get(1).getX(), p.get(1).getY(),
				p.get(1).getX(), p.get(1).getY(),
				p.get(2).getX(), p.get(2).getY(),
				p.get(2).getX(), p.get(2).getY(),
				p.get(3).getX(), p.get(3).getY(),
				p.get(3).getX(), p.get(3).getY(),
				p.get(0).getX(), p.get(0).getY(),
		};
		//this method invokes the aforementioned drawing
		canvas.drawLines(pts, paint);
		return null;
	}
}
