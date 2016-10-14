import java.util.ArrayList;

/*
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
	private ArrayList<ObjetZork> listeObjet;

	/*
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
	}

	/*
	 *  Initialise un joueur avec un nom et une capacite de poids maximale
	 *
	 * @param nom  Le nom du joueur.
	 * @param poidsMax  Le poids maximum portable par le joueur.
	 */
	public Joueur( String nom, int poidsMax )
	{
		this.nom = nom;
		this.poidsMax = poidsMax;
	}

	/*
	 *  Retourne un nom.
	 *
	 * @return nom Le nom du joueur.
	 */
	public String getNom()
	{
		return nom;
	}

	/*
	 *  Retourne un poids porte.
	 *
	 * @return poids Le poids porte par le joueur.
	 */	
	public int getPoids()
	{
		return poids;
	}

	/*
	 *  Retourne un poids maximumu.
	 *
	 * @return poidsMax Le poids maximum portable par le joueur
	 */
	public int getPoidsMax()
	{
		return poidsMax;
	}

	/*
	 *  Retourne un nombre d'objet.
	 *
	 * @return nbrObjets Le nombre d'objets du joueur.
	 */
	public int getNbrObjets()
	{
		return nbrObjets;
	}

	/*
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

	public boolean retirer(ObjetZork o)
	{
	for(int i=0; i<listeObjet.size(); i++)
	{
		if((listeObjet.get(i)).equals(o))
		{
			if((nbrObjets-1) != i)
			{
				listeObjet.remove(listeObjet.get(listeObjet.size()-1));
			}
			else
			{
				listeObjet.set(i, null);
			}
			nbrObjets --;
			return true;
		}
	}
	return false;
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
}
