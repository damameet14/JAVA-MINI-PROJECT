package updateDeleteChange;

public class UpdateQuantity {
    public static void update() {

        String url = "JAVA_DATABASE.mdb";
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            pstmt.setInt(2, TOTAL_QUANTITY);
            pstmt.setInt(3, PRICE);
            pstmt.setInt(4, PRODUCT_CATEGORY_ID);
            pstmt.setString(5, PRODUCT_DESCRIPTION);
            pstmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
