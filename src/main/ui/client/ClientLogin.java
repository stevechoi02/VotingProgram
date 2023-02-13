package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.dao.ClientDAO;
import main.ui.server.UserJDialogGUI;


public class ClientLogin extends JPanel implements ActionListener {

	private JLabel lblTitle;
	private JLabel lblName, lblPswd;

	public JTextField txtName;
	public JTextField txtPswd;

	private JButton btnOK, btnCancel;


	public JPanel panelMain, panel01, panel02;
	
	ClientDAO dao = new ClientDAO();

	public ClientLogin() {
		setSize(600,400);

		lblTitle = new JLabel("투표자 개인정보 입력");
		lblTitle.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);

		lblName = new JLabel("이      름 : ");
		lblPswd = new JLabel("주민번호 : ");

		txtName = new JTextField(15);
		txtPswd = new JTextField(15);


		btnOK = new JButton("확 인");
		btnCancel = new JButton("취 소");

		panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout());

		panel01 = new JPanel();
		panel01.add(lblTitle);

		panel02 = new JPanel();
		panel02.setLayout(null);
		panel02.add(lblName);
		panel02.add(lblPswd);

		lblName.setBounds(125, 50,400,20);
		lblPswd.setBounds(125, 100,400,20);

		panel02.add(txtName);
		panel02.add(txtPswd);

		txtName.setBounds(200, 50, 300, 20);
		txtPswd.setBounds(200, 100, 300, 20);

		btnOK.addActionListener(this);

		panel02.add(btnOK);
		panel02.add(btnCancel);
		btnOK.setBounds(200, 200, 80, 50);
		btnCancel.setBounds(350, 200, 80, 50);


		panelMain.add(panel01, BorderLayout.NORTH);
		panelMain.add(panel02, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(panelMain);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOK){
				if(dao.insertUserData(this)>0) {
					UserJDialogGUI.messageBox(this, "개인정보 확인 및 저장이 완료되었습니다.");
					ClientCards.controller.getView("Main");
				}else if(dao.existUserData(this)) {
					UserJDialogGUI.messageBox(this, "이미 저장 및 투표한 사용자입니다.");
					ClientCards.controller.getView("Main");
				}else {
					UserJDialogGUI.messageBox(this, "잘못된 사용자입니다.");
				}
			}
			if(e.getSource() == btnCancel){

			}
		}
	}