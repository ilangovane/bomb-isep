import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

class IA extends Player {
String cap ;
	IA(int id) {
		super(id);
		String cap = "D";
		// TODO Auto-generated constructor stub
	}
	
	public void move(Board b,Bomb bo){
	if(cap == "D"){
	 	if(	this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() - 1) || b.isGrass((int)this.getY()-1 , (int)this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()-1)){

    		b.repaint((int) this.getY(), (int) this.getX());
    		this.setY(this.getY()- dY);
    		
    	
    	}
	}else if(cap == "L"){
		if(this.go_through_destructible_wall( b , (int) this.getX()-1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX() -1) && !bo.is_bomb_already_exists( (int)this.getX()-1, (int)this.getY())){
		      
     		b.repaint((int) this.getY(), (int) this.getX());
    		this.setX(this.getX()-dX);	
    		
    	}
	}else if(cap == "R"){
		if(this.go_through_destructible_wall( b , (int) this.getX()+1, (int) this.getY()) || b.isGrass((int) this.getY(), (int) this.getX()+1) && !bo.is_bomb_already_exists( (int)this.getX()+1, (int)this.getY())){
     		
 			b.repaint((int) this.getY() , (int) this.getX());
    		this.setX(this.getX()+dX);
    		
    	}
	}else if(cap == "U"){
     	if(this.go_through_destructible_wall( b , (int) this.getX(), (int) this.getY() + 1) || b.isGrass((int) this.getY()+1, (int) this.getX()) && !bo.is_bomb_already_exists( (int)this.getX(), (int)this.getY()+1)){
     		
     		b.repaint((int) this.getY(), (int) this.getX());
    		this.setY(this.getY()+dY);
    		
    	}
	}
          
  
  		 
         	
  	
  		
     		
 	

 	}
	}


