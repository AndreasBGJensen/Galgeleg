package com.example.galgeleg.Controle.TwoPlayer;

import android.app.Activity;
import android.media.MediaPlayer;

import com.example.galgeleg.R;

public class Media {
   static MediaPlayer afspiller;
   static Activity context;



    public Media(Activity context){
        this.context = context;

    }

    public void play(){

            afspiller = MediaPlayer.create(context, R.raw.uplifting);
            afspiller.setVolume(1, 1);


            afspiller.start();

        Thread tjeckIsPlayinte = new Thread(r);
        tjeckIsPlayinte.start();
    }
    public void stop(){

            afspiller.stop();


    }

    public void release(){
        if(afspiller != null) {
            afspiller.release();
        }
    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            while(!afspiller.isPlaying()){
                afspiller.release();
            }
        }
    };
}
