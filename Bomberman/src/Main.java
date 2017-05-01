
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Main {

	public static void main(String[] args) {

		StdDraw.setCanvasSize(1050,850);
		Board game_board = new Board();
	
		/*
		 * Modifier les echelles X et Y pour avoir un système de coordonnées (X,Y)
		 *  Coordonnées (0,0) coin en bas à gauche et (17,21) coin en haut à droite
		 *  */
        StdDraw.setXscale(0 , 21);
        StdDraw.setYscale(0 , 17);
        
        //Dessiner le plateau et les joueurs
        game_board.beginGame();
        Player J1 = new Player(1); // le joueur 1 porte l'id 1 
        Player J2 = new Player(2); // le joueur 2 porte l'id 2 
      
       while(true){
        	J1.move(game_board);
        	J2.move(game_board);

        }

		
		
		
		
	}
	
	
}
