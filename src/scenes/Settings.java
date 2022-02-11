package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class Settings extends GameScene implements SceneMethods{

	public Settings(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 640, 640);
		
	}

	@Override
	public void mouseClicked(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int xCord, int yCord) {
		// TODO Auto-generated method stub
		
	}

}
