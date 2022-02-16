package scenes;

import main.Game;

/**
 * classe che funge da superclasse per tutte le scenes che si hanno nel gioco
 * @author User
 */

public class GameScene {

	public static final int SINGLE_IMG_WIDTH = 32; //pixel, larghezza immaginina
	public static final int COLOR_LIMIT = 256;   //valore limite colori escluso
	
	protected Game game;
	public GameScene(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
}
