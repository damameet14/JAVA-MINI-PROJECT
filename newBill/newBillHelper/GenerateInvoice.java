package newBill.newBillHelper;

import java.sql.*;

public class GenerateInvoice {
    public static void entry() {
        generateInvoice();
        Display.displayGeneratedInvoice();
    }

    public static double getTotalAmount() {
        double totalAmount = 0;
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT SUM(TOTAL_PRICE) FROM ORDER_LINE_ITEM WHERE ORDER_ID=?");) {
            stmt.setInt(1, ProductsData.getOrderID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                totalAmount = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAmount;
    }

    public static void generateInvoice() {
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "INSERT INTO INVOICES (ORDER_ID, CUSTOMER_ID, AMOUNT) VALUES (?, ?, ?)");) {
            stmt.setInt(1, ProductsData.getOrderID());
            stmt.setInt(2, CustomerData.getCustomerID());
            stmt.setDouble(3, getTotalAmount());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
