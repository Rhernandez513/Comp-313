package edu.luc.etl.cs313.android.shapes.model;

import android.graphics.Rect;

import java.util.List;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

  @Override
  public Location onCircle(final Circle c) {
		final int radius = c.getRadius();
		return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
  }

  @Override
  // This should recursively cause the boundingbox to drill into the shape
  public Location onFill(final Fill f)
  {
      return f.getShape().accept(this);
  }

  @Override
  public Location onGroup(final Group g) {
    // I'm was thinking something like the
    // first child-shape would have an actual location?
    int xleft = Integer.MAX_VALUE;
    int xright = Integer.MIN_VALUE;
    int ydown = Integer.MAX_VALUE;
    int yup = Integer.MIN_VALUE;
    List<? extends Shape> l = g.getShapes();
    for (Shape shape : l) {
      Location bb = shape.accept(this);
      Rectangle rect = (Rectangle) bb.getShape();

      xleft = Math.min(xleft, bb.getX());
      xright = Math.max(xright, bb.getX() + rect.getWidth());
      ydown = Math.min(ydown, bb.getY());
      yup = Math.max(yup, bb.getY() + rect.getHeight());
    }
    //The x and y attributes in the Location class represent the coordinates that the Shape should be drawn at.
    // In the case of a Rectangle, this would be the top left corner and then it draws to the right and down (width and height)
    return new Location(xleft, ydown, new Rectangle(xright-xleft, yup-ydown));
  }

  @Override
  public Location onLocation(final Location l) {
	  final int x = l.getX();
	  final int y = l.getY();

      Location myNewLocation = l.getShape().accept(this);
      final int nX = myNewLocation.getX();
      final int nY = myNewLocation.getY();
	  return new Location(x + nX, y + nY, myNewLocation.getShape());
  }

  @Override
  public Location onRectangle(final Rectangle r) {
    int x = r.getWidth();
    int y = r.getHeight();

    int startX = (1/2) * x;
    int startY = (1/2) * y;
    return new Location(-startX, -startY, new Rectangle(x, y));
  }

  @Override
  public Location onStroke(final Stroke s) {
    // This should recursively cause the boundingbox to drill into the shape
    return s.getShape().accept(this);
  }

  @Override
  public Location onOutline(final Outline o) {
    // This should recursively cause the boundingbox to drill into the shape
    return o.getShape().accept(this);
  }

  @Override
  public Location onPolygon(final Polygon s) {
    // TODO Fix this hard coded stuff
    int xleft = Integer.MAX_VALUE;
    int xright = Integer.MIN_VALUE;
    int ydown = Integer.MAX_VALUE;
    int yup = Integer.MIN_VALUE;
    List<? extends Point> l = s.getPoints();
    for (Point point : l) {
      xleft = Math.min(xleft, point.accept(this).getX());
      xright = Math.max(xright, point.accept(this).getX());
      ydown = Math.min(ydown, point.accept(this).getY());
      yup = Math.max(yup, point.accept(this).getY());
    }
    //The x and y attributes in the Location class represent the coordinates that the Shape should be drawn at.
    // In the case of a Rectangle, this would be the top left corner and then it draws to the right and down (width and height)
    return new Location(xleft, ydown, new Rectangle(xright-xleft, yup-ydown));
  }
}

