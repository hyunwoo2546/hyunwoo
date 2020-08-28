package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {

	private Connection conn;
	private ResultSet rs;
	
	/**** 생성자 DB 연결****/
	public BbsDAO() {
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
	
	/**** 현재시간 출력 기능 ****/
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류..
	}
	
	/**** 다음 게시글로 이동하는 기능 ****/
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;		// 첫번째 게시물인 경우..
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류..
	}
	
	/**** 현재시간 출력 기능 ****/
	public int write(String bbsTitle, String userID ,String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());			// getNext() 메소드 호출
			pstmt.setString(2, bbsTitle);		// 받아온 인자 값으로 세팅.
			pstmt.setString(3, userID);			// 받아온 인자 값으로 세팅.
			pstmt.setString(4, getDate());		// getDate() 메소드 호출.
			pstmt.setString(5, bbsContent);		// 받아온 인자 값으로 세팅.
			pstmt.setInt(6, 1);					// Available의 경우 글을 등록하는 것이기에 1로 값을 세팅.
			return pstmt.executeUpdate(); 		// 해당 Statement를 실행한 그 결과를 리턴.
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류..
	}
	
	/**** 게시글 목록 출력 기능 ****/
	public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";		// 실행순서 4.*** 중요
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);		// 실행순서 1.
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);			// 실행순서 2.
			rs = pstmt.executeQuery();									// 실행순서 3.
			
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);						// 세팅한 값 ArrayList에 추가.
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list; // 받아온 게시글 리스트 반환.
	}
	
	/**** 페이징 처리 기능 ****/
	public boolean nextPage (int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";		// 실행순서 4.*** 중요
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);		// 실행순서 1.
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);			// 실행순서 2.
			rs = pstmt.executeQuery();									// 실행순서 3.
			
			if (rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false; // 받아온 게시글 리스트 반환.
	}
	
}
