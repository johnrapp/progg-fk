package mountain;

public class Point {
	private int x, y;

	/** Constructs and initializes a point at the specified (x,y) location. */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** 
	 * Returns the x coordinate. 
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/** 
	 * Returns the y coordinate. 
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		} else {
			return false;
		}
	}

	public static Point between(Point a, Point b) {
		return new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
	}

	@Override
	public int hashCode() {
	    return  31 * x + y;
	}
	
}
