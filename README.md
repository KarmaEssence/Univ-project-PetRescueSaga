# Projet universitaire : PetRescueSaga

Ce document contient toutes les informations nécessaires au bon fonctionnement du projet.

## Fonctionnalitées présentent :

- Les mécaniques de base du jeu original.
- Les obstacles et bloc du décors.
- Un joueur robot.
- Une interface textuel.  
- Une interface graphique. 
- Un système de sauvegarde des utilisateurs et des niveaux.
- Une option d'analyse d'un niveau.

## Usage :
### Récupérer le projet :
1. Cloner le répertoire :
   


    git@github.com:KarmaEssence/Univ-project-PetRescueSaga.git

ou

    git@github.com:KarmaEssence/Univ-project-PetRescueSaga.git

2. Entrez dans le répertoire :
   
   
    cd Univ-project-PetRescueSaga


### Exécuter le projet :
- 1ère possibilité :
  
Compilation :
  Après avoir décompressé le projet dans l’emplacement de votre choix, 
  ouvrez votre terminal et rendez-vous dans le dossier du projet qui est 
  "PetRescueSaga" et placez-vous dans le dossier "src". 
  Entrez la commande suivante pour compiler le projet :
  
  
    javac com/launcher/PetRescueSaga.java

Exécution :
Après la compilation, toujours dans le dossier src, entrez la commande suivante 
pour exécuter le projet :

    java com.launcher.PetRescueSaga

- 2de possibilité :
  
Pour cela vous devez posséder un jdk 11.0.4 ou plus, ensuite allez 
  dans le dossier du projet "PetRescueSaga", dans celui-ci 
  vous devriez trouver un fichier "PetRescueSaga.jar", pour l'exécuter
  entrez la commande suivante :
  

    java -jar PetRescueSaga.jar
  
## Utilisation du projet :
La première chose que vous verrez sera une question dans le terminal vous 
demandant de choisir entre la vue textuel ou graphique, entrez celle désirée.
Peu importe le choix de la vue la seule différence au niveau de l’utilisation 
sera que pour l’interface graphique il vous suffira de cliquer sur des boutons 
alors que pour l’interface textuelle vous devrez écrire vos choix selon la 
syntaxe qui vous sera indiqué dans le terminal.

Tout d’abord vous arriverez sur le menu principal où vous pouvez choisir entre 
quitter le jeu ou y jouer. 
Si vous décider de jouer vous vous rendrez sur la page de sélection de
sauvegarde où vous pourrez choisir, créer ou supprimer une sauvegarde :
Lors de la création d’une sauvegarde il faudra lui attribuer un joueur humain ou robot, le robot jouera 
tout seul. Après avoir choisi une sauvegarde vous pourrez choisir un niveau et y jouer. 
Vous avez aussi une option disponible qui permet de vous laisser revenir en arrière si vous en avez envie.
Après avoir fini un niveau vous pourrez choisir d’arrêter ou de continuer selon vos envies. 
Vos résultats son sauvegardé dans l’emplacement de sauvegarde courant.

Pour jouer vous devez détruire des groupes de bloc, pour cela il faut cliquer sur un bloc de couleur,
voici les couleurs à connaitre :

- Pour l'interface textuelle :

  R: Bloc Rouge
  
  B: Bloc Bleu
  
  Y: Bloc Jaune
  
  G: Bloc Vert
  
  F: Bloc Fixe(Obstacle)
  
  A: Bloc Abstrait(bloc du décors)

  c: Chat
  
  d: Chien


- Pour l'interface graphique :
Couleurs que ne vous ne connaissez pas : 

## Collaborateur :

- KarmaEssence
- Holykanguru

## Copyright :

Les images de ce projet sont soumises à un droit de copyright par King et Rey Chan,
nous n'utilisons que celle-ci dans le cadre de l'apprentissage universitaire.

Dans le dossier ImageContent :

- Logo.png
- Main.png
- Summarize.png

Source de nos images:

- https://www.behance.net/illustraytion



