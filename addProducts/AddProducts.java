package addProducts;

import java.util.*;

public class AddProducts {
    public static void categoryProduct() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Enter new category(c)\n2.Add product(p)\n3.For Exit From Add Products");
            System.out.print("Enter your choice: ");
            char choice = sc.next().charAt(0);

            if (choice == '1' || choice == 'c' || choice == 'C') {
                AddCategoryHelper.display();
                AddCategoryHelper.CategoryEntry();
            } else if (choice == '2' || choice == 'p' || choice == 'P') {
                AddProductHelper.addProduct();
                AddProductHelper.display_product();

            } else if (choice == '3' || choice == 'e' || choice == 'E') {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        categoryProduct();
    }
}