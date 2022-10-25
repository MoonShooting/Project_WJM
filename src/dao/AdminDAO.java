package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;

public class AdminDAO {

	public static AdminDAO instance;
	Connection con;
	PreparedStatement pstmt; 
	ResultSet rs;
	DataSource ds;
	
	private AdminDAO() {}

	public static AdminDAO getInstance() {
		if(instance == null) {
			instance = new AdminDAO();
		}
		return instance;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<MemberBean> selectMemberList(String admin_menu, String admin_search, int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from user_view";
		ArrayList<MemberBean> memberList=new ArrayList<MemberBean>();
		MemberBean mb = null;
		int startrow = (page-1)*10;
		
		try {
			if(admin_menu.equals("u_num")) {
				sql += " where u_num like "+ "'%"+admin_search+"%'"+ " order by u_num asc, u_id desc limit "+startrow+",10";
			}else if(admin_menu.equals("u_id")) {
				sql += " where u_id like "+ "'%"+admin_search+"%'"+ " order by u_num asc, u_id desc limit "+startrow+",10";
			}else if(admin_menu.equals("u_num")) {
				sql += " where log_count like "+"'%"+admin_search+"%'"+" order by u_num asc, u_id desc limit "+startrow+",10";
			}else if(admin_menu.equals("log_time")) {
				sql += " where log_time like "+"'%"+admin_search+"%'"+" order by u_num asc, u_id desc limit "+startrow+",10";
			}else if(admin_menu.equals("grade")) {
				sql += " where grade like "+"'%"+admin_search+"%'"+" order by u_num asc, u_id desc limit "+ startrow+",10";
			}else {
				sql += " order by u_num asc, u_id desc limit "+ startrow+",10";
			}
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				do {
					mb=new MemberBean();
					mb.setU_num(rs.getInt("u_num"));
					mb.setU_id(rs.getString("u_id"));
					mb.setGrade(rs.getString("grade"));
					mb.setU_pw(rs.getString("u_pw"));
					mb.setName(rs.getString("name"));
					mb.setNickname(rs.getString("nickname"));
					mb.setTel(rs.getString("tel"));
					mb.setCount(rs.getInt("count"));
					mb.setSuccess(rs.getInt("success"));
					mb.setFail(rs.getInt("fail"));
					mb.setLog_count(rs.getInt("log_count"));
					mb.setLog_time(rs.getTimestamp("log_time"));
					mb.setBirth(rs.getString("birth"));
					memberList.add(mb);
					
				} while(rs.next());
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}

	public MemberBean selectMember(String viewId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from user_view where u_id=?";
		MemberBean mb = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, viewId);
			rs=pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				mb=new MemberBean();
				mb.setU_num(rs.getInt("u_num"));
				mb.setU_id(rs.getString("u_id"));
				mb.setGrade(rs.getString("grade"));
				mb.setU_pw(rs.getString("u_pw"));
				mb.setName(rs.getString("name"));
				mb.setNickname(rs.getString("nickname"));
				mb.setCount(rs.getInt("count"));
				mb.setSuccess(rs.getInt("success"));
				mb.setFail(rs.getInt("fail"));
				mb.setLog_count(rs.getInt("log_count"));
				mb.setLog_time(rs.getTimestamp("log_time"));
				mb.setBirth(rs.getString("birth"));
				mb.setTel(rs.getString("tel"));
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(rs);
		System.out.println(sql);
		return mb;
		}

	public int selectMemberListCount(String admin_menu, String admin_search, int page, int limit) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from user_view";
		int startrow = (page-1)*10;
			
		try {
			if(admin_menu.equals("u_num")) {
				sql += " where u_num like "+ "'%"+admin_search+"%'";
			}else if(admin_menu.equals("u_id")) {
				sql += " where u_id like "+ "'%"+admin_search+"%'";
			}else if(admin_menu.equals("u_num")) {
				sql += " where log_count like "+"'%"+admin_search+"%'";
			}else if(admin_menu.equals("log_time")) {
				sql += " where log_time like "+"'%"+admin_search+"%'";
			}else if(admin_menu.equals("grade")) {
				sql += " where grade like "+"'%"+admin_search+"%'";
			}else {
				sql = "select count(*) from user ";
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}



}
