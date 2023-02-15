
/*
package main.ui.server;

import main.Manager.UserJDialogGUI;
import main.dao.ServerDAO;
import main.dao.ServerVO;

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
    private final JPanel labels;

    private ServerDAO dao = new ServerDAO();
    private ServerVO vo = new ServerVO();

    private DefaultTableModel model;
    private JTable table;

    public ServerFrame(){
        frame = new JFrame("투표 관리 프로그램 서버 GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui = new JPanel(new BorderLayout(5, 5));
        gui.setBorder( new TitledBorder("서버 관리 목록") );

        labels = new JPanel(new GridLayout(2,3,3,3));
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

                final JCheckBox pack = new JCheckBox("선택시 Pack", true);
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
        String[] a = new String[0];
        String[] names = System.getProperties().
                stringPropertyNames().toArray(a);
        String[][] data = new String[names.length][2];
        for (int ii=0; ii<names.length; ii++) {
            data[ii][0] = names[ii];
            data[ii][1] = System.getProperty(names[ii]);
        }

import main.dao.ServerDAO;

import javax.swing.*;
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
                new TitledBorder("GridBagLayout()") );

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
*/


package main.ui.server;

import main.Manager.CandJDialogGUI;
import main.Manager.ElecJDialogGUI;
import main.dao.ServerDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ServerFrame {
    private final JFrame frame;

    private final JPanel gui;

    private ServerDAO dao = new ServerDAO();

    private DefaultTableModel elecModel;
    private DefaultListModel<ImageIcon> candModel;
    private JTable elecTable;
    private JList<ImageIcon> candList;

    public ServerFrame(){
        frame = new JFrame("투표 관리 프로그램 서버 GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui = new JPanel(new BorderLayout(5, 5));
        gui.setBorder( new TitledBorder("서버 관리 목록") );

        candModel = new DefaultListModel<ImageIcon>();

        candList = new JList<ImageIcon>(candModel);
        candList.setLayout(new GridLayout(0,3,3,3));
        candList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println(candList.getSelectedIndex());
                }
            }
        });
        

        /*final JPanel labels = new JPanel(new GridLayout(0,3,3,3));
        labels.setBorder(
                new EmptyBorder(10,10,10,10));*/
        JLabel title = new JLabel("후보 리스트");

        JPanel dynamicLabels = new JPanel(new BorderLayout(4,4));
        dynamicLabels.add(title, BorderLayout.NORTH);
        dynamicLabels.add( new JScrollPane(candList), BorderLayout.CENTER );
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
                        new ElecJDialogGUI(elecModel,elecTable, "서버 선거 추가");
                        
                    }
                });
                
                JButton edit = new JButton("수정");
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new ElecJDialogGUI(elecModel,elecTable, "서버 선거 수정");
                        
                    }
                });
                JButton delete = new JButton("삭제");
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row =elecTable.getSelectedRow();
                        int index = (int) elecTable.getValueAt(row, 0);
                        if(ElecJDialogGUI.massageConfirmBox(this, "정말 삭제하시겠습니까?")==0) {
                            if(dao.elecDelete(index)>0) {
                                dao.serverElecSelectAll(elecModel);
                                if(elecModel.getRowCount()>0) {
                                    elecTable.setRowSelectionInterval(0, 0);
                                }
                            }
                        }
                    }
                });
                
                JPanel searchPanel = new JPanel(new GridLayout());
                JTextField txtBar = new JTextField(10);
                JButton btn = new JButton("검색");
                searchPanel.add(txtBar);
                searchPanel.add(btn);

                panel.add(add);
                panel.add(edit);
                panel.add(delete);
                panel.add(searchPanel);

                return panel;
            }

            public JComponent makeCandPanel() {
                JPanel panel = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                JButton add = new JButton("추가");
                JButton edit = new JButton("수정");
                JButton delete = new JButton("삭제");

                add.addActionListener( new ActionListener(){

                    private int labelCount = 0;
                    
                    public void actionPerformed(ActionEvent ae) {
                    	
                        
                        new CandJDialogGUI(elecModel, elecTable, "선거 후보 등록");

                        candModel.addElement(new ImageIcon("mt01.jpeg"));
                        frame.validate();
                    }
                } );
                
                edit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new CandJDialogGUI(elecModel, elecTable, "선거 후보 수정");
						
					}
				});
                
                delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int row =elecTable.getSelectedRow();
						String elecName = elecTable.getValueAt(row, 1).toString();
						if(CandJDialogGUI.massageConfirmBox(this, "정말 후보를 삭제하시겠습니까?")==0) {
                            if(dao.candDelete(elecName /*, 후보list인자*/)>0) {
                                //dao.serverElecSelectAll(elecModel);
                                if(elecModel.getRowCount()>0) {
                                    elecTable.setRowSelectionInterval(0, 0);
                                }
                            }
                        }
						
					}
				});
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
        elecModel = new DefaultTableModel(header, 0);
        dao.serverElecSelectAll(elecModel);
        elecTable = new JTable(elecModel);
        try {
            elecTable.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        JScrollPane tableScroll = new JScrollPane(elecTable);
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