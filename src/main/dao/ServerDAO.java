package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import main.ui.server.UserJDialogGUI;

public class ServerDAO {

	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String driver = "oracle.jdbc.driver.OracleDriver";
	//String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String url = "jdbc:oracle:thin:@192.168.11.39:1521:xe";
	String user = "elec";
	String password = "456789";
	String sql = null;
	private String[] data;

	public ServerDAO() {
		try {
	         Class.forName(driver);
	         conn=DriverManager.getConnection(url, user, password);
	         

	      }catch(ClassNotFoundException e) {e.printStackTrace();}
	      catch(SQLException e) {e.printStackTrace();}

	}
	
	public void dbClose() {
	      try {
	         if(rs != null) rs.close();
	         if(stmt != null) stmt.close();
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

	//선거 레코드 조회
	public void serverElecSelectAll(DefaultTableModel dt) {
		sql = "select elec_Index, elec_Name, Elec_start, elec_end from ServerElec order by elec_Index asc";
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				Object[] data= {
						rs.getInt("elec_Index"),
						rs.getString("elec_Name"),
						rs.getDate("elec_Start"),
						rs.getDate("elec_End")
				};
				dt.addRow(data);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}


	}

	public int elecInsert(UserJDialogGUI user) {
		int re = -1;
		
		try {
			pstmt=conn.prepareStatement("insert into ServerElec (elec_Index, elec_Name, Elec_start, elec_end)"
					+ "values(eIndex_seq.nextval,?, sysdate,?)");
			pstmt.setString(1, user.txtName.getText().trim());
			pstmt.setString(2, user.txtEndDate.getText().trim());
			
			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return re;
	}

	public int elecUpdate(UserJDialogGUI user) {
		int re = -1;
		
		try {
			pstmt=conn.prepareStatement("update ServerElec set elec_name=?, elec_end=? where elec_Index=? ");
			pstmt.setString(1, user.txtName.getText().trim());
			pstmt.setString(2, user.txtEndDate.getText().trim());
			pstmt.setInt(3, user.index);
			
			
			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return re;
	}

	public int elecDelete(int index) {
		int re=-1;
		
		try {
			pstmt=conn.prepareStatement("delete from ServerElec where elec_Index=? ");
			pstmt.setInt(1, index);
			
			
			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return re;
	}

	

	public void listElec(String[] comboName) {
		sql = "select elec_Name from ServerElec order by elec_Index asc";
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(rs.getRow());
			for(int i=0;i<rs.getRow();i++) {
				data[i] = rs.getString(1);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
	}



}
