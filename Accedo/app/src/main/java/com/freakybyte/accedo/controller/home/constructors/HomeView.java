package com.freakybyte.accedo.controller.home.constructors;

import android.widget.ImageView;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public interface HomeView {

    void onGameWon(int iScore);

    void onGameLost();

    void updateScore(int iScore);

    void flipBack(ImageView imgCard);

    void flipCard(final ImageView imageBoard, final int idCard);

}
