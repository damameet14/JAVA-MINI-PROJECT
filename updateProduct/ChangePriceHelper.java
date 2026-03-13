package updateProduct;

import java.sql.*;
import java.util.Scanner;

public class ChangePriceHelper {
    public static void updatePrice() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Update price: ");
        int PRICE = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String PRODUCT_NAME = sc.nextLine();

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "UPDATE PRODUCTS SET PRICE = ? WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, PRICE);
            pstmt.setString(2, PRODUCT_NAME);
            pstmt.executeUpdate();
            displayUpdatedPrice(PRODUCT_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayUpdatedPrice(String PRODUCT_NAME) {
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Updated Price List ---");
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("PRODUCT_NAME") + " | Price: " + rs.getString("PRICE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
