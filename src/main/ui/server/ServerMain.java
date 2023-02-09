package main.ui.server;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.security.acl.Group;

public class ServerMain extends JPanel {
    //제목
    private JLabel lblTitle;
    //선거추가, 후보추가, 결과발표, 나가기
    private JPanel pnlMain;
    private JLabel lblElec, lblCand, lblRes, lblExit;

    public ServerMain(){
        setSize(1000,600);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lblElec = new JLabel("선거추가",SwingConstants.CENTER);
        lblCand = new JLabel("후보추가",SwingConstants.CENTER);
        lblRes = new JLabel("결과발표",SwingConstants.CENTER);
        lblExit = new JLabel("나가기",SwingConstants.CENTER);

        lblElec.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        lblCand.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        lblRes.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        lblExit.setFont(new Font(Font.DIALOG, Font.BOLD, 50));


        lblElec.setPreferredSize(new Dimension(200,200));
        lblCand.setPreferredSize(new Dimension(200,200));
        lblRes.setPreferredSize(new Dimension(200,200));
        lblExit.setPreferredSize(new Dimension(200,200));

        lblElec.setBorder(new LineBorder(Color.BLACK));
        lblCand.setBorder(new LineBorder(Color.BLACK));
        lblRes.setBorder(new LineBorder(Color.BLACK));
        lblExit.setBorder((new LineBorder(Color.BLACK)));

        c.insets = new Insets(0,0,0,10);
        add(lblElec,c);
        add(lblCand,c);

        add(lblRes,c);

        add(lblExit,c);

    }
}
