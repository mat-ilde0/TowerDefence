package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * in questa classe ci si occupa di tutta la parte del rendering, e quindi di mostrare
 * diverse schermate o comunque immagini/configurazioni in base allo stato attuale del gioco.
 * @author User
 *
 */

public class Render {
	
	Game game;
	
	//costruttore
	public Render(Game game) {
		this.game = game;
		
	}
	
	/**
	 * in base allo stato attuale del gioco si disegnerà qualcosa sulla finestra
	 * @param g della classe Graphics
	 */
	public void render(Graphics g) {
		
		switch (GameStates.gameState) {
		
		case MENU:
			game.getMenu().render(g);
			break;
		case PLAYING:
			game.getPlaying().render(g);
			break;
		case SETTINGS:
			game.getSettings().render(g);
			break;
		case EDIT:
			game.geteEditor().render(g);
			break;
		default:
			break;
	
		}
	}

}
