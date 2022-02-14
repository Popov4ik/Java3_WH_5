package Java3_HW_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch startRace;
    private CountDownLatch finishRace;
    private CyclicBarrier readyToRace;
    private static boolean RaceWinner;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch startRace, CountDownLatch finishRace, CyclicBarrier readyToRace) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.startRace = startRace;
        this.finishRace = finishRace;
        this.readyToRace = readyToRace;
    }

    private static synchronized void checkWin(Car car) {
        if (!RaceWinner) {
            System.out.println(car.getName() + " ПОБЕДИЛ В ГОНКЕ !!!!!!!");
            RaceWinner = true;
        }
    }

    @Override
    public void run() {
        try {
            RaceWinner = false;
            System.out.println(this.name + " готовится к гонке.");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов к гонке!");
            startRace.countDown();
            readyToRace.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        checkWin(this);

        finishRace.countDown();
    }
}