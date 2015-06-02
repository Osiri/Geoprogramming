
public class GPSTrack {

	private GPSPoint[] pts;
	
	public GPSTrack(GPSPoint[] pts)
	{
		this.pts = pts;
	}
	
	public int NumPoints()
	{
		return pts.length;
	}
	
	public GPSPoint PointN(int n)
	{
		return pts[n];
	}
	
}
