package updateProduct;

import java.sql.*;
import java.util.Scanner;

public class UpdateInventoryHelper {

    public static void entry(Scanner sc) {

        System.out.println("1. Increase Quantity[I]\n2. Decrease Quantity[D]");
        char choice = sc.next().charAt(0);
        sc.nextLine();

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";

        while (choice == 'I' || choice == 'D') {

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection conn = DriverManager.getConnection(url);

                if (choice == 'I') {

                    System.out.print("Enter Product Name: ");
                    String PRODUCT_NAME = sc.nextLine();

                    System.out.println("Available Quantity:");
                    displayUpdatedInventory(PRODUCT_NAME);

                    System.out.print("Enter Update Quantity: ");
                    int TOTAL_QUANTITY = sc.nextInt();
                    sc.nextLine();

                    String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = TOTAL_QUANTITY + ? WHERE PRODUCT_NAME = ?";
                    PreparedStatement pstmt2 = conn.prepareStatement(sql);

                    pstmt2.setInt(1, TOTAL_QUANTITY);
                    pstmt2.setString(2, PRODUCT_NAME);
                    pstmt2.executeUpdate();

                    displayUpdatedInventory(PRODUCT_NAME);
                }

                else if (choice == 'D') {

                    System.out.print("Enter Product Name: ");
                    String PRODUCT_NAME = sc.nextLine();

                    System.out.println("Available Quantity:");
                    displayUpdatedInventory(PRODUCT_NAME);

                    System.out.print("Enter Update Quantity: ");
                    int TOTAL_QUANTITY = sc.nextInt();
                    sc.nextLine();

                    boolean isAvailable = isQuantityAvailable(conn, PRODUCT_NAME, TOTAL_QUANTITY);

                    if (isAvailable) {

                        String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = TOTAL_QUANTITY - ? WHERE PRODUCT_NAME = ?";
                        PreparedStatement pstmt2 = conn.prepareStatement(sql);

                        pstmt2.setInt(1, TOTAL_QUANTITY);
                        pstmt2.setString(2, PRODUCT_NAME);
                        pstmt2.executeUpdate();

                        displayUpdatedInventory(PRODUCT_NAME);
                    }
                }

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Do you want to update again? (i/d) or press other key to exit");
            choice = sc.next().charAt(0);
            sc.nextLine();
        }
    }

    public static void displayUpdatedInventory(String productName) {

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);

            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Updated Inventory ---");

            while (rs.next()) {
                System.out.println(
                        "Product: " + rs.getString("PRODUCT_NAME") +
                                " | Quantity: " + rs.getInt("TOTAL_QUANTITY"));
            }

            System.out.println("-------------------------\n");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isQuantityAvailable(Connection conn, String PRODUCT_NAME, int enteredQuantity) {

        boolean available = false;

        try {
            String sql = "SELECT TOTAL_QUANTITY FROM PRODUCTS WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                int dbQuantity = rs.getInt("TOTAL_QUANTITY");

                if (enteredQuantity <= dbQuantity) {
                    available = true;
                } else {
                    System.out.println("Quantity entered not available. Available quantity: " + dbQuantity);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return available;
    }
}