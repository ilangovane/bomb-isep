import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;

public class Player {
	int id;//identifiant 
	int life = 3;//nombre de vie restant
	double X ; // position X (varie de 0 à 20)
 	double Y ; // position Y (varie de 0 à 16)
	double dX = 0.03 ; // le plus petit déplacement horizontal (définit la vitesse)
	double dY = 0.03 ; // le plus petit déplacement vertical (définit la vitesse)

	Player(int id){
		/* Si id vaut 1 il s'agit du joueur 1 , si 2 le joueur 2 si 3 une IA*/
		this.id = id;
		
		/*Le joueur 1 et 2 sont positionnées respectivement en (X,Y) = (1,1) et (X,Y) =  (19,15)*/
		if(this.id == 1){
			this.Y = 1;
			this.X = 1;
		}else{
			this.X = 19;
			this.Y = 15;
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



	


	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}


	

	/*
	 * Les déplacements des joueurs se font sur cette méthode
	 * Joueur 1 : Q => Gauche / S => BAS / D => DROITE / Z => HAUT / W => BOMBE
	 * Joueur 2 : fleches de déplacements et espace pour poser une bombe 
	 * 
	 * 
	 * */
	public void move(Board b,Bomb bo){
		//commande clavier du joueur 1 
		if(this.id == 1){
            if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S pressée
		           	if(	b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
		          
		        		b.setArea((int)this.getY(), (int)this.getX(), "green");
		        		this.setY(this.getY()- dY);
		        		
		        	
		        	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q pressée
          		 
	             	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	          
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D pressée
          		
	         		if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	         		
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		
	            	}
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z pressée
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		
	            	}
         	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_W)){//touche W pressée
        		 System.out.println("J1 pose une bombe");
        	}*/
		}
		
		//commande clavier du joueur 2 
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {//touche BAS pressée
            	
            		if(	b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
	     
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setY(this.getY()-dY);
	            		
	            	
	            	}
            	
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){//touche GAUCHE pressée
           		
	            	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	           
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		
	            	}
           		 
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche DROITE pressée
          		 
          		 
	             	if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	             	
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche HAUT pressée
          		 
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             	
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		
	            	}
          	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){//touche ESPACE pressée
        		 System.out.println("J2 pose une bombe");
        	}*/
		}
		
		if(this.id == 3){
			//IA
		}
		
		
		// affiche le joueur sur la case 
		b.setPlayer(this.getId(), (int) this.getX(), (int) this.getY());
		
		
	}
	
	/*La méthode renvoie vrai si le joueur est sur la case (X,Y) : pratique pour les explosions de bombes*/
	public boolean is_at_point(int x , int y){
		if((int) this.getX() == x && (int) this.getY() == y){
			return true;
		}else{
			return false;
		}
	}
	
	public void kill(){
		// le joueur perd une vie
		this.setLife( this.getLife() - 1 );
	}
	
	
	/*Le joeurs qui vient de perdre la vie ne risque pas de perdre une vie dès qu'il sera replacé sur une case de départ
	 * Le joueur est positionné sur le recoin (X,Y) = (-1,-1) pour des raisons de sécurités si des bombes sont a proximités
	 * Cela permettra d'éviter de predre 2 ou 3 vie au lieu d'une*/

	public void avoid_killing_player_two_times(Board b , Set<Bomb> list ){
		int i = 0;
		Bomb bo = new Bomb();
		bo.setBombs(list); // l'objet contient la liste de bombe 
		boolean safe = true;// le joueurs peut revenir à la position de resurrection
		if(this.getId() == 1 ){
			// pour le Joueur 1 
			while(i<=3){
				// le joueur après avoir perdu une vie et placé temporairement à la position (-1,-1)
				if(this.getX() == -1  && this.getY() == -1 && ( bo.is_bomb_already_exists(1, 1+i) ||bo.is_bomb_already_exists(i+1,1))  ){
					// si une bombe est placé à proximité, il mourra inexorablement
					safe = false;
				}
				i++;
			}
			// le joueur ne risque rien , le joueur peut à nouveau controler son personnage
			if(safe == true && this.getX() == -1  && this.getY() == -1 && !bo.is_bomb_already_exists(1, 1+i) && !bo.is_bomb_already_exists(i+1,1)){
				this.setX(1);  
				this.setY(1);
				b.setArea(0,0, "grey");
			}
		}else if(this.getId() ==2){
			//même commentaire que le joueur 1
			while(i<=3){
				if(this.getX() == -1  && this.getY() == -1 && ( bo.is_bomb_already_exists(19, 15-i) ||bo.is_bomb_already_exists(19-i,15))  ){
					safe = false;
				}
				i++;
			}
			if(safe == true && this.getX() == -1  && this.getY() == -1 &&  !bo.is_bomb_already_exists(1, 1+i) && !bo.is_bomb_already_exists(i+1,1)) {
				this.setX(19);  
				this.setY(15);
				b.setArea(0,0, "grey");
			}
		}
	}
	


}
