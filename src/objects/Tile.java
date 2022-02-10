package objects;

import java.awt.image.BufferedImage;

/**
 * classe che rappresenta la singola tegola di terreno, per la quale si indica
 * se su di essa è possibile camminarci oppure se ci si può costruire.
 * @author User
 *
 */

public class Tile {
	
	private BufferedImage sprite;
	
	public Tile(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

}
