package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.MemberBean;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();
		
		member.setU_id(request.getParameter("admin_id"));
		member.setU_pw(request.getParameter("admin_pw"));
		
		ActionForward forward = null;
		
		if(request.getParameter("admin_id").equals("admin")) {
			forward = new ActionForward();
			session.setAttribute("id", member.getU_id());
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/index.admin");
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로만 로그인이 가능합니다.')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
