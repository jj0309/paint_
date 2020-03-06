import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class TraceLibre {

	
	public void drawLeComponent(Graphics g,Trace pointDeTraceDepart,Trace PointDeTraceArrivee) // pour la tracé libre, on dessine une ligne (c'est plutôt un point) après l'ature qui se suivent
	{

			g.drawLine((int)pointDeTraceDepart.getX(),(int)pointDeTraceDepart.getY(),
					(int)PointDeTraceArrivee.getX(), (int)PointDeTraceArrivee.getY());

	}
	
}
