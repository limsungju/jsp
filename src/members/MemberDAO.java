package members;
import java.sql.*;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class MemberDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "jsp2";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//private DataSource dataSource;
	
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			///Context context = new InitialContext();
			//dataSource = 
			//(DataSource)context.lookup("java:comp/env/myContext");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<MemberDTO> memberView() {
		String sql = "select * from members";
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		try {
			//con = dataSource.getConnection();
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO mb = new MemberDTO();
				mb.setId(rs.getString("id"));
				mb.setPwd(rs.getString("pwd"));
				mb.setName(rs.getString("name"));
				mb.setAddr(rs.getString("addr"));
				mb.setTel(rs.getString("tel"));
				memberList.add(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}
	public int userCheck(String id,String pwd){
		String sql = "select * from members where id=?";
		int chk=-1;
		try {
			con = DriverManager.getConnection(url,user,this.pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("pwd").equals(pwd)){  chk = 0; }
				else{ chk = 1; }
			}else{ chk = -1; }
		} catch (SQLException e) {	e.printStackTrace(); }
		return chk;	
	}
	public MemberDTO memberView(String id){
		String sql = "select * from members where id=?";
		MemberDTO dto = new MemberDTO();
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id); 
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setId(rs.getString(1));	dto.setPwd(rs.getString(2));
				dto.setName(rs.getString(3)); dto.setAddr(rs.getString(4));
				dto.setTel(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close(); if(ps != null) ps.close(); if(con != null) con.close();
			} catch (SQLException e) { }
		}
		return dto;
	}
	public int register(MemberDTO user){
		String sql = "insert into members values(?,?,?,?,?)";
		int howRow=0;
		PreparedStatement pstmt;
		try {
			con = DriverManager.getConnection(url,this.user,pwd);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());   pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());  pstmt.setString(4, user.getAddr());
			pstmt.setString(5, user.getTel());   howRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();  if(ps != null) ps.close();  if(con != null) con.close();
			} catch (SQLException e) { }
		}
		return howRow;
	}
	public int changeUser(MemberDTO user){
		String sql = "update members set name=?,addr=?,tel=? where id=?";
		int result=0;
		try {
			con = DriverManager.getConnection(url,this.user,pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getAddr());
			ps.setString(3, user.getTel());
			ps.setString(4, user.getId());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) { }
		}
		return result;
	}
	public int delete(String Uid){
		String sql = "delete from members where id=?";
		int result=0;
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, Uid);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) { }
		}
		return result;
	}
}