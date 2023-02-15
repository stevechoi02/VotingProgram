
/*
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
*/


package main.ui.server;

import main.ui.server.UserJDialogGUI;
import main.dao.ServerDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ServerFrame {
    private final JFrame frame;

    private final JPanel gui;

    private ServerDAO dao = new ServerDAO();

    private DefaultTableModel model;
    private JTable table;

    public ServerFrame(){
        frame = new JFrame("투표 관리 프로그램 서버 GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui = new JPanel(new BorderLayout(5, 5));
        gui.setBorder( new TitledBorder("서버 관리 목록") );

        final JPanel labels = new JPanel(new GridLayout(0,3,3,3));
        labels.setBorder(
                new EmptyBorder(10,10,10,10));
        JLabel title = new JLabel("후보 리스트");

        JPanel dynamicLabels = new JPanel(new BorderLayout(4,4));
        dynamicLabels.add(title, BorderLayout.NORTH);
        dynamicLabels.add( new JScrollPane(labels), BorderLayout.CENTER );
        gui.add(dynamicLabels, BorderLayout.WEST);

        class TabbedPane extends JPanel {
            public TabbedPane() {
                super(new GridLayout(1, 1));

                JTabbedPane tabbedPane = new JTabbedPane();

                JComponent elecPanel = makeElecPanel();
                tabbedPane.addTab("선거", elecPanel);

                JComponent candPanel = makeCandPanel();
                tabbedPane.addTab("후보", candPanel);

                JComponent votePanel = makeVotePanel();
                tabbedPane.addTab("투표", votePanel);

                JComponent stgPanel = makeStgPanel();
                tabbedPane.addTab("설정", stgPanel);

                //Add the tabbed pane to this panel.
                add(tabbedPane);

                //The following line enables to use scrolling tabs.
                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            }

            protected JComponent makeElecPanel() {
                JPanel panel = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                JButton add = new JButton("추가");
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new UserJDialogGUI(model,table, "서버 선거 추가");
                        
                    }
                });
                
                JButton edit = new JButton("수정");
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new UserJDialogGUI(model,table, "서버 선거 수정");
                        
                    }
                });
                JButton delete = new JButton("삭제");
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row =table.getSelectedRow();
                        int index = (int) table.getValueAt(row, 0);
                        if(UserJDialogGUI.massageConfirmBox(this, "정말 삭제하시겠습니까?")==0) {
                            if(dao.elecDelete(index)>0) {
                                dao.serverElecSelectAll(model);
                                if(model.getRowCount()>0) {
                                    table.setRowSelectionInterval(0, 0);
                                }
                            }
                        }
                    }
                });
                
                JPanel searchPanel = new JPanel(new GridLayout());
                JTextField txtBar = new JTextField();
                JButton btn = new JButton("검색");
                searchPanel.add(txtBar);
                searchPanel.add(btn);

                panel.add(add);
                panel.add(edit);
                panel.add(delete);
                panel.add(searchPanel);

                return panel;
            }

            protected JComponent makeCandPanel() {
                JPanel panel = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                JButton add = new JButton("추가");
                JButton edit = new JButton("수정");
                JButton delete = new JButton("삭제");

                add.addActionListener( new ActionListener(){

                    private int labelCount = 0;

                    public void actionPerformed(ActionEvent ae) {

                        labels.add( new JLabel("후보 " + ++labelCount) );
                        frame.validate();
                    }
                } );

                panel.add(add);
                panel.add(edit);
                panel.add(delete);

                return panel;
            }

            protected JComponent makeVotePanel() {
                JPanel panel = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                JButton result = new JButton("결과 발표");
                JButton delete = new JButton("삭제(?)");

                panel.add(result);
                panel.add(delete);

                return panel;
            }

            protected JComponent makeStgPanel() {
                JPanel plafComponents = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                final UIManager.LookAndFeelInfo[] plafInfos =
                        UIManager.getInstalledLookAndFeels();
                String[] plafNames = new String[plafInfos.length];
                for (int ii = 0; ii < plafInfos.length; ii++) {
                    plafNames[ii] = plafInfos[ii].getName();
                }
                final JComboBox plafChooser = new JComboBox(plafNames);
                plafComponents.add(plafChooser);

                final JCheckBox pack = new JCheckBox("Pack on PLAF change", true);
                plafComponents.add(pack);

                plafChooser.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        int index = plafChooser.getSelectedIndex();
                        try {
                            UIManager.setLookAndFeel(
                                    plafInfos[index].getClassName());
                            SwingUtilities.updateComponentTreeUI(frame);
                            if (pack.isSelected()) {
                                frame.pack();
                                frame.setMinimumSize(frame.getSize());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                return plafComponents;
            }
        }

        TabbedPane tpd = new TabbedPane();
        gui.add(tpd, BorderLayout.NORTH);

        String[] header = {"ID", "선거", "시작 날짜", "개표 날짜"};
        /*String[] a = new String[0];
        String[] names = System.getProperties().
                stringPropertyNames().toArray(a);
        String[][] data = new String[names.length][2];
        for (int ii=0; ii<names.length; ii++) {
            data[ii][0] = names[ii];
            data[ii][1] = System.getProperty(names[ii]);
        }*/
        model = new DefaultTableModel(header, 0);
        dao.serverElecSelectAll(model);

        table = new JTable(model);
        try {
            // 1.6+
            table.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        JScrollPane tableScroll = new JScrollPane(table);
        Dimension tablePreferred = tableScroll.getPreferredSize();
        tableScroll.setPreferredSize(
                new Dimension(tablePreferred.width, tablePreferred.height/3) );

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBorder(
                new TitledBorder("후보 정보") );

        BufferedImage bi = new BufferedImage(
                200,200,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        GradientPaint gp = new GradientPaint(
                20f,20f,Color.red, 180f,180f,Color.yellow);
        g.setPaint(gp);
        g.fillRect(0,0,200,200);
        ImageIcon ii = new ImageIcon(bi);
        JLabel imageLabel = new JLabel(ii);
        imagePanel.add( imageLabel, null );

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                tableScroll,
                new JScrollPane(imagePanel));
        gui.add( splitPane, BorderLayout.CENTER );

        frame.setContentPane(gui);

        frame.pack();
        frame.setResizable(true);

        frame.setLocationRelativeTo(null);
        try {
            frame.setLocationByPlatform(true);
            frame.setMinimumSize(frame.getSize());
        } catch(Throwable ignoreAndContinue) {
        }
        frame.setVisible(true);
    }
    //패널 상태를 보기위한 임시적인 메인 함수
    public static void main(String[] args) {
        new ServerFrame();
    }
}