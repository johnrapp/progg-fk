package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

    public Point a, b, c;

    public Mountain(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getTitle() {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalTriangle(g, order, a, b, c);
    }

    public void fractalTriangle(TurtleGraphics g, int order, Point a, Point b, Point c) {
        if (order == 0) {
            g.moveTo(a.getX(), a.getY());
            g.forwardTo(b.getX(), b.getY());
            g.forwardTo(c.getX(), c.getY());
            g.forwardTo(a.getX(), a.getY());
        } else {
            Point ab = Point.middle(a, b);
            Point ac = Point.middle(a, c);
            Point bc = Point.middle(b, c);

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
