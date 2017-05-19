import java.awt.Font;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import edu.princeton.cs.introcs.StdDraw;

public class Board {
	/*La matrice contient des entiers : 
	 * 1 : mur incassable
	 * 2 : mur cassable
	 * 3 : case verte
	 * 
	 * 17 lignes et 21 colonnes
	 * */
	int[][] matrice = new int[17][21]; 


	
	/*Constructeur : initialise le plateau de jeu lors de l'instanciation d'objet */
	public Board(){
		/*taille de la fen�tre 1050 x 850 
		 * Une case doit faire 30 px => 30 x nb de ligne (17) et 30 x nb de colonne (21) 
		 * */
		StdDraw.setCanvasSize(40*30,21*30-1);
		/*
		 * Modifier les echelles X et Y pour avoir un syst�me de coordonn�es (X,Y)
		 *  Coordonn�es (0,0) coin en bas � gauche et (17,21) coin en haut � droite
		 *  */
        StdDraw.setXscale(0 , 30);
        StdDraw.setYscale(0 , 17);

		/*Murs incassables*/
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				if(column == 0 || column == 20 || line == 0 || line == 16){
					matrice[line][column] = 1;
				}
				
				if(column % 2 == 0 && line % 2 == 0){
					matrice[line][column] = 1;
				}

			}

		}
		
		/*Murs cassables*/
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				if(matrice[line][column] != 1 ){
					matrice[line][column] = 2;
					//matrice[line][column] = 3; 
				}
			}

		}
		
		/*Zone accessible*/
		//player 1 
		matrice[1][1] = 3;
		matrice[1][2] = 3;
		matrice[1][3] = 3;
		matrice[2][1] = 3;
		matrice[2][3] = 3;
		matrice[3][2] = 3;
		matrice[3][1] = 3;

		//player 2 
		matrice[15][19] = 3;
		matrice[15][18] = 3;
		matrice[15][17] = 3;
		matrice[14][19] = 3;
		matrice[14][17] = 3;
		matrice[13][18] = 3;
		matrice[13][19] = 3;
		
		
		
	}
	
	
	/*Getters et Setters*/
	public int[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
	public void setElementMatrice(int line , int column , int value){
		this.matrice[line][column] = value;
	}
	
	






//d�marrage du jeu en "convertissant" la matrice en plateau de jeu
public  void beginGame(){
	StdDraw.enableDoubleBuffering();		//	Suppression de l'affichage case par case
	StdDraw.clear(StdDraw.PRINCETON_ORANGE);
		//g�nerer le plateau case par case
		for(int line = 0 ; line < 17 ; line++ ){
			for(int column = 0 ; column <21 ; column ++ ){

		        if(matrice[line][column] == 1){ // cas d'un mur incassable 
					this.setArea(line , column , "grey");
		        }else if(matrice[line][column] == 2){// cas d'un mur cassable
		        	this.setArea(line , column , "orange");
		        }else if(matrice[line][column] == 3){ // cas de l'GREEN
		        	this.setArea(line , column , "green");
		        }

			}

		    
		}
	
		
		//placer les joueurs au d�but du jeu pendant l'initialisation
		//Joueur 1 en bleu

		this.setPlayer(1, 1, 1);
		
		//Joueur 2 en rouge 

		this.setPlayer(2, 15, 19);
		
	}

	/*Placer un joueur selon une position (X,Y) pour le joueur J1 (id = 1) , J2(id = 2) ou IA (id = 3 )*/
	public void setPlayer(int id,double X, double Y){
	
	if(id == 1){//Joueur 1 en rouge
		//StdDraw.setPenColor(StdDraw.RED);
		StdDraw.picture(X + 0.5 , Y + 0.5 , "/bomberman_picture/p1.png", 1, 1);	
		//StdDraw.save("p2.png");
	}else if(id==2){//Joueur 2 en bleu
		//StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.picture(X + 0.5 , Y + 0.5 , "/bomberman_picture/p2.png", 1, 1);	

	}else{
		//IA
	}

	
	

}

	/*La fonction permet de modifier la couleur d'une case en sp�cifiant la ligne, la colonne et la color "GREEN" , "grey" ou "orange"*/
	public void setArea(int line , int column , String color){
		switch(color){
		case "orange" : 
	    	//StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			//StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/destructibleWall.jpg",1,1);
			break;
		
		case "green":
        	//StdDraw.setPenColor(StdDraw.GREEN);
        	//StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/grass.jpg",1,1);
			
			break;
		case "grey":
			//StdDraw.setPenColor(StdDraw.DARK_GRAY);
			//StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/wall.jpg",1,1);
			
			break;
		default:
			
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
		}

	}
	
	public void repaint(int line , int column){
   		if(matrice[line][column] == 2){
   			this.setArea(line, column, "orange");
   		}else if(matrice[line][column] == 3){
   			this.setArea(line, column, "green");
   		}
}
	//dessine une bombe sur le plateau
	public void setBomb(int column , int line ){
	        StdDraw.picture(column + 0.5 , line + 0.5 , "bomb.gif", 1, 1);			
	}
	
	//Affiche toutes les bombes du plateau
	public void show_all_bombs(Set<Bomb> b){
		Iterator<Bomb> it = b.iterator();
		while (it.hasNext()){//parcours la liste de bombe
			Bomb bo = it.next();
			setBomb(bo.getX(), bo.getY());
		}
	}
	

	// si la case est verte la fonction renvoie true sinon false
	public boolean isGrass(int line , int column){
		/*La matrice contient des entiers : 
		 * 1 : mur incassable
		 * 2 : mur cassable
		 * 3 : case verte
		 * */
		if(line <0 || column<0 || column>20 || line>16){//hors indexe matrice
			
			return false;
			
		}
		if(matrice[line][column] == 0 || matrice[line][column] == 1 || matrice[line][column] == 2 ){
			return false;
		}
		return true;
	}
	
	//v�rifie si la case est celle d'un mur cassable ou GREEN
	public boolean isDestructible(int line, int column){
		/*La matrice contient des entiers : 
		 * 1 : mur incassable
		 * 2 : mur cassable
		 * 3 : case verte
		 * */
		if(line <0 || column<0 || column>20 || line>16){//hors indexe matrice
			
			return false;
			
		}
		if(matrice[line][column] == 1  ){
			return false;
		}
		return true;
	}
	
	//v�rifie si la case est celle d'un mur cassable seulement
	public boolean isWallDestructible(int line, int column){
		/*La matrice contient des entiers : 
		 * 1 : mur incassable
		 * 2 : mur cassable
		 * 3 : case verte 
		 * */
		if(line <0 || column<0 || column>20 || line>16){//hors indexe matrice
			
			return false;
			
		}
		if(matrice[line][column] == 1  || matrice[line][column] == 3){
			return false;
		}
		return true;
	}
	
	
	

	/*
	 * BONUSSSSSSSSSS
	 * 
	 * */
	
	public void show_bonus(Set<Bonus> b){
		Iterator<Bonus> it = b.iterator(); // on parcours l'element du premier jusqu'au dernier grace au curseur iterator
		while (it.hasNext()){//parcours la liste de bonus
			Bonus bo = it.next();
			setBonus(bo.getX(), bo.getY(), bo.getType_bonus());
		}
	}
	
		public void setBonus(int column , int line , String type ){

		switch (type){
		case "flamme_bleu":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/bluefire.png", 1,  1);
			break;
		case "flamme_jaune":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/yellowfire.png", 1,  1);
			break;
		case "flamme_rouge":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/redfire.png", 1,  1);
			break;
		case "bombe_rouge":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/redbomb.png", 1,  1);
			break;
		case "vie":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/heart.png", 1,  1);
			break;
		case "speed_up":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/speedup.png", 1,  1);
			break;
		case "speed_down":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/speeddown.png", 1,  1);
			break;
		case "bombe_plus":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/morebomb.png", 1,  1);
			break ;
		case "bombe_moins": 
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/lessbomb.png", 1,  1);
			break ;	
		case "shield":
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledCircle(column + 0.5 , line + 0.5 , 0.3);
			break;
		case "passe_muraille":
			StdDraw.setPenColor(StdDraw.PINK);
			StdDraw.filledCircle(column + 0.5 , line + 0.5 , 0.3);
			break;
		}
		
	}
	
	public void draw_life(Player J){
		Font font = new Font("Arial" , Font.BOLD , 30);
		StdDraw.setFont(font);
		if(J.getId() == 1){
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(24, 16, "Joueur 1");
			 	StdDraw.setPenColor(StdDraw.RED);
		        // draw diamond
		        double[] xs = { 23,  24, 25, 24 };
		        double[] ys = { 14, 13, 14, 15 };
		        StdDraw.filledPolygon(xs, ys);

		        // circles
		        StdDraw.filledCircle(24+0.5, 14+0.5, 1 / Math.sqrt(2));
		        StdDraw.filledCircle(24-0.5, 14+0.5, 1 / Math.sqrt(2));
		        StdDraw.setPenColor(StdDraw.WHITE);
		        StdDraw.text(24, 14, String.valueOf(J.getLife()));
		}else if(J.getId() == 2){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(24, 8, "Joueur 2");
		 	StdDraw.setPenColor(StdDraw.BLUE);
	        // draw diamond
	        double[] xs = { 23,  24, 25, 24 };
	        double[] ys = { 6, 5, 6, 7 };
	        StdDraw.filledPolygon(xs, ys);

	        // circles
	        StdDraw.filledCircle(24.5, 6.5, 1 / Math.sqrt(2));
	        StdDraw.filledCircle(23.5, 6.5, 1 / Math.sqrt(2));
	        StdDraw.setPenColor(StdDraw.WHITE);
	        StdDraw.text(24, 14-8, String.valueOf(J.getLife()));
		}

	}
	
	public void finalize(){
		
	}
	
}
