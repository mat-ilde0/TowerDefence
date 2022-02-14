package scenes;

import java.awt.Color;
import java.awt.Graphics;

import helperPackage.LevelBuild;
import helperPackage.LoadSave;
import main.Game;
import main.GameScreen;
import menagers.TileManager;
import objects.Tile;
import userInterface.ButtonBar;
import userInterface.MyButton;
import static main.GameStates.*;
import main.GameStates;

public class Playing extends GameScene implements SceneMethods{

	private int[][] levels;
	private TileManager tileManager;
	
	private ButtonBar buttonBar;
	private Tile selectedTile;
	
	private boolean drawSelect; //per verificare se è possibile disegnare o no (non se si è nella barra dei pulsanti)
	
	private int mouseX, mouseY;  //per tenere traccia della posizione del mouse, per poter
	                             //disegnare in maniera corretta il tile selezionato.
	private int lastTileX, lastTileY;  //variabili che tengono traccia della posizione dell'ultimo tile tracciato
	private int lastTileID;    //ID dell'ultimo tile cambiato
	
	//costruttore
	public Playing(Game game) {
		super(game);
	
		levels = LevelBuild.getLevelData();
		tileManager = new TileManager();
		buttonBar = new ButtonBar(0, 640, 640, 100, this);
		
		//LoadSave.createFile();
		LoadSave.writeToFile();
		
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
		
		//disegnare il tile selezionato
		drawSelectedTile(g);
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
			levels[tileY][tileX] = selectedTile.getId();
		}
	}
	
	//GESTIONE MOUSE
	
	/**
	 * metodo con il quale vengono disegnati tiles selezionati, dove l'utente decide, nel caso in cui 
	 * sia possibile.
	 */
	@Override
	public void mouseClicked(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mouseClicked(xCord, yCord);
		}else {
			changeTile(mouseX, mouseY);
		}
	}
	
	/**
	 * Si controlla se il mouse sta sulla buttonBar, e in quel caso si fanno i controlli
	 * riguardo i bottoni contenuti in essa, altrimenti si evitano.
	 * Una volta selezionato il Tile, questo metodo si occupa di gestire dove e se è possibile disegnarlo
	 * nel punto in cui si trova il cursore.
	 */

	@Override
	public void mouseMoved(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mouseMoved(xCord, yCord);
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

	/**
	 * Controlla se viene premuto il bottone per tornare al menu.
	 */
	@Override
	public void mousePressed(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mousePressed(xCord, yCord);
		}else {
			changeTile(mouseX, mouseY);
		}
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		buttonBar.mouseReleased(xCord, yCord);
	}
	
	/**
	 * Gestisce il fatto che il tile selezionato venga disegnato anche mentre si tiene
	 * premuto e trascinato il cursore. 
	 */
	@Override
	public void mouseDragged(int xCord, int yCord) {
		if(yCord >= 640) {
			buttonBar.mousePressed(xCord, yCord);
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
	
	public void setSelectedTile(Tile newTile) {
		this.selectedTile = newTile;
		drawSelect = true;
	}
	
}
