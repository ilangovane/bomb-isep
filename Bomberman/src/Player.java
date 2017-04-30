import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Player {
	int id;//identifiant 
	int life = 3;//nombre de vie restant
	int column ; // position X (varie de 0 à 20)
 	int line ; // position Y (varie de 0 à 16)
	int bombs = 3;

	Player(int id){
		/* Si id vaut 1 il s'agit du joueur 1 , si 2 le joueur 2 si 3 une IA*/
		this.id = id;
		
		/*Le joueur 1 et 2 sont positionnées respectivement en (X,Y) = (1,1) et (X,Y) =  (19,15)*/
		if(this.id == 1){
			this.line = 1;
			this.column = 1;
		}else{
			this.column = 19;
			this.line = 15;
		}
	}

	/*Getters et Setters*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}



	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}
	
	/*
	 * Déplacement
	 * */
	
	public void move(Board b){
		
		//commande clavier du joueur 1 => W A S et D (Q pour poser une bombe)
		if(this.id == 1){
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
          	   System.out.println("J1 descendre");
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_S)){
          		 System.out.println("J1 gauche");
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
         		 System.out.println("J1 droite");
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_A)){
         		 System.out.println("J1 haut");
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
        		 System.out.println("J1 pose une bombe");
        	}
		}
		
		//commande clavier du joueur 2 avec les flèches (espace pour poser une bombe)
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {
           	   System.out.println("J2 descendre");
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){
           		 System.out.println("J2 gauche");
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
          		 System.out.println("J2 droite");
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
          		 System.out.println("J2 haut");
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
        		 System.out.println("J2 pose une bombe");
        	}
		}
		
		if(this.id == 3){
			
		}
		
	}
	
	
	


}
