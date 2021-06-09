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
//영화 선택 창
public class SelectFrame extends JFrame{
	
	String username ;
	SelectFrame frame;
	//유저 핸드폰 번호
	static public String phone;
	static public String[][] movielist;
	//영화 선택시 listener으로부터 갱신됨
	static public String selected_movie;
	//영화 선택시 DBConnection으로부터 갱신됨
	static public ArrayList<Integer> disable_seat = new ArrayList<>();
	// 선택된 시간
	static public String selected_time ="0";
	// 선택한 인원 수
	static public int headcount =0;
	// 영화 단가
	static public String price = "0";
	// 선택한 자리
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
		this.setTitle("안녕하세요. "+username+"님^^ 로그인ID:"+phone);
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
				//체크박스에 영화이름 삽입
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
			
			JLabel exp = new JLabel("- 영화 예매 시간과 인원수를 선택하여주세요. -");
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
			//자리 선택 버튼
			seat_box = new JCheckBox[31];
			changedImg= new ImageIcon("./image/chair.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_REPLICATE);
			// 예약된 자리는 사용불가
			for(int count=1; count<seat_box.length; count++) {
				//DBConnection 으로부터 재갱신 받음 (비동기)
				seat_box[count] = new JCheckBox();
				seat_box[count].setIcon(new ImageIcon(changedImg));
				seat_box[count].setBorderPainted(true);
				
				sub_panel.add(seat_box[count]);
			}
			bt_yes = new JButton("예매하기");
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
