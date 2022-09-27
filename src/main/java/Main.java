import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int PREPARATION_TIME = 3000;
    static final int PURCHASE_TIME = 2000;
    static List<String> cars = new ArrayList<>();
    public static void main(String[] args) {
        new Seller().start();


        new Buyer().start();
        new Buyer().start();
        new Buyer().start();
    }
}
