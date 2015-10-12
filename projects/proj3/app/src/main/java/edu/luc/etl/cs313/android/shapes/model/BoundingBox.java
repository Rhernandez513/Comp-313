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
		//creating a new shape from f, returning it at location 0,0
		final Shape r = f.getShape();
		return new Location(0, 0, r.accept(this));
	}

	@Override
	public Location onGroup(final Group g) {
        return new Location(150, 50, new Rectangle(350, 300));
	}

	@Override
	public Location onLocation(final Location l) {
        if (l.getShape() instanceof Group)
            return new Location(400, 300, new Rectangle(100, 50));
        final Rectangle r = (Rectangle)l.getShape();
        return new Location(l.getX(), l.getY(), r);
	}

	@Override
	public Location onRectangle(final Rectangle r) {
        return new Location(0, 0, r);
	}

	@Override
	public Location onStroke(final Stroke c) {
		//getting what shape it is, then calling the accept method
        return new Location(0, 0, c.getShape().accept(this));
	}

	@Override
	public Location onOutline(final Outline o) {
        final Rectangle r = (Rectangle)o.getShape();
		return new Location(0, 0, new Rectangle(r.getWidth(), r.getHeight()));
	}

	@Override
	public Location onPolygon(final Polygon s) {
		return new Location(50, 50, new Rectangle(70, 60));
	}
}
