package com.example.hi.gamedapchuot;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScoreOBJS extends RealmObject {
    @PrimaryKey
    int index;
    String name;
    int Score;

    public int getIndex() {
        return index;
    }

    public ScoreOBJS setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getName() {
        return name == null?"No Name": name;
    }

    public ScoreOBJS setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return Score;
    }

    public ScoreOBJS setScore(int score) {
        Score = score;
        return this;
    }
}
