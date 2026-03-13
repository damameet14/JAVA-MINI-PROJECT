package addProducts;

import choiceP.*;
import java.util.*;

public class AddProducts {
    public static void entry() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Enter new category(C)\n2.Add product(P)\n3.For Exit From Add Products(E)");
            System.out.println();
            System.out.print("Enter your choice: ");
            char choice = sc.next().charAt(0);

            if (choice == 'C' || choice == 'c') {
                AddCategoryHelper.display();
                AddCategoryHelper.entry(sc);
            } else if (choice == 'P' || choice == 'p') {
                // AddProductHelper.display();
                AddProductHelper.entry(sc);

            } else if (choice == 'E' || choice == 'e') {
                System.out.println("Exiting...");
                Choice.entry();
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

    }

}