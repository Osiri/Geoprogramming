public class Point2D
{
	public double x;
	public double y;
	
	// Constructs an empty point;
	protected Point2D()
	{
		this(Double.NaN, Double.NaN);
	}

	// Constructs a 3D point with coordinates x, y, z;
	public Point2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	// Constructs a point from given point.
	public Point2D(Point2D p)
	{
		this.x = p.x;
		this.y = p.y;
	}
	
	// Returns the x coordinate of this point.
	public double getX() {
		return x;
	}
	
	// Returns the y coordinate of this point.
	public double getY() {
		return y;
	}
}
