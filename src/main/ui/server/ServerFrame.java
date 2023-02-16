package main.ui.server;


import main.Manager.CandJDialogGUI;
import main.Manager.ElecJDialogGUI;
import main.dao.ServerDAO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Date;

public class ServerFrame {
    private final JFrame frame;

    private final ServerDAO dao = new ServerDAO();

    private final DefaultTableModel elecModel;
    private final DefaultListModel<String> candModel;
    private final JTable elecTable;
    private final JList<String> candList;

    //현재 선거 변수
    private int currElec;

    public ServerFrame(){
        frame = new JFrame("투표 관리 프로그램 서버 GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gui = new JPanel(new BorderLayout(5, 5));
        gui.setBorder( new TitledBorder("서버 관리 목록") );

        candModel = new DefaultListModel<String>();
        candModel.addElement("선거를 선택하세요!");
        candList = new JList<String>(candModel);

        candList.setFixedCellHeight(50);
        //candList.setFixedCellWidth(100);

        JPanel dynamicLabels = new JPanel(new BorderLayout(4,4));
        dynamicLabels.setBorder(new TitledBorder("후보 리스트"));
        JScrollPane scroll = new JScrollPane();
        dynamicLabels.add(scroll.add(candList), BorderLayout.CENTER);
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

                add(tabbedPane);

                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            }

            //선거 탭 생성
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
                        if(elecTable.getSelectedRow()==-1){
                            ElecJDialogGUI.messageBox(null,"선거를 선택해주세요!");
                        }else{
                            new ElecJDialogGUI(elecModel,elecTable, "서버 선거 수정");
                        }
                    }
                });
                JButton delete = new JButton("삭제");
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (elecTable.getSelectedRow() == -1) {
                            ElecJDialogGUI.messageBox(null, "선거를 선택해주세요!");
                        } else {
                            int row = elecTable.getSelectedRow();
                            int index = (int) elecTable.getValueAt(row, 0);
                            String name = (String) elecTable.getValueAt(row, 1);
                            if (ElecJDialogGUI.massageConfirmBox(this, name + "를 삭제하시겠습니까?") == 0) {
                                if (dao.elecDelete(index) > 0) {
                                    dao.candDeleteByElecNum(index);
                                    dao.serverElecSelectAll(elecModel);
                                    if (elecModel.getRowCount() > 0) {
                                        elecTable.setRowSelectionInterval(0, 0);
                                    }
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

            //후보 탭 생성
            protected JComponent makeCandPanel() {
                JPanel panel = new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 3, 3));

                JButton add = new JButton("추가");
                JButton edit = new JButton("수정");
                JButton delete = new JButton("삭제");

                add.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        if(elecTable.getSelectedRow()==-1) {
                            CandJDialogGUI.messageBox(null,"선거를 선택해주세요!");
                        } else {
                            new CandJDialogGUI(candModel, candList, "등록", currElec);
                            /*dynamicLabels.repaint();
                            dynamicLabels.revalidate();*/
                        }
                    }
                } );

                edit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
                        if (candList.isSelectionEmpty()) {
                            CandJDialogGUI.messageBox(null,"후보를 선택해주세요!");
                        } else {
                            new CandJDialogGUI(candModel, candList, "수정", currElec);
                        }
					}
				});

                delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
                        if (candList.isSelectionEmpty()) {
                            CandJDialogGUI.messageBox(null,"후보를 선택해주세요!");
                        } else {
                            int row = candList.getSelectedIndex();
                            String candName = candList.getSelectedValue();
                            if (CandJDialogGUI.massageConfirmBox(this, candName+"을(를)) 삭제하시겠습니까?") == 0) {
                                if (dao.candDelete(row, currElec) > 0) {
                                    candModel.remove(row);
                                }
                            }
                            dao.serverCandSelectAll(candModel, currElec);
                        }
                    }
				});
                panel.add(add);
                panel.add(edit);
                panel.add(delete);
                return panel;
            }

            //투표 탭 생성
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
                final JComboBox<String> plafChooser = new JComboBox<String>(plafNames);
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

        //선거 리스트 테이블 생성
        String[] header = {"ID", "선거", "시작 날짜", "개표 날짜"};

        elecModel = new DefaultTableModel(header, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return Integer.class;
            return super.getColumnClass(column);
        }
        };

        dao.serverElecSelectAll(elecModel);
        elecTable = new JTable(elecModel);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        elecTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

        elecTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if(!e.getValueIsAdjusting()) {
                        int viewRow = elecTable.getSelectedRow();
                        int modelRow = elecTable.convertRowIndexToModel(viewRow);
                        currElec = (int) elecTable.getModel().getValueAt(modelRow, 0);
                        dao.serverCandSelectAll(candModel, currElec);
                    }
                }catch(Exception ignore){
                    ignore.printStackTrace();
                }
            }
        });

        try {
            elecTable.setAutoCreateRowSorter(true);
        } catch(Exception ignored) {
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
        imagePanel.add(imageLabel, null);

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