package com.mariontardiveau.applicationnumber1.model;

import java.util.Collections;
import java.util.List;

/**
 * @author M.Tardiveau @version: 1.0
 */
public class QuestionBank  {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

//___________________Constructor___________________________________________________________________

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;
        //melange la liste de questions avant de la stocker
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex=0;
    }

//___________________Getter___________________________________________________________________


    public Question getQuestion(){
        //passez en revue les questions et retournez en une nouvelle à chaque appel
        if(mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }

        return mQuestionList.get(mNextQuestionIndex++);


    }



}




