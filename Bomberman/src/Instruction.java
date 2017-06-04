import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.princeton.cs.introcs.StdDraw;

public class Instruction {

	private String ChoixMenu;
	
	public Font bombermanFont() throws FileNotFoundException, FontFormatException, IOException{
		Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Bomberman/src/bm.ttf"));
		font = font.deriveFont(20F);
		return font;
	}
	public void information(Menu menu) throws FileNotFoundException, FontFormatException, IOException{
		int nbligne = 2;					//nombre de ligne
		int nbcolonne = 30;					//nombre de colonne
		float centerL = (float)(nbligne/2);
		float centerC = (float)(nbcolonne/2);
		float largeurRect = 2.75f;
		float hauteurRect = 0.5f;
		
		StdDraw.clear(); // On clear la fenetre
		StdDraw.setFont(bombermanFont());
		StdDraw.picture(15, 8, "instructions.gif",30,18);
    	StdDraw.picture(15.75, 8.5, "/bomberman_picture/instructions.png",19,14);
    	displayRect2(centerC, centerL, largeurRect, hauteurRect,"Retour au menu");	
    	if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect && StdDraw.mouseY() <= centerL + hauteurRect){
			// Petit effet quand on place la souris sur le bouton
			displayEffect2(centerC, centerL, largeurRect, hauteurRect,"menu");		//  Dessine un rectangle
			if(StdDraw.mousePressed()){
			menu.setChoixMenu("home");
			
			}
		}
	}
	
	
	
	public void displayRect2(float colonne, float ligne, float largeur,float hauteur, String contenu) throws FileNotFoundException, FontFormatException, IOException{
		StdDraw.setPenColor(StdDraw.BLACK);								//  Couleur noir pour l'ecriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		StdDraw.setFont(bombermanFont());
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);					//  Couleur pour l'ecriture
		StdDraw.text(colonne, ligne, contenu);
	}
	
	public void displayEffect2(float colonne, float ligne, float largeur,float hauteur, String contenu) throws FileNotFoundException, FontFormatException, IOException{
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);						//  Couleur noir pour l'ecriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		StdDraw.setFont(bombermanFont());
		StdDraw.setPenColor(StdDraw.RED);			//  Couleur pour l'ecriture
		StdDraw.text(colonne, ligne, contenu);
	}
	
}
