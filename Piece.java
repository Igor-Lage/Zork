import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/*
 *
 *  Une piece dans un jeu d'aventure.
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.
 *
 *  Une "Piece" represente un des lieux dans lesquels se deroule l'action du
 *  jeu. Elle est reliee a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont etiquettees "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possede une reference sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version1.1
 * @since  August 2000
 */

public class Piece {

	private String description;
	private Map <String, Piece> sorties; // memorise les sorties de cette piece.
	private ArrayList <ObjetZork> listeObjet; // la liste des ObjetZork presents dans la piece
	private int nbrObjet; // nombres d'objets de la piece
	private int capacite; // capacite du poids total de la piece


	/*
	 *  Initialise une piece decrite par la chaine de caracteres specifiee.
	 *  Initialement, cette piece ne possede aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliotheque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) 
	{
		this.description = description;
		sorties = new HashMap();
	}

	/*
	 *  Initialise une piece decrite par la chaine de caracteres specifiee,
	 *  contenant nbrObjet ObjetZork contenus dans listeObjet.
	 *  Initialement, cette piece ne possede aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliotheque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 * @param  listeObjet  La liste des objets de la piece.
	 * @param  nbrObjet  Le nombre d'objets dans la piece.
	 */
	public Piece(String description, ArrayList <ObjetZork> listeObjet, int nbrObjet)
	{
		this.description = description;
		this.nbrObjet = nbrObjet;
		this.listeObjet =  (ArrayList <ObjetZork>) listeObjet.clone();
		sorties = new HashMap();
	}

	/*
	 *  Defini les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord  La sortie nord.
	 * @param  est  La sortie est.
	 * @param  sud  La sortie sud.
	 * @param  ouest  La sortie ouest.
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) 
	{
		if (nord != null) 
		{
		sorties.put("nord", nord);
		}

		if (est != null) 
		{
		sorties.put("est", est);
		}

		if (sud != null) 
		{
		sorties.put("sud", sud);
		}

		if (ouest != null) 
		{
		sorties.put("ouest", ouest);
		}
	}


	/*
	 *  Renvoie la description de cette piece (i.e. la description specifiee lors
	 *  de la creation de cette instance).
	 *
	 * @return  Description de cette piece.
	 */
	public String descriptionCourte() 
	{
		return description;
	}


	/*
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatee pour affichage, de la forme:
	 *  Vous etes dans la bibliotheque.
	 *  Sorties: nord ouest 
	 *  Cette description utilise la chaine de caracteres
	 *  renvoyee par la methode descriptionSorties pour decrire les sorties de
	 *  cette piece.
	 *
	 * @return  Description affichable de cette piece
	 */
	public String descriptionLongue() 
	{
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/*
	 *  Renvoie une description des sorties de cette piece, de la forme:
	 *  Sorties: nord ouest Cette description est utilisee dans la
	 *  description longue d'une piece.
	 *
	 *  @return Une description des sorties de cette piece.
	 */
	public String descriptionSorties() 
	{
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) 
		{
		resulString += " " + iter.next();
		}
		return resulString;
	}


	/*
	 *  Renvoie la piece atteinte lorsque l'on se deplace a partir de cette piece
	 *  dans la direction specifiee. Si cette piece ne possede aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se deplacer
	 * @return  Piece atteinte lorsque l'on se deplace dans la direction
	 *  specifiee ou null.
	 */
	public Piece pieceSuivante(String direction) 
	{
			return (Piece) sorties.get(direction);
	}

	/*
	 *  Renvoie le nombre d'occurences de l'objet dans listeObjet donne
	 *  en argument
	 *
	 * @param obj L'objet dont on cherche a trouver le nombre d'occurences dans listeObjet
	 * @return retourne le nombre d'occurences
	 *
	 */
	public int contientCombienDe(ObjetZork obj)
	{
		int nombreOcc = 0;
		for(int i=0; i<listeObjet.size();i++)
		{
			if((listeObjet.get(i)).equals(obj))
			{
				nombreOcc ++;
			}
		}
		return nombreOcc;
	}

	/*
	 *  Verifie si le nombre d'objet du tableau depasse sa capacite.
	 *  Ajoute l'objet et incremente le nombre d'objet.
	 *
	 * @param o objet a ajouter dans le tableau
	 */
	public void ajouter(ObjetZork o)
	{
		if(nbrObjet < capacite)
		{
			listeObjet.add(o);
			nbrObjet ++;
		}
	}

	/*
	 *  Supprime une instance d'ObjetZork equals a l'objet en parametre de la piece.
	 *  Retourne true si l'objet a ete supprime
	 *  sinon retourne false
	 *
	 * @param o ObjetZork qu'on decide de supprimer 
	 * @return true si l'objet a ete supprime sinon false
	 *
	 */
	public boolean retirer(ObjetZork o)
	{
		for(int i=0;i<listeObjet.size();i++)
		{
			if((listeObjet.get(i)).equals(o))
			{
				if((nbrObjet-1) != i)
				{
					listeObjet.remove(listeObjet.get(listeObjet.size()-1));
				}
				else
				{
					listeObjet.set(i, null);
				}
				nbrObjet --;
				return true;
			}
		}
		return false;
	}

	/*
	 *  Cherche si un ObjetZork specifie est dans une listeObjet.
	 *  Renvoie true si il y est, renvoie false sinon.
	 *
	 * @param o l'ObjetZork qu'on cherche
	 */
	public boolean contient(ObjetZork o){
	if(contientCombienDe(o)>0)
	{
		return true;
	}
	return false ;
	}

	/*
	 *  Retourne la liste d'ObjetZork d'une piece
	 *
	 * @return La listeObjet de la piece
	 */
	public  ArrayList <ObjetZork> getTableauObjet()
	{
		ArrayList <ObjetZork> TableauObjet = new ArrayList() ;
		TableauObjet = (ArrayList <ObjetZork>) listeObjet.clone();
		return TableauObjet;
	}	
}
