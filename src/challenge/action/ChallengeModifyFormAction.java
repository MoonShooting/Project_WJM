
package challenge.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import challenge.svc.ChallengeModifyFormService;
import vo.ActionForward;
import vo.ChallengeBean;

public class ChallengeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(session.getAttribute(id)==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 하세용.')");
			out.println("history.back()");
			out.println("</script>");
		}

		int c_num = Integer.parseInt(request.getParameter("c_num"));
		String page = (String)request.getParameter("page");
		
		ChallengeModifyFormService challengeArticleSvc = new ChallengeModifyFormService();
		ChallengeBean article = challengeArticleSvc.getArticle(c_num);
		System.out.println(id);
		System.out.println(article.getC_id());
		if(id.equals(article.getC_id()) || id.equals("admin")) {
			request.setAttribute("article", article);
			request.setAttribute("page", page);
			forward.setPath("/challenge/qna_challenge_modify.jsp");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('접근 권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}