import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece {
    private String description;
    
    // mémorise les sorties de cette piece.
    private Map <String, Piece> sorties;

    private ArrayList <ObjetZork> listeObjet;

    private int nbrObjet; // nombres d'objets max de la piece

    private int capacite; // capacite du poids total de la piece
    

    /**
     *  Initialise une piece décrite par la chaine de caractères spécifiée.
     *  Initialement, cette piece ne possède aucune sortie. La description fournie
     *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
     *
     * @param  description  Description de la piece.
     */
    public Piece(String description) {
	this.description = description;
	sorties = new HashMap();
    }

    public Piece(String description, ArrayList <ObjetZork> listeObjet, int nbrObjet)
    {
	this.description = description;
	this.nbrObjet = nbrObjet;
	this.listeObjet =  (ArrayList <ObjetZork>) listeObjet.clone();
    }

    /**
     *  Définie les sorties de cette piece. A chaque direction correspond ou bien
     *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
     *  cette direction.
     *
     * @param  nord   La sortie nord
     * @param  est    La sortie est
     * @param  sud    La sortie sud
     * @param  ouest  La sortie ouest
     */
    public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
	if (nord != null) {
	    sorties.put("nord", nord);
	}
	if (est != null) {
	    sorties.put("est", est);
	}
	if (sud != null) {
	    sorties.put("sud", sud);
	}
	if (ouest != null) {
	    sorties.put("ouest", ouest);
	}
    }


    /**
     *  Renvoie la description de cette piece (i.e. la description spécifiée lors
     *  de la création de cette instance).
     *
     * @return    Description de cette piece
     */
    public String descriptionCourte() {
	return description;
    }


    /**
     *  Renvoie une description de cette piece mentionant ses sorties et
     *  directement formatée pour affichage, de la forme: <pre>
     *  Vous etes dans la bibliothèque.
     *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
     *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
     *  cette piece.
     *
     * @return    Description affichable de cette piece
     */
    public String descriptionLongue() {
	return "Vous etes dans " + description + ".\n" + descriptionSorties();
    }


    /**
     *  Renvoie une description des sorties de cette piece, de la forme: <pre>
     *  Sorties: nord ouest</pre> Cette description est utilisée dans la
     *  description longue d'une piece.
     *
     * @return    Une description des sorties de cette pièce.
     */
    public String descriptionSorties() {
	String resulString = "Sorties:";
	Set keys = sorties.keySet();
	for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
	    resulString += " " + iter.next();
	}
	return resulString;
    }


    /**
     *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
     *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
     *  renvoie null.
     *
     * @param  direction  La direction dans laquelle on souhaite se déplacer
     * @return            Piece atteinte lorsque l'on se déplace dans la direction
     *      spécifiée ou null.
     */
	public Piece pieceSuivante(String direction) {
	return (Piece) sorties.get(direction);
    }

    /**
     *
     * Renvoie le nombre d'exemplaire de la pièce dans listeObjet donn&egrave;
     * en argument
     *
     *
     * @param obj L'objet qu'on cherche &agrave; trouver le nombre d'exemplaire dans listeObjet
     * @return retourne le nombre d'exemplaire
     *
     */
	public int contientCombienDe(ObjetZork obj)
    {
	int nombreExp = 0;
	for(int i=0; i<listeObjet.size();i++)
	    {
		if((listeObjet.get(i)).equals(obj))
		    {
			nombreExp ++;
		    }
	    }
	return nombreExp;
    }

    /**
     * Verifie si le nombre d'objet du tableau depasse sa capacite.
     * Ajoute l'objet et incremente le nombre d'objet.
     *
     *
     *
     *
     *
     * @param o objet &agrave; ajouter dans le tableau
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
    public boolean retirer(ObjetZork o)
    {
	boolean suppression = false;
	for(int i = 0; i<listeObjet.length; i++)
	    {
		if(listeObjet[i].equals(o))
		    {
			for(int j=i+1; j<listeObjet.length; j++)
			    {
				if(j < listeObjet.length)
				    {
					listeObjet[j+1] = listeObjet[j];
				    }
			    }
		    }
		suppression = true;
		nbrObjet --;
	    }
	return suppression;
    }
    */


    /**
     *
     * supprime une instance d'ObjetZork equals &agrave; l'objet en parametre de la piece.
     * retourne true si l'objet &agrave; &egrave;t&egrave; supprim&egrave;
     * sinon retourne false
     *
     * @param o ObjetZork qu'on d&egrave;cide de supprimer 
     * @return true si l'objet supprim&egrave; sinon false
     *
     */
	public boolean retirer(ObjetZork o)
    {
	for(int i=0;i<listeObjet.size();i++)
	    {
		if((listeObjet.get(i)).equals(o))
		    {
			if((nbrObjet-1)!=i)
			    {
				//listeObjet.get(i) = listeObjet.get((listeObjet.size()-1));
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

    /**
     *
     * renvoie le nombre d'emplaire de l'ObjetZork dans la liste
     *
     *
     *
     *
     *
     * @param o l'ObjetZork qu' 
     *
     *
     */

	public boolean contient(ObjetZork o){
	if(contientCombienDe(o)>0)
	    {
		return true;
	    }
	return false ;
	}

	public  ArrayList <ObjetZork> getTableauObjet()
	{
		ArrayList <ObjetZork> TableauObjet = new ArrayList() ;
		TableauObjet = (ArrayList <ObjetZork>) listeObjet.clone();
		return TableauObjet;
	}

		
}
