package FrozenForestCinema.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FrozenForestCinema.Listener.LoginAction;
import FrozenForestCinema.lib.ChangeFont;

//회원, 비회원 선택 창
public class LoginPanel extends JPanel {
	
	public JButton bt_member = new JButton("회원");
	public JButton bt_nonmember = new JButton("비회원");
	LoginAction login_action = new LoginAction(this);
	public JLabel greeting_label = new JLabel("~어서오세요~");
	JLabel center_img;
	public static JLabel exp_label = new JLabel("<html><body>Welcome to FrozenForestCinema,<br/> please click to Login Button</body></html>");
	
	
	public LoginPanel() {
		this.setLayout(new BorderLayout());
		greeting_label.setHorizontalAlignment(JLabel.CENTER);
		//폰트 색상 변경
		greeting_label.setForeground(new Color(255, 255, 255));
		exp_label.setForeground(new Color(255, 255, 255));
		ChangeFont.changefont(greeting_label,15);
		Image changedImg= new ImageIcon("./image/center_img.png").getImage().getScaledInstance(220, 300, Image.SCALE_REPLICATE );
		center_img = new JLabel(new ImageIcon(changedImg));
		bt_member.setPreferredSize(new Dimension(200,200));
		bt_nonmember.setPreferredSize(new Dimension(200,200));
		changedImg= new ImageIcon("./image/login.gif").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH );
		bt_member.setIcon(new ImageIcon(changedImg));
		changedImg= new ImageIcon("./image/register.gif").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH );
		bt_nonmember.setIcon(new ImageIcon(changedImg));

		bt_member.setOpaque(false);
		bt_nonmember.setOpaque(false);
		bt_member.setBorderPainted(false);
		bt_nonmember.setBorderPainted(false);
		bt_member.setContentAreaFilled(false);
		bt_nonmember.setContentAreaFilled(false);
		bt_member.setFocusPainted(false);
		bt_nonmember.setFocusPainted(false);
		
		this.add(greeting_label, BorderLayout.NORTH);
		this.add(bt_nonmember,BorderLayout.WEST);
		this.add(center_img,BorderLayout.CENTER);
		this.add(bt_member, BorderLayout.EAST);
		this.add(exp_label,BorderLayout.SOUTH);
		
		//글꼴변경
		ChangeFont.changefont_big(this);
		ChangeFont.changefont(greeting_label, 15);
		ChangeFont.changefont(exp_label, 15);
		bt_member.addActionListener(login_action);
		bt_nonmember.addActionListener(login_action);	
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
	}
	@Override
	protected void paintComponent(Graphics g) {
		Image changedImg = null;
		try {
			changedImg = ImageIO.read(new File("./image/background.jpg"));
			//changedImg= new ImageIcon("./image/background.jpg").getImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(changedImg, 0, 0, null);
		setOpaque(false);
	}
	
}
