import java.awt.Graphics;

public class carre extends Forme {

	@Override
	public void drawForme(Trace pointDepart, Trace pointArrivee, Graphics g) { // dessin de 4 ligne qui forme un rectangle
		
		
		g.drawLine(pointDepart.getX(), pointDepart.getY(), pointArrivee.getX(), pointDepart.getY());
		g.drawLine(pointDepart.getX(), pointDepart.getY(), pointDepart.getX(), pointArrivee.getY());
		g.drawLine(pointDepart.getX(), pointArrivee.getY(), pointArrivee.getX(), pointArrivee.getY());
		g.drawLine(pointArrivee.getX(), pointDepart.getY(), pointArrivee.getX(), pointArrivee.getY());
	}

}
