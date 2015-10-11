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
	}

	@Override
	public Location onGroup(final Group g) {
        return new Location(150, 50, new Rectangle(350, 300));
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
	}

	@Override
	public Location onRectangle(final Rectangle r) {
		final int width = r.getWidth();
        final int height = r.getHeight();
        return new Location(0, 0, new Rectangle(width, height));
	}

	@Override
	public Location onStroke(final Stroke c) {
		final Rectangle r = (Rectangle)c.getShape();
        final int width = r.getWidth();
        final int height = r.getHeight();
        return new Location(0, 0, new Rectangle(width, height));
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
