package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import main.Manager.CandVO;
import main.Manager.ElecJDialogGUI;
import main.dao.ServerDAO;

/**
 * The type Client vote image.
 */

/**
 * The type Client vote.
 */
public class ClientVote extends JPanel implements ActionListener, ItemListener {
    private JLabel lblTitle;
    //private JButton btnOk, btnCancel;
    private JButton btnVote;
    private ArrayList<String> candidates = new ArrayList<>();
    private static final ButtonGroup btnGroup = new ButtonGroup();
    
    public JRadioButton btn;
    public ArrayList<JRadioButton> radio = new ArrayList<>();
    public String selectedCand;
    
    public String ElecName;
    public int elecNum;
  
    /**
     * Instantiates a new Client vote.
     */
    ServerDAO dao = new ServerDAO();
	private ArrayList<CandVO> cands;
	
    public ClientVote(String Election, int elecNum){
        setLayout(new BorderLayout(10,10));
        JPanel labels = new JPanel(new GridLayout(0,3,3,3));
        labels.setBorder(new LineBorder(Color.BLACK));
        //updateCandList(Election);
        
       /* for(int i=1;i<=6;i++) {
        	candidates.add(dao.getCandNameByNum(Election, i));
        	
        }*/
        cands = dao.getCandListbyElecNum(elecNum);

        for(CandVO cand: cands){
            labels.add(makeCandPanel(cand.getCand_Name(), cand.getElec_Name()));
        }
        

       /* 
        * for(String candidate:candidates){
            labels.add(makeCandPanel(candidate, Election));
        }
       */
        

        lblTitle = new JLabel(Election);

		btnVote = new JButton("??????");
		btnVote.addActionListener(this);
        //btnCancel = new JButton("??????");
        //btnCancel.addActionListener(this);

        add(lblTitle,BorderLayout.NORTH);
        add(labels, BorderLayout.CENTER);
        add(btnVote, BorderLayout.SOUTH);
       

        //JPanel buttonPane = new JPanel();
        //buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.Y_AXIS));
        //buttonPane.add(Box.createVerticalGlue());
        //buttonPane.add(btnOk);
        //buttonPane.add(btnCancel);

        //add(buttonPane,BorderLayout.EAST);
        
        ElecName=Election;
        this.elecNum = elecNum;
    }

    protected JComponent makeCandPanel(String candidate, String Election){
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JLabel label = new JLabel();
        System.out.println(candidate);
        System.out.println(Election);
        
        BufferedImage img = dao.getCandImage(candidate, Election);
        img.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        label.setIcon(icon);
       
        btn = new JRadioButton(candidate);
        btnGroup.add(btn);
        btn.addActionListener(this);
        radio.add(btn);
        

        panel.add(label,BorderLayout.CENTER);
        panel.add(btn,BorderLayout.SOUTH);
        return panel;
    }

    protected void updateCandList(String Election){
    	

    }

   
/*
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ClientVote cVote = new ClientVote("?????????");
        frame.setContentPane(cVote);
        frame.pack();
        frame.setLocationRelativeTo(null);
        try {
            frame.setLocationByPlatform(true);
            frame.setMinimumSize(frame.getSize());
        } catch(Throwable ignoreAndContinue) {
        }

        frame.setVisible(true);
    }
*/
	


	@Override
	public void actionPerformed(ActionEvent e) {
		for(Enumeration<AbstractButton> btns= btnGroup.getElements(); btns.hasMoreElements();) {
            AbstractButton btn = btns.nextElement();
            if(btn.isSelected()){
                selectedCand = btn.getText();
            }
        }
		if(selectedCand==null) {
			ElecJDialogGUI.messageBox(this, "????????? ???????????????.");
		}else if(e.getSource()==btnVote) {
			System.out.println(ElecName);
			dao.addCandSelected(ElecName, selectedCand);
			ElecJDialogGUI.messageBox(this, selectedCand+ " ????????? ?????????????????????.");
			ClientCards.controller.getView("Main");
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		
	}

}
