package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import helperPackage.LoadSave;
import main.Game;

public class Menu extends GameScene implements SceneMethods{

	private BufferedImage img;
	
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;
	
	public Menu(Game game) {
		super(game);
		
		random = new Random();
		
		importImg();
		
		loadSprites();
	}

	private void importImg() {
		
		InputStream fileImageStream = getClass().getResourceAsStream("/spriteatlas.png");
				
		try {
			img = ImageIO.read(fileImageStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(Graphics g) {
		for(int i = 0; i<20; i++) {
			for(int j = 0; j<20; j++) {
				g.drawImage(sprites.get(getRandomInt()), i*SINGLE_IMG_WIDTH, j*SINGLE_IMG_WIDTH, null);
			}
		}
	}
	
	/**
	 * metodo per caricare tutte le immaginine (i singoli quadratini) come singole immagini
	 * ossia quelle che costituiscono lo sprite presente nelle res.
	 */
	private void loadSprites() {
		//(le immaginine sono 10 per riga e 10 per colonna, di dim 32 pixel)
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++) {
				//BufferedImage singleImg = img.getSubimage(i * SINGLE_IMG_WIDTH, j * SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH); 
				sprites.add(img.getSubimage(i * SINGLE_IMG_WIDTH, j * SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH, SINGLE_IMG_WIDTH));
			}
		}
		
	}
	
	/**
	 *utilizzato per riempire a caso la finestra con le immaginine
	 */
	private int getRandomInt() {
		return random.nextInt(100);
	}

}
