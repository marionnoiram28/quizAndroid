package com.mariontardiveau.applicationnumber1.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mariontardiveau.applicationnumber1.R;
import com.mariontardiveau.applicationnumber1.model.Question;
import com.mariontardiveau.applicationnumber1.model.QuestionBank;

import java.util.Arrays;

/**
 * @author M.Tardiveau Version: 1.0
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mQuestionText;
    private Button mAnswer1Btn;
    private Button mAnswer2Btn;
    private Button mAnswer3Btn;
    private Button mAnswer4Btn;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private  int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE ="BUNDLE_EXTRA_SCORE" ;
    public static final String BUNDLE_STATE_SCORE="currentScore";
    public static final String BUNDLE_STATE_QUESTION="currentQuestion";

    private boolean mEnableTouchEvents;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        if(savedInstanceState != null){
            mScore=savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions=savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore=0;
            mNumberOfQuestions=10;
        }

       mEnableTouchEvents = true;

        //Wire widgets
        mQuestionText= (TextView) findViewById(R.id.activity_game_question_txt);
        mAnswer1Btn= (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswer2Btn=(Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswer3Btn=(Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswer4Btn=(Button) findViewById(R.id.activity_game_answer4_btn);

        //use the tag property to name the buttons
        mAnswer1Btn.setTag(0);
        mAnswer2Btn.setTag(1);
        mAnswer3Btn.setTag(2);
        mAnswer4Btn.setTag(3);

        mAnswer1Btn.setOnClickListener(this);
        mAnswer2Btn.setOnClickListener(this);
        mAnswer3Btn.setOnClickListener(this);
        mAnswer4Btn.setOnClickListener(this);


        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }
    /**
     *
     * @param outState
     */
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }
    /**
     *
     * @param v
     */
    //click events
    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            //good answer
            Toast.makeText(this, "Bravo ! ", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            //wrong answer
            Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                //if this is the last question, ends the game.
                //else display the next question
                if (--mNumberOfQuestions == 0){
                    //end the game
                    endGame();
                }else{
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }

            }
        }, 2000); //Lenght short is usually 2 second long

    }
    /**
     *
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent ev){
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }
    /**
     *
     */
    //when the game is over
    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bien joué!")
                .setMessage("Ton score est de " + mScore+ "/10")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //end of the activity
                        Intent intent= new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     *
     * @param question
     */
    //associate an index with each answer
    private void displayQuestion (final Question question){
        mQuestionText.setText(question.getQuestion());
        mAnswer1Btn.setText(question.getChoiceList().get(0));
        mAnswer2Btn.setText(question.getChoiceList().get(1));
        mAnswer3Btn.setText(question.getChoiceList().get(2));
        mAnswer4Btn.setText(question.getChoiceList().get(3));
    }

    /**
     *
     * @return
     */
    private QuestionBank generateQuestions(){
        Question question1 = new Question("Stella a 16 tenues elle en vends 8. Combien lui reste-il de tenues?",
                Arrays.asList(
                        "15",
                        "8",
                        "7",
                        "5"),
        1);

        Question question2 = new Question("Tecna est la fée des tecnologies. Trouve le langage informatique caché dans cette liste: ",
                Arrays.asList(
                        "Sangoku",
                        "PC",
                        "Java",
                        "Android"),
                2);

        Question question3 = new Question("Bloom doit compter le nombre d'élèves. Il y'a 5 classes de 15 élèves : ",
                Arrays.asList(
                        "35",
                        "29",
                        "81",
                        "75"),
                3);

        Question question4 = new Question("Flora a trouvé 20 roses magiques. Il ya 4 vases. Combien de roses elle pourra mettre par vase? ",
                Arrays.asList(
                        "5",
                        "2",
                        "10",
                        "16"),
                0);
        Question question5 = new Question("Malaury veut faire un gateau au chocolat.Trouvez l'intrus dans la liste des ingrédients?",
                Arrays.asList(
                        "Chocolat",
                        "Pomme",
                        "Oeufs",
                        "Sucre"),
                1);
        Question question6 = new Question("Quel animal n'est pas un mammiphère?",
                Arrays.asList(
                        "Baleine",
                        "Fourmi",
                        "Chèvre",
                        "Biche"),
                1);
        Question question7 = new Question("Hello what's your name?",
                Arrays.asList(
                        "Me llamo Malaury.",
                        "I'm fine thanks you.",
                        "My name is Malaury ",
                        "I don't understand"),
                2);
        Question question8 = new Question("Quel mot ne veut pas dire je t'aime? ",
                Arrays.asList(
                        "I love you",
                        "mo content toi",
                        "anh yêu em",
                        "yako"),
                3);

        Question question9 = new Question("Idriss a fait 6 bêtises chaque jour pendant une semaine. Combien en a t'il fait dans la semaine? ",
                Arrays.asList(
                        "45",
                        "19",
                        "36",
                        "42"),
                3);
        Question question10 = new Question("Le spectacle de l'école du cirque a duré 90 minutes. Il a donc duré",
                Arrays.asList(
                        "1h30",
                        "45mn",
                        "1h15",
                        "2h"),
                0);
        Question question11 = new Question("Qui jouait divinement bien du clavecin?",
                Arrays.asList(
                        "Black M",
                        "Wolfgang Amadeus Mozart",
                        "Oshi",
                        "Rimbaud"),
                1);



        return new QuestionBank(Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11));


    }
    /**
     *
     */
    //trace activity
    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }
}
