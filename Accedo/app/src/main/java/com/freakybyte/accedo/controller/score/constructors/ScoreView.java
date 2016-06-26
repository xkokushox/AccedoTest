package com.freakybyte.accedo.controller.score.constructors;

import com.freakybyte.accedo.model.ScoreModel;

import java.util.List;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public interface ScoreView {

    void setItemsToAdapter(List<ScoreModel> scores);

    void refreshAdapter();

}
