package survey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class SurveyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		request.setAttribute("c_num", c_num);
		forward.setPath("/survey/surveyForm.jsp");
		return forward;
	}

}
