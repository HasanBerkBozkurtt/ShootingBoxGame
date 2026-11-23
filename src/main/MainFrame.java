package main;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public int state;
	public int menuState = 0;
	public int playState = 1;
	public int pauseState = 2;

	MenuPanel menuPanel;
	Panel gamePanel;
	PausePanel pausePanel;
	
	public MainFrame() {
		
		super("Shooting Box Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		state = menuState;
		
		menuPanel = new MenuPanel(this);
		gamePanel = new Panel(this);
		pausePanel = new PausePanel(this);
		
		setContentPane(menuPanel);
		
		pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
	}
	
	public void startGame() {
		
		state = playState;
		
		remove(menuPanel);
		
		setContentPane(gamePanel);
		
		pack();
		
		gamePanel.currentLevel = 1;
		
		LevelHandler.loadLevel(gamePanel, gamePanel.currentLevel);
		
		revalidate();
		
		repaint();
		
		gamePanel.startMainThread();
		
		gamePanel.requestFocusInWindow();
	
	}
	
	public void pauseGame() {
		
		state = pauseState;
		
		remove(gamePanel);
	
		setContentPane(pausePanel);
	
		revalidate();
		
		repaint();
		
		pausePanel.requestFocusInWindow();
		
	}
	
	public void returnToMenu() {
		
		state = menuState;
		
		remove(pausePanel);
		
		setContentPane(menuPanel);
		
		revalidate();
		
		repaint();
	
	}
	
	public void togglePause() {
		if (state == playState) {
			
			pauseGame();
			
		} else if (state == pauseState) {
			
			state = playState;
			
			remove(pausePanel);
			
			setContentPane(gamePanel);
			
			revalidate();
			
			repaint();
			
			gamePanel.requestFocusInWindow(); 
		
		}
	}
}