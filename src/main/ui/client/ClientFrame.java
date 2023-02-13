package main.ui.client;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame{
    public static ClientCards cCards;

    ClientFrame(){
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        cCards = new ClientCards();
        setLayout(new BorderLayout());
        add(cCards);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //패널 상태를 보기위한 임시적인 메인 함수
    public static void main(String[] args) {
        new ClientFrame();
    }

}
