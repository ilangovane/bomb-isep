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
	private int[][] matrice = new int[17][21]; 


	
	/*Constructeur : initialise le plateau de jeu lors de l'instanciation d'objet */
	public Board(){
		/*taille de la fenetre 1050 x 850 
		 * Une case doit faire 30 px => 30 x nb de ligne  et 30 x nb de colonne 
		 * */
		StdDraw.setCanvasSize(25*30,22*30);
		/*
		 * Modifier les echelles X et Y pour avoir un systeme de coordonnees (X,Y)
		 *  Coordonnees (0,-1) coin en bas � gauche et (21,18) coin en haut a droite
		 *  */
        StdDraw.setXscale(0 , 21);
        StdDraw.setYscale(-1 , 18);// de -2->0 et 17->19 infos des joueurs
        
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
	
	






//demarrage du jeu en "convertissant" la matrice en plateau de jeu
public  void beginGame(){
	StdDraw.enableDoubleBuffering();		//	Suppression de l'affichage case par case
	StdDraw.clear(StdDraw.LIGHT_GRAY);
		//generer le plateau case par case
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
	
		
		
	}

	/*Placer un joueur selon une position (X,Y) pour le joueur J1 (id = 1) , J2(id = 2) */
	public void setPlayer(Player P){
	int id = P.getId();
	int X = (int) P.getX();
	int Y = (int) P.getY();
	String avatar =  P.avatar;
		if(this.isGrass((int) Y, (int) X)){
			StdDraw.picture(X + 0.5 , Y + 0.5 , "/bomberman_picture/grass.jpg",1,1);
		}else{
			StdDraw.picture(X + 0.5 , Y + 0.5 , "/bomberman_picture/destructibleWall.png",1,1);
		}
		
	if(id == 1){//Joueur 1 en rouge
	
		StdDraw.picture(X + 0.5 , Y + 0.5 , avatar,1,1);
		if(P.isShield()){
			StdDraw.picture(X + 0.5 , Y + 0.5 , "aura.gif",1,1);
		}
		
	}else if(id==2){//Joueur 2 en bleu
		
		StdDraw.picture(X + 0.5 , Y + 0.5 , avatar,1,1);	
		if(P.isShield()){
			StdDraw.picture(X + 0.5 , Y + 0.5 , "aura2.gif",1,1);
		}
		
	}

	StdDraw.show(1);
	

}
	

	/*La fonction permet de modifier la couleur d'une case en specifiant la ligne, la colonne et la color "GREEN" , "grey" ou "orange"*/
	public void setArea(int line , int column , String color){

		switch(color){
		case "orange" : 
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/destructibleWall.png",1,1);
			break;
		case "green" : 
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/grass.jpg",1,1);
			break;
		case "grey":
			StdDraw.picture(column + 0.5,line + 0.5,"/bomberman_picture/wall.png",1,1);
			
			break;
		default:
			
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
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
			if(bo.getZ() == 0){
				setBomb(bo.getX(), bo.getY());	
			}else if(bo.getZ() == -1 && bo.getT_explosion() < System.currentTimeMillis()){
				this.setArea(bo.getY(), bo.getX(), "green");
			}
			
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
	
	//verifie si la case est celle d'un mur cassable ou GREEN
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
	
	//verifie si la case est celle d'un mur cassable seulement
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
	 * BONUS
	 * 
	 * */
	
	//Afficher tous les bonus sur le terrain
	public void show_bonus(Set<Bonus> b){
		Iterator<Bonus> it = b.iterator(); // on parcours l'element du premier jusqu'au dernier grace au curseur iterator
		while (it.hasNext()){//parcours la liste de bonus
			Bonus bo = it.next();
			setBonus(bo.getX(), bo.getY(), bo.getType_bonus());
		}
	}
	
	//Afficher un bonus sur le terrain
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
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/shield.png", 1,  1);
			break;
		case "passe_muraille":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/ghost.png", 1,  1);
			break;
		case "mine":
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/mine.png", 1,  1);
			break;
		case "kick" :
			StdDraw.picture(column + 0.5 , line + 0.5 , "/bomberman_picture/kick.png", 1,  1);
			break;
		case "bomb_line":
			StdDraw.picture(column + 0.5, line + 0.5, "/bomberman_picture/linebomb.jpeg",1,1);
			break;
		case "flamme_verte":
			StdDraw.picture(column + 0.5, line + 0.5, "/bomberman_picture/greenfire.png",0.8,1);
			break;
		}
		
	}
		
		/*Afficher banniere de jeu pour les joueurs : info de vie, portee des bombes, bonus actifs , etc...*/
	public void info_players(Player J1, Player J2 , Bomb bombe,Bonus bonus){
		
		
		for(int i = 0 ;i<=17 ; i++){// effacer les infos de la 1ere et derniere lignes
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.filledSquare(i, -0.5, 0.5);
			StdDraw.filledSquare(i, 17.5, 0.5);
		}
		if(J1.getId() == 1 ){
			
			float y = -0.5f;
			float x = 0.5f;
			StdDraw.picture(x+18,y,"/bomberman_picture/Konami.png",5,2.6);
			
			Font font = new Font("Tahoma" , Font.ITALIC , 18);
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.WHITE);
			
			StdDraw.text(x,y, "J1" );
			//StdDraw.picture(x+0.75,y,"/bomberman_picture/p1.png",0.8,0.8);
			
			StdDraw.picture(x+2,y,"/bomberman_picture/heart2.png",0.8,0.8);
			StdDraw.text(x+2,y,Integer.toString(J1.getLife()) );
			
			StdDraw.picture(x+3.5,y,"/bomberman_picture/bomb.png",0.8,0.8);
			StdDraw.text(x+4.5, y,":"+Integer.toString(bombe.getNbBombs(1))+"/"+Integer.toString(J1.getNb_bomb()));
			StdDraw.picture(x+5.5,y,"/bomberman_picture/range.png");
			StdDraw.text(x+6,y," +"+ Integer.toString(bonus.getJ1_bomb_range()));
			
			float  speed = J1.getdX() / 0.2f ;
			
			StdDraw.picture(x+7.25,y,"/bomberman_picture/speed.png");
			StdDraw.text(x+8,y," x"+ Float.toString(speed));
	
			if(J1.isPasse_muraille()){
				StdDraw.picture(x+9, y, "/bomberman_picture/ghost.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+10,y,0.5);
			}
			if(J1.isShield()){
				StdDraw.picture(x+10, y, "/bomberman_picture/shield.png",0.8,0.8);	
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+10,y,0.5);
			}
			if(J1.isKick()){
				StdDraw.picture(x+11, y, "/bomberman_picture/kick.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+11,y,0.5);
			}
			if(bonus.isJ1_mine_bomb()){
				StdDraw.picture(x+12, y, "/bomberman_picture/mine.png",0.8,0.8);	
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+12,y,0.5);
			}
			if(bonus.isJ1_red_bomb()){
				StdDraw.picture(x+13, y, "/bomberman_picture/redbomb.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+13,y,0.5);
			}
			if(bonus.isJ1_line_bomb()){
			
				StdDraw.picture(x+14, y, "/bomberman_picture/linebomb.jpeg",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+14, y, 0.3);
			}
			
			if(bonus.isJ1_flamme_verte()){
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.picture(x+15, y, "/bomberman_picture/greenfire.png",0.8,0.8);
				
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x+15, y, 0.3);
			}
			

		}
		
		if(J2.getId() == 2 ){
			
			float y = 17.5f;
			float x = 20.5f;
			
			StdDraw.picture(x-19,y,"/bomberman_picture/Banner1.jpg",3,1);
 
			
			Font font = new Font("Tahoma" , Font.ITALIC , 18);
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(x,y,"J2");
			StdDraw.picture(x-2,y,"/bomberman_picture/heart2.png",0.8,0.8);
			StdDraw.text(x-2,y,Integer.toString(J2.getLife()) );
			StdDraw.picture(x-4.5, y, "/bomberman_picture/bomb.png",0.8,0.8);
			StdDraw.text(x-3.5, y,":"+Integer.toString(bombe.getNbBombs(2))+"/"+Integer.toString(J2.getNb_bomb()));
			StdDraw.picture(x-6, y, "/bomberman_picture/range.png");
			StdDraw.text(x-5.5, y," +"+Integer.toString(bonus.getJ2_bomb_range()));
			float  speed = J2.getdX()/0.2f;
			StdDraw.picture(x-8,y,"/bomberman_picture/speed.png");
			StdDraw.text(x-7.25,y,"x" + Float.toString(speed));

			if(J2.isPasse_muraille()){
				StdDraw.picture(x-9, y, "/bomberman_picture/ghost.png",0.8,0.8);
			}
			else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-9,y,0.5);
			}
			
			if(J2.isShield()){
				StdDraw.picture(x-10, y, "/bomberman_picture/shield.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-10,y,0.5);
			}
			if(J2.isKick()){
				StdDraw.picture(x-11, y, "/bomberman_picture/kick.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-11,y,0.5);
			}
			if(bonus.isJ2_mine_bomb()){
				StdDraw.picture(x-12, y, "/bomberman_picture/mine.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-12,y,0.5);
			}
			if(bonus.isJ2_red_bomb()){
				StdDraw.picture(x-13, y, "/bomberman_picture/redbomb.png",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-13,y,0.5);
			}
			if(bonus.isJ2_line_bomb()){
				StdDraw.picture(x-14, y, "/bomberman_picture/linebomb.jpeg",0.8,0.8);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-14, y, 0.3);
			}
			if(bonus.isJ2_flamme_verte()){
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.picture(x-15, y, "/bomberman_picture/greenfire.png",0.8,0.8);
				//StdDraw.filledSquare(x-15, y, 0.3);
			}else{
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(x-15, y, 0.3);
			}

		}
		
		
	}
	
	public void finalize(){
		
	}
	
}
