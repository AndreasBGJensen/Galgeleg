package com.example.galgeleg.Model;


/*
This class holds information about player mode, ie. if it is one or twoplayer mode.
This could also have been implementet as a State Pattern or as SheredPreferences wich would be better.
But due to lac of time i had to implement it like this.
 */



public class Settings {

    private static boolean twoPlayerMode = false;


    public void setTwoPlayerMode(Boolean twoPlayerMode) {
        this.twoPlayerMode = twoPlayerMode;
    }


    public boolean isTwoplayer(){
        return twoPlayerMode;
    }


    public void printState(){
        System.out.println("Two player mode is :" + twoPlayerMode);
    }

}
