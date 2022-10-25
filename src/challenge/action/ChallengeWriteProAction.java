
package challenge.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletContext;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;

import com.oreilly.servlet.MultipartRequest;
import vo.ActionForward;
import challenge.svc.ChallengeWriteProService;
import vo.ChallengeBean;

public class ChallengeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("id");
		String c_subject = request.getParameter("c_subject");
		String c_text = request.getParameter("c_text");
		String startday = request.getParameter("startday");
		String endday = request.getParameter("endday");
		String c_time = request.getParameter("c_time");
		String c_mode = null;
		if(c_mode != null && !c_mode.equals("")) {
			c_mode = request.getParameter("mode");
		}else {
			c_mode = "";
		}
		if(c_time != null && !c_time.equals("")) {
			c_time = LocalTime.parse(request.getParameter("c_time")).toString();
		}else {
			c_time = null;
		}
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
		
		System.out.println(day);
		ChallengeWriteProService challengeWriterProService = new ChallengeWriteProService();
		boolean isWriteSuccess = challengeWriterProService.registArticle(c_id, c_subject, c_text, startday, endday, c_time, day, c_mode);
		System.out.println(!isWriteSuccess);
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ChallengeList.bo");
		}
		
		return forward;
	}

}