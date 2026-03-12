package addProducts;

import java.util.*;

public class AddProducts {
    public static void categoryProduct() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Existing category(n)\n 2. Enter new category(E)\n 3. Add product(A)");
            System.out.println("Enter your choice: ");
            char choice = sc.next().charAt(0);
            while (choice == '1' || choice == '2' || choice == '3' || choice == 'n' || choice == 'N' || choice == 'e'
                    || choice == 'E' || choice == 'a' || choice == 'A') {
                if (choice == '1' || choice == 'n' || choice == 'N') {
                    AddCategoryHelper.display();
                    break;
                } else if (choice == '2' || choice == 'e' || choice == 'E') {
                    AddCategoryHelper.CategoryEntry();
                    break;
                } else if (choice == '3' || choice == 'a' || choice == 'A') {
                    AddProductHelper.addProduct();
                    AddProductHelper.display_product();
                    break;
                } else {
                    System.out.println("Invalid choice");
                    System.out.println("Enter your choice: ");
                    choice = sc.next().charAt(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        categoryProduct();
    }
}