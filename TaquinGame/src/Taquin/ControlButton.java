package Taquin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ControlButton implements ActionListener{

    /*LES ATTRIBUTS ---------------------------------------------------------------------------------------*/

    private Fenetre fenetre;
    private Model model;
    private Chrono chrono;
    private ControlMenu controlMenu ; 
    //---------------------------------CONSTRUCTEUR ----------------------------------------

    public ControlButton(Model model, Fenetre fenetre) {

        this.model = model;
        this.fenetre = fenetre;
        chrono = new Chrono(fenetre.labelChrono);

        fenetre.setControlButton(this);

    }

    //-----------------------CONTROLES DES EVENEMENTS-------------------------------------------------

    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < fenetre.cellTab.length; i++){
            for(int j = 0; j < fenetre.cellTab[i].length; j++) {
                if (e.getSource() == fenetre.cellTab[i][j]) {
                    fenetre.undisplay();
                    model.verifMouvement(fenetre.cellTab, i, j);
                    fenetre.remetCouleur();
                    fenetre.setLocationRelativeTo(null);
                    fenetre.display();
                }
            }
        }

        if (model.getEtatPartie()){
            fenetre.getChrono().start();
            model.setEtatPartie(false);
        }

        if (model.estGagne(fenetre.cellTab)){

            System.out.println("Victoire");
            fenetre.getChrono().stopChrono();
            fenetre.creerBoiteDialogVictoire();
            model.setStringChrono(fenetre.getChrono().toString());
            System.out.println(fenetre.getChrono().toString());
            model.afficheNomJoueur();


            for (int i = 0; i < fenetre.cellTab.length; i++){
                for (int j = 0; j < fenetre.cellTab[i].length; j++){
                    fenetre.cellTab[i][j].removeActionListener(this);
                }
            }

            if(fenetre.cellTab.length == 3){
                try {
                    model.ecrireScore("score3.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(fenetre.cellTab.length == 4){
                try {
                    model.ecrireScore("score4.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(fenetre.cellTab.length == 5){
                try {
                    model.ecrireScore("score5.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

    }

}