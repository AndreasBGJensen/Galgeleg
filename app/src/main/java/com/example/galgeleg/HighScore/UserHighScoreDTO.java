package com.example.galgeleg.HighScore;

public class UserHighScoreDTO {

    private String name;
    private int score;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserHighScoreDTO(String name, int score){
        this.name = name;
        this.score = score;

    }
}
