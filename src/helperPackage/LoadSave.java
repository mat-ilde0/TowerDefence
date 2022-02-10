package helperPackage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 
 * @author User
 *
 */

public class LoadSave {

	/**
	 * metodo che importa l'immagine contenente tutti i tiles
	 * @return l'immagine intera 
	 */
	public static BufferedImage getSpriteAtlas() {
		BufferedImage img = null;
		
		InputStream fileImageStream = LoadSave.class.getClassLoader().
				getResourceAsStream("spriteatlas.png");
				
		try {
			img = ImageIO.read(fileImageStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
}
