package vo;

public class RankBean {
   private String u_id;
   private String nickname;
   private String grade;
   private int c_score;
   private int total_rank;
   private int ranking;
   
   
   public String getU_id() {
      return u_id;
   }
   public void setU_id(String u_id) {
      this.u_id = u_id;
   }
   public String getNickname() {
      return nickname;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
   public String getGrade() {
      return grade;
   }
   public void setGrade(String grade) {
      this.grade = grade;
   }
   public int getC_score() {
      return c_score;
   }
   public void setC_score(int c_score) {
      this.c_score = c_score;
   }
   public int getTotal_rank() {
      return total_rank;
   }
   public void setTotal_rank(int total_rank) {
      this.total_rank = total_rank;
   }
   public int getRanking() {
      return ranking;
   }
   public void setRanking(int ranking) {
      this.ranking = ranking;
   }
   
}