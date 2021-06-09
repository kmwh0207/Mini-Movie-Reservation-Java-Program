package FrozenForestCinema.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import FrozenForestCinema.Frame.CheckUserFrame;
import FrozenForestCinema.Frame.LoginPanel;

public class LoginAction implements ActionListener {
	
	LoginPanel l_panel;
	public LoginAction(LoginPanel panel){
		l_panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if((JButton)event == l_panel.bt_member) {
			System.out.println("·Î±×ÀÎ");
			new CheckUserFrame(1);
		}else if((JButton)event == l_panel.bt_nonmember) {
			new CheckUserFrame(2);
		}
	}

}
