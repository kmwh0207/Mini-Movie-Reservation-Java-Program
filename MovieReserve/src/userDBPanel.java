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

public class userDBPanel extends JPanel {
	
	public JPanel pan[] = new JPanel[2];
	public JButton insert = new JButton("추가");
	public JButton delete = new JButton("삭제");
	public JTextField ja1 = new JTextField();
	public JTextField ja2 = new JTextField();
	public JTextField ja3 = new JTextField();
	public JLabel lb1 = new JLabel("회원 이름 : ");
	public JLabel lb2 = new JLabel("나이 : ");
	public JLabel lb3 = new JLabel("전화번호 : ");
	
	String header[] = {"회원 이름", "나이", "전화번호"};
	public DefaultTableModel model = new DefaultTableModel(header,0);
	public JTable table = new JTable(model);

	public userDBPanel() {
		
		this.setLayout(new GridLayout(2,0));
		pan[0] = new JPanel();		
		pan[1] = new JPanel(new GridLayout(4,0));
		
		JScrollPane sp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		sp.setPreferredSize(new Dimension(400,300));
	
		
		pan[0].add(sp);
		pan[1].add(lb1);
		pan[1].add(ja1);
		pan[1].add(lb2);
		pan[1].add(ja2);
		pan[1].add(lb3);
		pan[1].add(ja3);
		pan[1].add(insert);
		pan[1].add(delete);
		
		this.add(pan[0]);
		this.add(pan[1]);

	}
	
}
