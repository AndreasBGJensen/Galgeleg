package com.example.galgeleg.Model.Spillogik.HighScore;

public class UserHighScoreDTO  implements Comparable   {

    private String name;
    private String score;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public UserHighScoreDTO(String name, String score){
        this.name = name;
        this.score = score;

    }

    public String toString(){
        return getName()+" score : " + getScore();
    }


//This code snip i from https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    @Override
    public int compareTo(Object o) {
        int compareage=Integer.parseInt(((UserHighScoreDTO)o).getScore());
        /* For Ascending order*/
        return compareage-Integer.parseInt(this.getScore());
    }
}
