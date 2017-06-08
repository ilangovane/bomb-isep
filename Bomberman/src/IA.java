import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.princeton.cs.introcs.StdDraw;

class IA extends Player {
int x_dest ;
int y_dest ; 
int dest_length = 1;
int react = 1;//temps de reaction du perso lors du move : plus react est grand et plus il hesitera � se deplacer

	IA(int id) {
		super(id);
		if(id == 1){
			x_dest = 1;
			y_dest = 1;
		}
		// TODO Auto-generated constructor stub
	}
	
	IA(int id,String avatar) {
		super(id,avatar);
		if(id == 1){
			x_dest = 1;
			y_dest = 1;
		}
		// TODO Auto-generated constructor stub
	}
	
	public void move(Board b,Bomb bo,Player other) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.setRandomDest(b, bo);
		if(time_react() != 0){
			return ; //sinon le joueur bouge trop
		}

		float dX = this.getdX();
		float dY = this.getdY();
        if(y_dest < (int) this.getY()) {
        	if(this.colision_player(other, (int) this.getX(), (int) this.getY() -1 )){
        		return;
        	}
           	if(bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()-1)	|| this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){

        		b.repaint((int) this.getY(), (int) this.getX());
        		this.setY(this.getY()- dY);
        		
        	
        	}
  	}else if(x_dest < (int) this.getX()){
        	if(this.colision_player(other, (int) this.getX()-1, (int) this.getY() )){
        		return;
        	}
         	if(bo.is_there_mine_bomb((int) this.getX()-1,(int) this.getY())	|| this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
      
         		b.repaint((int) this.getY(), (int) this.getX());
        		this.setX(this.getX()-dX);	
        		
        	}
  	}else if(x_dest > (int) this.getX()){
        	if(this.colision_player(other, (int) this.getX()+1, (int) this.getY() )){
        		return;
        	}
     		if(bo.is_there_mine_bomb((int) this.getX()+1,(int) this.getY())	|| this.go_through_destructible_wall( b , (int) this.getX()+1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
     		
     			b.repaint((int) this.getY() , (int) this.getX());
        		this.setX(this.getX()+dX);
        		
        	}
 	}else if(y_dest > (int) this.getY()){
        	if(this.colision_player(other, (int) this.getX(), (int) this.getY() +1 )){
        		return;
        	}
         	if(bo.is_there_mine_bomb((int) this.getX(),(int) this.getY()+1)	|| this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
         		
         		b.repaint((int) this.getY(), (int) this.getX());
        		this.setY(this.getY()+dY);
        		
        	}
 	}

		b.setPlayer(this);

 	}
	
	
	/*Ficxer un objectif de destination au bot*/
	public void setRandomDest(Board b , Bomb bo){
		if((int) this.getX() == x_dest && (int) this.getY() == y_dest ){

			Random r = new Random();
			dest_length = 1;
			int direction = r.nextInt(4); //0 gauche , 1 droite , 2 haut , 3 bas
			if(direction==0 && b.isGrass((int)this.getY() , (int)this.getX()-1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY()) ){
				while(b.isGrass((int)this.getY() , (int)this.getX()-dest_length-1) && !bo.is_bomb_already_exists( (int)this.getX()-dest_length-1, (int)this.getY())){
					dest_length++;
				}
				x_dest = (int) this.getX()-dest_length;
				y_dest = (int) this.getY();
			}else if(direction == 1 && b.isGrass((int)this.getY() , (int)this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY()) ){
				while(b.isGrass((int)this.getY() , (int)this.getX()+dest_length+1) && !bo.is_bomb_already_exists( (int)this.getX()+dest_length+1, (int)this.getY())){
					dest_length++;
				}
				x_dest = (int) this.getX()+dest_length;
				y_dest = (int) this.getY();
			}else if(direction == 2 && b.isGrass((int)this.getY()+1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
				while(b.isGrass((int)this.getY()+dest_length+1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+dest_length+1)){
					dest_length++;
				}
				x_dest = (int) this.getX();
				y_dest = (int) this.getY()+dest_length;
			}else if(direction == 3 && b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){
				while(b.isGrass((int)this.getY()-dest_length-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-dest_length-1)){
					dest_length++;
				}
				x_dest = (int) this.getX();
				y_dest = (int) this.getY()-dest_length;
			}



	
	}


	}
	
	
	/*Le bot a un temps de r�action lors de ses d�placement, si time react vaut une certaine valeur alors le bot peut se deplacer , poser des bombes ....*/
	public int time_react(){
		Random r = new Random();
		return r.nextInt(react);

		
	}
	public void add_Bomb(Bomb bomb_liste){
		Random r = new Random();
		if( r.nextInt(react*100) == 0 ){
			bomb_liste.getBombs().add(new Bomb(1 , (int) this.getY() , (int) this.getX() , bomb_liste.getTimer_bomb_J1() , bomb_liste.getTimer_bomb_J2() ));
		}
	}
	
	
	
}

