
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");


          con = DriverManager.getConnection(
                  "jdbc:mysql://localhost/kadaidb?ysySSl=false&allowPublicKeyRetrieval=true",
                  "root",
                  "tennisbaske098");

          String sql = "SELECT name, age FROM person WHERE id = ?";
          pstmt = con.prepareStatement(sql);

          System.out.print("検索キーワードを入力してください > ");
          int id = keyIn();
          pstmt.setInt(1, id);
          rs = pstmt.executeQuery();

          while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(name);
            System.out.println(age);
          }
        } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
        } finally {
          try {
            if (rs != null) {
              rs.close();
            }
            if (pstmt != null) {
              pstmt.close();
            }
            if (con != null) {
              con.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }


    private static int keyIn() {
        int line = 0;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = Integer.parseInt(key.readLine());
        } catch (IOException e) {
    }
        return line;
    }
}
