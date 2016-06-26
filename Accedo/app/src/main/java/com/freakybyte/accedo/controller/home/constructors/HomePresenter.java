package com.freakybyte.accedo.controller.home.constructors;

import android.widget.ImageView;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public interface HomePresenter {

    void saveUser(String username);

    void doTurn(ImageView imageBoard, int position);

    void addCardBoard(ImageView cardBoard);

    void restartBoard();

    void flipBackAllCards();

    void flipAllCards();

    void checkCards();

    boolean isGameWon();

    void startTimer(int iTimer);

    void onDestroy();

}
