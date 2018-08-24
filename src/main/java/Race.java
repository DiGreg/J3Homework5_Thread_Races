/*
Класс формирования заезда (гонки) из этапов
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;//объявлеие ссылки на список этапов

    //Конструктор заезда Race с аргументом переменной длины (в качестве параметра конструктору м.б. передан массив, либо один элемент):
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));//метод asList() формирует список этапов на основе массива этапов
    }
    //Геттер списка этапов заезда
    public ArrayList<Stage> getStages() {
        return stages;
    }
}
