package com.example.angelhack.DataModels;

public class globalVars {
    public static double currLat;
    public static double currLon;

    public static double getCurrLat() {
        return currLat;
    }

    public static void setCurrLat(double currLat) {
        globalVars.currLat = currLat;
    }

    public static double getCurrLon() {
        return currLon;
    }

    public static void setCurrLon(double currLon) {
        globalVars.currLon = currLon;
    }
}