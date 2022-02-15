package userInterface;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

/**
 * classe che si occupa di gestire i bottoni che vengono usati dall'utente per cambiare quello che è l'ambiente: per cambiare
 * tasselli del gioco, es. acqua con strada.
 * @author User
 *
 */

public class ButtonBar {
	
	private int x, y, width, height;
	private MyButton btnMenu, btnSaveLvl;
	private Playing playing;
	
	private Tile selectedTile;  //tile selezionato dall'utente con il quale vuole "costruire"
	
	//memorizza i bottoni assiociati a ciascun tipo di tile
	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	public ButtonBar (int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	public void draw(Graphics g) {
		//disegno sfondo
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		
		//disegno dei bottoni
		drawButtons(g);
	}
	
	/**
	 * inizializza tutti i bottoni presenti in questa UI
	 */
	public void initButtons() {
		
		btnMenu = new MyButton("Menu", 2, 642, 100, 30);
		btnSaveLvl = new MyButton("Save", 2, 674, 100, 30);
		
		
		//dimensioni bottoni
		int  w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f); //quanto dista un bottone dall'altro
		
		int i = 0;
		for (Tile tile : playing.getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}
		
	}

	private void drawButtons(Graphics g) {
		//disegno dei bottoni normali
		btnMenu.drawButton(g);
		btnSaveLvl.drawButton(g);
		
		//disegno tileButtons
		drawTileButtons(g);
		
		//disegnare il tile selezionato
		drawSelectedTile(g);
		
	}
	
	/**
	 * disegna i tiles nei rispettivi bottoni e gestisce i feedback al movimento del mouse
	 * @param g
	 */
	private void drawTileButtons(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		
		for (MyButton b : tileButtons) {
		
			//sprite
			g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
			
			//mouseOver
			if(b.isMouseOver()) {
				g.setColor(Color.white);
			}else {
				g.setColor(Color.black);
			}
			
			//border
			g.drawRect(b.x, b.y, b.width, b.height);
			
			//mousePressed -> si disegna un contorno più spesso e di un colore diverso
			if(b.isMousePressed()) {
				g2D.setStroke(new BasicStroke(2.5f));
				g2D.setColor(Color.white);
			}
			
			//border
			g.drawRect(b.x, b.y, b.width, b.height);
			//reset stroke
			g2D.setStroke(new BasicStroke(1f));
			
		}
		
	}
	
	public void drawSelectedTile(Graphics g) {
		if(selectedTile != null) {
			//disegna il tile selezionato in basso a destra, alla stessa altezza dei pulsanti
			g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(550, 650, 50, 50);
		}
		
	}
	
	private BufferedImage getButtImg(int id) {
		return playing.getTileManager().getSprite(id);
	}
	
	/**
	 * metodo che gestisce il salvataggio di un livello appena editato
	 */
	private void saveLevel() {
		playing.saveLevel();
	}

	//GESTIONE MOUSE
	/**
	 * Controlla se e dove il mouse è stato cliccato.
	 * Nel caso in cui venga selezionato un tile -> si assegna un valore alla variabile
	 * selectedTile -> che rappresenta ciò che l'utente vuole costruire.
	 * @param xCord
	 * @param yCord
	 */
	public void mouseClicked(int xCord, int yCord) {
		if(btnMenu.IfMouseOver(xCord, yCord)) {
			SetGameState(MENU);
		}
		else if(btnSaveLvl.IfMouseOver(xCord, yCord))
			saveLevel();
		else for (MyButton b : tileButtons) {
			if(b.IfMouseOver(xCord, yCord)) {
				selectedTile = playing.getTileManager().getTile(b.getId());
				playing.setSelectedTile(selectedTile);
				return;
			}
		}
		
	}

	public void mouseMoved(int xCord, int yCord) {
		//si resettano i bottoni 
		for (MyButton b : tileButtons) 
			b.setMouseOver(false);
	
		if(btnMenu.IfMouseOver(xCord, yCord))
			btnMenu.setMouseOver(true);
		else if(btnSaveLvl.IfMouseOver(xCord, yCord))
			btnSaveLvl.setMouseOver(true);
		else {
			for(MyButton b : tileButtons) {
				if(b.IfMouseOver(xCord, yCord)) {
					b.setMouseOver(true);
					return;
				}
			}
		}
	}

	/**
	 * Controlla se viene premuto il bottone per tornare al menu
	 */
	public void mousePressed(int xCord, int yCord) {
		if(btnMenu.IfMouseOver(xCord, yCord))
			btnMenu.setMousePressed(true);
		else if(btnSaveLvl.IfMouseOver(xCord, yCord))
			btnSaveLvl.setMousePressed(true);
		else {
			for (MyButton b : tileButtons) {
				if(b.IfMouseOver(xCord, yCord)) {
					b.setMousePressed(true);
					return;
				}
			}
		}	
	}

	public void mouseReleased(int xCord, int yCord) {
		//reset bottone menu
		btnMenu.resetBooleans();
		btnSaveLvl.resetBooleans();
		
		//reset TilesButtons
		for (MyButton b : tileButtons)
			b.resetBooleans();
	}

}
