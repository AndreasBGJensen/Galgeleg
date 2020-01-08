package com.example.galgeleg.Model.Spillogik.HighScore;

public class CalculatScore {

    private int currentScore;
    private int guesses;
    private int wordLength;

    private int[] scoreList = {10,9,8,7,6,5,4,3,2,1,0};

    /*
    param: The current score of the playing user, the number of guesses, and the length of the word to guess

     */

    public CalculatScore(String currentScore, String newScorePoints, int wordLength){
        this.currentScore=Integer.parseInt(currentScore);
        if(newScorePoints==null){
            newScorePoints="0";
        }
        this.guesses = Integer.parseInt(newScorePoints);
        this.wordLength = wordLength;
    }

    public String getNewScore(){
        String newScore = Integer.toString(calculateNewScore());
    return newScore;
    }

    private int calculateNewScore(){

        if(guesses<10){

            int scoreIndex = guesses-wordLength;
            //To avoid index out of bound. We will not get more than 6 guesses above due to the game will be lost before.
            if(scoreIndex<0){
                scoreIndex = 0;
            }

            return scoreList[scoreIndex] + currentScore;
        }

        return currentScore;
    }





    }
