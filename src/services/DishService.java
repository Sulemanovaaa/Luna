package services;

import entity.Dish;
import entity.DishDescription;
import utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.List;

public class DishService {

    private Dish dish;

    private StorageService storageService;

    public DishService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void start() {
        init();
    }

    public void init() {
        dish = new Dish();
        dish.init();
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String appendDishDescriptions() {
        StringBuilder output = new StringBuilder();
        for (Field field : dish.getFoodProperties().getClass().getDeclaredFields()) { // цикл полей блюда
            List<DishDescription> dishDescriptions = storageService.getDishDescriptionsByFieldName(field.getName()); // получаем границы и описания одного поля блюда
            for (DishDescription dishDescription : dishDescriptions) { // цикл сравнений
                if (ReflectionUtil.getFieldValueAsInt(dish.getFoodProperties(), field) <= dishDescription.getValue() && !dishDescription.getDescription().isEmpty()) { // сравниваем значение свойства блюда с границами из файла
                    output.append(dishDescription.getDescription()); // записываем описание по найденной границе
                    output.append("\n");
                    break;
                }
            }
        }
        return output.toString();
    }

    public String getDescriptionOfTheDish() {
        return "Вот что я приготовиль:\n" + appendDishDescriptions();
    }
}
