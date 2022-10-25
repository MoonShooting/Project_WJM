package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.ChallengeBean;
import vo.MemberBean;
import vo.MyPageBean;

public class MyPageDAO {

	public static MyPageDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	private MyPageDAO() {
	}

	public static MyPageDAO getInstance() {
		if (instance == null) {
			instance = new MyPageDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertMember(MyPageBean member) {
		PreparedStatement pstmt = null;
		String sql = "insert into user valuse(?,?,?,?,?,?,?,0,0,0,0,0,?,0)";
		int insertCount = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getU_num());
			pstmt.setString(2, member.getU_id());
			pstmt.setString(3, member.getGrade());
			pstmt.setString(4, member.getU_pw());
			pstmt.setString(5, member.getName());
			pstmt.setString(6, member.getNickname());
			pstmt.setString(7, member.getTel());
			pstmt.setString(8, member.getBirth());
			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(pstmt);
		return insertCount;
	}

	public String selectLoginId(MemberBean member) {
		String loginId = null;
		String sql = "select * from user_view where u_id=? and u_pw=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getU_id());
			pstmt.setString(2, member.getU_pw());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setNickname(rs.getString("nickname"));
				member.setGrade(rs.getString("grade"));
				member.setC_score(rs.getInt("c_score"));
			}
		} catch (Exception e) {
			System.out.println("loginDAO err" + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginId;
	}
	//user info
	public MyPageBean selectMemberInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from user_view where u_id=?";
		ArrayList<MyPageBean> mypageList = new ArrayList<MyPageBean>();
		MyPageBean mpb = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mpb = new MyPageBean();
				mpb.setU_id(rs.getString("u_id"));
				mpb.setGrade(rs.getString("grade"));
				mpb.setName(rs.getString("name"));
				mpb.setNickname(rs.getString("nickname"));			
				mpb.setSuccess(rs.getInt("success"));
				mpb.setC_score(rs.getInt("c_score"));
				mpb.setCount(rs.getInt("count"));
				mpb.setC_count(rs.getInt("c_count"));
			}
			//	System.out.println(mypageList.size() + "개 값 확인");
		} catch (Exception e) {
			System.out.println("MemberInfo(회원정보) err" + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return mpb;
	}

	public ArrayList<ChallengeBean> selectChallengeInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ChallengeBean> myPageChallengeList = null;
		ChallengeBean cb = null;
		
		String sql = "select * from c_proposer where u_id = ? and (c_condition = 'Y' or c_condition = 'ing' or c_condition is null)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				myPageChallengeList = new ArrayList<ChallengeBean>();
				do {
					cb = new ChallengeBean();
					cb.setC_num(rs.getInt("c_num"));
					cb.setU_condition(rs.getString("u_condition"));
					cb.setC_subject(rs.getString("c_subject"));
					cb.setStartday(rs.getString("startday"));
					cb.setEndday(rs.getString("endday"));
					cb.setC_time(rs.getString("c_time"));
					cb.setDay(rs.getString("day"));
					cb.setC_condition("c_condition");
					myPageChallengeList.add(cb);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
			close(pstmt);
		}
		
		return myPageChallengeList;
	}

	public MemberBean getMemberInfo(String id, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from user_view where u_id=? and u_pw=?";
		MemberBean mb = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs= pstmt.executeQuery();
			System.out.println(pstmt);
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setU_id(rs.getString("u_id"));
				mb.setGrade(rs.getString("grade"));
				mb.setNickname(rs.getString("nickname"));
				mb.setC_score(rs.getInt("c_score"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return mb;
	}

	public int insertMember(String id, String pw, String name, String nickname, String tel, String birth) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="insert into user(u_id, grade, u_pw, name, nickname, tel, count, success, fail, log_count, log_time, birth, c_score, c_count) value(?,'성냥',?,?,?,?,0,0,0,0,now(),?,0,0)";
		int success = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, nickname);
			pstmt.setString(5, tel);
			pstmt.setString(6, birth);
			success = pstmt.executeUpdate();
			System.out.println(pstmt);
			System.out.println(success);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return success;
	}

	public int getUpdate(String id) {
		int update = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="update user set log_count = log_count+1, log_time=now() where u_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			update = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return update;
	}

	public MemberBean getInfomation(String id) {
		MemberBean mb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user_view where u_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				mb = new MemberBean();
				mb.setU_id(rs.getString("u_id"));
				mb.setC_score(rs.getInt("c_score"));
				mb.setGrade(rs.getString("grade"));
				mb.setU_pw(rs.getString("u_pw"));				
				mb.setName(rs.getString("name"));
				mb.setNickname(rs.getString("nickname"));
				mb.setTel(rs.getString("tel"));
				mb.setCount(rs.getInt("count"));
				mb.setFail(rs.getInt("fail"));
				mb.setBirth(rs.getString("birth"));
				
				
				mb.setC_count(rs.getInt("c_count"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return mb;
	}

	public boolean myInfoUpdate(MemberBean mb) {
		boolean updateSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update user set u_pw=?, nickname=?, tel=? where u_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getU_pw());
			pstmt.setString(2, mb.getNickname());
			pstmt.setString(3, mb.getTel());
			pstmt.setString(4, mb.getU_id());
			updateSuccess = pstmt.execute();
			System.out.println(pstmt);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return updateSuccess;
	}

	public int getRank(String id) {
		int rank = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ranking from rank_view where u_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rank = rs.getInt(1);
			}
			System.out.println(pstmt);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return rank;
	}
	
	
}