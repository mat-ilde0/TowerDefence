package userInterface;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

/**
 * classe che si occupa di gestire i bottoni che vengono usati dall'utente per cambiare quello che è l'ambiente: per cambiare
 * tasselli del gioco, es. acqua con strada.
 * @author User
 *
 */

public class ButtonBar {
	
	private int x, y, width, height;
	private MyButton btnMenu;
	private Playing playing;
	//memorizza i bottoni assiociati a ciascun tipo di tile
	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	public ButtonBar (int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	public void draw(Graphics g) {
		//disegno sfondo
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		
		//disegno dei bottoni
		drawButtons(g);
	}
	
	/**
	 * inizializza tutti i bottoni presenti in questa UI
	 */
	public void initButtons() {
		
		btnMenu = new MyButton("Menu", 2, 642, 100, 30);
		
		//dimensioni bottoni
		int  w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f); //quanto dista un bottone dall'altro
		
		int i = 0;
		for (Tile tile : playing.getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}
		
	}

	private void drawButtons(Graphics g) {
		//disegno dei bottoni normali
		btnMenu.drawButton(g);
		
		//disegno tileButtons
		drawTileButtons(g);
		
	}
	
	/**
	 * disegna i tiles nei rispettivi bottoni
	 * @param g
	 */
	private void drawTileButtons(Graphics g) {
		for (MyButton b : tileButtons) {
			g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
		}
	}
	
	private BufferedImage getButtImg(int id) {
		return playing.getTileManager().getSprite(id);
	}

	//GESTIONE MOUSE
	public void mouseClicked(int xCord, int yCord) {
		if(btnMenu.getBounds().contains(xCord, yCord)) {
			SetGameState(MENU);
		}
	}

	public void mouseMoved(int xCord, int yCord) {
		if(btnMenu.IfMouseOver(xCord, yCord))
			btnMenu.setMouseOver(true);
	}

	/**
	 * Controlla se viene premuto il bottone per tornare al menu
	 */
	public void mousePressed(int xCord, int yCord) {
		if(btnMenu.getBounds().contains(xCord, yCord)) {
			btnMenu.setMousePressed(true);
		}	
	}

	public void mouseReleased(int xCord, int yCord) {
		btnMenu.resetBooleans();
	}

}
