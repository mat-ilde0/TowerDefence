package scenes;

import java.awt.Color;
import java.awt.Graphics;

import helperPackage.LevelBuild;
import main.Game;
import menagers.TileManager;
import userInterface.ButtonBar;
import userInterface.MyButton;
import static main.GameStates.*;
import main.GameStates;

public class Playing extends GameScene implements SceneMethods{

	private int[][] levels;
	private TileManager tileManager;
	
	private ButtonBar buttonBar;
	
	//costruttore
	public Playing(Game game) {
		super(game);
	
		levels = LevelBuild.getLevelData();
		tileManager = new TileManager();
		buttonBar = new ButtonBar(0, 640, 640, 100, this);
		
	}
	
	/**
	 * metodo che si occupa di disegnare gli sprite corrispondenti basandosi sugli indici
	 * di un array bidimensionale (quello della classe di utilità: LevelBuild).
	 * @param g
	 */
	private void createLevels(Graphics g) {
		for(int y = 0; y < levels.length; y++) {
			for(int x = 0; x < levels[y].length; x++) {
				int id = levels[y][x];
				g.drawImage(tileManager.getSprite(id), 
						x * GameScene.SINGLE_IMG_WIDTH, 
						y * GameScene.SINGLE_IMG_WIDTH, 
						null);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		//creazione dei livelli 
		createLevels(g);
		
		//disegno della barra 
		buttonBar.draw(g);
		
	}

	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mouseClicked(xCord, yCord);
		}else {
			//codice dopo
		}
	}
	
	/**
	 * si controlla se il mouse sta sulla buttonBar, e in quel caso si fanno i controlli
	 * riguardo i bottoni contenuti in essa, altrimenti si evitano
	 */

	@Override
	public void mouseMoved(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mouseMoved(xCord, yCord);
		}
	}

	/**
	 * Controlla se viene premuto il bottone per tornare al menu
	 */
	@Override
	public void mousePressed(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mousePressed(xCord, yCord);
		}else {
			//codice dopo
		}
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		buttonBar.mouseReleased(xCord, yCord);
	}

	@Override
	public void initButtons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawButtons(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	//GETTERS E SETTERS
	public TileManager getTileManager() {
		return tileManager;
	}
	
}
