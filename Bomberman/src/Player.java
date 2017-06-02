import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.princeton.cs.introcs.StdDraw;

public class Player {
	private int id;//identifiant 
	private int life = 3;//nombre de vie restant
	private float X ; // position X (varie de 0 � 20)
	private float Y ; // position Y (varie de 0 � 16)
	private float dX = 0.2f ; // le plus petit d�placement horizontal (d�finit la vitesse)
	private float dY = 0.2f ; // le plus petit d�placement vertical (d�finit la vitesse)
	private int nb_bomb = 3;// nombre de bombes que le joueur peut poser sur le terrain sumultanement
	private boolean shield;//indique si le joueur poss�de le bonus "Bouclier � usage unique"
	private boolean passe_muraille;
	private boolean kick;
	Player(int id){
		/* Si id vaut 1 il s'agit du joueur 1 , si 2 le joueur 2 si 3 une IA*/
		this.id = id;
		this.shield = false;
		this.passe_muraille = false;
		this.kick = false;
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

	public float getdX() {
		return dX;
	}

	public void setdX(float dX) {
		this.dX = dX;
	}

	public float getdY() {
		return dY;
	}

	public void setdY(float dY) {
		this.dY = dY;
	}

	public void setLife(int life) {
		this.life = life;
	}



	


	public boolean isKick() {
		return kick;
	}

	public void setKick(boolean kick) {
		this.kick = kick;
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

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}


	

	/*
	 * Les d�placements des joueurs se font sur cette m�thode
	 * Joueur 1 : Q => Gauche / S => BAS / D => DROITE / Z => HAUT / W => BOMBE
	 * Joueur 2 : fleches de d�placements et espace pour poser une bombe 
	 * 
	 * 
	 * */
	
	public void move(Board b,Bomb bo,Player other) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
	
		//commande clavier du joueur 1 
		if(this.id == 1){
            if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S press�e
	            	if(this.colision_player(other, (int) this.getX(), (int) this.getY() -1 )){
	            		return;
	            	}
		           	if(bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()-1)	|| this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){

		        		b.repaint((int) this.getY(), (int) this.getX());
		        		this.setY(this.getY()- dY);
		        		
		        	
		        	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q press�e
	            	if(this.colision_player(other, (int) this.getX()-1, (int) this.getY() )){
	            		return;
	            	}
	             	if(bo.is_there_mine_bomb((int) this.getX()-1,(int) this.getY())	|| this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	          
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()-dX);	
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D press�e
	            	if(this.colision_player(other, (int) this.getX()+1, (int) this.getY() )){
	            		return;
	            	}
	         		if(bo.is_there_mine_bomb((int) this.getX()+1,(int) this.getY())	|| this.go_through_destructible_wall( b , (int) this.getX()+1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	         		
	         			b.repaint((int) this.getY() , (int) this.getX());
	            		this.setX(this.getX()+dX);
	            		
	            	}
         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z press�e
	            	if(this.colision_player(other, (int) this.getX(), (int) this.getY() +1 )){
	            		return;
	            	}
	             	if(bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()+1)	|| this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             		
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setY(this.getY()+dY);
	            		
	            	}
         	}
		}
		
		//commande clavier du joueur 2 
		if(this.id == 2 ){
			
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN )) {//touche BAS press�e
	            	if(this.colision_player(other, (int) this.getX(), (int) this.getY() -1 )){
	            		return;
	            	}
            		if(	bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()-1)	|| this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
	     
            			b.repaint((int) this.getY() , (int) this.getX());
	            		this.setY(this.getY()-dY);
	            		
	            	
	            	}
            		
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT )){//touche GAUCHE press�e
	            	if(this.colision_player(other, (int) this.getX()-1, (int) this.getY()  )){
	            		return;
	            	}
	            	if(bo.is_there_mine_bomb((int) this.getX()-1,(int) this.getY())	||  this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY() ) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
	           
	            		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()-dX);	
	            		
	            	}
           		 
           	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche DROITE press�e
	            	if(this.colision_player(other, (int) this.getX()+1, (int) this.getY()  )){
	            		return;
	            	}
	             	if(bo.is_there_mine_bomb((int) this.getX()+1,(int) this.getY())	||  this.go_through_destructible_wall( b , (int) this.getX() + 1, (int) this.getY() ) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
	             	
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setX(this.getX()+dX);
	            		
	            	}
          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche HAUT press�e
	            	if(this.colision_player(other, (int) this.getX(), (int) this.getY() +1)){
	            		return;
	            	}
	             	if(bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()+1)	||  this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
	             	
	             		b.repaint((int) this.getY(), (int) this.getX());
	            		this.setY(this.getY()+dY);
	            		
	            	}
          	}
		}

		// affiche le joueur sur la case 
		b.setPlayer(this.getId(), (int) this.getX(), (int) this.getY());
		//StdDraw.show(30);
		//StdDraw.picture((int) this.getX() + 0.5, (int) this.getY() + 0.5, "/bomberman_picture/grass.jpg",1,1);
		//StdDraw.show(30);

	}
	
	/*Detecter les colisions de deux joueurs*/
	public boolean colision_player(Player other , int x , int y){
		if(other.is_at_point(x,y)){
			return true;
		}
		return false;
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
		if(this.isShield()){
			this.setShield(false);//bonus � usage unique
		}else{
			this.setLife( this.getLife() - 1 );
			// lorsqu'un joueur perd la vie, il est temporairement plac� dans un lieu s�re
			this.setX(-1);
			this.setY(-1);
			this.setdX(0.2f);
			this.setdY(0.2f);
			this.setNb_bomb(3);
			this.setPasse_muraille(false);
			this.setKick(false);
			
		}
	}
	
	public void passe_muraille(){
		
	}
	
	/*Le joueur qui vient de perdre la vie ne risque pas de perdre une vie d�s qu'il sera replac� sur une case de d�part
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
	
	public boolean go_through_destructible_wall(Board b , int x , int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

		if(this.isPasse_muraille() && b.isDestructible(y,x)){
			//audioPass.start();
			//audioPass.repeat();
			return true;
		}
		//audioPass.stop();

		return false;
	}
	
	public void kick(Bomb bombe,Board b,Animation anim,Player other) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		if(!kick){
			return;//si le joueur n'a pas de kick , le reste n'est pas execut�
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_X) && this.getId() == 1){
			
	           if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S press�e
	        	   
	            	Bomb bo = bombe.find_Bomb((int) this.getX(), (int) this.getY()-1);
	            	
	            	if(bo.getX() > 0 && bo.getY() > 0 ){
	            		b.setArea((int) this.getY()-1, (int) this.getX(), "green");
	            		int x = bo.getX();
	            		int y = bo.getY();
	            		while(b.isGrass(y-1,x) && !bombe.is_bomb_already_exists(x,y-1) && !other.colision_player(other, x, y-1)){
	            			y--;
	            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
	            			anim.add_liste(tir);
	            			anim.display_effects(b);
	            		}
	            		bo.setX(x);
	            		bo.setY(y);
	            		
	            	}
	          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q press�e
		        	  
		            	Bomb bo = bombe.find_Bomb((int) this.getX()-1, (int) this.getY());
		            	if(bo.getX() > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY(), (int) this.getX()-1, "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass( y,x-1) && !bombe.is_bomb_already_exists(x-1,y) && !other.colision_player(other, x-1, y)){
		            			x--;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}

	          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D press�e
		        	
		            	Bomb bo = bombe.find_Bomb((int) this.getX()+1, (int) this.getY());
		            	if(bo.getX()+1 > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY(), (int) this.getX()+1, "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass(y,x+1) && !bombe.is_bomb_already_exists(x+1,y) && !other.colision_player(other, x+1, y)){
		            			x++;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}

	         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z press�e
		   
		            	Bomb bo = bombe.find_Bomb((int) this.getX(), (int) this.getY()+1);
		            	if(bo.getX() > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY()+1, (int) this.getX(), "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass(y+1,x) && !bombe.is_bomb_already_exists( x, y+1) && !other.colision_player(other, x, y+1)){
		            			y++;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}
	         	}
			
		}else if(StdDraw.isKeyPressed(KeyEvent.VK_M) && this.getId() == 2){// touche 3 enfonc�e
			
	           if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
	        	   
	            	Bomb bo = bombe.find_Bomb((int) this.getX(), (int) this.getY()-1);
	            	if(bo.getX() > 0 && bo.getY() > 0 ){
	            		b.setArea((int) this.getY()-1, (int) this.getX(), "green");
	            		int x = bo.getX();
	            		int y = bo.getY();
	            		while(b.isGrass(y-1,x) && !bombe.is_bomb_already_exists(x,y-1) && !other.colision_player(other, x, y-1)){
	            			y--;
	            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
	            			anim.add_liste(tir);
	            			anim.display_effects(b);
	            			
	            		}
	            		bo.setX(x);
	            		bo.setY(y);
	            	}
	          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
	          		
		            	Bomb bo = bombe.find_Bomb((int) this.getX()-1, (int) this.getY());
		            	if(bo.getX() > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY(), (int) this.getX()-1, "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass(y,x-1) && !bombe.is_bomb_already_exists(x-1,y) && !other.colision_player(other, x-1, y)){
		            			x--;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}

	          	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
		        	
		            	Bomb bo = bombe.find_Bomb((int) this.getX()+1, (int) this.getY());
		            	if(bo.getX()+1 > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY(), (int) this.getX()+1, "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass(y,x+1) && !bombe.is_bomb_already_exists(x+1,y) && !other.colision_player(other, x+1, y)){
		            			x++;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}

	         	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
		   
		            	Bomb bo = bombe.find_Bomb((int) this.getX(), (int) this.getY()+1);
		            	if(bo.getX() > 0 && bo.getY() > 0 ){
		            		b.setArea((int) this.getY()+1, (int) this.getX(), "green");
		            		int x = bo.getX();
		            		int y = bo.getY();
		            		while(b.isGrass(y+1,x) && !bombe.is_bomb_already_exists(x,y+1) && !other.colision_player(other, x, y+1)){
		            			y++;
		            			Animation tir = new Animation("football" , x+0.5f , y+0.5f , 500);
		            			anim.add_liste(tir);
		            			anim.display_effects(b);
		            		}
		            		bo.setX(x);
		            		bo.setY(y);
		            	}
	         	}
		}
	}


}
