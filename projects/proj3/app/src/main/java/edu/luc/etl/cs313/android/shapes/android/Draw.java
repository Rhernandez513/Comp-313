package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import java.util.List;
import java.util.ArrayList;

import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

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
		int tempColor = paint.getColor();
		//specifying the stroke color to draw the shape
		paint.setColor(c.getColor());
		// visitor moves through the decorators
		c.getShape().accept(this);
		paint.setColor(tempColor);
		return null;
	}


	@Override
	public Void onFill(final Fill f) {
		//set the style of paint to fill the shape
		paint.setStyle(Style.FILL_AND_STROKE);
		//visitor moves through the decorators
		f.getShape().accept(this);
		paint.setStyle(Style.STROKE);
		return null;
	}

  @Override
  public Void onGroup(final Group g) {
    // for all the shapes in g, have a recursive call to the draw functions
    for (Shape s: g.getShapes()) { s.accept(this); }
    return null;
  }

	@Override
	public Void onLocation(final Location l) {
		int x = l.getX();
		int y = l.getY();
		canvas.translate(x, y);
		Shape s = l.getShape();
		s.accept(this);
		canvas.translate(-x, -y);
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
		canvas.drawRect(0,0,r.getWidth(), r.getHeight(), paint);
		return null;
	}

	@Override
	public Void onOutline(Outline o) {
		Style tmp = paint.getStyle();
		paint.setStyle(Style.STROKE);
		o.getShape().accept(this);
		paint.setStyle(tmp);
		return null;
	}

  @Override
  public Void onPolygon(final Polygon p) {
    // Extract an array of points from the polygon
    Point[] points = p.getPoints().toArray(new Point[p.getPoints().size()+1]);

    // canvas.drawLines needs >= 4 points
    assert (points.length >= 4);

    // Add last element to first to complete drawing
    points[points.length-1] = points[0];

    // "convert" to float vals
    List<Float> float_list = new ArrayList<Float>();
    for (int i = 0; i < points.length; ++i) {
      float_list.add(new Float(points[i].getX()));
      float_list.add(new Float(points[i].getY()));
    }

    float[] floatArray = new float[float_list.size()];
    int j = 0;
    for (Float f : float_list) {
      floatArray[j++] = (f != null ? f : Float.NaN); // Or whatever default you want.
    }
    
		////Returning the list of points from s
		//List<? extends Point> p = s.getPoints();
    ////Draw a series of lines. Each pair of points is start to finish,
    ////Need to draw four lines to make the polygon
    //final float[] pts = {
    //  p.get(0).getX(), p.get(0).getY(),
    //  p.get(1).getX(), p.get(1).getY(),
    //  p.get(1).getX(), p.get(1).getY(),
    //  p.get(2).getX(), p.get(2).getY(),
    //  p.get(2).getX(), p.get(2).getY(),
    //  p.get(3).getX(), p.get(3).getY(),
    //  p.get(3).getX(), p.get(3).getY(),
    //  p.get(0).getX(), p.get(0).getY(),
    //};

    //this method invokes the aforementioned drawing
    // canvas.drawLines(pts, paint);

    // canvas.drawLines(float_list.toArray(new float[float_list.size()]), paint);
    canvas.drawLines(floatArray, paint);
    return null;
  }
}
