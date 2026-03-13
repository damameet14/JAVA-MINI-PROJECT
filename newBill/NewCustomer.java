package newBill;

import choiceP.*;
import java.util.*;
import java.sql.*;

class NewCustomer {
    public static void entry(Scanner sc) {
        try {
            boolean isNotSuccess = true;
            while (isNotSuccess) {
                isNotSuccess = false;

                System.out.println("Enter customer name: ");
                String customerName = sc.nextLine();
                ViewCustomerData.displayCustomerData(customerName);
                ViewCustomerData.clearConsole();

                System.out.println("Enter customer phone number: ");
                String customerPhone = sc.nextLine();
                ViewCustomerData.displayCustomerData(customerName, customerPhone);
                ViewCustomerData.clearConsole();

                System.out.println("Enter customer Email: ");
                String customerEmail = sc.nextLine();
                ViewCustomerData.displayCustomerData(customerName, customerPhone, customerEmail);

                if (Validate.validateExistingEmail(customerEmail)) {
                    System.out.println("Customer already exists. Please enter another customer detail");
                    isNotSuccess = true;
                    continue;
                }
                InsertCustomerData.insertData(customerName, customerPhone, customerEmail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Choice.entry();
    }
}

class InsertCustomerData {

    public static void insertData(String customerName, String customerPhone, String customerEmail) {
        String dbPath = "JAVA_DATABASE.mdb";
        String url = "jdbc:ucanaccess://" + dbPath;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO CUSTOMER (NAME, MOBILE_NO, EMAIL) VALUES (?, ?, ?)");) {
            stmt.setString(1, customerName);
            stmt.setString(2, customerPhone);
            stmt.setString(3, customerEmail);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Validate {
    public static boolean validateExistingEmail(String customerEmail) throws SQLException {
        boolean exist = false;
        int count = 0;
        String dbPath = "JAVA_DATABASE.mdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE EMAIL=?");
        stmt.setString(1, customerEmail);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            count++;
        }
        if (count != 0)
            exist = true;

        return exist;
    }
}

class ViewCustomerData {
    static void formatData() {
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.println("Customer Details");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");
        System.out.printf("%5s %30s %20s %20s\n", "ID", "NAME", "PHONE", "EMAIL");
    }

    public static void displayCustomerData(String customerName) {
        formatData();
        System.out.printf("%5s %30s %20s %20s\n", " ", customerName, " ", " ");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");

    }

    public static void displayCustomerData(String customerName, String customerPhone) {
        formatData();
        System.out.printf("%5s %30s %20s %20s\n", " ", customerName, customerPhone, " ");
        System.out.println("---------------------+---------------------+---------------------+---------------------+");

    }

    public static void displayCustomerData(String customerName, String customerPhone, String customerEmail) {
        formatData();
        System.out.printf("%5s %30s %20s %20s\n", " ", customerName, customerPhone, customerEmail);
        System.out.println("---------------------+---------------------+---------------------+---------------------+");

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
