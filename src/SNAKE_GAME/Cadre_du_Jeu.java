package SNAKE_GAME;

import javax.swing.*;
/*
 * Fournit un ensemble de "léger"  composants qui au maximun de dégrer possible travaille la même chose sur toutes les platformes
 */

public class Cadre_du_Jeu extends JFrame{
    Cadre_du_Jeu(){
        this.add(new Panneau_du_Jeu());
        this.setTitle("SNAKE");
        /*
         * Définit le titre de ce cadre sur la chaine de caractère spécifiée
         */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Permet de définir l'opération qui se produira par défaut lorsque l'utilisateur initialise la "fermeture" sur ce cadre
         * Dans notre cas, quitter l'aplication en utilisant la methode "sortir" du système
         */
        this.setResizable(false);
        /*
         * Permet de spécifier si cette trame est redimensionnée par l'utilisateur ou pas
         */
        this.pack();
        /*
         * Permet à ce qu'une fenêtre soit redimensionnée pour s'adapter à la taille et aux mise à jour de ses  sous-composants
         *  La largeur et la hauteur résultantes de la fenêtre sont automatiquement agrandies si l'une des dimension est inférieur à la taille minimal spécifiée après l'appel de *setMinimunSize()*
         */
        this.setVisible(true);
        /*
         * Affiche ou cache une fenêtre en fonction des valeurs du paramètre "b"
         *  Si la methode indisue une fenêtre alors celle ci est focalisée suivant  des conditions
         */
        this.setLocationRelativeTo(null);
        /*
         * Défint la localisation de la fenêtre par rapport aux composants spécifiés selon plusieurs scénarios
         */
    }
}
