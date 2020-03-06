import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class Sauvegarde {
	
	public BufferedImage recupererImage ( JPanel surface ) // retourne l'image de la surface (JPanel) passé en paramètre
  	{
	    	Dimension size = surface.getSize();
	    	BufferedImage image = new BufferedImage( size.width, 	size.height , BufferedImage.TYPE_INT_RGB);
	     Graphics2D g2 = image.createGraphics();
	     surface.paint(g2);
	     return image;
     }

}
