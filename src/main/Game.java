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
	private long lastTimeActualFrm; //tempo al quale è stato mostrato l'ultimo frame
	public static final double FPS = 120.0;
	
	private double timePerUpdate;
	private long lastTimeActualUpdt;
	public static final double UPS = 60.0;  //Update Per Second
	
	//variabili per la verifica degli UPS
	private long lastTimeUPS;  
	private int updates;
	
	//costruttore
	public Game(){
		importImg(); //viene fatto prima prer threading
		initialize();
		
		timePerFrame = 1000000000.0 / FPS; 
		timePerUpdate = 1000000000.0 / UPS; 
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
			
			//update
			if(System.nanoTime() - lastTimeActualUpdt >= timePerUpdate) {
				lastTimeActualUpdt = System.nanoTime();
				updateGame();
				
				checkUPS();
			}
			
			//rendering
			if(System.nanoTime() - lastTimeActualFrm >= timePerFrame) {
				lastTimeActualFrm = System.nanoTime();
				repaint();
			}else {
				//nothing
			}
		}
	}
	
	/**
	 * metodo per l'update del gioco
	 */
	private void updateGame() {
		updates++;
		lastTimeActualUpdt = System.nanoTime();
		//System.out.println("Game updated!!");
	}
	
	/**
	 * metodo che controlla quanti updarte al secondo vengono fatti
	 */
	private void checkUPS() {
		/*
		 * ogni volta che passa un secondo vengono mostrati gli fps.
		 */
		if(System.currentTimeMillis()-lastTimeUPS >= 1000) {
			System.out.println("UPS: " + updates);
			updates = 0;
			lastTimeUPS = System.currentTimeMillis();
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
