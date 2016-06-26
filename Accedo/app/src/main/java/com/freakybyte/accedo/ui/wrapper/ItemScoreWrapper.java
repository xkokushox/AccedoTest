package com.freakybyte.accedo.ui.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.freakybyte.accedo.R;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ItemScoreWrapper extends RecyclerView.ViewHolder {

    public View view = null;

    private TextView txtScorePosition = null;
    private TextView txtScoreUser = null;
    private TextView txtScorePoints = null;


    public ItemScoreWrapper(View base) {
        super(base);
        this.view = base;
    }

    public TextView getTxtScorePosition() {
        if (txtScorePosition == null)
            txtScorePosition = (TextView) view.findViewById(R.id.txtScorePosition);
        return txtScorePosition;
    }

    public TextView getTxtScoreUser() {
        if (txtScoreUser == null)
            txtScoreUser = (TextView) view.findViewById(R.id.txtScoreUser);
        return txtScoreUser;
    }

    public TextView getTxtScorePoints() {
        if (txtScorePoints == null)
            txtScorePoints = (TextView) view.findViewById(R.id.txtScorePoints);
        return txtScorePoints;
    }


}
