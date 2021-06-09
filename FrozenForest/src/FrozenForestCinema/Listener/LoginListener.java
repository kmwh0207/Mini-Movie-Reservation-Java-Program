package FrozenForestCinema.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import FrozenForestCinema.Frame.CheckUserFrame;
import FrozenForestCinema.lib.*;

public class LoginListener implements ActionListener {
	Object event;
	CheckUserFrame panel;
	
	public LoginListener(CheckUserFrame panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		event = e.getSource();
		if(event == panel.bt[1]) {
			DBConnection connection = new DBConnection(1,panel.name_field.getText(),panel.phone_field.getText(),panel.age_field.getText());
			connection.start();
			panel.dispose();
		}
	}

}
