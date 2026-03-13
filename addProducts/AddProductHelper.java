package addProducts;

import java.sql.*;
import java.util.Scanner;

public class AddProductHelper {
    public static void display(String PRODUCT_NAME) {
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("---------------------------------------------------");
            System.out.println("name\tquantity\tprice\tcategory\tdescription");
            System.out.println("---------------------------------------------------");
            while (rs.next()) {
                System.out.print(rs.getString("PRODUCT_NAME") + "\t");
                System.out.print(rs.getInt("TOTAL_QUANTITY") + "\t");
                System.out.print(rs.getInt("PRICE") + "\t");
                System.out.print(rs.getInt("PRODUCT_CATEGORY_ID") + "\t");
                System.out.print(rs.getString("PRODUCT_DESCRIPTION") + "\t");
            }
            System.out.println("---------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void entry(Scanner sc) {
        System.out.println();
        sc.nextLine(); // Consume leftover newline from previous input
        System.out.print("Enter Product Name :  ");
        String PRODUCT_NAME = sc.nextLine();
        System.out.print("Enter Total Quantity :  ");
        int TOTAL_QUANTITY = sc.nextInt();
        System.out.print("Enter Price :  ");
        int PRICE = sc.nextInt();
        int PRODUCT_CATEGORY_ID = 0;
        boolean notExist = true;
        while (notExist) {
            System.out.println("----Existing Categories-----");
            AddCategoryHelper.display();
            System.out.print("Enter Product Category ID :  ");
            PRODUCT_CATEGORY_ID = sc.nextInt();
            sc.nextLine();
            try {
                if (Validate.validateExistingCategoryID(PRODUCT_CATEGORY_ID)) {
                    notExist = false;
                } else {
                    System.out.println("Category does not exist. Please enter a valid category ID.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Enter Product Description: ");
        String PRODUCT_DESCRIPTION = sc.nextLine();
        System.out.println();
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO PRODUCTS (PRODUCT_NAME,TOTAL_QUANTITY,PRICE,PRODUCT_CATEGORY_ID,PRODUCT_DESCRIPTION) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            pstmt.setInt(2, TOTAL_QUANTITY);
            pstmt.setInt(3, PRICE);
            pstmt.setInt(4, PRODUCT_CATEGORY_ID);
            pstmt.setString(5, PRODUCT_DESCRIPTION);
            pstmt.executeUpdate();
            System.out.println("****************Product was Added successfully****************");
            display(PRODUCT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Validate {
    public static boolean validateExistingCategoryID(int PRODUCT_CATEGORY_ID) throws SQLException {
        boolean exist = false;
        int count = 0;
        String dbPath = "JAVA_DATABASE.mdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID=?");
        stmt.setInt(1, PRODUCT_CATEGORY_ID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            count++;
        }
        if (count != 0)
            exist = true;

        return exist;
    }
}