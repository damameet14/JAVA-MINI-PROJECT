package newBill;

import java.util.*;

public class NewBill {
    public static void entry(Scanner sc) {
        System.out.println("1. New Customer[N]\n2. Existing customer[E]");
        System.out.println("Enter your choice: ");
        char choice = sc.next().charAt(0);
        while (choice == 'E' || choice == 'N') {
            if (choice == 'N') {
                NewCustomer.entry(sc);
                break;
            } else if (choice == 'E') {
                System.out.println("Existing Customer placeholder");
                break;
            } else {
                System.out.println("Invalid choice");
                System.out.println("Enter your choice: ");
                choice = sc.next().charAt(0);
            }
        }
    }
}