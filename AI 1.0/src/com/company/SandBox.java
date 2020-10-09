package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SandBox extends Application {

    public static final int X_GRID_COUNT = 40;
    public static final int Y_GRID_COUNT = 20;
    public static final int WIDTH_COUNT = 20;
    private static final Paint bgColor = Color.WHITE;
    private static final Paint cellColor = Color.BLACK;
    private static Pane root;
    private static ArrayList<Rectangle> rectangles;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth((X_GRID_COUNT+1)*WIDTH_COUNT);
        primaryStage.setHeight((Y_GRID_COUNT+1)*WIDTH_COUNT);
        primaryStage.setTitle("Standard AI");
        root = new Pane();
        rectangles = new ArrayList<>();
        root.getChildren().addAll(rectangles);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().removeAll(rectangles);
                update();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException ex){
                }
                root.getChildren().addAll(rectangles);
            }
        };
        timer.start();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launcher(){launch();}

    private static void update(){
        rectangles.removeAll(rectangles);
        System.out.println(World.cells.size());
        for(int i = 0; i < World.cells.size(); i++) {
            Cells cell = World.cells.get(i);
            Rectangle rect = new Rectangle(cell.x_pos*WIDTH_COUNT, cell.y_pos*WIDTH_COUNT,
                    WIDTH_COUNT, WIDTH_COUNT);
            rect.setFill(cellColor);
            rectangles.add(rect);
        }
        System.out.println("RECTS: "+rectangles.size());
        World.update();
    }
}
