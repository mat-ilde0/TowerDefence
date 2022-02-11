package userInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * classe che rappresenta un bottone customizzato che lancia eventi solo se 
 * il mouse clicca sul bottone e quindi se il cursore si trova attualmente sul bottone,
 * cosa che viene controllata grazie alla variabile rectangle.
 * @author User
 *
 */

public class MyButton {

	private int x, y, width, height;
	private String text;
	private Rectangle bounds; //per capire se il mouse è su un pulsante
	private boolean mouseOver; // segnala se il mouse si trova sul bottone
	private boolean mousePressed;
	
	public MyButton(String text, int x, int y, int width, int height) {
		this.height = height;
		this.text = text;
		this.width = width;
		this.x = x;
		this.y = y;
		
		initBounds();
	}
	
	/**
	 * metodo che serve per rapprendentare il bottone su schermo.
	 * @param g
	 */
	public void drawButton(Graphics g) {
		
		//corpo del bottone
		drawBody(g);
		
		//bordi
		drawBorder(g);
		
		//testo
		drawText(g);
		
	}
	
	/**
	 * metodo che disegna i bordi del bottone in base a se viene premuto oppure no.
	 * con questo metodo viene ispessito il bordo del rettangolo nel caso in cui il bottone venga premuto
	 * e rimane finché viene tenuto premuto.
	 * @param g
	 */
	private void drawBorder(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		
		g.setColor(Color.black);
		
		if(mousePressed)
			g2D.setStroke(new BasicStroke(2f));
		
		g.drawRect(x, y, width, height);
	}

	/**
	 * metodo che si occupa di disegnare il corpo del bottone e si occupa di colorarlo
	 * in un modo o in un altro in base alla posizione del mouse:
	 * - sul bottone -> bottone grigio
	 * - fuori dal bottone -> bottone bianco
	 * @param g
	 */
	private void drawBody(Graphics g) {
		if(mouseOver) 
			g.setColor(Color.GRAY);
		else
			g.setColor(Color.white);
		
		g.fillRect(x, y, width, height);
	}

	/**
	 * metodo che si occupa di disegnare decentemente il testo
	 */
	private void drawText(Graphics g) {
		 int textWidth = g.getFontMetrics().stringWidth(text);
		 int textHeight = g.getFontMetrics().stringWidth(text);
		 
		 g.drawString(text, x - textWidth / 2 + width / 2, y + textHeight / 2 + height / 2);
	}

	private void initBounds(){
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	/**
	 * metodo che resetta il botte alla sua forma originale, prima che venisse premuto
	 */
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	//GETTERS E SETTERS
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	
}
