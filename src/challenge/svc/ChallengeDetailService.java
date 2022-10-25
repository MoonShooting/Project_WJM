
package challenge.svc;

import java.sql.Connection;
import dao.ChallengeDAO;
import vo.ChallengeBean;
import vo.MissionBean;

import static db.JdbcUtil.*;

public class ChallengeDetailService {
	public ChallengeBean getArticle(int c_num)throws Exception {
		ChallengeBean article = null;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			
			challengeDAO.setConnection(con);
			int updateCount = challengeDAO.updateReadCount(c_num);
			
			if(updateCount > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			article = challengeDAO.selectArticle(c_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return article;
	}

	public boolean attend(String id, int c_num,String c_time) {
		boolean attend =false;
		Connection con = null;
		try {
			con= getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			attend = challengeDAO.attend(id,c_num,c_time);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return attend;
	}

	public int particip(int c_num) {
		int participCount = 0;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			participCount = challengeDAO.getParticip(c_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return participCount;
	}




	
}