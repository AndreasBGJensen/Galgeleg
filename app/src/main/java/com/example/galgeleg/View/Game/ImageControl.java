package com.example.galgeleg.View.Game;

import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.galgeleg.R;

/*
Image Controle is used to controle the images that are shown in the Fragment3_Game.
 */

public class ImageControl {


    ImageControl() { }

    public void map(int antalforkerte, ImageView errorIm, FrameLayout errorImage) {

        if (antalforkerte == 1) {

            errorIm.setImageResource(R.drawable.forkert1);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);

        } else if (antalforkerte == 2) {
            errorIm.setImageResource(R.drawable.forkert2);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);

        } else if (antalforkerte == 3) {
            errorIm.setImageResource(R.drawable.forkert3);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);

        } else if (antalforkerte == 4) {
            errorIm.setImageResource(R.drawable.forkert4);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);

        } else if (antalforkerte == 5) {
            errorIm.setImageResource(R.drawable.forkert5);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);

        } else if (antalforkerte == 6) {
            errorIm.setImageResource(R.drawable.forkert6);
            errorImage.removeAllViews();
            errorImage.addView(errorIm);
        }

    }
}
