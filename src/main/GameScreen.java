package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	
	public static final int SINGLE_IMG_WIDTH = 32; //pixel, larghezza immaginina
	public static final int COLOR_LIMIT = 256;   //valore limite colori escluso
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	//variabili per la gestione della velocità del repaint()
	private long lastTime;  
	private int frames;
	
	//costruttore
	public GameScreen(BufferedImage img) {
		this.img = img;
		
		loadSprites();
		
		this.random = new Random();
		
	}
	
	/**
	 * metodo per caricare tutte le immaginine (i singoli quadratini) come singole immagini
	 * ossia quelle che costituiscono lo sprite presente nelle res.
	 */
	private void loadSprites() {
		//(le immaginine sono 10 per riga e 10 per colonna, di dim 32 pixel)
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++) {
				//BufferedImage singleImg = img.getSubimage(i * SINGLE_IMG_WIDTH, j * SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH); 
				sprites.add(img.getSubimage(i * SINGLE_IMG_WIDTH, j * SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH));
			}
		}
		
	}

	/**
	 * Metodo che consente di disegnare sul componente: si usano tutti i metodi di Graphics per disegnare.
	 * 
	 * Ricorda: questo metodo non viene mai chiamato da nessun costruttore e neanche direttamente
	 * dal programmatore.
	 * Viene chiamato "automaticamente" quando si ha il bisogno di ridisegnare la finestra.
	 * 
	 * In questo caso l’orco verde è nella seconda riga e in ulti,ma posizione.
	 * Quindi con 9x32 si raggiunge l’angolo in alto a sinistra del quadratino 
	 * che contiene l’orco e per quanto riguarda la coordinata y sarà 1x32.
	 * Questi ragionamenti vanno fatti basandosi sulle proprietà dell'immagine.
	 */
	public void paintComponent(Graphics g) {
		//super che consente di disegnare lo sfondo del componente
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		//drawGrid(g);	
		
		//disegnare l'immagine importata
		//g.drawImage(img, 0, 0, null);
		
		//disegnare un elemento dello sprite atlas
		//g.drawImage(sprites.get(19), 0, 0, null);
		//g.drawImage(img.getSubimage(9*32, 32, SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH), 50, 50 , null);
		
		//disegnare le immaginine a caso
		randomImgsToScreen(g);
		
		callFPS();
		
		
	}
	
	/**
	 * metodo per visualizzare gli fps
	 */
	private void callFPS() {
		frames++;
		/*
		 * ogni volta che passa un secondo vengono mostrati gli fps.
		 */
		if(System.currentTimeMillis()-lastTime >= 1000) {
			System.out.println("FPS: " + frames);
			frames = 0;
			lastTime = System.currentTimeMillis();
		}

	}
	
	/**
	 * metodo che crea una griglia di colori casuali.
	 * 
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
	
	/**
	 *utilizzato per riempire a caso la finestra con le immaginine
	 */
	private int getRandomInt() {
		return random.nextInt(100);
	}
	
	private void randomImgsToScreen(Graphics g) {
		
		for(int i = 0; i<20; i++) {
			for(int j = 0; j<20; j++) {
				g.drawImage(sprites.get(getRandomInt()), i*SINGLE_IMG_WIDTH, j*SINGLE_IMG_WIDTH, null);
			}
		}
		
	}
 

	
}
