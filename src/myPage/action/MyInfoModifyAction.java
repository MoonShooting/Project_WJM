package myPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import myPage.svc.MyPageModifyService;
import vo.ActionForward;
import vo.MemberBean;

public class MyInfoModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String tel = request.getParameter("tel");
		String birth = request.getParameter("birth");
		System.out.println(id + "id");
		System.out.println(pw + "pw");
		System.out.println(name + "name");
		System.out.println(nickname + "nickname");
		System.out.println(tel + "tel");
		System.out.println(birth +"birth");
		boolean updateSuccess = false;
		MemberBean mb = new MemberBean();
		MyPageModifyService myPageModifyService = new MyPageModifyService();
		if(id != null && pw != null && name != null && nickname != null && tel != null && birth != null) {
			mb.setU_id(id);
			mb.setU_pw(pw);
			mb.setName(name);
			mb.setNickname(nickname);
			mb.setTel(tel);
			mb.setBirth(birth);
			updateSuccess = myPageModifyService.myInfoUpdate(mb);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/MyInfomation.me");
			
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('빈 값이 있을 수 없습니다')");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
