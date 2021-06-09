import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;

public class DBActionListener extends JFrame implements ActionListener{

	Statement stmt;
	public MovieReserve mv;
	
	public DBActionListener(MovieReserve movieReserve) {
		// TODO Auto-generated constructor stub
		this.mv = movieReserve;
		try {
			stmt = mv.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// ��ȭ �г� �߰� ��ư
		if (e.getSource() == ((movieDBPanel)mv.Panel[0]).insert) {
			try {
				// �̸��� ���ݿ� �Էµ� ���� DB������
				stmt.executeUpdate("insert into movielist(name, price) values ('"+ ((movieDBPanel)mv.Panel[0]).ja1.getText() + "', '" + ((movieDBPanel)mv.Panel[0]).ja2.getText() + "');");
				
				// �ؽ�Ʈ �ʵ� �ʱ�ȭ
				((movieDBPanel)mv.Panel[0]).ja1.setText(" ");
				((movieDBPanel)mv.Panel[0]).ja2.setText(" ");
				
				
				// ���ΰ�ħ
				int count = ((movieDBPanel)mv.Panel[0]).table.getRowCount();
				for (int i = 0; i<count;i++) {
					((movieDBPanel)mv.Panel[0]).model.removeRow(0);
				}
				
				try {
					ResultSet localrs = stmt.executeQuery("select * from movielist");
					
					try {
						while (localrs.next()) {
						int num = localrs.getInt("movienum");
						String name = localrs.getString("name");
						String price = localrs.getString("price");
						
						mv.rowData = new Vector();
						mv.rowData.addElement(num);
						mv.rowData.addElement(name);
						mv.rowData.addElement(price);
						
						((movieDBPanel)mv.Panel[0]).model.addRow(mv.rowData);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		// ��ȭ �г� ���� ��ư
		if (e.getSource() == ((movieDBPanel)mv.Panel[0]).delete ) {
			
			if ( ((movieDBPanel)mv.Panel[0]).table.isFocusable()) {
				int index = ((movieDBPanel)mv.Panel[0]).table.getSelectedRow();
				try {	
					// ���õ� ���̺� ���� �޾ƿ� DB���� ���� ����
					stmt.executeUpdate("delete from movielist where movienum = '"  + ((movieDBPanel)mv.Panel[0]).model.getValueAt(index, 0) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			// �ؽ�Ʈ �ʵ� �ʱ�ȭ
			((movieDBPanel)mv.Panel[0]).ja1.setText(" ");
			((movieDBPanel)mv.Panel[0]).ja2.setText(" ");
			
			
			// ���ΰ�ħ
			int count = ((movieDBPanel)mv.Panel[0]).table.getRowCount();
			for (int i = 0; i<count;i++) {
				((movieDBPanel)mv.Panel[0]).model.removeRow(0);
			}
			
			try {
				ResultSet localrs = stmt.executeQuery("select * from movielist");
				
				try {
					while (localrs.next()) {
					int num = localrs.getInt("movienum");
					String name = localrs.getString("name");
					String price = localrs.getString("price");
					
					mv.rowData = new Vector();
					mv.rowData.addElement(num);
					mv.rowData.addElement(name);
					mv.rowData.addElement(price);
					
					((movieDBPanel)mv.Panel[0]).model.addRow(mv.rowData);
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
			
		}
		
		
		// ���� �г� �߰� ��ư
		if (e.getSource() == ((userDBPanel)mv.Panel[1]).insert) {
			try {
				// �̸�, ����, �޴���ȭ��ȣ�� �Է��Ͽ� DB�� ����
				stmt.executeUpdate("insert into members (name, age, phone) values ('"+ ((userDBPanel)mv.Panel[1]).ja1.getText() + "', '" + ((userDBPanel)mv.Panel[1]).ja2.getText() + "', '" + ((userDBPanel)mv.Panel[1]).ja3.getText() + "')");
	
				// �ؽ�Ʈ �ʵ� �ʱ�ȭ
				((userDBPanel)mv.Panel[1]).ja1.setText(" ");
				((userDBPanel)mv.Panel[1]).ja2.setText(" ");
				((userDBPanel)mv.Panel[1]).ja3.setText(" ");
				
				// ���ΰ�ħ
				int count = ((userDBPanel)mv.Panel[1]).table.getRowCount();
				for (int i = 0; i<count;i++) {
					((userDBPanel)mv.Panel[1]).model.removeRow(0);
				}
				
				try {
					ResultSet localrs = stmt.executeQuery("select * from members");
					
					try {
						while (localrs.next()) {
						String name = localrs.getString("name");
						int age = localrs.getInt("age");
						String phone = localrs.getString("phone");
						
						mv.rowData = new Vector();
						mv.rowData.addElement(name);
						mv.rowData.addElement(age);
						mv.rowData.addElement(phone);
						
						((userDBPanel)mv.Panel[1]).model.addRow(mv.rowData);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		// ���� �г� ���� ��ư
		if (e.getSource() == ((userDBPanel)mv.Panel[1]).delete ) {

			if ( ((userDBPanel)mv.Panel[1]).table.isFocusable()) {
				int index = ((userDBPanel)mv.Panel[1]).table.getSelectedRow();
				try {	
					// ���̺��� ���õ� �޴���ȭ�� ���� ����� DB���� ����
					stmt.executeUpdate("delete from members where phone = '"  + ((userDBPanel)mv.Panel[1]).model.getValueAt(index, 2) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			// �ؽ�Ʈ �ʵ� �ʱ�ȭ
			((userDBPanel)mv.Panel[1]).ja1.setText(" ");
			((userDBPanel)mv.Panel[1]).ja2.setText(" ");
			((userDBPanel)mv.Panel[1]).ja3.setText(" ");
			
			
			// ���ΰ�ħ
			int count = ((userDBPanel)mv.Panel[1]).table.getRowCount();
			for (int i = 0; i<count;i++) {
				((userDBPanel)mv.Panel[1]).model.removeRow(0);
			}
			
			try {
				ResultSet localrs = stmt.executeQuery("select * from members");
				
				try {
					while (localrs.next()) {
					String name = localrs.getString("name");
					String phone = localrs.getString("phone");
					int age = localrs.getInt("age");
					
					mv.rowData = new Vector();
					mv.rowData.addElement(name);
					mv.rowData.addElement(age);
					mv.rowData.addElement(phone);
					
					((userDBPanel)mv.Panel[1]).model.addRow(mv.rowData);
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
			
		}
		
		
		
		// ���� �г� �߰� ��ư
		if (e.getSource() == ((reserveDBPanel)mv.Panel[2]).insert ) {
			try {
				// �޴���ȭ, ��ȭ��ȣ, �ð�, �¼��� DB�� ����.
				// ��, �޴���ȭ��ȣ�� �����гο� �̹� �ִ� ���̿��� ������ �����ϴ�.
				stmt.executeUpdate("insert into orders (phone, movienum, time, seat) values ('"+ ((reserveDBPanel)mv.Panel[2]).ja1.getText() + "', '" + ((reserveDBPanel)mv.Panel[2]).ja2.getText()  + "', '" +  ((reserveDBPanel)mv.Panel[2]).ja3.getText()  + "', '" +  ((reserveDBPanel)mv.Panel[2]).ja4.getText()  + "')");
				
				// �ؽ�Ʈ �ʵ� �ʱ�ȭ
				((reserveDBPanel)mv.Panel[2]).ja1.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja2.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja3.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja4.setText(" ");
				
				// ���ΰ�ħ
				int count = ((reserveDBPanel)mv.Panel[2]).table.getRowCount();
				for (int i = 0; i<count;i++) {
					((reserveDBPanel)mv.Panel[2]).model.removeRow(0);
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
						
						
						mv.rowData = new Vector();
						mv.rowData.addElement(orderNum);
						mv.rowData.addElement(phone);
						mv.rowData.addElement(movienum);
						mv.rowData.addElement(seat);
						mv.rowData.addElement(time);
						
						
						((reserveDBPanel)mv.Panel[2]).model.addRow(mv.rowData);
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
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		
		
		// ���� �г� ���� ��ư
		if (e.getSource() == ((reserveDBPanel)mv.Panel[2]).delete ) {

			if ( ((reserveDBPanel)mv.Panel[2]).table.isFocusable()) {
				int index = ((reserveDBPanel)mv.Panel[2]).table.getSelectedRow();
				try {	
					// ���õ� ���̺��� �����ȣ�� �ҷ��� ����
					stmt.executeUpdate("delete from orders where ordernum = '"  + ((reserveDBPanel)mv.Panel[2]).model.getValueAt(index, 0) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//�ؽ�Ʈ �ʵ� �ʱ�ȭ
			((reserveDBPanel)mv.Panel[2]).ja1.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja2.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja3.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja4.setText(" ");
			
			
			//���ΰ�ħ
			int count = ((reserveDBPanel)mv.Panel[2]).table.getRowCount();
			for (int i = 0; i<count;i++) {
				((reserveDBPanel)mv.Panel[2]).model.removeRow(0);
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
					
					
					mv.rowData = new Vector();
					mv.rowData.addElement(orderNum);
					mv.rowData.addElement(phone);
					mv.rowData.addElement(movienum);
					mv.rowData.addElement(seat);
					mv.rowData.addElement(time);
					
					
					((reserveDBPanel)mv.Panel[2]).model.addRow(mv.rowData);
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
			
		}
		
		
		// ��� �޴����� ���ΰ�ħ ��ư
		if (e.getSource() == mv.setMenuItem[0]) {
			
			
			// ��ȭ ���̺� ���ΰ�ħ
			int count = ((movieDBPanel)mv.Panel[0]).table.getRowCount();
			for (int i = 0; i<count;i++) {
				((movieDBPanel)mv.Panel[0]).model.removeRow(0);
			}
			
			try {
				ResultSet localrs = stmt.executeQuery("select * from movielist");
				
				try {
					while (localrs.next()) {
					int num = localrs.getInt("movienum");
					String name = localrs.getString("name");
					String price = localrs.getString("price");
					
					mv.rowData = new Vector();
					mv.rowData.addElement(num);
					mv.rowData.addElement(name);
					mv.rowData.addElement(price);
					
					((movieDBPanel)mv.Panel[0]).model.addRow(mv.rowData);
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
			
			
			
			// ���� �г� ���ΰ�ħ
			int count1 = ((userDBPanel)mv.Panel[1]).table.getRowCount();
			for (int i = 0; i<count1;i++) {
				((userDBPanel)mv.Panel[1]).model.removeRow(0);
			}
			
			try {
				ResultSet localrs = stmt.executeQuery("select * from members");
				
				try {
					while (localrs.next()) {
					String name = localrs.getString("name");
					int age = localrs.getInt("age");
					String phone = localrs.getString("phone");
					
					mv.rowData = new Vector();
					mv.rowData.addElement(name);
					mv.rowData.addElement(age);
					mv.rowData.addElement(phone);
					
					((userDBPanel)mv.Panel[1]).model.addRow(mv.rowData);
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
			
	
			
			
			
			// ���� �г� ���ΰ�ħ
			int count2 = ((reserveDBPanel)mv.Panel[2]).table.getRowCount();
			for (int i = 0; i<count2;i++) {
				((reserveDBPanel)mv.Panel[2]).model.removeRow(0);
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
					
					
					mv.rowData = new Vector();
					mv.rowData.addElement(orderNum);
					mv.rowData.addElement(phone);
					mv.rowData.addElement(movienum);
					mv.rowData.addElement(seat);
					mv.rowData.addElement(time);
					
					
					((reserveDBPanel)mv.Panel[2]).model.addRow(mv.rowData);
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
		}
		
		// ��� �޴��� ���� ��ư
		if(e.getSource() == mv.setMenuItem[1]) {
			System.exit(ABORT);
		}

	}

}
