package main.ui.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The type Client vote image.
 */
class ClientVoteImage extends JPanel{
    //후보 리스트
    private final ArrayList<JPanel> candidates = new ArrayList<>();

    /**
     * Instantiates a new Client vote image.
     */
    ClientVoteImage(){
        //패널 사이즈, 레이아웃 설정
        setSize(400,300);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        //패널에 후보패널 추가
        for(JPanel candidate: candidates) add(candidate);
    }

    /**
     * CandidatePanel를 후보 리스트에 추가
     *
     * @param panel the panel
     */
    public void addImage(JPanel panel){
        candidates.add(panel);
    }
}

/**
 * The type Client vote.
 */
public class ClientVote extends JPanel {
    private JLabel lblTitle;
    private ClientVoteImage pnlCand;
    private JButton btnOk, btnCancel;

    /**
     * Instantiates a new Client vote.
     */
    public ClientVote(String Election){
        setSize(600,400);
        setLayout(new BorderLayout());
        lblTitle = new JLabel(Election);
        pnlCand = new ClientVoteImage();
        btnOk = new JButton("ok");
        btnCancel = new JButton("cancel");

        add(lblTitle,BorderLayout.NORTH);
        add(pnlCand,BorderLayout.WEST);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.PAGE_AXIS));
        buttonPane.add(Box.createVerticalGlue());
        buttonPane.add(btnOk);
        buttonPane.add(btnCancel);

        add(buttonPane);
    }

    public ClientVoteImage getPnlCand(){
        return pnlCand;
    }

}
