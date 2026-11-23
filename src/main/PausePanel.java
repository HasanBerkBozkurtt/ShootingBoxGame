package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends JPanel {

	JButton saeButton;
	MainFrame frame;
	ButtonListener buttonList;
	
	public PausePanel(MainFrame frame) {
		this.frame = frame;
		this.buttonList = new ButtonListener(frame);
		
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		
		this.setFocusable(true);
		this.addKeyListener(new Keyboard(frame, frame.gamePanel));
		
		saeButton = new JButton("SAVE AND EXIT");
		saeButton.setPreferredSize(new Dimension(300, 200));
		saeButton.setFocusable(false);
		
		saeButton.addActionListener(buttonList);
		
		this.add(saeButton);
	}
}