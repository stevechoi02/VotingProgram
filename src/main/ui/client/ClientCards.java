package main.ui.client;

import main.controller.CardController;

import javax.swing.*;
import java.awt.*;

public class ClientCards extends JPanel{
    private static final CardLayout cardLayout = new CardLayout();
    private static final JPanel view = new JPanel(cardLayout);
    static final CardController controller = new CardController(view, cardLayout);
    public ClientCards() {
        setLayout(new BorderLayout());

        ClientElec cElec = new ClientElec();
        ClientLogin cLogin = new ClientLogin();
        ClientMain cMain = new ClientMain();
        //ClientVote cVote = new ClientVote("Election");


        controller.addView(cMain, "Main");
        controller.addView(cLogin, "Login");
        controller.addView(cElec, "Election");
       // controller.addView(cVote, "Vote");
        controller.getView("Login");
        validate();

        add(view);
    }

    public static void getView(String name){
        controller.getView(name);
    }
}
