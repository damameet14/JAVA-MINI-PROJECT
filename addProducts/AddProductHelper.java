package addProducts;

import java.sql.*;
import java.util.Scanner;

public class AddProductHelper {
    public static void display_product() {
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCT";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Add Category Helper placeholder");
    }

    public static void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product Name: ");
        String PRODUCT_NAME = sc.nextLine();
        System.out.print("Enter Total Quantity: ");
        int TOTAL_QUANTITY = sc.nextInt();
        System.out.print("Enter Price: ");
        int PRICE = sc.nextInt();
        System.out.print("Enter Product Category ID: ");
        int PRODUCT_CATEGORY_ID = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Product Description: ");
        String PRODUCT_DESCRIPTION = sc.nextLine();
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO products (PRODUCT_NAME,TOTAL_QUANTITY,PRICE,PRODUCT_CATEGORY_ID,PRODUCT_DESCRIPTION) VALUES (?, ?, ?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            pstmt.setInt(2, TOTAL_QUANTITY);
            pstmt.setInt(3, PRICE);
            pstmt.setInt(4, PRODUCT_CATEGORY_ID);
            pstmt.setString(5, PRODUCT_DESCRIPTION);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}