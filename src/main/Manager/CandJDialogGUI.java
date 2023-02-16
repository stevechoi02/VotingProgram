
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
    public int elecNum;

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
    JButton btnImg;

    ServerDAO dao = new ServerDAO();

    JList<String> list;
    DefaultListModel<String> model;

    public CandJDialogGUI(DefaultListModel<String> model, JList<String> list, String name, int elecNum) {
        this.list = list;
        this.model = model;
        this.elecNum =elecNum;
        this.elecName = dao.getElecNameByNum(elecNum);

        setTitle("후보자 추가/수정");
        this.model = model;
        this.list = list;
        
        int row =list.getSelectedIndex();
        //elecName = list.getValueAt(row, 1).toString();
        
        if(name.equals("등록")) {
        	btnOK = new JButton("등록");
        }else {
        	btnOK = new JButton("수정");
        	/*txtID.setText(table.getValueAt(row, 0).toString());
        	txtName.setText(table.getValueAt(row, 1).toString());
        	txtSent.setText(table.getValueAt(row, 2).toString());*/
        }

        
        btnCancel = new JButton("취소");
        btnImg = new JButton("경로선택");

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
        JOptionPane.showMessageDialog((Component) obj, message);
    }
    public static int massageConfirmBox(Object obj, String message) {
        return JOptionPane.showConfirmDialog(null,message,"데이터 삭제",JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLabel = e.getActionCommand();

        switch (btnLabel) {
            case "등록":
                if (dao.candInsert(this) > 0) {
                    messageBox(this, "후보가 등록되었습니다.");
                    dispose();
                    dao.serverCandSelectAll(model, elecNum);
                }
                break;
            case "취소":
                dispose();

                break;
            case "수정":
                if (dao.candUpdate(this) > 0) {
                    messageBox(this, "수정되었습니다.");
                    dispose();
                    dao.serverCandSelectAll(model, elecNum);
                }
                break;
            case "경로 선택":
                ImgChooser imgChooser = new ImgChooser();
                imgChooser.printFilePath(txtImg);
        }

    }
}