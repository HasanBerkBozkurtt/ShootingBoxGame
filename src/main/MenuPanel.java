package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	JButton newGame;
	JButton loadGame;
	MainFrame frame;
	ButtonListener buttonList;
	
	public MenuPanel(MainFrame frame) {
    	
		this.frame = frame;
		
		buttonList = new ButtonListener(frame);
		
		setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(2, 1, 30, 30));
        
        newGame = new JButton("NEW GAME");
        newGame.setFocusable(false);
        newGame.addActionListener(buttonList);
        this.add(newGame);

        loadGame = new JButton("LOAD GAME");
        loadGame.setFocusable(false);
        loadGame.addActionListener(buttonList);
        this.add(loadGame);
           
	}
}