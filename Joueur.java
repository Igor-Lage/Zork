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
	 *  Initialise un joueur avec tout les arguments possibles.
	 *
	 * @param nom  Le nom du joueur.
	 * @param poids  Le poids des objets portes par le joueur.
	 * @param poidsMax  Le poids maximum portable par le joueur.
	 * @param nbrObjets  Le nombre d'objets du joueur
	 * @param listeObjet  La liste d'ObjetZork portes par le joueur.
	 */
	public Joueur( String nom, int poids, int poidsMax, int nbrObjets, ArrayList<ObjetZork> listeObjet )
	{
		this.nom = nom;
		this.poids = poids;
		this.poidsMax = poidsMax;
		this.nbrObjets = nbrObjets;
		this.listeObjet = (ArrayList <ObjetZork>) listeObjet.clone();
		listePiece = new ArrayList <Piece>();
	}

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
	 *  Retourne un poids porte.
	 *
	 * @return poids Le poids porte par le joueur.
	 */	
	public int getPoids()
	{
		return poids;
	}

	/**
	 *  Retourne un poids maximumu.
	 *
	 * @return poidsMax Le poids maximum portable par le joueur
	 */
	public int getPoidsMax()
	{
		return poidsMax;
	}

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
			System.out.println("Il n'y rien ici");
		}
	}

	public  ArrayList <Piece> getTableauPiece()
	{
		ArrayList <Piece> TableauPiece = new ArrayList() ;
		TableauPiece = (ArrayList <Piece>) listePiece.clone();
		return TableauPiece;
	}

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

	public void addPiece(Piece p)
	{
		listePiece.add(p);
		nbrPieces ++;
	}

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
	
	public boolean contient(ObjetZork o)
	{
		if(contientCombienDe(o)>0)
		{
			return true;
	    	}
		return false ;
	}

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
		!listeObjet.equals(j.getTableauObjet()))
		{
			return false;
		}
		return true;
	}

	public Piece pieceCourante()
	{
		return listePiece.get(nbrPieces-1);
	}

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
