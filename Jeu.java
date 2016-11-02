import java.util.ArrayList;

/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure 
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Joueur joueur;
	private int temps;

	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
		joueur = new Joueur("Igor", 40);
		joueur.addPiece(pieceCourante);
		temps = 2;
	}


	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece dehors;
		Piece salleTD;
		Piece taverne;
		Piece batimentC;
		Piece burreau;

		// création de tout les objets
		ObjetZork poubelle = new ObjetZork("poubelle", 35);
		ObjetZork detritus = new ObjetZork("détritus", 10);
		ObjetZork voiture = new ObjetZork("voiture");

		// création des pieces
		dehors = new Piece("devant le batiment C", 30);
		salleTD = new Piece("une salle de TD dans le batiment G", 6);
		taverne = new Piece("la taverne", 10);
		batimentC = new Piece("le batiment C", 20);
		burreau = new Piece("le secrétariat", 0);
		dehors.ajouter(poubelle);
		dehors.ajouter(detritus);
		dehors.ajouter(voiture);


		// initialise les sorties des pieces
		dehors.setSorties(null, salleTD, batimentC, taverne);
		salleTD.setSorties(null, null, null, dehors);
		taverne.setSorties(null, dehors, null, null);
		batimentC.setSorties(dehors, burreau, null, null);
		burreau.setSorties(null, null, null, batimentC);

		// le jeu commence dehors
		pieceCourante = dehors;
	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			if (temps == 0)
			{
				System.out.println("Vous n'avez plus de temps");
				break;
			}
			else
			{
				Commande commande = analyseurSyntaxique.getCommande();
				termine = traiterCommande(commande);
			}
		}
		System.out.println("Merci d'avoir joué.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le monde de Zork !");
		System.out.println("Zork est un nouveau jeu d'aventure.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}


	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) 
		{
			afficherAide();
		} 
		else if (motCommande.equals("aller")) 
		{
			if(!commande.aSecondMot())
			{
				System.out.println("Aller où ?");
			}
			else
			{
				deplacerVersAutrePiece(commande);
			}
		} 
		else if (motCommande.equals("quitter"))
		{
			if (commande.aSecondMot()) 
			{
				System.out.println("Quitter quoi ?");
			} 
			else {
				return true;
			}
		}
		else if (motCommande.equals("fouiller"))
		{
			pieceCourante.afficherListeObjet();
		}
		else if (motCommande.equals("retour"))
		{
			retour();
		}
		else if (motCommande.equals("trajet"))
		{
			joueur.afficherListePiece();
		}
		else if (motCommande.equals("prendre"))
		{
			if(!commande.aSecondMot())
			{
				System.out.println("Prendre quoi?");
			}
			else
			{
				prendre(commande.getSecondMot());
			}
		}
		else if (motCommande.equals("poser"))
		{
			if(!commande.aSecondMot())
			{
				System.out.println("Poser quoi?");
			}
			else
			{
				poser(commande.getSecondMot());
			}
		}
		else if (motCommande.equals("inventaire"))
		{
			joueur.afficherListeObjet();
		}
		else if (motCommande.equals("temps"))
		{
			afficherTemps();
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("sur le campus de l'Université Paris 13.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		String direction = commande.getSecondMot();
		temps -- ;

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) 
		{
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} 
		else 
		{
			pieceCourante = pieceSuivante;
			joueur.addPiece(pieceCourante);
			System.out.println(pieceCourante.descriptionLongue());
		}
	}

	/**
	 *  Le joueur essaie de retourner dans la dernière pièce
	 *  qu'il a visité. Si il n'a visité que la pièce de
	 *  départ il y reste.
	 */
	public void retour()
	{
		joueur.removeLastPiece();
		Piece pieceSuivante = (joueur.getTableauPiece()).get(joueur.getNbrPieces()-1);
		if (pieceCourante != pieceSuivante)
		{
			temps++;
		}
		pieceCourante = pieceSuivante;
		System.out.println(pieceCourante.descriptionLongue());
	}

	/**
	 *  Le joueur essaie de prendre un objet nommé objet.
	 *  Si la pièce a l'objet et le joueur peut le porter
	 *  retourne true, retour false sinon
	 *
	 * @param objet  L'objet à prendre
	 * @return true  si l'objet a pû être pris, false sinon
	 */
	public boolean prendre(String objet)
	{
		ObjetZork test = new ObjetZork("erreur");
		if (test.equals( pieceCourante.chercher(objet))) // Si l'a pièce n'a pas l'objet, chercher renvoie l'ObjetZork ("erreur")
		{
			return false ;
		}
		test = pieceCourante.chercher(objet);
		if(joueur.ajouter(test)) // Si on pû ajouter l'objet à l'inventaire du joueur
		{
			pieceCourante.retirer(test);
		
		return true;
		}
		return false ;
	}

	/**
	 *  Le joueur essaie de poser un objet nommé objet.
	 *  Si le joueur a l'objet et la pièce peut le contenir
	 *  retourne true, retour false sinon
	 *
	 * @param objet  L'objet à poser
	 * @return true  si l'objet a pû être posé, false sinon
	 */
	public boolean poser(String objet)
	{
		ObjetZork test = new ObjetZork("erreur");
		if (test.equals( joueur.chercher(objet))) // Si le joueur n'a pas l'objet, chercher renvoie l'ObjetZork ("erreur")
		{
			return false ;
		}
		test = joueur.chercher(objet);
		if (pieceCourante.ajouter(test)) // Si on pû ajouter l'objet à la pièce
		{
			joueur.retirer(test);
			return true;
		}
		return false ;
	}

	/**
	 *  Affiche le temps restant avant la fin du jeu
	 */
	public void afficherTemps()
	{
		System.out.println("Il vous reste " +temps +" minutes");
	}
}

