Tp TextMining
=============

# Prérequis pour lancer le TP:

* Avoir javac installé
* Avoir java installé
* Posséder le JDK ou JRE de java 8 (ou plus)

# Pour lancer le programme:

1. Aller dans le dossier
2. faire make dans un terminal
3. l'executable se lance

Make propose les options suivantes:

* all (par défaut): compile et lance le programme
* compile: compile le programme dans un dossier build
* run: exécute le programme
* clean: supprime les binaires (dossier build)


# Utiliser le TP:

* Pour indiquer un chemin, il est déconseillé d'utiliser ~.
* Une fois la recherche lancée, on peut:
		* avoir une aide avec __--help--__
		* relancer l'initialisation du programme avec __--reinit--__
		* charger un index avec __--load_index--__
		* quitter le programme avec __--exit--__

Il y a cependant certains problème après le help, la lecture sur l'entrée standard n'étant pas parfaite.
Il est parfois nécessaire de faire une ou deux inputs (inutiles) en plus. A corriger. Pas eu le temps :) .
On peut effectuer des recherches tant que le programme n'a pas été explicitement quitté (__--exit--__).