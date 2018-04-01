package com.example.sushmita.fbquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

      Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        int percent= score/4;
        if(percent < 1){
            ratingBar.setRating(percent);
        } else if(percent < 2){
            ratingBar.setRating(percent);
        }else if(percent < 3){
            ratingBar.setRating(percent);
        }else if(percent < 4){
            ratingBar.setRating(percent);
        }else if(percent < 5){
            ratingBar.setRating(percent);
        }else if(percent == 5){
        }        ratingBar.setRating(percent);


        scoreTxtView.setText(String.valueOf(score));

    }
}
