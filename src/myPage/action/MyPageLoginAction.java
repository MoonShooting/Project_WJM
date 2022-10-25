package myPage.action;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import myPage.svc.MyPageLoginService;
import vo.ActionForward;
import vo.ChallengeBean;
import vo.MemberBean;
import vo.MyPageBean;

public class MyPageLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();
		
		String id = request.getParameter("u_id");
		String pw = request.getParameter("u_pw");
		int update = 0;
		MyPageBean mpb = null;
		ActionForward forward = null;
		ArrayList<ChallengeBean> cb = null;
		
		if(id != null) {
			forward = new ActionForward();
			MyPageLoginService myPageLoginService = new MyPageLoginService();
			member = myPageLoginService.getMemberInfo(id, pw);
			update = myPageLoginService.getUpdayte(id);
			if(member != null) {
				session.setAttribute("id", id);
				session.setAttribute("members", member);
				forward.setRedirect(true);
				forward.setPath(request.getContextPath()+"/ChallengeList.bo");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정보가 일치하지 않습니다')");
				out.println("history.back();");
				out.println("</script>");
			}
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보가 일치하지 않습니다')");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
