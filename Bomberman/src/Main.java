
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Main {

	public static void main(String[] args) {


		Board game_board = new Board();
        //Dessiner le plateau et les joueurs
        game_board.beginGame();
        Player J1 = new Player(1); // le joueur 1 porte l'id 1 
        Player J2 = new Player(2); // le joueur 2 porte l'id 2 
        Bomb bomb_liste = new Bomb();
        boolean game_over = false;
       while(!game_over){
    	   /*Gère les déplacements des joueurs 1 et 2*/
        	J1.move(game_board,bomb_liste);
        	J2.move(game_board,bomb_liste);

        	/*Chaque joueur peut poser des bombes en appuyant soit sur espace ou sur W*/
        	bomb_liste.putBomb(game_board, J1.getId(), (int)J1.getX(), (int)J1.getY());
        	bomb_liste.putBomb(game_board, J2.getId(), (int)J2.getX(), (int)J2.getY());
        	/*Les bombes explosent 5 secondes après être déposée*/
        	bomb_liste.explose(game_board,J1,J2);
        	/*Les bombes sont affichées */
        	game_board.show_all_bombs(bomb_liste.getBombs());

        	game_over = (J1.getLife() == 0 ) || (J2.getLife() == 0); //la partie est fini si la condition vaut TRUE
        	
        }
       System.out.println("GAME OVER !!! Life of J1 : " + J1.getLife() + "\t Life of J2 : " + J2.getLife());
       

		
		
		
		
	}
	
	
	
	
}
