
package challenge.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.ChallengeDAO;
import vo.ChallengeBean;

public class ChallengeListService {
	
	public int getListCount(String challenge_menu, String challenge_search, int page, int limit) throws Exception {
		int listCount =0;
		Connection con = null;
		try{
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			listCount = challengeDAO.selectListCount(challenge_menu, challenge_search,page,limit);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return listCount;
	}
	
	public ArrayList<ChallengeBean> getArticleList (String challenge_menu, String challenge_search, int page, int limit) throws Exception{
		
		ArrayList<ChallengeBean> articleList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			ChallengeDAO challengeDAO = ChallengeDAO.getInstance();
			challengeDAO.setConnection(con);
			articleList = challengeDAO.selectArticleList(challenge_menu, challenge_search,page,limit);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		close(con);
		}
		return articleList;
	}


}