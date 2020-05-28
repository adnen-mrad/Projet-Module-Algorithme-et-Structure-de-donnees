package Taquin;

import javax.swing.*;
import java.text.DecimalFormat;

public class Chrono extends Thread{

    private JLabel labelChrono;
    private boolean startChrono;
    private String strChrono;
    
    
    
    //---------------Constructeur--------------------------- 
    
    
    public Chrono(JLabel labelChrono){
    	
        this.labelChrono = labelChrono;
        strChrono = "0";
    }
    
    //----------------Accesseur strchrono-------------------
    
    public String getStrChrono(){ 
    	return strChrono;
    }
    

    //----------------Start le jeu--------------------------
    public void run(){

        startChrono = true;
        double time = 0;

        while(startChrono){

            try{
                sleep(10);
                time = time + 0.01;
                DecimalFormat df = new DecimalFormat("######0.00");
                strChrono = df.format(time);
                labelChrono.setText("Chronometre : " + strChrono);
            }
            catch (InterruptedException e){
               stopChrono();
            }

        }

    }
    
    
    //--------------------Stop le jeu-------------------
    public void stopChrono(){
    	
        interrupt();

    }
    
    
    //------------------getting strChrono-----------------
    public String toString(){
        return strChrono;
    }

}