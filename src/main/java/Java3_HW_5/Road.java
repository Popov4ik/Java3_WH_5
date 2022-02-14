package Java3_HW_5;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога длиною " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description + " пройдена!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

