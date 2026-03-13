package updateProduct;
import java.sql.*;
import java.util.Scanner;

public class UpdateInventoryHelper{
    public static void updateInventory(Scanner sc) {
        System.out.println("do you want to increase or decrease the quantity? (i/d)");
        char choice = sc.next().charAt(0);
        System.out.print("Enter Update Quantity: ");
        int TOTAL_QUANTITY = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String PRODUCT_NAME = sc.nextLine();

        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        if(choice == 'i' || choice == 'I'){
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection conn = DriverManager.getConnection(url);
                String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = TOTAL_QUANTITY + ? WHERE PRODUCT_NAME = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, TOTAL_QUANTITY);
                pstmt.setString(2, PRODUCT_NAME);
                pstmt.executeUpdate();
                displayUpdatedInventory(PRODUCT_NAME);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(choice == 'd' || choice == 'D'){
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection conn = DriverManager.getConnection(url);
                String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = TOTAL_QUANTITY - ? WHERE PRODUCT_NAME = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, TOTAL_QUANTITY);
                pstmt.setString(2, PRODUCT_NAME);
                pstmt.executeUpdate();
                displayUpdatedInventory(PRODUCT_NAME);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid choice");
            return;
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
                System.out.println("Product: " + rs.getString("PRODUCT_NAME") +
                        " | Quantity: " + rs.getInt("TOTAL_QUANTITY"));
            }
            System.out.println("-------------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
