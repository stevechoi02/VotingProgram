package main.ui.client;

import main.controller.ClientUI.ClientMainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClientCards extends JPanel{
    private static final CardLayout cardLayout = new CardLayout();
    private static final JPanel view = new JPanel(cardLayout);
    static final ClientMainController controller = new ClientMainController(view, cardLayout);
    public ClientCards() {
        setLayout(new BorderLayout());

        ClientElec cElec = new ClientElec();
        ClientLogin cLogin = new ClientLogin();
        ClientMain cMain = new ClientMain();
        ClientVote cVote = new ClientVote("Election");


        controller.addView(cMain, "Main");
        controller.addView(cLogin, "Login");
        controller.addView(cElec, "Election");
        controller.addView(cVote, "Vote");

        controller.getView("Login");

        /*JPanel controls = new JPanel();
        JButton btnPrev = new JButton("<");
        JButton btnHome = new JButton("Home");
        JButton btnNext = new JButton(">");

        controls.add(btnPrev);
        controls.add(btnHome);
        controls.add(btnNext);

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextView();
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.previousView();
            }
        });
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goHome();
            }
        });*/

        add(view);
        /*add(controls, BorderLayout.SOUTH);*/

    }
}
