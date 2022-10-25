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

public class SurveyDAO {

	public static SurveyDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	private SurveyDAO() {
	}

	public static SurveyDAO getInstance() {
		if (instance == null) {
			instance = new SurveyDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int getParticip(int c_num) {
		int particip = 0;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select c_particip from c_challenge where c_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			particip = rs.getInt("c_particip");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return particip;
	}

	public boolean insertSurvey(int c_num, String u_id, int satisfaction, int experience, int score) {
		boolean insertSuccess = false;
		PreparedStatement pstmt = null;
		String sql = "insert into c_answer(c_num, u_id, q1a, q2a, score) values(?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setString(2, u_id);
			pstmt.setInt(3, satisfaction);
			pstmt.setInt(4, experience);
			pstmt.setInt(5, score);
			insertSuccess = pstmt.execute();
			System.out.println(pstmt);
		}catch(Exception e) {
			e.printStackTrace();}finally {
				close(pstmt);
			}
		
		return insertSuccess;
	}

	public ArrayList<MyPageBean> scorePoint(int c_num) {
		ArrayList<MyPageBean> mpb = new ArrayList<MyPageBean>();
		MyPageBean myPageBean = null;
		PreparedStatement pstmt = null;
		String sql = "select * from c_answer where c_num = ?";
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				myPageBean = new MyPageBean();
				do {
					myPageBean.setC_score(rs.getInt("score"));
					mpb.add(myPageBean);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();}finally {
				close(pstmt);
				close(rs);
			}
		return mpb;
	}

	public String madeUser(int c_num) {
		String madeUser = null;
		PreparedStatement pstmt = null;
		String sql = "select * from c_challenge where c_num = ?";
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				madeUser = rs.getString("c_id");
			}
		}catch(Exception e) {
			e.printStackTrace();}finally {
				close(pstmt);
				close(rs);
			}
		return madeUser;
	}

	public int updatePoint(String made, int c_point) {
		int updatePoint = 0;
		PreparedStatement pstmt = null;
		String sql = "update user set c_score = c_score+? where u_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,c_point);
			pstmt.setString(2, made);
			updatePoint = pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();}finally {
				close(pstmt);
			}
		return updatePoint;
	}
	

	
}