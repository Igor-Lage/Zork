import java.lang.InternalError;
import java.lang.Object;

/**
 *  
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 *
 * Cette classe permet de creer et manipuler des objets. 
 *
 * @author Paris13
 * @version 1.0
 * @since  September 2016
 *
 */

public class ObjetZork implements Cloneable
{
	private String description;
	private int poids;
	private boolean transportable;

	/**
	 *
	 * initialise un ObjetZork qui n'est pas transportable.
	 *
	 * @param description Nom de l'objet
	 *
	 */
	public ObjetZork(String description)
	{
		this.description = description;
		transportable = false;
	}

	/**
	 *
	 * initialise un ObjetZork qui est transportable
	 * si le poids est invalide, on affiche une erreur.
	 * 
	 * @param description Nom de l'objet
	 * @param poids Le poids de l'objet
	 *
	 */
	public ObjetZork(String description, int poids)
	{
		if( checkPoids(poids) == true)
		{
			this.description = description;
			this.poids = poids;
			transportable = true;
		}
		else
		System.out.println("erreur (poids invalide)");
	}

	/**
	 *
	 * retourne le poids de l'objet ObjetZork
	 *
	 * @return La valeur du poids de l'objet 
	 *
	 */
	public int getPoids()
	{
		return poids;
	}

	/**
	 *
	 * retourne la description de l'objet ObjetZork
	 *
	 * @return La description de l'objet
	 *
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 *
	 * retourne la portabilite de l'objet ObjetZork
	 *
	 * @return true si l'objet est transportable
	 *
	 */
	public boolean getTransportable()
	{
		return transportable;
	}

	/**
	 *
	 * verifie si le poids est positif
	 * 
	 * @param poids le poids d'un objet
	 * @return true si le poids est valide (au dessus de 0)
	 *
	 */
	public boolean checkPoids(int poids)
	{
		if(poids < 0)
		{
			return false;
		}
		else
		return true;
	}



	/**
	 *
	 * retrourne vrai si les deux instances sont de type
	 * "ObjetZork" et qu'elles sont égales
	 *
	 *
	 * @param obj L'Object qu'on veut comparer
	 * @return true si les 2 objets sont égaux sinon false
	 *
	 */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ObjetZork))
		{
			return false;
		}
		else
		{
			ObjetZork objZ = (ObjetZork) obj;
			if(objZ.poids == getPoids() &&
			   objZ.transportable == getTransportable() &&
			   objZ.description.equals(description))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 *  implémentation de la méthode clone pour les ObjetZork
	 *
	 *
	 * @return un clone de l'objet si il s'agit bien d'un ObjetZork
	 *
	 */
	public Object clone() 
	{
		Object o = null;
		try 
		{
			o = super.clone(); /* On récupère l'instance à renvoyer par l'appel de la méthode super.clone()*/
		} 
		catch(CloneNotSupportedException cnse)
		{
			throw new InternalError("...");
		}
		return o;
	}
}
