package menagers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helperPackage.LoadSave;
import objects.Tile;
import scenes.GameScene;

/**
 * classe che si occupa di gestire le tegoline
 * @author User
 *
 */

public class TileManager {

	public Tile GRASS, WATER, ROAD;
	public BufferedImage atlas;   //immagine che raggruppa tutti i tiles
	
	public ArrayList<Tile> tiles = new ArrayList<>();
	
	public TileManager() {
		
		loadAtlas();
		createTiles();
	}

	/**
	 * Metodo che estrae tutti i tiles dall'immagine e che li aggiunge nell'array
	 */
	private void createTiles() {
		//prima l'indice sull'asse x e poi quello sull'asse y
		tiles.add((GRASS) = new Tile(getSprite(8,1)));
		tiles.add((WATER) = new Tile(getSprite(0,6)));
		tiles.add((ROAD) = new Tile(getSprite(9,0)));
	}

	/**
	 * 
	 */
	private void loadAtlas() {
		
		atlas = LoadSave.getSpriteAtlas();
		
	}
	
	/**
	 * simile a quello sotto ma permette di accedere direttamente al tile desiderato
	 * prendendolo dall'array;
	 */
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	/**
	 * metodo che estrae il singolo quadratino.
	 * Gli si passa l'indice del quadratino interessato e non i pixel.
	 * Con questo metodo si ragiona con il sistema di riferimento standard basato
	 * su pixel e con l'asse delle ordinate orientata verso il basso.
	 */
	public BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * GameScene.SINGLE_IMG_WIDTH, yCord * GameScene.SINGLE_IMG_WIDTH, 
				GameScene.SINGLE_IMG_WIDTH, GameScene.SINGLE_IMG_WIDTH);
	}
	
	
}
