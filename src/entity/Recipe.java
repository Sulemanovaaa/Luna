package entity;

import java.util.*;

public class Recipe {

    private int id;
    private String name;
    private int cookingTime;
    private String fileName;

    private Map<Integer, List<Integer>> stages;

    /*
        STAGES - СОВОКУПНОСТЬ ШАГОВ И ДЕЙСТВИЙ
        STEPS - ШАГИ
        ACTIONS - ДЕЙСТВИЯ
        ПРЕДСТАВЛЯЕМ ТАК - Map<Step, List<Action>> stages
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<Integer, List<Integer>> getStages() {
        return stages;
    }

    public void setStages(Map<Integer, List<Integer>> stages) {
        this.stages = stages;
    }

    public List<Integer> getAllActionsIdInStep(int id) {
        return new ArrayList<>(stages.get(id));
    }

    public List<Integer> getAllStepsIdInStages() {
        return new ArrayList<>(stages.keySet());
    }
}
