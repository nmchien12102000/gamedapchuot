package com.example.hi.gamedapchuot;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class HightScoreController {
    public static List<ScoreOBJS> getHightScore() {
        List<ScoreOBJS> list = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ScoreOBJS> realmResults = realm.where(ScoreOBJS.class).sort("Score", Sort.DESCENDING).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            if (i >= 10) break;
            ScoreOBJS scoreOBJS = realmResults.get(i);
            ScoreOBJS scoreOBJS1 = new ScoreOBJS();
            scoreOBJS1.setName(scoreOBJS.getName());
            scoreOBJS1.setScore(scoreOBJS.getScore());
            list.add(scoreOBJS1);
        }
        realm.commitTransaction();
        return list;
    }

    public static void addScore(ScoreOBJS scoreOBJS) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int index = 1;
        List<ScoreOBJS> list = realm.where(ScoreOBJS.class).findAll();
        if (list.size() > 0) {
            index = realm.where(ScoreOBJS.class).max("index").intValue() + 1;
            for (int i = list.size() - 1; i >= 0; i--) {
                if (i >= 10) {
                    list.get(i).deleteFromRealm();
                } else break;
            }
        }
        scoreOBJS.setIndex(index);
        realm.insertOrUpdate(scoreOBJS);
        realm.commitTransaction();
    }

    public static int getMinimumScore() {
        int score = 0;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ScoreOBJS> realmResults = realm.where(ScoreOBJS.class).sort("Score", Sort.DESCENDING).findAll();
        if (realmResults.size() > 0) {
            if (realmResults.size() > 9) {
                    score = realmResults.get(9).getScore();
            } else {
                score = realmResults.get(realmResults.size()-1).getScore();
            }
        }
        realm.commitTransaction();
        return score;
    }
}
