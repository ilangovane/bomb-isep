import java.awt.Font;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.princeton.cs.introcs.StdDraw;
import java.util.Random;
public class Bonus {

	/*
	 * Déclaration des attributs 
	 * 
	 * */
	private int J1_bomb_range=3;
	private int J2_bomb_range=3; // portée des joueurs 
	private boolean J1_red_bomb=false;
	private boolean J2_red_bomb=false; 
	private int X, Y;
	private boolean J1_mine_bomb = false;
	private boolean J2_mine_bomb = false;
	private boolean J1_line_bomb = false;
	private boolean J2_line_bomb = false;
	private int J1_timer=5000;
	private int J2_timer=5000;
	private boolean J1_flamme_verte = false;
	private boolean J2_flamme_verte = false;

	private Set<Bonus> Bonus = new HashSet<Bonus>(); // Nouvelle liste de bonus vide 
	private String type_bonus;
	/*CONSTRUCTEUR*/
	public Bonus(int X, int Y){
		this.X= X;
		this.Y= Y;
		this.type_bonus = random_type(); // renvoie le type au hasard 
		this.type_bonus = "flamme_verte";
	}
	
	/*CONSTRUCTEUR POUR LA CREATION DE LA LISTE DE BONUS*/
	public Bonus(){
		
	}
	/*
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * */
	
	public int getJ1_bomb_range() {
		return J1_bomb_range;
	}

	public void setJ1_bomb_range(int j1_bomb_range) {
		J1_bomb_range = j1_bomb_range;
	}



	public int getJ2_bomb_range() {
		return J2_bomb_range;
	}



	public void setJ2_bomb_range(int j2_bomb_range) {
		J2_bomb_range = j2_bomb_range;
	}



	public boolean isJ1_red_bomb() {
		return J1_red_bomb;
	}



	public void setJ1_red_bomb(boolean j1_red_bomb) {
		J1_red_bomb = j1_red_bomb;
	}



	public boolean isJ2_red_bomb() {
		return J2_red_bomb;
	}



	public void setJ2_red_bomb(boolean j2_red_bomb) {
		J2_red_bomb = j2_red_bomb;
	}



	public boolean isJ1_line_bomb() {
		return J1_line_bomb;
	}

	public void setJ1_line_bomb(boolean j1_line_bomb) {
		J1_line_bomb = j1_line_bomb;
	}



	public boolean isJ2_line_bomb() {
		return J2_line_bomb;
	}

	public void setJ2_line_bomb(boolean j2_line_bomb) {
		J2_line_bomb = j2_line_bomb;
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



	public boolean isJ1_mine_bomb() {
		return J1_mine_bomb;
	}

	public void setJ1_mine_bomb(boolean j1_mine_bomb) {
		J1_mine_bomb = j1_mine_bomb;
	}

	public boolean isJ2_mine_bomb() {
		return J2_mine_bomb;
	}

	public void setJ2_mine_bomb(boolean j2_mine_bomb) {
		J2_mine_bomb = j2_mine_bomb;
	}

	public Set<Bonus> getBonus() {
		return Bonus;
	}



	public void setBonus(Set<Bonus> bonus) {
		Bonus = bonus;
	}

	
	
	public String getType_bonus() {
		return type_bonus;
	}

	public void setType_bonus(String type_bonus) {
		this.type_bonus = type_bonus;
	}
	
	public int getJ1_timer() {
		return J1_timer;
	}

	public void setJ1_timer(int j1_timer) {
		J1_timer = j1_timer;
	}

	public int getJ2_timer() {
		return J2_timer;
	}

	public void setJ2_timer(int j2_timer) {
		J2_timer = j2_timer;
	}

	public boolean isJ1_flamme_verte() {
		return J1_flamme_verte;
	}

	public void setJ1_flamme_verte(boolean j1_flamme_verte) {
		J1_flamme_verte = j1_flamme_verte;
	}

	public boolean isJ2_flamme_verte() {
		return J2_flamme_verte;
	}

	public void setJ2_flamme_verte(boolean j2_flamme_verte) {
		J2_flamme_verte = j2_flamme_verte;
	}

	public void create_bonus(int X, int Y){
		if (is_hidden()==true){ // si p = 20 %
				this.Bonus.add(new Bonus(X,Y)); // on ajoute une bombe dans la liste

		}
	}

	public boolean is_hidden() //Fonction pour générer une probabilité de 20% pour que les bonus s'affiche pour 1 blocs/5 cassable
	{
		Random rand = new Random();
		
		int nombreAleatoire = rand.nextInt(5); //Cette méthode va revoyer des nombres entre 0 et 4 donc 5 valeurs 
		
		if (nombreAleatoire == 2){ // Si 1 valeurs sur 5 est trouvé
			return true; // alors on a une prob de 20%
		}
		return false;
	}
	
		public String random_type(){ // Fonction permettant de choisir le type bonus au hasard 
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(15); // 15 bonus en tout 
		switch (nombreAleatoire){
		case 0:
			//retourne flamme bleu : portée -1
			return "flamme_bleu";
		case 1:
			//retourne flamme jaune : portée +1
			return "flamme_jaune";
		case 2:
			//retourne flamme rouge : portée +10
			return "flamme_rouge";
		case 3:
			//retourne bombe rouge : Eclate plusieurs murs destructibles 
			return "bombe_rouge";
		case 4: 
			return "vie";
		case 5: 
			return "speed_up";
		case 6: 
			return "speed_down";
		case 7: 
			return "bombe_plus";
		case 8: 
			return "bombe_moins";	
		case 9 : 
			return "shield";
		case 10 :
			return "passe_muraille";
		case 11 :
			return "mine";
		case 12 :
			return "kick";
		case 13 : 
			return "bomb_line";
		case 14 : 
			return "flamme_verte";
		default : 
			return "vie";
		}
	}
	
	/*
	 * COLLECTE BONUS
	 * */
	public void collect_bonus(Player J1, Player J2, Board b) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		Iterator<Bonus> it = this.Bonus.iterator(); // on parcours l'element du premier jusqu'au dernier grace au curseur iterator
		Bomb Bomb = new Bomb();
		Audio audiobonus = new Audio("Bomberman/src/bonus.wav");
		Audio audioSuper = new Audio("Bomberman/src/superbonus.wav");
		Audio audioPass = new Audio("Bomberman/src/passe_muraille.wav");

		
		while (it.hasNext()){//parcours la liste de bonus
			Bonus bo = it.next();
			
			
			if ((int)(J1.getX()) == bo.getX() && (int)(J1.getY()) == bo.getY()){ //si mon joueur 1 et le bonus sont ÃƒÂ  la meme position
				// Je n'affiche plus le bonus : j'appelle la fonction setArea et je colorie la case en vert
				audiobonus.start();

				b.setArea(bo.getY(), bo.getX() , "green");
				

				
				//j'affecte au J1 les fonctionnalitÃƒÂ©s du bonus en fonction du type de bonus
				int range = this.getJ1_bomb_range();
				int timer1 = this.getJ1_timer();
				int vie = J1.getLife();
				float dX = J1.getdX();
				float dY = J1.getdY();

				switch (bo.getType_bonus()){
				
				case "flamme_bleu":
					if (range>1){
						this.setJ1_bomb_range(range-1);
					}
					break;
				case "flamme_jaune":
					if (range>1 && range<=10){
						this.setJ1_bomb_range(range+1);
					}
					break;
				case "flamme_rouge":
					this.setJ1_bomb_range(10);
					break;
				case "bombe_rouge":
					this.setJ1_red_bomb(true);
					break;
				case "vie": 
					J1.setLife(vie+1);
					break ;
				case "speed_up": 
					if (J1.getdX()<0.8f && J1.getdY()<0.8f){
					J1.setdX(dX*2); 
					J1.setdY(dY*2);
					}
					break ;
				case "speed_down":
					if (J1.getdX()>0.05f && J1.getdY()>0.05f){
					J1.setdX(dX/2); 
					J1.setdY(dY/2);
					}
					break;
				case "bombe_plus":
					J1.setNb_bomb(J1.getNb_bomb()+2);
					if (J1.getNb_bomb()>7)
						J1.setNb_bomb(7);
					break ;
				case "bombe_moins": 
					J1.setNb_bomb(J1.getNb_bomb()-2);
					if (J1.getNb_bomb()<2)
						J1.setNb_bomb(2);
					break ;	
				case "shield":
					J1.setShield(true);
					break ;	
				case "passe_muraille":
					audioSuper.start();
					audioPass.start();
					J1.setPasse_muraille(true);
					break;
				case "mine" :
					this.setJ1_mine_bomb(true);
					break;
				case "kick" :
					audioSuper.start();
					J1.setKick(true);
					break;
				case "bomb_line":
					audioSuper.start();
					this.setJ1_line_bomb(true);
					break;
				case "flamme_verte": 
					audioSuper.start();
					if (range>1 && range<=10){
						this.setJ1_bomb_range(range+1);
					}
					if (timer1>3000){
						setJ1_flamme_verte(true);
						setJ1_timer(getJ1_timer() -1000);
					}
					break;
				}
				
				//je retire la bombe de la liste 
				it.remove();
			}
			
			
			else if ((int)J2.getX() == bo.getX() && (int)J2.getY() == bo.getY()){
				audiobonus.start();

				// je n'affiche plus le bonus : j'appelle la fonction setArea et je colorie la case en vert
				b.setArea(bo.getY(), bo.getX() , "green");
				

				
				//j'affecte au J2 les fonctionnalitÃ©es du bonus en fonction du type de bonus
				int range = this.getJ2_bomb_range();
				int vie = J2.getLife();
				int timer2 = this.getJ2_timer();

				float dX = J2.getdX();
				float dY = J2.getdY();

				switch (bo.getType_bonus()){
				
				case "flamme_bleu":
					if (range>1){
						this.setJ2_bomb_range(range-1);
					}
					break;
				case "flamme_jaune":
					if (range>1){
						this.setJ2_bomb_range(range+1);
					}
					break;
				case "flamme_rouge":
					this.setJ2_bomb_range(10);
					break;
				case "bombe_rouge":
					this.setJ2_red_bomb(true);
					break;
				case "vie": 
					J2.setLife(vie+1);
					break ;
				case "speed_up": 
					if (J2.getdX()<0.8f && J2.getdY()<0.8f){
					J2.setdX(dX*2); 
					J2.setdY(dY*2);
					}
					break ;
				case "speed_down":
					if (J2.getdX()>0.05f && J2.getdY()>0.05f){
					J2.setdX(dX/2); 
					J2.setdY(dY/2);
					}
					break;
				case "bombe_plus":
					J2.setNb_bomb(J2.getNb_bomb()+2);
					if (J2.getNb_bomb()>7)
						J2.setNb_bomb(7);
					break ;
				case "bombe_moins": 
					J2.setNb_bomb(J2.getNb_bomb()-2);
					if (J2.getNb_bomb()<2)
						J2.setNb_bomb(2);
					break ;	
				case "shield":
					J2.setShield(true);
					break ;	
				case "passe_muraille":
					audioSuper.start();
					audioPass.start();
					J2.setPasse_muraille(true);
					break;
				case "mine" :
					this.setJ2_mine_bomb(true);
					break;
				case "kick" :
					audioSuper.start();
					J2.setKick(true);
					break;
				case "bomb_line":
					audioSuper.start();
					this.setJ2_line_bomb(true);
					break;
				case "flamme_verte": 
					audioSuper.start();
					if (range>1 && range<=10){
						this.setJ2_bomb_range(range+1);
						}
					if (timer2>3000){
						setJ2_flamme_verte(true);
						setJ2_timer(getJ2_timer() -1000);
					}
					break;
			}
				it.remove();
			}
		}
	}

	//FONCTION POUR SYNCHRONISER LA LISTE DE BOMBE ET LA LISTE DE BONUS
	public void synchro(Bomb bomb) {// fonction synchronize decrit ci-dessus
		Iterator<Bomb> it = bomb.getBombs().iterator();
		while (it.hasNext()){//parcours la liste de bombe
			Bomb bo = it.next(); // un élement de la liste de Bombes
			if(bo.getOwner_id() == 1){
				bo.setRange(J1_bomb_range) ; // on mets à jour la portée pour le joueur 1
				bo.setIs_red(J1_red_bomb); // on mets à jour le type de bombde du joueur 1
			//getter et setter de bomb pour modifier la portée et le boolean is_red du joeuur 1
			}else if(bo.getOwner_id() == 2){
				//getter et setter de bomb pour modifier la portée et le boolean is_red du joueur 2
				bo.setRange(J2_bomb_range) ; // on mets jour la portée pour le joueur 2
				bo.setIs_red(J2_red_bomb); // on mets à jour le type de bombde du joueur 2
			}
		}
		
	}
	
}
