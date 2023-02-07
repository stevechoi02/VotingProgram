package main.ui.client;

import javax.swing.*;
import java.awt.*;

public class CandidatePanel extends JPanel {
    private JLabel image;
    private JRadioButton btn;

    CandidatePanel(ImageIcon icon){
        setLayout(new BorderLayout());
        image = new JLabel();
        image.setIcon(icon);
        btn = new JRadioButton();

        add(image,BorderLayout.NORTH);
        add(btn,BorderLayout.SOUTH);
    }
}
