package main.ui.client;

import com.sun.security.ntlm.Client;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    ClientFrame(){
        setSize(1000,600);
        setResizable(false);
        setLayout(new BorderLayout());

        ClientLogin cLogin = new ClientLogin();
        ClientVote cVote = new ClientVote("aa");
        ClientElec cElec = new ClientElec();
        ClientMain cMain = new ClientMain();

        this.add(cMain, BorderLayout.CENTER);

        CardLayout cards = new CardLayout();
        setVisible(true);
    }

    //패널 상태를 보기위한 임시적인 메인 함수
    public static void main(String[] args) {
        new ClientFrame();
    }
}
