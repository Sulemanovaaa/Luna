package entity;

import utils.ReflectionUtil;

public class FoodProperties {

    //Внешний вид
    private int view;
    //Острота
    private int keenness;
    //Соленость
    private int salinity;
    //Сладость
    private int sweetness;
    //Кислотность
    private int sourness;
    //Горький
    private int bitter;
    //Степень прожарки
    private int roasting;
    //Запах
    private int smell;

    public FoodProperties() {
        int defaultValue = 50;
        ReflectionUtil.setFieldsValueAsDefault(this, defaultValue);
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getKeenness() {
        return keenness;
    }

    public void setKeenness(int keenness) {
        this.keenness = keenness;
    }

    public int getSalinity() {
        return salinity;
    }

    public void setSalinity(int salinity) {
        this.salinity = salinity;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getSourness() {
        return sourness;
    }

    public void setSourness(int sourness) {
        this.sourness = sourness;
    }

    public int getBitter() {
        return bitter;
    }

    public void setBitter(int bitter) {
        this.bitter = bitter;
    }

    public int getRoasting() {
        return roasting;
    }

    public void setRoasting(int roasting) {
        this.roasting = roasting;
    }

    public int getSmell() {
        return smell;
    }

    public void setSmell(int smell) {
        this.smell = smell;
    }
}
