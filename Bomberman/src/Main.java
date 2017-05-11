
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Main {

	public static void main(String[] args) {

		
		Board game_board = new Board();
        //Dessiner le plateau et les joueurs
        game_board.beginGame();
        
        Player J1 = new Player(1); // le joueur 1 porte l'id 1 
        Player J2 = new Player(2); // le joueur 2 porte l'id 2 
        // l'objet contient une liste de Bombes vierge
        Bomb bomb_liste = new Bomb();
        Bonus bonus_liste =  new Bonus();
        // Le jeu doit reboucler � l'infini tant que les joueurs ont plus de 0 vie 
        boolean game_over = false;
       while(!game_over){
    	   /*G�re les d�placements des joueurs 1 et 2*/
        	J1.move(game_board,bomb_liste);
        	J2.move(game_board,bomb_liste);

        	/*Chaque joueur peut poser des bombes en appuyant soit sur espace ou sur W*/
        	bomb_liste.putBomb(game_board, J1);
        	bomb_liste.putBomb(game_board, J2);
        	/*Les bombes explosent 5 secondes apr�s �tre d�pos�e*/
        	bomb_liste.explose(game_board,J1,J2,bonus_liste);
        	
        	/*Les bombes et les bonus sont affich�es sur le plateau de jeu */
        	game_board.show_all_bombs(bomb_liste.getBombs());
        	game_board.show_bonus(bonus_liste.getBonus());
        	
        	/*On collecte les bonus*/
        	bonus_liste.collect_bonus(J1, J2, game_board);
        	
        	/*On synchronise les listes bombes et bonus*/
        	bonus_liste.synchro(bomb_liste);
        	
        	
        	/*Les donn�es des joueurs sont affich�s dans la console (nombre de vies et coordonn�es X et Y)*/
        	//info(J1,J2);
        	
        	/*Mise � jour du boolean game_over*/
        	game_over = (J1.getLife() <= 0 ) || (J2.getLife() <= 0); //la partie est fini si la condition vaut TRUE
        	//StdDraw.show(1000);
        	
        }
       System.out.println("FIN DE LA PARTIE !!!");
       
       int winner = 0;// le perdant est celui qui a 0 vie
       if(J1.getLife() == 0){
    	   winner = 2;
       }else{
    	   winner = 1;
       }
       //l'identité du gagnant est révelée
       game_board.game_over(winner);

		
		
		
		
	}
	
	public static void info(Player J1 , Player J2){
		System.out.println("Joueur 1  vie : " + J1.getLife() + " | X : "+ J1.getX() + " | Y : "+ J1.getY());
		System.out.println("Joueur 2  vie : " + J2.getLife() + " | X : "+ J2.getX() + " | Y : "+ J2.getY());
	}
	
	
	
}
