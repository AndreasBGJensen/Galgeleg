package com.example.galgeleg.Controle.TwoPlayer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.R;

public class Media {
   static MediaPlayer afspiller;
   static Context context;
   static Media media = null;



    private Media(Context context){
        this.context = context;
        afspiller = MediaPlayer.create(context, R.raw.uplifting);
        afspiller.setVolume(1, 1);

    }

    public static Media getInstance(Context context){
        if(media == null){
            media = new Media(context);
        }

        return media;
    }

    public static Media restartPlayer(Activity context){
        if(media == null){
            return getInstance(context);
        }
            afspiller.release();
            media = new Media(context);
        System.out.println("restart player");
            return media;

}

    public void play(){
        System.out.println("Starting Mediaplayer");
            afspiller.start();


        //Skal sikre at ressurcen bliver frigivet når afspiller er færdig med at spille.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(afspiller.isPlaying()){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Released the mediaplayer");
                afspiller.release();
            }
        };

        Thread tjeckIsPlayinte = new Thread(r);
        tjeckIsPlayinte.start();
    }
    public void stop(){
        System.out.println("Stopping Mediaplayer");
            afspiller.stop();
    }



}
