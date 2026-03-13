package newBill.newBillHelper;

import java.sql.*;

public class Display {

    static void formatProductData() {
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.println("Product Details");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.printf("%7s | %20s | %7s | %3s | %10s | %15s\n", "ID", "NAME", "ORDER ID", "QUANTITY", "UNIT PRICE",
                "TOTAL PRICE");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
    }

    static void displayProductData() {
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT * FROM ORDER_LINE_ITEM WHERE ORDER_ID=?");) {
            stmt.setInt(1, ProductsData.getOrderID());
            ResultSet rs = stmt.executeQuery();
            formatProductData();
            while (rs.next()) {
                System.out.printf("%7s | %20s | %7s | %3s | %10s | %15s\n", rs.getInt("PRODUCT_ID"),
                        rs.getString("NAME"), rs.getInt("ORDER_ID"), rs.getInt("PRODUCT_QUANTITY"),
                        rs.getDouble("UNIT_PRICE"), rs.getDouble("TOTAL_PRICE"));
                System.out.print(
                        "---------------------+---------------------+---------------------+---------------------+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void formatCustomerData() {
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.println("Customer Details");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.printf("%5s | %30s | %20s | %20s\n", "ID", "NAME", "PHONE", "EMAIL");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");

    }

    public static void displayCustomerData() {
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?");) {
            stmt.setInt(1, CustomerData.getCustomerID());
            ResultSet rs = stmt.executeQuery();
            formatCustomerData();
            while (rs.next()) {
                System.out.printf("%5s | %30s | %20s | %20s\n", rs.getInt("CUSTOMER_ID"), rs.getString("NAME"),
                        rs.getString("MOBILE_NO"), rs.getString("EMAIL"));
                System.out.print(
                        "---------------------+---------------------+---------------------+--------------------+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // "cmd /c cls" is required because cls is a built-in shell command, not an
                // executable
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
