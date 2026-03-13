package addProducts;

import java.sql.*;
import java.util.Scanner;

public class AddCategoryHelper {
    public static void entry(Scanner sc) {
        sc.nextLine();
        System.out.print("\nEnter a new Product Category Name: ");
        String PRODUCT_CATEGORY_NAME = sc.nextLine();
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO PRODUCT_CATEGORY (PRODUCT_CATEGORY_NAME) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_CATEGORY_NAME);
            pstmt.executeUpdate();
            System.out.println("category was added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void display() {

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCT_CATEGORY";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("----------------------------------------------------");
            System.out.println("Product Category Name\tProduct Category ID");
            System.out.println("----------------------------------------------------");
            while (rs.next()) {
                System.out.print(rs.getString("PRODUCT_CATEGORY_NAME") + "\t\t");
                System.out.print(rs.getInt("PRODUCT_CATEGORY_ID"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}