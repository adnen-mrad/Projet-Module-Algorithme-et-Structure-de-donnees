package Taquin;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Model {

    //-----------------------------------LES ATTRIBUTS-------------------------------------------------

    private Fenetre fenetre;
    private Chrono chrono;
    private String score;
    private boolean etatPartie;

    private String nomJoueur;

    public  final static String SEPARATOR = "_";

    //Listes des lignes present dans les fichiers textes
    private ArrayList<String> lireScore3;
    private ArrayList<String> lireScore4;
    private ArrayList<String> lireScore5;

    //Listes composes du tableau de String pour avoir les temps separes des noms
    private ArrayList<String[]> scoreTime3;
    private ArrayList<String[]> scoreTime4;
    private ArrayList<String[]> scoreTime5;

    //Listes avec les temps pour pouvoir les ranger dans l'ordre croissant
    private ArrayList<String> scoreChrono3;
    private ArrayList<String> scoreChrono4;
    private ArrayList<String> scoreChrono5;

    //Liste final qui contient les 3 meilleurs scores avec les noms
    private ArrayList<String[]> meilleurScore3;
    private ArrayList<String[]> meilleurScore4;
    private ArrayList<String[]> meilleurScore5;

    private String line;

    //-----------------------------LE CONSTRUCTEUR--------------------------------------------------

    Model(){

        etatPartie = false;
        nomJoueur = "";
        line = "";
        score = "0";

        lireScore3 = new ArrayList<>();
        lireScore4 = new ArrayList<>();
        lireScore5 = new ArrayList<>();

        scoreTime3 = new ArrayList<>();
        scoreTime4 = new ArrayList<>();
        scoreTime5 = new ArrayList<>();

        scoreChrono3 = new ArrayList<>();
        scoreChrono4 = new ArrayList<>();
        scoreChrono5 = new ArrayList<>();

        meilleurScore3 = new ArrayList<>();
        meilleurScore4 = new ArrayList<>();
        meilleurScore5 = new ArrayList<>();
        
    }

    //------------------------------ACCESSEUR D'ECRITURE--------------------------------------------

    public boolean getEtatPartie() {
        return etatPartie;
    }

    public ArrayList<String[]> getMeilleurScore3() {
        return meilleurScore3;
    }

    public ArrayList<String[]> getMeilleurScore4() {
        return meilleurScore4;
    }

    public ArrayList<String[]> getMeilleurScore5() {
        return meilleurScore5;
    }

    //---------------------------ACCESSEUR DE LECTURE-------------------------------------------------------
    
    public void setEtatPartie(boolean etatPartie){
        this.etatPartie = etatPartie;
    }

    public void setNomJoueur(String nomJoueur){ this.nomJoueur = nomJoueur;}

    public void setStringChrono(String strChrono){
        score = strChrono;
    }

    //-----------------------------RESOUDRE LE JEU--------------------------------------------------------------

    public void verifMouvement(JButton[][] tabCase, int positionX, int positionY) {

        System.out.println("x = " + positionX + ", y = " + positionY);
        String temp;

        if (positionX != 0) {

            if (tabCase[positionX - 1][positionY].getText().equals("")){
                temp = tabCase[positionX - 1][positionY].getText();
                tabCase[positionX - 1][positionY].setText(tabCase[positionX][positionY].getText());
                tabCase[positionX][positionY].setText(temp);
                return;
            }

        }

        if (positionX != tabCase.length - 1) {

            if (tabCase[positionX + 1][positionY].getText().equals("")){
                temp = tabCase[positionX + 1][positionY].getText();
                tabCase[positionX + 1][positionY].setText(tabCase[positionX][positionY].getText());
                tabCase[positionX][positionY].setText(temp);
                return;
            }

        }

        if (positionY != tabCase.length - 1) {

            if (tabCase[positionX][positionY + 1].getText().equals("")){
                temp = tabCase[positionX][positionY +1].getText();
                tabCase[positionX][positionY + 1].setText(tabCase[positionX][positionY].getText());
                tabCase[positionX][positionY].setText(temp);
                return;
            }

        }

        if (positionY != 0) {

            if (tabCase[positionX][positionY  - 1].getText().equals("")){
                temp = tabCase[positionX][positionY - 1].getText();
                tabCase[positionX][positionY - 1].setText(tabCase[positionX][positionY].getText());
                tabCase[positionX][positionY].setText(temp);
                return;
            }

        }

    }

    public boolean estGagne(JButton[][] tabCase){

        //creation d'un tableau pour veifier si les cases sont dans l'ordre
        String[][] tabString = new String[tabCase.length][tabCase.length];
        int nbr = 0;
        for (int i = 0; i < tabString.length; i++){
            for (int j = 0; j < tabString[i].length; j ++){
                tabString[i][j] = String.valueOf(nbr);
                nbr++;
            }
        }
        tabString[0][0] = "";

        //compteur des cases dans l'ordre
        int cptOrdre = 0;

        //verification des cases 
        for (int i= 0; i < tabCase.length; i++){
            for (int j = 0; j < tabCase[i].length; j++){
                if (tabCase[i][j].getText().equals(tabString[i][j])){
                    cptOrdre++;
                    System.out.println("cptOrdre:"+cptOrdre+" nbrCase:"+tabCase.length*tabCase.length);
                }
                else{
                    cptOrdre = 0;
                    System.out.println("cptOrdre:"+cptOrdre+" nbrCase:"+tabCase.length*tabCase.length);
                }
            }
        }
        if (cptOrdre == tabCase.length*tabCase.length){
            System.out.println("Dans l'ordre");
            return true;
        }
        else{
            System.out.println("Pas dans l'ordre");
            return false;
        }

    }

    //----------------------------------LECTURE ET ECRITURE DES SCORES------------------------------------------

    //Ecriture
    public void ecrireScore(String fichier) throws IOException {

        System.out.println("Fichier : "+fichier);
        File file = new File(fichier);
        System.out.println("File : "+file);
        System.out.println(score);

        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        bw.write(nomJoueur);
        bw.write("_");
        bw.write(score);
        bw.newLine();
        bw.close();

    }

    //Lecture des scores dans les fichiers textes
    public void lireScore(String fichier) throws IOException {

        System.out.println("Je lis:"+fichier);

        String s;

        File file = new File(fichier);

        BufferedReader br = new BufferedReader(new FileReader(file));

        if (fichier.equals("score3.txt")){
            while ((s = br.readLine()) != null) {
                lireScore3.add(s);
            }
        }

        if (fichier.equals("score4.txt")){
            while ((s = br.readLine()) != null) {
                lireScore4.add(s);
            }
        }

        if (fichier.equals("score5.txt")){
            while ((s = br.readLine()) != null) {
                lireScore5.add(s);
            }
        }

        br.close();

        for (int i = 0; i < lireScore3.size(); i++){
            System.out.println("Score3 ligne " + i + " : " + lireScore3.get(i));
        }

        for (int i = 0; i < lireScore4.size(); i++){
            System.out.println("Score4 ligne " + i + " : " + lireScore4.get(i));
        }

        for (int i = 0; i < lireScore5.size(); i++){
            System.out.println("Score5 ligne " + i + " : " + lireScore5.get(i));
        }

    }

    public void rangerScore(String fichier) throws IOException {

        lireScore(fichier);

        if (fichier.equals("score3.txt")){

            //On met dans un tableau de taille 2 le nom et le temps separement
            String[] splitArray = null;
            String[] strListe = new String[lireScore3.size()];
            for (int i = 0; i < lireScore3.size(); i++) {
                strListe[i] = lireScore3.get(i);
                splitArray = strListe[i].split(SEPARATOR);
                System.out.print(splitArray[1]);
                scoreTime3.add(splitArray);
                System.out.println(Arrays.toString(scoreTime3.get(i)));
            }

            //on range la liste dans l'ordre decroissant par rapport au temps
            for (int i = 0; i < scoreTime3.size(); i++){
                //récupération du temps
                scoreChrono3.add(scoreTime3.get(i)[1]);
                System.out.println(scoreChrono3.get(i));
            }
            //trie dans l'ordre croissant
            Collections.sort(scoreChrono3);
            for (int i = 0; i < scoreChrono3.size(); i++){
                System.out.println(scoreChrono3.get(i));
            }

            //On recherche les noms qui vont avec les temps qui sont cette fois trie
            for (int i = 0; i < scoreChrono3.size(); i++) {
                for (int j = 0; j < scoreTime3.size(); j++) {
                    if (scoreChrono3.get(i).equals(scoreTime3.get(j)[1])) {
                        String[] meilleur3 = new String[2];
                        //contient les noms
                        meilleur3[0] = scoreTime3.get(j)[0];
                        //contient les temps
                        meilleur3[1] = scoreTime3.get(j)[1];
                        meilleurScore3.add(meilleur3);
                    }
                }
            }
            System.out.println(meilleurScore3.size());
            for (int i = 0; i < meilleurScore3.size(); i++){
                System.out.println("Meilleur score 3 " + i);
                System.out.println(Arrays.toString(meilleurScore3.get(i)));
            }

        }

        if (fichier.equals("score4.txt")){

            //On met dans un tableau de taille 2 le nom et le temps separement
            String[] splitArray = null;
            String[] strListe = new String[lireScore4.size()];
            for (int i = 0; i < lireScore4.size(); i++) {
                strListe[i] = lireScore4.get(i);
                splitArray = strListe[i].split(SEPARATOR);
                System.out.print(splitArray[1]);
                scoreTime4.add(splitArray);
                System.out.println(Arrays.toString(scoreTime4.get(i)));
            }

            //on range la liste dans l'ordre decroissant par rapport au temps
            for (int i = 0; i < scoreTime4.size(); i++){
                //récupération du temps
                scoreChrono4.add(scoreTime4.get(i)[1]);
                System.out.println(scoreChrono4.get(i));
            }
            //trie dans l'ordre croissant
            Collections.sort(scoreChrono4);
            for (int i = 0; i < scoreChrono4.size(); i++){
                System.out.println(scoreChrono4.get(i));
            }

            //On recherche les noms qui vont avec les temps qui sont cette fois trie
            for (int i = 0; i < scoreChrono4.size(); i++) {
                for (int j = 0; j < scoreTime4.size(); j++) {
                    if (scoreChrono4.get(i).equals(scoreTime4.get(j)[1])) {
                        String[] meilleur4 = new String[2];
                        //contient les noms
                        meilleur4[0] = scoreTime4.get(j)[0];
                        //contient les temps
                        meilleur4[1] = scoreTime4.get(j)[1];
                        meilleurScore4.add(meilleur4);
                    }
                }
            }
            for (int i = 0; i < meilleurScore4.size(); i++){
                System.out.println("Meilleur score 4 " + i);
                System.out.println(Arrays.toString(meilleurScore4.get(i)));
            }

        }

        if (fichier.equals("score5.txt")){

            //On met dans un tableau de taille 2 le nom et le temps separement
            String[] splitArray = null;
            String[] strListe = new String[lireScore5.size()];
            for (int i = 0; i < lireScore5.size(); i++) {
                strListe[i] = lireScore5.get(i);
                splitArray = strListe[i].split(SEPARATOR);
                System.out.print(splitArray[1]);
                scoreTime5.add(splitArray);
                System.out.println(Arrays.toString(scoreTime5.get(i)));
            }


            //on range la liste dans l'ordre deroissant par rapport au temps
            for (int i = 0; i < scoreTime5.size(); i++){
                //recuperation du temps
                scoreChrono5.add(scoreTime5.get(i)[1]);
                System.out.println(scoreChrono5.get(i));
            }
            //trie dans l'ordre croissant
            Collections.sort(scoreChrono5);
            for (int i = 0; i < scoreChrono5.size(); i++){
                System.out.println(scoreChrono5.get(i));
            }

            //On recherche les noms qui vont avec les temps qui sont cette fois trie
            for (int i = 0; i < scoreChrono5.size(); i++) {
                for (int j = 0; j < scoreTime5.size(); j++) {
                    if (scoreChrono5.get(i).equals(scoreTime5.get(j)[1])) {
                        String[] meilleur5 = new String[2];
                        //contient les noms
                        meilleur5[0] = scoreTime5.get(j)[0];
                        //contient les temps
                        meilleur5[1] = scoreTime5.get(j)[1];
                        meilleurScore5.add(meilleur5);
                    }
                }
            }
            for (int i = 0; i < meilleurScore5.size(); i++){
                System.out.println("Meilleur score 5" + i);
                System.out.println(Arrays.toString(meilleurScore5.get(i)));
            }

        }

    }

    public void afficheNomJoueur(){
        System.out.println(nomJoueur);
    }

}