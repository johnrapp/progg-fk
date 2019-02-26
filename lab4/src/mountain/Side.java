package mountain;

public class Side {
    public Point a,b;

    public Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Side)) {
            return  false;
        }
        Side s = (Side) obj;

        return (s.a == this.a && s.b == this.b) || (s.b == this.a && s.a == this.b);
    }
}
