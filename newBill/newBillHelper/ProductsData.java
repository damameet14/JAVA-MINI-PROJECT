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
            Display.displayProductData();

            while (true) {
                System.out.println("\n1. Add More [A]");
                System.out.println("2. Generate Invoice [I]");
                char choice = sc.next().charAt(0);
                if (choice == 'A') {
                    ProductsData.askData(sc);
                    break;

                } else if (choice == 'I') {
                    GenerateInvoice.entry();
                    System.out.println("\nContinue [C]");
                    sc.next();
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insertData(int productID, int productQuantity) {
        try (PreparedStatement stmtOrderLineItems = ConnObjClass.connObj().prepareStatement(
                "INSERT INTO ORDER_LINE_ITEM (PRODUCT_ID, ORDER_ID, QUANTITY, DISCOUNT, UNIT_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?, ?)");) {
            stmtOrderLineItems.setInt(1, productID);
            stmtOrderLineItems.setInt(2, getOrderID());
            stmtOrderLineItems.setInt(3, productQuantity);
            stmtOrderLineItems.setInt(4, 0);
            stmtOrderLineItems.setInt(5, getPrice(productID));
            stmtOrderLineItems.setInt(6, getPrice(productID) * productQuantity);
            stmtOrderLineItems.executeUpdate();
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

    public static int getPrice(int productID) {
        int price = 0;
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT PRICE FROM PRODUCTS WHERE PRODUCT_ID=?");) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                price = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

}
