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
    // This should recursively cause the boundingbox to drill into the shape
    return f.getShape().accept(this);
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
    return l; 
  }

  @Override
  public Location onRectangle(final Rectangle r) {
    return new Location(r.getWidth(), -r.getHeight(), r); 
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
    return new Location(50, 50, new Rectangle(70, 60));
  }
}

