package com.chisw.newsapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {

        switch (item.getItemId()) {
            case R.id.navigation_news:
                mTextMessage.setText(R.string.title_news);
                return true;
            case R.id.navigation_weather:
                mTextMessage.setText(R.string.title_weather);
                return true;
            case R.id.navigation_saved:
                mTextMessage.setText(R.string.title_saved);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
