
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Main {


	public static void main(String[] args) {
	    
		Menu menu = new Menu();
		Player j1 = new Player(1);
		int i=0;
		while(menu.getChoixMenu() != "exit"){
			
			//audio
			//String audioFilePath = "/Users/apple/Downloads/sound.wav";
		    //Audio musique = new Audio();
		    //musique.play(audioFilePath);
		    
	       switch(menu.getChoixMenu()){
	       case "home":
	    	   	menu.menu();
	    	   	break;
	       case "gameover":
	    	   	menu.game_over(i);//attention au cas ou il y a match null 
	    	   	break;
	       case "multiplayers":
	        	i = menu.start_game(false);//pas de IA
	        	break;
	       case "IA":
	        	i = menu.start_game(true);//IA presente
	        	break;
	       case "instructions":
	        	System.out.println("INSTRUCTIONS");
	        	break;
	       case "exit":
	    	   //System.out.println("QUITTER");
	        	break;
	       default:
	    	   System.out.print("RIEN");			
	       }
	       //System.out.println(menu.getChoixMenu());
	       
	       StdDraw.show(30);  
		}
		   System.exit(0);		// On ferme la fenetre 
	}
	

		
}
