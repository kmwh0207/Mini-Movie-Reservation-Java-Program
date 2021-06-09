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
	public JButton insert = new JButton("�߰�");
	public JButton delete = new JButton("����");
	public JTextField ja1 = new JTextField();
	public JTextField ja2 = new JTextField();
	public JTextField ja3 = new JTextField();
	public JTextField ja4 = new JTextField();
	public JLabel lb1 = new JLabel("��ȭ��ȣ : ");
	public JLabel lb2 = new JLabel("��ȭ��ȣ : ");
	public JLabel lb3 = new JLabel("�ð� : ");
	public JLabel lb4 = new JLabel("�¼� : ");
	
	String header[] = {"�����ȣ", "��ȭ��ȣ", "��ȭ��ȣ", "�ð�", "�¼�"};
	public DefaultTableModel model = new DefaultTableModel(header,0);
	public JTable table = new JTable(model);
	
	
	public reserveDBPanel() {
		
		this.setLayout(new GridLayout(2,0));
		pan[0] = new JPanel();
		pan[1] = new JPanel(new GridLayout(5,0));
		
		JScrollPane sp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		sp.setPreferredSize(new Dimension(400,300));
		
		table.getColumn("�����ȣ").setPreferredWidth(70);
		table.getColumn("��ȭ��ȣ").setPreferredWidth(150);
		table.getColumn("��ȭ��ȣ").setPreferredWidth(60);
		table.getColumn("�ð�").setPreferredWidth(40);
		table.getColumn("�¼�").setPreferredWidth(40);
		
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
