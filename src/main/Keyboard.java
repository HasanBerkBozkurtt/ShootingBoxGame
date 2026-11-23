package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	public boolean w, s, a, d;
	
	MainFrame mf;
	Panel gp;
	
	public Keyboard(MainFrame mf, Panel gp) {
		
		this.mf = mf;
		this.gp = gp;
	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {

		int dummy = e.getKeyCode();

		if (dummy == KeyEvent.VK_ESCAPE) {
			
			mf.togglePause();
			return;
			
		}
				
		if (mf.state == mf.playState && gp.player != null) {
			if (dummy == KeyEvent.VK_W) {
				gp.player.moveUp(); 
			}
			if (dummy == KeyEvent.VK_S) {
				gp.player.moveDown();
			}
			if (dummy == KeyEvent.VK_A) {
				gp.player.moveLeft();
			}
			if (dummy == KeyEvent.VK_D) {
				gp.player.moveRight();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}