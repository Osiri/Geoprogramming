import java.util.GregorianCalendar;

public class GPSPoint {

	private double x;
	private double y;
	private GregorianCalendar gc;
	
	public GPSPoint(double x, double y, GregorianCalendar gc) {
		this.x = x;
		this.y = y;
		this.gc = gc;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public GregorianCalendar getDateAndTime() {
		return gc;
	}
	
	public String toString() {
		
		String s = new String();
		
		int hrs = gc.get(GregorianCalendar.HOUR);
		if (gc.get(GregorianCalendar.AM) == 1)
			hrs += 12;
		if (hrs < 10)
			s = s + "0";
		s = s + hrs + ":";
		
		int min = gc.get(GregorianCalendar.MINUTE);
		if (min < 10)
			s = s + "0";
		s = s + min + ":";
		
		int sec = gc.get(GregorianCalendar.SECOND);
		if (sec < 10)
			s = s + "0";
		s = s + sec;
		
		return s + "   " + x + ", " + y; 
	}
	
}
