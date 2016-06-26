package com.freakybyte.accedo.controller.score.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freakybyte.accedo.R;
import com.freakybyte.accedo.model.ScoreModel;
import com.freakybyte.accedo.ui.wrapper.ItemScoreWrapper;

import java.util.ArrayList;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ScoreListAdapter extends RecyclerView.Adapter<ItemScoreWrapper> {

    public static final String TAG = "ScoreListAdapter";
    private ArrayList<ScoreModel> aListScore = new ArrayList<>();
    private Activity mActivity;

    /**
     * @param context
     */
    public ScoreListAdapter(Activity context) {
        this.mActivity = context;
    }

    @Override
    public ItemScoreWrapper onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_score, parent, false);
        ItemScoreWrapper vh = new ItemScoreWrapper(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ItemScoreWrapper viewHolder, final int position) {
        final ScoreModel mScore = getListScores().get(position);

        viewHolder.getTxtScorePosition().setText(String.valueOf(mScore.getPosition()));
        viewHolder.getTxtScoreUser().setText(mScore.getName());
        viewHolder.getTxtScorePoints().setText(String.valueOf(mScore.getScore()));

    }


    @Override
    public int getItemCount() {
        return getListScores().size();
    }

    public ArrayList<ScoreModel> getListScores() {
        return aListScore;
    }

}
