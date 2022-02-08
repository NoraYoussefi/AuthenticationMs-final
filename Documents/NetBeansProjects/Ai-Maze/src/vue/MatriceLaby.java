/*  Created by riccardild on Apr 13, 2018 4:32:14 PM
 *  (C) Copyright Gianni Riccardi.
 */
package vue;

import controleur.ImpossibleMoveException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modele.*;

/**
 *
 * @author riccardild
 */
public class MatriceLaby implements ActionListener{
    public JButton boutons[][]; // matrice de boutons
    Labyrinthe laby;

    public MatriceLaby(Labyrinthe laby) {
        this.laby = laby;
    }
    
    public void init(Labyrinthe laby){
        boutons = new JButton[laby.getTailleY()][]; // instancier les lignes de la matrice de boutons
        for (int i = 0; i < laby.getTailleY(); i++)
            boutons[i] = new JButton[laby.getTailleX()];// Pour chaque ligne de la matrice, instancier les boutons
        // Ajouter les boutons dans le panneau
        for (int i = 0; i < laby.getTailleY(); i++) {
            for (int j = 0; j < laby.getTailleX(); j++) {
                boutons[i][j] = new JButton(); // instancier chaque bouton 
                boutons[i][j].putClientProperty("column", j);
                boutons[i][j].putClientProperty("row", i);
                boutons[i][j].addActionListener(this);
            }
        }
    }
    
    public void afficheMatrice() {
        for (int y = 0; y < laby.getTailleY(); y++) {
            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    // Mur
                    boutons[y][x].setText("X");
                    //boutonsLaby.boutons[y][x].setBorder(null);
                } else {
                    if (laby.getCurrentPositionX() == x && laby.getCurrentPositionY() == y) {
                        // Case actuelle
                        boutons[y][x].setText("YOU");
                        //boutonsLaby.boutons[y][x].setBorder(null);
                    } 
                    else if (laby.getArriveeX() == x && laby.getArriveeY() == y) {
                        // Case arrivee
                        boutons[y][x].setText("GOAL");
                        //boutonsLaby.boutons[y][x].setBorder(null);
                    }
                    else {
                        // Case vide
                        boutons[y][x].setText(" ");
                        //boutonsLaby.boutons[y][x].setBorder(null);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        // Debug
        System.out.println("\nThis button contains " + btn.getText() 
                + ", clicked column " + btn.getClientProperty("column")
                + ", row " + btn.getClientProperty("row"));
        int x = (int) btn.getClientProperty("column");
        int y = (int) btn.getClientProperty("row");
        
        Case c = laby.getCase(y, x);
        if (c instanceof CaseMur) {          
            // Mur
        } else {              
            if (laby.getCurrentPositionX()+1 == x && laby.getCurrentPositionY()   == y
             || laby.getCurrentPositionX()   == x && laby.getCurrentPositionY()+1 == y
             || laby.getCurrentPositionX()-1 == x && laby.getCurrentPositionY()   == y
             || laby.getCurrentPositionX()   == x && laby.getCurrentPositionY()-1 == y) {
                // Mouvement ok
                try {
                    laby.move(y, x);
                    System.out.println("legal move");
                } catch (ImpossibleMoveException ex) {
                    System.out.println("illegal move");
                }
            }                                  
            else {                        
                // Case vide                       
            }     
        }
        afficheMatrice();
    }
}
