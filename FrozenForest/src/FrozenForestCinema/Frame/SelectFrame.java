package FrozenForestCinema.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import FrozenForestCinema.Listener.SelectListener;
import FrozenForestCinema.lib.ChangeFont;
//��ȭ ���� â
public class SelectFrame extends JFrame{
	
	String username ;
	SelectFrame frame;
	//���� �ڵ��� ��ȣ
	static public String phone;
	static public String[][] movielist;
	//��ȭ ���ý� listener���κ��� ���ŵ�
	static public String selected_movie;
	//��ȭ ���ý� DBConnection���κ��� ���ŵ�
	static public ArrayList<Integer> disable_seat = new ArrayList<>();
	// ���õ� �ð�
	static public String selected_time ="0";
	// ������ �ο� ��
	static public int headcount =0;
	// ��ȭ �ܰ�
	static public String price = "0";
	// ������ �ڸ�
	static public ArrayList<Integer> selected_seat = new ArrayList<>();
	Image changedImg;
	static public JCheckBox[] seat_box;
	static public JButton bt_yes;
	
	int post_w=80;
	int post_h=120;
	public SelectListener selectlistener;
	
	public SelectFrame(ResultSet result,String... args) {		
		username = args[0];
		phone = args[1];
		frame=this;
		this.setLayout(new GridLayout(0,1,10,10));
		this.setTitle("�ȳ��ϼ���. "+username+"��^^ �α���ID:"+phone);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,600);
		
		
		try {
			result.last();
			movielist = new String[result.getRow()][3];
			result.beforeFirst();
			for(int count=0; count<movielist.length; count++) {
				result.next();
				movielist[count][0] = String.valueOf(result.getInt("movienum"));
				movielist[count][1] = String.valueOf(result.getString("name"));
				movielist[count][2] = String.valueOf(result.getInt("price"));
			}
			System.out.println(movielist.length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.add(new JScrollPane(new postpanel(),ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		this.add(new subpanel());
		this.add(new seatpanel());
		this.setResizable(false);
		this.setVisible(true);
	}
	
	protected class postpanel extends JPanel {
		JCheckBox checkbox[] =new JCheckBox[movielist.length];
		ButtonGroup buttongroup = new ButtonGroup();
		protected postpanel() {
			selectlistener = new SelectListener(checkbox,movielist,frame);
			this.setLayout(new GridLayout(1,0,5,5));
			this.setPreferredSize(new Dimension(movielist.length*(post_w+20),(post_h+50)));
			//UIManager.put("CheckBox.focus",Color.white);

			for(int count=0; count<movielist.length; count++) {
				//üũ�ڽ��� ��ȭ�̸� ����
				checkbox[count] = new JCheckBox(movielist[count][1]);
				checkbox[count].setPreferredSize(new Dimension(post_w,post_h));
				checkbox[count].setBorderPainted(true);
				checkbox[count].setContentAreaFilled(false);
				checkbox[count].setFocusPainted(false);
				buttongroup.add(checkbox[count]);
				checkbox[count].addItemListener(selectlistener);
				changedImg= new ImageIcon("./image/"+movielist[count][1]+".jpg").getImage().getScaledInstance(post_w, post_h, Image.SCALE_SMOOTH );
				checkbox[count].setIcon(new ImageIcon(changedImg));
				checkbox[count].setVerticalTextPosition(JCheckBox.BOTTOM);
				checkbox[count].setHorizontalTextPosition(JCheckBox.CENTER);
				checkbox[count].setHorizontalAlignment(JCheckBox.CENTER);
				this.add(checkbox[count]);
			}
			this.setOpaque(true);
			this.setBackground(new Color(255,255,255));
			this.setBorder(new EmptyBorder(10, 0, 10, 0));
		}
	}
	protected class subpanel extends JPanel {
		protected subpanel() {
			JButton minus = new JButton();
			JButton plus = new JButton();
			
			JLabel headcount_label= new JLabel(String.valueOf(headcount));
			this.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
			DefaultListModel timelistmodel= new DefaultListModel();
			JList timelist = new JList(timelistmodel);
			for(int time=0; time<24; time++) {
				timelistmodel.addElement(time);
			}
			
			changedImg= new ImageIcon("./image/minus.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH );
			minus.setIcon(new ImageIcon(changedImg));
			changedImg= new ImageIcon("./image/plus.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH );
			plus.setIcon(new ImageIcon(changedImg));
			changedImg = null;
			ChangeFont.changefont(headcount_label,30);
			
			minus.setBorderPainted(false);
			minus.setContentAreaFilled(false);
			minus.setFocusPainted(false);
			plus.setBorderPainted(false);
			plus.setContentAreaFilled(false);
			plus.setFocusPainted(false);
			
			minus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					--headcount;
					headcount_label.setText(String.valueOf(headcount));
				}
			});
			plus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					++headcount;
					headcount_label.setText(String.valueOf(headcount));
				}
			});
			timelist.addListSelectionListener(selectlistener);
			JScrollPane timepanel = new JScrollPane(timelist);
			timepanel.setPreferredSize(new Dimension(100,100));
			
			JLabel exp = new JLabel("- ��ȭ ���� �ð��� �ο����� �����Ͽ��ּ���. -");
			this.add(exp);
			ChangeFont.changefont(exp, 15);
			this.add(timepanel);
			this.add(minus);
			this.add(headcount_label);
			this.add(plus);
			this.setOpaque(true);
			this.setBackground(new Color(255,255,255));
			this.setBorder(new EmptyBorder(10, 0, 10, 0));
		}
	}
	protected class seatpanel extends JPanel {
		protected seatpanel() {
			JPanel sub_panel = new JPanel(new GridLayout(3,10,7,7));
			this.setLayout(new BorderLayout());
			//�ڸ� ���� ��ư
			seat_box = new JCheckBox[31];
			changedImg= new ImageIcon("./image/chair.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_REPLICATE);
			// ����� �ڸ��� ���Ұ�
			for(int count=1; count<seat_box.length; count++) {
				//DBConnection ���κ��� �簻�� ���� (�񵿱�)
				seat_box[count] = new JCheckBox();
				seat_box[count].setIcon(new ImageIcon(changedImg));
				seat_box[count].setBorderPainted(true);
				
				sub_panel.add(seat_box[count]);
			}
			bt_yes = new JButton("�����ϱ�");
			bt_yes.setFont(new Font("Serif",Font.BOLD,18));
			bt_yes.addActionListener(selectlistener);
			this.add(sub_panel,BorderLayout.CENTER);
			this.add(bt_yes,BorderLayout.SOUTH);
			this.setOpaque(true);
			this.setBackground(new Color(255,255,255));
			this.setBorder(new EmptyBorder(10, 20, 10, 20));
		}
	}
}
