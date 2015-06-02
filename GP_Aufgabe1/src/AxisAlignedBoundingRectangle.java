
public class AxisAlignedBoundingRectangle {

	double minx = Double.MAX_VALUE;	// Hier muss etwas stehen, dass auf jeden Fall eine ungültige Koordinate ist.
	double maxx = -Double.MAX_VALUE;
	double miny = Double.MIN_VALUE;
	double maxy = -Double.MIN_VALUE;

	boolean isValid(){						// Wenn sich der ungültige Wert verändert hat, ist es nun ein gültiger Wert
										// Bzw. Test, ob überhaupt Daten vorhanden sind oder nicht.
		return minx!=
				Double.MAX_VALUE;
	}
		
	// Ab hier AABR Code schreiben?
	/* extendBy methode
	 * breite
	 * mitte
	 * ob Punkt innerhalb BoundingBox
	 * ob BB gültig
	 */

	public double minx() {
		return 0; 		// Returniert Minx
	}
	
	public double maxx() {
		return 0; 		// Returniert Maxx
	}
	
	public double miny() {
		return 0; 		// Returniert Miny
	}
	
	public double maxy() {
		return 0; 		// Returniert Maxy
	}
	
	public double width() {
		return maxx - minx; 		// Returniert Breite		
	}
	
	public double height() {
		return maxy - miny; 		// Returniert Höhe
	}
	
	public void extendBy(double x, double y){ 	// ExtendBy Methode
	
	if (x < minx){
		minx = x;
	}	
	if (y < miny){
		miny = y;
	}
	if (x > maxx){
		maxx = x;
	}	
	if ( y > maxy){
		maxy = y;
	}
	}
	}		// Klassenende


	/*	If ((A.X + A.Width)>= (B.X)); 	// Dies wäre ein Kollisionstest ( Quelle: https://www.youtube.com/watch?v=8b_reDI7iPM )
			(A.X) <= (B.X + B.Width) && 
			(A.Y + A.Height) >= (B.Y) && 
			(A.Y) <= (B.Y + B.Height));     */
	
	/* Oder etwas wie: 
	public AABR(double minX, double minY, double maxX, double maxY){
	set(minX, minY, maxX, maxY);
	}
	public AABR(Point2D min, Point2D max)
	{
	set(min, max);
	}
	*/
