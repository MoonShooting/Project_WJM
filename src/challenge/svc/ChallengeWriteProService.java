
package challenge.svc;

import java.sql.Connection;
import java.sql.Time;
import java.util.Date;

import dao.ChallengeDAO;
import vo.ChallengeBean;
import static db.JdbcUtil.*;

public class ChallengeWriteProService {

	public boolean registArticle(String c_id, String c_subject, String c_text, String startday, String endday, String c_time, String day, String c_mode) {
		boolean isWriteSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			int insertCount = challengeDAO.insertArticle(c_id, c_subject, c_text, startday, endday, c_time, day, c_mode);
			
			if(insertCount > 0 ) {
				commit(con);
				isWriteSuccess = true;
			}else {
				rollback(con);
			}
			
		}catch(Exception e) {
			rollback(con);
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		
		return isWriteSuccess;
	}

}