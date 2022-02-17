package menagers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helperPackage.ImgFix;
import helperPackage.LoadSave;
import objects.Tile;
import scenes.GameScene;

/**
 * classe che si occupa di gestire le tegoline.
 * BL = Bottom Left.
 * TL = Top Left
 * @author User
 *
 */

public class TileManager {

	//Tutti i tipi di tiles presenti
	public Tile GRASS, WATER, 
	ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R,
	BL_WATER_CORNER, BR_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, L_WATER, R_WATER, T_WATER, B_WATER,
	TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE; 
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
		
		int id = 0;
		
		//prima l'indice sull'asse x e poi quello sull'asse y
		
		//tiles "semplici"
		tiles.add((GRASS) = new Tile(getSprite(9,0), id++, "Grass"));
		tiles.add((WATER) = new Tile(getSprite(0,0), id++, "Water"));
		tiles.add((ROAD_LR) = new Tile(getSprite(8,0), id++, "Road_LR"));
		
		//strada
		tiles.add(ROAD_TB = new Tile(ImgFix.getRotatediImg(getSprite(8, 0), 90), id++, "TB_Road"));
		tiles.add((ROAD_B_TO_R) = new Tile(getSprite(7,0), id++, "Road_Bottom_To_Right"));
		tiles.add(ROAD_L_TO_B = new Tile(ImgFix.getRotatediImg(getSprite(7, 0), 90), id++, "Road_Left_To_Bottom"));
		tiles.add(ROAD_L_TO_T = new Tile(ImgFix.getRotatediImg(getSprite(7, 0), 180), id++, "Road_Left_To_Top"));
		tiles.add(ROAD_T_TO_R = new Tile(ImgFix.getRotatediImg(getSprite(7, 0), 270), id++, "Road_Top_To_Right"));
		
		//acqua
		tiles.add(BL_WATER_CORNER = new Tile(ImgFix.buildImg(getImgs(0, 0, 5, 0)), id++, "BL_Water_Corner"));
		tiles.add(TL_WATER_CORNER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 5, 0), 90, 1), id++, "TL_Water_Corner"));
		tiles.add(TR_WATER_CORNER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 5, 0), 180, 1), id++, "TR_Water_Corner"));
		tiles.add(BR_WATER_CORNER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 5, 0), 270, 1), id++, "BR_Water_Corner"));
		
		tiles.add(T_WATER = new Tile(ImgFix.buildImg(getImgs(0, 0, 6, 0)), id++, "T_Water"));
		tiles.add(R_WATER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 6, 0), 90, 1), id++, "R_Water"));
		tiles.add(B_WATER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 6, 0), 180, 1), id++, "B_Water"));
		tiles.add(L_WATER = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 6, 0), 270, 1), id++, "L_Water"));
		
		//isola
		tiles.add(TL_ISLE = new Tile(ImgFix.buildImg(getImgs(0, 0, 4, 0)), id++, "TL_Isle"));
		tiles.add(TR_ISLE = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 4, 0), 90, 1), id++, "TR_Isle"));
		tiles.add(BR_ISLE = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 4, 0), 180, 1), id++, "BR_Isle"));
		tiles.add(BL_ISLE = new Tile(ImgFix.getRotate2ndLayer(getImgs(0, 0, 4, 0), 270, 1), id++, "BL_Isle"));
		
	}


	private void loadAtlas() {
		
		atlas = LoadSave.getSpriteAtlas();
		
	}
	
	/**
	 * ritorna un array di immagini, quelle che andranno a costituire
	 * un unico sprite. In questo caso si richiedono le coordinate solo per due immaginine
	 * perché in questo programma se ne aggregano al massimo 2.
	 * @return
	 */
	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
		return new BufferedImage[] {getSprite(firstX, firstY), 
				getSprite(secondX, secondY)};
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
	
	//GETTERS E SETTERS
	public Tile getTile(int index) {
		return tiles.get(index);
	}
	
}
