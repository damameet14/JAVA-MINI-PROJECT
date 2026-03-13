package newBill.newBillHelper;

import java.sql.*;

public class Validate {
    public static int customerID = 0;
    public static boolean isCustomerExist = false;

    public static boolean validatePhone(String customerPhone) {
        if (customerPhone.length() != 10) {
            return false;
        }
        for (int i = 0; i < customerPhone.length(); i++) {
            if (!Character.isDigit(customerPhone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateEmail(String customerEmail) {
        if (customerEmail.contains("@") && customerEmail.contains(".")) {
            return true;
        }
        return false;
    }

    public static boolean validateExistingCustomer(String customerPhone, String customerEmail) throws SQLException {
        boolean exist = false;
        int count = 0;
        PreparedStatement stmtCheckPhone = ConnObjClass.connObj()
                .prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER WHERE MOBILE_NO=?");
        stmtCheckPhone.setString(1, customerPhone);
        ResultSet rsCheckPhone = stmtCheckPhone.executeQuery();
        while (rsCheckPhone.next()) {
            customerID = rsCheckPhone.getInt(1);
            count++;
        }
        PreparedStatement stmtCheckEmail = ConnObjClass.connObj()
                .prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL=?");
        stmtCheckEmail.setString(1, customerEmail);
        ResultSet rsCheckEmail = stmtCheckEmail.executeQuery();
        while (rsCheckEmail.next()) {
            customerID = rsCheckEmail.getInt(1);
            count++;
        }

        if (count != 0)
            exist = true;
        isCustomerExist = exist;

        return exist;
    }

    public static boolean validateProductExistence(int productID) throws SQLException {
        boolean exist = false;
        int count = 0;
        PreparedStatement stmt = ConnObjClass.connObj()
                .prepareStatement("SELECT COUNT(*) FROM PRODUCTS WHERE PRODUCT_ID=?");
        stmt.setInt(1, productID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        if (count == 0)
            exist = true;

        return exist;
    }
}
