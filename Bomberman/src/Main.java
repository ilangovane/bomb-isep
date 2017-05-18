
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Main {


	public static void main(String[] args) {
		Menu menu = new Menu();
		Player j1 = new Player(1);
		
		while(menu.getChoixMenu() != "exit"){
	       switch(menu.getChoixMenu()){
	       case "home":
	    	   	menu.menu();
	    	   	break;
	       case "gameover":
	    	   	menu.game_over(j1);
	    	   	break;
	       case "multiplayers":
	        	menu.start_game(false);//pas de IA
	        	break;
	       case "IA":
	        	menu.start_game(true);//IA presente
	        	break;
	       case "instructions":
	        	System.out.println("INSTRUCTIONS");
	        	break;
	       case "exit":
	    	   System.out.println("QUITTER");
	    	   System.exit(0);		// On ferme la fenetre 
	        	break;
	       default:
	    	   System.out.print("RIEN");			
	       }
	       StdDraw.show(30);  
		}
	}
	
	public static void info(Player J1 , Player J2){
		System.out.println("Joueur 1  vie : " + J1.getLife() + " | X : "+ J1.getX() + " | Y : "+ J1.getY());
		System.out.println("Joueur 2  vie : " + J2.getLife() + " | X : "+ J2.getX() + " | Y : "+ J2.getY());
	}
		
}
