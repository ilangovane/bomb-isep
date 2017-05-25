import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.introcs.StdDraw;


public class Bomb {
	private int owner_id; // id de celui qui a d�pos� la bombe id:1<=>J1 | id:2<=>J2 | id:3 <=> IA
	private long t_explosion ; // le timestamps de l'explosion
	private int X; // coordonn�e X de la bombe
	private int Y; //coordonn�e Y de la bombe
	private int Z;
	private Set<Bomb> Bombs = new HashSet<Bomb>();
	private int range = 3; //port�e de la bombe
	private boolean is_red = false;//les bombes rouges peuvent d�truire les murs et les joueurs plac�s derri�re
	//choix de deux Constructeurs
	//lors de la cr�ation d'une bombe
	public Bomb(int id,int line, int column){
		this.owner_id = id;
		this.t_explosion = System.currentTimeMillis() + 5000 ; // la bombe explose 5 secondes apr�s �tre d�pos�e
		this.X = column;
		this.Y = line;

	}
	public Bomb(int id,int line, int column,int z){
		this.owner_id = id;
		this.t_explosion = System.currentTimeMillis() + 3000 ; // la bombe disparait apres 3 s
		this.X = column;
		this.Y = line;
		this.Z=z;

	}
	//lors de la cr�ation de la liste de bombs
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



	public int getZ() {
		return Z;
	}

	public void setZ(int z) {
		Z = z;
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
			if(bo.getOwner_id() == id && bo.getZ() != -1){
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

	/*Ajoute une bombe � la liste HashSet Bombs*/
	public void addBomb(int id , int x , int y){
		Bombs.add(new Bomb(id,y,x));
		this.setBombs(Bombs);
	}
	
	public void addBomb(int id , int x , int y,int z){
		Bombs.add(new Bomb(id,y,x,z));
		this.setBombs(Bombs);
	}
	/*Lorsque la touche espace ou W est enfonc�e les bombes s'ajoutent � la liste aucun doublons n'est tol�r�
	 * Un doublons => bombes au m�me emplacement aux coordonn�es (X,Y) d'ou la m�thode this.is_bomb_already_exists(x, y)*/
	public void putBomb(Board b ,Player J,Bonus bonus,Animation anim,Player other){
		if(bonus.isJ1_line_bomb() && StdDraw.isKeyPressed(KeyEvent.VK_W) && J.getId() == 1){//bonus lingne bombe actif
						
						if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {//touche S press�e
							 int i = 1;
							 while( !other.colision_player(other, (int) J.getX(), (int) J.getY() - i) && b.isGrass((int) J.getY() - i ,(int) J.getX() ) && !this.is_bomb_already_exists( (int)J.getX(), (int)J.getY()-i)){
								 this.addBomb(1, (int) J.getX() , (int)J.getY() - i);
								 i++;
							 }
			       	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){//touche Q press�e
						 int i = 1;
						 while(!other.colision_player(other, (int) J.getX()-i, (int) J.getY()) && b.isGrass((int) J.getY() ,(int) J.getX()-i ) && !this.is_bomb_already_exists( (int)J.getX()-i, (int)J.getY())){
							 this.addBomb(1, (int) J.getX() - i, (int) J.getY());
							 i++;
						 }
			       	}else if(StdDraw.isKeyPressed(KeyEvent.VK_D)){//touche D press�e
						 int i = 1;
						 while(!other.colision_player(other, (int) J.getX()+i, (int) J.getY()) && b.isGrass((int) J.getY() ,(int) J.getX()+i ) && !this.is_bomb_already_exists( (int)J.getX()+i, (int)J.getY())){
							 this.addBomb(1, (int) J.getX() + i, (int) J.getY());
							 i++;
						 }
			      	}else if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){//touche Z press�e
						 int i = 1;
						 while(!other.colision_player(other, (int) J.getX(), (int) J.getY() + i) && b.isGrass((int) J.getY()+i  ,(int) J.getX() ) && !this.is_bomb_already_exists( (int)J.getX(), (int)J.getY()+i)){
							 this.addBomb(1, (int) J.getX() , (int) J.getY()+i);
							 i++;
						 }
						 
			      	}
      	}else if(bonus.isJ2_line_bomb() && StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && J.getId() == 2){
		
			if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {//touche S press�e
				 int i = 1;
				 while(!other.colision_player(other, (int) J.getX(), (int) J.getY() - i) && b.isGrass((int) J.getY() - i ,(int) J.getX() ) && !this.is_bomb_already_exists( (int)J.getX(), (int)J.getY()-i)){
					 this.addBomb(1, (int) J.getX() , (int)J.getY() - i);
					 i++;
				 }
       	}else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){//touche Q press�e
			 int i = 1;
			 while(!other.colision_player(other, (int) J.getX()-i, (int) J.getY() ) && b.isGrass((int) J.getY() ,(int) J.getX()-i ) && !this.is_bomb_already_exists( (int)J.getX()-i, (int)J.getY())){
				 this.addBomb(1, (int) J.getX() - i, (int) J.getY());
				 i++;
			 }
       	}else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){//touche D press�e
			 int i = 1;
			 while(!other.colision_player(other, (int) J.getX()+i, (int) J.getY() ) && b.isGrass((int) J.getY() ,(int) J.getX()+i ) && !this.is_bomb_already_exists( (int)J.getX()+i, (int)J.getY())){
				 this.addBomb(1, (int) J.getX() + i, (int) J.getY());
				 i++;
			 }
      	}else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){//touche Z press�e
			 int i = 1;
			 while(!other.colision_player(other, (int) J.getX(), (int) J.getY() + i) && b.isGrass((int) J.getY()+i  ,(int) J.getX() ) && !this.is_bomb_already_exists( (int)J.getX(), (int)J.getY()+i)){
				 this.addBomb(1, (int) J.getX() , (int) J.getY()+i);
				 i++;
			 }
      	}
			
			return;//on sort de la fonction
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_W) && J.getId() == 1 && !b.isWallDestructible((int) J.getY(), (int) J.getX())){//touche W press�e
			if(!this.is_bomb_already_exists((int)J.getX(), (int)J.getY()) && this.getNbBombs(1) <J.getNb_bomb()){
				if(bonus.isJ1_mine_bomb()){
					addBomb(J.getId() , (int)J.getX() , (int)J.getY() , -1);
					bonus.setJ1_mine_bomb(false);
					Animation add = new Animation("mine_under_ground" , (int)J.getX()+0.5f , (int)J.getY()+0.5f , 3000);
					anim.add_liste(add);
				
				}else{
					addBomb(J.getId() , (int)J.getX() , (int)J.getY());
					
				}
				
				//b.setBomb(x,y);
			}
			
		}
		
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && J.getId() == 2&& !b.isWallDestructible((int) J.getY(), (int) J.getX()) ){//touche ESPACE press�e
   		 
			if(!this.is_bomb_already_exists((int) J.getX(), (int) J.getY()) && this.getNbBombs(2) <J.getNb_bomb()){
				if(bonus.isJ2_mine_bomb()){
					addBomb(J.getId() , (int)J.getX() , (int)J.getY() , -1);
					bonus.setJ2_mine_bomb(false);
					Animation add = new Animation("mine_under_ground" , (int)J.getX()+0.5f , (int)J.getY()+0.5f , 3000);
					anim.add_liste(add);
			
				}else{
					addBomb(J.getId() , (int)J.getX() , (int)J.getY());	
				}
				
			}
		}
		
	}
	
	/*avant d'ajouter la liste � la bombe il faut v�rifier qu'elle n'existe pas pour �viter les doublons
	 * Autre cas utile : lorsqu'un joueur perd une vie, il est possible qu'il meurt � nouveau � cause d'une bombe plac� � c�t�s (ce qui est dommage) */
	public boolean is_bomb_already_exists(int x,int y){
		Iterator<Bomb> it = Bombs.iterator();
		boolean exist = false;
		while (it.hasNext()){//parcours la liste de bombes
			Bomb bo = it.next();
			if((int) bo.getX() == x && (int) bo.getY() == y){
				return true;
			}
		
		}
		
		return exist;
	}
	
	public boolean is_there_mine_bomb(int x,int y){
		Iterator<Bomb> it = Bombs.iterator();
		boolean exist = false;
		while (it.hasNext()){//parcours la liste de bombes
			Bomb bo = it.next();
			if((int) bo.getX() == x && (int) bo.getY() == y && bo.getZ() == -1){
				return  true;
			}
		
		}
		
		return exist;
	}
	/*Renvoie la bombe ayant les coordonn�es suivantes : (X,Y) = (x,y)*/
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

	
	//modifie le timer de l'explosion d'une bombe � "maintenant" 
	public void explose_bomb_around(Bomb bo , Board b , Player J1 , Player J2,Bonus bonus,Animation anim){
		bo.setT_explosion(System.currentTimeMillis());
		bo.Bombs.add(bo);
		bo.explose(b, J1, J2,bonus,anim);
	}
	


	/*Retire les bombes de la liste lors de l'explosion
	 * Dï¿½truit les murs jusqu'au mur cassable */
	public void explose(Board b,Player J1, Player J2, Bonus bonus,Animation anim){ 
		Iterator<Bomb> it = Bombs.iterator();
		int i;
		while (it.hasNext()){//parcours la liste de bombe

			Bomb bo = it.next();
		if(bo.getZ() == -1 && bo.getT_explosion() <System.currentTimeMillis()){//bombe mine
			b.setArea(bo.getY(), bo.getX(), "green");
				if(J1.is_at_point(bo.getX(), bo.getY())){
					bo.setRange(0);
					explose_bomb_around( bo , b , J1 ,J2,bonus,anim);
					// le joueur 1 se trouve dans la portï¿½e de la bombe
					if(!J1.isShield()){
						bonus.setJ1_bomb_range(3);
						bonus.setJ1_red_bomb(false);
						bonus.setJ1_line_bomb(false);
						bonus.setJ1_mine_bomb(false);
					}

					J1.kill();
					b.setArea(bo.getY(), bo.getX(), "green");
					it.remove();
					if(J1.getX() == -1 && J1.getY() == -1){
						anim.add_liste(new Animation("dead" ,bo.getX()+0.5f , bo.getY()+0.5f ,  1000 ));
						J1.setX(1);
						J1.setY(1);	
					}

				}
			
				if(J2.is_at_point(bo.getX(), bo.getY()) ){
					bo.setRange(0);
					explose_bomb_around( bo , b , J1 ,J2,bonus,anim);
					// le Joueur 2 se trouve dans la portï¿½e de la bombe 
					if(!J2.isShield()){
						bonus.setJ2_bomb_range(3);
						bonus.setJ2_red_bomb(false);
						bonus.setJ2_line_bomb(false);
						bonus.setJ2_mine_bomb(false);
					}
					J2.kill();
					b.setArea(bo.getY(), bo.getX(), "green");
					it.remove();
					if(J2.getX() == -1 && J2.getY() == -1){// du  a la methode kill()
						anim.add_liste(new Animation("dead" ,bo.getX() +0.5f , bo.getY()+0.5f ,  1000 ));
						J2.setX(19);
						J2.setY(15);
					}

				}

		}
		
			if( bo.getT_explosion() <  System.currentTimeMillis() && bo.getZ() == 0){ // le minuteur prend fin
				i=0;
				// la bomb a une portï¿½e de 3 et s'arrete au mur incassable dans toute les directions
				boolean is_red_bomb = bo.isIs_red();
				int bomb_range = bo.getRange(); //portï¿½e de la bombe de l'objet "bo" qui change ï¿½ chaque boucle
				while(b.isDestructible(bo.getY()+i, bo.getX()) && i<=bomb_range){// soit case verte un mur cassable
					//StdDraw.picture(bo.getX()+0.5, bo.getY()+i+0.5, "/bomberman_picture/explosion.png",1,1);
					Animation anim_add = new Animation("explosion" , bo.getX()+0.5f, bo.getY()+i+0.5f , 1000);
					anim.add_liste(anim_add);

					if(b.isWallDestructible(bo.getY()+i, bo.getX()) ){
						b.setArea(bo.getY() +i, bo.getX(), "green");// coloration case verte 
						b.setElementMatrice(bo.getY() +i, bo.getX(), 3); // matrice mis ï¿½ jour 
						//fonction qui crÃ©e le bonus dans la liste 
						bonus.create_bonus(bo.getX(),bo.getY()+i); //on rÃ©cupÃ¨re les coordonnÃ©es du mur cassable pour les mettres en parametre 
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
					}
					if(J1.is_at_point(bo.getX(), bo.getY()+i)){
						// le joueur 1 se trouve dans la portï¿½e de la bombe
						if(!J1.isShield()){
							bonus.setJ1_bomb_range(3);
							bonus.setJ1_red_bomb(false);
							bonus.setJ1_line_bomb(false);
							bonus.setJ1_mine_bomb(false);
						}

						J1.kill();
						b.setArea(bo.getY()+i, bo.getX(), "green");
						
						if(J1.getX() == -1 && J1.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f , bo.getY()+ i +0.5f ,  1000 ));
						}
					}
					if(J2.is_at_point(bo.getX(), bo.getY()+i) ){
						// le Joueur 2 se trouve dans la portï¿½e de la bombe 
						if(!J2.isShield()){
							bonus.setJ2_bomb_range(3);
							bonus.setJ2_red_bomb(false);
							bonus.setJ2_line_bomb(false);
							bonus.setJ2_mine_bomb(false);
						}
						J2.kill();
						b.setArea(bo.getY()+i, bo.getX(), "green");
						if(J2.getX() == -1 && J2.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f , bo.getY()+0.5f+i ,  1000 ));
						}
					}
					
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()+ i),b, J1, J2,bonus,anim);
					i ++;				

				}
				
				//commentaire identique
				i=0;
				while(b.isDestructible(bo.getY()-i, bo.getX()) && i<=bomb_range){	
					//StdDraw.picture(bo.getX() +0.5, bo.getY() -i+ 0.5, "/bomberman_picture/explosion.png",1,1);
					Animation anim_add = new Animation("explosion" , bo.getX() +0.5f, bo.getY() -i+ 0.5f , 1000);
					anim.add_liste(anim_add);

					if(b.isWallDestructible(bo.getY()-i, bo.getX()) ){
						b.setArea(bo.getY()-i, bo.getX(), "green");
						b.setElementMatrice(bo.getY()-i, bo.getX(), 3);
						//fonction qui crÃ©e le bonus dans la liste 
						bonus.create_bonus(bo.getX(),bo.getY()-i); //on rÃ©cupÃ¨re les coordonnÃ©es du mur cassable pour les mettres en parametre 	
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
					}
					if(J1.is_at_point(bo.getX(), bo.getY()-i) ){
						if(!J1.isShield()){
							bonus.setJ1_bomb_range(3);
							bonus.setJ1_red_bomb(false);
							bonus.setJ1_line_bomb(false);
							bonus.setJ1_mine_bomb(false);
						}
						J1.kill();
						b.setArea(bo.getY()-i, bo.getX(), "green");
						if(J1.getX() == -1 && J1.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f , bo.getY()+0.5f-i ,  1000 ));
						}
					}
					if(J2.is_at_point(bo.getX(), bo.getY()-i) ){
						if(!J2.isShield()){
							bonus.setJ2_bomb_range(3);
							bonus.setJ2_red_bomb(false);
							bonus.setJ2_line_bomb(false);
							bonus.setJ2_mine_bomb(false);
						}
						J2.kill();

						b.setArea(bo.getY()-i, bo.getX(), "green");
						if(J2.getX() == -1 && J2.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f , bo.getY()+0.5f-i ,  1000 ));
						}
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()-i),b, J1, J2,bonus,anim);
					i++;
					
				}
				//commentaire identique
				i=0;
				while(b.isDestructible(bo.getY(), bo.getX()+i) && i<=bomb_range){
					//StdDraw.picture(bo.getX()+i +0.5, bo.getY()+0.5,"/bomberman_picture/explosion.png",1,1);
					Animation anim_add = new Animation("explosion" , bo.getX()+i +0.5f, bo.getY()+0.5f , 1000);
					anim.add_liste(anim_add);

					if(b.isWallDestructible(bo.getY(), bo.getX()+i) ){
						b.setArea(bo.getY(), bo.getX()+i, "green");

						b.setElementMatrice(bo.getY(), bo.getX()+i, 3);
						//fonction qui crÃ©e le bonus dans la liste 
						bonus.create_bonus(bo.getX()+i,bo.getY()); //on rÃ©cupÃ¨re les coordonnÃ©es du mur cassable pour les mettres en parametre 
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
						
					}
					
					
					if(J1.is_at_point(bo.getX()+i, bo.getY()) ){
						if(!J1.isShield()){
							bonus.setJ1_bomb_range(3);
							bonus.setJ1_red_bomb(false);
							bonus.setJ1_line_bomb(false);
							bonus.setJ1_mine_bomb(false);
						}
						J1.kill();
						
						b.setArea(bo.getY(), bo.getX()+i, "green");
						if(J1.getX() == -1 && J1.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f+i , bo.getY()+0.5f ,  1000 ));
						}
					}
					if(J2.is_at_point(bo.getX()+i, bo.getY()) ){
						if(!J2.isShield()){
							bonus.setJ2_bomb_range(3);
							bonus.setJ2_red_bomb(false);
							bonus.setJ2_line_bomb(false);
							bonus.setJ2_mine_bomb(false);
						}
						J2.kill();

						b.setArea(bo.getY(), bo.getX()+i, "green");
						if(J2.getX() == -1 && J2.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f + i , bo.getY()+0.5f ,  1000 ));
						}
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()+i,bo.getY()),b, J1, J2,bonus,anim);
					i++;
					

					
				}
				//commentaire identique
				i=0;
				while(b.isDestructible(bo.getY(), bo.getX()-i) && i<=bomb_range){

					Animation anim_add = new Animation("explosion" , bo.getX()-i+0.5f, bo.getY()+0.5f , 1000);
					anim.add_liste(anim_add);
					
					if(b.isWallDestructible(bo.getY(), bo.getX()-i) ){
						b.setArea(bo.getY(), bo.getX()-i, "green");
						b.setElementMatrice(bo.getY(), bo.getX()-i, 3);
						//fonction qui crÃ©e le bonus dans la liste 
						bonus.create_bonus(bo.getX()-i,bo.getY()); //on rÃ©cupÃ¨re les coordonnÃ©es du mur cassable pour les mettres en parametre 
						if(!is_red_bomb){//les bombes rouges continue d'exploser en cassant les murs cassables
							i = 1000;// on sort de la boucle 
						}
						
					}
					if(J1.is_at_point(bo.getX()-i, bo.getY()) ){
						if(!J1.isShield()){
							bonus.setJ1_bomb_range(3);
							bonus.setJ1_red_bomb(false);
							bonus.setJ1_line_bomb(false);
							bonus.setJ1_mine_bomb(false);
						}
						J1.kill();
						
						b.setArea(bo.getY(), bo.getX()-i, "green");
						if(J1.getX() == -1 && J1.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f-i , bo.getY()+0.5f ,  1000 ));
						}
					}
					if(J2.is_at_point(bo.getX()-i, bo.getY()) ){
						if(!J2.isShield()){
							bonus.setJ2_bomb_range(3);
							bonus.setJ2_red_bomb(false);
							bonus.setJ2_line_bomb(false);
							bonus.setJ2_mine_bomb(false);
						}
						J2.kill();

						b.setArea(bo.getY(), bo.getX()-i, "green");
						if(J2.getX() == -1 && J2.getY() == -1){// du  a la methode kill()
							anim.add_liste(new Animation("dead" ,bo.getX() +0.5f - i , bo.getY()+0.5f ,  1000 ));
						}
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()-i,bo.getY()),b, J1, J2,bonus,anim);
					i++;

				}

				it.remove(); // bombe supprimï¿½e de la liste Bombs
				J1.avoid_killing_player_two_times(b , this.getBombs());
				J2.avoid_killing_player_two_times(b , this.getBombs());
				b.setArea(bo.getY(), bo.getX(), "green");// il faut faire disparaitre la bombe de l'ï¿½cran en recoloriant la case en verte
				StdDraw.show(30);

			}


		}
		//StdDraw.show(30);

		
	}
	
}
