package FrozenForestCinema.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FrozenForestCinema.Frame.SelectFrame;
import FrozenForestCinema.lib.DBConnection;

public class SelectListener implements ItemListener, ListSelectionListener, ActionListener{
	private JCheckBox[] checkbox;
	private String[][] movielist;
	private SelectFrame frame;
	public SelectListener(JCheckBox[] checkbox,String[][] movielist,SelectFrame frame) {
		this.checkbox = checkbox;
		this.movielist = movielist;
		this.frame = frame;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object event = e.getItem();
		for(int i=0; i<checkbox.length; i++) {
			if(event == checkbox[i] && e.getStateChange() == ItemEvent.SELECTED) {
				SelectFrame.selected_movie=movielist[i][0];
				SelectFrame.price=movielist[i][2];
			}
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList timelist = (JList) e.getSource();
		if(e.getValueIsAdjusting() == true) {
			SelectFrame.selected_time = timelist.getSelectedValue().toString();
			DBConnection loadseat=new DBConnection(2,SelectFrame.selected_movie,SelectFrame.selected_time);
			SwingUtilities.invokeLater(loadseat);
			//loadseat.start();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button == SelectFrame.bt_yes) {
			int seat_count=0;
			for(int count=1; count<SelectFrame.seat_box.length; count++) {
				if(SelectFrame.seat_box[count].isSelected()) {
					SelectFrame.selected_seat.add(count);
					seat_count++;
					System.out.println("������ �ο� ��:"+SelectFrame.headcount+" ,������ �ڸ���:"+seat_count+" ,���õ� �ڸ�:"+count);
				}
			}
			if(seat_count != SelectFrame.headcount) {
				SelectFrame.selected_seat.clear();
				JOptionPane.showMessageDialog(frame,"�ο����� ������ �ڸ��� ������ �����ּ���.");
			}else {
				JOptionPane.showMessageDialog(frame,"���Ÿ� �����մϴ�.");
				JOptionPane.showMessageDialog(frame,"�� ����� "+SelectFrame.headcount*Integer.valueOf(SelectFrame.price)+"�� �Դϴ�.");
				DBConnection order = new DBConnection(frame,SelectFrame.selected_seat,SelectFrame.phone,SelectFrame.selected_movie,SelectFrame.selected_time);
				SwingUtilities.invokeLater(order);
				//order.start();
			}
		}
	}

}
