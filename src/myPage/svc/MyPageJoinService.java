package myPage.svc;

import java.sql.Connection;

import dao.MyPageDAO;

import static db.JdbcUtil.*;

import vo.MemberBean;

public class MyPageJoinService {

	public int joinSuccess(String id, String pw, String name, String nickname, String tel, String birth) {
		int success = 0;
		Connection con = null;
		
		try{
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			success = myPageDAO.insertMember(id, pw, name, nickname, tel, birth);
			if(success > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return success;
	}

}
