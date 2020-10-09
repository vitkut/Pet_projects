package com.company;

public class Cells {

    public int x_pos;
    public int y_pos;

    public Cells(int x, int y){
        this.x_pos = x;
        this.y_pos = y;
    }

    public void create(int x, int y){
        Cells cell = new Cells(x, y);
        World.newCells.add(cell);
    }

}
