
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.princeton.cs.introcs.StdDraw;

public class Main {


	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, FontFormatException, InterruptedException {
	    
		Menu menu = new Menu();
		
		int i=0;
		//audio
	    
		Instruction instruction = new Instruction();
		Audio musique = new Audio("Bomberman/src/bomberman_backsound.wav");
	    Audio click = new Audio("Bomberman/src/start.wav");
		musique.start();

		
		while(menu.getChoixMenu() != "exit"){
		   musique.repeat();
	       switch(menu.getChoixMenu()){
	       case "home":
	    	   	menu.menu();
	    	   	break;
	       case "gameover":
	    	   	menu.game_over(i);//attention au cas ou il y a match null 
	    	   	break;
	       case "multiplayers":
	   	    	click.start();
	   	    	i = menu.start_game_multi();//pas de IA
	   	    	click.stop();
	        	break;
	       case "IA":
	        	i = menu.start_game_single();//IA presente
	        	break;
	       case "instructions":
	        	//System.out.println("INSTRUCTIONS");
	    	    instruction.information();
	        	break;
	       case "exit":
	        	break;
	       case "avatar": 
	    	   	Avatar avatar = new Avatar();
	    	    avatar.choix();
	    	    menu.setChoixMenu("multiplayers");
	    	    break;
	       default:
	    	   System.out.print("RIEN");			
	       }	   
	       StdDraw.show(30);
		}
		   System.exit(0);		// On ferme la fenetre 
	       musique.stop();
	       
	}
	

		
}
