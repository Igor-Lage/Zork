import java.util.ArrayList;

/**
 *
 *  Un joueur dans un jeu d'aventure.
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.
 *
 *  Un "Joueur" est un acteur du jeu, il peut donc transporter des objets, si il
 *  ne dépassent pas le poids maximum portable par celui_ci.
 */
public class Joueur
{
	private String nom;
	private int poids;
	private int poidsMax;
	private int nbrObjets;
	private int nbrPieces;
	private ArrayList<ObjetZork> listeObjet;
	private ArrayList<Piece> listePiece;

	/**
	 *  Initialise un joueur avec un nom et une capacite de poids maximale
	 *
	 * @param nom  Le nom du joueur.
	 * @param poidsMax  Le poids maximum portable par le joueur.
	 */
	public Joueur( String nom, int poidsMax )
	{
		this.nom = nom;
		this.poidsMax = poidsMax;
		listePiece = new ArrayList <Piece>();
		listeObjet = new ArrayList <ObjetZork>();
	}

	/**
	 *  Retourne un nom.
	 *
	 * @return nom Le nom du joueur.
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 *  Retourne un poids porté.
	 *
	 * @return poids Le poids porte par le joueur.
	 */	
	public int getPoids()
	{
		return poids;
	}

	/**
	 *  Retourne un poids maximumm.
	 *
	 * @return poidsMax Le poids maximum portable par le joueur
	 */
	public int getPoidsMax()
	{
		return poidsMax;
	}

	/**
	 *  Retourne un nombre de pièces
	 *
	 * @return nbrPieces Le nombre de pièces visitées par le joueur
	 */
	public int getNbrPieces()
	{
		return nbrPieces;
	}

	/**
	 *  Retourne un nombre d'objet.
	 *
	 * @return nbrObjets Le nombre d'objets du joueur.
	 */
	public int getNbrObjets()
	{
		return nbrObjets;
	}

	/**
	 *  Retourne une liste d'objets.
	 *
	 * @return TableauObjet L'ArrayList des objets du joueur.
	 */
	public  ArrayList <ObjetZork> getTableauObjet()
	{
		ArrayList <ObjetZork> TableauObjet = new ArrayList() ;
		TableauObjet = (ArrayList <ObjetZork>) listeObjet.clone();
		return TableauObjet;
	}

	/**
	 *  Retourne une liste de pièces.
	 *
	 * @return TableauPiece L'ArrayList des pièces visitées par le joueur.
	 */
	public  ArrayList <Piece> getTableauPiece()
	{
		ArrayList <Piece> TableauPiece = new ArrayList() ;
		TableauPiece = (ArrayList <Piece>) listePiece.clone();
		return TableauPiece;
	}

	/**
	 *  Affiche la liste des objets du joueur ainsi que
	 *  leur poids total et le poids maximal portable par le joueur
	 */
	public void afficherListeObjet()
	{
		int i;
		ObjetZork o = new ObjetZork("o");
		ArrayList <ObjetZork>test = (ArrayList <ObjetZork>)listeObjet.clone();
		System.out.println("Poids :" +getPoids() +"/" +getPoidsMax());
		for (i=0; i<getNbrObjets(); i++)
		{
			o = test.get(i);
			System.out.println(o.getDescription() +"\t" +o.getPoids());
		}
		if (getNbrObjets() == 0)
		{
			System.out.println("Vous n'avez rien");
		}
	}

	/**
	 *  Retire un objet du joueur, retourne true si
	 *  ça a été possible, false sinon
	 *
	 * @param o l'ObjetZork qu'il faut retire du joueur
	 * return true si le joueur a l'objet, false sinon.
	 */
	public boolean retirer(ObjetZork o)
	{
	for(int i=0; i<listeObjet.size(); i++)
	{
		if((listeObjet.get(i)).equals(o))
		{
			listeObjet.remove(listeObjet.get(i));
			nbrObjets --;
			poids -= o.getPoids();
			return true;
		}
	}
	return false;
	}

	/**
	 *  Ajoute un objet dans l'inventaire du joueur si 
	 *  il est transportable et si le joueur peut porter
	 *  son poids.
	 *
	 * @param o l'ObjetZork à ajouter
	 * @return true si l'objet a pû être ajouté, false sinon
	 */
	public boolean ajouter(ObjetZork o)
	{
		if (o.getTransportable() == false)
		{
			System.out.println("Vous ne pouvez pas prendre ça");
			return false;
		}
		else
		{
			if ((poids + o.getPoids()) < poidsMax)
			{
				listeObjet.add(o);
				nbrObjets ++;
				poids += o.getPoids();
				return true ;
			}
			else
			{
				System.out.println("Cet objet est trop lourd à porter");
				return false;
			}
		}
	}

	/**
	 *  Ajoute une pièce dans la liste des pièces
	 *  visitées par le joueur.
	 *
	 * @param p La pièce à ajouter.
	 */
	public void addPiece(Piece p)
	{
		listePiece.add(p);
		nbrPieces ++;
	}

	/**
	 *  Renvoie le nombre d'occurences d'un objet dans
	 *  l'inventaire du joueur
	 *
	 * @param obj  L'objet que l'on cherche
	 * @return nombreOcc  Le nombre d'occurences de obj dans l'inventaire du joueur
	 */
	public int contientCombienDe(ObjetZork obj)
    	{
		int nombreOcc = 0;
		for(int i=0; i<listeObjet.size(); i++)
		{
			if((listeObjet.get(i)).equals(obj))
			{
				nombreOcc ++;
			}
		}
		return nombreOcc;
    	}
	
	/**
	 *  Renvoie un booléen indiquant si le joueur possède l'objet 
	 *
	 * @param o L'ObjetZork que l'on cherche
	 * @return true si le joueur a l'objet, false sinon
	 */
	public boolean contient(ObjetZork o)
	{
		if(contientCombienDe(o)>0)
		{
			return true;
	    	}
		return false ;
	}

	/**
	 *  Teste si deux instances de Joueur sont égales
	 *
	 * @param o Le joueur auquel on compare
	 * @return true si les deux instances sont égales, false sinon
	 */
	public boolean equals(Object o)
	{
		if (!(o instanceof Joueur))
		{
			return false;
		}
		Joueur j = (Joueur) o;
		if(!nom.equals(j.getNom()) || 
		poids != (j.getPoids()) || 
		poidsMax != (j.getPoidsMax()) ||
		nbrObjets != (j.getNbrObjets()) || 
		!listePiece.equals(j.getTableauPiece()) ||
		!listeObjet.equals(j.getTableauObjet()))
		{
			return false;
		}
		return true;
	}

	/**
	 *  Affiche la liste des pièces visitées par le joueur
	 *
	 */
	public void afficherListePiece()
	{
		int i;
		Piece p = new Piece("p", 0);
		ArrayList <Piece>test = (ArrayList <Piece>)listePiece.clone();
		for (i=0; i<getNbrPieces(); i++)
		{
			p = test.get(i);
			System.out.println(p.descriptionCourte());
		}
		if (getNbrPieces() == 0)
		{
			System.out.println("Il n'y rien ici");
		}
	}

	/**
	 *  Enlève la dernière pièce visitée par le joueur
	 *  de la liste des pièces visitées
	 *
	 * @return false si le joueur n'a visité que la pièce
	 *  de départ, true sinon
	 */
	public boolean removeLastPiece()
	{
		if (nbrPieces > 1)
		{
			listePiece.remove(nbrPieces-1);
			nbrPieces--;
			return true;
		}
		return false;
	}

	/**
	 *  Cherche si le joueur possède un ObjetZork nommé objet
	 *
	 * @param objet L'objet que l'on cherche
	 * @return un ObjetZork nommé objet de l'inventaire du joueur ou un ObjetZork
	 *  nommé "erreur" si ce n'est pas possible
	 */
	public ObjetZork chercher( String objet )
	{
		int i;
		for(i = 0; i < nbrObjets; i++)
		{
			if (((listeObjet.get(i)).getDescription()).equals(objet))
			{
				return listeObjet.get(i);
			}
		}
		System.out.println("Vous n'avez pas cet objet");
		return new ObjetZork( "erreur");
	}
}
