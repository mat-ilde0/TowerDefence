package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helperPackage.LoadSave;
import main.Game;
import objects.Tile;
import userInterface.ToolBar;

/**
 * Classe dedicata alla gestione dell'editing dei vari livelli.
 * @author User
 *
 */

public class Editing extends GameScene implements SceneMethods{

	
	private int[][] level;
	
	private Tile selectedTile;
	private boolean drawSelect; //per verificare se è possibile disegnare o no (non se si è nella barra dei pulsanti)
	private int mouseX, mouseY;  //per tenere traccia della posizione del mouse, per poter
	                             //disegnare in maniera corretta il tile selezionato.
	private int lastTileX, lastTileY;  //variabili che tengono traccia della posizione dell'ultimo tile tracciato
	private int lastTileID;    //ID dell'ultimo tile cambiato
	
	private ToolBar toolBar;
	
	public Editing(Game game) {
		super(game);
		
		loadDefaultLevel();
		
		toolBar = new ToolBar(0, 640, 640, 100, this);
	}

	@Override
	public void render(Graphics g) {
		//disgno livello di partenza
		drawLevel(g);
		
		toolBar.draw(g);
		
		//disegnare il tile selezionato
		drawSelectedTile(g);
	
	}
	
	//METODI
	
	private void loadDefaultLevel() {
		level = LoadSave.getLevelData("newLevel");
	}
	
	
	private void drawSelectedTile(Graphics g) {
		if(selectedTile != null && drawSelect) {
			//disegna il tile selezionato in basso a destra, alla stessa altezza dei pulsanti
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, GameScene.SINGLE_IMG_WIDTH, GameScene.SINGLE_IMG_WIDTH, null);
			g.setColor(Color.black);
			g.drawRect(550, 650, 50, 50);
		}
	}
	
	/**
	 * metodo che salva il livello appena editato
	 */
	public void saveLevel() {
		LoadSave.saveLevel("newLevel", level);
		game.getPlaying().setLevel(level);
	}
	
	
	/**
	 * Metodo che disegna il selected tile nel quadratino in cui si trova e viene cliccato il mouse
	 * In questo metodo si controlla anche se si è nello stesso tile -> per evitare che il metodo venga
	 * sfruttato inutilmente.
	 * @param xMouseCord coordinata x effettiva ed attuale del mouse
	 * @param yMouseCord coordinata y effettiva ed attuale del mouse
	 */
	private void changeTile(int xMouseCord, int yMouseCord) {
		
		if(selectedTile != null) {
			//calcolo coordinate angolo in alto a sinistra del riquadretto selezionato
			int tileX = xMouseCord / 32;
			int tileY = yMouseCord /32;
			
			//si controlla se si cerca di cambiare Tile su un tile appena cambiato
			//e se il tile è lo stesso che era stato appena messo
			if(lastTileX == tileX && lastTileY == tileY && lastTileID == selectedTile.getId())
				return;
			
			lastTileX = tileX;
			lastTileY = tileY;
			lastTileID = selectedTile.getId();
			
			//nell'array il tileY indica in realtà la riga e tileX indica la colonna, per questo vengono usati in questo modo
			level[tileY][tileX] = selectedTile.getId();
		}
	}
	

	//DISEGNO UI
	
	/**
	 * metodo che si occupa di disegnare gli sprite corrispondenti basandosi sugli indici
	 * di un array bidimensionale (quello della classe di utilità: LevelBuild).
	 * @param g
	 */
	private void drawLevel(Graphics g) {
		for(int y = 0; y < level.length; y++) {
			for(int x = 0; x < level[y].length; x++) {
				int id = level[y][x];
				g.drawImage(getSprite(id),
						x * GameScene.SINGLE_IMG_WIDTH, 
						y * GameScene.SINGLE_IMG_WIDTH, 
						null);
			}
		}
	}
	
	//GESTIONE MOUSE

	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(yCord >= 640) {
			toolBar.mouseClicked(xCord, yCord);
		}else {
			changeTile(mouseX, mouseY);
		}
	}

	@Override
	public void mouseMoved(int xCord, int yCord) {
		if(yCord >= 640) {
			toolBar.mouseMoved(xCord, yCord);
			drawSelect = false;
		}
		else {
			drawSelect = true;
			//si cercano le coordinate dell'angolo in alto a sinistra in cui si trova
			//il mouse e in questo modo vanno a """"scatti"""" seguendo i quadratini
			mouseX = (xCord / 32) * 32;
			mouseY = (yCord / 32) * 32;
			
		}
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		if(yCord >= 640) {
			toolBar.mousePressed(xCord, yCord);
		}
	}
	
	@Override
	public void mouseDragged(int xCord, int yCord) {
		if(yCord >= 640) {
			toolBar.mousePressed(xCord, yCord);
		}else {
			changeTile(xCord, yCord);
			/*
			 * non vengono usate le due variabili d'istanza mouseX e mouseY perché
			 * altrimenti il metodo non andrebbe e farebbe praticamente la stessa cosa
			 * di mouseClicked()
			 */
		}
	}
		
	@Override
	public void mouseReleased(int xCord, int yCord) {
		if(yCord >= 640) {
			toolBar.mouseReleased(xCord, yCord);
		}
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
	
	public void setSelectedTile(Tile newTile) {
		this.selectedTile = newTile;
		drawSelect = true;
	}
	
	private BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}

	

}
