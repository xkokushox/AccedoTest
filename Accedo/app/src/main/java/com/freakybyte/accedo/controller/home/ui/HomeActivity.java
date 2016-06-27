package com.freakybyte.accedo.controller.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.freakybyte.accedo.AccedoApplication;
import com.freakybyte.accedo.R;
import com.freakybyte.accedo.controller.dialog.SaveScoreDialog;
import com.freakybyte.accedo.controller.home.constructors.HomeView;
import com.freakybyte.accedo.controller.home.di.DaggerHomeComponent;
import com.freakybyte.accedo.controller.home.di.HomeModule;
import com.freakybyte.accedo.controller.home.impl.HomePresenterImpl;
import com.freakybyte.accedo.controller.score.ui.ScoreActivity;
import com.freakybyte.accedo.di.manager.WidgetManager;
import com.freakybyte.accedo.di.module.WidgetModule;
import com.freakybyte.accedo.listener.ListenerDialog;
import com.freakybyte.accedo.model.ScoreModel;
import com.freakybyte.accedo.util.DebugUtils;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeView, View.OnClickListener {

    public static final String TAG = "HomeActivity";

    @Inject
    public WidgetManager mWidgetManager;

    @Inject
    public HomePresenterImpl mPresenter;

    private ImageView mImgViewScore;
    private ImageView mImgViewCard1x1;
    private ImageView mImgViewCard1x2;
    private ImageView mImgViewCard1x3;
    private ImageView mImgViewCard1x4;
    private ImageView mImgViewCard2x1;
    private ImageView mImgViewCard2x2;
    private ImageView mImgViewCard2x3;
    private ImageView mImgViewCard2x4;
    private ImageView mImgViewCard3x1;
    private ImageView mImgViewCard3x2;
    private ImageView mImgViewCard3x3;
    private ImageView mImgViewCard3x4;
    private ImageView mImgViewCard4x1;
    private ImageView mImgViewCard4x2;
    private ImageView mImgViewCard4x3;
    private ImageView mImgViewCard4x4;
    private TextView mTxtScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DaggerHomeComponent.builder()
                .sqliteComponent(((AccedoApplication) getApplication()).getSqliteComponent())
                .homeModule(new HomeModule(this)).widgetModule(new WidgetModule(this)).build().inject(this);

        getImgViewScore().setOnClickListener(this);
        getImgViewCard1x1().setOnClickListener(this);
        getImgViewCard1x2().setOnClickListener(this);
        getImgViewCard1x3().setOnClickListener(this);
        getImgViewCard1x4().setOnClickListener(this);
        getImgViewCard2x1().setOnClickListener(this);
        getImgViewCard2x2().setOnClickListener(this);
        getImgViewCard2x3().setOnClickListener(this);
        getImgViewCard2x4().setOnClickListener(this);
        getImgViewCard3x1().setOnClickListener(this);
        getImgViewCard3x2().setOnClickListener(this);
        getImgViewCard3x3().setOnClickListener(this);
        getImgViewCard3x4().setOnClickListener(this);
        getImgViewCard4x1().setOnClickListener(this);
        getImgViewCard4x2().setOnClickListener(this);
        getImgViewCard4x3().setOnClickListener(this);
        getImgViewCard4x4().setOnClickListener(this);

        mPresenter.addCardBoard(getImgViewCard1x1());
        mPresenter.addCardBoard(getImgViewCard1x2());
        mPresenter.addCardBoard(getImgViewCard1x3());
        mPresenter.addCardBoard(getImgViewCard1x4());
        mPresenter.addCardBoard(getImgViewCard2x1());
        mPresenter.addCardBoard(getImgViewCard2x2());
        mPresenter.addCardBoard(getImgViewCard2x3());
        mPresenter.addCardBoard(getImgViewCard2x4());
        mPresenter.addCardBoard(getImgViewCard3x1());
        mPresenter.addCardBoard(getImgViewCard3x2());
        mPresenter.addCardBoard(getImgViewCard3x3());
        mPresenter.addCardBoard(getImgViewCard3x4());
        mPresenter.addCardBoard(getImgViewCard4x1());
        mPresenter.addCardBoard(getImgViewCard4x2());
        mPresenter.addCardBoard(getImgViewCard4x3());
        mPresenter.addCardBoard(getImgViewCard4x4());

        mPresenter.restartBoard();
    }


    @Override
    public void onGameFinished(final int iScore) {
        mWidgetManager.createShortToast(R.string.toast_won);
        SaveScoreDialog dialogScore = new SaveScoreDialog();
        dialogScore.setListenerDialog(new ListenerDialog() {
            @Override
            public void btnOkClick(String username) {
                ScoreModel mScore= new ScoreModel(username, iScore);
                mPresenter.saveUser(mScore);
                mWidgetManager.createShortToast(String.format(getString(R.string.home_your_score), username, iScore));
                openHighScoreScreen(mScore);

            }

            @Override
            public void btnOnCancel() {
                mPresenter.restartBoard();
            }
        });
        dialogScore.show(getSupportFragmentManager(), SaveScoreDialog.TAG);
    }

    @Override
    public void updateScore(final int iScore) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getTxtScore().setText(String.format(getString(R.string.home_score), iScore));
            }
        });
    }

    @Override
    public void flipBack(final ImageView imgCard) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imgCard.setImageResource(R.drawable.card_bg);
            }
        });
    }

    @Override
    public void changeVisibilityCard(final ImageView imgCard, final boolean visible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imgCard.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    @Override
    public void flipCard(final ImageView imageBoard, final int idCard) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (idCard) {
                    case 0:
                        imageBoard.setImageResource(R.drawable.colour1);
                        break;
                    case 1:
                        imageBoard.setImageResource(R.drawable.colour2);
                        break;
                    case 2:
                        imageBoard.setImageResource(R.drawable.colour3);
                        break;
                    case 3:
                        imageBoard.setImageResource(R.drawable.colour4);
                        break;
                    case 4:
                        imageBoard.setImageResource(R.drawable.colour5);
                        break;
                    case 5:
                        imageBoard.setImageResource(R.drawable.colour6);
                        break;
                    case 6:
                        imageBoard.setImageResource(R.drawable.colour7);
                        break;
                    case 7:
                        imageBoard.setImageResource(R.drawable.colour8);
                        break;
                    default:
                        DebugUtils.getSingleton().logDebug(TAG, "Flip Card Unknown");
                        break;
                }
            }
        });
    }

    @Override
    public void openHighScoreScreen(ScoreModel score) {
        Intent mIntentScore = new Intent(HomeActivity.this, ScoreActivity.class);
        mIntentScore.putExtra(ScoreModel.TAG, score);
        startActivity(mIntentScore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgViewScore:
                openHighScoreScreen(null);
                break;
            case R.id.imgViewCard1x1:
                mPresenter.doTurn(getImgViewCard1x1(), 0);
                break;
            case R.id.imgViewCard1x2:
                mPresenter.doTurn(getImgViewCard1x2(), 1);
                break;
            case R.id.imgViewCard1x3:
                mPresenter.doTurn(getImgViewCard1x3(), 2);
                break;
            case R.id.imgViewCard1x4:
                mPresenter.doTurn(getImgViewCard1x4(), 3);
                break;
            case R.id.imgViewCard2x1:
                mPresenter.doTurn(getImgViewCard2x1(), 4);
                break;
            case R.id.imgViewCard2x2:
                mPresenter.doTurn(getImgViewCard2x2(), 5);
                break;
            case R.id.imgViewCard2x3:
                mPresenter.doTurn(getImgViewCard2x3(), 6);
                break;
            case R.id.imgViewCard2x4:
                mPresenter.doTurn(getImgViewCard2x4(), 7);
                break;
            case R.id.imgViewCard3x1:
                mPresenter.doTurn(getImgViewCard3x1(), 8);
                break;
            case R.id.imgViewCard3x2:
                mPresenter.doTurn(getImgViewCard3x2(), 9);
                break;
            case R.id.imgViewCard3x3:
                mPresenter.doTurn(getImgViewCard3x3(), 10);
                break;
            case R.id.imgViewCard3x4:
                mPresenter.doTurn(getImgViewCard3x4(), 11);
                break;
            case R.id.imgViewCard4x1:
                mPresenter.doTurn(getImgViewCard4x1(), 12);
                break;
            case R.id.imgViewCard4x2:
                mPresenter.doTurn(getImgViewCard4x2(), 13);
                break;
            case R.id.imgViewCard4x3:
                mPresenter.doTurn(getImgViewCard4x3(), 14);
                break;
            case R.id.imgViewCard4x4:
                mPresenter.doTurn(getImgViewCard4x4(), 15);
                break;
            default:
                DebugUtils.getSingleton().logError(TAG, "OnClick:: view not handled " + v.getId());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.flipBackAllCards();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private ImageView getImgViewScore() {
        if (mImgViewScore == null)
            mImgViewScore = (ImageView) findViewById(R.id.imgViewScore);
        return mImgViewScore;
    }

    private ImageView getImgViewCard1x1() {
        if (mImgViewCard1x1 == null)
            mImgViewCard1x1 = (ImageView) findViewById(R.id.imgViewCard1x1);
        return mImgViewCard1x1;
    }

    private ImageView getImgViewCard1x2() {
        if (mImgViewCard1x2 == null)
            mImgViewCard1x2 = (ImageView) findViewById(R.id.imgViewCard1x2);
        return mImgViewCard1x2;
    }

    private ImageView getImgViewCard1x3() {
        if (mImgViewCard1x3 == null)
            mImgViewCard1x3 = (ImageView) findViewById(R.id.imgViewCard1x3);
        return mImgViewCard1x3;
    }

    private ImageView getImgViewCard1x4() {
        if (mImgViewCard1x4 == null)
            mImgViewCard1x4 = (ImageView) findViewById(R.id.imgViewCard1x4);
        return mImgViewCard1x4;
    }

    private ImageView getImgViewCard2x1() {
        if (mImgViewCard2x1 == null)
            mImgViewCard2x1 = (ImageView) findViewById(R.id.imgViewCard2x1);
        return mImgViewCard2x1;
    }

    private ImageView getImgViewCard2x2() {
        if (mImgViewCard2x2 == null)
            mImgViewCard2x2 = (ImageView) findViewById(R.id.imgViewCard2x2);
        return mImgViewCard2x2;
    }

    private ImageView getImgViewCard2x3() {
        if (mImgViewCard2x3 == null)
            mImgViewCard2x3 = (ImageView) findViewById(R.id.imgViewCard2x3);
        return mImgViewCard2x3;
    }

    private ImageView getImgViewCard2x4() {
        if (mImgViewCard2x4 == null)
            mImgViewCard2x4 = (ImageView) findViewById(R.id.imgViewCard2x4);
        return mImgViewCard2x4;
    }

    private ImageView getImgViewCard3x1() {
        if (mImgViewCard3x1 == null)
            mImgViewCard3x1 = (ImageView) findViewById(R.id.imgViewCard3x1);
        return mImgViewCard3x1;
    }

    private ImageView getImgViewCard3x2() {
        if (mImgViewCard3x2 == null)
            mImgViewCard3x2 = (ImageView) findViewById(R.id.imgViewCard3x2);
        return mImgViewCard3x2;
    }

    private ImageView getImgViewCard3x3() {
        if (mImgViewCard3x3 == null)
            mImgViewCard3x3 = (ImageView) findViewById(R.id.imgViewCard3x3);
        return mImgViewCard3x3;
    }

    private ImageView getImgViewCard3x4() {
        if (mImgViewCard3x4 == null)
            mImgViewCard3x4 = (ImageView) findViewById(R.id.imgViewCard3x4);
        return mImgViewCard3x4;
    }

    private ImageView getImgViewCard4x1() {
        if (mImgViewCard4x1 == null)
            mImgViewCard4x1 = (ImageView) findViewById(R.id.imgViewCard4x1);
        return mImgViewCard4x1;
    }

    private ImageView getImgViewCard4x2() {
        if (mImgViewCard4x2 == null)
            mImgViewCard4x2 = (ImageView) findViewById(R.id.imgViewCard4x2);
        return mImgViewCard4x2;
    }

    private ImageView getImgViewCard4x3() {
        if (mImgViewCard4x3 == null)
            mImgViewCard4x3 = (ImageView) findViewById(R.id.imgViewCard4x3);
        return mImgViewCard4x3;
    }

    private ImageView getImgViewCard4x4() {
        if (mImgViewCard4x4 == null)
            mImgViewCard4x4 = (ImageView) findViewById(R.id.imgViewCard4x4);
        return mImgViewCard4x4;
    }

    private TextView getTxtScore() {
        if (mTxtScore == null)
            mTxtScore = (TextView) findViewById(R.id.txtScore);
        return mTxtScore;
    }

}
