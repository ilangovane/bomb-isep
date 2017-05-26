
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.princeton.cs.introcs.StdDraw;

public class Main {


	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	    
		Menu menu = new Menu();
		Player j1 = new Player(1);
		int i=0;
		//audio
	    Audio musique = new Audio("src/bomberman_sound.wav");
	    musique.start();
		while(menu.getChoixMenu() != "exit"){
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
	       StdDraw.show(30); 
	       musique.repeat();
		}
		   System.exit(0);		// On ferme la fenetre 
	       musique.stop();
	}
	

		
}
