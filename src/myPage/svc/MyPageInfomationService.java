package myPage.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MyPageDAO;
import vo.MemberBean;

public class MyPageInfomationService {

	public MemberBean getInfomation(String id) {
		MemberBean mb = null;
		Connection con = null;
		try {
			con = getConnection();
			MyPageDAO myPageDAO = MyPageDAO.getInstance();
			myPageDAO.setConnection(con);
			mb = myPageDAO.getInfomation(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con);
		}
		return mb;
	}

}
