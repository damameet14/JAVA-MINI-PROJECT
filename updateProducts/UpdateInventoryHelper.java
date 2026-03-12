package updateProducts;

public class UpdateQuantity {
    public static void updateInventory(){   

        String url = "JAVA_DATABASE.mdb";
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "UPDATE PRODUCTS SET TOTAL_QUANTITY = ? WHERE PRODUCT_NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, PRODUCT_NAME);
            pstmt.setInt(2, TOTAL_QUANTITY);
            pstmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        public static displayUpdatedInventory()
    {
         String url = "JAVA_DATABASE.mdb";
        try 
        {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT Total Quantity,PRODUCT_NAME FROM PRODUCTS"
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery(sql);
            
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("Add Category Helper placeholder");
    }
    public static void addProduct() 

    }

}
