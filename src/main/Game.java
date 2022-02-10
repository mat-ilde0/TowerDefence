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

public class Game extends JFrame implements Runnable{
	
	private GameScreen gameScreen;
	
	private BufferedImage img;
	
	//variabili per la gestione del loop 
	public static final double FPS = 120.0;
	public static final double UPS = 60.0;  //Update Per Second
	
	//variabili per la gestione dei Thread
	private Thread gameThread;
	
	//costruttore
	public Game(){
		importImg(); //viene fatto prima prer threading
		initialize();
	}
	

	//MAIN METHOD
	public static void main(String[] args) {
		Game game = new Game();
		//game.gameLoop();
		game.begin();    //start mi mandava in confusione 
		

	}
	
	/**
	 * inizializzazione del gameThread
	 */
	private void begin() {
		gameThread = new Thread(this);
		gameThread.start(); //metodo di thread
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
	 * metodo per l'update del gioco
	 */
	private void updateGame() {
		//System.out.println("Game updated!!");
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

	/**
	 * tutto quello che deve fare il thread, in questo metodo verrà messo il gameLoop
	 * e quindi questo comprende:
	 * - rendering
	 * - update
	 * - checkFPS
	 * - checkUPS
	 */
	@Override
	public void run() {
		//rendering variables
		double timePerFrame = 1000000000.0 / FPS; //per quanto tempo viene visualizzato un frame
		long lastTimeActualFrm = System.nanoTime(); //tempo al quale è stato mostrato l'ultimo frame
		//variabili per la verifica degli FPS e UPS
		long lastTimeCheck = System.currentTimeMillis();  //sia per UPS che FPS
		int frames = 0;
		int updates = 0;
		
		//variabili per l'update
		double timePerUpdate = 1000000000.0 / UPS; ;
		long lastTimeActualUpdt = System.nanoTime();
		
		while(true) {
			
			//rendering
			if(System.nanoTime() - lastTimeActualFrm >= timePerFrame) {
				lastTimeActualFrm = System.nanoTime();
				repaint();
				frames++;
			}
			
			//update
			if(System.nanoTime() - lastTimeActualUpdt >= timePerUpdate) {
				lastTimeActualUpdt = System.nanoTime();
				updateGame();
				updates++;
			}
			
			//checkUPS and checkFPS
			if(System.currentTimeMillis()-lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + "  |  UPS: "+ updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

			
		}
	}

}
