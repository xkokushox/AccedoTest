package com.freakybyte.accedo.controller.home.impl;

import android.os.CountDownTimer;
import android.widget.ImageView;

import com.freakybyte.accedo.R;
import com.freakybyte.accedo.controller.home.constructors.HomePresenter;
import com.freakybyte.accedo.controller.home.constructors.HomeView;
import com.freakybyte.accedo.di.manager.SqliteManager;
import com.freakybyte.accedo.model.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class HomePresenterImpl implements HomePresenter {

    private HomeView mHomeView;

    private SqliteManager mSqliteManager;

    private static CountDownTimer cdTimer = null;


    private List<CardModel> cardsList = new ArrayList<>();
    private List<ImageView> cardsView = new ArrayList<>();

    private CardModel c1;
    private CardModel c2;

    private int pairs = 8;
    private boolean bAllowClick;

    @Inject
    public HomePresenterImpl(SqliteManager mSqliteManager, HomeView homeView) {
        mHomeView = homeView;
        this.mSqliteManager = mSqliteManager;
    }

    @Override
    public void saveUser() {

    }

    @Override
    public void doTurn(ImageView imageBoard, int position) {
        if (bAllowClick)
            if (!cardsList.get(position).isMatched())
                if (c1 == null) {
                    c1 = cardsList.get(position);
                    mHomeView.flipCard(imageBoard, c1.getId());
                } else if (c2 == null) {
                    c2 = cardsList.get(position);
                    mHomeView.flipCard(imageBoard, c2.getId());
                    checkCards();
                }
    }

    @Override
    public void addCardBoard(ImageView cardBoard) {
        cardsView.add(cardBoard);
    }

    @Override
    public void restartBoard() {
        List<Integer> cardVals = new ArrayList<>();

        cardVals.clear();
        cardsList.clear();

        for (int i = 0; i < pairs; i++) {
            cardVals.add(i);
            cardVals.add(i);
        }

        Collections.shuffle(cardVals);

        for (int val : cardVals) {
            CardModel c = new CardModel();
            c.setId(val);
            cardsList.add(c);
        }
        c1 = null;
        c2 = null;
        bAllowClick = true;

        mHomeView.restartGame();

    }

    @Override
    public void flipBackAllCards() {
        for (int a = 0; a < cardsList.size(); a++) {
            if (!cardsList.get(a).isMatched())
                cardsView.get(a).setImageResource(R.drawable.card_bg);
        }
        bAllowClick = true;
        c1 = null;
        c2 = null;
    }

    @Override
    public void checkCards() {
        if (c1.getId() == c2.getId()) {
            c1.setMatched(true); //flags the button as having been matched
            c2.setMatched(true);
            if (isGameWon()) {
                mHomeView.wonGame();
            } else {
                c1 = null;
                c2 = null;
            }
        } else {
            bAllowClick = false;
            startTimer();
        }
    }

    @Override
    public boolean isGameWon() {
        for (int a = 0; a < cardsList.size(); a++) {
            if (cardsList.get(a).isMatched() == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void startTimer() {
        if (cdTimer == null) {
            cdTimer = new CountDownTimer(1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    flipBackAllCards();
                }
            };
            cdTimer.start();
        } else {
            cdTimer.cancel();
            cdTimer.start();
        }
    }

    @Override
    public void onDestroy() {

    }
}
