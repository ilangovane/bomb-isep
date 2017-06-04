
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.princeton.cs.introcs.StdDraw;
//import java.awt.Font;

public class Avatar {

	private String choix_j1 = "";
	private String choix_j2 = "";
	public int[][] matrice = new int[3][3]; // Contient tous le gifs
	Menu menu = new Menu();
	private int id=1;
	public Avatar(){
		StdDraw.setCanvasSize(20*30,20*30);
		StdDraw.setYscale(0,3);
		StdDraw.setXscale(0,3);		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
					matrice[i][j] = 0;
			}
		}
		
	}
	public Font bombermanFont() throws FileNotFoundException, FontFormatException, IOException{
		Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Bomberman/src/bm.ttf"));
		font = font.deriveFont(20F);
		return font;
	}
	
	public void choix() throws FileNotFoundException, FontFormatException, IOException{
		boolean end = false;
		StdDraw.clear(); // On clear la fenetre
		StdDraw.setFont(bombermanFont());
		StdDraw.picture(15, 8, "instructions.gif",30,18);
		StdDraw.setPenColor(StdDraw.RED);					//  Couleur pour l'ecriture
		StdDraw.text(1, 2.5, "JOUEUR 1 : ");	
		this.print();
			while(!end){
				//StdDraw.picture(15, 8, "instructions.gif",30,18);				
				//this.print();
				if(!StdDraw.mousePressed()){
					//end = this.getChoix_j1()=="" || this.getChoix_j2()=="";	
					//c'est le tour du joueur 1				
					while(!choose(1)){					

					}
					//c'est le tour du joueur 2
					StdDraw.setPenColor(StdDraw.BLUE);					//  Couleur pour l'ecriture
					StdDraw.text(1, 2.5, "JOUEUR 2 : ");
					//�tat du plateau
					this.print();
					
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					//end = this.getChoix_j1()=="" || this.getChoix_j2()=="";	
					while(!choose(2)){

					}
					//�tat du plateau 
					this.print();
					end = true;
				}else StdDraw.show(30);
			}
	}
	
	public boolean choose(int id) {
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		this.id = id;
		if(StdDraw.mousePressed() && x>=0 && x<3 && y>=0 && y<2){
			int x1 = (int) Math.floor(x);
			int y1 = (int) Math.floor(y);
			if(matrice[x1][y1] == 0){// le joueur a choisi une case vierge
				matrice[x1][y1] = id;
				StdDraw.clear();
				displayCarre(x1+0.5,y1+0.5,0.3,0.35);
				return true;
			}
		}
		return false;
	}
	
	public void displayCarre(double colonne, double ligne, double largeur,double hauteur){
		if(id == 1){
		StdDraw.setPenColor(StdDraw.RED);								//  Couleur rouge pour le carre
		}else if(id == 2){
			StdDraw.setPenColor(StdDraw.BLUE);								//  Couleur bleu pour le carre			
		}
		StdDraw.rectangle(colonne, ligne, largeur, hauteur);		//  Dessine un carre	
	}
	
	public String getChoix_j1() {
		return choix_j1;
	}

	public void setChoix_j1(String choix_j1) {
		this.choix_j1 = choix_j1;
	}

	public String getChoix_j2() {
		return choix_j2;
	}

	public void setChoix_j2(String choix_j2) {
		this.choix_j2 = choix_j2;
	}

	public void print(){
		int index = 1;
		for(int i = 0; i<3; i++){
			for(int j = 0; j<2; j++){
					StdDraw.picture(i+0.5, j+0.5, "p"+index+".gif",1,1);
					if(matrice[i][j]==1){
						this.setChoix_j1("p1.gif");
					}else if(matrice[i][j]==2)
						this.setChoix_j2("p2.gif");
					index++;
			}
		}
		StdDraw.show(30);
	}

}

