package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;
import vo.RankBean;

public class RankDAO {
   
   public static RankDAO rankDAO;
   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   DataSource ds;
   
   private RankDAO() {}
   
   public static RankDAO getInstance() {
      if(rankDAO == null) {
         rankDAO = new RankDAO();
      }
      return rankDAO;
   }

   public void setConncetion(Connection con) {
      this.con = con;      
   }

   public ArrayList<RankBean> selectRankinfo() {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      RankBean rank = null;
      ArrayList<RankBean> rb = new ArrayList<>();
      
      String sql = "select * from rank_view where u_id not in('admin')";

      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         System.out.println("pstmt : " + pstmt);
         if(rs.next()) {
            do {
               rank = new RankBean();
               rank.setU_id(rs.getString("u_id"));
               rank.setNickname(rs.getString("nickname"));
               rank.setGrade(rs.getString("grade"));
               rank.setC_score(rs.getInt("c_score"));
               rank.setRanking(rs.getInt("ranking"));
               rb.add(rank);
            } while(rs.next());
         }
      }catch (Exception e) {
         e.printStackTrace();
         System.out.println("selectRankDAO err : " + e);
      }finally {
         close(rs);
         close(pstmt);
      }
      System.out.println("DAO 지나가유~");
      return rb;
   }
   
}