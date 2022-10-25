package challenge.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import challenge.svc.ChallengeDeleteProService;
import vo.ActionForward;

public class ChallengeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward= null;
		
		
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("id");
		System.out.println(c_id + " : act session id");
		ChallengeDeleteProService challengeDeleteProService = new ChallengeDeleteProService();
		boolean isArticleWriter = challengeDeleteProService.isArticleWriter(c_num,c_id);
		System.out.println("isArticleWrtier : "+isArticleWriter);
		
		boolean SuperPowerAdmin = false;
		
		if(c_id.equals("admin")) {
			SuperPowerAdmin = true;
		}
		
		
		if(!SuperPowerAdmin) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('너 어드민 아니잖아~~.');");
			out.println("history.back();");
			out.println("</script>");	
			out.close();
			if(!isArticleWriter) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('본인이 작성한 게시물만 지울수있습니다.');");
				out.println("history.back();");
				out.println("</script>");	
				out.close();
			}
		}else {
			boolean isDeleteSuccess = challengeDeleteProService.removeArticle(c_num);
			
			 System.out.println(isDeleteSuccess + " is");
			 
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
			
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("ChallengeList.bo");
			}
		}
		return forward;
	}

}