package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

/**
 * componente che permette di disegnarci sopra e che consente di raggruppare altri componenti
 * @author User
 *
 */

public class GameScreen extends JPanel {
	
	Random random;   //viene utilizzato per determinare a caso dei colori
	private BufferedImage img;
	
	public static final int COLOR_LIMIT = 256;   //valore limite colori escluso

	//costruttore
	public GameScreen(BufferedImage img) {
		this.img = img;
		this.random = new Random();
	}
	
	/**
	 * Metodo che consente di disegnare sul componente: si usano tutti i metodi di Graphics per disegnare.
	 * 
	 * Ricorda: questo metodo non viene mai chiamato da nessun costruttore e neanche direttamente
	 * dal programmatore.
	 * Viene chiamato "automaticamente" quando si ha il bisogno di ridisegnare la finestra.
	 */
	public void paintComponent(Graphics g) {
		//super che consente di disegnare lo sfondo del componente
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		drawGrid(g);	
	}
	
	/**
	 * 
	 * metodo che consente di creare una griglia di quadrati
	 */
	private void drawGrid(Graphics g) {
		
		int squareWidth = (int)(getWidth() / 100);
		
		for(int x = 0; x < getWidth() / squareWidth; x++) {
			for(int y = 0; y < getHeight() / squareWidth; y++) {
				//determinazione a caso del colore del quadratino 
				g.setColor(randomColor());
				g.fillRect(x * squareWidth, y* squareWidth, squareWidth, squareWidth);
			}
		}
	}
	
	/**
	 * metodo che restituisce un colore a caso ogni volta che lo si chiama
	 */
	private Color randomColor() {
		int red = random.nextInt(COLOR_LIMIT);
		int green = random.nextInt(COLOR_LIMIT);
		int blue = random.nextInt(COLOR_LIMIT);
	
		return new Color(red, green, blue);
	}
 

	
}
