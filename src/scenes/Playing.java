package scenes;

import java.awt.Color;
import java.awt.Graphics;

import helperPackage.LevelBuild;
import main.Game;
import menagers.TileManager;

public class Playing extends GameScene implements SceneMethods{

	private int[][] levels;
	private TileManager tileManager;
	
	//costruttore
	public Playing(Game game) {
		super(game);
	
		levels = LevelBuild.getLevelData();
		tileManager = new TileManager();
		
		
	}

	@Override
	public void render(Graphics g) {
		
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
}
