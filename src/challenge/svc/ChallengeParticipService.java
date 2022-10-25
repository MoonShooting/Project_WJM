package challenge.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import vo.ChallengeBean;
import dao.AdminDAO;
import dao.ChallengeDAO;

public class ChallengeParticipService {

	public ChallengeBean getParticipInfo(int c_num, String id) {
		ChallengeBean cb = null;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			cb = challengeDAO.participInfo(c_num, id);
			if(cb != null) {
				
			}else {
				cb = challengeDAO.participInfomation(c_num, id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return cb;
	}

	public int getParticipCondition(int c_num, int insertCount, ChallengeBean challengeBean, boolean attend, String id) {
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			insertCount = challengeDAO.participCondition(c_num,insertCount,challengeBean,attend, id);
			if(insertCount > 0) {
				commit(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return insertCount;
	}

	public int plusParticipCount(boolean attend, ChallengeBean challengeBean) {
		int c_particip = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			c_particip = challengeDAO.plusParticipCount(attend, challengeBean);
			if(c_particip > 0) {
				commit(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return c_particip;
	}

	public int plusAttendCount(String id) {
		int attendCount = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			attendCount = challengeDAO.plusAttendCount(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return attendCount;
	}

	public boolean getParticipDouble(int c_num, String id) {
		boolean particip = false;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			particip = challengeDAO.getParticipDouble(c_num, id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return particip;
	}

}
