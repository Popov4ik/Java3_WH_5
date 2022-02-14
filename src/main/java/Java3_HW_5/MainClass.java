package Java3_HW_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {

        CountDownLatch startRace = new CountDownLatch(CARS_COUNT); // щелчек для начала гонки

        CountDownLatch finishRace = new CountDownLatch(CARS_COUNT); // щелчек для конца гонки

        CyclicBarrier readyToRace = new CyclicBarrier(CARS_COUNT); // барьер для ожидания готовности

        Semaphore smp = new Semaphore(CARS_COUNT/2);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка к гонке!!!");
        Race race = new Race(new Road(60), new Tunnel(smp), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), startRace, finishRace, readyToRace);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            startRace.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> ГОНКА НАЧАЛАСЬ!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            finishRace.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> ГОНКА ЗАВЕРШЕНА!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}