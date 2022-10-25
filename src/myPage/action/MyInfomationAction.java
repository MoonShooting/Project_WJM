package myPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import myPage.svc.MyPageInfomationService;
import vo.ActionForward;
import vo.MemberBean;

public class MyInfomationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id != null) {
			MyPageInfomationService myPageInfomationService = new MyPageInfomationService();
			MemberBean mb = myPageInfomationService.getInfomation(id);
			forward = new ActionForward();
			request.setAttribute("memberInfo", mb);
			forward.setPath("/myPage/myPageInfo.jsp");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하셔야 합니다.')");
			out.println("location.href='"+request.getContextPath()+"/loginForm.jsp';");
			out.println("</script>");
		}
		return forward;
	}

}
