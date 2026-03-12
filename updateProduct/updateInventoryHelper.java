package updateProduct;

import java.sql.*;
import java.util.Scanner;

public class updateInventoryHelper {
    public static void updateInventory() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Update Quantity: ");
        int TOTAL_QUANTITY = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String PRODUCT_NAME = sc.nextLine();

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = ? WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, TOTAL_QUANTITY);
            pstmt.setString(2, PRODUCT_NAME);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayUpdatedInventory() {
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCTS";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Updated Inventory ---");
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("PRODUCT_NAME") +
                        " | Quantity: " + rs.getInt("TOTAL_QUANTITY"));
            }
            System.out.println("-------------------------\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
