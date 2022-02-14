package scenes;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import helperPackage.LoadSave;
import main.Game;
import userInterface.MyButton;
import static main.GameStates.*;
import main.GameStates;

public class Menu extends GameScene implements SceneMethods{

	private BufferedImage img;
	
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;
	
	//bottoni nel menu
	private MyButton btnPlaying, btnSettings, btnQuit;
	
	public Menu(Game game) {
		super(game);
		
		random = new Random();
		
		importImg();
		loadSprites();
		initButtons();
	}

	/**
	 * metodo per impostare i bottoni che servono
	 */
	public void initButtons() {
		
		int w = 150;
		int h = w/ 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		
		btnPlaying = new MyButton("Play", x, y, w, h);
		btnSettings = new MyButton("Settings", x, y + yOffset, w, h);
		btnQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
	}

	private void importImg() {
		
		InputStream fileImageStream = getClass().getResourceAsStream("/spriteatlas.png");
				
		try {
			img = ImageIO.read(fileImageStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * aggiunta dei bottoni
	 */
	@Override
	public void render(Graphics g) {
		
		
		drawButtons(g);
		
		/*for(int i = 0; i<20; i++) {
			for(int j = 0; j<20; j++) {
				g.drawImage(sprites.get(getRandomInt()), i*SINGLE_IMG_WIDTH, j*SINGLE_IMG_WIDTH, null);
			}
		}*/
	}
	
	/**
	 * disegna tutti i bottoni presenti nel menu
	 */
	public void drawButtons(Graphics g) {
		btnPlaying.drawButton(g);
		btnSettings.drawButton(g);
		btnQuit.drawButton(g);
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
	 *utilizzato per riempire a caso la finestra con le immaginine
	 */
	private int getRandomInt() {
		return random.nextInt(100);
	}
	
	//GESTIONE EVENTI

	/**
	 * Gestisce le pressioni del mouse mentre il gioco si trova nello stato di MENU
	 */
	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(btnPlaying.getBounds().contains(xCord, yCord)) {
			SetGameState(PLAYING);
		}
		else if(btnSettings.getBounds().contains(xCord, yCord)) {
			SetGameState(SETTINGS);
		}
		else if(btnQuit.getBounds().contains(xCord, yCord))
			System.exit(0); //metodo che fa terminare il gioco
	}

	/*@Override
	public void mouseMoved(int xCord, int yCord) {
		btnPlaying.setMouseOver(false);
		if(btnPlaying.getBounds().contains(xCord, yCord)) {
			btnPlaying.setMouseOver(true);
		}
	}*/
	
	/**
	 * metodo che controlla, per ogni bottone se gli si passa sopra e in caso setta il suo booleano
	 */
	@Override
	public void mouseMoved(int xCord, int yCord) {
		
		if(btnPlaying.IfMouseOver(xCord, yCord))
			btnPlaying.setMouseOver(true);
		else if(btnSettings.IfMouseOver(xCord, yCord))
			btnSettings.setMouseOver(true);
		else if(btnQuit.IfMouseOver(xCord, yCord))
			btnQuit.setMouseOver(true);
		
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		if(btnPlaying.getBounds().contains(xCord, yCord)) {
			btnPlaying.setMousePressed(true);
		}
		else if(btnSettings.getBounds().contains(xCord, yCord)) {
			btnSettings.setMousePressed(true);
		}
		else if(btnQuit.getBounds().contains(xCord, yCord)) {
			btnQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		btnPlaying.resetBooleans();
		btnSettings.resetBooleans();
		btnQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}
	
	
}
