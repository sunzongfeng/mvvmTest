package com.example.ht2015.test.group.model;

import android.content.res.Resources;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.view.View;

import com.example.ht2015.test.login.activity.LoginActivity;
import com.example.ht2015.test.R;

import java.util.Random;

public class ClassModel {
    public ObservableField<String> mGroupName = new ObservableField<>();
    private boolean mIsLoaded;
    private Resources mResources;

    public ClassModel(Resources resources) {
        mResources = resources; // You might want to abstract this for testability
        init();
    }

    public boolean isLoaded() {
        return mIsLoaded;
    }

    public String getmGroupName() {
        return mGroupName.get();
    }

    public void setmGroupName(String GroupName) {
        mGroupName.set(GroupName);
    }

    private void init() {
        mGroupName.set("15班");
    }

    public void loadAsync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {Thread.sleep(2000);} catch (Exception ex) {};
                mIsLoaded = true;
                return null;
            }
        }.execute((Void) null);
    }
}
