package updateProduct;

import java.sql.*;
import java.util.Scanner;

public class ChangePriceHelper {
    public static void updatePrice() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Update price: ");
        int PRICE = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter Product Name

        
        String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "UPDATE PRODUCTS SET PRICE = ? WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, PRICE);
            pstmt.setString(2, PRODUCT_NAME);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public static void displayUpdatedPrice()
    { String url = "jdbc:ucanaccess://JAVA_DATABASE.mdb";
        try 
        {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM PRODUCTS

            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Updated Price List ---");
                        
                System.out.println("Product: " + rs.getString("PRODUCT_NAME") + " | Price: " + rs.getString("PRICE"));
            }

          }  catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
