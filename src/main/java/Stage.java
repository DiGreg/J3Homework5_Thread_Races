/*
Суперкласс для этапов заезда (гонки)
 */
public abstract class Stage {
    protected int length;//длина этапа
    protected String description; //описание этапа

    public String getDescription() {
        return description;
    }
    public abstract void go(Car car);
}
