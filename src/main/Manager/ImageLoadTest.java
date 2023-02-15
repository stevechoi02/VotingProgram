package main.Manager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;





public class ImageLoadTest extends JPanel{
	BufferedImage img;
	
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
	
	public ImageLoadTest() {
		try {
	         Class.forName(driver);
	         conn=DriverManager.getConnection(url, user, password);
	         

	      }catch(ClassNotFoundException e) {e.printStackTrace();}
	      catch(SQLException e) {e.printStackTrace();}
		
		try {
			img = getDBImage();
			
			System.out.println(img);
		}catch(Exception e) {}
		
	}
	
	

	@Override
	public void print(Graphics g) {
		g.drawImage(img, 0, 0, null);
		//repaint();
	}
	
	public Dimension getPreferredSize() {
		
		if(img == null) {
			return new Dimension(100,100);
			
		}else {
			return new Dimension(img.getWidth(null),img.getHeight(null));
			
		}
		
	}



	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		
		f.add(new ImageLoadTest());
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public BufferedImage getDBImage(){
		BufferedImage bi = null;

		sql = "select elec_Img from ServerElec where elec_Index=15";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				System.out.println("11");
				System.out.println(in);
		        bi = ImageIO.read(in);
			}else {
				System.out.println("2222");
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		

        return bi;

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

}
