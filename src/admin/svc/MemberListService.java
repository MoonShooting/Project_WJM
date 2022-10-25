package admin.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.MemberBean;

public class MemberListService {

	public ArrayList<MemberBean> getSearchMemberList(String admin_menu, String admin_search, int page, int limit)throws Exception {
		
		ArrayList<MemberBean> memberList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			AdminDAO adminDAO = AdminDAO.getInstance();
			adminDAO.setConnection(con);
			memberList = adminDAO.selectMemberList(admin_menu, admin_search, page, limit);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return memberList;
	}

	public static int getListCount(String admin_menu, String admin_search, int page, int limit) throws Exception{
		int listCount = 0;
		Connection con = null;
		try {
			con = getConnection();
			AdminDAO adminDAO = AdminDAO.getInstance();
			adminDAO.setConnection(con);
			listCount = adminDAO.selectMemberListCount(admin_menu, admin_search, page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return listCount;
	}
	
}
