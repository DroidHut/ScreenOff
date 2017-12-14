package me.andika.lockscreen.NewsApp;

import android.app.Application;

public class NewsApplication extends Application {

    private static NewsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized NewsApplication getInstance() {
        return mInstance;
    }



    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}