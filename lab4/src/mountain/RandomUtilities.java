package mountain;

public class RandomUtilities {
	public static double randFunc(double dev) {
		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
		if (Math.random() < 0.5)
			t = -t;
		return t;
	}

	public static Point offset(Point p, double dev, int order) {
		double offset = randFunc(dev) / Math.pow(2, order);
		return new Point(p.getX(), (int) (p.getY() + offset));
	}
}
