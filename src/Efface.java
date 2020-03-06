import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Efface
{
	Color color;
	
	public Efface()
	{}
	
	
	
	public Color getColor() {
		return color;
	}



	public void setColor(Color color) {
		this.color = color;
	}
	
	public void adjustAvecBackground(Vector<Trace> vectorDeSurface,Color couleurBackground) // fonction pour quand on a effacer quelquechose et on remplit le fond, la partie effacer
	{																						// devient de la même couleur du fond
		for(int index=0;index < vectorDeSurface.size();index++) // on parcour le vecteur
		{
			if(vectorDeSurface.get(index).getTypeDeElement().equals("efface")) // si c'est une tracé d'efface
			{
				vectorDeSurface.get(index).setColor(couleurBackground); // on le set au couleur du background qu'on a remplit
			}
		}
	}



	public void drawEfface(Graphics g,Trace pointD,Trace pointA) // la tracé d'efface selon les points du vecteur qui se suivent
	{
		g.drawLine(pointD.getX(),pointD.getY(),
				pointA.getX(), pointA.getY());
	}
}
