package challenge.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import challenge.svc.ChallengeParticipService;
import vo.ActionForward;
import vo.ChallengeBean;

public class ChallengeParticipAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String id= null;
		int c_num = 0;
		boolean attend = false;
		int c_particip = 0;
		int attendCount = 0;
		if(request.getParameter("c_num") != null) {
			c_num = Integer.parseInt(request.getParameter("c_num"));
		}else {
			c_num = 0;
		}
		if(session.getAttribute("id") != null) {
			id = (String)session.getAttribute("id");
		}else {
			id = "";
		}
		if(id==null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다')");
			out.println("history.back()");	
			out.println("</script>");			
			} else {
			ChallengeBean challengeBean = new ChallengeBean();
			ChallengeParticipService challengeParticipService = new ChallengeParticipService();
			int insertCount = 0;
			challengeBean = challengeParticipService.getParticipInfo(c_num, id);
			if(challengeBean != null) {
				attend = challengeParticipService.getParticipDouble(c_num,id);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.')");
				out.println("history.back()");	
				out.println("</script>");
			}
			
			if(attend) {
				insertCount = challengeParticipService.getParticipCondition(c_num,insertCount,challengeBean,attend, id);
				c_particip = challengeParticipService.plusParticipCount(attend,challengeBean);
				attendCount = challengeParticipService.plusAttendCount(id);
				
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('챌린지 참여가 완료되었습니다.');");
				out.println("location.href='"+request.getContextPath()+"/MyPageForm.me?id="+id+"'");
				out.println("</script>");
			}
			forward = null;
		}
		return forward;
	}

}
