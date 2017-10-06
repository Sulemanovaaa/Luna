package entity;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private int time;
    private ArrayList<Stage> stages;

    public Recipe(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }
}
