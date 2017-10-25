package services;

import com.google.gson.reflect.TypeToken;
import entity.Action;
import entity.Reaction;
import entity.Step;
import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class StorageService {

    private Map<Integer, Step> steps;
    private Map<Integer, Action> actions;
    private Map<Integer, Reaction> reactions;

    public void init() {
        steps = new HashMap<>();
        actions = new HashMap<>();
        reactions = new HashMap<>();
    }

    public boolean loadData() {
        if (steps != null && actions != null && reactions != null) {
            steps = JsonUtil.loadGenericMap(new TypeToken<Map<Integer, Step>>() {
            }.getType(), DirectoryUtil.STEPS_PATH);
            actions = JsonUtil.loadGenericMap(new TypeToken<Map<Integer, Action>>() {
            }.getType(), DirectoryUtil.ACTIONS_PATH);
            reactions = JsonUtil.loadGenericMap(new TypeToken<Map<Integer, Reaction>>() {
            }.getType(), DirectoryUtil.REACTIONS_PATH);
            return true;
        }
        return false;
    }



    public Step getStepById(int id) {
        return steps.get(id);
    }

    public Action getActionById(int id) {
        return actions.get(id);
    }

    public Reaction getReactionById(int id) {
        return reactions.get(id);
    }



    public Map<Integer, Step> getSteps() {
        return steps;
    }

    public void setSteps(Map<Integer, Step> steps) {
        this.steps = steps;
    }

    public Map<Integer, Action> getActions() {
        return actions;
    }

    public void setActions(Map<Integer, Action> actions) {
        this.actions = actions;
    }

    public Map<Integer, Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Map<Integer, Reaction> reactions) {
        this.reactions = reactions;
    }
}
