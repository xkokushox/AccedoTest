package com.freakybyte.accedo.controller.home.impl;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.freakybyte.accedo.controller.home.constructors.HomePresenter;
import com.freakybyte.accedo.controller.home.constructors.HomeView;
import com.freakybyte.accedo.di.manager.SqliteManager;
import com.freakybyte.accedo.model.CardModel;
import com.freakybyte.accedo.model.ScoreModel;

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

    private CountDownTimer cdTimer = null;

    private List<CardModel> cardsList = new ArrayList<>();
    private List<ImageView> cardsView = new ArrayList<>();

    private CardModel c1;
    private CardModel c2;

    private int pairs = 8;
    private boolean bAllowClick;
    private int iUserScore;
    private final int iCorrectPair = 2;
    private final int iInCorrectPair = 1;

    @Inject
    public HomePresenterImpl(@Nullable SqliteManager mSqliteManager, @Nullable HomeView homeView) {
        mHomeView = homeView;
        this.mSqliteManager = mSqliteManager;
    }

    @Override
    public void saveUser(ScoreModel score) {
        mSqliteManager.getScoreDao().insertScore(score);
        restartBoard();
    }

    @Override
    public void doTurn(ImageView imageBoard, int position) {
        if (mHomeView == null)
            return;

        if (bAllowClick) {
            if (!cardsList.get(position).isMatched())
                if (c1 == null && !cardsList.get(position).isFlipped()) {
                    cardsList.get(position).setFlipped(true);
                    c1 = cardsList.get(position);
                    mHomeView.flipCard(imageBoard, c1.getId());
                } else if (c2 == null && !cardsList.get(position).isFlipped()) {
                    cardsList.get(position).setFlipped(true);
                    c2 = cardsList.get(position);
                    mHomeView.flipCard(imageBoard, c2.getId());

                    bAllowClick = false;
                    startTimer(1000);
                }
        }
    }

    @Override
    public void addCardBoard(ImageView cardBoard) {
        cardsView.add(cardBoard);
    }

    @Override
    public void restartBoard() {
        if (mHomeView == null)
            return;

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

        iUserScore = 0;
        mHomeView.updateScore(iUserScore);
        changeVisibilityCards();
        flipBackAllCards();
    }

    @Override
    public void flipBackAllCards() {
        if (mHomeView == null)
            return;

        for (int a = 0; a < cardsList.size(); a++) {
            if (!cardsList.get(a).isMatched()) {
                cardsList.get(a).setFlipped(false);
                mHomeView.flipBack(cardsView.get(a));
            } else {
                cardsList.get(a).setFlipped(true);
            }
        }
        c1 = null;
        c2 = null;
    }

    @Override
    public void flipAllCards() {
        if (mHomeView == null)
            return;

        bAllowClick = false;

        for (int a = 0; a < cardsList.size(); a++)
            mHomeView.flipCard(cardsView.get(a), cardsList.get(a).getId());

        startTimer(3000);
    }

    @Override
    public void changeVisibilityCards() {
        for (int a = 0; a < cardsList.size(); a++)
            mHomeView.changeVisibilityCard(cardsView.get(a), !cardsList.get(a).isMatched());
    }

    @Override
    public void checkCards() {
        if (mHomeView == null)
            return;

        if (c1.getId() == c2.getId()) {
            c1.setMatched(true);
            c2.setMatched(true);
            iUserScore += iCorrectPair;
            mHomeView.updateScore(iUserScore);
            c1 = null;
            c2 = null;
            changeVisibilityCards();

            if (isGameWon())
                mHomeView.onGameFinished(iUserScore);
        } else {
            iUserScore -= iInCorrectPair;

            mHomeView.updateScore(iUserScore);
            flipBackAllCards();
        }

        bAllowClick = true;

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
    public void startTimer(final int iTimer) {
        cdTimer = new CountDownTimer(iTimer, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                checkCards();
            }
        };
        cdTimer.start();
    }

    @Override
    public void onDestroy() {
        mHomeView = null;
        cdTimer = null;
        cardsList = null;
        cardsView = null;
        c1 = null;
        c2 = null;
    }
}
