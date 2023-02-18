package main.ui.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.dao.ServerDAO;
import main.dao.ServerVO;

public class ClientElec extends JPanel implements ActionListener {

	private JLabel lblTitle;
	private JLabel lblGuide;
	private JLabel lblImg;

	private JButton btnOK, btnCancel;

	private String[] tableName = {"번호", "선거 이름", "시작 날짜", "마감 날짜"};
	private String[] longList;

	//private JComboBox cbList = new JComboBox(longList);

	public JPanel panelMain;
	private JPanel panel01, panel02;
	private JPanel panel03;
	JPanel panelElec, panelVote;
	
	String selectedCand;
	
	
	
	

	DefaultTableModel dt = new DefaultTableModel(tableName, 0);
	public JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	BufferedImage bi;
	ServerDAO dao = new ServerDAO();
	ServerVO vo = new ServerVO();
	private String election;
	//ClientVote clientvote = new ClientVote(election);

	public ClientElec() {
		lblTitle = new JLabel("선거 목록");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblGuide = new JLabel("선거 목록에서 선택해주세요.");
		lblGuide.setHorizontalAlignment(JLabel.CENTER);
		lblImg = new JLabel();
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		dao.serverElecSelectAll(dt);
		
		btnOK = new JButton("확인");
		btnCancel = new JButton("취소");
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);


		panelMain = new JPanel();
		panelMain.setLayout(new GridLayout(1, 2,100,10));
		panel01 = new JPanel();
		panel01.setLayout(new BorderLayout());
		panel02 = new JPanel();
		panel02.setLayout(new BorderLayout());
		panelVote = new JPanel();
		panelVote.setLayout(new BorderLayout());
		
		
		panel03 = new JPanel();
		panel03.add(btnOK);
		panel03.add(btnCancel);
		
		panel01.add(lblTitle, BorderLayout.NORTH);
		panel01.add(jsp, BorderLayout.CENTER);
		panel01.add(panel03,BorderLayout.SOUTH);
		
		
		
		panelVote.add(lblImg, BorderLayout.NORTH);
		

		panel02.add(lblGuide, BorderLayout.NORTH);
		panel02.add(panelVote, BorderLayout.CENTER);
		
		
		

		panelMain.add(panel01);
		panelMain.add(panel02);

		add(panelMain);
		
		
			
		
		
	}
		


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel){
			ClientCards.controller.getView("Main");
		}
		if(e.getSource() == btnOK) {
			
			int row = jt.getSelectedRow();		
	        int index = (int) jt.getValueAt(row, 0);
			System.out.println(row);
			System.out.println(index);
	        bi = dao.getElecImage(index);
	        ImageIcon img = new ImageIcon(bi);
			lblImg.setIcon(img);
			lblGuide.setText("후보자를 선택해 투표해 주세요.");
			
			election = dao.getElecNameByNum(index);
			panelElec = new ClientVote(election);
			panelVote.add(panelElec, BorderLayout.CENTER);
			
			
			
		}
		
		
	}
}