import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;


public class Bomb {
	public int owner_id; // id de celui qui a déposé la bombe id:1<=>J1 | id:2<=>J2 | id:3 <=> IA
	public long t_explosion ; // le timestamps de l'explosion
	public int X; // coordonnée X de la bombe
	public int Y; //coordonnée Y de la bombe
	Set<Bomb> Bombs = new HashSet<Bomb>();
	public int range = 3; //portée de la bombe
	boolean is_red = false;//les bombes rouges peuvent détruire les murs et les joueurs placés derrière
	//choix de deux Constructeurs
	//lors de la création d'une bombe
	public Bomb(int id,int line, int column){
		this.owner_id = id;
		this.t_explosion = System.currentTimeMillis() + 5000 ; // la bombe explose 5 secondes après être déposée
		this.X = column;
		this.Y = line;

	}
	
	//lors de la création de la liste de bombs
	public Bomb(){
		
	}
	
	/*Getters et Setters*/
	
	public int getOwner_id() {
		return owner_id;
	}



	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}



	public long getT_explosion() {
		return t_explosion;
	}



	public void setT_explosion(long t_explosion) {
		this.t_explosion = t_explosion;
	}



	public int getX() {
		return X;
	}



	public void setX(int x) {
		X = x;
	}



	public int getY() {
		return Y;
	}



	public void setY(int y) {
		Y = y;
	}



	public Set<Bomb> getBombs() {
		return Bombs;
	}

	public void setBombs(Set<Bomb> bombs) {
		Bombs = bombs;
	}

	public int getNbBombs(int id) {// compte le nombre de bombes dans la  liste Bombs contenant l'owner_id = id
		Iterator<Bomb> it = this.Bombs.iterator();
		int c = 0;//nb de bombe du joueur 
		while (it.hasNext()){//parcours la liste de bombe
			Bomb bo = it.next();
			if(bo.getOwner_id() == id){
				c++;
			}
		}
		return c;
	}

	

	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	

	public boolean isIs_red() {
		return is_red;
	}

	public void setIs_red(boolean is_red) {
		this.is_red = is_red;
	}

	/*Ajoute une bombe à la liste HashSet Bombs*/
	public void addBomb(int id , int x , int y){
		Bombs.add(new Bomb(id,y,x));
		this.setBombs(Bombs);
	}
	
	/*Lorsque la touche espace ou W est enfoncée les bombes s'ajoutent à la liste aucun doublons n'est toléré
	 * Un doublons => bombes au même emplacement aux coordonnées (X,Y) d'ou la méthode this.is_bomb_already_exists(x, y)*/
	public void putBomb(Board b ,int id , int x , int y){
		if(StdDraw.isKeyPressed(KeyEvent.VK_W) && id == 1){//touche W pressée
			if(!this.is_bomb_already_exists(x, y) && this.getNbBombs(1) <4){
				addBomb(id , x , y);
				b.setBomb(x,y);
			}
			
		}
		
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && id == 2){//touche ESPACE pressée
   		 
			if(!this.is_bomb_already_exists(x, y) && this.getNbBombs(2) <4){
				addBomb(id , x , y);
				
				b.setBomb(x,y);
				
			}
		}
		
	}
	
	/*avant d'ajouter la liste à la bombe il faut vérifier qu'elle n'existe pas pour éviter les doublons
	 * Autre cas utile : lorsqu'un joueur perd une vie, il est possible qu'il meurt à nouveau à cause d'une bombe placé à côtés (ce qui est dommage) */
	public boolean is_bomb_already_exists(int x,int y){
		Iterator<Bomb> it = Bombs.iterator();
		boolean exist = false;
		while (it.hasNext()){//parcours la liste de bombes
			Bomb bo = it.next();
			if((int) bo.getX() == x && (int) bo.getY() == y){
				exist = true;
			}
		
		}
		
		return exist;
	}
	
	/*Renvoie la bombe ayant les coordonnées suivantes : (X,Y) = (x,y)*/
	public Bomb find_Bomb(int x , int y){
		Iterator<Bomb> it = Bombs.iterator();
		Bomb find = new Bomb();//bombe null
		while (it.hasNext()){//parcours la liste de bombes
			
			Bomb bo = it.next();
			if((int) bo.getX() == x && (int) bo.getY() == y){
				find =  bo;
			}
		
		}
		return find;
		
	}

	//modifie le timer de l'explosion d'une bombe à "maintenant" 
	public void explose_bomb_around(Bomb bo , Board b , Player J1 , Player J2){
		bo.setT_explosion(System.currentTimeMillis());
		bo.Bombs.add(bo);
		bo.explose(b, J1, J2);
	}
	


	/*Retire les bombes de la liste lors de l'explosion
	 * Détruit les murs jusqu'au mur cassable */
	public void explose(Board b,Player J1, Player J2){ 
		Iterator<Bomb> it = Bombs.iterator();
		int i;
		while (it.hasNext()){//parcours la liste de bombe

			Bomb bo = it.next();
		
			if( bo.getT_explosion() <  System.currentTimeMillis() ){ // le minuteur prend fin
				i=1;
				// la bomb a une portée de 3 et s'arrete au mur incassable dans toute les directions
				boolean is_red_bomb = bo.isIs_red();
				int bomb_range = bo.getRange(); //portée de la bombe de l'objet "bo" qui change à chaque boucle
				while(b.isDestructible(bo.getY()+i, bo.getX()) && i<=bomb_range){// soit case verte un mur cassable
					if(b.isWallDestructible(bo.getY()+i, bo.getX()) ){
						b.setArea(bo.getY() +i, bo.getX(), "green");// coloration case verte 
						b.setElementMatrice(bo.getY() +i, bo.getX(), 3); // matrice mis à jour 
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
						
					}
					if(J1.is_at_point(bo.getX(), bo.getY()+i)){
			// le joueur 1 se trouve dans la portée de la bombe
						J1.kill();
						b.setPlayer(1,1,1);
						// lorsqu'un joueur perd la vie, il est temporairement placé dans un lieu sûre
						J1.setX(-1);
						J1.setY(-1);
						b.setArea(bo.getY()+i, bo.getX(), "green");
					}
					if(J2.is_at_point(bo.getX(), bo.getY()+i) ){
						// le Joueur 2 se trouve dans la portée de la bombe 
						J2.kill();
						b.setPlayer(2,19,15);
						// lorsqu'un joueur perd la vie, il est temporairement placé dans un lieu sûre
						J2.setX(-1);
						J2.setY(-1);
						b.setArea(bo.getY()+i, bo.getX(), "green");
					}
					
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()+ i),b, J1, J2);
					i ++;
				

				}
				
				//commentaire identique
				i=1;
				while(b.isDestructible(bo.getY()-i, bo.getX()) && i<=bomb_range){	
					if(b.isWallDestructible(bo.getY()-i, bo.getX()) ){
						b.setArea(bo.getY()-i, bo.getX(), "green");
						b.setElementMatrice(bo.getY()-i, bo.getX(), 3);
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
					}
					if(J1.is_at_point(bo.getX(), bo.getY()-i) ){
		
						J1.kill();
						b.setPlayer(1,1,1);
						J1.setX(-1);
						J1.setY(-1);
						b.setArea(bo.getY()-i, bo.getX(), "green");
					}
					if(J2.is_at_point(bo.getX(), bo.getY()-i) ){
						J2.kill();
	
						b.setPlayer(2,19,15);
						J2.setX(-1);
						J2.setY(-1);
						b.setArea(bo.getY()-i, bo.getX(), "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()-i),b, J1, J2);
					i++;
				
					
				}
				//commentaire identique
				i=1;
				while(b.isDestructible(bo.getY(), bo.getX()+i) && i<=bomb_range){
					if(b.isWallDestructible(bo.getY(), bo.getX()+i) ){
						b.setArea(bo.getY(), bo.getX()+i, "green");
						b.setElementMatrice(bo.getY(), bo.getX()+i, 3);
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
					}
					if(J1.is_at_point(bo.getX()+i, bo.getY()) ){
						J1.kill();
		
						b.setPlayer(1,1,1);
						J1.setX(-1);
						J1.setY(-1);
						b.setArea(bo.getY(), bo.getX()+i, "green");
					}
					if(J2.is_at_point(bo.getX()+i, bo.getY()) ){
						J2.kill();

						b.setPlayer(2,19,15);
						J2.setX(-1);
						J2.setY(-1);
						b.setArea(bo.getY(), bo.getX()+i, "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()+i,bo.getY()),b, J1, J2);
					i++;
					
					
				}
				//commentaire identique
				i=1;
				while(b.isDestructible(bo.getY(), bo.getX()-i) && i<=bomb_range){
					if(b.isWallDestructible(bo.getY(), bo.getX()-i) ){
						b.setArea(bo.getY(), bo.getX()-i, "green");
						b.setElementMatrice(bo.getY(), bo.getX()-i, 3);
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
					}
					if(J1.is_at_point(bo.getX()-i, bo.getY()) ){
						J1.kill();

						b.setPlayer(1,1,1);
						J1.setX(-1);
						J1.setY(-1);
						b.setArea(bo.getY(), bo.getX()-i, "green");
					}
					if(J2.is_at_point(bo.getX()-i, bo.getY()) ){
						J2.kill();

						b.setPlayer(2,19,15);
						J2.setX(-1);
						J2.setY(-1);
						b.setArea(bo.getY(), bo.getX()-i, "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()-i,bo.getY()),b, J1, J2);
					i++;
				
				}

				it.remove(); // bombe supprimée de la liste Bombs
	        	//this.avoid_kill_player_two_times(b,J1);
	        	//this.avoid_kill_player_two_times(b,J2);
				J1.avoid_killing_player_two_times(b , this.getBombs());
				J2.avoid_killing_player_two_times(b , this.getBombs());
				b.setArea(bo.getY(), bo.getX(), "green");// il faut faire disparaitre la bombe de l'écran en recoloriant la case en verte
			}

			
			

		}

		
	}
	
}
