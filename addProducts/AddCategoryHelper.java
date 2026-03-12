package addProducts;

import java.sql.*;
import java.util.Scanner;

public class AddCategoryHelper {
    public static void CategoryEntry() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entera new  Product Category Name: ");
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
            while (rs.next()) {
                System.out.print("Name ");
                System.out.println(rs.getString("PRODUCT_CATEGORY_NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}