package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener{

	MainFrame mf;
	
	public ButtonListener(MainFrame mf){
		
		this.mf = mf;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("NEW GAME")) {
			
			mf.startGame();
		
		}
		else if (e.getActionCommand().equals("LOAD GAME")) {
			mf.startGame();
			SaveLoadManager.loadGame(mf.gamePanel);
		}
		else if (e.getActionCommand().equals("SAVE AND EXIT")) {
			SaveLoadManager.saveGame(mf.gamePanel);
			mf.returnToMenu();
		}	
	}		
}