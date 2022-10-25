package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rank.action.RankListAction;
import vo.ActionForward;

/**
 * Servlet implementation class RankFrontController
 */
@WebServlet("*.ra")
public class RankFrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankFrontController() {
        super();
        // TODO Auto-generated constructor stub
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
      request.setCharacterEncoding("UTF-8");
      doProcess(request,response);
   }

   private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String RequestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String command = RequestURI.substring(contextPath.length());
      System.out.println(command);
      
      ActionForward forward = new ActionForward();
      Action action = null;
      
      if(command.equals("/rank.ra")) {
        forward = new ActionForward(); 
         action = new RankListAction();
         try {
            forward = action.execute(request, response);
         }catch (Exception e) {
         e.printStackTrace();
      }
     
      }
      
      /* forward 동작 부분   */
      if(forward != null) {
         if(forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
         } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
            dispatcher.forward(request, response);
         }
      }
   }

}