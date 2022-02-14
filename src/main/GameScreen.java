package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyBoardListener;
import inputs.MyMouseListener;

/**
 * componente che permette di disegnarci sopra e che consente di raggruppare altri componenti
 * @author User
 *
 */

public class GameScreen extends JPanel {
	
	private Game game;
	
	private Dimension size;
	

	//variabili per la gestione degli input da mouse e da tastiera
	private MyMouseListener myMouseListener;
	private KeyBoardListener myKeyboardListener;
	  
	
	//costruttore
	public GameScreen(Game game) {
		this.game = game;
		
		setPanelSize();
		
	}
	
	/**
	 * settare la dimensione del pannello
	 */
	private void setPanelSize() {
		//size = new Dimension(640, 640);
		//la grandezza viene aumentata per farci stare la nuova barra dei bottoni 
		size = new Dimension(640, 740);
		
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	/**
	 * Metodo che consente di disegnare sul componente: si usano tutti i metodi di Graphics per disegnare.
	 * 
	 * Ricorda: questo metodo non viene mai chiamato da nessun costruttore e neanche direttamente
	 * dal programmatore.
	 * Viene chiamato "automaticamente" quando si ha il bisogno di ridisegnare la finestra.
	 */
	public void paintComponent(Graphics g) {
		//super che consente di disegnare lo sfondo del componente
		super.paintComponent(g);
		
		game.getRender().render(g);
	}
	
	/**
	 * metodo che si occupa dell'inizializzazione di ogni Listener e di aggiungerli
	 */
	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		myKeyboardListener = new KeyBoardListener();
		
		//aggiunta dei listener al gioco
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(myKeyboardListener);
		
		/*
		 * The purpose of the requestFocus() is to get the focus on the 
		 * particular component and also on the window that contains the component.
		 */
		requestFocus();
	}
	
}
