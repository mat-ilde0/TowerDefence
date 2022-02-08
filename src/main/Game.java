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
	
	//costruttore
	public Game(){
		importImg(); //viene fatto prima prer threading
		initialize();
	}
	

	//MAIN METHOD
	public static void main(String[] args) {
		Game gioco = new Game();

	}
	
	/**
	 * metodo che inizializza tutte gli elementi necessari per visualizzare la finestra
	 */
	private void initialize() {
		setSize(1000 , 600);
		setVisible(true);
		//setResizable(false);   se lo si toglie i colori cambieranno ogni volta che la finestra viene ridimensionata
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//creazione e aggiunta del gameScreen al JFrame
		this.gameScreen = new GameScreen(img);
		this.add(gameScreen);

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
