
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.princeton.cs.introcs.StdDraw;
//import java.awt.Font;

public class Avatar {

	private String choix_j1 = "";
	private String choix_j2 = "";
	public int[][] matrice = new int[3][3]; // Contient tous le gifs
	Menu menu = new Menu();
	int id=1;
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
	
	public void choix(){
		boolean end = false;
			while(!end){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				end = this.getChoix_j1()=="" || this.getChoix_j2()=="";	
				//c'est le tour du joueur 1
				this.print();
				while(!choose(1) && !end){
					//System.out.println("1");
					
				}
				//�tat du plateau
				this.print();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				end = this.getChoix_j1()=="" || this.getChoix_j2()=="";	
				//c'est le tour du joueur 2
				while(!choose(2) && !end){
					//System.out.println("2");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("choix2");
				//�tat du plateau 
				this.print();

			}
	}
	
	public boolean choose(int id) {
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		//System.out.println("chosse");
		if(StdDraw.mousePressed() && x>=0 && x<3 && y>=0 && y<3){

			int x1 = (int) Math.floor(x);
			int y1 = (int) Math.floor(y);
			System.out.println(x1 +" x");
			System.out.println(y1 +" y");
			int test = matrice[x1][y1];
			//System.out.println("id " + test);
			System.out.println(id);
			if(matrice[x1][y1] ==0){// le joueur a choisi une case vierge
				matrice[x1][y1] = id;
				test = matrice[x1][y1];
				System.out.println("id2 " + test);
				return true;
			}
		}
		return false;
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
		int index = 0;
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
					StdDraw.picture(i+0.5, j+0.5, "p1.gif",1,1);/*"+index+"*/
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

