
package challenge.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.ChallengeBean;
import dao.ChallengeDAO;

public class ChallengeModifyProService {
	public boolean modifyArticle(ChallengeBean article)throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			int updateCount = challengeDAO.updateArticle(article);
			
			if(updateCount > 0) {
				commit(con);
				isModifySuccess = true;
				
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isModifySuccess;
	}
	
}