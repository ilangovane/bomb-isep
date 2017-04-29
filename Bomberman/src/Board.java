import edu.princeton.cs.introcs.StdDraw;

public class Board {
	/*La matrice contient des entiers : 
	 * 1 : mur incassable
	 * 2 : mur cassable
	 * 3 : herbe
	 * */
	int[][] matrice = new int[17][21]; 
	
	/*Constructeur : initialise le plateau de jeu lors de l'instanciation d'objet */
	public Board(){
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				matrice[line][column] = 0;
			}

		}
		
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
				}
			}

		}
		
		/*Zone accessible*/
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
	
public  void beginGame(){

		//génerer le plateau case par case
		for(int line = 0 ; line < 17 ; line++ ){
			for(int column = 0 ; column <21 ; column ++ ){

		        if(matrice[line][column] == 1){ // cas d'un mur incassable 
					StdDraw.setPenColor(StdDraw.DARK_GRAY);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : dark_gray	" + "x:" + line +  " y:" + column);
		        }else if(matrice[line][column] == 2){// cas d'un mur cassable
		        	StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : orange	" + "x:" + line +  " y:" + column);
		        }else if(matrice[line][column] == 3){ // cas de l'herbe
		        	StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : green	" + "x:" + line +  " y:" + column);
		        }

			}

		    
		}
	
		
		//placer les joeurs au début du jeu pendant l'initialisation
		//Joueur 1 en bleu
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(15 + 0.5 , 19 + 0.5 , 0.3 );
		
		//Joueur 2 en rouge 
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(1 + 0.5 , 1 + 0.5 , 0.3 );
		
		
	}
	
	
	
}
