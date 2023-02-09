package main.ui.server;

import static javax.swing.GroupLayout.Alignment.CENTER;

import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class test extends JFrame {
    public test() {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        JLabel avLbl = new JLabel("Available");
        JLabel tagsLbl = new JLabel("Tags");
        JLabel selLbl = new JLabel("Selected");

        JButton newBtn = new JButton("New");
        JButton moveBtn = new JButton(">>");
        JButton remBtn = new JButton("Remove");

        JList leftList = new JList();
        JScrollPane spleft = new JScrollPane(leftList);
        JList rightList = new JList();
        JScrollPane spright = new JScrollPane(rightList);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl
                .createParallelGroup(CENTER)
                .addComponent(tagsLbl)
                .addGroup(
                        gl.createSequentialGroup()
                                .addGroup(
                                        gl.createParallelGroup(CENTER).addComponent(avLbl)
                                                .addComponent(spleft, 100, 200, Short.MAX_VALUE)
                                                .addComponent(newBtn))
                                .addComponent(moveBtn)
                                .addGroup(
                                        gl.createParallelGroup(CENTER).addComponent(selLbl)
                                                .addComponent(spright, 100, 200, Short.MAX_VALUE)
                                                .addComponent(remBtn))));
        gl.setVerticalGroup(gl
                .createSequentialGroup()
                .addComponent(tagsLbl)
                .addGroup(
                        gl.createParallelGroup(CENTER)
                                .addGroup(
                                        gl.createSequentialGroup().addComponent(avLbl)
                                                .addComponent(spleft, 100, 250, Short.MAX_VALUE)
                                                .addComponent(newBtn))
                                .addComponent(moveBtn)
                                .addGroup(
                                        gl.createSequentialGroup().addComponent(selLbl)
                                                .addComponent(spright, 100, 250, Short.MAX_VALUE)
                                                .addComponent(remBtn))));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        test ex = new test();
        ex.setVisible(true);
    }
}