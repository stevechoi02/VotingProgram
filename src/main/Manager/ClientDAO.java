package main.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ui.client.ClientLogin;

public class ClientDAO {//JDBC

   Connection conn;
   PreparedStatement pstmt;
   ResultSet rs;
   String driver = "oracle.jdbc.driver.OracleDriver";
   String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
   //String url = "jdbc:oracle:thin:@192.168.11.39:1521:xe";
   String user = "elec";
   String password = "456789";
   String sql = null;

   public ClientDAO() {
      try {
         Class.forName(driver);
         conn=DriverManager.getConnection(url, user, password);

      }catch(ClassNotFoundException e) {e.printStackTrace();}
      catch(SQLException e) {e.printStackTrace();}
   }

   public void dbClose() {
      try {
         if(rs != null) rs.close();
         if(pstmt != null) pstmt.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }//dbClose()

   public void conClose() {
      try {
         if(conn != null) conn.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

   //로그인 화면 개인정보 DB에 저장
   public int insertUserData(ClientLogin cLogin) {
      int re =-1;

      try {
         pstmt=conn.prepareStatement("insert into ClientLogin (user_name, user_idnum) values(?,?)");
         pstmt.setString(1, cLogin.txtName.getText().trim());
         pstmt.setString(2, cLogin.txtPswd.getText().trim());

         re = pstmt.executeUpdate();
      }catch(Exception e) {e.printStackTrace();}
      finally {
         dbClose();
      }

      return re;
   }

   public void insertCand(){

   }

   public void editCand(String name){

   }

   public String getCandidateName(){
      return "";
   }

   //로그인 화면 이미 가입한 사람 분별
   public boolean existUserData(ClientLogin cLogin) {
      boolean re = false;


      try {
         pstmt=conn.prepareStatement("select * from ClientLogin where user_name = ? and user_idnum = ?");
         pstmt.setString(1, cLogin.txtName.getText().trim());
         pstmt.setString(2, cLogin.txtPswd.getText().trim());

         rs = pstmt.executeQuery();

         if(rs.next()) re = true;
      }catch(Exception e) {e.printStackTrace();}
      finally {
         dbClose();
      }
      return re;

   }//existUserData();




}
