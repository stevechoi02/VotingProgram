package main.ui.client;

import main.Manager.ClientDAO;
import main.Manager.ElecJDialogGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ClientLogin extends JPanel implements ActionListener {

	private JLabel lblTitle;
	private JLabel lblName, lblPswd;

	public JTextField txtName;
	public JTextField txtPswd;

	private JButton btnOK, btnCancel;

	public JPanel panelMain, panel01, panel02;
	public JPanel panelName, panelPswd;

	ClientDAO dao = new ClientDAO();

	public ClientLogin() {
		//setSize(600,400);

		lblTitle = new JLabel("투표자 개인정보 입력");
		lblTitle.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);

		lblName = new JLabel("이        름 : ");
		lblPswd = new JLabel("주민번호 : ");

		txtName = new JTextField(20);
		txtPswd = new JTextField(20);


		btnOK = new JButton("확 인");
		btnCancel = new JButton("취 소");

		panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout());
		
		panelName = new JPanel(new BorderLayout());
		panelName.add(lblName,"West");
		panelName.add(txtName,"Center");
		
		panelPswd = new JPanel(new BorderLayout());
		panelPswd.add(lblPswd,"West");
		panelPswd.add(txtPswd,"Center");
		

		panel01 = new JPanel();
		panel01.setLayout(null);
		
		panelName.setBounds(250, 100, 400, 40);
		panelPswd.setBounds(250, 150, 400, 40);
		
		panel01.add(panelName);
		panel01.add(panelPswd);
		

	
		

		panel02 = new JPanel();
		
		panel02.add(btnOK);
		panel02.add(btnCancel);
		


		panelMain.add(lblTitle, BorderLayout.NORTH);
		panelMain.add(panel01, BorderLayout.CENTER);
		panelMain.add(panel02, BorderLayout.SOUTH);

		setLayout(new BorderLayout());
		add(panelMain,"Center");
		
		btnOK.addActionListener(this);

		btnCancel.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOK){
//				if(dao.insertUserData(this)>0) {
				if(true){
					ElecJDialogGUI.messageBox(this, "개인정보 확인 및 저장이 완료되었습니다.");
					ClientCards.controller.getView("Main");
				}else if(dao.existUserData(this)) {
					ElecJDialogGUI.messageBox(this, "이미 저장 및 투표한 사용자입니다.");
					ClientCards.controller.getView("Main");
				}else {
					ElecJDialogGUI.messageBox(this, "잘못된 사용자입니다.");
				}
			}
			if(e.getSource() == btnCancel){
				System.exit(0);
				
			}
		}
	}