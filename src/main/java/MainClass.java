import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_QTY = 4;//количество машин в заезде - поменял имя с неудачного CARS_COUNT

    /*
    CyclicBarrier - счётчик потоков, готовых к выполнению.
    Здесь применён особый конструктор CyclicBarrier() с действием barrierAction = new BarrierAction(),
    которое выполняется по готовности всех потоков к дальнейшему исполнению.
    Как только 4 машины готовы к старту, напечатается сообщение "Гонка началась!".
    */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_QTY, new BarrierAction());

    /*
    Вложенный Runnable класс для выполнения действия в cyclicBarrier,
    когда все потоки (машины) соберутся в одной временной точке для дальнейшего выполнения (старта по готовности)
     */
    public static class BarrierAction implements Runnable{
        @Override
        public void run() {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

    //Счётчик выполненных потоков - для отслеживания завершения гонки машинами:
    static CountDownLatch cdl = new CountDownLatch(CARS_QTY);//т.к. не public, то доступен только для классов в текущем пакете

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));//формирование заезда из этапов

        Car[] cars = new Car[CARS_QTY]; //объявление массива для 4-х машин

        //создание машин/ заполнение массива:
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int)Math.random() * 10);
        }
        //старт потоков-машин:
        for (int i = 0; i <cars.length; i++) {
            new Thread(cars[i]).start();
        }

        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            cdl.await();//ожидаю выполнения всех потоков из группы, пока счётчик не приравняется 0, и стартую основной
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
