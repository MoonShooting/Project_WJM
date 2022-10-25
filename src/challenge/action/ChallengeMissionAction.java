package challenge.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import challenge.svc.ChallengeMissionService;
import vo.ActionForward;
import vo.ChallengeBean;

public class ChallengeMissionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		int userUpdate = 0;
		boolean result = false;
		int check = 0;
		int proposerCheck = 0;
		if(id != null) {
			ChallengeMissionService challengeMissionService = new ChallengeMissionService();
			proposerCheck = challengeMissionService.proposerChecking(id,c_num);
			if(proposerCheck > 0) {
				check = challengeMissionService.checking(id,c_num);
				if(check == 0) {
				userUpdate = challengeMissionService.userUpdate(id, c_num);
				result = challengeMissionService.resultMission(id, c_num);
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath(request.getContextPath()+"/surveyForm.survey?c_num="+c_num+"&id="+id);
				}else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('이미 완료한 챌린지 입니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('챌린지에 참여한 유저만 미션완료가 가능합니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하셔야 합니다.')");
			out.println("location.href='"+request.getContextPath()+"/login.main';");
			out.println("</script>");
		}
		
		return forward;
	}

}
