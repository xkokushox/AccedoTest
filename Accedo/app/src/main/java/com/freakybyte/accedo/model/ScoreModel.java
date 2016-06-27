package com.freakybyte.accedo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ScoreModel implements Parcelable {

    public static final String TAG = "ScoreModel";

    private int position;
    private String name;
    private int score;

    public ScoreModel() {

    }

    public ScoreModel(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Student data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeString(name);
        dest.writeInt(score);
    }

    private ScoreModel(Parcel in) {
        this.position = in.readInt();
        this.name = in.readString();
        this.score = in.readInt();
    }

    public static final Parcelable.Creator<ScoreModel> CREATOR = new Parcelable.Creator<ScoreModel>() {

        @Override
        public ScoreModel createFromParcel(Parcel source) {
            return new ScoreModel(source);
        }

        @Override
        public ScoreModel[] newArray(int size) {
            return new ScoreModel[size];
        }
    };


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
