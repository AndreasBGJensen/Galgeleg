package com.example.galgeleg.Model.Spillogik.HighScore;

public class CalculatScore {

    private int currentScore;
    private int newScore;

    private int[] scoreList = {10,9,8,7,6,5,4,3,2,1,0};

    //TODO: Change this highScore so that it only uses the guesses that are longer than the length of the words.

    public CalculatScore(String currentScore, String newScorePoints){
        this.currentScore=Integer.parseInt(currentScore);
        if(newScorePoints==null){
            newScorePoints="0";
        }
        this.newScore = Integer.parseInt(newScorePoints);
    }

    public String getNewScore(){
        String newScore = Integer.toString(calculateNewScore());
    return newScore;
    }

    private int calculateNewScore(){

        if(newScore<10){

            return scoreList[newScore] + currentScore;
        }

        return currentScore;
    }





    }
