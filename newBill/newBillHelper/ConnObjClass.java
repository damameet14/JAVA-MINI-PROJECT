package newBill.newBillHelper;

import java.sql.*;

public class ConnObjClass {
    public static Connection connObj() throws SQLException {
        String dbPath = "JAVA_DATABASE.mdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
