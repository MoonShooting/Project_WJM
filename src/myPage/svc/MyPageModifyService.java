package myPage.svc;

import java.sql.Connection;

import dao.MyPageDAO;

import static db.JdbcUtil.*;

import vo.MemberBean;

public class MyPageModifyService {

	public boolean myInfoUpdate(MemberBean mb) {
		boolean updateSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			updateSuccess = myPageDAO.myInfoUpdate(mb);
			if(!updateSuccess) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return updateSuccess;
		
		
		
	}

}
