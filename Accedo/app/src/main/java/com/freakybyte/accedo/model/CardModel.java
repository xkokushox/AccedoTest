package com.freakybyte.accedo.model;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class CardModel {
    private int id;
    private boolean matched = false;
    private boolean flipped = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
