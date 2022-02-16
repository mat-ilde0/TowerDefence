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
	
	//bottoni nel menu
	private MyButton btnPlaying, btnSettings, btnQuit, btnEdit;
	
	public Menu(Game game) {
		super(game);
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
		btnEdit = new MyButton("Edit", x, y + yOffset, w, h);
		btnSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
		btnQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
	}

	/**
	 * aggiunta dei bottoni
	 */
	@Override
	public void render(Graphics g) {
		
		drawButtons(g);
		
	}
	
	/**
	 * disegna tutti i bottoni presenti nel menu
	 */
	public void drawButtons(Graphics g) {
		btnPlaying.drawButton(g);
		btnEdit.drawButton(g);
		btnSettings.drawButton(g);
		btnQuit.drawButton(g);
	}
	
	//GESTIONE EVENTI

	/**
	 * Gestisce le pressioni del mouse mentre il gioco si trova nello stato di MENU
	 */
	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(btnPlaying.IfMouseOver(xCord, yCord)) {
			SetGameState(PLAYING);
		}
		else if(btnSettings.IfMouseOver(xCord, yCord)) {
			SetGameState(SETTINGS);
		}
		else if(btnEdit.IfMouseOver(xCord, yCord)) {
			SetGameState(EDIT);
		}
		else if(btnQuit.IfMouseOver(xCord, yCord))
			System.exit(0); //metodo che fa terminare il gioco
	}
	
	/**
	 * metodo che controlla, per ogni bottone se gli si passa sopra e in caso setta il suo booleano
	 */
	@Override
	public void mouseMoved(int xCord, int yCord) {
		btnPlaying.setMouseOver(false);
		btnEdit.setMouseOver(false);
		btnSettings.setMouseOver(false);
		btnQuit.setMouseOver(false);
		
		if(btnPlaying.IfMouseOver(xCord, yCord))
			btnPlaying.setMouseOver(true);
		else if(btnSettings.IfMouseOver(xCord, yCord))
			btnSettings.setMouseOver(true);
		else if(btnEdit.IfMouseOver(xCord, yCord))
			btnEdit.setMouseOver(true);
		else if(btnQuit.IfMouseOver(xCord, yCord))
			btnQuit.setMouseOver(true);
		
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		if(btnPlaying.IfMouseOver(xCord, yCord)) {
			btnPlaying.setMousePressed(true);
		}
		else if(btnSettings.IfMouseOver(xCord, yCord)) {
			btnSettings.setMousePressed(true);
		}
		if(btnEdit.IfMouseOver(xCord, yCord)) {
			btnEdit.setMousePressed(true);
		}
		else if(btnQuit.IfMouseOver(xCord, yCord)) {
			btnQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		btnPlaying.resetBooleans();
		btnSettings.resetBooleans();
		btnEdit.resetBooleans();
		btnQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}
	
	
}
