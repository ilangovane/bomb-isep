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
	int nb_J1 = 0;//nb de bombe déposées
	int nb_J2 = 0;
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

	public int getNb_J1() {// compte le nombre de bombes dans la  liste Bombs contenant l'owner_id = 2
		Iterator<Bomb> it = this.Bombs.iterator();
		int c = 0;//nb de bombe du joueur J1
		while (it.hasNext()){//parcours la liste de bombe
			Bomb bo = it.next();
			if(bo.getOwner_id() == 1){
				c++;
			}
		}
		return c;
	}

	public void setNb_J1(int nb_J1) {
		this.nb_J1 = nb_J1;
	}

	public int getNb_J2() {// compte le nombre de bombes dans la  liste Bombs contenant l'owner_id = 2
		Iterator<Bomb> it = this.Bombs.iterator();
		int c = 0;//nb de bombe du joueur J1
		while (it.hasNext()){//parcours la liste de bombe
			Bomb bo = it.next();
			if(bo.getOwner_id() == 2){
				c++;
			}
		}
		return c;
	}

	public void setNb_J2(int nb_J2) {
		this.nb_J2 = nb_J2;
	}

	
	/*Ajoute une bombe à la liste HashSet Bombs*/
	public void addBomb(int id , int x , int y){
		Bombs.add(new Bomb(id,y,x));
		System.out.println("Add bomb for ID :  |" + id + " X :  |" + x + " Y : " + y);
		this.setBombs(Bombs);
	}
	/*Lorsque la touche espace ou W est enfoncée les bombes s'ajoutent à la liste aucun doublons n'est toléré
	 * Un doublons => bombes au même emplacement aux coordonnées (X,Y) d'ou la méthode this.is_bomb_already_exists(x, y)*/
	public void putBomb(Board b ,int id , int x , int y){
		if(StdDraw.isKeyPressed(KeyEvent.VK_W) && id == 1){//touche W pressée
			if(!this.is_bomb_already_exists(x, y) && this.getNb_J1() <4){
				System.out.println("J1 set a bomb at  X :  |" + x + " Y : " + y );
				addBomb(id , x , y);
				
				b.setBomb(x,y);
			}else{
				System.out.println("Max Bomb for J1 reached : " + this.getNb_J1() );
			}
			
		}
		
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && id == 2){//touche ESPACE pressée
   		 
			if(!this.is_bomb_already_exists(x, y) && this.getNb_J2() <4){
				System.out.println("J2 set a bomb at  X :  |" + x + " Y : " + y );
				addBomb(id , x , y);
				
				b.setBomb(x,y);
				
			}else{
				System.out.println("Max Bombs for J2 reached : " + this.getNb_J2());
			}
		}
		
		System.out.println("Size of the list Bombs : " + Bombs.size());
	}
	
	//avant d'ajouter la liste à la bombe il faut vérifier qu'elle n'existe pas pour éviter les doublons 
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
	public void explose_bomb_around(Bomb bo){
		bo.setT_explosion(System.currentTimeMillis());
		
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
				
				while(b.isDestructible(bo.getY()+i, bo.getX()) && i<=3){// soit case verte un mur cassable
					if(b.isWallDestructible(bo.getY()+i, bo.getX()) ){
						b.setArea(bo.getY() +i, bo.getX(), "green");// coloration case verte 
						b.setElementMatrice(bo.getY() +i, bo.getX(), 3); // matrice mis à jour 
						break ;// on sort de la boucle 
					}
					if(J1.is_at_point(bo.getX(), bo.getY()+i)){
						J1.kill();
						System.out.println("J1 lose a life. LIFE :" + J1.getLife());
						b.setPlayer(1,1,1);
						J1.setX(1);
						J1.setY(1);
						b.setArea(bo.getY()+i, bo.getX(), "green");
					}
					if(J2.is_at_point(bo.getX(), bo.getY()+i)){
						J2.kill();
						System.out.println("J2 lose a life. LIFE :" + J2.getLife());
						b.setPlayer(2,19,15);
						J2.setX(19);
						J2.setY(15);
						b.setArea(bo.getY()+i, bo.getX(), "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()+ i));
					i++;
				}
				
				i=1;
				while(b.isDestructible(bo.getY()-i, bo.getX()) && i<=3){	
					if(b.isWallDestructible(bo.getY()-i, bo.getX()) ){
						b.setArea(bo.getY()-i, bo.getX(), "green");
						b.setElementMatrice(bo.getY()-i, bo.getX(), 3);
						i=10; ;// on sort de la boucle 
					}
					if(J1.is_at_point(bo.getX(), bo.getY()-i)){
						J1.kill();
						System.out.println("J1 lose a life. LIFE :" + J1.getLife());
						b.setPlayer(1,1,1);
						J1.setX(1);
						J1.setY(1);
						b.setArea(bo.getY()-i, bo.getX(), "green");
					}
					if(J2.is_at_point(bo.getX(), bo.getY()-i)){
						J2.kill();
						System.out.println("J2 lose a life. LIFE :" + J2.getLife());
						b.setPlayer(2,19,15);
						J2.setX(19);
						J2.setY(15);
						b.setArea(bo.getY()-i, bo.getX(), "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX(),bo.getY()-i));
					i++;
				}
				
				i=1;
				while(b.isDestructible(bo.getY(), bo.getX()+i) && i<=3){
					if(b.isWallDestructible(bo.getY(), bo.getX()+i) ){
						b.setArea(bo.getY(), bo.getX()+i, "green");
						b.setElementMatrice(bo.getY(), bo.getX()+i, 3);
						i=10; ;// on sort de la boucle 
					}
					if(J1.is_at_point(bo.getX()+i, bo.getY())){
						J1.kill();
						System.out.println("J1 lose a life. LIFE :" + J1.getLife());
						b.setPlayer(1,1,1);
						J1.setX(1);
						J1.setY(1);
						b.setArea(bo.getY(), bo.getX()+i, "green");
					}
					if(J2.is_at_point(bo.getX()+i, bo.getY())){
						J2.kill();
						System.out.println("J2 lose a life. LIFE :" + J2.getLife());
						b.setPlayer(2,19,15);
						J2.setX(19);
						J2.setY(15);
						b.setArea(bo.getY(), bo.getX()+i, "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()+i,bo.getY()));
					i++;
				}
				i=1;
				while(b.isDestructible(bo.getY(), bo.getX()-i) && i<=3){
					if(b.isWallDestructible(bo.getY(), bo.getX()-i) ){
						b.setArea(bo.getY(), bo.getX()-i, "green");
						b.setElementMatrice(bo.getY(), bo.getX()-i, 3);
						i=10 ;// on sort de la boucle 
					}
					if(J1.is_at_point(bo.getX()-i, bo.getY())){
						J1.kill();
						System.out.println("J1 lose a life. LIFE :" + J1.getLife());
						b.setPlayer(1,1,1);
						J1.setX(1);
						J1.setY(1);
						b.setArea(bo.getY(), bo.getX()-i, "green");
					}
					if(J2.is_at_point(bo.getX()-i, bo.getY())){
						J2.kill();
						System.out.println("J2 lose a life. LIFE :" + J2.getLife());
						b.setPlayer(2,19,15);
						J2.setX(19);
						J2.setY(15);
						b.setArea(bo.getY(), bo.getX()-i, "green");
					}
					this.explose_bomb_around(this.find_Bomb(bo.getX()-i,bo.getY()));
					i++;
				}
				it.remove(); // bombe supprimée de la liste Bombs
				b.setArea(bo.getY(), bo.getX(), "green");// il faut faire disparaitre la bombe de l'écran en recoloriant la case en verte
			}

			

		}

		
	}
	
}
