import java.awt.Graphics;

public class Triangle extends Forme{

	@Override
	public void drawForme(Trace pointDepart, Trace pointArrivee, Graphics g) { // dessine trois ligne qui se relie et qui forme un triangle 

			g.drawLine(pointDepart.getX(), pointDepart.getY(), pointArrivee.getX(), pointArrivee.getY());
			g.drawLine(pointDepart.getX(), pointDepart.getY(), (pointDepart.getX()-(pointArrivee.getX()-pointDepart.getX())), pointArrivee.getY());
			g.drawLine((pointDepart.getX()-(pointArrivee.getX()-pointDepart.getX())), pointArrivee.getY(), pointArrivee.getX(), pointArrivee.getY());
	}

}
