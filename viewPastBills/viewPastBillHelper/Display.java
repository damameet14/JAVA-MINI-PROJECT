package viewPastBills.viewPastBillHelper;

import java.sql.*;

public class Display {
    public static void displayPastInvoice(String startDate, String endDate) {
        newBill.newBillHelper.Display.clearConsole();
        newBill.newBillHelper.Display.formatGeneratedInvoice();
        int widthInvoiceNumber = 15;
        int widthOrderID = 10;
        int widthCustomerID = 15;
        int widthCustomerName = 30;
        int widthAmount = 15;
        int widthInvoiceDate = 20;
        String divider = "+" + "-".repeat(widthInvoiceNumber + 2) + "+" + "-".repeat(widthOrderID + 2) + "+"
                + "-".repeat(widthCustomerID + 2) + "+" + "-".repeat(widthCustomerName + 2) + "+"
                + "-".repeat(widthAmount + 2) + "+" + "-".repeat(widthInvoiceDate + 2) + "+";
        try (PreparedStatement stmt = newBill.newBillHelper.ConnObjClass.connObj().prepareStatement(
                "SELECT INVOICE_NUMBER, ORDER_ID, INVOICES.CUSTOMER_ID, CUSTOMER.NAME, AMOUNT, INVOICE_DATE FROM INVOICES INNER JOIN CUSTOMER ON INVOICES.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE INVOICE_DATE BETWEEN ? AND ?");) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf(
                        "| %-" + widthInvoiceNumber + "s | %-" + widthOrderID + "s | %-" + widthCustomerID + "s | %-"
                                + widthCustomerName + "s | %-" + widthAmount + "s | %-" + widthInvoiceDate + "s |\n",
                        rs.getInt("INVOICE_NUMBER"), rs.getInt("ORDER_ID"), rs.getInt("INVOICES.CUSTOMER_ID"),
                        rs.getString("CUSTOMER.NAME"), rs.getDouble("AMOUNT"), rs.getDate("INVOICE_DATE"));
                System.out.print(divider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
