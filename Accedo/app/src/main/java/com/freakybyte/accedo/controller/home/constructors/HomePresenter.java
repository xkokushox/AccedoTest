package com.freakybyte.accedo.controller.home.constructors;

import android.widget.ImageView;

import com.freakybyte.accedo.model.ScoreModel;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public interface HomePresenter {

    /**
     * Saves the user in the internal db.
     *
     * @param score
     */
    void saveUser(ScoreModel score);

    /**
     * Card Selected to turn.
     *
     * @param imageBoard
     * @param position
     */
    void doTurn(ImageView imageBoard, int position);

    /**
     * Add a card to the list in the presenter layer.
     *
     * @param cardBoard
     */
    void addCardBoard(ImageView cardBoard);

    /**
     * Start a new game.
     *
     */
    void restartBoard();

    /**
     * Turn back all the non paired cards.
     *
     */
    void flipBackAllCards();

    /**
     * Turn up all the cards.
     *
     */
    void flipAllCards();

    /**
     * Change the visibility of the cards.
     *
     */
    void changeVisibilityCards();

    /**
     * Check all the cards and positions.
     *
     */
    void checkCards();

    /**
     * Turn up all the cards.
     *
     */
    boolean isGameWon();

    /**
     * Start the timer to wait for the review of the cards.
     *
     * @param iTimer
     */
    void startTimer(int iTimer);

    void onDestroy();

}
