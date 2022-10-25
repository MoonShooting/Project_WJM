
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import challenge.action.ChallengeDeleteFormAction;
import challenge.action.ChallengeDeleteProAction;
//import action.ChallengeDeleteProAction;
import challenge.action.ChallengeDetailAction;
import challenge.action.ChallengeGiveupAction;
import challenge.action.ChallengeListAction;
import challenge.action.ChallengeMissionAction;
import challenge.action.ChallengeModifyFormAction;
import challenge.action.ChallengeModifyProAction;
import challenge.action.ChallengeParticipAction;
import challenge.action.ChallengeScoreAction;
//import action.ChallengeModifyFormAction;
//import action.ChallengeModifyProAction;
//import action.ChallengeReplyFormAction;
//import action.ChallengeReplyProAction;
import challenge.action.ChallengeWriteProAction;
//import action.FileDownAction;
import vo.ActionForward;

/**
 * Servlet implementation class ChallengeFrontController
 */
@WebServlet(urlPatterns = {"*.bo"})
public class ChallengeFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String RequestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println(command);
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	// command 부분
    	if(command.equals("/ChallengeWriteForm.bo")) {
    		forward = new ActionForward();
    		forward.setPath("/challenge/qna_challenge_write.jsp");
    	} else if(command.equals("/ChallengeWritePro.bo")) {
    		action = new ChallengeWriteProAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if (command.equals("/ChallengeList.bo")) {
    		action = new ChallengeListAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if (command.equals("/ChallengeDetail.bo")) {
    		action = new ChallengeDetailAction();
    		try {
    			
    			forward = action.execute(request, response);
    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	
    	}else if (command.equals("/ChallengeModifyForm.bo")) {
    		action = new ChallengeModifyFormAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if (command.equals("/ChallengeModifyPro.bo")) {
    		action = new ChallengeModifyProAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/ChallengeDeleteForm.bo")){
    		action = new ChallengeDeleteFormAction();
    		try{
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if (command.equals("/ChallengeDeletePro.bo")) {
    		action = new ChallengeDeleteProAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}	
    
		}else if(command.equals("/ChallengeParticip.bo")) {
    			action = new ChallengeParticipAction();
        		try {
        			forward = action.execute(request, response);
        		} catch (Exception e) {
        			e.printStackTrace();
    		}
    		}else if(command.equals("/ChallengeGiveup.bo")) {
    			action = new ChallengeGiveupAction();
    			try {
    				forward = action.execute(request, response);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}else if(command.equals("/ChallengeMission.bo")) {
    			action = new ChallengeMissionAction();
    			try {
    				forward = action.execute(request, response);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}else if(command.equals("/ChallengeScore.bo")) {
    			action = new ChallengeScoreAction();
    			try {
    				forward = action.execute(request, response);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}else if(command.equals("/ChallengeScore.bo")) {
        		forward = new ActionForward();
        		forward.setPath("/challenge/qna_challenge_score.jsp");
        	} else if (command.equals("/ChallengeDeleteForm.bo")){
        		action = new ChallengeDeleteFormAction();
        		try{
        			forward = action.execute(request, response);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}
        	}
    
    	// forward 부분 -> 동작을 해야만 경로가 이동이 된다.
    	if(forward != null) {
    		if(forward.isRedirect()) {
    		response.sendRedirect(forward.getPath());
    	}else{
    		RequestDispatcher dispatcher =
    				request.getRequestDispatcher(forward.getPath());
    		dispatcher.forward(request, response);
    		}
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		doProcess(request, response);
	}
}