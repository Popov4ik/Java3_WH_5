package Java3_HW_5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore smp;
    public Tunnel(Semaphore smp) {
        this.length = 80;
        this.description = "Тоннель длиною " + length + " метров";
        this.smp = smp;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description + " пройден!");
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
