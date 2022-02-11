package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import inputs.KeyBoardListener;
import inputs.MyMouseListener;
import scenes.*;

/**
 * classe che estende JFrame e che rappresenta quello che poi sarà il giococ
 * Classe che rappresenta il controller del programma
 * @author User
 *
 */

public class Game extends JFrame implements Runnable{
	
	private GameScreen gameScreen;
		
	//variabili per la gestione del loop 
	public static final double FPS = 120.0;
	public static final double UPS = 60.0;  //Update Per Second
	
	//variabili per la gestione dei Thread
	private Thread gameThread;
	
	//Classi 
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	
	//costruttore
	public Game(){
		initClasses();
		initialize();
	}
	

	//MAIN METHOD
	public static void main(String[] args) {
		Game game = new Game();
		//game.gameLoop();
		game.gameScreen.initInputs();
		game.begin();    //start mi mandava in confusione 
		
	}
	
	/**
	 * metodo che si occupa di inizializzare ogni variabile riguardanti le classi appositamente
	 * create
	 */
	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);
		
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
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
		setResizable(false);  // se lo si toglie i colori cambieranno ogni volta che la finestra viene ridimensionata
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//aggiunta del gameScreen al JFrame
		this.add(gameScreen);
		
		pack(); //metodo che va posizionato dopo aver aggiunto il Panel.
		
		setVisible(true);

	}
	
	/**
	 * metodo per l'update del gioco
	 */
	private void updateGame() {
		//System.out.println("Game updated!!");
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
		//
		long ActualTime;
		
		while(true) {
			
			ActualTime = System.nanoTime();
			
			//rendering
			if(ActualTime- lastTimeActualFrm >= timePerFrame) {
				lastTimeActualFrm = ActualTime;
				repaint();
				frames++;
			}
			
			//update
			if(ActualTime - lastTimeActualUpdt >= timePerUpdate) {
				lastTimeActualUpdt = ActualTime;
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
	
	//GETTERS and SETTERS
	public Render getRender() {
		return render;
	}


	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

}
