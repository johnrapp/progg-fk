package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

import java.util.HashMap;

public class Mountain extends Fractal {

    private Point a, b, c;
    private double dev;

    private HashMap<Side, Point> midpoints = new HashMap<>();

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

    private Point getMidpoint(Point a, Point b, int order) {
        Side s = new Side(a, b);
        Point midpoint = midpoints.get(s);
        if (midpoint == null) {
            midpoint = RandomUtilities.offset(Point.between(a, b), dev, this.order - order);
            midpoints.put(s, midpoint);
        } else {
            //midpoints.remove(s, midpoint);
        }
        return midpoint;
    }



    private void fractalTriangle(TurtleGraphics g, int order, Point a, Point b, Point c) {
        if (order == 0) {
            g.moveTo(a.getX(), a.getY());
            g.forwardTo(b.getX(), b.getY());
            g.forwardTo(c.getX(), c.getY());
            g.forwardTo(a.getX(), a.getY());
        } else {
            Point ab = getMidpoint(a, b, order);
            Point ac = getMidpoint(a, c, order);
            Point bc = getMidpoint(b, c, order);

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
