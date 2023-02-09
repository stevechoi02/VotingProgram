package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientElec extends JPanel {
	
	private JLabel lblTitle;
	private JLabel lblGuide;
	private JLabel lblImg;
	
	private JButton btnOK, btnCancel;
	
	private String[] tableName = {"湲고", "대", "吏", "ы щ"};
	private String[] longList;
	
	//private JComboBox cbList = new JComboBox(longList);
	
	public JPanel panelMain;
	private JPanel panel01, panel02;
	private JPanel panel03;
	
	DefaultTableModel dt = new DefaultTableModel(tableName, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	
	public ClientElec() {
		lblTitle = new JLabel("嫄 紐⑸");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblGuide = new JLabel("嫄 紐⑸ 댁＜몄.");
		lblGuide.setHorizontalAlignment(JLabel.CENTER);

		lblImg = new JLabel();
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		ImageIcon img = new ImageIcon("./대�吏 遺ъ DB寃쎈");
		
//		// ≪泥由
//		lblImg.setIcon(img);
//		lblGuide.setText("");
	
		btnOK = new JButton("");
		btnCancel = new JButton("痍⑥");
		
		panelMain = new JPanel();
		panelMain.setLayout(new GridLayout(1, 2,10,10));
		panel01 = new JPanel();
		panel01.setLayout(new BorderLayout());
		panel02 = new JPanel();
		panel02.setLayout(new BorderLayout());
		panel03 = new JPanel();
		
		panel01.add(lblGuide, BorderLayout.NORTH); 
		panel01.add(lblImg, BorderLayout.CENTER);
		panel01.add(panel03,BorderLayout.SOUTH);
		
		
		panel03.add(btnOK);
		panel03.add(btnCancel);
		
		panel02.add(lblTitle, BorderLayout.NORTH);
		panel02.add(jsp, BorderLayout.CENTER);
		
		panelMain.add(panel01);
		panelMain.add(panel02);
		
		add(panelMain);
		
		
		
	}

}
