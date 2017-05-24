import java.awt.Font;

import edu.princeton.cs.introcs.StdDraw;

public class Menu {
	/*
	 * valeurs prises par ChoixMenu : "home" , "multiplayers" , "IA" , "exit" , "instructions"
	 * */
	private String ChoixMenu;
	
	public Menu(){
		StdDraw.setCanvasSize(40*30,21*30);
		/*
		 * Modifier les echelles X et Y pour avoir un syst�me de coordonn�es (X,Y)
		 *  Coordonn�es (0,0) coin en bas � gauche et (17,21) coin en haut � droite
		 *  */
        StdDraw.setXscale(0 , 30);
        StdDraw.setYscale(0 , 17);
		this.ChoixMenu = "home";
	}
	public String getChoixMenu() {
		return ChoixMenu;
	}


	public void setChoixMenu(String choixMenu) {
		ChoixMenu = choixMenu;
	}
	public void game_over(int winner){
		//StdDraw.setCanvasSize(25*30,22*30);
		/*
		 * Modifier les echelles X et Y pour avoir un syst�me de coordonn�es (X,Y)
		 *  Coordonn�es (0,0) coin en bas � gauche et (17,21) coin en haut � droite
		 *  */
        //StdDraw.setXscale(0 , 21);
       // StdDraw.setYscale(-1 , 18);// de -2->0 et 17->19 infos des joueurs
	    StdDraw.clear(); // On clear la fenêtre
		Font font = new Font("Bomberman" , Font.ITALIC , 30);
		StdDraw.setFont(font);
		StdDraw.picture(10.5, 10, "/bomberman_picture/endgame.png",22,22);
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		if(winner!=0){
			StdDraw.text(10.5, 3, "Le joueur " + winner + " à gagné");
		}else{
			StdDraw.text(10.5, 3, "Match null");
		}
		
		StdDraw.show(30);
		
		/*BOUTON RETOUR AU MENU PRINCIPAL*/
		int nbligne = 2;					//nombre de ligne
		int nbcolonne = 21;					//nombre de colonne
		float centerL = (float)(nbligne/2);
		float centerC = (float)(nbcolonne/2);
		int largeurRect = 3;
		float hauteurRect = 0.5f;			
		this.setChoixMenu("gameover");
				displayRect(centerC, centerL, largeurRect, hauteurRect,"Menu");
				StdDraw.show(30);
				if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect && StdDraw.mouseY() <= centerL + hauteurRect){
				// Petit effet quand on place la souris sur le bouton
				displayEffect(centerC, centerL, largeurRect, hauteurRect,"Menu");
				StdDraw.show(30);
				if(StdDraw.mousePressed()){
				setChoixMenu("home");
				}
			}	
	}
	
	public void displayRect(float colonne, float ligne, int largeur,float hauteur, String contenu){
		StdDraw.setPenColor(StdDraw.BLACK);								//  Couleur noir pour l'écriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		Font font = new Font("Bomberman", Font.ROMAN_BASELINE, 28);		//	Initialisation de la police
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);					//  Couleur pour l'écriture
		StdDraw.text(colonne, ligne, contenu);
	}
	
	public void displayEffect(float colonne, float ligne, int largeur,float hauteur, String contenu){
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);						//  Couleur noir pour l'écriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		Font font = new Font("Bomberman", Font.ITALIC, 20);//	Initialisation de la police
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.RED);			//  Couleur pour l'écriture
		StdDraw.text(colonne, ligne, contenu);
	}
	
	public void menu(){ // Afficher le menu principal
		int nbligne = 17;					//nombre de ligne
		int nbcolonne = 30;					//nombre de colonne
		float centerL = (float)(nbligne/2);
		float centerC = (float)(nbcolonne/2);
		int decalage = 2;
		int largeurRect = 4;
		float hauteurRect = 0.5f;			
		StdDraw.clear(StdDraw.PRINCETON_ORANGE);				//  Fond d'écran 
		//StdDraw.picture(15, 13, "stars.gif");

		/*BOUTON JOUER*/
		displayRect(centerC, centerL, largeurRect, hauteurRect,"Multijoueurs");		
		
		/*BOUTON IA*/
		displayRect(centerC, centerL - decalage, largeurRect, hauteurRect, "Mode 1 joueur");	
		
		/*BOUTON Instructions*/
		displayRect(centerC, centerL - 2*decalage, largeurRect, hauteurRect,"Instructions");	
		
		/*BOUTON QUITTER*/
		displayRect(centerC, centerL - 3*decalage, largeurRect, hauteurRect, "Quitter");	

		/*On affiche le logo bomberman*/
		StdDraw.picture(15, 13, "/bomberman_picture/banner.png");
		
		/*A gauche on met le joueur 1*/
		StdDraw.picture(5, 8, "/bomberman_picture/p1.png", 8,11);
		
		/*A droite le joueur 2*/
		StdDraw.picture(25, 8, "/bomberman_picture/p2.png");
		
		
			if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect && StdDraw.mouseY() <= centerL + hauteurRect){
				// Petit effet quand on place la souris sur le bouton
				displayEffect(centerC, centerL, largeurRect, hauteurRect,"Jouer");		//  Dessine un rectangle
				if(StdDraw.mousePressed()){
				setChoixMenu("multiplayers");
				//System.out.println(centerL - hauteurRect +""+ centerL + hauteurRect);
				}
			}
			else if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect - decalage && StdDraw.mouseY() <= centerL + hauteurRect - decalage){
				displayEffect(centerC, centerL - decalage, largeurRect, hauteurRect, "Jouer");	
				if(StdDraw.mousePressed()){
				setChoixMenu("IA"); // ce sera a changé
				}
			}
			else if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect - 2*decalage && StdDraw.mouseY() <= centerL + hauteurRect - 2*decalage){
				displayEffect(centerC, centerL - 2*decalage, largeurRect, hauteurRect,"Instructions");	
				if(StdDraw.mousePressed()){
				setChoixMenu("instructions"); // ce sera a changé
				}
			}
			else if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect - 3*decalage && StdDraw.mouseY() <= centerL + hauteurRect - 3*decalage){
				displayEffect(centerC, centerL - 3*decalage, largeurRect, hauteurRect,"Quitter");	
				if(StdDraw.mousePressed()){
			
				setChoixMenu("exit");
				
				}
		
			}
	}
	public int  start_game(boolean IA){

        //Dessiner le plateau et les joueurs
		Board game_board = new Board();
        game_board.beginGame();
        Player J1 = new Player(1); // le joueur 1 porte l'id 1 
        Player J2 = new Player(2); // le joueur 2 porte l'id 2 
        // l'objet contient une liste de Bombes vierge
        Bomb bomb_liste = new Bomb();
        Bonus bonus_liste =  new Bonus();
        Animation animation_liste = new Animation();
        // Le jeu doit reboucler � l'infini tant que les joueurs ont plus de 0 vie 
        boolean game_over = false;
		while(!game_over){
			//System.out.println("La vitesse est : "+J2.getdX() +"!!!!!!!!");

	    	   /*G�re les d�placements des joueurs 1 et 2*/
	        	J1.move(game_board,bomb_liste);
	        	J2.move(game_board,bomb_liste);
	        	J1.kick(bomb_liste, game_board,animation_liste);
	        	J2.kick(bomb_liste, game_board,animation_liste);
	        	/*Chaque joueur peut poser des bombes en appuyant soit sur espace ou sur W*/
	        	bomb_liste.putBomb(game_board, J1,bonus_liste,animation_liste);
	        	bomb_liste.putBomb(game_board, J2,bonus_liste,animation_liste);
	        	/*Les bombes explosent 5 secondes apr�s �tre d�pos�e*/
	        	bomb_liste.explose(game_board,J1,J2,bonus_liste,animation_liste);
	        	
	        	/*Les bombes et les bonus sont affich�es sur le plateau de jeu */
	        	game_board.show_all_bombs(bomb_liste.getBombs());
	        	animation_liste.bomb_timer(bomb_liste);
	        	game_board.show_bonus(bonus_liste.getBonus());
	        	
	        	/*On collecte les bonus*/
	        	bonus_liste.collect_bonus(J1, J2, game_board);
	        	
	        	/*On synchronise les listes bombes et bonus*/
	        	bonus_liste.synchro(bomb_liste);
	        	
	        	
	        	/*Les donn�es des joueurs sont affich�s dans la console (nombre de vies et coordonn�es X et Y)*/
	        	//info(J1,J2);
	        	/*Animation */
	        	animation_liste.display_effects(game_board);
	        	StdDraw.show(30);
	        	/*Mise � jour du boolean game_over*/
	        	game_over = (J1.getLife() <= 0 ) || (J2.getLife() <= 0); //la partie est fini si la condition vaut TRUE
	        	game_board.info_players(J1, J2,bomb_liste,bonus_liste);
	        	StdDraw.show(30);
	        	
	        }
			//String temp = this.getChoixMenu();
			//System.out.println(temp);
	       //l'identité du gagnant est révelée
	      this.setChoixMenu("gameover");

	      
	      
	     
	      game_board.finalize();
	      int winner = 0 ;
	      if(J1.getLife() == 0 	&& J2.getLife() != 0 ){
	    	   winner = 2;
	    	  
	       }else if(J2.getLife() == 0 && J1.getLife() != 0){
	    	   winner = 1;
	    	   
	       }
	      this.game_over(winner);
	      return winner;
	      
	}
	public void finalize(){
		
	}
}