package com.freakybyte.accedo.controller.home.constructors;

import android.widget.ImageView;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public interface HomeView {

    void showLoader();

    void restartGame();

    void wonGame();

    void openScoreScreen();

    void onErrorUser();

    void flipBack(ImageView imgCard);

    void flipCard(final ImageView imageBoard, final int idCard);

}
