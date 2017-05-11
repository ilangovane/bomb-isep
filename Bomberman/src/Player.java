import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;

public class Player {
	int id;//identifiant 
	int life = 3;//nombre de vie restant
	double X ; // position X (varie de 0 � 20)
 	double Y ; // position Y (varie de 0 � 16)
	double dX = 0.03 ; // le plus petit d�placement horizontal (d�finit la vitesse)
	double dY = 0.03 ; // le plus petit d�placement vertical (d�finit la vitesse)
	int nb_bomb = 3;// nombre de bombes que le joueur peut poser sur le terrain sumultanement
	Player(int id){
		/* Si id vaut 1 il s'agit du joueur 1 , si 2 le joueur 2 si 3 une IA*/
		this.id = id;
		
		/*Le joueur 1 et 2 sont positionn�es respectivement en (X,Y) = (1,1) et (X,Y) =  (19,15)*/
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
	 * Les d�placements des joueurs se font sur cette m�thode
	 * Joueur 1 : Q => Gauche / S => BAS / D => DROITE / Z => HAUT / W => BOMBE
	 * Joueur 2 : fleches de d�placements et espace pour poser une bombe 
	 * 
	 * 
	 * */
	public void move(Board b,Bomb bo){
		//commande clavier du joueur 1 
		if(this.id == 1){
            if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S press�e
		           	if(	b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
		          
		        		b.setArea((int)this.getY(), (int)this.getX(), "green");
		        		this.setY(this.getY()- dY);
		        		
		        	
		        	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q press�e
          		 
	             	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	          
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D press�e
          		
	         		if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	         		
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		
	            	}
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z press�e
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		
	            	}
         	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_W)){//touche W press�e
        		 System.out.println("J1 pose une bombe");
        	}*/
		}
		
		//commande clavier du joueur 2 
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {//touche BAS press�e
            	
            		if(	b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
	     
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setY(this.getY()-dY);
	            		
	            	
	            	}
            		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){//touche GAUCHE press�e
           		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	            	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	           
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		
	            	}
           		 
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche DROITE press�e
          		 
           		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	             	if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	             	
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche HAUT press�e
          		System.out.println("POS J2 X : " + this.getX() + " Y: "+ this.getY());
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             	
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		
	            	}
          	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){//touche ESPACE press�e
        		 System.out.println("J2 pose une bombe");
        	}*/
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
	
	
	/*Le joeurs qui vient de perdre la vie ne risque pas de perdre une vie d�s qu'il sera replac� sur une case de d�part
	 * Le joueur est positionn� sur le recoin (X,Y) = (-1,-1) pour des raisons de s�curit�s si des bombes sont a proximit�s
	 * Cela permettra d'�viter de predre 2 ou 3 vie au lieu d'une*/

	public void avoid_killing_player_two_times(Board b , Set<Bomb> list ){
		int i = 0;
		Bomb bo = new Bomb();
		bo.setBombs(list); // l'objet contient la liste de bombe 
		boolean safe = true;// le joueurs peut revenir � la position de resurrection
		if(this.getId() == 1 ){
			// pour le Joueur 1 
			while(i<=3){
				// le joueur apr�s avoir perdu une vie et plac� temporairement � la position (-1,-1)
				if(this.getX() == -1  && this.getY() == -1 && ( bo.is_bomb_already_exists(1, 1+i) ||bo.is_bomb_already_exists(i+1,1))  ){
					// si une bombe est plac� � proximit�, il mourra inexorablement
					safe = false;
				}
				i++;
			}
			// le joueur ne risque rien , le joueur peut � nouveau controler son personnage
			if(safe == true && this.getX() == -1  && this.getY() == -1 && !bo.is_bomb_already_exists(1, 1+i) && !bo.is_bomb_already_exists(i+1,1)){
				this.setX(1);  
				this.setY(1);
				b.setArea(0,0, "grey");
			}
		}else if(this.getId() ==2){
			//m�me commentaire que le joueur 1
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
