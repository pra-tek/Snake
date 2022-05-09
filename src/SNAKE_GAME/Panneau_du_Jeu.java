package SNAKE_GAME;

import javax.swing.*;
import java.awt.*;
/*
 * Contient toutes les classes nécessaires à la création d'une interface utilisateur,
 *  de peinture gragique et des images
 */
import java.awt.event.*;
/*
 * fournit des interfaces et des classes pour traiter avec différents types d'évenements tirés par des composants AWT
 */
import java.util.Random;
/*
 * Une instance de cette classe est utilisée pour générer un flux de nombres pseudo-aléatoire
 */

public class Panneau_du_Jeu extends JPanel implements ActionListener {
    static int LARGEUR_ECRAN = 650;
    static final int HAUTEUR_ECRAN= 650;
    static final int TAILLE_UNITE = 25;
    static final int UNITE_JEU = (LARGEUR_ECRAN*HAUTEUR_ECRAN)/TAILLE_UNITE;
    static final int RETARD = 100;
    final int[] x = new int[UNITE_JEU];
    final int[] y = new int[UNITE_JEU];
    int bodyParts = 7;
    int pomme_manger;
    int pommeX;
    int pommeY;
    char direction = 'R';
    boolean fonctionnement = false;
    Timer timer;
    Random random;
    Panneau_du_Jeu(){
        random = new Random();
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN,HAUTEUR_ECRAN));
        /*
         * Définit la taille préférée du composant
         */
        this.setBackground(Color.black);
        this.setFocusable(true);
        /*
         *  Définit l'état sensible du composant à la valeur spécifiée
         *  Cette valeur remplace la focalisation par défaut du composant
         */
        this.addKeyListener(new MonAdaptateurdeTouche());
        /*
         * Ajoute l'auditeur de clé spécifié à recevoir des clés du composant
         * Si elle est nulle, aucune exception n'est lancée et aucune action n'est effectuée
         */
        Demarer();
    }


    public void Demarer(){
        NouvelPomme();
        fonctionnement = true;
        timer = new Timer(RETARD, this);
        /*
         * Crée une minuterie
         * Initialise le delay initial et  entre deux évènements en milisecondes
         */
        timer.start();
        /*
         * Demare la miniterie, lui permettant ainsi d'envoyer des évènements d'action à ses auditeurs
         */
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /*
         * Appelle la methode de la penture du  délégué, si le délégué de  I.U est non nul
         *  Ils passent la copie du déléguéà l'objet graphique pour protéger le reste des modification irrévocables
         *  Si vous remplissez cela dans une sous-classe, vous ne pourrez pas effectuer des modifications permanentes des graphiques passés
         */
        Dessiner(g);
    }


    public void Dessiner(Graphics g){
        if (fonctionnement) {
           /*for (int i = 0; i < HAUTEUR_ECRAN/TAILLE_UNITE; i++) {
               *g.drawLine(i*TAILLE_UNITE, 0, i*TAILLE_UNITE, HAUTEUR_ECRAN);
               *g.drawLine(0, i*TAILLE_UNITE, LARGEUR_ECRAN, i*TAILLE_UNITE);
           }*/
            g.setColor(Color.yellow);
            g.fillOval(pommeX, pommeY, TAILLE_UNITE, TAILLE_UNITE);
            /*
             * Remplit un oval délimité par un rectangle spécifié avec la couleur actuelle
             */
            for (int i = 0; i < bodyParts; i ++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], TAILLE_UNITE, TAILLE_UNITE);
                }
                else {
                    //g.setColor(new Color(60,190,60));
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], TAILLE_UNITE,TAILLE_UNITE);
                    /*
                     * Remplit le rectangle spécifié
                     */
                }
            }
            // code permettant d'afficher le score pendant la partie
           /*g.setColor(Color.blue);
           g.setFont(new Font("Ink Free", Font.BOLD,40));
           FontMetrics metrics = getFontMetrics(g.getFont());
                    /*
                    * La classe FontMetrics définit des objets de métrique de polices,
                    * qui encapsules des informations sur le rendu d'une police particulière sur un écran particuier
                     */
           /*g.drawString("Score: "+pomme_manger, (LARGEUR_ECRAN = metrics.stringWidth("Score: " +pomme_manger))/4,g.getFont().getSize());
                    /*
                    * Dessine le texte indiqué par la chaîne spécifiée,
                    * en utilisant le police et la couleur actuelles de ce contexte graphique
                     */
        }
        else {
            GameOver(g);
        }
    }


    public void NouvelPomme(){
        pommeX = random.nextInt(LARGEUR_ECRAN/TAILLE_UNITE)*TAILLE_UNITE;
        /*
         * Retourne un pseudo-aléatoire,
         * uniformément distribué entre 0 et une valeur spécifiée,
         * dessine avec cette séquence de générateur de nombres aléatoire
         */
        pommeY = random.nextInt(HAUTEUR_ECRAN/TAILLE_UNITE)*TAILLE_UNITE;
    }


    public void Deplacer(){
        for (int i = bodyParts; i > 0; i --) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - TAILLE_UNITE;
            case 'D' -> y[0] = y[0] + TAILLE_UNITE;
            case 'L' -> x[0] = x[0] - TAILLE_UNITE;
            case 'R' -> x[0] = x[0] + TAILLE_UNITE;
        }
    }


    public void VerifierlaPomme(){
        if ((x[0] == pommeX) && (y[0] == pommeY)){
            bodyParts ++;
            pomme_manger ++;
            NouvelPomme();
        }
    }


    public void Collision(){
        for (int i = bodyParts; i > 0; i --) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                fonctionnement = false;
                break;
            }
        }
        // Vérifier s'il touche la bordure gauche
        if (x[0] < 0) {
            fonctionnement = false;
        }
        // Vérifier s'il touche la bordure droite
        if (x[0] > LARGEUR_ECRAN) {
            fonctionnement = false;
        }
        // Vérifier s'il touche la bordure de haut
        if (y[0] < 0) {
            fonctionnement = false;
        }
        // Vérifier s'il touche la bordure du bas'
        if (y[0] > HAUTEUR_ECRAN) {
            fonctionnement = false;
        }
        if (!fonctionnement) {
            timer.stop();
            /*
             * Arrête la miniterie, stopant ainsi les envoies d'évènements d'action à ses auditeurs
             */
        }
    }


    public void GameOver(Graphics g){
        //Code permettant d'afficher le score à la fin du jeu
        g.setColor(Color.red);
        g.setFont(new Font("Liberation Serif", Font.BOLD,30));
        FontMetrics metrics0 = getFontMetrics(g.getFont());
        g.drawString("Score: "+pomme_manger, (LARGEUR_ECRAN = metrics0.stringWidth("Score: " +pomme_manger))/4,g.getFont().getSize());

        //Code permettant d'afficher "Gamer Over"
        g.setColor(Color.red);
        g.setFont(new Font("Liberation Serif", Font.BOLD,60));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER !", (LARGEUR_ECRAN = metrics1.stringWidth("GAME OVER !"))/3, HAUTEUR_ECRAN/3);
    }
    @Override


    public void actionPerformed(ActionEvent e) {
        if (fonctionnement) {
            Deplacer();
            VerifierlaPomme();
            Collision();
        }
    }


    public class MonAdaptateurdeTouche extends KeyAdapter{
        @Override

        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            /*
             * Invoquer lorsqu'on une touches
             */
            switch(e.getKeyCode()) {
                /*
                 * Renvoie la clé de code associé à la clé de cet évènement
                 */
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
