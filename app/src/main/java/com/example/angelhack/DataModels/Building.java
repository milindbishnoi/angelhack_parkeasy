package com.example.angelhack.DataModels;

import java.util.List;

public class Building {
    int[] floor1;
    int[] floor2;
    int[] floor3;
    int[] floor4;

    public Building(int[] floor1, int[] floor2, int[] floor3, int[] floor4) {
        this.floor1 = floor1;
        this.floor2 = floor2;
        this.floor3 = floor3;
        this.floor4 = floor4;
    }

    public int[] getFloor1() {
        return floor1;
    }

    public void setFloor1(int[] floor1) {
        this.floor1 = floor1;
    }

    public int[] getFloor2() {
        return floor2;
    }

    public void setFloor2(int[] floor2) {
        this.floor2 = floor2;
    }

    public int[] getFloor3() {
        return floor3;
    }

    public void setFloor3(int[] floor3) {
        this.floor3 = floor3;
    }

    public int[] getFloor4() {
        return floor4;
    }

    public void setFloor4(int[] floor4) {
        this.floor4 = floor4;
    }


}
