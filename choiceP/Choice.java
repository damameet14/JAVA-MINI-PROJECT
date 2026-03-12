package choiceP;

import newBill.*;
import addProducts.*;
import java.util.*;

public class Choice {
    public static void entry() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. New Bill[B]");
        System.out.println("2. Add Products[A]");
        System.out.println("3. Update Products[U]");
        System.out.println("4. View Past Bills[V]");
        System.out.println("5. Exit[E]");
        System.out.print("\nEnter your choice: ");
        char choice = sc.next().charAt(0);
        while (choice != 'E') {
            if (choice == 'B') {
                NewBill.entry();
                break;
            } else if (choice == 'A') {
                AddProducts.entry();
                break;
            } else if (choice == 'U') {
                System.out.println("Update Product placeholder");
                break;
            } else if (choice == 'V') {
                System.out.println("View Past Bills placeholder");
                break;
            } else if (choice == 'E') {
                System.out.println("Thank you!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice");
                System.out.print("\nEnter your choice: ");
                choice = sc.next().charAt(0);

            }
        }
    }
}