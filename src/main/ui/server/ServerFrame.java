package main.ui.server;

import javax.swing.*;

public class ServerFrame extends JFrame {
    ServerFrame(){
        setSize(1000,600);

        ServerMain sMain = new ServerMain();
        ServerElec sElec = new ServerElec();
        ServerCand sCand = new ServerCand();

        add(sCand);

        setVisible(true);
    }
    //패널 상태를 보기위한 임시적인 메인 함수
    public static void main(String[] args) {
        new ServerFrame();
    }
}
