package com.freakybyte.accedo.controller.home.constructors;

import android.widget.ImageView;

import com.freakybyte.accedo.model.ScoreModel;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public interface HomeView {

    void onGameFinished(int iScore);

    void updateScore(int iScore);

    void flipBack(ImageView imgCard);

    void changeVisibilityCard(ImageView imgCard, boolean visible);

    void flipCard(final ImageView imageBoard, final int idCard);

    void openHighScoreScreen(ScoreModel score);

}
