package myPage.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MyPageDAO;
import vo.ChallengeBean;
import vo.MyPageBean;

public class MyPageFormService {

	public MyPageBean getMyPageInfo(String id) {
		Connection con = null;
		MyPageBean mypageList = null;
		
		try {
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			mypageList = myPageDAO.selectMemberInfo(id);
		}catch (Exception e) {
			System.out.println("MypageFormsvc err" + e);
			e.printStackTrace();
		}finally {
			close(con);
		}
		return mypageList;
	}

	public ArrayList<ChallengeBean> getMyPageSchedule(String id) {
		Connection con = null;
		ArrayList<ChallengeBean> myPageChallengeList = null;
		MyPageDAO myPageDAO = null;
		
		try {
			con = getConnection();
			myPageDAO = myPageDAO.getInstance();
			myPageDAO.setConnection(con);
			myPageChallengeList = myPageDAO.selectChallengeInfo(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return myPageChallengeList;
	}

	public int getRank(String id) {
		int rank = 0;
		Connection con = null;
		MyPageDAO myPageDAO = null;
		try {
			con = getConnection();
			myPageDAO = myPageDAO.getInstance();
			myPageDAO.setConnection(con);
			rank = myPageDAO.getRank(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return rank;
	}

}