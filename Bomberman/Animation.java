import java.awt.Font;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;

public class Animation {
	/*
	 * Une animation a un type, une coordonnee (X,Y) , une duree d'expiration
	 * */
	private String type;
	private float x;
	private float y;
	private long end;
	
	/*Comme la liste de bombe et de bonus => une liste d�di�e aux Animation*/
	private Set<Animation> liste = new HashSet<Animation>();
	
	public Animation(String type , float x , float y , int d){
		this.type = type;
		this.x = x;
		this.y = y;
		this.end = System.currentTimeMillis() + d;

	}
	public Animation(){
		
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public Set<Animation> getListe() {
		return liste;
	}
	public void setListe(Set<Animation> liste) {
		this.liste = liste;
	}
	public void add_liste(Animation a){
		this.liste.add(a);
	}
	public void display_effects(Board b){
		Iterator<Animation> it = this.getListe().iterator();
		while (it.hasNext()){//parcours la liste de bombe
			Animation anim = it.next();
			if(anim.getType() == "explosion"){
				
				StdDraw.picture(anim.getX(), anim.getY(), "/bomberman_picture/explosion2.png",1,1);
			}else if(anim.getType() == "mine_under_ground"){
				//StdDraw.setPenColor(StdDraw.GREEN);
				//StdDraw.filledCircle(anim.getX(), anim.getY(), 0.3);
				StdDraw.picture(anim.getX(), anim.getY(), "/bomberman_picture/antenna.png",1,1);
				
			}else if(anim.getType() == "football" ){

				StdDraw.picture(anim.getX(), anim.getY(), "/bomberman_picture/ball.png",1,1);
				
			}else if(anim.getType() == "dead"){
				
				StdDraw.picture(anim.getX(), anim.getY(), "/bomberman_picture/death.png",1,1);
			}
			
			if(anim.getEnd()<System.currentTimeMillis()){
				
				anim.hide_effect(b , anim);
				it.remove();
			
				
			}
		}
		
		
	}
	
	public void hide_effect(Board b,Animation anim){
		b.setArea((int)anim.getY(), (int) anim.getX(), "green");
	}
	
	public void bomb_timer(Bomb list){
		Iterator<Bomb> it = list.getBombs().iterator();
		
		
		
		

		Font font = new Font("Bomberman" , Font.BOLD , 20);
		StdDraw.setFont(font);
		while (it.hasNext()){//parcours la liste de bombes
			Bomb bo = it.next();
			double timer = bo.getT_explosion() - System.currentTimeMillis();
			if(timer <= 4000 &&  timer >3000){
				StdDraw.setPenColor(StdDraw.WHITE);
				//StdDraw.arc(bo.getX() +0.5, bo.getY()+0.5, 0.5, 0, 360*timer/4000) ;
				StdDraw.text(bo.getX() +0.4, bo.getY()+0.3, "4");
			}else if(timer <= 3000 &&  timer >2000){
				StdDraw.setPenColor(StdDraw.YELLOW);
				//StdDraw.arc(bo.getX() +0.5, bo.getY()+0.5, 0.5, 0,360*timer/4000) ;
				StdDraw.text(bo.getX()+0.4, bo.getY()+0.3, "3");
			
			}else if(timer <= 2000 &&  timer >1000){
				StdDraw.setPenColor(StdDraw.ORANGE);
				//StdDraw.arc(bo.getX() +0.5, bo.getY()+0.5, 0.5, 0,360*timer/4000 ) ;
				StdDraw.text(bo.getX()+0.4, bo.getY()+0.3, "2");
			}else if(timer <= 1000 &&  timer >0){
				StdDraw.setPenColor(StdDraw.RED);
				//StdDraw.arc(bo.getX() +0.5, bo.getY()+0.5, 0.5, 0,360*timer/4000) ;
				StdDraw.text(bo.getX()+0.4, bo.getY()+0.3, "1");
			}
				
			
		
		}
	}
}
