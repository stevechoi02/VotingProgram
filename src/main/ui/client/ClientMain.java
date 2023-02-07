package main.ui.client;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ClientMain 클래스
 */
public class ClientMain extends JFrame {
    GridBagLayout gbl;
    JPanel vote;

    /**
     * ClientMain 생성자
     */
    public ClientMain() {
		super("메인 ui");//프레임 제목
		setSize(1000,600);//실행했을때 켜지는 컨테이너 크기 설정
		setVisible(true);
		
		setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER,20,30));
		
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
		 
	JPanel VoteBtn = new JPanel();
	
	JButton btn1 = new JButton("선거선택");
	JButton btn2 = new JButton("투표");
	JButton btn3 = new JButton("내 투표 확인");
	JButton btn4 = new JButton("나가기");
	
	VoteBtn.add(btn1);
	VoteBtn.add(btn2);
	VoteBtn.add(btn3);
	VoteBtn.add(btn4);
	
	add(VoteBtn);
	 
    setVisible(true);
	}

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
	 ClientMain m= new ClientMain();
	m.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}

}
