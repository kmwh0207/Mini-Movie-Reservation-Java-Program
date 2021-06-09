package FrozenForestCinema.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import FrozenForestCinema.Frame.LoginPanel;
import FrozenForestCinema.Frame.SelectFrame;

public class DBConnection extends Thread {
	Connection connection;
	PreparedStatement stmt;
	ResultSet result;
	String id = "root";
	String pw = "password";
	String url = "jdbc:mysql://localhost:3306/movie?characterEncoding=UTF-8&serverTimezone=UTC";
	String sql_login = "select name,phone,age from members where phone=(?)";
	String sql_register = "insert into members values (?,?,?)";
	String sql_getdb = "select movienum,name,price from movielist";
	String sql_getseat = "select ordernum,phone,movienum,time,seat from orders where movienum=(?) and time=(?)";
	String sql_send = "insert into orders(phone,movienum,time,seat) values (?,?,?,?)";
	String sql_getordernum = "select ordernum from orders where  = (?)";
	
	String text[];
	ArrayList<Integer> seat_list;
	int mode;
	SelectFrame frame;
	public DBConnection(int mode,String... text){
		this.text = text;
		this.mode = mode;
	}
	public DBConnection(SelectFrame frame,ArrayList<Integer> list,String... text){
		this.frame = frame;
		this.text = text;
		this.seat_list = list;
		this.mode = 3;
	}
	public void run() {
		try {
			LoginPanel.exp_label.setText("DB접속 중 (로딩 중 입니다. 잠시만 기다려주세요.)");
			//DB연결, 로그인
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success load jdbc");
			connection = DriverManager.getConnection(url,id,pw);
			System.out.println("Success DB Connect");
			switch(mode) {
			case 1:
				System.out.println("이름:"+text[0]+", 연락처:"+text[1]);
				LoginPanel.exp_label.setText("로그인 중 (버튼을 누르지 말고 기다려주세요.)");
				//DB에서 이름과 휴대폰번호 일치여부 확인
				if(text[0].length()>0 && text[2].length()>0) {
					stmt = connection.prepareStatement(sql_register);
					stmt.setString(1, text[0]);
					stmt.setString(2, text[1]);
					stmt.setString(3, text[2]);
					int result = stmt.executeUpdate();
					System.out.println("회원가입:"+result);
				}else if(text[1].length()>0) {
					stmt = connection.prepareStatement(sql_login);
					stmt.setString(1, text[1]);
					result = stmt.executeQuery();
					if(result.next()) {
						text[0]= result.getString("name");
						text[2]= String.valueOf(result.getInt("age"));
						System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getInt(3));
					}else {
						throw new Exception("실패");
					}
				}else {
					throw new Exception("텍스트창이 비었습니다.");
				}
				//영화 선택창 호출
				stmt = connection.prepareStatement(sql_getdb);
				result = stmt.executeQuery();
				new SelectFrame(result, text);
				break;
			case 2:
				SelectFrame.disable_seat.clear();
				stmt = connection.prepareStatement(sql_getseat);
				stmt.setString(1, text[0]);
				stmt.setString(2, text[1]);
				result = stmt.executeQuery();
				while(result.next()) {
					SelectFrame.disable_seat.add(result.getInt("seat"));
					System.out.println(result.getInt("seat"));
				}
				for(int count=1; count<SelectFrame.seat_box.length; count++) {
					//사용가능 좌석 재갱신
					if(SelectFrame.disable_seat.contains(count)) {
						SelectFrame.seat_box[count].setEnabled(false);
					}else {
						SelectFrame.seat_box[count].setEnabled(true);
					}
				}
				break;
			case 3:
				String order_seat = "";
				stmt = connection.prepareStatement(sql_send);
				stmt.setString(1, text[0]);
				stmt.setString(2, text[1]);
				stmt.setString(3, text[2]);
				for(int count=0; count<seat_list.size(); count++) {
					LoginPanel.exp_label.setText("예매 처리중입니다. 잠시만 기다려주세요^^");
					stmt.setString(4,seat_list.get(count).toString());
					int result = stmt.executeUpdate();
					System.out.println("예매 갱신:"+result);
					order_seat += seat_list.get(count).toString()+" ";
				}
				LoginPanel.exp_label.setText("예매가 완료되었습니다.");
				JOptionPane.showMessageDialog(null,"예매 정보: 휴대폰번호("+text[0]+"), 영화("+SelectFrame.movielist[Integer.valueOf(text[1])][1]+"), 예매시간("+text[2]+"), 자리("+order_seat+")");
				//정보 초기화
				SelectFrame.movielist = null;
				SelectFrame.phone = null;
				SelectFrame.selected_movie = null;
				SelectFrame.selected_time = "0";
				SelectFrame.headcount = 0;
				SelectFrame.price = "0";
				SelectFrame.seat_box = null;
				SelectFrame.bt_yes = null;
				frame.dispose();
				break;
			}
		}catch(Exception e) {
			LoginPanel.exp_label.setText("회원정보를 찾을 수 없습니다.");
			e.printStackTrace();
		}finally {
			try {
				//스트림 종료
				stmt.close();
				connection.close();
				LoginPanel.exp_label.setText("즐거운 하루 되세요~");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
