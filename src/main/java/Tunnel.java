/*
Этап - Тоннель
 */
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore = new Semaphore(2);//семафор для тоннеля - не более 2-х машин в тоннель за раз

    //Конструктор
    public Tunnel(){
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    //переопределение метода "go" ("проедь") для тоннеля
    @Override
    public void go (Car car){
        //try{
            try{
                System.out.println(car.getName() + " готовится к этапу (ждёт): " + description);
                semaphore.acquire();//запускаю семафор

                System.out.println(car.getName() + " начал этап: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() + " закончил этап: " + description);
                semaphore.release();//освобождаю поток - в тоннеле появилось место для ожидающей машины
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
