public class Main {

    public static void main(String[] args) {
        final int ThreadCount = 5;

        for (int i = 1; i <= ThreadCount; i++) {
            Thread Thread = new Thread(new MyThread("Thread " + i));
            Thread.start();
        }
    }
}

class MyThread implements Runnable {
    private String ThreadName;

    public MyThread(String ThreadName) {
        this.ThreadName = ThreadName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(ThreadName + " is working.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
