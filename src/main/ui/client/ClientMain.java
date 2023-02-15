package main.ui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.Group;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ClientMain 클래스
 */
public class ClientMain extends JPanel implements ActionListener{
    GridBagLayout gbl;
    private JPanel vote;

	private JPanel VoteBtn;

	private JButton btn1, btn2, btn3, btn4;

    /**
     * ClientMain 생성자
     */
    public ClientMain() {
		//프레임 제목
		setSize(1000,800);//실행했을때 켜지는 컨테이너 크기 설정
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		JPanel vote= new JPanel(new GridLayout(2,15));
		vote.add(new JLabel("투표",JLabel.CENTER));
		
		vote.setBackground(Color.red);
		vote.setPreferredSize(new Dimension(100, 50));
		
		
		JPanel vote1 = new JPanel(new GridLayout(2,100));
		vote1.add(new JLabel("<html><body style='text-align:center;'>투표장에 오신걸 환영합니다.<br />투표버튼을 눌러서 투표를 시작해주십시오."
				+ "<br />한번만 투표가능하오니 신중하게 선택해주십시오.<br />투표가 끝나면 어느후보에게 <br />투표했는지 확인할 수 있습니다.<br />"
				+ "감사합니다.</body></html>", JLabel.CENTER));
		vote1.setBackground(Color.yellow);
		vote.setPreferredSize(new Dimension(100,50));
		 add(vote);
		 add(vote1);
		 
		 
	//버튼
		 
		VoteBtn = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		btn1 = new JButton("선거선택");
		btn2 = new JButton("투표");
		btn3 = new JButton("내 투표 확인");
		btn4 = new JButton("나가기");

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);

		c.gridwidth=GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		VoteBtn.add(btn1,c);
		VoteBtn.add(btn2,c);
		VoteBtn.add(btn3,c);
		VoteBtn.add(btn4,c);

	//	add(VoteBtn);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(vote1)
				.addComponent(vote)
				.addComponent(VoteBtn)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(vote)
				.addComponent(vote1)
				.addComponent(VoteBtn)
		);
		//setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1){
			ClientCards.controller.getView("Election");
		}
		if(e.getSource() == btn2){
			ClientCards.controller.getView("Vote");
		}
		if(e.getSource() == btn4){
			ClientCards.controller.getView("Login");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ClientMain cMain = new ClientMain();

		frame.add(cMain);
		frame.setSize(1000,800);
		frame.validate();
		frame.setVisible(true);
	}
}
