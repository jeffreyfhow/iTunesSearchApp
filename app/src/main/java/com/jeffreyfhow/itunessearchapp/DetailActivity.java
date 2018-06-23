package com.jeffreyfhow.itunessearchapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeffreyfhow.itunessearchapp.utility.URLImageLoader;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE_ID = "titleID";
    public static final String EXTRA_DESC_ID = "descID";
    public static final String EXTRA_POSTER_ID = "posterID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE_ID);
        String desc = intent.getStringExtra(EXTRA_DESC_ID);
        String poster = intent.getStringExtra(EXTRA_POSTER_ID);

        TextView titleTextView = findViewById(R.id.detailTitle);
        TextView descTextView = findViewById(R.id.detailDescriptionText);
        ImageView posterImageView = findViewById(R.id.detailPosterImage);

        titleTextView.setText(title);
        descTextView.setText(desc);

        URLImageLoader.getInstance().loadImage(this, poster, posterImageView);
    }
}
