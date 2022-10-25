package myPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import myPage.svc.MyPageJoinService;
import vo.ActionForward;
import vo.MemberBean;

public class MyPageJoinAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		MemberBean mb = null;
		boolean joinSuccess = false;
		int success = 0;
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String tel = request.getParameter("tel");
		String birth = request.getParameter("birth");
	
		MyPageJoinService myPageJoinService = new MyPageJoinService();
		success = myPageJoinService.joinSuccess(id, pw, name, nickname, tel, birth);
		
		if(success <= 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패했습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/MyPageLoginForm.me");
		}
		return forward;
	}

}
