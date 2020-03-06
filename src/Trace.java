import java.awt.Color;

public class Trace {
	//****************************************************************************************************************************************************************************
	// class de trait qu'on utilise pour placer dans les vecteurs avec ***********************************************************************************************************
	//les coordonnées(x,y), la couleur, le type d'élément qu'on va dessiner et la taille du trait********************************************************************************
	private int x,y;
	private Color color;
	private String typeDeElement="";
	private int tailleDeTrait;
	
	public Trace(int xP,int yP ,String type,Color laColor,int taille)
	{
		color = laColor;
		x= xP;
		y= yP;
		typeDeElement = type;
		tailleDeTrait = taille;
	}
	public int getX() {
		return x;
	}
	public int getTailleDeTrait() {
		return tailleDeTrait;
	} 
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getTypeDeElement() {
		return typeDeElement;
	}
	public void setTypeDeElement(String typeDeElement) {
		this.typeDeElement = typeDeElement;
	}
	
	
}
