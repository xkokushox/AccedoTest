package com.freakybyte.accedo.model;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ScoreModel {

    private int position;
    private String name;
    private int score;

    public ScoreModel(){

    }

    public ScoreModel(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
