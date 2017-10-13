package entity;


import java.util.List;

public class Stage {

    private String name;
    private int time;

    private List<Integer> actions;

    public Stage(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Integer> getActions() {
        return actions;
    }

    public void setActions(List<Integer> actions) {
        this.actions = actions;
    }
}
