package main.Manager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.dao.ServerDAO;
import main.ui.server.ServerFrame;





public class ImageLoadTest extends JFrame{
	BufferedImage img,img02;
	private JPanel panel;
	private JLabel label, label02;
	
	ServerDAO dao = new ServerDAO();
	
	public ImageLoadTest(int row) {
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		panel = new JPanel();
		label = new JLabel();
		label02 = new JLabel();
		
		img = dao.getElecImage(row);
		//img02 = dao.getCandImage(candNum, elecNum);
		ImageIcon icon = new ImageIcon(img);
		//ImageIcon icon2 = new ImageIcon(img02);
		label.setIcon(icon);
		//label.setIcon(icon2);
		panel.add(label);
		panel.add(label02);
		add(panel);
		setVisible(true);
		
		
	}
	

	
	

}
