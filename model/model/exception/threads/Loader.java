package threads;

public class Loader implements Runnable {

    @Override
    public void run() {
        try {
            System.out.print("Loading");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(350);
                System.out.print(".");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Loading interrupted!");
        }
    }
}
