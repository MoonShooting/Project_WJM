
package challenge.action;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import challenge.svc.ChallengeModifyProService;
import vo.ActionForward;
import vo.ChallengeBean;

public class ChallengeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		String nowPage = (String)request.getParameter("page");
		
		
		ChallengeBean article = new ChallengeBean();
		ChallengeModifyProService challengeModifyProService = new ChallengeModifyProService();
		
		if(c_num == 0) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			article.setC_num(c_num);
			article.setC_mode(request.getParameter("mode"));
			article.setC_subject(request.getParameter("c_subject"));
			article.setC_text(request.getParameter("c_text"));
			article.setStartday(request.getParameter("startday"));
			article.setEndday(request.getParameter("endday"));
			article.setC_time(request.getParameter("c_time"));
			String[] days = request.getParameterValues("day");
			String day = null;
			if(days == null && days.equals("")) {
				day = "";
			}else {
				if(days.length > 0) {
				day = Arrays.toString(days);
				}else {
					day = request.getParameter("day");
					
				}
			}
			article.setDay(day);
			isModifySuccess = challengeModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				System.out.println(!isModifySuccess);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<scirpt>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				request.setAttribute("article",article);
				forward.setRedirect(true);
				forward.setPath("ChallengeDetail.bo?c_num="+c_num+"&page="+nowPage);
			}
		}
		return forward;
	}

}