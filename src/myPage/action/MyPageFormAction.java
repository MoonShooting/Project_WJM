
package myPage.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import myPage.svc.MyPageFormService;
import vo.ActionForward;
import vo.ChallengeBean;
import vo.MyPageBean;

public class MyPageFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		MyPageBean mypageList = null;
		ArrayList<ChallengeBean> myPageChallengeList = null;
		int rank = 0;
		if(id == null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/MyPageLoginForm.me");
		}else {
			forward = new ActionForward();
			MyPageFormService MypageFormsvc = new MyPageFormService();
			mypageList = MypageFormsvc.getMyPageInfo(id);
			myPageChallengeList = MypageFormsvc.getMyPageSchedule(id);
			rank = MypageFormsvc.getRank(id);
			System.out.println(myPageChallengeList + "aaa");
			request.setAttribute("rank", rank);
			request.setAttribute("mypageList", mypageList);
			request.setAttribute("schedule", myPageChallengeList);
			forward.setPath("/myPage/myPageForm.jsp");
		}
		System.out.println("action");
		return forward;
	}

}