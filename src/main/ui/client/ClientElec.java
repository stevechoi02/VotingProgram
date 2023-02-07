package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Component;

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
	
	private String[] tableName = {"��ȣ", "�̸�", "��¥", "��ǥ ����"};
	private String[] longList;
	
	//private JComboBox cbList = new JComboBox(longList);
	
	public JPanel panelMain;
	private JPanel panel01, panel02;
	private JPanel panel03;
	
	DefaultTableModel dt = new DefaultTableModel(tableName, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	
	public ClientElec() {
		lblTitle = new JLabel("���� ���");
		lblGuide = new JLabel("���� ��Ͽ��� �������ּ���.");
		lblImg = new JLabel();
		ImageIcon img = new ImageIcon("./�̹��� �ҷ��� DB���");
		
//		//�Ŀ� �׼�ó��
//		lblImg.setIcon(img);
//		lblGuide.setText("");
	
		btnOK = new JButton("Ȯ��");
		btnCancel = new JButton("���");
		
		panelMain = new JPanel();
		panel01 = new JPanel();
		panel02 = new JPanel();
		panel03 = new JPanel();
		
		panel01.add(lblGuide); 
		panel01.add(lblImg);
		panel01.add(panel03);
		
		panel03.add(btnOK);
		panel03.add(btnCancel);
		
		panel02.add(lblTitle);
		panel02.add(jsp);
		
		panelMain.add(panel01);
		panelMain.add(panel02);
		
		
	}

}
