package newBill.newBillHelper;

import java.sql.*;
import java.util.Scanner;

public class ProductsData {
    static boolean orderNotCreated = true;

    public static void entry(Scanner sc) {
        askData(sc);
    }

    public static void askData(Scanner sc) {
        try {
            boolean isNotSuccess = true;
            int productID = 0;
            int productQuantity = 0;
            while (isNotSuccess) {
                isNotSuccess = false;

                System.out.print("Enter product ID: ");
                productID = sc.nextInt();
                if (Validate.validateProductExistence(productID)) {
                    System.out.println("Product does not exist. Please enter another product detail");
                    isNotSuccess = true;
                    continue;
                }
            }

            System.out.print("Enter product quantity: ");
            productQuantity = sc.nextInt();
            ProductsData.insertData(productID, productQuantity);
            getOrderLineItemId();

            System.out.println("1. Add More [A]");
            System.out.println("2. Generate Invoice [I]");
            char choice = sc.next().charAt(0);
            if (choice == 'A') {
                ProductsData.askData(sc);

            } else if (choice == 'I') {
                GenerateInvoice.generateInvoice();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getOrderLineItemId() {
        int orderLineItemId = 0;
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT MAX(ORDER_LINE_ITEM_ID) FROM ORDER_LINE_ITEM");) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderLineItemId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderLineItemId;
    }

    public static int getOrderID() {
        int orderID = 0;
        if (orderNotCreated) {
            try (PreparedStatement stmtOrder = ConnObjClass.connObj().prepareStatement(
                    "INSERT INTO ORDERS (CUSTOMER_ID) VALUES (?)");) {
                stmtOrder.setInt(1, CustomerData.getCustomerID());
                stmtOrder.executeUpdate();
                orderNotCreated = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT MAX(ORDER_ID) FROM ORDERS");) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderID;
    }

    public static double getPrice(int productID) {
        double price = 0;
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT PRICE FROM PRODUCTS WHERE PRODUCT_ID=?");) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                price = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public static void insertData(int productID, int productQuantity) {
        try (PreparedStatement stmtOrderLineItems = ConnObjClass.connObj().prepareStatement(
                "INSERT INTO ORDER_LINE_ITEM (PRODUCT_ID, PRODUCT_QUANTITY, ORDER_ID, UNIT_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)");) {
            stmtOrderLineItems.setInt(1, productID);
            stmtOrderLineItems.setInt(2, productQuantity);
            stmtOrderLineItems.setInt(3, getOrderID());
            stmtOrderLineItems.setDouble(4, getPrice(productID));
            stmtOrderLineItems.setDouble(5, getPrice(productID) * productQuantity);
            stmtOrderLineItems.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
