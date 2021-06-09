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
	
	Connection con = null;	// DB����
	Statement stmt = null;	// ������Ʈ��Ʈ ����
	ResultSet rsMovie = null;	//��ȭ DB�� ���� ResultSet
	ResultSet rsUser = null;	//���� DB�� ���� ResultSet
	ResultSet rsReserve = null;	//���� DB�� ���� ResultSet
	
	Vector rowData = null;	//���̺� ����
	
	public JPanel Panel[] = new JPanel[3];	//��ȭ,����,������ �����ϱ� ���� ��� 3�� ����
	
	String setMenuList[] = {"���ΰ�ħ","����"};		//��ܹٿ� ��ġ�� �޴� ����Ʈ
	JMenuItem setMenuItem[] = new JMenuItem[setMenuList.length];
	
	
	public MovieReserve() {
		con = getConnection();	//DB���� �޼ҵ�
		
		Panel[0] = new movieDBPanel();	//��ȭ PANEL
		Panel[1] = new userDBPanel();	//���� PANEL
		Panel[2] = new reserveDBPanel();//���� PAENL
		
		try {
			//������Ʈ ��Ʈ ����
			stmt = con.createStatement();
			
			//������ DB�� ������ �ٸ��Ƿ� ������ ResultSet�� ����
			rsMovie = stmt.executeQuery("select * from movielist");
			rsUser = stmt.executeQuery("select * from members");
			rsReserve = stmt.executeQuery("select * from orders");
			
			System.out.println("������Ʈ��Ʈ ���� ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("������Ʈ��Ʈ ���� ����");
		}

		// ��ȭ�г��� ���ΰ�ħ ���
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
				System.out.println("��ȭ �г� ���ΰ�ħ ����");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("��ȭ �г� ���ΰ�ħ ����");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("��ȭ �г� Query���� ���� ����");
		}
		
		
		
		// �����г��� ���ΰ�ħ ���
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
				System.out.println("���� �г� ���ΰ�ħ ����");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("���� �г� ���ΰ�ħ ����");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("���� �г� Query���� ���� ����");
		}
		
		
		
		
		// �����г��� ���ΰ�ħ ���
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
				System.out.println("���� �г� ���ΰ�ħ ����");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("���� �г� ���ΰ�ħ ����");
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("���� �г� Query���� ���� ����");
		}
		
		
		
		// ��ư ������ ����
		
		DBActionListener MDBAL = new DBActionListener(this);

		((movieDBPanel) Panel[0]).insert.addActionListener(MDBAL);
		((movieDBPanel) Panel[0]).delete.addActionListener(MDBAL);
		((userDBPanel) Panel[1]).insert.addActionListener(MDBAL);
		((userDBPanel) Panel[1]).delete.addActionListener(MDBAL);
		((reserveDBPanel) Panel[2]).insert.addActionListener(MDBAL);
		((reserveDBPanel) Panel[2]).delete.addActionListener(MDBAL);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("DB����â");
		this.setSize(500, 800);
		
		this.add(Panel[0]);
		this.add(Panel[1]);
		this.add(Panel[2]);
		
		createMenuBar(); //��� �޴��� ����
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.add("movie", Panel[0]);
		tab.add("user",Panel[1]);
		tab.add("reserve",Panel[2]);
		
		this.add(tab);
		
		this.setVisible(true);
	}
	
	//��� �޴��� ����
	private void createMenuBar() {
		// TODO Auto-generated method stub
		
		DBActionListener MDBAL = new DBActionListener(this);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu setMenu = new JMenu("����");
		
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
			System.out.println("jdbc ���� ����");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB���� ����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("jdbc ���� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB���� ����");
			e.printStackTrace();
		}

		return con;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MovieReserve();
	}

}
