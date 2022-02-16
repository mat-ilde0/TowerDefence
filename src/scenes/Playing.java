package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helperPackage.LoadSave;
import main.Game;
import userInterface.ActionBar;


public class Playing extends GameScene implements SceneMethods{

	private int[][] level;
	
	private ActionBar buttonBar;
	private int mouseX, mouseY;  //per tenere traccia della posizione del mouse, per poter
	                             //disegnare in maniera corretta il tile selezionato.
	
	//costruttore
	public Playing(Game game) {
		super(game);
		loadDefaultLevel();
		
		buttonBar = new ActionBar(0, 640, 640, 100, this);
		
		
	}
	
	//GESTIONE LIVELLI
	
	private void loadDefaultLevel() {
		level = LoadSave.getLevelData("newLevel");
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
	
	@Override
	public void render(Graphics g) {
		
		//creazione dei livelli 
		drawLevel(g);
		
		//disegno della barra 
		buttonBar.draw(g);
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
		if(yCord >= 640) 
			buttonBar.mouseMoved(xCord, yCord);
		else {
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
		/*if(yCord >= 640) {
			buttonBar.mousePressed(xCord, yCord);
		}else {
			changeTile(xCord, yCord);
			/*
			 * non vengono usate le due variabili d'istanza mouseX e mouseY perché
			 * altrimenti il metodo non andrebbe e farebbe praticamente la stessa cosa
			 * di mouseClicked()
			 */
		//}
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
	
	private BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}
	
	public void setLevel(int[][] level) {
		this.level = level;
	}

}
