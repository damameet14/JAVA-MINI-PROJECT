package newBill;

import choiceP.*;
import java.util.*;
import java.sql.*;

class NewCustomer 
{
    public static void entry() 
    {
        try{
            Scanner sc = new Scanner(System.in);
            boolean isSuccess=true;
            String customerName = new String();
            String customerPhone= new String();
            String customerEmail= new String();
            while(isSuccess)
            {
                isSuccess=false;
                System.out.println("Enter customer name: ");
                customerName = sc.nextLine();
                System.out.println("Customer Name: " + customerName);

                System.out.println("Enter customer phone number: ");
                customerPhone = sc.nextLine();
                System.out.println("Customer Name: " + customerName);
                System.out.println("Customer Phone: " + customerPhone);

                System.out.println("Enter customer Email: ");
                customerEmail = sc.nextLine();
                System.out.println("Customer Name: " + customerName);
                System.out.println("Customer Phone: " + customerPhone);
                System.out.println("Customer Email: " + customerEmail);
                if(Validate.validateExistingEmail(customerEmail)==true)
                {
                    System.out.println("Customer already exists! Please enter details of a new customer");
                    isSuccess=true;
                    continue;
                }

            }
            InsertCustomerData.insertData(customerName, customerPhone, customerEmail);
            Choice.choiceEntry();
            sc.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

class InsertCustomerData 
{

    public static void insertData(String customerName, String customerPhone, String customerEmail) 
    {
        
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

class Validate {//will return true if the customer already exists
    public static boolean validateExistingEmail(String customerEmail) throws SQLException {
        boolean exist = false;
        int count = 0;
        String dbPath = "JAVA_DATABASE.mdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE EMAIL=?");
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
