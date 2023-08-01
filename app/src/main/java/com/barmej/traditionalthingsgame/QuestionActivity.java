package com.barmej.traditionalthingsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    int questions []=new int[]{
       R.drawable.img, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
           R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8, R.drawable.img_9,
           R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
    };
private ImageView questions_picture;
private String [] ANSWERS;
private String [] ANSEWRS_DESCRIPTION;
String  indexAnswers,indexanswers_description;
 int currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences( Constants.APP_PREF, MODE_PRIVATE);
        String appLang = sharedPreferences.getString(Constants.APP_LANG,"");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this,appLang);

        setContentView(R.layout.activity_main);
       questions_picture= (ImageView) findViewById(R.id.image_view_question);


       ANSWERS = getResources().getStringArray(R.array.answers);
       ANSEWRS_DESCRIPTION =getResources().getStringArray(R.array.answer_description);

       chaingeQuestion();
}
    private void  showLanguageDialogue(){
        AlertDialog alertdialogue = new AlertDialog.Builder(this)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Constants.APP_LANG, language);
                        editor.apply();

                        LocaleHelper.setLocale(QuestionActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create();
        alertdialogue.show();
    }
    public void showNewLanguage (View view){
        Toast.makeText(this,"do not use arabic for the first time shose any language than shose arabic",Toast.LENGTH_LONG).show();
        showLanguageDialogue();
    }
    public void chaingeQuestion(){
        Random random =new Random();
        int randomIndex =random.nextInt(questions.length);
        indexAnswers = ANSWERS[randomIndex];
        indexanswers_description =ANSEWRS_DESCRIPTION[randomIndex];
        currentQuestion = questions[randomIndex];
        questions_picture.setImageResource(currentQuestion);
    }
    public void showNewQuestion(View view){
        chaingeQuestion();
    }
    public void showAnswwer (View view) {
        Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
        intent.putExtra("answer_description", indexAnswers +":"+ indexanswers_description);
        startActivity(intent);
    }
    public void shareQuestion(View view){
        Intent intent = new Intent(QuestionActivity.this, ShareActivity.class);
        intent.putExtra("answer_description",currentQuestion );
        startActivity(intent);
    }
}