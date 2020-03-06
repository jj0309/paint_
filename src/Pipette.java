import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pipette
{

	public Color obtenirCouleur(BufferedImage image,int x, int y) // on prend un buffered image en paramètre et les coordonné de l'emplacement dont on veut avoir la couleur
	{
		Color couleur = new Color(image.getRGB(x, y));// prend la couleur à le pixel indiqué
		return couleur;// retourne la couleur
	}
}
