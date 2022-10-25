package survey.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import survey.svc.SurveyProService;
import vo.ActionForward;
import vo.MyPageBean;

public class SurveyProAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		/*값 받아오기*/
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		System.out.println(c_num + "c_num");
		String u_id = (String)session.getAttribute("id");
		int satisfaction = Integer.parseInt(request.getParameter("satisfaction"));
		int experience = Integer.parseInt(request.getParameter("experience"));
		
		/*점수계*/
		int sum = satisfaction + experience; //합계
		boolean save = false;
		int c_point = 0;
		int point = 0;
		String made = null;
		int updatePoint = 0;
		ArrayList<MyPageBean> mpb = new ArrayList<MyPageBean>(); 
		ActionForward forward = new ActionForward();
		if(u_id != null) {
			SurveyProService surveyProService = new SurveyProService();
			save = surveyProService.insertSurvey(c_num, u_id, satisfaction, experience, sum);
			mpb = surveyProService.scorePoint(c_num);
			System.out.println(mpb + "mpb");
			if(mpb != null) {
				made = surveyProService.madeUser(c_num);
				
				for(int i =0; i < mpb.size(); i++) {
					point += mpb.get(i).getC_score();
				}
				if(point > 0) {
					c_point = (point / mpb.size());
					updatePoint = surveyProService.updatePoint(made, c_point);
					System.out.println(updatePoint);
					forward = new ActionForward();
					forward.setPath("/ChallengeList.bo");
				}
		}
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 해야 합니다.');");
			out.println("location.href='login.me';");
			out.println("</script>");
		}
		return forward;
	}
}
