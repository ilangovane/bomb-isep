import java.awt.Font;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Random;
public class Bonus {

	/*
	 * Déclaration des attributs 
	 * 
	 * */
	int J1_bomb_range=3;
	int J2_bomb_range=3; // portée des joueurs 
	boolean J1_red_bomb=false;
	boolean J2_red_bomb=false; 
	int X, Y;
	Set<Bonus> Bonus = new HashSet<Bonus>(); // Nouvelle liste de bonus vide 
	String type_bonus;
	/*CONSTRUCTEUR*/
	public Bonus(int X, int Y){
		this.X= X;
		this.Y= Y;
		this.type_bonus = random_type(); // renvoie le type au hasard 
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

	public void create_bonus(int X, int Y){
		if (is_hidden()==true){ // si p = 20 %
			System.out.println("BONUS CREEE X:" + X + " Y:"+Y );
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
		int nombreAleatoire = rand.nextInt(9); // 9 bus en tout 
		switch (nombreAleatoire){
		case 0:
			//retourne flamme bleu
			return "flamme_bleu";
		case 1:
			//retourne flamme bleu
			return "flamme_jaune";
		case 2:
			//retourne flamme bleu
			return "flamme_rouge";
		case 3:
			//retourne flamme bleu
			return "bombe_rouge";
		default:
				return "flamme_bleu";
		}
	}
	
	/*
	 * COLLECTE BONUS
	 * */
	public void collect_bonus(Player J1, Player J2, Board b){
		Iterator<Bonus> it = this.Bonus.iterator(); // on parcours l'element du premier jusqu'au dernier grace au curseur iterator
		//System.out.println("TAILLE DE LA LISTE " + Bonus.size());

		
		while (it.hasNext()){//parcours la liste de bonus
			Bonus bo = it.next();
			System.out.println("POS BOMBE X : " + bo.getX() + " Y: "+ bo.getY());
			if ((int)(J1.getX()) == bo.getX() && (int)(J1.getY()) == bo.getY()){ //si mon joueur 1 et le bonus sont à la meme position
				System.out.println("BONUS COLLECTE " + bo.getType_bonus());
				// je n'affiche plus le bonus : j'appelle la fonction setArea et je colorie la case en vert
				b.setArea(bo.getY(), bo.getX() , "green");
				
				//je retire la bombe de la liste 
				it.remove();
				
				//j'affecte au J1 les fonctionnalités du bonus en fonction du type de bonus
				int range = bo.getJ1_bomb_range();
				switch (bo.getType_bonus()){
				
				case "flamme_bleu":
					if (range>1){
						bo.setJ1_bomb_range(range-1);
					}
					break;
				case "flamme_jaune":
					if (range>1){
						bo.setJ1_bomb_range(range+1);
					}
					break;
				case "flamme_rouge":
					bo.setJ1_bomb_range(10);
					break;
				case "bombe_rouge":
					bo.setJ1_red_bomb(true);
					break;

				}
				
			}else if ((int)J2.getX() == bo.getX() && (int)J2.getY() == bo.getY()){
				
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
