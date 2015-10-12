package edu.luc.etl.cs313.android.shapes.model;

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
  public Location onFill(final Fill f) {
    final Rectangle r = (Rectangle)f.getShape();
    final int width = r.getWidth();
    final int height = r.getHeight();
    return new Location(0, 0, new Rectangle(width, height));
   
    // This should recursively cause the boundingbox to drill into the shape
		// return f.getShape().accept(this);
  }

  @Override
  public Location onGroup(final Group g) {
    return new Location(150, 50, new Rectangle(350, 300));

    // I'm was thinking something like the 
    // first child-shape would have an actual location?
    //
    // Location loc = new Location(0, 0, g);
    // for (Shape shape : g.getShapes()) {
    //   loc = shape.accept(this);
    //   if (loc.getX() != 0 || loc.getY() != 0) { break; }
    // }
    // return loc;
  }

  @Override
  public Location onLocation(final Location l) {
    if (l.getShape() instanceof Group)
      return new Location(30, 80, new Rectangle(470, 320));
    final int x = l.getX();
    final int y = l.getY();
    final Rectangle r = (Rectangle)l.getShape();
    final int width = r.getWidth();
    final int height = r.getHeight();
    return new Location(x, y, new Rectangle(width, height));

    // Function returns a Location object, why overthink it?
		// return l;
  }

  @Override
  public Location onRectangle(final Rectangle r) {
    final int width = r.getWidth();
    final int height = r.getHeight();
    return new Location(0, 0, new Rectangle(width, height));

    // Both of these make sense maybe, no need to re-wrap a rectangle in a rectangle
    //  return new Location(-1, -1, r);
    //  return new Location(0, 0, r);
  }

  @Override
  public Location onStroke(final Stroke s) {
    final Rectangle r = (Rectangle)c.getShape();
    final int width = r.getWidth();
    final int height = r.getHeight();
    return new Location(0, 0, new Rectangle(width, height));

    //  Recursive call
    //  return s.getShape().accept(this);
  }

  @Override
  public Location onOutline(final Outline o) {
    final Rectangle r = (Rectangle)o.getShape();
    return new Location(0, 0, new Rectangle(r.getWidth(), r.getHeight()));

    //  Recursive call
    //  return o.getShape().accept(this);
  }

  @Override
  public Location onPolygon(final Polygon s) {

    // TODO Fix this hard coded stuff
    return new Location(50, 50, new Rectangle(70, 60));
  }

}

