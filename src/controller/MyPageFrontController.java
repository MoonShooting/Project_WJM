package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import myPage.action.MyInfoModifyAction;
import myPage.action.MyInfomationAction;
import myPage.action.MyPageFormAction;
import myPage.action.MyPageJoinAction;
import myPage.action.MyPageLoginAction;
import myPage.action.idCheckAction;
import vo.ActionForward;

/**
 * Servlet implementation class MypageForntController
 */
@WebServlet("*.me")
public class MyPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request,response);
	}    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		//commad 확인
		System.out.println(command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MyPageForm.me")) {
			action = new MyPageFormAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyPageList 에러 :" + e);
				e.printStackTrace();
			}
		}else if(command.equals("/MyPageLoginAction.me")) {
			action = new MyPageLoginAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyPageLogin 에러 :" + e);
				e.printStackTrace();
			}
		}else if(command.equals("/MyPageLoginForm.me")) {
			forward = new ActionForward();
    		forward.setPath("loginForm.jsp");
		}else if(command.equals("/MyPageJoinForm.me")) {
			forward = new ActionForward();
    		forward.setPath("joinForm.jsp");
		}else if(command.equals("/MyPageJoinAction.me")) {
			action = new MyPageJoinAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyPageJoin 에러 :" + e);
				e.printStackTrace();
			}
		}else if(command.equals("/MyInfomation.me")) {
			action = new MyInfomationAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyInfomationAction 에러 :" + e);
				e.printStackTrace();
			}
		}else if(command.equals("/MyInfoModify.me")) {
			action = new MyInfoModifyAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyInfoModify.me 에러 :" + e);
				e.printStackTrace();
			}
		}else if(command.equals("/MyPageLogout.me")) {
			HttpSession session = request.getSession();
			session.invalidate();
			forward = new ActionForward();
    		forward.setPath("/MyPageLoginForm.me");
		}else if(command.equals("/idCheck.me")) {
			action = new idCheckAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("idCheckAction.me 에러 :" + e);
				e.printStackTrace();
			}
		}
		
//################구분선####################
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	
	

}