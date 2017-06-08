
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
	public int[][] matrice = new int[3][2]; // Contient tous le gifs
	Menu menu = new Menu();
	private int id;

	public Avatar(){
			
		for(int i = 0; i<3; i++){
			for(int j = 0; j<2; j++){
					matrice[i][j] = 0;
			}
		}
		
	}
	public Font bombermanFont() throws FileNotFoundException, FontFormatException, IOException{
		Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Bomberman/src/bm.ttf"));
		font = font.deriveFont(20F);
		return font;
	}
	
	public boolean find(int element , int[][] array){
		boolean find = false;
		for(int i =0 ;  i < array.length ; i++ ){
			for(int j=0 ; j< array[0].length ; j++){
				if(array[i][j] == element){
					return true;
				}
			
			}
		}
		return find;
	}
	public void choix() throws FileNotFoundException, FontFormatException, IOException{
		StdDraw.setCanvasSize(20*30,20*30);
		StdDraw.setYscale(0,3);
		StdDraw.setXscale(0,3);	
		boolean end = false;
		StdDraw.setFont(bombermanFont());
		//this.print();

		
		//StdDraw.show(30);
			while(!end){
				this.print(1);

				if(!StdDraw.mousePressed()){
					//c'est le tour du joueur 1	
					
					while( !this.find(1, matrice)){
						this.choose(1);
						this.print(1);	
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					while(!this.find(2, matrice)){
						this.choose(2);
						this.print(2);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//�tat du plateau 
					//this.print();
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
			//matrice[0][0] = 2; 
			if(matrice[x1][y1] == 0){// le joueur a choisi une case vierge
				matrice[x1][y1] = id;
			
				displayCarre(id,x1+0.5,y1+0.5,0.5,0.5);
				StdDraw.show(30);
			

				return true;
			}
		}
		return false;
	}
	
	public void displayCarre(int id , double colonne, double ligne, double largeur,double hauteur){
		StdDraw.setPenRadius(0.03);
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

	public void print(int id){
		int index = 1;
		StdDraw.picture(1.5,1.5, "instructions.gif",3,3);
		if(id ==1){
			StdDraw.setPenColor(StdDraw.RED);					//  Couleur pour l'ecriture
			StdDraw.text(1, 2.5, "JOUEUR 1 : ");	
		}else{
			StdDraw.setPenColor(StdDraw.BLUE);					//  Couleur pour l'ecriture
			StdDraw.text(1, 2.5, "JOUEUR 2 : ");	
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<2; j++){
					StdDraw.picture(i+0.5, j+0.5, "p"+index+".gif",1,1);
					StdDraw.show(1);
					
					if(matrice[i][j]==1){
						
						displayCarre(1,i+0.5,j+0.5,0.5,0.5);
						this.setChoix_j1("p"+index+".gif");
					} 
					if(matrice[i][j]==2){
						//c'est le tour du joueur 2
						displayCarre(2,i+0.5,j+0.5,0.5,0.5);
						this.setChoix_j2("p"+index+".gif");
					}

					index++;

			}
		}

	}

}

