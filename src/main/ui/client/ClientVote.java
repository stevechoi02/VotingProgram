package main.ui.client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The type Client vote image.
 */

/**
 * The type Client vote.
 */
public class ClientVote extends JPanel implements ActionListener {
    private JLabel lblTitle;
    private JButton btnOk, btnCancel;
    private final ArrayList<String> candidates = new ArrayList<>();
    private static final ButtonGroup btnGroup = new ButtonGroup();
    /**
     * Instantiates a new Client vote.
     */
    public ClientVote(String Election){
        setLayout(new BorderLayout(10,10));
        JPanel labels = new JPanel(new GridLayout(2,3,3,3));
        labels.setBorder(new LineBorder(Color.BLACK));
        updateCandList();
        for(String candidate:candidates){
            labels.add(makeCandPanel(candidate));
        }

        labels.add(makeCandPanel("Test1"));
        labels.add(makeCandPanel("Test2"));
        labels.add(makeCandPanel("Test3"));

        lblTitle = new JLabel(Election);
        btnOk = new JButton("확인");
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);

        add(lblTitle,BorderLayout.NORTH);
        add(labels, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.Y_AXIS));
        buttonPane.add(Box.createVerticalGlue());
        buttonPane.add(btnOk);
        buttonPane.add(btnCancel);

        add(buttonPane,BorderLayout.EAST);
    }

    protected JComponent makeCandPanel(String candidate){
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JLabel label = new JLabel(candidate);
        JRadioButton btn = new JRadioButton();
        btnGroup.add(btn);

        panel.add(label,BorderLayout.CENTER);
        panel.add(btn,BorderLayout.SOUTH);
        return panel;
    }

    protected void updateCandList(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnCancel){
            ClientCards.controller.getView("Election");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ClientVote cVote = new ClientVote("테스트 선거1");
        frame.setContentPane(cVote);
        frame.pack();
        frame.setLocationRelativeTo(null);
        try {
            frame.setLocationByPlatform(true);
            frame.setMinimumSize(frame.getSize());
        } catch(Throwable ignoreAndContinue) {
        }

        frame.setVisible(true);
    }

}
