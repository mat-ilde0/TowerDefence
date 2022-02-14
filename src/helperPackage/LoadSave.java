package helperPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

/**
 * Classe statica dedicata alla gesione di file legati al progetto 
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
	
	/**
	 * metodo per la creazione di un file di testo
	 */
	public static void createFile() {
		File txtFile = new File("resources/testTextFile.txt");
		
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo che consente di scrivere su un file di testo
	 */
	public static void writeToFile() {
		File txtFile = new File("resources/testTextFile.txt");
	
		try {
			PrintWriter pw = new PrintWriter(txtFile);
			pw.print("hello");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
