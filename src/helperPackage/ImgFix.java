package helperPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * classe contentente metodi utili per la gestione degli sprites, per poterli ruotare
 * @author User
 *
 */

public class ImgFix {
	
	/**
	 * metodo che consente di ruotare le immaginine solo per incrementi di 90 gradi.
	 * L'immagine non viene renderizzata direttamente sullo screen ma in una {@link BufferedImage}
	 * che viene poi ritornata, ossia newImg.
	 * (NB: la rotazione avviene in senso orario).
	 */
	public static BufferedImage getRotatediImg(BufferedImage img, int rotAngle){
		int w = img.getWidth();
		int h = img.getHeight();
		
		//Creates a Graphics2D, which can be used to draw into this BufferedImage.
		BufferedImage newImg = new BufferedImage(w, h, img.getType());
		Graphics2D g2d = newImg.createGraphics();
		
		//il metodo necessita di radianti e del centr di rotazione dell'immagine
		g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose(); //cessa di usare tutte le impostazioni usate, come rotate
		//se dispose() non venisse usato allora verrebbero ruotate anche tutte le altre immagini
		//disegnate in seguito.
		
		return newImg;
	}
	
	/**
	 * metodo che distingue i diversi livelli all'interno della scena ambientale.
	 * Sostanzialmente disegna gli sprites passati uno sopra l'altro.
	 */
	public static BufferedImage buildImg(BufferedImage[] imgs) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();
		
		//Creates a Graphics2D, which can be used to draw into this BufferedImage.
		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for (BufferedImage img : imgs) {
			//layer 0 -> water = backgrownd
			//layer 1 -> grass
			g2d.drawImage(img, 0, 0, null);
		}
		g2d.dispose();
		
		return newImg;
		
	}
	
	/**
	 * metodo che ruota solo la seconda immagine dello sprite, ossia il secondo strato,
	 * quello più alto. Ad esempio per lo sprite costituito da acqua e dal contorno
	 * di terra -> viene ruotato solo il contorno di terra.
	 */
	public static BufferedImage getRotate2ndLayer(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();
		
		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for(int i = 0; i < imgs.length; i++) {
			if(rotAtIndex == i)
				g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
			g2d.drawImage(imgs[i], 0, 0, null);
			//si reimposta la rotazione normale per evitare che tutte le altre vengano
			//ruotate.
			if(rotAtIndex == i)
				g2d.rotate(Math.toRadians(-rotAngle), w/2, h/2);
		}
		g2d.dispose();
		
		return newImg;
		
	}

}
