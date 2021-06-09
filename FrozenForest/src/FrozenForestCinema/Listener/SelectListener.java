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
					System.out.println("선택한 인원 수:"+SelectFrame.headcount+" ,선택한 자리수:"+seat_count+" ,선택된 자리:"+count);
				}
			}
			if(seat_count != SelectFrame.headcount) {
				SelectFrame.selected_seat.clear();
				JOptionPane.showMessageDialog(frame,"인원수와 선택한 자리의 개수를 맞춰주세요.");
			}else {
				JOptionPane.showMessageDialog(frame,"예매를 진행합니다.");
				JOptionPane.showMessageDialog(frame,"총 요금은 "+SelectFrame.headcount*Integer.valueOf(SelectFrame.price)+"원 입니다.");
				DBConnection order = new DBConnection(frame,SelectFrame.selected_seat,SelectFrame.phone,SelectFrame.selected_movie,SelectFrame.selected_time);
				SwingUtilities.invokeLater(order);
				//order.start();
			}
		}
	}

}
