package admin.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.MemberBean;

public class MemberViewService {

	public MemberBean getMember(String viewId) {
		
		MemberBean member = null;
		Connection con = null;
		
		try {
			con = getConnection();
			AdminDAO adminrDAO = AdminDAO.getInstance();
			adminrDAO.setConnection(con);
			member = adminrDAO.selectMember(viewId);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
		return member;
	}
	
	
}
