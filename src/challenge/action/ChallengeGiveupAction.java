
package challenge.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import challenge.svc.ChallengeGiveupService;
import vo.ActionForward;


public class ChallengeGiveupAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		boolean identification = false;
		
		String id = null;
		int c_num = 0;
		
		if(request.getParameter("c_num")!=null) {
			c_num = Integer.parseInt(request.getParameter("c_num"));
		}else {
			c_num = 0;
		}
		if(request.getParameter("id")!= null) {
			id = (String)request.getParameter("id");
		}else {
			id = "";
		}
		System.out.println(id +" : id");
		if(id == null) {
			forward.setRedirect(true);
			forward.setPath("login.go");
		}else {
			
			ChallengeGiveupService challengeGiveupService = new ChallengeGiveupService();
			identification = challengeGiveupService.identification(c_num,id);
			if(identification = true) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ChallengeList.bo");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('포기실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		int minusCount = ChallengeGiveupService.minusCount(id);
		
		
		return forward;
	}

}