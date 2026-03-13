package newBill.newBillHelper;

import java.sql.*;

public class Display {

    static void formatProductData() {
        int widthId = 10;
        int widthOrderId = 10;
        int widthQuantity = 10;
        int widthUnitPrice = 15;
        int widthTotalPrice = 15;
        String divider = "+" + "-".repeat(widthId + 2) + "+" + "-".repeat(widthOrderId + 2) + "+"
                + "-".repeat(widthQuantity + 2) + "+" + "-".repeat(widthUnitPrice + 2) + "+"
                + "-".repeat(widthTotalPrice + 2) + "+";
        System.out.println("\n\n" + divider);
        System.out.println("Product Details");
        System.out.println(divider);
        System.out.printf(
                "| %-" + widthId + "s | %-" + widthOrderId + "s | %-" + widthQuantity + "s | %-" + widthUnitPrice
                        + "s | %-"
                        + widthTotalPrice + "s |\n",
                "ID", "ORDER ID", "QUANTITY", "UNIT PRICE",
                "TOTAL PRICE");
        System.out.println(divider);
    }

    static void displayProductData() {
        int widthId = 10;
        int widthOrderId = 10;
        int widthQuantity = 10;
        int widthUnitPrice = 15;
        int widthTotalPrice = 15;
        String divider = "+" + "-".repeat(widthId + 2) + "+" + "-".repeat(widthOrderId + 2) + "+"
                + "-".repeat(widthQuantity + 2) + "+" + "-".repeat(widthUnitPrice + 2) + "+"
                + "-".repeat(widthTotalPrice + 2) + "+";
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT * FROM ORDER_LINE_ITEM WHERE ORDER_ID = ?");
                PreparedStatement stmtTotaAmount = ConnObjClass.connObj().prepareStatement(
                        "SELECT SUM(TOTAL_PRICE) FROM ORDER_LINE_ITEM WHERE ORDER_ID = ?")) {
            stmt.setInt(1, ProductsData.getOrderID());
            stmtTotaAmount.setInt(1, ProductsData.getOrderID());
            ResultSet rs = stmt.executeQuery();
            ResultSet rsTotalAmount = stmtTotaAmount.executeQuery();
            clearConsole();
            displayCustomerData();
            formatProductData();
            while (rs.next()) {
                System.out.printf(
                        "| %-" + widthId + "s | %-" + widthOrderId + "s | %-" + widthQuantity + "s | %-"
                                + widthUnitPrice
                                + "s | %-" + widthTotalPrice + "s |\n",
                        rs.getInt("PRODUCT_ID"),
                        rs.getInt("ORDER_ID"), rs.getInt("QUANTITY"),
                        rs.getDouble("UNIT_PRICE"), rs.getDouble("TOTAL_PRICE"));
                System.out.print(divider);
                System.out.println();
            }
            while (rsTotalAmount.next()) {
                double totalPrice = rsTotalAmount.getDouble(1);
                System.out.printf(
                        "  %-" + widthId + "s   %-" + widthOrderId + "s   %-" + widthQuantity + "s   %-"
                                + widthUnitPrice
                                + "s | %-" + widthTotalPrice + "s |\n",
                        "",
                        "", "",
                        "TOTAL AMOUNT: ", totalPrice);
                System.out.print(divider);
                System.out.println();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void formatCustomerData() {
        int widthID = 5;
        int widthName = 30;
        int widthPhone = 20;
        int widthEmail = 20;
        String divider = "+" + "-".repeat(widthID + 2) + "+" + "-".repeat(widthName + 2) + "+"
                + "-".repeat(widthPhone + 2)
                + "+" + "-".repeat(widthEmail + 2) + "+";
        System.out.println(divider);
        System.out.println("Customer Details");
        System.out.println(divider);
        System.out.printf(
                "| %-" + widthID + "s | %-" + widthName + "s | %-" + widthPhone + "s | %-" + widthEmail + "s |\n",
                "ID", "NAME", "PHONE", "EMAIL");
        System.out.println(divider);

    }

    public static void displayCustomerData() {
        int widthID = 5;
        int widthName = 30;
        int widthPhone = 20;
        int widthEmail = 20;
        String divider = "+" + "-".repeat(widthID + 2) + "+" + "-".repeat(widthName + 2) + "+"
                + "-".repeat(widthPhone + 2)
                + "+" + "-".repeat(widthEmail + 2) + "+";

        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?");) {
            stmt.setInt(1, CustomerData.getCustomerID());
            ResultSet rs = stmt.executeQuery();
            formatCustomerData();
            while (rs.next()) {
                System.out.printf(
                        "| %-" + widthID + "s | %-" + widthName + "s | %-" + widthPhone + "s | %-" + widthEmail
                                + "s |\n",
                        rs.getInt("CUSTOMER_ID"), rs.getString("NAME"),
                        rs.getString("MOBILE_NO"), rs.getString("EMAIL"));
                System.out.print(divider);
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

    public static void formatGeneratedInvoice() {
        int widthInvoiceNumber = 15;
        int widthOrderID = 10;
        int widthCustomerID = 15;
        int widthCustomerName = 30;
        int widthAmount = 15;
        int widthInvoiceDate = 20;
        String divider = "+" + "-".repeat(widthInvoiceNumber + 2) + "+" + "-".repeat(widthOrderID + 2) + "+"
                + "-".repeat(widthCustomerID + 2) + "+" + "-".repeat(widthCustomerName + 2) + "+"
                + "-".repeat(widthAmount + 2) + "+" + "-".repeat(widthInvoiceDate + 2) + "+";
        System.out.println(divider);
        System.out.println("Generated Invoice");
        System.out.println(divider);
        System.out.printf(
                "| %-" + widthInvoiceNumber + "s | %-" + widthOrderID + "s | %-" + widthCustomerID + "s | %-"
                        + widthCustomerName + "s | %-" + widthAmount + "s | %-" + widthInvoiceDate + "s |\n",
                "INVOICE NUMBER", "ORDER ID", "CUSTOMER ID", "CUSTOMER NAME", "AMOUNT", "INVOICE DATE");
        System.out.println(divider);
    }

    public static void displayGeneratedInvoice() {
        clearConsole();
        displayCustomerData();
        System.out.println("\n\nINVOICE FOR ORDER ID: " + ProductsData.getOrderID() + "\n\n");
        formatGeneratedInvoice();
        int widthInvoiceNumber = 15;
        int widthOrderID = 10;
        int widthCustomerID = 15;
        int widthCustomerName = 30;
        int widthAmount = 15;
        int widthInvoiceDate = 20;
        String divider = "+" + "-".repeat(widthInvoiceNumber + 2) + "+" + "-".repeat(widthOrderID + 2) + "+"
                + "-".repeat(widthCustomerID + 2) + "+" + "-".repeat(widthCustomerName + 2) + "+"
                + "-".repeat(widthAmount + 2) + "+" + "-".repeat(widthInvoiceDate + 2) + "+";
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT INVOICE_NUMBER, ORDER_ID, INVOICES.CUSTOMER_ID, CUSTOMER.NAME, AMOUNT, INVOICE_DATE FROM INVOICES INNER JOIN CUSTOMER ON INVOICES.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID WHERE ORDER_ID = ?");) {
            stmt.setInt(1, ProductsData.getOrderID());
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
