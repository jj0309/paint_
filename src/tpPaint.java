import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.GridLayout;
/*Auteur: Ka-son Chau
 * Travail: paint sur java avec crayon, efface, remplir,pipette, 
 * date: 20/03/2019
 * */
public class tpPaint extends JFrame {
	private JPanel panel_Dessin;
	private JToggleButton buttonCrayon;
	private JToggleButton buttonEfface;
	private JToggleButton buttonColorTaker;
	private JToggleButton buttonRemplir;
	private JButton buttonSauvegarde;
	private JToggleButton buttonSquare;
	private JToggleButton buttonCercle;
	private JToggleButton buttonTriangle;
	private JToggleButton buttonColor1;
	private JToggleButton buttonColor2;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JPanel panel_Option;
	private JPanel panel_Geo;
	private JPanel panel_ColorChoices;
	private JPanel panel_Colors;

	/* deux vecteur
	 *  vectorDeToutElement est un vecteur qui contient tout les éléments de la surface dessins.
	 *  le vecteur prototypeDeForme est un vecteur qui permet de contenir les points de tracées pour faire un "preview" des tracées géométrique
	 * */
	private Vector<Trace> vectorDeToutElement = new Vector<Trace>();
	private Vector<Trace> prototypeDeForme = new Vector<Trace>();


	private int tailleDeLigne = 5; // pour la taille de ligne 5 par défaut au début du logiciel
	private Color backGroundColor; // pour la couleur du background
	private Color colorDeLigne; // pour la couleur des tracées
	
	/*Initialisation pour faire appelle au fonctions de ces class plus tard*/
	TraceLibre lignes = new TraceLibre();
	Efface efface = new Efface();
	Sauvegarde laSauvegarde = new Sauvegarde();
	carre leCarre = new carre();
	Oval cercle = new Oval();
	Triangle triangle = new Triangle();

	
	private EcouteurMouseMoved ecMoved; // ecouteurs de sourie pour la surface de dessin
	Ecouteurclicked ecouteurClicked;
	private Ecouteur ec; // ecouteur event pour la taille de texte
	private EcouteurPourCouleur ecCouleur; // ecouteur souris pour quand on click sur une couleur
	private JLabel colorBlue;
	private JLabel colorNoir;
	private JLabel colorRouge;
	private JLabel colorBlanc;
	private JLabel colorCyan;
	private JLabel colorViolet;
	private JLabel colorGreen;
	private JLabel colorOrange;
	private JLabel colorRose;
	private JLabel colorJaune;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tpPaint frame = new tpPaint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tpPaint() {
		setBounds(0, 0, 900, 545);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setTitle("Java Paint"); // titre de l'application
		
		panel_Dessin = new JPanel();
		panel_Dessin.setBackground(Color.WHITE); // panel de dessin commence initiallament avec un arriere plan blanc
		panel_Dessin.setBounds(10, 109, 864, 386);
		getContentPane().add(panel_Dessin);
		
		// pour les images des buttons
		ImageIcon Crayon = new ImageIcon("icones/crayon.gif");
		
		ImageIcon Efface = new ImageIcon("icones/efface.gif");
		
		ImageIcon Pipette = new ImageIcon("icones/pipette.gif");
		
		ImageIcon Remplissage = new ImageIcon("icones/remplissage.gif");
		
		ImageIcon Sauvegarde = new ImageIcon("icones/save.gif");
		
		ImageIcon Square = new ImageIcon("icones/rectangle.gif");
		
		ImageIcon Cercle = new ImageIcon("icones/cercle.gif");
		
		ImageIcon Triangle = new ImageIcon("icones/triangle.gif");
		
		lblNewLabel = new JLabel("Taille du trait:");
		lblNewLabel.setBounds(16, 69, 105, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 63, 122, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		// creation des groupes de buttons
		ButtonGroup groupButton = new ButtonGroup(); // groupe pour le choix de dessin
		ButtonGroup groupButton3 = new ButtonGroup(); // groupe pour toggle buttons couleur 1 et couleur 2
		
		panel_Option = new JPanel();
		panel_Option.setBounds(0, 18, 256, 51);
		getContentPane().add(panel_Option);
		panel_Option.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonCrayon = new JToggleButton(Crayon);
		panel_Option.add(buttonCrayon);
		groupButton.add(buttonCrayon);
		buttonEfface = new JToggleButton(Efface);
		panel_Option.add(buttonEfface);
		groupButton.add(buttonEfface);
		buttonColorTaker = new JToggleButton(Pipette);
		panel_Option.add(buttonColorTaker);
		groupButton.add(buttonColorTaker);
		buttonRemplir = new JToggleButton(Remplissage);
		panel_Option.add(buttonRemplir);
		groupButton.add(buttonRemplir);
		
		panel_Geo = new JPanel();
		panel_Geo.setBounds(325, 18, 211, 67);
		getContentPane().add(panel_Geo);
		buttonSquare = new JToggleButton(Square);
		panel_Geo.add(buttonSquare);
		groupButton.add(buttonSquare);
		buttonCercle = new JToggleButton(Cercle);
		panel_Geo.add(buttonCercle);
		groupButton.add(buttonCercle);
		buttonTriangle = new JToggleButton(Triangle);
		panel_Geo.add(buttonTriangle);
		groupButton.add(buttonTriangle);
		
		panel_ColorChoices = new JPanel();
		panel_ColorChoices.setBounds(516, 5, 155, 60);
		getContentPane().add(panel_ColorChoices);
		
		buttonColor1 = new JToggleButton("");
		buttonColor1.setPreferredSize(new Dimension(50, 50));
		panel_ColorChoices.add(buttonColor1);
		groupButton3.add(buttonColor1);
		
		buttonColor2 = new JToggleButton("");
		buttonColor2.setPreferredSize(new Dimension(50, 50));
		panel_ColorChoices.add(buttonColor2);
		groupButton3.add(buttonColor2);
		
		panel_Colors = new JPanel();
		panel_Colors.setPreferredSize(new Dimension(40, 40));
		panel_Colors.setBounds(676, 20, 178, 76);
		getContentPane().add(panel_Colors);
		panel_Colors.setLayout(new GridLayout(0, 5, 0, 0));
		
		ecouteurClicked = new Ecouteurclicked();
		ecCouleur = new EcouteurPourCouleur();
		
		
		// les couleurs sont des jlabels transparant avec une couleur de fond
		colorJaune = new JLabel("");
		colorJaune.setBackground(Color.YELLOW);
		colorJaune.setOpaque(true);
		colorJaune.addMouseListener(ecCouleur); // on ajoute l'écouteur dédié à la section de couleur aux couleurs
		panel_Colors.add(colorJaune);
		
		colorRose = new JLabel("");
		colorRose.setBackground(Color.PINK);
		colorRose.setOpaque(true);
		colorRose.addMouseListener (ecCouleur);
		panel_Colors.add(colorRose);
		
		colorOrange = new JLabel("");
		colorOrange.setBackground(Color.ORANGE);
		colorOrange.setOpaque(true);
		colorOrange.addMouseListener(ecCouleur);
		panel_Colors.add(colorOrange);
		
		colorGreen = new JLabel("");
		colorGreen.setBackground(Color.GREEN);
		colorGreen.setOpaque(true);
		colorGreen.addMouseListener(ecCouleur);
		panel_Colors.add(colorGreen);
		
		colorViolet = new JLabel("");
		colorViolet.setBackground(Color.MAGENTA);
		colorViolet.setOpaque(true);
		colorViolet.addMouseListener(ecCouleur);
		panel_Colors.add(colorViolet);
		
		colorCyan = new JLabel("");
		colorCyan.setBackground(Color.CYAN);
		colorCyan.setOpaque(true);
		colorCyan.addMouseListener(ecCouleur);
		panel_Colors.add(colorCyan);
		
		colorBlue = new JLabel("");
		colorBlue.setBackground(Color.BLUE);
		colorBlue.setOpaque(true);
		colorBlue.addMouseListener(ecCouleur);
		panel_Colors.add(colorBlue);
		
		colorNoir = new JLabel("");
		colorNoir.setBackground(Color.BLACK);
		colorNoir.setOpaque(true);
		colorNoir.addMouseListener(ecCouleur);
		panel_Colors.add(colorNoir);
		
		colorRouge = new JLabel("");
		colorRouge.setBackground(Color.RED);
		colorRouge.setOpaque(true);
		colorRouge.addMouseListener(ecCouleur);
		panel_Colors.add(colorRouge);
		
		colorBlanc = new JLabel("");
		colorBlanc.setBackground(new Color(255, 255, 255));
		colorBlanc.addMouseListener(ecCouleur);
		colorBlanc.setOpaque(true);
		panel_Colors.add(colorBlanc);
		
		
		ecMoved = new EcouteurMouseMoved();
		ec = new Ecouteur();
		textField.addActionListener(ec); // ajout de écouteur pour le textfield de la taille de trait
		
		panel_Dessin.addMouseMotionListener(ecMoved);  //on ajoute les écouteurs au panel de dessin
		panel_Dessin.addMouseListener(ecouteurClicked);		
		
		Panel_Graphique p_G = new Panel_Graphique(); // initialisation du panel graphique
		p_G.setBounds(10, 10, 900, 545);
		panel_Dessin.add(p_G); // on ajoute le panel graphique
		panel_Dessin.setLayout(null);
		buttonSauvegarde = new JButton(Sauvegarde);
		buttonSauvegarde.setBounds(266, 18, 47, 35);
		buttonSauvegarde.addMouseListener(ecouteurClicked);
		getContentPane().add(buttonSauvegarde);
		buttonColor1.setSelected(true);
		buttonCrayon.setSelected(true);
		buttonColor1.setBackground(Color.BLACK);
		buttonColor2.setBackground(Color.WHITE);
	}
	
	private class Ecouteur implements ActionListener
	{

		@Override // pour prendre la taille de trait
		public void actionPerformed(ActionEvent e) {
			String valeurDeTaille; 
			JTextField source = (JTextField)e.getSource(); // source transtypé en jtextfield
			valeurDeTaille = source.getText(); // on prend la string
			tailleDeLigne = (Integer.parseInt(valeurDeTaille)); // la taille de trait
		}
		
	}
	
	private class EcouteurPourCouleur implements MouseListener // ecouteur dédié pour la couleur par ce que sinon quand on click sur une couleur et le crayon est
	{															// sélectioné, il prend en compte que on essaye de faire un tracé alors qu'on veut juste changer de couleur

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() instanceof JLabel) // selection de couleurs
			{
				if(buttonColor1.isSelected()) // si la couleur 1 est sélectioné
				{
					buttonColor1.setBackground(((JLabel)e.getSource()).getBackground()); // on place comme couleur de fond du buttonColor1 la couleur de fond du couleur sélectioné
				}																			// on va aller récuperer la couleur de fond le buttoncolor1 pour changer de couleur dans 
				else if(buttonColor2.isSelected())											// l'écouteur mousePressed(dans Ecouteurclicked) plus tard.
				{
					buttonColor2.setBackground(((JLabel)e.getSource()).getBackground());
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
	private class Ecouteurclicked implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			
			if(buttonRemplir.isSelected()) // si buttonRemplir est toggled
			{
				panel_Dessin.setBackground(colorDeLigne); // on remplit la couleur de background du panel de dessin avec notre couleur sélectioné
				efface.adjustAvecBackground(vectorDeToutElement, panel_Dessin.getBackground());
			}
			else if(buttonColorTaker.isSelected()) // si la pipette est toggled
			{
				Pipette pipette = new Pipette();
				Sauvegarde sauvegarde = new Sauvegarde();
				colorDeLigne = pipette.obtenirCouleur(sauvegarde.recupererImage(panel_Dessin), e.getX(), e.getY());// on prend un pixel sur x et y du bufferedimage et on retourne une couleur
				buttonColor1.setBackground(colorDeLigne); // on place la couleur dans buttonColor1 (qui est sélectioné comme couleur actuel)
				buttonCrayon.setSelected(true); // on fait en sorte que le choix est de retour au crayon
			}
			
			if(e.getSource() instanceof JButton) // Le seul JButton dans le frame est la sauvegarde donc si la sauvegarde est la source de l'action
			{
				JFileChooser fileChooser = new JFileChooser("F:\\java_H2019\\Tp1 paint");
				fileChooser.setDialogTitle("Sauvegarder un fichier");
				int selection = fileChooser.showSaveDialog(tpPaint.this);
				
				if(selection == JFileChooser.APPROVE_OPTION)
				{
					fileChooser.getSelectedFile();
				}
				Sauvegarde sauvegarde = new Sauvegarde();
				try {
					ImageIO.write(sauvegarde.recupererImage(panel_Dessin), "png",new File(fileChooser.getSelectedFile().getAbsolutePath()+".png")); // sauvegarde en format png
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			colorDeLigne = buttonColor1.getBackground(); // couleur par défaut est celui du buttoncolor1
			if(buttonColor2.isSelected())					// mais si l'utilisateur a toggled buttonColor2, on va utiliser la couleur de fond de buttonColor2
				colorDeLigne = buttonColor2.getBackground();
			
			// couleur selon si on fait un click de gauche ou un click de droite (exception pour efface)
			if(e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON1 && buttonEfface.isSelected())
			{
				colorDeLigne = buttonColor2.getBackground();
			}
			if(e.getButton() == MouseEvent.BUTTON1 && !buttonEfface.isSelected())
			{
				colorDeLigne = buttonColor1.getBackground();
			}
			
			/********À partir de ici, on ajoute une première point (de départ) de tracé avec sa couleur et taille dans le vecteur d'élément de la surface de dessin*************************************/
			
			if(buttonCrayon.isSelected()) // si le crayon est sélectioné
			{
				Trace trace = new Trace(e.getX(),e.getY(),"crayon",colorDeLigne,tailleDeLigne);
				vectorDeToutElement.add(trace);
			}
			if(buttonEfface.isSelected()) // si l'éfface est sélectioné
			{ 
				efface.setColor(buttonColor2.getBackground());
				Trace trace = new Trace(e.getX(),e.getY(),"efface",efface.getColor(),tailleDeLigne);
				vectorDeToutElement.add(trace);
			}
			if(buttonSquare.isSelected())// si carré est sélectioné
			{
				Trace trace = new Trace(e.getX(),e.getY(),"carre",colorDeLigne,tailleDeLigne);
				Trace prototype = new Trace(e.getX(),e.getY(),"prototypeCarre",colorDeLigne,tailleDeLigne); // nos forme géométrique ont un deuxième point placé dans
				prototypeDeForme.add(0, prototype);															// un vecteur de prototype qu'on va utiliser pour faire un 
				vectorDeToutElement.addElement(trace);														// preview de la forme (pour le prototype on va utiliser index 0)
			}
			if(buttonCercle.isSelected())// si le cercle est sélectioné
			{
				Trace trace = new Trace(e.getX(),e.getY(),"cercle",colorDeLigne,tailleDeLigne);
				Trace prototype = new Trace(e.getX(),e.getY(),"prototypeCercle",colorDeLigne,tailleDeLigne);
				prototypeDeForme.add(0, prototype);
				vectorDeToutElement.addElement(trace);
			}
			if(buttonTriangle.isSelected())// si le triangle est sélectioné
			{
				Trace trace = new Trace(e.getX(),e.getY(),"triangle",colorDeLigne,tailleDeLigne);
				Trace prototype = new Trace(e.getX(),e.getY(),"prototypeTriangle",colorDeLigne,tailleDeLigne);
				prototypeDeForme.add(0, prototype);
				vectorDeToutElement.addElement(trace);
			}
			/*****************************************fin d'ajout du premier point dans le vecteur selon ce qui est sélectioné***********************************************/
		}

		@Override
		public void mouseReleased(MouseEvent e)  // quand on release, on ajoute un deuxieme point qui indique le point d'arrivé de la forme géometrique
		{
			if(buttonSquare.isSelected())
			{
				Trace trace = new Trace(e.getX(),e.getY(),"carre",colorDeLigne,tailleDeLigne);
				vectorDeToutElement.add(trace);
			}
			if(buttonCercle.isSelected())
			{
				Trace trace = new Trace(e.getX(),e.getY(),"cercle",colorDeLigne,tailleDeLigne);
				vectorDeToutElement.addElement(trace);
			}
			if(buttonTriangle.isSelected())
			{
				Trace trace = new Trace(e.getX(),e.getY(),"triangle",colorDeLigne,tailleDeLigne);
				vectorDeToutElement.addElement(trace);
			}
			
			prototypeDeForme.clear();
			
			repaint();
		}
	}
	
	private class EcouteurMouseMoved implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e) // quand on bouge la souris avec un coup maintenu
		{
			/*****************************on ajoute un deuxième point(arrivé) pour le crayon ou efface********************************************************/
			
			if(buttonCrayon.isSelected()) // pour selection crayon
			{
				Trace trace = new Trace(e.getX(),e.getY(),"crayon",colorDeLigne,tailleDeLigne);
				vectorDeToutElement.add(trace);
			}
			if(buttonEfface.isSelected()) // pour selection efface
			{
				Trace trace = new Trace(e.getX(),e.getY(),"efface",efface.getColor(),tailleDeLigne);
				vectorDeToutElement.add(trace); 
			}
			/****************************si on a une forme géométrique sélectioné, on ajoute un point d'arrivé au vecteur prototype************************/
			if(buttonTriangle.isSelected() || buttonSquare.isSelected() || buttonCercle.isSelected())
			{
				Trace prototype = new Trace(e.getX(),e.getY(),"",colorDeLigne,tailleDeLigne);
				prototypeDeForme.add(1, prototype); // on utilise 0 et 1 comme index du prototype car on va le remplacer à chaque fois (quand 
			}											//on repaint, sa ne fait que montrer un preview de ce qu'on va avoir tant qu'on ne relache pas la sourie)

			repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) 
		{
			Trace trace = new Trace(e.getX(),e.getY(),"",colorDeLigne,tailleDeLigne); // on prend en compte de l'emplacement de la sourie en tout temps même si on ne dessine rien
			vectorDeToutElement.add(trace);
		}	
	}
	
	private class Panel_Graphique extends JPanel
	{
		@Override
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for(int indexDuVector = 0;indexDuVector < vectorDeToutElement.size()-1;indexDuVector++)
			{
				g.setColor(vectorDeToutElement.get(indexDuVector).getColor());
				((Graphics2D) g).setStroke(new BasicStroke(vectorDeToutElement.get(indexDuVector).getTailleDeTrait())); // pour taille de ligne
				
				// *********** on dessine tout ce qui est dans notre vecteur selon s'il est un crayon,efface,forme,etc.*****************************************************************
				
				if(vectorDeToutElement.get(indexDuVector).getTypeDeElement().equals("crayon"))
				{
					lignes.drawLeComponent(g,vectorDeToutElement.get(indexDuVector),vectorDeToutElement.get(indexDuVector+1));
				}
				else if(vectorDeToutElement.get(indexDuVector).getTypeDeElement().equals("efface"))
				{
					efface.drawEfface(g, vectorDeToutElement.get(indexDuVector),vectorDeToutElement.get(indexDuVector+1));
				}
				else if(vectorDeToutElement.get(indexDuVector).getTypeDeElement().equals("carre"))
				{
					leCarre.drawForme(vectorDeToutElement.get(indexDuVector),vectorDeToutElement.get(indexDuVector+1), g);
				}
				else if(vectorDeToutElement.get(indexDuVector).getTypeDeElement().equals("cercle"))
				{
					cercle.drawForme(vectorDeToutElement.get(indexDuVector),vectorDeToutElement.get(indexDuVector+1), g);
				}
				else if(vectorDeToutElement.get(indexDuVector).getTypeDeElement().equals("triangle"))
				{
					triangle.drawForme(vectorDeToutElement.get(indexDuVector),vectorDeToutElement.get(indexDuVector+1), g);
				}
				/******************************************************************************************************************************************************************/
			}

			if(!prototypeDeForme.isEmpty()) // si le prototype est pas vide (pour ne pas crash au départ du logiciel par ce que notre vecteur est vide)
			{
				// on dessine le preview de la forme (avec les point dans le vecteur de prototype) selon la forme qu'on veut dessiner
				if(prototypeDeForme.get(0).getTypeDeElement().equals("prototypeCarre"))
				{
					leCarre.drawForme(prototypeDeForme.get(0),prototypeDeForme.get(1), g);
				}
				else if(prototypeDeForme.get(0).getTypeDeElement().equals("prototypeTriangle"))
				{
					triangle.drawForme(prototypeDeForme.get(0),prototypeDeForme.get(1), g);
				}
				else if(prototypeDeForme.get(0).getTypeDeElement().equals("prototypeCercle"))
				{
					cercle.drawForme(prototypeDeForme.get(0),prototypeDeForme.get(1), g);
				}
			}
		}
		
		
		
	}
}
