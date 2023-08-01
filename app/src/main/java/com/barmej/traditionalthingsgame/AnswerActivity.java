package com.barmej.traditionalthingsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView answer_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        answer_text_view =(TextView) findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra("answer_description");
        if (answer != null){
            answer_text_view.setText(answer);
        }
    }
    public void returnclicked (View view){
        finish();
    }
}