package rank.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.RankDAO;

import static db.JdbcUtil.*;
import vo.RankBean;

public class RankService {
   //유저정보 select (view table)
   public ArrayList<RankBean> getRankinfo() {
      Connection con = null;
      RankDAO rankDAO = null;
      ArrayList<RankBean> rb = null;
      try {
         con = getConnection();
         rankDAO = RankDAO.getInstance();
         rankDAO.setConncetion(con);
         rb = rankDAO.selectRankinfo();
         
      }catch (Exception e) {
         System.out.println("svc getarticle err : " + e);
         e.printStackTrace();
      }finally {
         close(con);
      }
      System.out.println("svc ㅇㅇ");
      return rb;
   }

}