package userInterface;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;

import scenes.Playing;

/**
 * classe che si occupa di gestire i bottoni che vengono usati dall'utente per cambiare quello che è l'ambiente: per cambiare
 * tasselli del gioco, es. acqua con strada.
 * @author User
 *
 */

public class ActionBar extends Bar{
	
	private MyButton btnMenu;
	private Playing playing;

	public ActionBar (int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		
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
		
	}

	private void drawButtons(Graphics g) {
		//disegno dei bottoni normali
		btnMenu.drawButton(g);
	}

	//GESTIONE MOUSE
	/**
	 * Controlla se e dove il mouse è stato cliccato.
	 * @param xCord
	 * @param yCord
	 */
	public void mouseClicked(int xCord, int yCord) {
		if(btnMenu.IfMouseOver(xCord, yCord)) {
			SetGameState(MENU);
		}
	}

	public void mouseMoved(int xCord, int yCord) {
		//si resettano i bottoni 
		btnMenu.setMouseOver(false);
		if(btnMenu.IfMouseOver(xCord, yCord))
			btnMenu.setMouseOver(true);
	}

	/**
	 * Controlla se viene premuto il bottone per tornare al menu
	 */
	public void mousePressed(int xCord, int yCord) {
		if(btnMenu.IfMouseOver(xCord, yCord))
			btnMenu.setMousePressed(true);	
	}

	public void mouseReleased(int xCord, int yCord) {
		//reset bottone menu
		btnMenu.resetBooleans();
	}
}
