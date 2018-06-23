package com.jeffreyfhow.itunessearchapp;

import android.view.View;
import android.widget.EditText;

public class SearchScreen {
    public EditText editText;

    public SearchScreen(View parent){
       editText = parent.findViewById(R.id.searchText);
    }

    public String getSearchText(){
        return editText
                .getText()
                .toString()
                .replaceAll("\\s","+");
    }

}
