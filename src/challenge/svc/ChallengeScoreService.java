package challenge.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.util.ArrayList;

import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.ChallengeBean;
import vo.MissionBean;
import dao.ChallengeDAO;

public class ChallengeScoreService {

	public ArrayList<ChallengeBean> getScore(String id) {
		ArrayList<ChallengeBean> cb = null;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			cb = challengeDAO.getScore(id);
			System.out.println(cb);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return cb;
	}

	public int getMissionCount(String id) {
		Connection con = null;
		int count = 0;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			count = challengeDAO.getMissionCount(id);
			System.out.println(count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return count;
	}

	public int getUpdateScore(String id, int count) {
		Connection con = null;
		int updateSuccess = 0;
		int userUpdate = 0;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			userUpdate = challengeDAO.getUserUpdate(id, count);
			updateSuccess = challengeDAO.getUpdateScore(id);
			System.out.println(userUpdate + "user");
			System.out.println(updateSuccess + "success");
			if(updateSuccess > 0 && userUpdate > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			System.out.println(updateSuccess);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return updateSuccess;
	}

	public ArrayList<MissionBean> getMissionList(String id) {
		ArrayList<MissionBean> mb = null;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			mb = challengeDAO.getMissionList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return mb;
	}

}
