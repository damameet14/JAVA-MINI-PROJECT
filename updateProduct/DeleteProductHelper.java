package updateProduct;

import java.sql.*;
import java.util.Scanner;

public class DeleteProductHelper {
    public static void deleteProduct(Scanner sc) {
        System.out.print("Enter Product Name to delete: ");
        String PRODUCT_NAME = sc.nextLine();

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "UPDATE PRODUCTS SET IS_ACTIVE = 0 WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            pstmt.executeUpdate();
            displayDeletedPrice(PRODUCT_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayDeletedPrice(String PRODUCT_NAME) {
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Products Status ---");
            while (rs.next()) {
                try {
                    System.out.println("Product: " + rs.getString("PRODUCT_NAME") +
                            " | Status (IS_ACTIVE): " + rs.getString("IS_ACTIVE"));
                } catch (SQLException ex) {
                    System.out.println("Product: " + rs.getString("PRODUCT_NAME") + " (Deleted)");
                }
            }
            System.out.println("-----------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
