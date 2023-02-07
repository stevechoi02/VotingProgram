package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * 사용자 어플리케이션 초기화면
 */


public class ClientLogin extends JPanel implements ActionListener {
	/*
	private JLabel lblTitle, lblName, lblPswd, lblRegion;
    private JTextField txtName;
    private JTextField txtPswd;
    private JComboBox<String> cbRegion;
    private JButton btnOk, btnCancel;
	 */

	private JLabel lblTitle;
	private JLabel lblName, lblPswd, lblRegion;

	private JTextField txtName, txtPswd;

	private JButton btnOK, btnCancel;

	private String location[] = {"","서울특별시","경기도","강원도","충청도",
			"전라도","경상도","등등"};

	private JComboBox cbRegion = new JComboBox(location);

	public JPanel panelMain;
	private JPanel panel01, panel02;

	public ClientLogin() {

		//setTitle("선거관리 프로그램 : 사용자용");
		//setSize(600,400);
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//윈도우 프레임 중앙 위치
		setResizable(false);//사이즈 조절 불가 설정
		 */
		
		lblTitle = new JLabel("투표자 개인정보 입력");
		lblTitle.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);

		lblName = new JLabel("이      름 : ");
		lblPswd = new JLabel("주민번호 : ");
		lblRegion = new JLabel("지      역 : ");

		txtName = new JTextField(15);
		txtPswd = new JTextField(15);

		cbRegion.setSelectedIndex(0);

		btnOK = new JButton("확 인");
		btnCancel = new JButton("취 소");
		//btnOK.setPreferredSize(new Dimension(80,50));
		//btnCancel.setPreferredSize(new Dimension(80,50));
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		
		panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout());

		panel01 = new JPanel();
		panel01.add(lblTitle);

		panel02 = new JPanel();
		panel02.setLayout(null);
		panel02.add(lblName);
		panel02.add(lblPswd);
		panel02.add(lblRegion);

		lblName.setBounds(125, 50,400,20);
		lblPswd.setBounds(125, 100,400,20);
		lblRegion.setBounds(125, 150,400,20);

		panel02.add(txtName);
		panel02.add(txtPswd);
		panel02.add(cbRegion);

		txtName.setBounds(200, 50, 300, 20);
		txtPswd.setBounds(200, 100, 300, 20);
		cbRegion.setBounds(200, 150, 300, 20);

		//panel03 = new JPanel();
		//panel03.setLayout(null);
		panel02.add(btnOK);
		panel02.add(btnCancel);
		btnOK.setBounds(200, 200, 80, 50);
		btnCancel.setBounds(350, 200, 80, 50);


		panelMain.add(panel01, BorderLayout.NORTH);
		panelMain.add(panel02, BorderLayout.CENTER);

		//pack();
		//setVisible(true);


	}//생성자
/*
	public static void main(String[] args) {

		new ClientLogin();

	}//main
*/
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnOK) {
			//new ClientPage2();
			//new Choose01();
			setVisible(false);
			//System.out.println("test");
		}else if(e.getSource() == btnCancel){
			//System.out.println("test btnCancel");
//			dispose();
		}
	}

}
