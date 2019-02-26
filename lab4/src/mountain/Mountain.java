package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

    public Point a, b, c;
    public double dev;

    public Mountain(Point a, Point b, Point c, double dev) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.dev = dev;
    }

    @Override
    public String getTitle() {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalTriangle(g, order, a, b, c);
    }

    private void fractalTriangle(TurtleGraphics g, int order, Point a, Point b, Point c) {
        if (order == 0) {
            g.moveTo(a.getX(), a.getY());
            g.forwardTo(b.getX(), b.getY());
            g.forwardTo(c.getX(), c.getY());
            g.forwardTo(a.getX(), a.getY());
        } else {
            Point ab = RandomUtilities.offset(Point.between(a, b), dev, order);
            Point ac = RandomUtilities.offset(Point.between(a, c), dev, order);
            Point bc = RandomUtilities.offset(Point.between(b, c), dev, order);

            // Top
            fractalTriangle(g, order - 1, ab, b, bc);
            // Middle
            fractalTriangle(g, order - 1, ab, ac, bc);
            // Left
            fractalTriangle(g, order - 1, a, ab, ac);
            // Right
            fractalTriangle(g, order - 1, ac, bc, c);
        }
    }
}
