package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {			// 생성자

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**** 생성자 DB 연결****/
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/HYUNWOO_BBS";	// DB URL 담기
			String dbID = "root";										// DB ID 담기
			String dbPassword = "gus31216";								// DB PASSWORD 담기
			Class.forName("com.mysql.jdbc.Driver");	// jdbc 드라이버
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);	// 커넥션 객체 conn으로 DB 연결
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**** 로그인 기능 ****/
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID =?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) 
					return 1; // 로그인 성공!!
				else
					return 0; // 비밀번호 불일치..
			}
			return -1;// 아이디 없음..
		} catch (Exception e){
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류..
	}
	
	/**** 회원가입 기능 ****/
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();	// 해당 Statement를 실행한 그 결과를 리턴.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류..
	}
	
}
