
# Analyse de graphes — PIF1005

Programme Java en console permettant d'analyser les propriétés mathématiques d'un graphe non orienté.

## Fonctionnalités

- Construction d'un graphe via saisie interactive (sommets et arcs)
- Détection de cycle eulérien
- Détection de chaîne eulérienne
- Vérification de la planarité (heuristique K5 / K3,3)
- Affichage des matrices d'adjacence et d'incidence
- Comptage des affectations et itérations

## Technologies

- Java (console)
- Eclipse IDE

## Lancer le projet

```bash
# Compiler
javac src/*.java

# Exécuter
java src.Main
```

Le programme demande ensuite d'entrer le nombre de sommets, d'arcs, puis les paires de sommets reliés par chaque arc.

## Structure

```
src/
  Main.java    — point d'entrée
  Graphe.java  — logique du graphe
```

---

Projet universitaire solo — cours PIF1005, UQTR.
