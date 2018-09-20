package com.mariontardiveau.applicationnumber1.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mariontardiveau.applicationnumber1.R;
import com.mariontardiveau.applicationnumber1.model.User;

/**
 * @author M.Tardiveau @version: 1.0
 */
public class MainActivity extends AppCompatActivity {

    private TextView mGreetinText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    //identifiant du jeu =50
    public static final int GAME_ACTIVITY_REQUEST_CODE = 50;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE ="PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("GameActivity::onCreate()");

        mUser=new User();

        mPreferences =getPreferences(MODE_PRIVATE);


        mGreetinText=(TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput= (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton= (Button) findViewById(R.id.activity_main_play_btn);

       mPlayButton.setEnabled(false);

       greetUser();

       mNameInput.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() !=0);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
        /**
         *
         */
        //click events
       mPlayButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String firstname = mNameInput.getText().toString();
               mUser.setFirstname(firstname);

               mPreferences.edit().putString("PREF_KEY_FIRSTNAME", mUser.getFirstname()).apply();
                //user click the button
               Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
               startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE );
           }
       });

    }
    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    //get the score data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            //Fetch the score from the intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);

            mPreferences.edit().putInt("PREF_KEY_SCORE", score).apply();

            greetUser();
        }
    }
    /**
     *
     */
    //display score and firstname when the user has already played
    private void greetUser(){
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

        if(null != firstname){
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Bonjour " + firstname + ",\nton dernier score était de "+score+ " points. Pourras tu faire mieux ?";
            mGreetinText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
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
