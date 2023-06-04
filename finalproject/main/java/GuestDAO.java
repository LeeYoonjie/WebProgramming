package finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

	//DB 연결을 가져오는 메서드 
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	

	public List<Guest> getAll() throws Exception{
		Connection conn = open();
		List<Guest> guestList = new ArrayList<>();
		
		String sql = "select aid, username, email, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from guest"; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Guest g = new Guest();
				g.setAid(rs.getInt("aid"));
				g.setUsername(rs.getString("username"));
				g.setEmail(rs.getString("email"));
				g.setTitle(rs.getString("title"));
				g.setDate(rs.getString("cdate")); 
				
				
				guestList.add(g);
			}
			return guestList;			
		}
	}
	
	public Guest getGuest(int aid) throws SQLException {
		Connection conn = open();
		
		Guest g = new Guest();
		String sql = "select aid, username, email, password, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from Guest where aid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
 
		rs.next();
		
		try(conn; pstmt; rs){
			g.setAid(rs.getInt("aid"));
			g.setUsername(rs.getString("username"));
			g.setEmail(rs.getString("email"));
			g.setTitle(rs.getString("title"));
			g.setPassword(rs.getString("password"));
			g.setDate(rs.getString("cdate"));		
			g.setContent(rs.getString("content"));
			
			pstmt.executeQuery();
			return g; 
		}
	}
	
	public void addGuest(Guest g) throws Exception{
		Connection conn = open();
		
		String sql = "insert into guest(username, email, title, password, date, content) values(?,?,?,?, CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, g.getUsername());
			pstmt.setString(2, g.getEmail());
			pstmt.setString(3, g.getTitle());
			pstmt.setString(4, g.getPassword());
			pstmt.setString(5, g.getContent());
			pstmt.executeUpdate();
		}
	}

	
	public void delGuest(int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from guest where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, aid);
			// 삭제된 뉴스 기사가 없을 경우
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
	public void updateGuest(Guest g) throws SQLException {
		Connection conn = open();
		
		String sql="UPDATE guest SET username=?, email=?, title=?, password=?, content=? WHERE aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
	        pstmt.setString(1, g.getUsername());
	        pstmt.setString(2, g.getEmail());
	        pstmt.setString(3, g.getTitle());
	        pstmt.setString(4, g.getPassword());
	        pstmt.setString(5, g.getContent());
	        pstmt.setInt(6, g.getAid());
	        pstmt.executeUpdate();
	    }
	}
}