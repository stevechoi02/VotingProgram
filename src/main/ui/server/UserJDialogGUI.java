/*
package main.ui.server;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.dao.ServerDAO;

public class UserJDialogGUI extends JDialog implements ActionListener{
	
	public int index;

	JPanel pLabel;
	JPanel pField;
	JPanel pButton = new JPanel();

	JLabel lblName = new JLabel("이  름");
	JLabel lblAge = new JLabel("나 이");
	JLabel lblEndDate = new JLabel("마 감");
	JLabel lblImg = new JLabel("사  진");

	public JTextField txtName = new JTextField(10);
	public JTextField txtAge = new JTextField(10);
	public JTextField txtEndDate = new JTextField(10);
	public JTextField txtImg = new JTextField(10);

	JButton btnOK;
	JButton btnCancel; 
	
	ServerElec sElec;
	ServerCand sCand;
	
	ServerDAO dao = new ServerDAO();

	public UserJDialogGUI(ServerElec sElec, String name) {
		setTitle("선거 추가/수정");
		this.sElec = sElec;
		
		if(name.equals("추 가")) {
			btnOK = new JButton("확인");
		}else {
			btnOK = new JButton("수정");
			
			int row = sElec.jt.getSelectedRow();
			index=(int) sElec.jt.getValueAt(row, 0);
			txtName.setText(sElec.jt.getValueAt(row, 1).toString());
			txtEndDate.setText(sElec.jt.getValueAt(row, 2).toString());
			
			
		}
		
		
		btnCancel = new JButton("취소");
		
		pLabel = new JPanel(new GridLayout(3,1,5,10));
		pField = new JPanel(new GridLayout(3,1,5,10));

		pLabel.add(lblName);
		pLabel.add(lblEndDate);
		pLabel.add(lblImg);

		pField.add(txtName);
		pField.add(txtEndDate);
		pField.add(txtImg);

		pButton.add(btnOK);
		pButton.add(btnCancel);

		add(pLabel, "West");
		add(pField, "Center");
		add(pButton, "South");

		setSize(300,150);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);

	}//생성자

	public UserJDialogGUI(ServerCand sCand, String name) {

		setTitle("후보자 추가/수정");
		this.sCand = sCand;
		
		btnOK = new JButton("OK");
		btnCancel = new JButton("Cancel");
		
		pLabel = new JPanel(new GridLayout(3,1,5,10));
		pField = new JPanel(new GridLayout(3,1,5,10));

		pLabel.add(lblName);
		pLabel.add(lblAge);
		pLabel.add(lblImg);

		pField.add(txtName);
		pField.add(txtAge);
		pField.add(txtImg);

		pButton.add(btnOK);
		pButton.add(btnCancel);

		add(pLabel, "West");
		add(pField, "Center");
		add(pButton, "South");

		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);

	}//생성자

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
	public static int massageConfirmBox(Object obj, String message) {
	
		
		int re = JOptionPane.showConfirmDialog(null,message,"데이터 삭제",JOptionPane.YES_NO_OPTION);
		return re;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		
		if(btnLabel.equals("확인")) {
			if(dao.elecInsert(this) >0) {
				messageBox(this, "선거가 등록되었습니다.");
				dispose();
				dao.serverElecSelectAll(sElec.dt);
				
				if(sElec.dt.getRowCount()>0) {
					sElec.jt.setRowSelectionInterval(0, 0);
				}
			}
				
			}else if(btnLabel.equals("취소")) {
				dispose();
			
		}else if (btnLabel.equals("수정")) {
			if(dao.elecUpdate(this)>0) {
				messageBox(this, "수정되었습니다.");
				dispose();
				
				dao.serverElecSelectAll(sElec.dt);
				if(sElec.dt.getRowCount()>0) {
					sElec.jt.setRowSelectionInterval(0, 0);
				}
			}
		}
		
	}






}
*/

package main.ui.server;

import main.ui.server.ServerCand;
import main.ui.server.ServerElec;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.dao.ServerDAO;

public class UserJDialogGUI extends JDialog implements ActionListener{
	
	public int index;

	JPanel pLabel;
	JPanel pField;
	JPanel pButton = new JPanel();

	JLabel lblName = new JLabel("이  름");
	JLabel lblAge = new JLabel("나 이");
	JLabel lblEndDate = new JLabel("마 감");
	JLabel lblImg = new JLabel("사  진");

	public JTextField txtName = new JTextField(10);
	public JTextField txtAge = new JTextField(10);
	public JTextField txtEndDate = new JTextField(10);
	public JTextField txtImg = new JTextField(10);

	JButton btnOK;
	JButton btnCancel; 

	ServerCand sCand;
	
	ServerDAO dao = new ServerDAO();

	JTable table;
	DefaultTableModel model;

	public UserJDialogGUI(DefaultTableModel model, JTable table, String name) {
		this.table = table;
		this.model = model;
		setTitle("선거 추가/수정");
		if(name.equals("서버 선거 추가")){btnOK = new JButton("확인");}
		else{
			btnOK = new JButton("수정");
			int row = table.getSelectedRow();
			index = (int) table.getValueAt(row, 0);
			txtName.setText(table.getValueAt(row, 1).toString());
			txtEndDate.setText(table.getValueAt(row, 2).toString());
		}

		
		btnCancel = new JButton("취소");
		
		pLabel = new JPanel(new GridLayout(3,1,5,10));
		pField = new JPanel(new GridLayout(3,1,5,10));

		pLabel.add(lblName);
		pLabel.add(lblEndDate);
		pLabel.add(lblImg);

		pField.add(txtName);
		pField.add(txtEndDate);
		pField.add(txtImg);

		pButton.add(btnOK);
		pButton.add(btnCancel);

		add(pLabel, "West");
		add(pField, "Center");
		add(pButton, "South");

		setSize(300,150);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);

	}//생성자

	public UserJDialogGUI(JTable table, String name) {

		setTitle("후보자 추가/수정");
		this.sCand = sCand;
		
		btnOK = new JButton("OK");
		btnCancel = new JButton("Cancel");
		
		pLabel = new JPanel(new GridLayout(3,1,5,10));
		pField = new JPanel(new GridLayout(3,1,5,10));

		pLabel.add(lblName);
		pLabel.add(lblAge);
		pLabel.add(lblImg);

		pField.add(txtName);
		pField.add(txtAge);
		pField.add(txtImg);

		pButton.add(btnOK);
		pButton.add(btnCancel);

		add(pLabel, "West");
		add(pField, "Center");
		add(pButton, "South");

		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);

	}//생성자

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
	public static int massageConfirmBox(Object obj, String message) {
	
		
		int re = JOptionPane.showConfirmDialog(null,message,"데이터 삭제",JOptionPane.YES_NO_OPTION);
		return re;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		
		if(btnLabel.equals("확인")) {
			if(dao.elecInsert(this) >0) {
				messageBox(this, "선거가 등록되었습니다.");
				dispose();
				dao.serverElecSelectAll(model);
				
				if(table.getRowCount()>0) {
					table.setRowSelectionInterval(0, 0);
				}
			}
				
			}else if(btnLabel.equals("취소")) {
				dispose();
			
		}else if (btnLabel.equals("수정")) {
			if(dao.elecUpdate(this)>0) {
				messageBox(this, "수정되었습니다.");
				dispose();
				
				dao.serverElecSelectAll(model);
				if(table.getRowCount()>0) {
					table.setRowSelectionInterval(0, 0);
				}
			}
		}
		
	}






}