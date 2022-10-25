package admin.action;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String id=(String)session.getAttribute("id");
		String admin_menu = null;
		String admin_search = null;
		
		if(request.getParameter("admin_menu") != null) {
			admin_menu = request.getParameter("admin_menu");
		}else {
			admin_menu = "";
		}
		if(request.getParameter("admin_search") != null) {
			admin_search = request.getParameter("admin_search");
		}else {
			admin_search = "";
		}
		
		
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
		MemberListService memberListService = new MemberListService();
		int page = 1;
		int limit = 10;
		int limitPage = 10;
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println(page);
		int listCount = MemberListService.getListCount(admin_menu, admin_search, page, limit);
		memberList = memberListService.getSearchMemberList(admin_menu, admin_search, page, limit);	
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int) ((double)page / limitPage +0.9)) -1) * limitPage + 1;
		int endPage = startPage + limitPage - 1 ;
			if(id==null) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("login.admin");
				
			} else if(!id.equals("admin")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자로 로그인 하세요')");
				out.println("location.href=${pageContext.request.contextPath}/login.admin;");
				out.println("</script>");
				
			}
		
		else {
						
			if(endPage > maxPage) endPage = maxPage;
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setPage(page);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setStartPage(startPage);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("memberList", memberList);
			forward = new ActionForward();
			forward.setPath("/adm/index.jsp");
		}
		return forward;
	}

}
