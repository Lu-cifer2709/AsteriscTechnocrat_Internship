import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CafeBilling {
    // Define the menu with item names and prices
    private static final Map<String, Double> menu = new HashMap<>();
    static {
        menu.put("Coffee", 19.00);
        menu.put("Tea", 09.00);
        menu.put("Sandwich", 49.00);
        menu.put("Cake", 69.00);
        menu.put("Pasta", 99.00);
        menu.put("Pizza", 199.00);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> order = new HashMap<>();

        System.out.println("\n++++++++++++++++++++++Welcome to Destiny Cafe!++++++++++++++++++++++++++++");
        System.out.println("\nMenu:");
        displayMenu();

        while (true) {
            System.out.print("\nEnter the item name (or 'Exit' to finish): ");
            String itemName = scanner.nextLine();

            if (itemName.equalsIgnoreCase("Exit")) {
                break;
            }

            if (!menu.containsKey(itemName)) {
                System.out.println("Item not found in the menu. Please try again.");
                continue;
            }

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            order.put(itemName, order.getOrDefault(itemName, 0) + quantity);
        }

        System.out.println("\n\nOrder Summary:");
        generateBill(order);

        scanner.close();
    }

    private static void displayMenu() {
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            String itemName = entry.getKey();
            double price = entry.getValue();
            System.out.println(itemName + " - $" + price);
        }
    }

    private static void generateBill(Map<String, Integer> order) {
        double totalBill = 0.0;
        System.out.println("\nItem\t\tQuantity\tPrice");
        System.out.println("--------------------------------");
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = menu.get(itemName);
            double itemTotal = price * quantity;
            totalBill += itemTotal;
            System.out.println(itemName + "\t\t" + quantity + "\t\t$" + itemTotal);
        }
        System.out.println("--------------------------------");
        System.out.println("\nTotal Bill: $" + totalBill);
        System.out.println("\nThank you visit Again!");
    }
}
