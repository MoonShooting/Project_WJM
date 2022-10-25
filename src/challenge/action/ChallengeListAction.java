package challenge.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import challenge.svc.ChallengeListService;
import vo.ActionForward;
import vo.ChallengeBean;
import vo.PageInfo;


public class ChallengeListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<ChallengeBean> articleList = new ArrayList<ChallengeBean>();
		int page = 1;
		int limit = 6;
		int limitPage = 10;
		String challenge_menu = null;
		String challenge_search = null;
		
		if(request.getParameter("challenge_menu") != null) {
			challenge_menu = request.getParameter("challenge_menu");
		}else {
			challenge_menu = "";
		}
		
		if(request.getParameter("challenge_search") != null) {
			challenge_search = request.getParameter("challenge_search");
		}else {
			challenge_search = "";
		}
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println(page);
		
		ChallengeListService challengeListService = new ChallengeListService();
		int listCount = challengeListService.getListCount(challenge_menu, challenge_search,page,limit);
		articleList = challengeListService.getArticleList(challenge_menu, challenge_search,page,limit);
		int maxPage = (int)((double)listCount/limit +0.95);
		int startPage = (((int)((double)page / limitPage + 0.9))-1)*limitPage +1;
		int endPage = startPage + limitPage -1;
		
		if(endPage > maxPage) endPage = maxPage;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setPage(page);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward = new ActionForward();
		forward.setPath("/challenge/qna_challenge_list.jsp");
		return forward;
	}

}