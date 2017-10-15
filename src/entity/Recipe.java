package entity;

import java.util.*;

public class Recipe {

    private String name;
    private int time;

    private Map<Integer, List<Integer>> stages;

    /*
        STAGES - СОВОКУПНОСТЬ ШАГОВ И ДЕЙСТВИЙ
        STEPS - ШАГИ
        ACTIONS - ДЕЙСТВИЯ
        ПРЕДСТАВЛЯЙ ТАК - Map<Step, List<Action> stages
     */

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

    public Map<Integer, List<Integer>> getStages() {
        return stages;
    }

    public void setStages(Map<Integer, List<Integer>> stages) {
        this.stages = stages;
    }

    public List<Integer> getAllActionsInStep(int id) {
        return new ArrayList<>(stages.get(id));
    }

    public List<Integer> getAllStepsInStages() {
        return new ArrayList<>(stages.keySet());
    }
}
