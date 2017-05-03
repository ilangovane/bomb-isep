import java.awt.event.KeyEvent;


import edu.princeton.cs.introcs.StdDraw;

public class Player {
	int id;//identifiant 
	int life = 3;//nombre de vie restant
	double X ; // position X (varie de 0 à 20)
 	double Y ; // position Y (varie de 0 à 16)
	double dX = 0.05 ; // le plus petit déplacement horizontal (définit la vitesse)
	double dY = 0.05 ; // le plus petit déplacement vertical (définit la vitesse)

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
		           		System.out.println("J1 DOWN");
		        		b.setArea((int)this.getY(), (int)this.getX(), "green");
		        		this.setY(this.getY()- dY);
		        		
		        		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
		        	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q pressée
          		 
	             	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	             		System.out.println("J1 LEFT");
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D pressée
          		
	         		if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	         			System.out.println("J1 RIGHT");
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z pressée
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		System.out.println("J1 UP");
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
         	}/*else if(StdDraw.isKeyPressed(KeyEvent.VK_W)){//touche W pressée
        		 System.out.println("J1 pose une bombe");
        	}*/
		}
		
		//commande clavier du joueur 2 
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {//touche BAS pressée
            	
            		if(	b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
	            		System.out.println("J2 DOWN");
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setY(this.getY()-dY);
	            		
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
            	
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){//touche GAUCHE pressée
           		
	            	if(b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	            		System.out.println("J2 LEFT");
	            		b.setArea((int)this.getY(), (int)this.getX(), "green");
	            		this.setX(this.getX()-dX);	
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
           		 
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche DROITE pressée
          		 
          		 
	             	if(b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	             		System.out.println("J2 RIGHT");
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setX(this.getX()+dX);
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche HAUT pressée
          		 
	             	if(b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		System.out.println("J2 UP");
	            		b.setArea((int) this.getY(), (int) this.getX(), "green");
	            		this.setY(this.getY()+dY);
	            		System.out.println("ID : " + this.getId() + "	| X :  " + this.getX() + "|  Y :  " + this.getY() );
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
	
	
	


}
