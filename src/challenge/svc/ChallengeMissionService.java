package challenge.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ChallengeDAO;
import vo.ChallengeBean;

public class ChallengeMissionService {
	public int userUpdate(String id, int c_num) {
		int userUpdate = 0;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			userUpdate = challengeDAO.userUpdate(id, c_num);
			if(userUpdate > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return userUpdate;
	}

	public boolean resultMission(String id, int c_num) {
		boolean result = false;
		ChallengeBean cb = null;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			cb = challengeDAO.resultMission(id, c_num);
			result = challengeDAO.resultInsert(cb);
			System.out.println(result);
			if(!result) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return result;
	}

	public int checking(String id, int c_num) {
		int check = 0;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			check = challengeDAO.checkInfo(id, c_num);
			System.out.println(check);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return check;
	}

	public int proposerChecking(String id, int c_num) {
		int procheck = 0;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			procheck = challengeDAO.proposerCheck(id, c_num);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return procheck;
	}


}
