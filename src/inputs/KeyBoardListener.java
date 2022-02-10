package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static main.GameStates.*;

import main.GameStates;

/**
 * classe utile per la gestione degli input da tastiera.
 * @author User
 *
 */

public class KeyBoardListener implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode( ) == KeyEvent.VK_A)
			GameStates.gameState = MENU;
		else if(e.getKeyCode( ) == KeyEvent.VK_P)
			GameStates.gameState = PLAYNG;
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
