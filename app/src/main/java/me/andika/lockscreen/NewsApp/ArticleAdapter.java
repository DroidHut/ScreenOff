package me.andika.lockscreen.NewsApp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.andika.lockscreen.R;


public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROG = 1;
    private int rowLayout;
    private List<Article> articles = new ArrayList<>();
    private Context mContext;
    private RecyclerView.ViewHolder vh;

    public ArticleAdapter(List<Article> list, int rowLayout, Context context, RecyclerView recyclerview) {
        this.articles = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return articles.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }

    @Override
    public long getItemId(int item) {
        // TODO Auto-generated method stub
        return item;
    }

    public void clearData() {
        if (articles != null)
            articles.clear();
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
            vh = new MyViewHolder(view);
        } else if (viewType == VIEW_PROG) {
           // View view = LayoutInflater.from(mContext).inflate(R.layout.loading_item, parent, false);
           // vh = new LoadingViewHolder(view);
        }
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            Article currentArticle = articles.get(position);

            String a = articles.get(position).getLink();
            if (isContain(a, "outlookhindi")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.outlook));
            } else if (isContain(a, "news18")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.news18));
            } else if (isContain(a, "bbc")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.bbc));
            } else if (isContain(a, "zeenews")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.zeenews));
            } else if (isContain(a, "aljazeera")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.aljazeera));
            } else if (isContain(a, "livemint")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.livemint));
            } else if (isContain(a, "outlookindia")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.outlook));
            } else if (isContain(a, "wired")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.wired));
            } else if (isContain(a, "bollywoodtrade")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.bollywoodtrade));
            } else if (isContain(a, "seattletimes")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.seattletimes));
            } else if (isContain(a, "economist")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.economist));
            } else if (isContain(a, "openthemagazine")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.openthemagazine));
            } else if (isContain(a, "ndtvmovies")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.ndtvmovies));
            } else if (isContain(a, "espncricinfo")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.espn));
            } else if (isContain(a, "businessinsider")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.businessinsider));
            } else if (isContain(a, "independent")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.independent));
            } else if (isContain(a, "forbesindia")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.forbesindia));
            } else if (isContain(a, "thehindubusinessline")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.hindubusiness));
            } else if (isContain(a, "time")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.time));
            } else if (isContain(a, "indiatvnews")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.indiatv));
            } else if (isContain(a, "abc")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.abcnews));
            } else if (isContain(a, "wral")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.wral));
            } else if (isContain(a, "newsnation")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.newsnation));
            } else if (isContain(a, "theguardian")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.guardian));
            } else if (isContain(a, "newsnation")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.newsnation));
            } else if (isContain(a, "hindustantimes")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.ht));
            } else if (isContain(a, "moneycontrol")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.moneycontrol));
            } else if (isContain(a, "timesofindia")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.toi));
            } else if (isContain(a, "economictimes")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.economictimes));
            } else if (isContain(a, "indianexpress")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.indianexpress));
            } else if (isContain(a, "financialexpress")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.financialexpress));
            } else if (isContain(a, "ndtv")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.ndtv));
            } else if (isContain(a, "business-standard")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.businessstandard));
            } else if (isContain(a, "khabarindiatv")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.indiatv));
            } else if (isContain(a, "dailypioneer")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.dailyppioneer));
            } else if (isContain(a, "cnbc")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.cnbc));
            } else if (isContain(a, "washingtonpost")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.washingtonpost));
            } else if (isContain(a, "indiatoday")) {
                myViewHolder.titlePaper.setText(mContext.getResources().getString(R.string.indiatoday));
            }
        
            else {
                myViewHolder.titlePaper.setText("");
            }

            Locale.setDefault(Locale.getDefault());
            Date date = currentArticle.getPubDate();
            try {
                if (date.equals(null)) {
                    myViewHolder.pubDate.setText("");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH : mm, dd MMM");
                    final String pubDateString = sdf.format(date);
                    myViewHolder.pubDate.setText(pubDateString);
                }
            } catch (NullPointerException e) {
            }

            myViewHolder.title.setText(currentArticle.getTitle());
        //    String description = currentArticle.getDescription();
            // myViewHolder.description.setText(currentArticle.getDescription());

      
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250);
                LinearLayout.LayoutParams paramstext = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramstext.leftMargin = 15;
                paramstext.weight = 1;
                params.weight = 1 + (1 / 10);
                myViewHolder.image.setLayoutParams(params);
            
            
            try {
                String image = currentArticle.getImage();
                if (image.isEmpty()) {
                    //Picasso.with(mContext).load(R.drawable.placeholder).placeholder(R.drawable.placeholder).fit().centerCrop().into(myViewHolder.image);
                } else if (!image.isEmpty()) {
                    myViewHolder.image.setVisibility(View.VISIBLE);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    requestOptions.centerCrop();
                    requestOptions.override(250, 250);
                    requestOptions.placeholder(Color.GRAY);
                    requestOptions.error(Color.YELLOW);
                    // requestOptions.override(140,110);

                    Glide.with(mContext).load(image)
                            .apply(requestOptions)
                            .into(myViewHolder.image);
                    //  Picasso.with(mContext).load(image).resize(130,100).placeholder(R.drawable.placeholder).into(myViewHolder.image);


                }
            } catch (NullPointerException e) {
                myViewHolder.image.setVisibility(View.GONE);
            }
        

        } else if (viewHolder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }


    private static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView titlePaper;
        TextView description;
        TextView pubDate;
        ImageView image;
        ImageView share;
        TextView category;
        TextView author;


        public MyViewHolder(View itemView) {
            super(itemView);

           // description = (TextView) itemView.findViewById(R.id.description);
            title = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.pubDate);
            image = (ImageView) itemView.findViewById(R.id.image);
            titlePaper = (TextView) itemView.findViewById(R.id.titlePaper);

        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
          //  progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
