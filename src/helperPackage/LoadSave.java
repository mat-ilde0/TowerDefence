package helperPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	 * @param f  file sul quale scrivere
	 * @param idArray array contentente i dati da scrivere
	 */
	private static void writeToFile(File f, int[] idArray) {
	
		try {
			PrintWriter pw = new PrintWriter(f);
			
			for (Integer i : idArray)
				pw.println(i);
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo di lettura da file di testo
	 */
	private static ArrayList<Integer> readFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int[][] getLevelData(String name) {
		File lvlFile = new File("resources/" + name + ".txt");
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			return Utils.ArrayListTo2Dint(list, 20, 20);
		}else {
			System.out.println("File: " + name + " does not exist!");
			return null;
		}
	}
	
	public static void createLevel(String name, int[] idArray) {
		File newLevel = new File("resources/" + name + ".txt");
		
		if(newLevel.exists()) {
			System.out.println("File: " + name + " already exists!");
			return;
		}else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeToFile(newLevel, idArray);
	}
	
	/**
	 * metodo che salva effettivamente su un file di testo il livello appena creato
	 */
	public static void saveLevel(String name, int[][] idArr) {
		File levelFile = new File("resources/" + name + ".txt");
		
		if(levelFile.exists()) {
			int[] arr1d = new int[400];
			arr1d = Utils.TwoDin1DArr(idArr);
			writeToFile(levelFile, arr1d);
		}else {
			System.out.println("File: " + name + " does not exists!");
			return;
		}
	}
	
}
