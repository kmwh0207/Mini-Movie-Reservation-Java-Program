package FrozenForestCinema;

import javax.swing.JFrame;

import FrozenForestCinema.Listener.LoginAction;
import FrozenForestCinema.Frame.LoginPanel;
import FrozenForestCinema.lib.ChangeFont;

public class MainFrame extends JFrame {
	LoginPanel loginpanel;
	
	public MainFrame() {
		this.setTitle("FrozenForestCinema");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,480);
		
		loginpanel = new LoginPanel();
		setContentPane(loginpanel);
		
		//this.pack();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
