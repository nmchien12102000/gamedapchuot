package com.example.hi.gamedapchuot;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("mouse.realm").schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
