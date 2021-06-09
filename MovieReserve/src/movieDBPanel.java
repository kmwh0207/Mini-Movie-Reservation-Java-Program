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

public class movieDBPanel extends JPanel {
	public JPanel pan[] = new JPanel[2];
	public JButton insert = new JButton("추가");
	public JButton delete = new JButton("삭제");
	public JTextField ja1 = new JTextField(10);
	public JTextField ja2 = new JTextField(10);
	public JLabel lb1 = new JLabel("영화제목 : ");
	public JLabel lb2 = new JLabel("가격 : ");
	
	String header[] = {"영화번호", "영화 제목", "가격"};
	public DefaultTableModel model = new DefaultTableModel(header,0);
	public JTable table = new JTable(model);

	
	public movieDBPanel() {
		this.setLayout(new GridLayout(2,0));
		pan[0] = new JPanel();
		pan[1] = new JPanel(new GridLayout(3,0));
		
		JScrollPane sp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(400,300));	
		table.getColumn("영화번호").setPreferredWidth(70);
		table.getColumn("영화 제목").setPreferredWidth(150);
		table.getColumn("가격").setPreferredWidth(60);

		pan[0].add(sp);
		pan[1].add(lb1);
		pan[1].add(ja1);
		pan[1].add(lb2);
		pan[1].add(ja2);
		pan[1].add(insert);
		pan[1].add(delete);

		this.add(pan[0]);
		this.add(pan[1]);
	}
}
