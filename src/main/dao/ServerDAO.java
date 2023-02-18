package main.dao;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.Manager.CandJDialogGUI;
import main.Manager.CandVO;
import main.Manager.ElecJDialogGUI;
import main.ui.server.ServerFrame;

public class ServerDAO {

	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//String url = "jdbc:oracle:thin:@192.168.11.39:1521:xe";
	String user = "vote";
	String password = "56789";
	String sql = null;
	private Object[] data;
	private static boolean openConn;

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
			openConn=false;
		}
	}

	public int elecInsert(ElecJDialogGUI user) {
		int re = -1;

		try {
			if(user.txtImg.getText().length()==0) {
				pstmt=conn.prepareStatement("insert into ServerElec (elec_Index, elec_Name, elec_start, elec_end, elec_fin)"
						+ "values(eIndex_seq.nextval,?, sysdate,TO_DATE(?,'YYYY-MM-DD'),?)");
				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtEndDate.getText().trim());
				pstmt.setInt(3,0);
				re = pstmt.executeUpdate();
			}else {
				pstmt=conn.prepareStatement("insert into ServerElec (elec_Index, elec_Name, elec_start, elec_end, elec_img,elec_fin)"
						+ "values(eIndex_seq.nextval,?, sysdate,TO_DATE(?,'YYYY-MM-DD'), ?,?)");
				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtEndDate.getText().trim());
				FileInputStream fin=new FileInputStream(user.txtImg.getText());
				pstmt.setBinaryStream(3, fin, fin.available());
				pstmt.setInt(4,0);
				re = pstmt.executeUpdate();
				fin.close();
			}

		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}

	public int elecUpdate(ElecJDialogGUI user) {
		int re = -1;

		try {
			if(user.txtImg.getText().length()==0) {
				pstmt=conn.prepareStatement("update ServerElec set elec_name=?, elec_end=? "
						+ " where elec_Index=? ");
				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtEndDate.getText().trim());
				pstmt.setInt(3, user.index);
				re = pstmt.executeUpdate();
			}else {
				pstmt=conn.prepareStatement("update ServerElec set elec_name=?, elec_end=?, elec_img=? "
						+ " where elec_Index=? ");
				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtEndDate.getText().trim());
				FileInputStream fin=new FileInputStream(user.txtImg.getText());
				pstmt.setBinaryStream(3, fin, fin.available());
				pstmt.setInt(3, user.index);

				re = pstmt.executeUpdate();
				fin.close();
			}
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
			pstmt.setInt(1, index);
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
			ResultSet rs1 = pstmt.executeQuery();
			dl.clear();

			if(!rs1.next()){
				dl.addElement("후보를 추가해주세요!");
			}else {
				do{
					String name = rs1.getString("cand_Name");
					int num = rs1.getInt("cand_Index");
					dl.addElement("후보 #" + num + ": " + name);
				}while (rs1.next());
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

			if(user.txtImg.getText().length()==0) {
				pstmt=conn.prepareStatement("insert into ServerCand (cand_Index, cand_Name, cand_Sent, cand_ElecName, cand_ElecNum)"
						+ "values(?,?,?,?,?)");
				pstmt.setString(1, user.txtID.getText().trim());
				pstmt.setString(2, user.txtName.getText().trim());
				pstmt.setString(3, user.txtSent.getText().trim());
				pstmt.setString(4,user.elecName);
				pstmt.setInt(5, user.elecNum);

				re = pstmt.executeUpdate();
			}else {
				pstmt=conn.prepareStatement("insert into ServerCand (cand_Index, cand_Name, cand_Sent, cand_ElecName, cand_ElecNum, cand_Img)"
						+ "values(?,?,?,?,?,?)");
				pstmt.setString(1, user.txtID.getText().trim());
				pstmt.setString(2, user.txtName.getText().trim());
				pstmt.setString(3, user.txtSent.getText().trim());
				pstmt.setString(4,user.elecName);
				pstmt.setInt(5, user.elecNum);
				FileInputStream fin=new FileInputStream(user.txtImg.getText());
				pstmt.setBinaryStream(6, fin, fin.available());

				re = pstmt.executeUpdate();
				fin.close();
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			dbClose();
		}

		return re;
	}

	public int candUpdate(CandJDialogGUI user) {
		int re = -1;

		try {
			if(user.txtImg.getText().length()==0) {
				pstmt=conn.prepareStatement("update ServerCand set cand_Name=?,cand_Sent=?"
						+ " where cand_elecnum=? and cand_Index=? ");

				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtSent.getText());
				pstmt.setInt(3, user.elecNum);
				pstmt.setString(4, user.txtID.getText().trim());

				re = pstmt.executeUpdate();
			}else {
				pstmt=conn.prepareStatement("update ServerCand set cand_Name=?,cand_Sent=?,cand_img=?"
						+ " where cand_elecnum=? and cand_Index=? ");

				pstmt.setString(1, user.txtName.getText().trim());
				pstmt.setString(2, user.txtSent.getText());
				FileInputStream fin=new FileInputStream(user.txtImg.getText());
				pstmt.setBinaryStream(3, fin, fin.available());
				pstmt.setInt(4, user.elecNum);
				pstmt.setString(5, user.txtID.getText().trim());
				re = pstmt.executeUpdate();
				fin.close();
			}
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

	public BufferedImage getElecImage(int index){
		BufferedImage bi = null;

		sql = "select elec_Img from ServerElec where elec_Index=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				InputStream in = rs.getBinaryStream(1);

				bi = ImageIO.read(in);
			}else {
				System.out.println("로드에 실패");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return bi;

	}

	public BufferedImage getCandImage(int candNum, int elecNum){
		BufferedImage bi = null;

		sql = "select cand_Img from ServerCand where cand_Index=? and cand_Elecnum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, candNum+1);
			pstmt.setInt(2,elecNum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				if(in!=null)
					bi = ImageIO.read(in);

			}else {
				System.out.println("로드에 실패");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return bi;

	}

	public ArrayList<CandVO> getCandListbyElecNum(int num){
		ArrayList<CandVO> cList = new ArrayList<>();
		sql = "select * from ServerCand where cand_ElecNum = ? order by cand_Index asc";
		BufferedImage bi = null;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()) {
				CandVO c = new CandVO();
				c.setCand_No(rs1.getInt("cand_Index"));
				c.setCand_Name(rs1.getString("cand_Name"));
				c.setCand_Sent(rs1.getString("cand_Sent"));
				c.setElec_Name(rs1.getString("cand_elecname"));
				c.setElec_Num(rs1.getInt("cand_elecnum"));
				c.setCand_Selected(rs1.getInt("cand_Selected"));
				InputStream in = rs1.getBinaryStream("cand_Img");
				bi = ImageIO.read(in);
				c.setCand_Img(bi);
				cList.add(c);
			}
		}catch(Exception e){e.printStackTrace();}
		finally {
			dbClose();
		}
		return cList;
	}

	public void StopElection(int currElec) {
		sql = "update ServerElec set elec_fin = 1 where elec_index=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currElec);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}


	public int getElecFin(int currElecNum){
		int res = 0;
		sql = "select elec_fin from serverelec where elec_index=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, currElecNum);
			ResultSet rs1 = pstmt.executeQuery();
			if(rs1.next()){
				res = rs1.getInt("elec_fin");
			}

		}catch(Exception e){e.printStackTrace();}
		finally {
			dbClose();
		}

		return res;
	}
}
