import java.awt.Graphics;

public class Oval extends Forme{

	@Override
	public void drawForme(Trace pointDepart, Trace pointArrivee,Graphics g) { // on dessine un oval selon les points dans le vecteur passé en paramètre
		if(pointDepart.getX()<pointArrivee.getX() && pointDepart.getY()<pointArrivee.getY()) // on dessine selon quel coté la souris se dirige
		{
			g.drawOval(pointDepart.getX(), pointDepart.getY(), pointArrivee.getX()-pointDepart.getX(),pointArrivee.getY()-pointDepart.getY());
		}
		else if(pointArrivee.getX()<pointDepart.getX() && pointDepart.getY()>pointArrivee.getY())
		{
			g.drawOval(pointArrivee.getX(), pointArrivee.getY(), pointDepart.getX()-pointArrivee.getX(),pointDepart.getY()-pointArrivee.getY());
		}
		else if(pointArrivee.getX()<pointDepart.getX() && pointArrivee.getY()>pointDepart.getY())
		{
			g.drawOval(pointArrivee.getX(), pointArrivee.getY(), pointDepart.getX()-pointArrivee.getX(),pointArrivee.getY()-pointDepart.getY());
		}
		else
			g.drawOval(pointDepart.getX(), pointDepart.getY(),pointArrivee.getX()-pointDepart.getX(),pointDepart.getY()-pointArrivee.getY());
	}

}
