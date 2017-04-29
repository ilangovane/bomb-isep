
import edu.princeton.cs.introcs.StdDraw;

public class Main {

	public static void main(String[] args) {

		/*taille de la fenêtre 1050 x 850 
		 * Je n'arrive pas à ajuster la longueur et largeur pour que les dessins soit des carrées (=>rectangles) et cercles (ellipses)
		 * Pour moi une case doit faire 50 px => 50 x nb de ligne (17) = 850 et 50 x nb de colonne (21) = 1050
		 * */
		StdDraw.setCanvasSize(1050,850);
		
		/*
		 * Modifier les echelles X et Y pour avoir un système de coordonnées (X,Y)
		 *  Coordonnées (0,0) coin en bas à gauche et (17,21) coin en haut à droite
		 *  */
        StdDraw.setXscale(0 , 17);
        StdDraw.setYscale(0 , 21);
        
        //Dessiner le plateau et les joueurs
        beginGame();
		
		
		
		
	}
	
	public static void beginGame(){
		
		boolean[][] wall_unbreakable = new boolean[17][21];
		wall_unbreakable = init_wall_unbreakable();	
		boolean[][] grass = new boolean[17][21];
		grass = init_grass();
		boolean[][] wall = new boolean[17][21];
		wall = init_wall(wall_unbreakable, grass);	
		//génerer le plateau case par case
		for(int line = 0 ; line < 17 ; line++ ){
			for(int column = 0 ; column <21 ; column ++ ){

		        if(wall_unbreakable[line][column]){ // cas d'un mur incassable 
					StdDraw.setPenColor(StdDraw.DARK_GRAY);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : dark_gray	" + "x:" + line +  " y:" + column);
		        }else if(wall[line][column]){// cas d'un mur cassable
		        	StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : orange	" + "x:" + line +  " y:" + column);
		        }else if(grass[line][column]){ // cas de l'herbe
		        	StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(line + 0.5 , column + 0.5 , 0.5 );
					System.out.println("color : green	" + "x:" + line +  " y:" + column);
		        }

			}

		    
		}
		
		//placer les joueurs
		setPlayers();
		//StdDraw.show();
		
		
		
		
	}
	
	
	//Initialisation du jeu avec les murs incassables (en GRIS)
	public static boolean[][] init_wall_unbreakable(){
		
		boolean[][] wall = new boolean[17][21];
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				wall[line][column] = false;
			}

		}
		
		
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				if(column == 0 || column == 20 || line == 0 || line == 16){
					wall[line][column] = true;
				}
				
				if(column % 2 == 0 && line % 2 == 0){
					wall[line][column] = true;
				}

			}

		}
		return wall;
		
		
	}
	
	// initialisation des murs cassables (en ORANGE)
	public static boolean[][] init_wall(boolean[][] wall_unbreakable, boolean[][] wall_destroyed){
		boolean[][] wall = new boolean[17][21];
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				wall[line][column] = false;
			}

		}
		
		for(int line = 0 ; line <17 ; line ++){
			for(int column = 0 ; column < 21 ; column++){
				if(!wall_unbreakable[line][column] && !wall_destroyed[line][column]){
					wall[line][column] = true;
				}
			}

		}
		
		return wall;
	
	}

	//initialisation des zones où le joueur peut se déplacer => HERBE en VERT
	public static boolean[][] init_grass(){
		boolean[][] grass = new boolean[17][21];
		
		//player 1 
		grass[1][1] = true;
		grass[1][2] = true;
		grass[1][3] = true;
		grass[2][1] = true;
		grass[2][3] = true;
		grass[3][2] = true;
		grass[3][1] = true;

		//player 2 
		grass[15][19] = true;
		grass[15][18] = true;
		grass[15][17] = true;
		grass[14][19] = true;
		grass[14][17] = true;
		grass[13][18] = true;
		grass[13][19] = true;
		return grass;
	
	}
	
	//placer les joeurs au début du jeu pendant l'initialisation
	public static void setPlayers(){
		
		//Joueur 1 en bleu
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(15 + 0.5 , 19 + 0.5 , 0.3 );
		
		//Joueur 2 en rouge 
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(1 + 0.5 , 1 + 0.5 , 0.3 );
	}
}
