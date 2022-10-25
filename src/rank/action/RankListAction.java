package rank.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import rank.svc.RankService;
import vo.ActionForward;
import vo.RankBean;

public class RankListAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession session = request.getSession();
      ArrayList<RankBean> rb = null;
      boolean isRightRank = false;
      ActionForward forward = null;
      
      RankService ranksvc = new RankService();
      rb = ranksvc.getRankinfo();
      
      System.out.println("rb값 : " + rb);
      if (rb != null) {
        forward = new ActionForward();
        
        isRightRank = true;
         request.setAttribute("rb", rb);
        forward.setPath("/rank/rank.jsp");
      } else {
         response.setContentType("text/html;charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('랭크 정보를 불러올 수 없습니다.');");
         out.println("location.href='./index.jsp';");
         out.println("</script>");
      }
      return forward;
   }

}