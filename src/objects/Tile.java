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
	private int id;   //serve per identificare il tile specifico
	private String name;
	
	public Tile(BufferedImage sprite, int id, String name) {
		this.sprite = sprite;
		this.id = id;
		this.name = name;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
