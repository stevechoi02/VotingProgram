package main.ui.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

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
  
    /**
     * Instantiates a new Client vote.
     */
    ServerDAO dao = new ServerDAO();
    public ClientVote(String Election){
        setLayout(new BorderLayout(10,10));
        JPanel labels = new JPanel(new GridLayout(2,3,3,3));
        labels.setBorder(new LineBorder(Color.BLACK));
        //updateCandList(Election);
        
        for(int i=1;i<=6;i++) {
        	candidates.add(dao.getCandNameByNum(Election, i));
        	
        }
        

        for(String candidate:candidates){
            labels.add(makeCandPanel(candidate, Election));
        }
        

        lblTitle = new JLabel(Election);

		btnVote = new JButton("투표");
		btnVote.addActionListener(this);
        //btnCancel = new JButton("취소");
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

   

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ClientVote cVote = new ClientVote("삼국지");
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

	


	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<=5;i++) {
			if(e.getSource() == radio.get(i)) {
				selectedCand = radio.get(i).getText();
				System.out.println(selectedCand);
			}
	
		}
		if(selectedCand==null) {
			ElecJDialogGUI.messageBox(this, "후보를 골라주세요.");
		}else if(e.getSource()==btnVote) {
			System.out.println(ElecName);
			dao.addCandSelected(ElecName, selectedCand);
			ElecJDialogGUI.messageBox(this, selectedCand+ " 님에게 투표하셨습니다.");
			System.exit(0);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		
	}

}
