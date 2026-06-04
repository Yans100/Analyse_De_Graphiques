
												/************************************************
												 * 				Devoir 1 PIF1005-00				*
												 * 		Par : Yannick Poirier, POIY04109403		*
												 * 				Pour : Ismail Biskri			*
												 ************************************************/

package src;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
	    // Création d'un scanner pour lire les données saisies par l'utilisateur
	    Scanner scanner = new Scanner(System.in);

	    // Demande à l'utilisateur d'entrer le nombre de sommets, puis le scanner récupere l'information
	    System.out.println("Inscrire le nombre de sommets : ");
	    int sommets = scanner.nextInt();

	    // Demande à l'utilisateur d'entrer le nombre d'arcs, puis le scanner récupere l'information
	    System.out.println("Inscrire le nombre d'arcs qui relie les sommets : ");
	    int arcs = scanner.nextInt();
	    
	    // Création d'un objet Graphe.
	    Graphe graph = new Graphe(sommets, arcs);

	    // Vérifie si le graphe est eulérien ou non et affiche à la console le résultat en conséquence
	    if (graph.estEulerienouNon()) {
	        System.out.println("****************************************************************************************");
	        System.out.println("\nCe present graphe a un cycle eulerien");
	    } else {
	        System.out.println("****************************************************************************************");
	        System.out.println("\nCe present graphe n'a pas de cycle eulerien");
	    }

	    // Vérifie si le graphe a une chaîne eulérienne ou non et affiche à la console le résultat en conséquence
	    if (graph.aChaineEulerienneouNon()) {
	        System.out.println("Ce present graphe a une chaine eulerienne");
	    } else {
	        System.out.println("Ce present graphe n'a pas de chaine eulerienne");
	    }

	    // Vérifie si le graphe est planaire ou non et affiche à la console le résultat en conséquence
	    if (graph.estPlanaireouNon()) {
	        System.out.println("Ce present graphe est planaire");
	    } else {
	        System.out.println("Ce present graphe n'est pas planaire");
	    }
	    
	    // Affiche à la console le nombre total de sommets et le nombre total d'arcs du graphe
	    System.out.println("Nombre total de sommets : " + sommets);
	    System.out.println("Nombre total d'arcs : " + arcs);

	    // Appelle de la méthode printmatriceAdjacence pour afficher la matrice d'adjacence du graphe à la console
	    graph.printmatriceAdjacence();

	    // Appelle de la méthode printmatriceIncidence pour afficher la matrice d'incidence du graphe à la console
	    graph.printmatriceIncidence();

	    // Ferme le scanner
	    scanner.close();
	}

}