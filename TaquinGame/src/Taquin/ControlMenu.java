package Taquin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlMenu implements ActionListener {

    private Fenetre fenetre;
    private Model model;
    private ControlButton controlButton;
    public static Musique musique =null ;


    //-----------------------------------CONSTRUCTEUR---------------------------------------------------------------

    public ControlMenu(Fenetre fenetre, Model model) {

        this.fenetre = fenetre;
        this.model = model;
        fenetre.setControlMenu(this);
        musique = new Musique("./Ressources/GameSong.mp3", true);
        musique.start();

    }
    
    //-------------------------------GETTING MUSIQUE----------------------------------------------------------------
    
    
	public Musique getMusique() {
		return musique;
	}

	

    //-------------------------------METHODES POUR CONTROLER LE MENU----------------------------------------------------

    public void actionPerformed(ActionEvent e) throws IndexOutOfBoundsException{

        //Choisir la taille de la table
    	
    	
        if (e.getSource() == fenetre.tailleTrois){
            fenetre.undisplay();
            fenetre = new Fenetre(model, 3);
            fenetre.setControlMenu(this);
            fenetre.display();
            

        }
        if (e.getSource() == fenetre.tailleQuatre){
        	
            fenetre.undisplay();
            fenetre = new Fenetre(model, 4);
            fenetre.setControlMenu(this);
            fenetre.display();
           
            
        }

        if (e.getSource() == fenetre.tailleCinq){
            fenetre.undisplay();
            fenetre = new Fenetre(model, 5);
            fenetre.setControlMenu(this);
            fenetre.display();
            
        }

        //Melanger

        if (e.getSource() == fenetre.menuMelanger) {
            fenetre.undisplay();
            controlButton = new ControlButton(model, fenetre);
            model.setEtatPartie(true);
            fenetre.melangerCase();
            fenetre.remetCouleur();
            fenetre.setLocationRelativeTo(null);
            fenetre.display();
            fenetre.menuMelanger.removeActionListener(this);
        }
        
        //Comment Jouer ?
        
        if (e.getSource() == fenetre.commentJouer) {
        	fenetre.creerBoiteDialogCommentJouer("Le jeu taquin se compose d'un cadre de tuiles\n" + 
        			"carrées numérotées dans un ordre aléatoire avec\n" + 
        			"une tuile manquante. Pour résoudre le casse tête,\n" + 
        			"les joueurs doivent placer les tuiles dans l'ordre\n" + 
        			"en effectuant des mouvements de glissement qui\n" + 
        			"utilisent l'espace vide. ");
        }
        
        // A propos 
        if (e.getSource() == fenetre.Apropos) {
        	fenetre.creerBoiteDialogApropos("Réaliser par : \n  Adnen Mrad ");
        }
        
        //Lire les scores

        if (e.getSource() == fenetre.score3){

            try{
                model.rangerScore("score3.txt");

                String meilleurNom1, meilleurNom2, meilleurNom3;
                String meilleurScore1, meilleurScore2, meilleurScore3;
                if(!model.getMeilleurScore3().get(0).equals("")){
                    meilleurNom1 = model.getMeilleurScore3().get(0)[0];
                    meilleurScore1 = model.getMeilleurScore3().get(0)[1];
                }
                else{
                    meilleurNom1 = "";
                    meilleurScore1 = "";
                }
                if(!model.getMeilleurScore3().get(1).equals("")){
                    meilleurNom2 = model.getMeilleurScore3().get(1)[0];
                    meilleurScore2 = model.getMeilleurScore3().get(1)[1];
                }
                else{
                    meilleurNom2 = "";
                    meilleurScore2 = "";
                }
                if(!model.getMeilleurScore3().get(2).equals("")){
                    meilleurNom3 = model.getMeilleurScore3().get(2)[0];
                    meilleurScore3 = model.getMeilleurScore3().get(2)[1];
                }
                else{
                    meilleurNom3 = "";
                    meilleurScore3 = "";
                }

                fenetre.creerBoiteDialogScore("3x3", "1- " + meilleurNom1 + "=>" + meilleurScore1
                        + "\n2- " + meilleurNom2 + "=>" + meilleurScore2
                        + "\n3- " + meilleurNom3 + "=>" + meilleurScore3, "score3.txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if (e.getSource() == fenetre.score4) {

            try {
                model.rangerScore("score4.txt");

                String meilleurNom1, meilleurNom2, meilleurNom3;
                String meilleurScore1, meilleurScore2, meilleurScore3;
                if (!model.getMeilleurScore4().get(0).equals("")) {
                    meilleurNom1 = model.getMeilleurScore4().get(0)[0];
                    meilleurScore1 = model.getMeilleurScore4().get(0)[1];
                } else {
                    meilleurNom1 = "";
                    meilleurScore1 = "";
                }
                if (!model.getMeilleurScore4().get(1).equals("")) {
                    meilleurNom2 = model.getMeilleurScore4().get(1)[0];
                    meilleurScore2 = model.getMeilleurScore4().get(1)[1];
                } else {
                    meilleurNom2 = "";
                    meilleurScore2 = "";
                }
                if (!model.getMeilleurScore4().get(2).equals("")) {
                    meilleurNom3 = model.getMeilleurScore4().get(2)[0];
                    meilleurScore3 = model.getMeilleurScore4().get(2)[1];
                } else {
                    meilleurNom3 = "";
                    meilleurScore3 = "";
                }

                fenetre.creerBoiteDialogScore("4x4", "1- " + meilleurNom1 + "=>" + meilleurScore1
                        + "\n2- " + meilleurNom2 + "=>" + meilleurScore2
                        + "\n3- " + meilleurNom3 + "=>" + meilleurScore3, "score4.txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == fenetre.score5) {

                System.out.println("score5");

                try {
                    model.rangerScore("score5.txt");

                    String meilleurNom1, meilleurNom2, meilleurNom3;
                    String meilleurScore1, meilleurScore2, meilleurScore3;
                    if (!model.getMeilleurScore5().get(0).equals("")) {
                        meilleurNom1 = model.getMeilleurScore5().get(0)[0];
                        meilleurScore1 = model.getMeilleurScore5().get(0)[1];
                    } else {
                        meilleurNom1 = "";
                        meilleurScore1 = "";
                    }
                    if (!model.getMeilleurScore5().get(1).equals("")) {
                        meilleurNom2 = model.getMeilleurScore5().get(1)[0];
                        meilleurScore2 = model.getMeilleurScore5().get(1)[1];
                    } else {
                        meilleurNom2 = "";
                        meilleurScore2 = "";
                    }
                    if (!model.getMeilleurScore5().get(2).equals("")) {
                        meilleurNom3 = model.getMeilleurScore5().get(2)[0];
                        meilleurScore3 = model.getMeilleurScore5().get(2)[1];
                    } else {
                        meilleurNom3 = "";
                        meilleurScore3 = "";
                    }

                    fenetre.creerBoiteDialogScore("5x5", "1- " + meilleurNom1 + "=>" + meilleurScore1
                            + "\n2- " + meilleurNom2 + "=>" + meilleurScore2
                            + "\n3- " + meilleurNom3 + "=>" + meilleurScore3, "score5.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

    	


}