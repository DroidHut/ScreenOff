package me.andika.lockscreen.NewsApp;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Parser extends AsyncTask<String, Void, String> implements Observer {

    private XMLParser xmlParser;
    private OnTaskCompleted onComplete;

    public Parser() {

        xmlParser = new XMLParser();
        xmlParser.addObserver(this);
    }

    public interface OnTaskCompleted {
        void onTaskCompleted(ArrayList<Article> list);

        void onError();
    }

    public void onFinish(OnTaskCompleted onComplete) {
        this.onComplete = onComplete;
    }


    @Override
    protected String doInBackground(String... ulr) {
        Response response;

        try {
            OkHttpClient client = new OkHttpClient();
            if (ulr == null) throw new IllegalArgumentException("Null url: " + ulr);
            Request request = new Request.Builder().url(ulr[0]).build();
            if (request == null) throw new IllegalArgumentException("unexpected url: " + ulr);

            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            onComplete.onError();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            xmlParser.parseXML(result);
            Log.i("RSS Parser ", "RSS parsed correctly!");
        } catch (Exception e) {
            e.printStackTrace();
            onComplete.onError();

        }
    }

   @Override
    @SuppressWarnings("unchecked")
    public void update(Observable observable, Object data) {

        ArrayList<Article>  articles = (ArrayList<Article>) data;
        onComplete.onTaskCompleted(articles);

    }

}
