package main.ui.server;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserJDialogGUI extends JDialog {
	
	JPanel pLabel = new JPanel(new GridLayout(2,1,5,10));
	JPanel pField = new JPanel(new GridLayout(2,1,5,10));
	JPanel pButton = new JPanel();
	
	JLabel lblName = new JLabel("이  름");
	JLabel lblImg = new JLabel("사  진");
	
	public JTextField txtName = new JTextField(10);
	public JTextField txtImg = new JTextField(10);
	
	JButton btnOK = new JButton("확인");
	JButton btnCancel = new JButton("취소");
	
	public UserJDialogGUI(ServerElec sElec, String name) {
		setTitle("추가/수정");
		
		pLabel.add(lblName);
		pLabel.add(lblImg);
		
		pField.add(txtName);
		pField.add(txtImg);
		
		pButton.add(btnOK);
		pButton.add(btnCancel);
		
		add(pLabel, "West");
		add(pField, "Center");
		add(pButton, "South");
		
		setSize(300,150);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
	public static void massageConfirmBox(Object obj, String message) {
		JOptionPane.showConfirmDialog(null,message,"데이터 삭제",JOptionPane.YES_NO_OPTION);
	}




	

}
