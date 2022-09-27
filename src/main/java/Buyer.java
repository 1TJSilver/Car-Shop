public class Buyer extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Buyer " + Thread.currentThread().getName() + " came to the salon");
            synchronized (Main.cars){
                if (!Main.cars.isEmpty()) {
                    System.out.println("Buyer " + Thread.currentThread().getName() + " get new car: " +
                            Main.cars.get(0));
                    Main.cars.remove(0);
                    try {
                        Thread.sleep(Main.PURCHASE_TIME);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("No cars");
                    try {
                        Main.cars.wait();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
