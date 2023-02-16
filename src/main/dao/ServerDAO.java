package main.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.Manager.CandJDialogGUI;
import main.Manager.ElecJDialogGUI;

public class ServerDAO {

	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//String url = "jdbc:oracle:thin:@192.168.11.39:1521:xe";
	String user = "elec";
	String password = "456789";
	String sql = null;
	private String[] data;

	public ServerDAO() {
		try {
	         Class.forName(driver);
	         conn=DriverManager.getConnection(url, user, password);
	         

	      }catch(ClassNotFoundException | SQLException e) {e.printStackTrace();}

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
		sql = "select elec_Index, elec_Name, elec_start, elec_end from ServerElec order by elec_Index asc";
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
				dt.fireTableDataChanged();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}

	public int elecInsert(ElecJDialogGUI user) {
		int re = -1;

		try {
			pstmt=conn.prepareStatement("insert into ServerElec (elec_Index, elec_Name, elec_start, elec_end,elec_img)"
					+ "values(eIndex_seq.nextval,?, sysdate,TO_DATE(?,'YYYY-MM-DD'),?)");
			pstmt.setString(1, user.txtName.getText().trim());
			pstmt.setString(2, user.txtEndDate.getText().trim());

			FileInputStream fin=new FileInputStream(user.txtImg.getText());
			pstmt.setBinaryStream(3, fin, fin.available());
			
			re = pstmt.executeUpdate();
			fin.close();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return re;
	}

	public int elecUpdate(ElecJDialogGUI user) {
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
			pstmt=conn.prepareStatement("delete from ServerElec where elec_Index=?");

			pstmt.setInt(1, index);
			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}
		
		return re;
	}

	public void candDeleteByElecNum(int index){
		try{
			pstmt = conn.prepareStatement("delete from ServerCand where cand_ElecNum=?");
			pstmt.executeQuery();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			dbClose();
		}
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

	public void serverCandSelectAll(DefaultListModel<String> dl, int elecNum) {
		try {
			pstmt=conn.prepareStatement("select cand_name, cand_Index from ServerCand where cand_elecnum=? order by cand_Index asc");
			pstmt.setInt(1, elecNum);
			rs = pstmt.executeQuery();
			dl.clear();

			if(!rs.next()){
				dl.addElement("후보를 추가해주세요!");
			}else {
				do{
					String name = rs.getString("cand_Name");
					int num = rs.getInt("cand_Index");
					dl.addElement("후보 #" + num + ": " + name);
				}while (rs.next());

			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}

	public int candInsert(CandJDialogGUI user) {
		int re = -1;

		try {
			pstmt=conn.prepareStatement("insert into ServerCand (cand_Index, cand_Name, cand_Sent, cand_ElecName, cand_ElecNum)"
					+ "values(?,?,?,?,?)");
			pstmt.setString(1, user.txtID.getText().trim());
			pstmt.setString(2, user.txtName.getText().trim());
			pstmt.setString(3, user.txtSent.getText().trim());
			pstmt.setString(4,user.elecName);
			pstmt.setInt(5, user.elecNum);

			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}

	public int candUpdate(CandJDialogGUI user) {
		int re = -1;

		try {
			pstmt=conn.prepareStatement("update ServerCand set cand_Name=?,cand_Sent=?"
					+ " where cand_elecnum=? and cand_Index=? ");
			
			pstmt.setString(1, user.txtName.getText().trim());
			pstmt.setString(2, user.txtSent.getText());
			pstmt.setInt(3, user.elecNum);
			pstmt.setString(4, user.txtID.getText().trim());

			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}
	//삭제 쿼리 수정해야함 2/15
	public int candDelete(int candNum, int elecNum) {
		int re=-1;

		try {
			pstmt=conn.prepareStatement("delete from ServerCand where cand_Index=? and cand_Elecnum=?");
			pstmt.setInt(1, candNum+1);
			pstmt.setInt(2,elecNum);
			re = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}

	public String getElecNameByNum(int elecNum) {
		String name = "";
		try{
			pstmt=conn.prepareStatement("select elec_name from ServerElec where elec_index=?");
			pstmt.setInt(1, elecNum);
			rs = pstmt.executeQuery();
			rs.next();
			name = rs.getString("elec_name");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			dbClose();
		}
		return name;
	}


	//필요 없어보임
	/*public void listCand(String[] comboName) {
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

	}*/

}
