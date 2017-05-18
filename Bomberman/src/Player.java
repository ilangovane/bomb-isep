import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;

public class Player {
	int id;//identifiant 
	int life = 3;//nombre de vie restant
	double X ; // position X (varie de 0 ï¿½ 20)
 	double Y ; // position Y (varie de 0 ï¿½ 16)
	double dX = 0.2 ; // le plus petit dï¿½placement horizontal (dï¿½finit la vitesse)
	double dY = 0.2 ; // le plus petit dï¿½placement vertical (dï¿½finit la vitesse)
	int nb_bomb = 3;// nombre de bombes que le joueur peut poser sur le terrain sumultanement
	boolean shield;//indique si le joueur possède le bonus "Bouclier à usage unique"
	boolean passe_muraille;
	Player(int id){
		/* Si id vaut 1 il s'agit du joueur 1 , si 2 le joueur 2 si 3 une IA*/
		this.id = id;
		this.shield = false;
		this.passe_muraille = false;
		/*Le joueur 1 et 2 sont positionnï¿½es respectivement en (X,Y) = (1,1) et (X,Y) =  (19,15)*/
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

	public int getNb_bomb() {
		return nb_bomb;
	}

	public void setNb_bomb(int nb_bomb) {
		this.nb_bomb = nb_bomb;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLife() {
		return life;
	}

	public double getdX() {
		return dX;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}

	public double getdY() {
		return dY;
	}

	public void setdY(double dY) {
		this.dY = dY;
	}

	public void setLife(int life) {
		this.life = life;
	}



	


	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	
	public boolean isPasse_muraille() {
		return passe_muraille;
	}

	public void setPasse_muraille(boolean passe_muraille) {
		this.passe_muraille = passe_muraille;
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
	 * Les dï¿½placements des joueurs se font sur cette mï¿½thode
	 * Joueur 1 : Q => Gauche / S => BAS / D => DROITE / Z => HAUT / W => BOMBE
	 * Joueur 2 : fleches de dï¿½placements et espace pour poser une bombe 
	 * 
	 * 
	 * */
	public void move(Board b,Bomb bo){
		int[][] map = b.getMatrice();
		//commande clavier du joueur 1 
		if(this.id == 1){
            if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S pressï¿½e
		           	if(	this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){

		        		b.repaint((int) this.getY(), (int) this.getX());
		        		this.setY(this.getY()- dY);
		        		
		        	
		        	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q pressï¿½e
          		 
	             	if(this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	          
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()-dX);	
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D pressï¿½e
          		
	         		if(this.go_through_destructible_wall( b , (int) this.getX()+1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	         		
	         			b.repaint((int) this.getY() , (int) this.getX());
	            		this.setX(this.getX()+dX);
	            		
	            	}
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z pressï¿½e
	             	if(this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setY(this.getY()+dY);
	            		
	            	}
         	}
		}
		
		//commande clavier du joueur 2 
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {//touche BAS pressï¿½e
            	
            		if(	this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
	     
            			b.repaint((int) this.getY() , (int) this.getX());
	            		this.setY(this.getY()-dY);
	            		
	            	
	            	}
            		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){//touche GAUCHE pressï¿½e
           		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	            	if(this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY() ) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	           
	            		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()-dX);	
	            		
	            	}
           		 
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche DROITE pressï¿½e
          		 
           		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	             	if(this.go_through_destructible_wall( b , (int) this.getX() + 1, (int) this.getY() ) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	             	
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()+dX);
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche HAUT pressï¿½e
          		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	             	if(this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             	
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setY(this.getY()+dY);
	            		
	            	}
          	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){//touche ESPACE pressï¿½e
        		 System.out.println("J2 pose une bombe");
        	}*/
		}
		

		
		
		// affiche le joueur sur la case 
		b.setPlayer(this.getId(), (int) this.getX(), (int) this.getY());
		
		
	}
	
	/*La mÃ©thode renvoie vrai si le joueur est sur la case (X,Y) : pratique pour les explosions de bombes*/
	public boolean is_at_point(int x , int y){
		if((int) this.getX() == x && (int) this.getY() == y){
			return true;
		}else{
			return false;
		}
	}
	
	public void kill(){
		// le joueur perd une vie
		
		if(this.isShield()){
			this.setShield(false);//bonus à usage unique
		}else{
			this.setLife( this.getLife() - 1 );
			// lorsqu'un joueur perd la vie, il est temporairement placï¿½ dans un lieu sï¿½re
			this.setX(-1);
			this.setY(-1);
		}
	}
	
	public void passe_muraille(){
		
	}
	
	/*Le joeurs qui vient de perdre la vie ne risque pas de perdre une vie dï¿½s qu'il sera replacï¿½ sur une case de dï¿½part
	 * Le joueur est positionnï¿½ sur le recoin (X,Y) = (-1,-1) pour des raisons de sï¿½curitï¿½s si des bombes sont a proximitï¿½s
	 * Cela permettra d'ï¿½viter de predre 2 ou 3 vie au lieu d'une*/

	public void avoid_killing_player_two_times(Board b , Set<Bomb> list ){
		int i = 0;
		Bomb bo = new Bomb();
		bo.setBombs(list); // l'objet contient la liste de bombe 
		boolean safe = true;// le joueurs peut revenir ï¿½ la position de resurrection
		if(this.getId() == 1 ){
			// pour le Joueur 1 
			while(i<=3){
				// le joueur aprï¿½s avoir perdu une vie et placï¿½ temporairement ï¿½ la position (-1,-1)
				if(this.getX() == -1  && this.getY() == -1 && ( bo.is_bomb_already_exists(1, 1+i) ||bo.is_bomb_already_exists(i+1,1))  ){
					// si une bombe est placï¿½ ï¿½ proximitï¿½, il mourra inexorablement
					safe = false;
				}
				i++;
			}
			// le joueur ne risque rien , le joueur peut ï¿½ nouveau controler son personnage
			if(safe == true && this.getX() == -1  && this.getY() == -1 && !bo.is_bomb_already_exists(1, 1+i) && !bo.is_bomb_already_exists(i+1,1)){
				this.setX(1);  
				this.setY(1);
				b.setArea(0,0, "grey");
			}
		}else if(this.getId() ==2){
			//mï¿½me commentaire que le joueur 1
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
	
	public boolean go_through_destructible_wall(Board b , int x , int y){
		if(this.isPasse_muraille() && b.isDestructible(y,x)){
			return true;
		}
		return false;
	}
	
	public void kick(Bomb bombe){
		if(StdDraw.isKeyPressed(KeyEvent.VK_X) && this.getId() == 1){
			
		}else if(StdDraw.isKeyPressed(KeyEvent.VK_3) && this.getId() == 2){// touche 3 enfoncée
			
		}
	}


}
