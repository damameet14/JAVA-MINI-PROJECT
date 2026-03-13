package newBill.newBillHelper;

import java.sql.*;
import java.util.Scanner;

public class CustomerData {

    public static void entry(Scanner sc) {
        askData(sc);
    }

    public static void askData(Scanner sc) {
        try {

            System.out.println("Enter customer name: ");
            String customerName = sc.nextLine();

            boolean isNotValidePhone = true;
            String customerPhone = "";
            while (isNotValidePhone) {
                isNotValidePhone = false;
                System.out.println("Enter customer phone number: ");
                customerPhone = sc.next();
                if (!Validate.validatePhone(customerPhone)) {
                    System.out.println("Invalid phone number");
                    isNotValidePhone = true;
                    continue;
                }
            }
            Display.clearConsole();

            boolean isNotValideEmail = true;
            String customerEmail = "";
            while (isNotValideEmail) {
                isNotValideEmail = false;
                System.out.println("Enter customer Email: ");
                customerEmail = sc.next();
                if (!Validate.validateEmail(customerEmail)) {
                    System.out.println("Invalid email");
                    isNotValideEmail = true;
                    continue;
                }
            }
            Display.clearConsole();
            CustomerData.insertNewCustomerData(customerName, customerPhone, customerEmail);
            Display.displayCustomerData();
            if (Validate.validateExistingCustomer(customerPhone, customerEmail)) {
                System.out.println("\n-------------------------\nExisting Customer\n-------------------------\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getCustomerID() {
        int customerID = 0;
        try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER");) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customerID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerID;
    }

    public static void insertNewCustomerData(String customerName, String customerPhone, String customerEmail) {
        try {
            if (!Validate.validateExistingCustomer(customerPhone, customerEmail)) {

                try (PreparedStatement stmt = ConnObjClass.connObj().prepareStatement(
                        "INSERT INTO CUSTOMER (NAME, MOBILE_NO, EMAIL) VALUES (?, ?, ?)");) {
                    stmt.setString(1, customerName);
                    stmt.setString(2, customerPhone);
                    stmt.setString(3, customerEmail);
                    stmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
