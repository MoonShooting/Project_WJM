package myPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import myPage.svc.IdCheckService;
import vo.ActionForward;

public class idCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String idCheck = null;
		if(request.getParameter("idCheck") != null){
			idCheck = request.getParameter("idCheck").trim();
		}
		String nickname = null;
		if(request.getParameter("nickname") != null){
			nickname = request.getParameter("nickname").trim();
		}
		boolean success = false;
		IdCheckService idCheckService = new IdCheckService();
		if(idCheck != null && nickname != null) {
			success = idCheckService.checkIdNickname(idCheck, nickname);
			if(success) {
				forward.setPath("joinForm.jsp?id="+idCheck+"&nickname="+nickname);
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('중복된 아이디 혹은 닉네임 입니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('값을 입력 해 주세요');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
