import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.princeton.cs.introcs.StdDraw;

public class Instruction {

	private String ChoixMenu;
	
	//Fonction permettant de definir une police externe
	public Font bombermanFont() throws FileNotFoundException, FontFormatException, IOException{ 
		Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Bomberman/src/bm.ttf"));
		font = font.deriveFont(20F);
		return font;
	}
	
	// Fonction permettant d'afficher la banniere pour les deux personnages
	public void information(Menu menu) throws FileNotFoundException, FontFormatException, IOException{
		int nbligne = 30;						//nombre de ligne
		int nbcolonne = 30;						//nombre de colonne
		float centerL = (float)(nbligne/2);		// permet de definir le centre de la fenetre
		float centerC = (float)(nbcolonne/2);	// permet de definir le centre de la fenetre
		float largeurRect = 2.75f;
		float hauteurRect = 0.5f;
		
		StdDraw.clear(); // On clear la fenetre
		StdDraw.setFont(bombermanFont());	// On defini la police de l'ecriture
		StdDraw.picture(15, 8, "instructions.gif",30,18);	// On affiche le fond de la page d'instructions
    	StdDraw.picture(15.75, 8.5, "/bomberman_picture/instructions.png",19,14);	// On affiche par dessus le gif toutes les instructions grace a une image
    	displayRect2(centerC, centerL, largeurRect, hauteurRect,"Retour au menu");	// On met en place un "bouton" grace a un rectangle pour revenir au menu
    	// Si l'utilisateur clique sur le triangle qui est pour nous un bouton on retourne au menu
    	if (StdDraw.mouseX() >= centerC-largeurRect && StdDraw.mouseX() <= centerC+largeurRect && StdDraw.mouseY() >= centerL - hauteurRect && StdDraw.mouseY() <= centerL + hauteurRect){
			// Petit effet quand on place la souris sur le bouton 
			displayEffect2(centerC, centerL, largeurRect, hauteurRect,"menu");		//  Dessine un rectangle avec un effet
			if(StdDraw.mousePressed()){ // Si la souris est presse on retourne au menu principal
			menu.setChoixMenu("home");			
			}
		}
	}	
	
	// Fonction permettant de creer un rectangle
	public void displayRect2(float colonne, float ligne, float largeur,float hauteur, String contenu) throws FileNotFoundException, FontFormatException, IOException{
		StdDraw.setPenColor(StdDraw.BLACK);								//  On defini la couleur pour l'ecriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		StdDraw.setFont(bombermanFont());								//  On defini la police de l'ecriture
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);					//  On defini la couleur pour l'ecriture
		StdDraw.text(colonne, ligne, contenu);							//  On place le texte souhaite dans le rectangle
	}
	
	// Fonction permettant de creer un rectangle avec un effet lorsque la souris sera sur le rectangle
	public void displayEffect2(float colonne, float ligne, float largeur,float hauteur, String contenu) throws FileNotFoundException, FontFormatException, IOException{
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);						//  On defini la couleur pour l'ecriture
		StdDraw.filledRectangle(colonne, ligne, largeur, hauteur);		//  Dessine un rectangle
		StdDraw.setFont(bombermanFont());								//  On defini la police de l'ecriture
		StdDraw.setPenColor(StdDraw.RED);								//  On defini la couleur pour l'ecriture
		StdDraw.text(colonne, ligne, contenu);							//  On place le texte souhaite dans le rectangle
	}
	
}
