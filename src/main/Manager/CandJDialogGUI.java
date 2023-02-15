
package main.Manager;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.dao.ServerDAO;

public class CandJDialogGUI extends JDialog implements ActionListener{

    public int index;
    public String elecName;
    

    JPanel pLabel;
    JPanel pField;
    JPanel pButton = new JPanel();

    JLabel lblID = new JLabel("기 호");
    JLabel lblName = new JLabel("이  름");
    JLabel lblImg = new JLabel("사  진");
    JLabel lblSent = new JLabel("각 오");

    public JTextField txtID = new JTextField(10);
    public JTextField txtName = new JTextField(10);
    public JTextField txtImg = new JTextField(10);
    public JTextArea txtSent = new JTextArea(5, 10);

    JButton btnOK;
    JButton btnCancel;

    ServerDAO dao = new ServerDAO();

    JTable table;
    DefaultTableModel model;

    public CandJDialogGUI(DefaultTableModel model, JTable table, String name) {

        setTitle("후보자 추가/수정");
        this.model = model;
        this.table = table;
        
        int row =table.getSelectedRow();
        elecName = table.getValueAt(row, 1).toString();
        
        if(name.equals("선거 후보 등록")) {
        	btnOK = new JButton("등록");
        }else {
        	btnOK = new JButton("수정");
        	/*
        	 * 여기 table을 후보자list에 대응되도록 변경해야함
        	txtID.setText(table.getValueAt(row, 0).toString());
        	txtName.setText(table.getValueAt(row, 1).toString());
        	txtSent.setText(table.getValueAt(row, 2).toString());
        	*/
        }

        
        btnCancel = new JButton("취소");

        pLabel = new JPanel(new GridLayout(4,1,5,10));
        pField = new JPanel(new GridLayout(4,1,5,10));

        pLabel.add(lblID);
        pLabel.add(lblName);
        pLabel.add(lblImg);
        pLabel.add(lblSent);

        pField.add(txtID);
        pField.add(txtName);
        pField.add(txtImg);
        pField.add(txtSent);

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

        if(btnLabel.equals("등록")) {
        	
            if(dao.candInsert(this) >0) {
                messageBox(this, "후보가 등록되었습니다.");
                dispose();
                //dao.serverElecSelectAll(model);

                if(table.getRowCount()>0) {
                    table.setRowSelectionInterval(0, 0);
                }
            }

        }else if(btnLabel.equals("취소")) {
            dispose();

        }else if (btnLabel.equals("수정")) {
            if(dao.candUpdate(this)>0) {
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