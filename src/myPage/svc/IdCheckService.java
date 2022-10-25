package myPage.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ChallengeDAO;

public class IdCheckService {

	public boolean checkIdNickname(String idCheck, String nickname) {
		boolean success = false;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			success = challengeDAO.checkIdNickname(idCheck, nickname);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return success;
	}

}
