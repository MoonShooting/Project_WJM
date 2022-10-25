package challenge.action;

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
import challenge.svc.ChallengeScoreService;
import vo.ActionForward;
import vo.ChallengeBean;
import vo.MissionBean;

public class ChallengeScoreAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ChallengeScoreService challengeScoreService = new ChallengeScoreService();
		int count = 0;
		int updateSuccess = 0;
		ArrayList<MissionBean> mb = null;
		if(id != null) {
			count = challengeScoreService.getMissionCount(id);
			System.out.println(count + "count");
			if(count > 0) {
				updateSuccess = challengeScoreService.getUpdateScore(id, count);
				if(updateSuccess > 0) {
					mb = challengeScoreService.getMissionList(id);
					for(int i = 0 ; i <mb.size(); i++) {
						System.out.println(mb.get(i).getC_num());
						System.out.println(mb.get(i).getU_id());
					}
					forward = new ActionForward();				
					forward.setRedirect(false);
					request.setAttribute("mission", mb);
					forward.setPath("/challenge/qna_challenge_score.jsp");
				}
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('받을 점수가 없습니다.')");
				out.println("history.back();");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하셔야 합니다.')");
			out.println("location.href='MyPageLoginForm.me';");
			out.println("</script>");
		}
		System.out.println("aad"+mb);
		return forward;
	}

}
