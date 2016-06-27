package com.freakybyte.accedo.controller.score.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.freakybyte.accedo.AccedoApplication;
import com.freakybyte.accedo.R;
import com.freakybyte.accedo.controller.score.adapter.ScoreListAdapter;
import com.freakybyte.accedo.controller.score.constructors.ScoreView;
import com.freakybyte.accedo.controller.score.di.DaggerScoreComponent;
import com.freakybyte.accedo.controller.score.di.ScoreModule;
import com.freakybyte.accedo.controller.score.impl.ScorePresenterImpl;
import com.freakybyte.accedo.di.manager.WidgetManager;
import com.freakybyte.accedo.di.module.WidgetModule;
import com.freakybyte.accedo.model.ScoreModel;
import com.freakybyte.accedo.util.DebugUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ScoreActivity extends AppCompatActivity implements ScoreView {

    public static final String TAG = "ScoreActivity";

    @Inject
    public WidgetManager mWidgetManager;

    @Inject
    public ScorePresenterImpl mPresenter;

    private RecyclerView mRecyclerView;
    private TextView txtEmptyView;
    private Toolbar mToolbar;

    private LinearLayoutManager mLayoutManager;

    private ScoreListAdapter mAdapter;

    private ScoreModel mScoreModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mScoreModel = getIntent().getParcelableExtra(ScoreModel.TAG);

        DaggerScoreComponent.builder()
                .sqliteComponent(((AccedoApplication) getApplication()).getSqliteComponent())
                .scoreModule(new ScoreModule(this)).widgetModule(new WidgetModule(this)).build().inject(this);

        setSupportActionBar(getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getToolbar().setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_back));
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getListServices().setAdapter(getListAdapter());

        getListAdapter().setCurrentScore(mScoreModel);

        mPresenter.getHighScore();
    }

    @Override
    public void setItemsToAdapter(List<ScoreModel> scores) {
        DebugUtils.getSingleton().logDebug(TAG, "Number Of Items:: " + String.valueOf(scores.size()));

        getListAdapter().getListScores().addAll(scores);
    }

    @Override
    public void refreshAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getTxtEmptyView().setVisibility(getListAdapter().getListScores().isEmpty() ? View.VISIBLE : View.GONE);
                getListAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }


    private Toolbar getToolbar() {
        if (mToolbar == null)
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        return mToolbar;
    }

    private TextView getTxtEmptyView() {
        if (txtEmptyView == null)
            txtEmptyView = (TextView) findViewById(R.id.txtEmptyList);
        return txtEmptyView;
    }


    private LinearLayoutManager getLayoutManager() {
        if (mLayoutManager == null)
            mLayoutManager = new LinearLayoutManager(ScoreActivity.this);
        return mLayoutManager;
    }

    private RecyclerView getListServices() {
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.listScores);
            mRecyclerView.setLayoutManager(getLayoutManager());
        }
        return mRecyclerView;
    }

    private ScoreListAdapter getListAdapter() {
        if (mAdapter == null)
            mAdapter = new ScoreListAdapter(ScoreActivity.this);
        return mAdapter;
    }

}
