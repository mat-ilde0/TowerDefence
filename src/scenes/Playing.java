package scenes;

import java.awt.Color;
import java.awt.Graphics;

import helperPackage.LevelBuild;
import main.Game;
import menagers.TileManager;
import userInterface.MyButton;

public class Playing extends GameScene implements SceneMethods{

	private int[][] levels;
	private TileManager tileManager;
	
	private MyButton btnMenu;
	
	//costruttore
	public Playing(Game game) {
		super(game);
	
		levels = LevelBuild.getLevelData();
		tileManager = new TileManager();
		
		initButtons();
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
		
		//disegno dei bottoni
		drawButtons(g);
		
	}

	@Override
	public void mouseClicked(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawButtons(Graphics g) {
		btnMenu.drawButton(g);
	}

	@Override
	public void initButtons() {
		btnMenu = new MyButton("Menu", 50, 50, 150, 150 / 3);
	}
}
