/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.Scanner;
import modele.*;

/**
 *
 * @author segado
 */
public class LabyConsole {

    Scanner in = new Scanner(System.in); // lire au clavier

    @Override
    public String toString() {
        String s;
       
        s = in.next();
        return s;
    }

    /**
     * affiche le menu et retourne le choix
     *
     * @return choix
     */
    public char menu() {

        String choix;
        System.out.println("1 Déplacer automatique en profondeur");
        System.out.println("2 Déplacer aléatoire");
        System.out.println("0 Quitter");

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre choix :");
        choix = sc.next();

        return choix.charAt(0);
    }

    /**
     * affiche la poasition de la case 
     * @param c 
     */
    public void affiche(Case c) {
        System.out.println("ligne = " + c.getPositionY() + " colonne = " + c.getPositionX());
    }

    /**
     * affiche un labyrinthe en mode console
     * @param laby
     */
    public void affiche(Labyrinthe laby) {
        // Lire les cases du labyrinthe
        for (int y = 0; y < laby.getTailleY(); y++) { // lignes

            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    System.out.print("X");
                } else {
                    if (c.getVisited()) {
                        System.out.print("V");
                    } else {
                        System.out.print("_");
                    }
                }
            }
            System.out.println();
        }
    }
}
