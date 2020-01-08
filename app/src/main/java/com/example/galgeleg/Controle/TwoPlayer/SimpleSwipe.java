package com.example.galgeleg.Controle.TwoPlayer;

import android.app.Activity;
import android.view.GestureDetector;

public class SimpleSwipe extends GestureDetector.SimpleOnGestureListener {


    public final static int SWIPE_UP = 1;

    private Activity context;
    private SimpleGestureListener listener;
    private GestureDetector detector;

    public SimpleSwipe(Activity context, SimpleGestureListener listener){
        this.context=context;
        this.listener = listener;
        detector = new GestureDetector(context,this);
    }

    public interface SimpleGestureListener{
        void onSwipe(int direction);

        void onDoubleTap();
    }

}


