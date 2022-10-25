package survey.svc;

import static db.JdbcUtil.*;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SurveyDAO;
import db.JdbcUtil.*;
import vo.MyPageBean;
public class SurveyProService {
	public boolean insertSurvey(int c_num, String u_id, int satisfaction, int experience, int score) {
		boolean insertSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			SurveyDAO surveyDAO = SurveyDAO.getInstance();
			surveyDAO.setConnection(con);
			insertSuccess = surveyDAO.insertSurvey(c_num, u_id, satisfaction, experience, score);
			if(!insertSuccess) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return insertSuccess;
	}

	public ArrayList<MyPageBean> scorePoint(int c_num) {
		ArrayList<MyPageBean> mpb = null;
		Connection con = null;
		try {
			con = getConnection();
			SurveyDAO surveyDAO = SurveyDAO.getInstance();
			surveyDAO.setConnection(con);
			mpb = surveyDAO.scorePoint(c_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return mpb;
	}

	public String madeUser(int c_num) {
		String madeUser = null;
		Connection con = null;
		try {
			con = getConnection();
			SurveyDAO surveyDAO = SurveyDAO.getInstance();
			surveyDAO.setConnection(con);
			madeUser = surveyDAO.madeUser(c_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return madeUser;
	}

	public int updatePoint(String made, int c_point) {
		int updatePoint = 0;
		Connection con = null;
		try {
			con = getConnection();
			SurveyDAO surveyDAO = SurveyDAO.getInstance();
			surveyDAO.setConnection(con);
			updatePoint = surveyDAO.updatePoint(made, c_point);
			if(updatePoint > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return updatePoint;
	}

	

}
