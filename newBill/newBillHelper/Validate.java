package newBill.newBillHelper;

import java.sql.*;

public class Validate {
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
        int checkPhone = 0;
        int checkEmail = 0;
        PreparedStatement stmtCheckPhone = ConnObjClass.connObj()
                .prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE MOBILE_NO=?");
        stmtCheckPhone.setString(1, customerPhone);
        ResultSet rsCheckPhone = stmtCheckPhone.executeQuery();
        while (rsCheckPhone.next()) {
            checkPhone = rsCheckPhone.getInt(1);
        }
        PreparedStatement stmtCheckEmail = ConnObjClass.connObj()
                .prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE EMAIL=?");
        stmtCheckEmail.setString(1, customerEmail);
        ResultSet rsCheckEmail = stmtCheckEmail.executeQuery();
        while (rsCheckEmail.next()) {
            checkEmail = rsCheckEmail.getInt(1);
        }

        if (checkPhone != 0 || checkEmail != 0)
            exist = true;

        return exist;
    }

    public static boolean validateProductExistence(int productID) throws SQLException {
        boolean exist = false;
        int count = 0;
        PreparedStatement stmt = ConnObjClass.connObj().prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_IDS=?");
        stmt.setInt(1, productID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            count++;
        }
        if (count != 0)
            exist = true;

        return exist;
    }
}
