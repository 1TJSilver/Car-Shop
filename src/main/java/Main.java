import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int PREPARATION_TIME = 3000;
        final int PURCHASE_TIME = 2000;
        List<String> cars = new ArrayList<>();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                synchronized (cars){
                    cars.add("Toyota");
                    System.out.println("Seller make new car: " + cars.get(0));
                    cars.notify();
                    try {
                        Thread.sleep(PREPARATION_TIME);
                    } catch (InterruptedException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }).start();

        Runnable buyer = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Buyer " + Thread.currentThread().getName() + " came to the salon");
                synchronized (cars){
                    if (!cars.isEmpty()) {
                        System.out.println("Buyer " + Thread.currentThread().getName() + " get new car: " +
                                cars.get(0));
                        cars.remove(0);
                        try {
                            Thread.sleep(PURCHASE_TIME);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("No cars");
                        try {
                            cars.wait();
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        };
        new Thread(buyer).start();
        new Thread(buyer).start();
        new Thread(buyer).start();
    }
}
