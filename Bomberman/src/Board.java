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
		/*taille de la fenêtre 1050 x 850 
		 * Une case doit faire 30 px => 30 x nb de ligne (17) et 30 x nb de colonne (21) 
		 * */
		StdDraw.setCanvasSize(21*30,17*30);

	
		/*
		 * Modifier les echelles X et Y pour avoir un système de coordonnées (X,Y)
		 *  Coordonnées (0,0) coin en bas à gauche et (17,21) coin en haut à droite
		 *  */
        StdDraw.setXscale(0 , 21);
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

	
	//démarrage du jeu en "convertissant" la matrice en plateau de jeu
public  void beginGame(){

		//génerer le plateau case par case
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
	
		
		//placer les juoeurs au début du jeu pendant l'initialisation
		//Joueur 1 en bleu

		this.setPlayer(1, 1, 1);
		
		//Joueur 2 en rouge 

		this.setPlayer(2, 15, 19);
		
	}

	/*Placer un joueur selon une position (X,Y) pour le joueur J1 (id = 1) , J2(id = 2) ou IA (id = 3 )*/
	public void setPlayer(int id,double X, double Y){
	
	if(id == 1){//Joueur 1 en rouge
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(X + 0.5 , Y + 0.5 , 0.3 );	
	}else if(id==2){//Joueur 2 en bleu
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(X + 0.5 , Y + 0.5 , 0.3 );

	}else{
		//IA
	}

	
	

}

	/*La fonction permet de modifier la couleur d'une case en spécifiant la ligne, la colonne et la color "GREEN" , "grey" ou "orange"*/
	public void setArea(int line , int column , String color){
		switch(color){
		case "orange" : 
	    	StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			System.out.println("color : orange	|" + "line : " + line +  "	| column : " + column);
			break;
		
		case "green":
        	StdDraw.setPenColor(StdDraw.GREEN);
        	StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			System.out.println("color : green	|" + "line : " + line +  "	| column : " + column);
			break;
		case "grey":
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
			System.out.println("color : dark_grey	|" + "line : " + line +  "	| column : " + column);
			break;
		default:
			System.out.println("UNKNOWN COLOR");
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(column + 0.5 , line + 0.5 , 0.5 );
		}

	}
	
	//dessine une bombe sur le plateau
	public void setBomb(int column , int line ){
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.filledCircle(column + 0.5 , line + 0.5 , 0.3 );
			System.out.println("color : orange	|" + "line : " + line +  "	| column : " + column);
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
			System.out.println("Out of index matrice[ligne][colonne]");
			return false;
			
		}
		if(matrice[line][column] == 0 || matrice[line][column] == 1 || matrice[line][column] == 2 ){
			return false;
		}
		return true;
	}
	
	//vérifie si la case est celle d'un mur cassable ou GREEN
	public boolean isDestructible(int line, int column){
		/*La matrice contient des entiers : 
		 * 1 : mur incassable
		 * 2 : mur cassable
		 * 3 : case verte
		 * */
		if(line <0 || column<0 || column>20 || line>16){//hors indexe matrice
			System.out.println("Out of index matrice[ligne][colonne]");
			return false;
			
		}
		if(matrice[line][column] == 1  ){
			return false;
		}
		return true;
	}
	
	//vérifie si la case est celle d'un mur cassable seulement
	public boolean isWallDestructible(int line, int column){
		/*La matrice contient des entiers : 
		 * 1 : mur incassable
		 * 2 : mur cassable
		 * 3 : case verte 
		 * */
		if(line <0 || column<0 || column>20 || line>16){//hors indexe matrice
			System.out.println("Out of index matrice[ligne][colonne]");
			return false;
			
		}
		if(matrice[line][column] == 1  || matrice[line][column] == 3){
			return false;
		}
		return true;
	}
	
	public void game_over(int id){
		
		Font font = new Font("Arial" , Font.BOLD , 30);
		StdDraw.setFont(font);
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(7, 10, "Le joueur " + id + " a gagné !");
	}
	

	
	
}
