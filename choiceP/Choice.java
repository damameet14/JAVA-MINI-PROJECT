package choiceP;

import newBill.*;
import newBill.newBillHelper.*;
import addProducts.*;
import java.util.*;

public class Choice {
    public static void entry() {
        Display.clearConsole();
        Scanner sc = new Scanner(System.in);
        // boolean loop = true;
        // while (loop) {
        System.out.println("1. New Bill[B]");
        System.out.println("2. Add Products[A]");
        System.out.println("3. Update Products[U]");
        System.out.println("4. View Past Bills[V]");
        System.out.println("5. Exit[E]");
        System.out.println();
        System.out.print("Enter your choice: ");
        char choice = sc.next().charAt(0);
        System.out.println();

        while (choice != 'E' || choice != 'e') {
            if (choice == 'B' || choice == 'b') {
                System.out.println();
                NewBill.entry(sc);
                break;
            } else if (choice == 'A' || choice == 'a') {
                AddProducts.entry();
                break;
            } else if (choice == 'U' || choice == 'u') {
                System.out.println();
                System.out.println("Update Product placeholder");
                break;
            } else if (choice == 'V' || choice == 'v') {
                System.out.println();
                System.out.println("View Past Bills placeholder");
                break;
            } else if (choice == 'E' || choice == 'e') {
                System.out.println();
                // loop = false;
                System.out.println("Thank you!");
                System.exit(0);

            } else {
                System.out.println();
                System.out.println("Invalid choice");

                System.out.print("\nEnter your choice: ");
                choice = sc.next().charAt(0);
                System.out.println();
            }
            // }
        }
        sc.close();
    }
}