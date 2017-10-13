package entity;

import java.util.List;

public class Recipe {

    private String name;
    //private int time;

    private List<Stage> stages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
*/
    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
