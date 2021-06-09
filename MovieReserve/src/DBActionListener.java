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
		
		// 영화 패널 추가 버튼
		if (e.getSource() == ((movieDBPanel)mv.Panel[0]).insert) {
			try {
				// 이름과 가격에 입력된 값을 DB에삽입
				stmt.executeUpdate("insert into movielist(name, price) values ('"+ ((movieDBPanel)mv.Panel[0]).ja1.getText() + "', '" + ((movieDBPanel)mv.Panel[0]).ja2.getText() + "');");
				
				// 텍스트 필드 초기화
				((movieDBPanel)mv.Panel[0]).ja1.setText(" ");
				((movieDBPanel)mv.Panel[0]).ja2.setText(" ");
				
				
				// 새로고침
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		// 영화 패널 삭제 버튼
		if (e.getSource() == ((movieDBPanel)mv.Panel[0]).delete ) {
			
			if ( ((movieDBPanel)mv.Panel[0]).table.isFocusable()) {
				int index = ((movieDBPanel)mv.Panel[0]).table.getSelectedRow();
				try {	
					// 선택된 테이블 값을 받아와 DB에서 내용 삭제
					stmt.executeUpdate("delete from movielist where movienum = '"  + ((movieDBPanel)mv.Panel[0]).model.getValueAt(index, 0) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			// 텍스트 필드 초기화
			((movieDBPanel)mv.Panel[0]).ja1.setText(" ");
			((movieDBPanel)mv.Panel[0]).ja2.setText(" ");
			
			
			// 새로고침
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
			
		}
		
		
		// 유저 패널 추가 버튼
		if (e.getSource() == ((userDBPanel)mv.Panel[1]).insert) {
			try {
				// 이름, 나이, 휴대전화번호를 입력하여 DB에 삽입
				stmt.executeUpdate("insert into members (name, age, phone) values ('"+ ((userDBPanel)mv.Panel[1]).ja1.getText() + "', '" + ((userDBPanel)mv.Panel[1]).ja2.getText() + "', '" + ((userDBPanel)mv.Panel[1]).ja3.getText() + "')");
	
				// 텍스트 필드 초기화
				((userDBPanel)mv.Panel[1]).ja1.setText(" ");
				((userDBPanel)mv.Panel[1]).ja2.setText(" ");
				((userDBPanel)mv.Panel[1]).ja3.setText(" ");
				
				// 새로고침
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		// 유저 패널 삭제 버튼
		if (e.getSource() == ((userDBPanel)mv.Panel[1]).delete ) {

			if ( ((userDBPanel)mv.Panel[1]).table.isFocusable()) {
				int index = ((userDBPanel)mv.Panel[1]).table.getSelectedRow();
				try {	
					// 테이블에서 선택된 휴대전화의 값을 갖고와 DB에서 삭제
					stmt.executeUpdate("delete from members where phone = '"  + ((userDBPanel)mv.Panel[1]).model.getValueAt(index, 2) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			// 텍스트 필드 초기화
			((userDBPanel)mv.Panel[1]).ja1.setText(" ");
			((userDBPanel)mv.Panel[1]).ja2.setText(" ");
			((userDBPanel)mv.Panel[1]).ja3.setText(" ");
			
			
			// 새로고침
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
			
		}
		
		
		
		// 예약 패널 추가 버튼
		if (e.getSource() == ((reserveDBPanel)mv.Panel[2]).insert ) {
			try {
				// 휴대전화, 영화번호, 시간, 좌석를 DB에 삽입.
				// 단, 휴대전화번호가 유저패널에 이미 있는 값이여야 삽입이 가능하다.
				stmt.executeUpdate("insert into orders (phone, movienum, time, seat) values ('"+ ((reserveDBPanel)mv.Panel[2]).ja1.getText() + "', '" + ((reserveDBPanel)mv.Panel[2]).ja2.getText()  + "', '" +  ((reserveDBPanel)mv.Panel[2]).ja3.getText()  + "', '" +  ((reserveDBPanel)mv.Panel[2]).ja4.getText()  + "')");
				
				// 텍스트 필드 초기화
				((reserveDBPanel)mv.Panel[2]).ja1.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja2.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja3.setText(" ");
				((reserveDBPanel)mv.Panel[2]).ja4.setText(" ");
				
				// 새로고침
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
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		
		
		// 예약 패널 삭제 버튼
		if (e.getSource() == ((reserveDBPanel)mv.Panel[2]).delete ) {

			if ( ((reserveDBPanel)mv.Panel[2]).table.isFocusable()) {
				int index = ((reserveDBPanel)mv.Panel[2]).table.getSelectedRow();
				try {	
					// 선택된 테이블에서 예약번호를 불러와 삭제
					stmt.executeUpdate("delete from orders where ordernum = '"  + ((reserveDBPanel)mv.Panel[2]).model.getValueAt(index, 0) + "' ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//텍스트 필드 초기화
			((reserveDBPanel)mv.Panel[2]).ja1.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja2.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja3.setText(" ");
			((reserveDBPanel)mv.Panel[2]).ja4.setText(" ");
			
			
			//새로고침
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
			
		}
		
		
		// 상단 메뉴바의 새로고침 버튼
		if (e.getSource() == mv.setMenuItem[0]) {
			
			
			// 영화 테이블 새로고침
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
			
			
			
			// 유저 패널 새로고침
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
			
	
			
			
			
			// 예약 패널 새로고침
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
		}
		
		// 상단 메뉴바 종료 버튼
		if(e.getSource() == mv.setMenuItem[1]) {
			System.exit(ABORT);
		}

	}

}
