
package main.Manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.dao.ServerDAO;

public class ElecJDialogGUI extends JDialog implements ActionListener{
	
	public int index;

	JPanel pLabel;
	JPanel pField;
	JPanel pImg = new JPanel(new BorderLayout());
	JPanel pButton = new JPanel();

	JLabel lblName = new JLabel("이  름");
	JLabel lblAge = new JLabel("나 이");
	JLabel lblEndDate = new JLabel("마 감");
	JLabel lblImg = new JLabel("사  진");

	public JTextField txtName = new JTextField();
	public JTextField txtAge = new JTextField();
	public JTextField txtEndDate = new JTextField();
	public JTextField txtImg = new JTextField();

	JButton btnOK;
	JButton btnCancel;
	JButton btnImg;
	
	ServerDAO dao = new ServerDAO();

	JTable table;
	DefaultTableModel model;

	public ElecJDialogGUI(DefaultTableModel model, JTable table, String name) {
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
		btnImg = new JButton("경로선택");
		
		pLabel = new JPanel(new GridLayout(3,1,5,10));
		pField = new JPanel(new GridLayout(3,1,5,10));
		
		pImg.add(txtImg, "Center");
		pImg.add(btnImg, "East");

		pLabel.add(lblName);
		pLabel.add(lblEndDate);
		pLabel.add(lblImg);

		pField.add(txtName);
		pField.add(txtEndDate);
		pField.add(pImg);

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
		btnImg.addActionListener(this);

	}//생성자

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
	public static int massageConfirmBox(Object obj, String message) {
		return JOptionPane.showConfirmDialog(null,message,"데이터 삭제",JOptionPane.YES_NO_OPTION);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();

		switch (btnLabel) {
			case "확인":
				if (dao.elecInsert(this) > 0) {
					messageBox(this, "선거가 등록되었습니다.");
					dispose();
					dao.serverElecSelectAll(model);
					if (table.getRowCount() > 0) {
						table.setRowSelectionInterval(0, 0);
					}
				}
				break;
			case "취소":
				dispose();

				break;
			case "수정":
				if (dao.elecUpdate(this) > 0) {
					messageBox(this, "수정되었습니다.");
					dispose();

					dao.serverElecSelectAll(model);
					if (table.getRowCount() > 0) {
						table.setRowSelectionInterval(0, 0);
					}
				}
				break;
			case "경로선택":
				ImgChooser imgChooser = new ImgChooser();
				imgChooser.printFilePath(txtImg);
				break;
		}
		
	}
}