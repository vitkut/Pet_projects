package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {

    public static ArrayList<Cells> cells = new ArrayList<>();
    public static ArrayList<Cells> newCells = new ArrayList<>();

    public World(){

    }

    public static void create(){

        cells.add(new Cells(9, 9));
        cells.add(new Cells(10, 9));
        cells.add(new Cells(9, 8));
        cells.add(new Cells(10, 8));
        cells.add(new Cells(11, 8));

    }

    public static void update(){
        newCells.removeAll(newCells);
        for(int i = 0; i < cells.size(); i++){
            checkNearest(cells.get(i));
        }
        for(int i = 0; i < newCells.size(); i++){
            if(i < cells.size()){
                cells.set(i, newCells.get(i));
            } else {
                cells.add(newCells.get(i));
            }
        }
    }

    public static void checkNearest(Cells cell){
        double degree = 0;
        int x, y, x_empty = 0, y_empty = 0;
        int countNear = 0;
        for(int i = 0; i < 8; i++){
            x = cell.x_pos + (int) Math.round(Math.sin(Math.toRadians(degree)));
            y = cell.y_pos + (int) Math.round(Math.cos(Math.toRadians(degree)));

            if(x > 0 && y > 0 && x < SandBox.X_GRID_COUNT && y < SandBox.Y_GRID_COUNT){
                if(checkByCoordinates(x, y)){
                    countNear++;
                } else {
                    if(x_empty == 0 && y_empty == 0){
                        x_empty = x;
                        y_empty = y;
                    }
                }
            }
            degree += 45.0d;
        }
        if(countNear > 2 && countNear < 5){
            cell.create(x_empty, y_empty);
            newCells.add(cell);
        }
    }

    public static boolean checkByCoordinates(int x, int y){
        for(int i = 0; i < cells.size(); i++){
            if(cells.get(i).x_pos == x && cells.get(i).y_pos == y){
                return true;
            }
        }
        return false;
    }
}
