package scenes;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import userInterface.MyButton;

public class Settings extends GameScene implements SceneMethods{

	private MyButton btnMenu;
	
	public Settings(Game game) {
		super(game);
		
		initButtons();
	}

	@Override
	public void render(Graphics g) {
		
		//cosa viene mostrato 
		g.setColor(Color.red);
		g.fillRect(0, 0, 640, 640);
		
		//disegno dei bottoni
		drawButtons(g);
	}

	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(btnMenu.getBounds().contains(xCord, yCord)) {
			SetGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int xCord, int yCord) {
		if(btnMenu.getBounds().contains(xCord, yCord)) {
			btnMenu.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		if(btnMenu.getBounds().contains(xCord, yCord)) {
			btnMenu.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		btnMenu.resetBooleans();
	}

	@Override
	public void initButtons() {
		btnMenu = new MyButton("Menu", 50, 50, 150, 150 / 3);
		
	}

	@Override
	public void drawButtons(Graphics g) {
		btnMenu.drawButton(g);
		
	}

}
