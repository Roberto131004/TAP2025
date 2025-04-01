package org.example.tap2025.componentes;

import javafx.scene.control.ProgressBar;
import java.util.Random;


public class Hilo extends Thread {
    private ProgressBar pgbRuta;


    public Hilo(String nombre, ProgressBar pbg) {
        super(nombre);
        this.pgbRuta = pbg;
    }

    @Override
    public void run() {
        super.run();
        double avance = 0;
        while (avance<1){
            avance += Math.random()*.01;
            this.pgbRuta.setProgress(avance);
            try{
                sleep((long)(Math.random()*700));
            }catch(InterruptedException e){}
        }
    }

}
