import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;

public class Animation {
	/*
	 * Une animation a un type, une coordonnee (X,Y) , une duree d'expiration
	 * */
	String type;
	float x;
	float y;
	long end;
	
	/*Comme la liste de bombe et de bonus => une liste dédiée aux Animation*/
	Set<Animation> liste = new HashSet<Animation>();
	
	public Animation(String type , float x , float y , int d){
		this.type = type;
		this.x = x;
		this.y = y;
		this.end = System.currentTimeMillis() + d;
		System.out.println("X : " + x + " Y : " + y + " D " + d + " type : " + type);
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
				
				StdDraw.picture(anim.getX(), anim.getY(), "/bomberman_picture/explosion.png",1,1);
			}else if(anim.getType() == "mine_under_ground"){
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(anim.getX(), anim.getY(), 0.3);
				
			}else if(anim.getType() == "football" ){
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledCircle(anim.getX(), anim.getY(), 0.3);
				
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
	
}
