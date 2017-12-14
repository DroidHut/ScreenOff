package me.andika.lockscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.andika.lockscreen.NewsApp.Article;
import me.andika.lockscreen.NewsApp.ArticleAdapter;
import me.andika.lockscreen.NewsApp.NewsApplication;
import me.andika.lockscreen.NewsApp.Parser;


public class LockScreenActivity extends AppCompatActivity {
private ArrayList<String> newsArray=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        UnlockBar unlock = (UnlockBar) findViewById(R.id.unlock);
        String [] urlArray=getResources().getStringArray(R.array.urltop);

        // Attach listener
        unlock.setOnUnlockListenerRight(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Right Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        unlock.setOnUnlockListenerLeft(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Left Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        
        for (int count = 0; count < urlArray.length; count++) {
            loadFeed(urlArray[count]);

        }
    }
    public List<Article> loadFeed(String news) {
        List<Article> newList=new ArrayList<>();
        final ArrayList<Article> arrayList=new ArrayList<>();

        Parser parser = new Parser();
        parser.execute(news);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(final ArrayList<Article> list) {

                    arrayList.addAll(list);
                   List newList=arrayList.subList(0,2);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(LockScreenActivity.this));
                    mAdapter= new ArticleAdapter(newList, R.layout.row, LockScreenActivity.this, mRecyclerView);
                    mRecyclerView.setAdapter(mAdapter);
                

            }

            @Override
            public void onError() {
                if(NewsApplication.isActivityVisible()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("Unable to load ", "articles");
                        }
                    });
                }
            }
        });
        
            return newList;
    }


    @Override
    public void onAttachedToWindow() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
//                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onAttachedToWindow();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LockApplication) getApplication()).lockScreenShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((LockApplication) getApplication()).lockScreenShow = false;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}
