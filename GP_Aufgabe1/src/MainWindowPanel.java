import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class MainWindowPanel extends JPanel
{
	private static final long serialVersionUID = -3144253396928258562L;
	
	private List<GPSTrack> gpsTracks;
	
	public MainWindowPanel(List<GPSTrack> gpsTracks)
	{
		this.gpsTracks = gpsTracks; 
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);								// 

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (gpsTracks.size() == 0)		// wenn kein gps track vorhanden ist nichts zeichnen.
			return;
		
		// Beispiel aus Skript für Ellipsen:
		
		
		// Berechne Achsparalleles Rechteck aller GPS-Tracks // Für alle Punkte auswerten, die in GPS Track drin sind.
		AxisAlignedBoundingRectangle AABR = new AxisAlignedBoundingRectangle();
		for (GPSTrack t : gpsTracks)
			for (int i = 0; i < t.NumPoints(); i++)
				AABR.extendBy(t.PointN(i).getX(), t.PointN(i).getY()); //Nun brauchen wir die Methode AABR, die genau diese Funktionen zur Verfügung stellt.
		// Eventuell Construktor nehmen, der es innerhalb des Konstruktors errechnet, wäre aber unschön, wegen Verknüpfungen.
	
		// Zeichne GPS-Tracks:
		g2.fill(new Ellipse2D.Double(100.0, 100.0, 10.0, 10.0)); 		// Einen Punkt zeichnen, wenn ein gps track vorhanden ist
																		// Tipp: 95, 95, 10, 10 Wenn man Punkte zentriert haben möchte.
		
		// für jeden GPS-Tracks
		//    für jeden GPS-Punkt
		//        Transformiere GPS-Punkt-Koordinaten
		//        Zeichne GPS-Punkt
		
		
		
		// SKALIEREN:
		/* px = (px-minx) / AABB.width()*Panel.width()
		   py = py – miny
		*/
				
		for (GPSTrack t:gpsTracks){
			for(int i = 0; i < t.NumPoints(); i++){
				double ptx = t.PointN(i).getX();
				double pty = t.PointN(i).getY();
				
		// http://www.java-tutorial.org/vererbung.html		
				
		/*		
		x = (ptx.getX() - AxisAlignedBoundingRectangle.minx()) / AxisAlignedBoundingRectangle.width()*getWidth();
		y = (pty.getY() - AxisAlignedBoundingRectangle.miny()) / AxisAlignedBoundingRectangle.height()*getHeight();
		r = 0.5;
		*/		
			
		/* Bzw.: an der Tafel stand:
		x = (p.getX() - AABB.minx()) / AABB.width()*width();
		y = (p.getY() – AABB.miny())/ AABB.height()*height();
		r=0.5;
		g2.draw(new Ellipse2D.double(x-r.y-r,r+r, r+r);		
		*/		
				
			/*	
			double s;
			double sx = (PanelWidth/AABR.Width);
			double sy = (PanelHeight/AABR.Height);  //getHeight-1
			double x = (PointX – AABR.MinX)*s;
			double y = (PointY – AABR.MinY)*s;
			double s = sx < sy?sx:sy; 
			
			double sx = tX+cX;
			double sy= getHeight()-(ty+cy)-1;      // Bildschirm umklappen.
			
			
			g2.draw(new Ellipse2D.Double(x-pty-ptx,ptx+ptx, ptx+ptx, y)); 	// Skalieren ENDE	// ???? RICHTIG ?????
			
			
			cx = 0.5*(PanelWidth-(AABR*s))		// Verschieben (Fenstergröße anpassen)
			cy = 0.5*(PanelHeight-(AABR*s))		// Wenn man PanelWidth verkleinert, dann erhält man Rand (z.B.: PanelWidth(-10))
	*/
					
			} 
		}
	} 
}
		