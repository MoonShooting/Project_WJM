package challenge.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import challenge.svc.ChallengeDetailService;
import challenge.svc.ChallengeWriteProService;
import vo.ChallengeBean;
import vo.MissionBean;

public class ChallengeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		String page = request.getParameter("page");
		boolean attend = false;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int participCount = 0;
		
	
		
		ChallengeDetailService challengeDetailService = new ChallengeDetailService();
		

		ChallengeBean article = challengeDetailService.getArticle(c_num); 
		String c_time = null;
		if(article != null)
			c_time = article.getC_time();
		
		
	
		attend = challengeDetailService.attend(id,c_num,c_time);
		participCount = challengeDetailService.particip(c_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		request.setAttribute("participCount", participCount);
		request.setAttribute("attend", attend);
		request.setAttribute("c_time", c_time);
		forward.setPath("/challenge/qna_challenge_view.jsp");
		
		return forward;
	}

}