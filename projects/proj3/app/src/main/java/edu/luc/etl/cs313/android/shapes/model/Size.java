package edu.luc.etl.cs313.android.shapes.model;

/**
 * A visitor to compute the number of basic shapes in a (possibly complex)
 * shape.
 */
public class Size implements Visitor<Integer> {

	// TODO entirely your job

	@Override
	public Integer onPolygon(final Polygon p) {
    // int count = 0;
    // for (Point point : p.getPoints()) {
    //   count += point.accept(this);
    // }
    // return count;

		return 1;  // Would a polygon count as one shape?
	}

	@Override
	public Integer onCircle(final Circle c) {
		return 1;
	}

	@Override
	public Integer onGroup(final Group g) {
		return g.getShapes().size(); // Group doesn't have a size method

    // int count = 0;
    // for (Shape shape : g.getShapes()) {
    //   count += shape.accept(this);
    // }
    // return count;
	}

	@Override
	public Integer onRectangle(final Rectangle q) {
		return 1;
	}

	@Override
	public Integer onOutline(final Outline o) {
    // I don't think that an outline affects size but is only a decorator
		return o.getShape().accept(this);
	}

	@Override
	public Integer onFill(final Fill f) {
    // Same as outline?
		return f.getShape().accept(this);
	}

	@Override
	public Integer onLocation(final Location l) {
		if (!(l.getShape() instanceof Group)) { return 1; }
      return 6; // I see what your doing but is Group limited to 6?

    // Same thing as outline?
		// return l.getShape().accept(this);
	}

	@Override
	public Integer onStroke(final Stroke c) {
    // Same thing as outline & location?
		return c.getShape().accept(this);
	}
}
