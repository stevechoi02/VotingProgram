package main.ui.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.dao.ServerElecDAO;

public class ServerElec extends JPanel implements ActionListener{
	
	private JLabel lblTitle;
	private JLabel lbl01,lbl02;
	
	private JButton btnCreate, btnEdit, btnDelete, btnCancel;
	
	private String[] tableName = {"번호", "이름", "시작 날짜", "마감 날짜"};
	
	DefaultTableModel dt = new DefaultTableModel(tableName, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	JPanel panelMain;
	JPanel panel01,panel02;
	
	ServerElecDAO dao = new ServerElecDAO();
	
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
		
		dao.serverElecSelectAll(dt);
		
		if(dt.getRowCount()>0) {
			jt.setRowSelectionInterval(0, 0);
		}
		
	}//생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate) {
			new UserJDialogGUI(this, "추 가");
		}else if(e.getSource() == btnEdit) {
			new UserJDialogGUI(this, "수  정");
		}else if(e.getSource() == btnDelete) {
			int row =jt.getSelectedRow();
			int index = (int) jt.getValueAt(row, 0);
			if(UserJDialogGUI.massageConfirmBox(this, "정말 삭제하시겠습니까?")==0) {
				if(dao.elecDelete(index)>0) {
					dao.serverElecSelectAll(dt);
					if(dt.getRowCount()>0) {
						jt.setRowSelectionInterval(0, 0);
					}
				}
			}
		}else if(e.getSource() == btnCancel) {
			dao.conClose();
			System.exit(0);
		}
		
	}
	
	
	
}
