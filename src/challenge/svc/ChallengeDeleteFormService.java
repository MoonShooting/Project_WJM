
package challenge.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ChallengeDAO;
import vo.ChallengeBean;

public class ChallengeDeleteFormService {
	
	public ChallengeBean getArticle(int c_num) {
		ChallengeBean article = null;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challnegeDAO = ChallengeDAO.getInstance();
			challnegeDAO.setConnection(con);
			article = challnegeDAO.selectArticle(c_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return article;
		
	}

}