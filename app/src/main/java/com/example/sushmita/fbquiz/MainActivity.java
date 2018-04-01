package com.example.sushmita.fbquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;



public class MainActivity extends AppCompatActivity {
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mExit;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    private Firebase mQRef,mC1Ref,mC2Ref,mC3Ref,mAnsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mScoreView= findViewById(R.id.score);
        mQuestionView= findViewById(R.id.question);

        mButtonChoice1= findViewById(R.id.choice1);
        mButtonChoice2= findViewById(R.id.choice2);
        mButtonChoice3= findViewById(R.id.choice3);
        mExit= findViewById(R.id.quit);
        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore=mScore+4;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    mScore=mScore-1;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore=mScore+4;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    mScore=mScore-1;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore=mScore+4;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    updateQuestion();
                }
                else{
                    mScore=mScore-1;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();

                    updateQuestion();

                }
            }
        });

     mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Result();
            }
        });

    }
public void Result(){

    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
    Bundle b = new Bundle();
    b.putInt("score",mScore);
    intent.putExtras(b);
    startActivity(intent);
    finish();
}

    private void updateScore(int score){
        mScoreView.setText("" + mScore);
        Log.d("Score", "Your score: "+score);
    }
    public void updateQuestion(){
        mQRef= new Firebase("https://quiz-5857b.firebaseio.com/"+ mQuestionNumber +"/question");
        mQRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                mQuestionView.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mC1Ref= new Firebase("https://quiz-5857b.firebaseio.com/"+ mQuestionNumber +"/c1");
        mC1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice= dataSnapshot.getValue(String.class);
                mButtonChoice1.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mC2Ref= new Firebase("https://quiz-5857b.firebaseio.com/"+ mQuestionNumber +"/c2");
        mC2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice= dataSnapshot.getValue(String.class);
                mButtonChoice2.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mC3Ref= new Firebase("https://quiz-5857b.firebaseio.com/"+ mQuestionNumber +"/c3");
        mC3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice= dataSnapshot.getValue(String.class);
                mButtonChoice3.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAnsRef= new Firebase("https://quiz-5857b.firebaseio.com/"+ mQuestionNumber +"/ans");
        mAnsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAnswer = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        if(mQuestionNumber>4)
        {
            Result();
        }
        else
        mQuestionNumber++;

    }
}
