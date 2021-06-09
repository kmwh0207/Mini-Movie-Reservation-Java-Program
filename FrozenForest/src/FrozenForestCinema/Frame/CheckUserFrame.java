package FrozenForestCinema.Frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FrozenForestCinema.Listener.LoginListener;
import FrozenForestCinema.lib.ChangeFont;

//로그인 창, 네트워크 비동기 구현
public class CheckUserFrame extends JFrame {
	
	JPanel mem_login;
	JLabel phone_label = new JLabel("휴대폰번호");
	JLabel name_label = new JLabel("이름");
	JLabel age_label = new JLabel("나이");
	public JTextField name_field = new JTextField(15);
	public JTextField phone_field = new JTextField(15);
	public JTextField age_field = new JTextField(3);
	public JButton bt[] = {new JButton("취소"),new JButton("로그인")};
	LoginListener login_listener= new LoginListener(this);
	Image chagedImg;
	
	
	public CheckUserFrame(int mode) {
		this.setTitle("로그인");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 150);
		
		mem_login = new JPanel();
		mem_login.setBackground(new Color(255,255,255));
		mem_login.setLayout(new GridLayout(0,2));
		
		//취소 누르면 창 닫기
		bt[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//System.exit(0);
			}
		});
		bt[1].addActionListener(login_listener);		
		
		this.add(mem_login);
		this.setVisible(true);
		
		//회원, 비회원 구분
		if(mode==1) {
			MemberLogin();
		}else if(mode==2) {
			NonMemberLogin();
		}
	}
	
	public void MemberLogin(){
		
		bt[0].setBorderPainted(true);
		bt[1].setBorderPainted(true);
		bt[0].setContentAreaFilled(false);
		bt[1].setContentAreaFilled(false);
		bt[0].setFocusPainted(false);
		bt[1].setFocusPainted(false);
		
		mem_login.add(phone_label);
		mem_login.add(phone_field);
		mem_login.add(bt[0]);
		mem_login.add(bt[1]);
		//폰트변경
		ChangeFont.changefont_small(this.mem_login);
		//회원 속성 삽입
		bt[1].putClientProperty("is_user", 1);
		
	}
	
	public void NonMemberLogin(){
		
		mem_login.add(name_label);
		mem_login.add(name_field);
		mem_login.add(phone_label);
		mem_login.add(phone_field);
		mem_login.add(age_label);
		mem_login.add(age_field);
		mem_login.add(bt[0]);
		mem_login.add(bt[1]);
		//폰트변경
		ChangeFont.changefont_small(this.mem_login);
		//비회원 속성 삽입
		bt[1].putClientProperty("is_user", 0);
		
	}	
}
