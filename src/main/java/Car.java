/*
Автомобиль для заезда
 */

public class Car implements Runnable{
    /*
    Счётчик номера участника - используется только в классе Car.
    И вдобавок - это, по факту, счётчик машин, ещё не завершивших гонку
     */
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }
    private Race race;//ссылка на заезд (гонку), в котором будет участвовать машина
    private int speed;
    private String name; //имя участника/машины

    //Конструктор для машины:
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;//инкремент счётчика участника при каждом создании экземпляра машины
        this.name = "Участник #" + CARS_COUNT;//инициализация имени участника/машины
    }

    //Геттеры:
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    //метод run() машины:
    @Override
    public void run() {
        try{
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)Math.random() * 800);//рандомное время на подготовку машины
            System.out.println(this.name + " готов");
            MainClass.cyclicBarrier.await();//жду готовности всех потоков
        } catch (Exception e){
            e.printStackTrace();
        }

        //прохождение по этапам гонки машины:
        for(int i = 0; i < race.getStages().size(); i++){
            //получение i-го элемента списка этапов гонки "race" и запуск для него метода "go" с текущим объектом машины
            race.getStages().get(i).go(this);
            if (i == 2) {
                CARS_COUNT--;//уменьшаю счётчик машин не прошедших гонку
                if (CARS_COUNT == 3) System.out.println(this.getName() + " WIN!!! ПОБЕДИТЕЛЬ!");
            }
        }
        MainClass.cdl.countDown();//как только поток выполнится счётчик CountDownLatch уменьшится на 1
    }
}
