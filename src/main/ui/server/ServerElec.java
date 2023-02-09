package main.ui.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServerElec extends JPanel implements ActionListener{
	
	private JLabel lblTitle;
	private JLabel lbl01,lbl02;
	
	private JButton btnCreate, btnEdit, btnDelete, btnCancel;
	
	private String[] tableName = {"기호", "이름", "시작 시간", "마감 시간"};
	
	DefaultTableModel dt = new DefaultTableModel(tableName, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	JPanel panelMain;
	JPanel panel01,panel02;
	
	public ServerElec() {
		lblTitle = new JLabel("선거 관리");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("Serif",Font.BOLD,30));
		
		lbl01 = new JLabel();
		lbl02 = new JLabel();
		
		btnCreate = new JButton("추 가");
		btnEdit = new JButton("수 정");
		btnDelete = new JButton("삭 제");
		btnCancel = new JButton("나가기");
		
		panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(10,10));
		panel01 = new JPanel();
		panel01.setLayout(new BorderLayout());
		panel02 = new JPanel();
		panel02.setLayout(new GridLayout(6, 2,10,40));
		
		panel01.add(lblTitle, BorderLayout.NORTH);
		panel01.add(jsp, BorderLayout.CENTER);
		
		
		panel02.add(lbl01);
		panel02.add(btnCreate);
		panel02.add(btnEdit);
		panel02.add(btnDelete);
		panel02.add(btnCancel);
		panel02.add(lbl02);
		
		panelMain.add(panel01, BorderLayout.CENTER);
		panelMain.add(panel02, BorderLayout.EAST);
		
		add(panelMain);
		
		btnCreate.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnCancel.addActionListener(this);
		
	}//생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate) {
			new UserJDialogGUI(this, "추 가");
		}else if(e.getSource() == btnEdit) {
			new UserJDialogGUI(this, "수  정");
		}else if(e.getSource() == btnDelete) {
			UserJDialogGUI.massageConfirmBox(this, "정말 삭제하시겠습니까?");
		}else if(e.getSource() == btnCancel) {
			
		}
		
	}
	
	
	
}
