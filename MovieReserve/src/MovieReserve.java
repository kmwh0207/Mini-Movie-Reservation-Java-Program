import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MovieReserve extends JFrame{
	
	Connection con = null;	// DB연결
	Statement stmt = null;	// 스테이트먼트 생성
	ResultSet rsMovie = null;	//영화 DB를 위한 ResultSet
	ResultSet rsUser = null;	//유저 DB를 위한 ResultSet
	ResultSet rsReserve = null;	//예약 DB를 위한 ResultSet
	
	Vector rowData = null;	//테이블 벡터
	
	public JPanel Panel[] = new JPanel[3];	//영화,유저,예약을 관리하기 위한 페널 3개 생성
	
	String setMenuList[] = {"새로고침","종료"};		//상단바에 위치한 메뉴 리스트
	JMenuItem setMenuItem[] = new JMenuItem[setMenuList.length];
	
	
	public MovieReserve() {
		con = getConnection();	//DB연결 메소드
		
		Panel[0] = new movieDBPanel();	//영화 PANEL
		Panel[1] = new userDBPanel();	//유저 PANEL
		Panel[2] = new reserveDBPanel();//예약 PAENL
		
		try {
			//스테이트 먼트 생성
			stmt = con.createStatement();
			
			//각각의 DB에 내용이 다르므로 각각의 ResultSet을 생성
			rsMovie = stmt.executeQuery("select * from movielist");
			rsUser = stmt.executeQuery("select * from members");
			rsReserve = stmt.executeQuery("select * from orders");
			
			System.out.println("스테이트먼트 생성 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("스테이트먼트 생성 실패");
		}

		// 영화패널의 새로고침 기능
		int count = ((movieDBPanel)Panel[0]).table.getRowCount();
		for (int i = 0; i<count;i++) {
			((movieDBPanel)Panel[0]).model.removeRow(0);
		}
		
		try {
			ResultSet localrs = stmt.executeQuery("select * from movielist");
			
			try {
				while (localrs.next()) {
				int num = localrs.getInt("movienum");
				String name = localrs.getString("name");
				String price = localrs.getString("price");
				
				rowData = new Vector();
				rowData.addElement(num);
				rowData.addElement(name);
				rowData.addElement(price);
				
				((movieDBPanel)Panel[0]).model.addRow(rowData);
				System.out.println("영화 패널 새로고침 성공");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("영화 패널 새로고침 실패");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("영화 패널 Query구문 수행 실패");
		}
		
		
		
		// 유저패널의 새로고침 기능
		int count1 = ((userDBPanel)Panel[1]).table.getRowCount();
		for (int i = 0; i<count1;i++) {
			((userDBPanel)Panel[1]).model.removeRow(0);
		}
		
		try {
			ResultSet localrs = stmt.executeQuery("select * from members");
			
			try {
				while (localrs.next()) {
				String name = localrs.getString("name");
				int age = localrs.getInt("age");
				String phone = localrs.getString("phone");
				
				rowData = new Vector();
				rowData.addElement(name);
				rowData.addElement(age);
				rowData.addElement(phone);
				
				((userDBPanel)Panel[1]).model.addRow(rowData);
				System.out.println("유저 패널 새로고침 성공");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("유저 패널 새로고침 실패");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("유저 패널 Query구문 수행 실패");
		}
		
		
		
		
		// 예약패널의 새로고침 기능
		int count2 = ((reserveDBPanel)Panel[2]).table.getRowCount();
		for (int i = 0; i<count2;i++) {
			((reserveDBPanel)Panel[2]).model.removeRow(0);
		}
		
		try {
			ResultSet localrs = stmt.executeQuery("select * from orders");
			
			try {
				while (localrs.next()) {
				String orderNum = localrs.getString("orderNum");
				String phone = localrs.getString("phone");
				int movienum = localrs.getInt("movienum");
				int seat = localrs.getInt("seat");
				int time = localrs.getInt("time");
				
				
				rowData = new Vector();
				rowData.addElement(orderNum);
				rowData.addElement(phone);
				rowData.addElement(movienum);
				rowData.addElement(seat);
				rowData.addElement(time);
				
				
				((reserveDBPanel)Panel[2]).model.addRow(rowData);
				System.out.println("예약 패널 새로고침 성공");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("예약 패널 새로고침 실패");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("예약 패널 Query구문 수행 실패");
		}
		
		
		
		// 버튼 리스너 생성
		
		DBActionListener MDBAL = new DBActionListener(this);

		((movieDBPanel) Panel[0]).insert.addActionListener(MDBAL);
		((movieDBPanel) Panel[0]).delete.addActionListener(MDBAL);
		((userDBPanel) Panel[1]).insert.addActionListener(MDBAL);
		((userDBPanel) Panel[1]).delete.addActionListener(MDBAL);
		((reserveDBPanel) Panel[2]).insert.addActionListener(MDBAL);
		((reserveDBPanel) Panel[2]).delete.addActionListener(MDBAL);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("DB관리창");
		this.setSize(500, 800);
		
		this.add(Panel[0]);
		this.add(Panel[1]);
		this.add(Panel[2]);
		
		createMenuBar(); //상단 메뉴바 생성
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.add("movie", Panel[0]);
		tab.add("user",Panel[1]);
		tab.add("reserve",Panel[2]);
		
		this.add(tab);
		
		this.setVisible(true);
	}
	
	//상단 메뉴바 생성
	private void createMenuBar() {
		// TODO Auto-generated method stub
		
		DBActionListener MDBAL = new DBActionListener(this);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu setMenu = new JMenu("설정");
		
		for(int i=0; i<setMenuList.length; i++) {
			setMenuItem[i]=new JMenuItem(setMenuList[i]);
			setMenuItem[i].addActionListener(MDBAL);
			setMenu.add(setMenuItem[i]);
		}
		
		mb.add(setMenu);

		this.setJMenuBar(mb);
	}


	private Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/movie?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "password";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("jdbc 연결 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("jdbc 연결 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}

		return con;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MovieReserve();
	}

}
