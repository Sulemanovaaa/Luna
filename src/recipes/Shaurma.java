package recipes;

import entity.Action;
import entity.Stage;

import java.util.ArrayList;

public class Shaurma {
    private String name = "Шаурма";
    private int time = 60;
    private ArrayList<Stage> stages;
    private ArrayList<Action> actions;

    public Shaurma() {
        stages = new ArrayList<>();
        stages.add(new Stage("Порезать овощи", 10));

    }
}
