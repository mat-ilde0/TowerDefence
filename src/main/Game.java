package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * classe che estende JFrame e che rappresenta quello che poi sarà il giococ
 * @author User
 *
 */

public class Game extends JFrame{
	
	private GameScreen gameScreen;
	
	private BufferedImage img;
	
	//variabili per la gestione del loop 
	private double timePerFrame; //per quanto tempo viene visualizzato un frame
	private long lastTimeActualFr; //tempo al quale è stato mostrato l'ultimo frame
	
	
	//costruttore
	public Game(){
		importImg(); //viene fatto prima prer threading
		initialize();
		
		timePerFrame = 1000000000.0 / 60.0; 
	}
	

	//MAIN METHOD
	public static void main(String[] args) {
		Game game = new Game();
		
		game.gameLoop();

	}
	
	/**
	 * metodo che inizializza tutte gli elementi necessari per visualizzare la finestra
	 */
	private void initialize() {
		setSize(1000 , 600);
		//setResizable(false);   se lo si toglie i colori cambieranno ogni volta che la finestra viene ridimensionata
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//creazione e aggiunta del gameScreen al JFrame
		this.gameScreen = new GameScreen(img);
		this.add(gameScreen);
		
		setVisible(true);

	}
	
	/**
	 * metodo per la gestione del repaint() e degli fps, stabiliti a 60.
	 */
	private void gameLoop() {
		/*
		 * si controlla se il frame attuale è stato mostrato per il tempo uguale o maggiore rispetto a quello che dovrebbe essere
		 */
		while(true) {
			if(System.nanoTime() - lastTimeActualFr >= timePerFrame) {
				lastTimeActualFr = System.nanoTime();
				repaint();
			}else {
				//nothing
			}
		}
	}
	
	/**
	 * metodo utile per importare immagini (contenute in res)
	 */
	private void importImg() {
		InputStream fileImageStream = getClass().getResourceAsStream("/spriteatlas.png");
		
		try {
			img = ImageIO.read(fileImageStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
