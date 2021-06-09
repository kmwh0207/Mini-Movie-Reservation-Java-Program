import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class reserveDBPanel extends JPanel {

	public JPanel pan[] = new JPanel[2];
	public JButton insert = new JButton("추가");
	public JButton delete = new JButton("삭제");
	public JTextField ja1 = new JTextField();
	public JTextField ja2 = new JTextField();
	public JTextField ja3 = new JTextField();
	public JTextField ja4 = new JTextField();
	public JLabel lb1 = new JLabel("전화번호 : ");
	public JLabel lb2 = new JLabel("영화번호 : ");
	public JLabel lb3 = new JLabel("시간 : ");
	public JLabel lb4 = new JLabel("좌석 : ");
	
	String header[] = {"예약번호", "전화번호", "영화번호", "시간", "좌석"};
	public DefaultTableModel model = new DefaultTableModel(header,0);
	public JTable table = new JTable(model);
	
	
	public reserveDBPanel() {
		
		this.setLayout(new GridLayout(2,0));
		pan[0] = new JPanel();
		pan[1] = new JPanel(new GridLayout(5,0));
		
		JScrollPane sp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		sp.setPreferredSize(new Dimension(400,300));
		
		table.getColumn("예약번호").setPreferredWidth(70);
		table.getColumn("전화번호").setPreferredWidth(150);
		table.getColumn("영화번호").setPreferredWidth(60);
		table.getColumn("시간").setPreferredWidth(40);
		table.getColumn("좌석").setPreferredWidth(40);
		
		pan[0].add(sp);
		
		pan[1].add(lb1);
		pan[1].add(ja1);
		pan[1].add(lb2);
		pan[1].add(ja2);
		pan[1].add(lb3);
		pan[1].add(ja3);
		pan[1].add(lb4);
		pan[1].add(ja4);
		pan[1].add(insert);
		pan[1].add(delete);
		
		this.add(pan[0]);
		this.add(pan[1]);
		
	}
}
