package main;

/**
 * classe che rappresenta tutti i vari stati del gioco e in base allo stato 
 * in cui si trova il gioco verrà disegnato uno scenario diverso.
 * @author User
 *
 */

public enum GameStates {
	
	PLAYNG,
	MENU,
	SETTINGS;
	
	public static GameStates gameState = MENU; //lo stato con cui si comincia
}
