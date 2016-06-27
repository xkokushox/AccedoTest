package com.freakybyte.accedo.controller.score.constructors;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public interface ScorePresenter {

    /**
     * Get the list of all the scores from the database in DESC order.
     */
    void getHighScore();


    void onDestroy();
}
