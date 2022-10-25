
package challenge.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ChallengeDAO;

public class ChallengeDeleteProService {

	public boolean isArticleWriter(int c_num,String c_id) {
		boolean isArticleWriter = false;
		Connection con =null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			isArticleWriter = challengeDAO.isArticleChallengeWriter(c_num,c_id);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isArticleWriter;
	}
	
	public boolean removeArticle(int c_num) {
		
		boolean isRemoveArticle = false;
		Connection con = null;
		
		try {
			
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			int deleteCount = challengeDAO.deleteArticle(c_num);
			int deleteProposer = challengeDAO.deleteProposer(c_num);
			
			System.out.println("deleteProposer : "+deleteProposer);
			System.out.println("deleteCount : "+deleteCount);
			
			if(deleteCount >0) {
				
				commit(con);
				isRemoveArticle = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isRemoveArticle;
	}
	
}