package com.example.galgeleg.Model.Spillogik;

/*
Singleton for Galgelogik
 */

public class WrapperGalgelogik {

    static Galgelogik galgelogik = null;

    private WrapperGalgelogik(){}

    public static Galgelogik getInstance(){

        if (galgelogik == null)
            galgelogik = new Galgelogik();
        return galgelogik;
    }
}
