package com.jeffreyfhow.itunessearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private SearchScreen mSearchScreen;
    private SearchController mSearchController;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_favorite:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MovieListModel model = new MovieListModel();

        View mainContainer = findViewById(R.id.mainContainer);

        mSearchScreen = new SearchScreen(mainContainer);
        MovieListScreen listScreen = new MovieListScreen(this, mainContainer);
        listScreen.setRecyclerClickListener(new MovieResultAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Movie m = model.getMovieAt(position);
                goToDetailActivity(m.getTitle(), m.getImageUrl(), m.getDescription());
            }
        });

        model.registerObserver(listScreen);

        mSearchController = new SearchController(model);


    }

    public void onSearchButtonClicked(View view) {
        mSearchController.requestMovies(mSearchScreen.getSearchText(),50);
    }

    public void goToDetailActivity(String title, String posterUrl, String desc){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TITLE_ID, title);
        intent.putExtra(DetailActivity.EXTRA_POSTER_ID, posterUrl);
        intent.putExtra(DetailActivity.EXTRA_DESC_ID, desc);
        startActivity(intent);
    }


}
