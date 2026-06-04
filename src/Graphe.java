												
												/************************************************
												 * 				Devoir 1 PIF1005-00				*
												 * 		Par : Yannick Poirier, POIY04109403		*
												 * 				Pour : Ismail Biskri			*
												 ************************************************/

package src;
import java.util.Scanner;

/************************************************************************************
 * Déclaration des variables et du constructeur utilisées dans le programme  		*
 ***********************************************************************************/
public class Graphe {
    private int[][] matriceAdjacence; //déclaration de la matrice adjacence
    private int[][] matriceIncidence; //déclaration de la matrice incidente
    private int[] degres; //tableau qui va représenter chaque degrés de chaque sommet
    private int sommets; //sommets dans le graphe
    private int arcs; //arcs dans le graphe
    private int nbrIteration = 0; //déclaration du compteur d'itération
    private int nbrAffectation = 0; //déclaration du compteur d'affectation

    public Graphe(int sommets, int arcs) {
        this.sommets = sommets;
        this.arcs = arcs;
        matriceAdjacence = new int[sommets][sommets];
        matriceIncidence = new int[sommets][arcs];
        degres = new int[sommets];

        Scanner scanner = new Scanner(System.in);

/**********************************************************************************
* Cet algorithme permet d'ajouter des arcs au graphe en demandant à l'utilisateur *
* d'y inscrire les sommets reliés par un arc									  *
***********************************************************************************/
        for (int i = 0; i < arcs; i++) {
            System.out.println("Entrer les deux sommets relier par l'arc numero " + (i+1) + " sous le format suivant : (1 2): ");
            int sommet1 = scanner.nextInt();
            int sommet2 = scanner.nextInt();
            matriceAdjacence[sommet1][sommet2] = 1;
            matriceAdjacence[sommet2][sommet1] = 1;
            matriceIncidence[sommet1][i] = 1;
            matriceIncidence[sommet2][i] = 1;
            degres[sommet1]++;
            degres[sommet2]++;
            nbrAffectation += 4; // Initialisation du compteur des affectations dans les matrices
            nbrIteration++; // Initialisation du compteur des itérations de boucle dans les matrices
        }
        scanner.close();
     }
    
/**********************************************************************************
* Cette méthode vérifie si un graphe est eulérien ou non en vérifiant si tout les *
* sommet ont un degré pair								  						  *
***********************************************************************************/   
        public boolean estEulerienouNon() {
            for (int i = 0; i < sommets; i++) {
                if (degres[i] % 2 != 0) {
                    return false; //Retourne faux s'il y a un sommet impair 
                }
            }
/**********************************************************************************
* Cette partie vérifie si un graphe est connexe ou non en parcourant tout les 	  *
* sommet et marque ceux qui ont été visités							  			  *
***********************************************************************************/  
            boolean[] parcours = new boolean[sommets];
            parcoursProfondeur(0, parcours);
            for (int i = 0; i < sommets; i++) {
                if (!parcours[i] && degres[i] > 0) {
                    return false; //Retourne faux si un sommet n'a pas été visité et à un degré supérieur à 0
                }
            }
            return true; //Retourne vrai si les deux conditions ci-haute sont vrai. Le Graphe est alors eulérien
        }
        
/**********************************************************************************
* Cette méthode met à jour le tableau "parcours" selon les données entrées par 	  *
* l'utilisateur. Utilise la matrice d'adjacence pour determiner les sommets qui   *
* n'ont pas encore été visités										  			  *
***********************************************************************************/ 
        private void parcoursProfondeur(int sommet, boolean[] parcours) {
            parcours[sommet] = true;
            for (int i = 0; i < sommets; i++) {
                if (matriceAdjacence[sommet][i] == 1 && !parcours[i]) {
                    parcoursProfondeur(i, parcours);
                }
            }
            }
        
/**********************************************************************************
* Cette méthode vérifie si un graphe possède une chaine eulérienne ou non en 	  *
* vérifiant s'il y a des sommets avec un degré impair. Le compteur est alors      *
* incrémenté. Si celui-ci dépasse 2, il n'y a pas de chaine eulérienne			  *
***********************************************************************************/ 
        public boolean aChaineEulerienneouNon() {
                int nbrSommetsImpairs = 0;
                for (int i = 0; i < sommets; i++) {
                    if (degres[i] % 2 != 0) {
                        nbrSommetsImpairs++;
                    }
                    if (nbrSommetsImpairs > 2) {
                        return false; //Retourne faux s'il y a plus de 2 sommets impairs
                    }
                }
                return true; //Retourne vrai s'il y a moins de 2 sommets impairs. Le graphe possède une chaine eulérienne
    }
        
/**********************************************************************************
* Cette méthode vérifie si le graphe est planaire ou non en parcourt toutes les   *
* paires de sommets du graphe et vérifie s'il existe un troisième sommet qui est  *
* adjacent à l'un des sommets mais pas à l'autre.	  							  *
***********************************************************************************/         
        public boolean estPlanaireouNon() {
            // Cet algorithme Vérifie si le graphe contient un sous-graphe isomorphe à K5 ou K3,3
            for (int i = 0; i < sommets; i++) {
                for (int j = i+1; j < sommets; j++) {
                    if (matriceAdjacence[i][j] == 0) {
                    	
                        // Cet algorithme cherche s'il y a un troisième sommet qui est adjacent à i, mais pas à j
                        for (int k = 0; k < sommets; k++) {
                            if (matriceAdjacence[i][k] == 1 && matriceAdjacence[j][k] == 0) {
                            	
                                // Cet algorithme vérifie si k est adjacent à tous les autres sommets dans le triangle formé de i, j, k
                                boolean K5 = true;
                                for (int l = 0; l < sommets; l++) {
                                    if (l != i && l != j && l != k) {
                                        if (matriceAdjacence[k][l] == 0 || matriceAdjacence[j][l] == 0 || matriceAdjacence[i][l] == 0) {
                                            K5 = false;
                                            break;
                                        }
                                    }
                                }
                                if (K5) {
                                    return false;
                                }
                                
                                // Cet algorithme vérifie si k est adjacent à tous les sommets dans le triangle de i, j, k en utilisant matrice adjacence
                                boolean K33 = true;
                                for (int l = 0; l < sommets; l++) {
                                    if (l != i && l != j && l != k) {
                                        if (matriceAdjacence[k][l] == 1 && matriceAdjacence[j][l] == 1 && matriceAdjacence[i][l] == 0) {
                                            K33 = false;
                                            break;
                                        }
                                        if (matriceAdjacence[k][l] == 1 && matriceAdjacence[i][l] == 1 && matriceAdjacence[j][l] == 0) {
                                            K33 = false;
                                            break;
                                        }
                                    }
                                }
                                if (K33) {
                                    return false; // Retourne faux si le graphe n'est pas planaire 
                                }
                            }
                        }
                    }
                }
            }
            return true; // Retourne vrai si le graphe est planaire 
        }
        
/**********************************************************************************
* Cette méthode permet d'afficher à la console la matrice d'adjacence d'un graphe *
* ainsi que le degré de chaque sommet											  *
***********************************************************************************/
    public void printmatriceAdjacence() {
    	System.out.println("\n****************************************************************************************");
        System.out.println("Voici la matrice d'adjacence : \n");
        for (int i = 0; i < sommets; i++) { // parcours la matrice d'adjacence
            for (int j = 0; j < sommets; j++) {
                System.out.print(matriceAdjacence[i][j] + " ");
                nbrAffectation++; // compte l'affectation dans la boucle
            }
            System.out.print("\tLe degre du sommet numero " + i + " est de " + degres[i]); // Ajout du degré pour chaque sommets
            System.out.println();
            nbrIteration++; // compte l'itération de boucle
        }
        System.out.println("\nNombre total d'affectations dans la matrice d'adjacence : " + nbrAffectation);
        System.out.println("Nombre total d'iterations dans la matrice d'adjacence : " + nbrIteration);
    }

/**********************************************************************************
* Cette méthode permet d'afficher à la console la matrice d'incidence d'un graphe *
* ainsi que le degré de chaque sommet											  *
***********************************************************************************/
    public void printmatriceIncidence() {
    	System.out.println("\n****************************************************************************************");
        System.out.println("Voici la matrice d'incidence : \n");
        for (int i = 0; i < sommets; i++) { // parcours de la matrice d'incidence
            for (int j = 0; j < arcs; j++) {
                System.out.print(matriceIncidence[i][j] + " ");
                nbrAffectation++; // compte l'affectation dans la boucle
            }
            System.out.print("\tLe degre du sommet numero " + i + " est de " + degres[i]); // Ajout du degré pour chaque sommets
            System.out.println();
            nbrIteration++; // compte l'itération de boucle
        }
        System.out.println("\nNombre total d'affectations dans la matrice d'incidence : " + nbrAffectation);
        System.out.println("Nombre total d'iterations dans la matrice d'incidence : " + nbrIteration);
        System.out.println("\n****************************************************************************************");
      
    } 
}
