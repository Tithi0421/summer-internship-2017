package com.elitewaresolutions.krte.activity;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Tithi on 25-06-2017.
 */
public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
