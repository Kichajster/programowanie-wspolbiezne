public class Main {
    public static void main(String[] args) {
        // Tworzenie wątków
        int numOfThreads = 5;
        int sum = 0;
        Thread[] threads = new Thread[numOfThreads];

        // Oczekiwanie na zakończenie wszystkich wątków
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new WorkerThread(i + 1); // Przekazujemy numer wątku
            threads[i].start(); // Uruchamiamy wątek
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Sumowanie wyników
            WorkerThread thread = (WorkerThread) threads[i];
            sum += thread.getResult();
        }

        // Wyświetlanie wyniku
        System.out.println("Sum of all threads: " + sum);
    }
}

class WorkerThread extends Thread {
    private final int threadNumber;
    private int result;

    public WorkerThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        // Symulacja obliczeń w każdym wątku (dodawanie dwóch liczb)
        int operand1 = 2 * threadNumber;
        int operand2 = 3 * threadNumber;
        result = operand1 + operand2;

        // Wyświetlanie wyniku obliczeń w danym wątku
        System.out.println("Thread " + threadNumber + ": " + operand1 + " + " + operand2 + " = " + result);
    }

    public int getResult() {
        return result;
    }
}
