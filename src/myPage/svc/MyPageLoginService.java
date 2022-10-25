package myPage.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MyPageDAO;

import static db.JdbcUtil.*;

import vo.ChallengeBean;
import vo.MemberBean;

public class MyPageLoginService {
	public MemberBean getMemberInfo(String id, String pw) {
		MemberBean member =null;
		Connection con = null;
		
		try {
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			member = myPageDAO.getMemberInfo(id, pw);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return member;
	}

	public int getUpdayte(String id) {
		int update = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			update = myPageDAO.getUpdate(id);
			if(update > 0) {
				commit(con);
				System.out.println("commit success");
			}else {
				rollback(con);
				System.out.println("rollback success");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return update;
	}

}
