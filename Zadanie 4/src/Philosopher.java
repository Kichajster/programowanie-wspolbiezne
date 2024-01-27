import java.util.concurrent.locks.Lock;
class Philosopher extends Thread {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String RESET = "\u001B[0m";

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            // wyświetlanie statusu brania/odkładania widelcy
            while (true) {
                think();
                leftFork.lock();
                System.out.println("Philosopher " + (id+1) + " picked up left fork.");
                Thread.sleep(1000);

                if (rightFork.tryLock()) {
                    try {
                        System.out.println("Philosopher " + (id+1) + " picked up right fork.");
                        eat();
                        Thread.sleep(1000);
                    } finally {
                        rightFork.unlock();
                        System.out.println("Philosopher " + (id+1) + " put down right fork.");
                        Thread.sleep(1000);
                    }
                }

                leftFork.unlock();
                System.out.println("Philosopher " + (id+1) + " put down left fork.");
                Thread.sleep(1000);
            }
        } // wyłapanie exception, które pozwala na wyświetlenie statusu myślących/jedzących filozofów
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //wyświetlanie filozofów, którzy myślą
    private void think() throws InterruptedException {
        System.out.println(YELLOW + "Philosopher " + (id+1) + " is thinking." + RESET);
        Thread.sleep(1000);
    }
    //wyświetlanie filozofów, którzy jedzą
    private void eat() throws InterruptedException {
        System.out.println(GREEN + "Philosopher " + (id+1) + " is eating." + RESET);
        Thread.sleep(1000);
    }
}