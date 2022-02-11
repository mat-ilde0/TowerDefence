package scenes;

import java.awt.Graphics;

public interface SceneMethods {

	public void render(Graphics g);
	
	/**
	 * metodo che viene implementato per fare una determinata cosa se il mouse viene 
	 * cliccato in una determinata posizione fornita per parametro
	 * @param xCord coordinata x attuale del mouse
	 * @param yCord coordinata y attuale del mouse
	 */
	public void mouseClicked(int xCord, int yCord);
	
	/**
	 * metodo che fa determinate cose in base alla posizione attuale del mouse e in 
	 * base allo stato attuale del gioco.
	 * @param xCord
	 * @param yCord
	 */
	public void mouseMoved(int xCord, int yCord);
	
	/**
	 * metodo che ritorna un feedback quando il bottone viene premuto
	 */
	public void mousePressed(int xCord, int yCord);
	
	/**
	 * metodo che ritorna feedback quanto un tasto del mouse che era stato premuto
	 * viene rilasciato.
	 * Il fatto che vi siano comunque le coordinate serve ad esempio per quando bisogna posizionare
	 * le torri nel gioco.
	 */
	public void mouseReleased(int xCord, int yCord);
	
	/**
	 * metodo che inizializza tutti i bottoni presenti
	 */
	public void initButtons();
	
	/**
	 * metodo che si deve occupare di disegnare tutti i bottoni presenti nello stato
	 * di gioco corrente.
	 * @param g
	 */
	public void drawButtons(Graphics g);
	
	
	
}
