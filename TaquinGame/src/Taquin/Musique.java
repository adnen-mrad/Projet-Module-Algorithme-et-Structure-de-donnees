package Taquin;
import java.io.FileInputStream;

import javazoom.jl.player.*;

public class Musique extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player player;
    private int pauseOnFrame = 0;
    
    //-----------------Constructeur------------------------
    public Musique(String fileLocation, boolean loop) {
        this.fileLocation = fileLocation;
        this.loop = loop;
    }
    
    //------------------Lancer la musique-----------------------
    @Override
    public void run() {

        try {
            do {
                FileInputStream buff = new FileInputStream(fileLocation);
                player = new Player(buff);
                player.play();
            } while (loop);
        } catch (Exception ioe) {
        	System.out.println(ioe.toString());
        }
    }
    
   //--------------Stop la musique------------------------
    public void close(){
        loop = false;
        player.close();
        this.interrupt();
    }


}