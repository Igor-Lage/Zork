/**
 * Test de la classe Piece.
 * 
 * <p>Cette classe posséde une méthode <code>boolean testXXX()</code>
 * pour chaque méthode testée de la classe <code>Piece</code> 
 * (i.e. une méthode <code>boolean testRetirer()</code> correspond à la 
 * méthode <code>retirer</code> ...). Chacune de ces méthodes:
 * <ul>
 *      <li>crée et initialise les instances nécessaires aux tests de 
 *      cette méthode (i.e. les données de test).</li>
 *      <li>exécute les actions prévues pour chaque instance</li>
 *      <li>vérifie que les résultats prévus sont bien obtenus, affiche 
 *      un message d'erreur et renvoye la valeur <code>false</code> en 
 *      cas d'erreur, renvoye la valeur <code>true</code> sinon.
 * </ul></p>
 * 
 * @author Marc Champesme 
 * @since 18 février 2007
 * @version 28 février 2010 (passage au codage UTF-8)
 * @version 12 octobre 2014 (ajout de méthodes pour tester le contrat)
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class TestPiece {
    public static void main(String[] args) {
        TestPiece tp = new TestPiece();
        boolean retVal;
        
        // Tests
        retVal = tp.testRetirer();
        
        if (!retVal) {
            System.out.println("Echec test méthode retirer()");
        } else {
            System.out.println("Réussite test méthode retirer()");
        }
        
        int nbErreurs = tp.testNContratRetirer();
        
        if (nbErreurs != 0) {
            System.out.println("Echec test contrat méthode retirer():" + nbErreurs + " violation(s) du contrat.");
        } else {
            System.out.println("Réussite test contrat méthode retirer()");
        }
        
    }
    
    private List<ObjetZork> lOz;
    private Random randomGenerator = new Random();
    
    /**
     * Initialisation d'une instance.
     */
    public TestPiece() {
	lOz = creationObjetZorks(15);
    }

    /**
     * Test de la méthode retirer.
     * 
     * @return <code>false</code> en cas d'erreur, <code>true</code> sinon
     */
    public boolean testRetirer() {
        boolean reussite = true;
        boolean retVal;
        
        // Initialisation données tests
        Piece p1 = new Piece("Chambre", 10);        
        Piece p2 = new Piece("Cuisine", 10);
        Piece p3 = new Piece("Salon", 10);
        ObjetZork oz1 = new ObjetZork("Parchemin", 1);
        ObjetZork oz2 = new ObjetZork("Tapis", 10);
        ObjetZork oz3 = new ObjetZork("Tapis", 10);
        ObjetZork oz4 = new ObjetZork("Tapis", 10);
        ObjetZork oz5 = new ObjetZork("Vase", 5);  
        
        p2.ajouter(oz1);
        p3.ajouter(oz2);        
        p3.ajouter(oz3);
        p3.ajouter(oz4);        
        
        // execution retirer
        retVal = p1.retirer(oz5);
        
        // Tests post exec
        if (retVal) {
            System.out.println(p1 + ":valeur de retour devrait etre false");
            reussite = false;
        }
        if (p1.getNbObjets() != 0) {
            System.out.println(p1 + ":nb objets devrait etre 0");
            reussite = false;
        }
        if (p1.contientCombienDe(oz5) != 0) {
            System.out.println(p1 + ":devrait contenir 0" + oz5);
            reussite = false;
        }
        if (p1.contient(oz5)) {
            System.out.println(p1 + ":ne devrait pas contenir" + oz5);
            reussite = false;
        }

        // execution retirer
        retVal = p2.retirer(oz1);
        
        // Tests post exec
        if (!retVal) {
            System.out.println(p2 + ":valeur de retour devrait etre true");
            reussite = false;
        }
        if (p2.getNbObjets() != 0) {
            System.out.println(p2 + ":nb objets devrait etre 0");
            reussite = false;
        }
        if (p2.contientCombienDe(oz1) != 0) {
            System.out.println(p2 + ":devrait contenir 0" + oz1);
            reussite = false;
        }
        if (p2.contient(oz1)) {
            System.out.println(p2 + ":ne devrait pas contenir" + oz1);
            reussite = false;
        }        
        
        // execution retirer
        retVal = p3.retirer(oz3);
        
        // Tests post exec
        if (!retVal) {
            System.out.println(p3 + ":valeur de retour devrait etre true");
            reussite = false;
        }
        if (p3.getNbObjets() != 2) {
            System.out.println(p2 + ":nb objets devrait etre 2");
            reussite = false;
        }
        if (p3.contientCombienDe(oz3) != 2) {
            System.out.println(p3 + ":devrait contenir 2" + oz3);
            reussite = false;
        }
        if (!p3.contient(oz3)) {
            System.out.println(p3 + ":devrait contenir" + oz3);
            reussite = false;
        }                
        return reussite;
    }
    
    public List<ObjetZork> creationObjetZorks(int nbObj) {
	List<ObjetZork> resultat = new ArrayList<ObjetZork>(nbObj);
	int nbDistinctObj = nbObj / 2;
	
	for (int i = 0; i < nbDistinctObj; i++) {
		resultat.add(new ObjetZork("objet" + i, 10 + i));
	}
	
	for (int i = nbDistinctObj; i < nbObj; i++) {
		resultat.add(resultat.get(i - nbDistinctObj));
	}

	return resultat;
    }
    
    public List<Piece> creationPieces(int nbPieces) {
	List<Piece> lPieces = new ArrayList<Piece>(nbPieces);
	Collections.shuffle(lOz);
	for (int i = 0; i < nbPieces; i++) {
		Piece p = new Piece("piece" + i, 10);
		int nbOz = randomGenerator.nextInt(lOz.size());
		// System.out.println("Ajout de " + nbOz + " objets à la pièce: " + p);
		for (int j = 0; j < nbOz; j++) {
			p.ajouter(lOz.get(j));
		}
		lPieces.add(p);
	}
	return lPieces;
    }
    
    /**
     * Teste si le contrat de la méthode retirer est respecté.
     */
    public int testNContratRetirer() {
	int nbEchecs = 0;
	List<Piece> lp = creationPieces(100);
	Collections.shuffle(lOz);
	int indexOz = 0;
	System.out.print("P.retirer() [" + lp.size() + "]:");
	for (Piece p : lp) {
		ObjetZork oz = lOz.get(indexOz);
		
		int retVal = testContratRetirer(p, oz);
		switch(retVal) {
			case 0: System.out.print("."); // Non testé
				break;
			case 1: System.out.print("+"); // Succès
				break;
			default: nbEchecs++; // Violation contrat
		}
		
		indexOz = (indexOz + 1) % lOz.size();
	}
	System.out.println("/");
	return nbEchecs;
    }
    
	public int testContratRetirer(Piece p, ObjetZork oz) {
		if (!testPreRetirer(p, oz)) {
			return 0;
		}
		
		if (!testInvariantPiece(p)) {
			System.out.println("Piece.retirer(): Violation invariant avant execution pour Piece: " + p);
			return 2;
		}
		
		int oldNbObjets = p.getNbObjets();
		boolean oldContient = p.contient(oz); 
		int oldNboz = p.contientCombienDe(oz);
		
		boolean retVal = p.retirer(oz);
		if (retVal) {
			System.out.print("R");
		}
		
		
		if (!testInvariantPiece(p)) {
			System.out.println("Piece.retirer(): Violation invariant après execution pour Piece: " + p);
			return 2;
		}
		
		if (!testPostRetirer(p, oz, retVal, oldNbObjets, oldContient, oldNboz)) {
			return 3;
		} 
		return 1;
	}
    
    public boolean testPreRetirer(Piece p, ObjetZork oz) {
	return oz != null;
    }
    
    public boolean testInvariantPiece(Piece p) {
	return (p.descriptionCourte() != null) && (p.getNbObjets() >= 0);
    }
    
    public boolean testPostRetirer(Piece p, ObjetZork oz, boolean retVal, int oldNbObjets, boolean oldContient, int oldNboz) {
	if (!(retVal == (p.getNbObjets() == (oldNbObjets -1)))) {
		return false;
	}
	
	if (!(!retVal ==  (p.getNbObjets() == oldNbObjets))) {
		return false;
	}
	
	if (!(retVal == oldContient)) {
		return false;
	}

	if (!(oldContient == (p.contientCombienDe(oz) == (oldNboz - 1)))) {
		return false;
	}

	if (!(!oldContient == (p.contientCombienDe(oz) == oldNboz))) {
		return false;
	}

	if (oldNboz == 1) {
		if (p.contient(oz)) {
			return false;
		}
	}

	if (!((oldNboz > 1) == p.contient(oz))) {
		return false;
	}
	return true;
    }
}

