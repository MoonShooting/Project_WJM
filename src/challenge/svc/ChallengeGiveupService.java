
package challenge.svc;

import static db.JdbcUtil.*;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import db.JdbcUtil.*;
import dao.ChallengeDAO;


public class ChallengeGiveupService {

	public boolean identification(int c_num, String id) {
		boolean identification = false;
		Connection con = null;
		int update = 0;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			identification = challengeDAO.indentification(c_num, id);
			update = challengeDAO.updateUser(id);
			if(identification && update > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
			
		}
		
		return identification;
	}

	public static int minusCount(String id) {
		int minusCount = 0;
		Connection con = null;
		
		try {
			con=getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			minusCount = challengeDAO.minusCount(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return minusCount;
	}

}