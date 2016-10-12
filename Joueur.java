import java.util.ArrayList;

public class Joueur
{
	private String nom;
	private int poids;
	private int poidsMax;
	private int nbrObjets;
	private int score;
	private ArrayList<ObjetZork> listeObjet;

	public Joueur( String nom, int poids, int poidsMax, int nbrObjets, int score, ArrayList<ObjetZork> listeObjet )
	{
		this.nom = nom;
		this.poids = poids;
		this.poidsMax = poidsMax;
		this.nbrObjets = nbrObjets;
		this.score = score;
		this.listeObjet = listeObjet;
	}

	public Joueur( String nom, int poidsMax )
	{
		this.nom = nom;
		this.poidsMax = poidsMax;
	}

	public String getNom()
	{
		return nom;
	}
	
	public int getPoids()
	{
		return poids;
	}
	
	public int getPoidsMax()
	{
		return poidsMax;
	}

	public int getScore()
	{
		return score;
	}

	public int getNbrObjets()
	{
		return nbrObjets;
	}

	public  ArrayList <ObjetZork> getTableauObjet()
	{
		ArrayList <ObjetZork> TableauObjet = new ArrayList() ;
		TableauObjet = (ArrayList <ObjetZork>) listeObjet.clone();
		return TableauObjet;
	}

	public boolean retirer(ObjetZork o)
    {
	for(int i=0;i<listeObjet.size();i++)
	    {
		if((listeObjet.get(i)).equals(o))
		    {
			if((nbrObjets-1)!=i)
			    {
				//listeObjet.get(i) = listeObjet.get((listeObjet.size()-1));
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
	
	public boolean contient(ObjetZork o){
	if(contientCombienDe(o)>0)
	    {
		return true;
	    }
	return false ;
	}

	public boolean equals( Object o )
	{
		if (!o.instanceOf(Joueur))
		{
			return false;
		}
		Joueur j = (joueur) o;
		if(!nom.equals(getNom(j)) || !poids.equals(getPoids(j)) || !poidsMax.equals(getPoidsMax(j)) ||
		!nbrObjets.equals(getNbrObjets(j)) ||!score.equals(getScore(j)) || !listeObjets.equals(getTableauObjet(j)))
		{
			return false;
		}
		return true;
	}
}
