package userInterface;

import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

/**
 * base per Playing e ButtonBar
 * @author User
 *
 */

public class Bar {
	
	protected int x, y, width, height;
		
	public Bar (int x, int y, int width, int height) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
	}
	 
}
