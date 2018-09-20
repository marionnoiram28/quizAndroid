package com.mariontardiveau.applicationnumber1.model;

import java.util.List;

/**
 * @author M.Tardiveau @version: 1.0
 */
public class Question {
    //la question
    private String mQuestion;
    //liste des réponses proposées
    private List<String> mChoiceList;
    //index de la réponse de la liste mChoiceList
    private int mAnswerIndex;

    //___________________________constructeur_____________________________________________________

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    //___________________________Get/setters______________________________________________________


    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if(choiceList == null){
            throw new IllegalArgumentException("Array cannot be nul");
        }
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if(answerIndex < 0 || answerIndex >= mChoiceList.size()){
            throw new IllegalArgumentException("Answer index is out of bound");
        }

        mAnswerIndex = answerIndex;
    }

    //__________________________toString___________________________________________________________


    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
