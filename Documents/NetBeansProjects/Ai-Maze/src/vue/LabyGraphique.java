/*  Created by riccardild on Apr 5, 2018 4:59:30 PM
 *  (C) Copyright Gianni Riccardi.
 */
package vue;

import javax.swing.*;
import java.awt.*;
import modele.*;

/**
 *
 * @author riccardild
 */
public class LabyGraphique extends JFrame {
    private final JPanel pan ; // panneau
    private final JPanel lab;
    private final JPanel menu; // gère les choix
    private final MatriceLaby boutonsLaby;
    public JButton setDFS = new JButton("DFS");
    public JButton setRandom = new JButton("Random");
    public JButton setExit = new JButton("Exit");
    
    public LabyGraphique (Labyrinthe laby){ // constructeur        
        pan = new JPanel(); // instancier le panneau 
        menu = new JPanel(); // menu
        lab = new JPanel(); // labyrinthe
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        pan.add(lab);
        pan.add(menu);
        boutonsLaby = new MatriceLaby(laby);
    }
    
    public void init(Labyrinthe laby) {
        getContentPane().add(pan); // ajouter le panneau dans la fenêtre
        setTitle ("Labyrinthe");
        setLocationRelativeTo(null);
        setSize (470, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boutonsLaby.init(laby);
        labInit(laby);
        menuInit();
        // rendre la fenetre visible
        this.setVisible(true);
    }
    
    // Méthode qui affiche la grille du labyrinthe 
    public void affiche(Labyrinthe laby) {
        /*
        Créer deuxième matrice de boutons por afficher perso + départ/arrivée
        Images devront être transparentes
        */
        boutonsLaby.afficheMatrice();
        // rendre la fenetre visible
        this.setVisible(true);
    }
    
    public void labInit(Labyrinthe laby) {
        lab.setLayout(new GridLayout(laby.getTailleY(), laby.getTailleX())); // mise en forme avec une grille 
        for (int i = 0; i < laby.getTailleY(); i++) {
            for (int j = 0; j < laby.getTailleX(); j++) {
                lab.add(boutonsLaby.boutons[i][j]);
            }
        }
    }
    
    public void menuInit() {
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        menu.add(setDFS);
        menu.add(setRandom);
        menu.add(setExit);
    }
}
