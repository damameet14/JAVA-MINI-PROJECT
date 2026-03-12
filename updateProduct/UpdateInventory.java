package updateProduct;

import java.util.Scanner;

public class UpdateInventory {
    public static void updateInventory() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. update total quantity(n)\n 2. update price(E)\n 3. delete product(A)");
            System.out.println("Enter your choice: ");
            char choice = sc.next().charAt(0);
            while (choice == '1' || choice == '2' || choice == '3' || choice == 'n' || choice == 'N' || choice == 'e'
                    || choice == 'E' || choice == 'a' || choice == 'A') {
                if (choice == '1' || choice == 'n' || choice == 'N') {
                    updateInventoryHelper.updateInventory();
                    updateInventoryHelper.displayUpdatedInventory();
                    break;
                } else if (choice == '2' || choice == 'e' || choice == 'E') {
                    ChangePriceHelper.updatePrice();
                    ChangePriceHelper.displayUpdatedPrice();
                    break;
                } else if (choice == '3' || choice == 'a' || choice == 'A') {
                    DeleteProductHelper.deleteProduct();
                    DeleteProductHelper.displayDeletedPrice();
                    break;
                } else {
                    System.out.println("Invalid choice");
                    System.out.println("Enter your choice: ");
                    choice = sc.next().charAt(0);
                }
            }
            System.out.println("Do you want to continue updating inventory? (Y/N): ");
            char cont = sc.next().charAt(0);
            if (cont == 'n' || cont == 'N') {
                break;
            }
        }
    }

    public static void main(String[] args) {
        updateInventory();
    }
}
