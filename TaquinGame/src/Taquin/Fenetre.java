package Taquin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Fenetre extends JFrame {

    //---------------------------LES ATTRIBUTS -------------------------------------------

    protected Model model;
    public static Random random = new Random();

    //Menu
    protected JMenuBar barMenu;
    protected JMenu menuOpen;
    protected JMenuItem menuMelanger;
    protected JMenu menuScores;
    protected JMenuItem score3;
    protected JMenuItem score4;
    protected JMenuItem score5;
    protected JMenu taille;
    protected JMenuItem tailleTrois;
    protected JMenuItem tailleQuatre;
    protected JMenuItem tailleCinq;
    protected JMenu menuAide ; 
    protected JMenuItem commentJouer ; 
    protected JMenuItem Apropos ; 

    //Affichage du chrono avec son JPanel
    protected JLabel labelChrono;
    protected JPanel panelChrono;
    private Chrono chrono;

    //JPanel vide pour pouvoir utiliser le borderLayout ( les bordures )
    protected JPanel panelNord;
    protected JPanel panelWest;
    protected JPanel panelEast;
    protected JPanel panelSouth;

    protected JPanel panelTab;   //JPanel contenant la table

    protected JPanel panelGeneral;   //JPanel general

    protected JButton[][] cellTab;     //Table avec les JButton

    protected JOptionPane dialogVictoire;    //Boite de dialog pour enregistrer son score lors de la victoire

    protected JOptionPane dialogScore;       //Boite de dialog pour afficher les scores

    private ControlMenu controlMenu;      //Accees au controleur

    private int tailleTab;    //Taille du tableau
    


    
    //--------------------------------LE CONSTRUCTEUR ----------------------------

    public Fenetre(Model model, int tailleTab){

        this.model = model;

        initAttribut(tailleTab);
        creerWidnetVersionGeneral();
        creerMenu();
        setSize(600, 600);
        setTitle("Taquin");
        display();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    //---------------------LES ACCESSEUR DE LECTURE--------------------------------

    public void setControlButton(ActionListener listener){
        for(int i = 0; i < cellTab.length; i++)
            for(int j = 0; j < cellTab[i].length; j++)
                cellTab[i][j].addActionListener(listener);
    }

    public void setControlMenu(ActionListener listener){
        tailleTrois.addActionListener(listener);
        tailleQuatre.addActionListener(listener);
        tailleCinq.addActionListener(listener);
        menuMelanger.addActionListener(listener);
        score3.addActionListener(listener);
        score4.addActionListener(listener);
        score5.addActionListener(listener);
        commentJouer.addActionListener(listener);
        Apropos.addActionListener(listener);

    }

    public void setTailleTab(int tailleTab){
        this.tailleTab = tailleTab;
    }

    //----------------------LES ACCESSEURS D'ECRITURE-----------------------------

    public JButton[][] getCellTab(){
        return cellTab;
    }

    public int getTailleTab(){
        return tailleTab;
    }

    public Chrono getChrono(){
        return chrono;
    }

    //------------------------ININITIALISTION DES ATTRIBUTS -------------------------
    
    public  void initAttribut(int tailleTab){

        //initialisation du menu
        barMenu = new JMenuBar();
        menuOpen = new JMenu("Open");
        menuAide = new JMenu("Aide") ;
        menuMelanger = new JMenuItem("Melanger");
        menuScores = new JMenu("Scores");
        score3 = new JMenuItem("score 3x3");
        score4 = new JMenuItem("score 4x4");
        score5 = new JMenuItem("score 5x5");
        taille = new JMenu("Nouvelle partie");
        tailleTrois = new JMenuItem("3x3");
        tailleQuatre = new JMenuItem("4x4");
        tailleCinq = new JMenuItem("5x5");
        commentJouer = new JMenuItem("Comment Jouer ? ");
        Apropos = new JMenuItem("A propos");


        //initialisation du JLabel et du JPanel pour chronometre
        labelChrono = new JLabel("Chronometre: ");
        chrono = new Chrono(labelChrono);
        panelChrono = new JPanel();

        //panel general
        panelGeneral = new JPanel(new BorderLayout());

        //Creation des panels
        panelNord = new JPanel();
        panelEast = new JPanel();
        panelTab = new JPanel();
        panelWest = new JPanel();
        panelSouth = new JPanel();

        //taille du tableau
        this.tailleTab = tailleTab;

        dialogVictoire = new JOptionPane();
        dialogScore = new JOptionPane();

    }

    //---------------------------------CREATION DE LA TABLE-----------------------------------

    public void creationTable(){

        //taille de la table
        cellTab = new JButton[tailleTab][tailleTab];
        System.out.println("taille = " + cellTab.length);

        //initilaiser les cases par les nombres 
        int nbr  = 0;

        try {

            //creation du tableau
            for (int i = 0; i < cellTab.length; i++){
                for(int j = 0; j < cellTab[i].length; j++) {
                    String stringSurCase = Integer.toString(nbr);
                    cellTab[i][j] = new JButton(stringSurCase);
                    cellTab[i][j].setBackground(Color.getHSBColor(178, 81, 53));
                    nbr++;
                }
            }

            //la 1ere cellule est vide
            cellTab[0][0] = new JButton();
            cellTab[0][0].setBackground(Color.getHSBColor(178, 81, 53));

        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            System.out.println("Probleme");
        }

    }

    //--------------------------MISE EN PLACE DU TABLEAU--------------------------------------------

    public void rangeTab(){

        creationTable();

        try {
            panelTab = new JPanel(new GridLayout(tailleTab, tailleTab));
            for (int i = 0; i < cellTab.length; i++)
                for (int j = 0; j < cellTab[i].length; j++)
                    panelTab.add(cellTab[i][j]);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }

    //----------------------------MISE EN FORME --------------------------------------------------

    public void creerWidnetVersionGeneral(){

        rangeTab();
        
        panelChrono.add(labelChrono); 
        panelNord.add(panelChrono);  

        // 
        panelNord.setBackground(Color.getHSBColor(178, 81, 53));
        panelWest.setBackground(Color.getHSBColor(178, 81, 53));
        panelEast.setBackground(Color.getHSBColor(178, 81, 53));
        panelSouth.setBackground(Color.getHSBColor(178, 81, 53));

        panelGeneral.add(panelNord, BorderLayout.NORTH);
        panelGeneral.add(panelWest, BorderLayout.WEST);
        panelGeneral.add(panelTab, BorderLayout.CENTER);
        panelGeneral.add(panelEast, BorderLayout.EAST);
        panelGeneral.add(panelSouth, BorderLayout.SOUTH);

        setContentPane(panelGeneral);

    }

    //Creation du menu
    public void creerMenu(){

        taille.add(tailleTrois);
        taille.add(tailleQuatre);
        taille.add(tailleCinq);

        menuScores.add(score3);
        menuScores.add(score4);
        menuScores.add(score5);

        menuOpen.add(taille);
        menuOpen.addSeparator();
        menuOpen.add(menuMelanger);
        menuOpen.addSeparator();
        menuOpen.add(menuScores);
        
        menuAide.add(commentJouer);
        menuAide.addSeparator();
        menuAide.add(Apropos) ;
        
        barMenu.add(menuOpen);
        barMenu.add(menuAide);
        setJMenuBar(barMenu);


    }

    //rendre la fenetre visible
    public void display() {
        setVisible(true);
    }

    //faire disparaitre la fenetre
    public void undisplay() {
        setVisible(false);
    }

    //--------------------------------MELANGER LES CASES EN DEBUT DE LA PARTIE-----------------------------

    
    //Melange les cases
    public void melangerCase(){

        int k = 0, posX = 0, posY = 0;
        
        while (k < 100){

            int mouvAlea = random.nextInt(4) + 1;
            
            if (mouvAlea == 1){
                if (posX != 0){
                    model.verifMouvement(cellTab, posX - 1, posY);
                    posX = posX - 1;
                }
            }

            if (mouvAlea == 2){
                if (posY != cellTab.length - 1){
                    model.verifMouvement(cellTab, posX, posY + 1);
                    posY = posY + 1;
                }
            }

            if (mouvAlea == 3){
                if (posX != cellTab.length - 1){
                    model.verifMouvement(cellTab, posX + 1, posY);
                    posX = posX + 1;
                }
            }

            if (mouvAlea == 4){
                if (posY != 0){
                    model.verifMouvement(cellTab, posX, posY - 1);
                    posY = posY - 1;
                }
            }

            k++;

        }

    }

    //Permet de remettre les bonnes couleurs
    public void remetCouleur(){

        for(int i = 0; i < cellTab.length; i++){
            for(int j = 0; j < cellTab[i].length; j++) {
                String ceQuiASurLeBouton = cellTab[i][j].getText();
                if (ceQuiASurLeBouton.equals(""))
                    cellTab[i][j].setBackground(Color.red );
                else
                    cellTab[i][j].setBackground(Color.getHSBColor(178, 81, 53));
            }
        }

    }

    //------------------------AFFICHAGE LORS DE LA VICTOIRE------------------------------------------------

    //Creation de la fenetre de dialog
    public void creerBoiteDialogVictoire(){

        dialogVictoire = new JOptionPane();
        String nomJoueur = JOptionPane.showInputDialog(null, "Bravo vous avez fini, mettez votre nom pour enregistrer votre chrono.", "Victoire", JOptionPane.QUESTION_MESSAGE);
        model.setNomJoueur(nomJoueur);

    }

    //---------------------------AFFICHER LES SCORES-----------------------------------------------------

    public void creerBoiteDialogScore(String categorie, String score, String fichier) throws IOException {

        model.rangerScore(fichier);
        dialogScore = new JOptionPane();
        JOptionPane.showMessageDialog(null, "Meilleur score : " + categorie + "\n\n" + score, "Meilleur score", JOptionPane.PLAIN_MESSAGE);

    }
    
    //---------------------------AFFICHER A PROPOS-------------------------------------------------
    
	public void creerBoiteDialogApropos(String string) {
		
        JOptionPane.showMessageDialog(null, string," A propos",JOptionPane.PLAIN_MESSAGE);

	}
    
	//---------------------------AFFICHER COMMENT JOUER------------------------------------------------
	
	public void creerBoiteDialogCommentJouer(String string) {
			
		JOptionPane.showMessageDialog(null, string," Comment Jouer ? ",JOptionPane.PLAIN_MESSAGE);

	}  

}