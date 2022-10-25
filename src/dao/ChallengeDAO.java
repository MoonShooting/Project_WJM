
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import vo.ChallengeBean;
import vo.MissionBean;

import static db.JdbcUtil.*;

public class ChallengeDAO {
	
	DataSource ds;
	Connection con;
	private static ChallengeDAO challengeDAO;
	
	private ChallengeDAO() {}
	
	public static ChallengeDAO getInstance() {
		if(challengeDAO == null) {
			challengeDAO = new ChallengeDAO();
		}
		return challengeDAO;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//글 개수보기
	
	public int selectListCount(String challenge_menu, String challenge_search, int page, int limit) {
		int listCount =0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from c_challenge_view where c_condition='Y' or c_condition='ing'";
		int startrow = (page-1)*limit;
		try {
			if(challenge_menu.equals("c_num")) {
				sql += " and c_num like "+ "'%"+challenge_search+"%'";
			}else if(challenge_menu.equals("c_subject")) {
				sql += " and c_subject like "+ "'%"+challenge_search+"%'";
			}else if(challenge_menu.equals("c_id")) {
				sql += " and c_id like "+ "'%"+challenge_search+"%'";
			}else if(challenge_menu.equals("startday")) {
				sql += " and startday like "+ "'%"+challenge_search+"%'";
			}else {
				sql = "select count(*) from c_challenge_view where c_condition='Y' or c_condition='ing'";
			}
			pstmt = con.prepareStatement(sql);//board = table
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount 오류 : "+ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	//글목록 보기
	public ArrayList<ChallengeBean> selectArticleList(String challenge_menu, String challenge_search, int page, int limit){
		ArrayList<ChallengeBean> articleList = new ArrayList<ChallengeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from c_challenge_view where c_condition='Y' or c_condition='ing'";
		ChallengeBean challenge = null;
		int startrow = (page-1)*limit;	//시작 row번호
		try {
			if(challenge_menu.equals("c_num")) {
				sql += " and c_num like "+ "'%"+challenge_search+"%'"+ " order by c_condition desc, c_num desc limit "+startrow+","+ limit;
			}else if(challenge_menu.equals("c_subject")) {
				sql += " and c_subject like "+ "'%"+challenge_search+"%'"+ " order by c_condition desc, c_num desc limit "+startrow+","+ limit;
			}else if(challenge_menu.equals("c_id")) {
				sql += " and c_id like "+ "'%"+challenge_search+"%'"+ " order by c_condition desc, c_num desc limit "+startrow+","+ limit;
			}else if(challenge_menu.equals("startday")) {
				sql += " and startday like "+ "'%"+challenge_search+"%'"+ " order by c_condition desc, c_num desc limit "+startrow+ limit;
			}else {
				sql += " order by c_condition desc, c_num desc limit "+ startrow+"," + limit;
			}
			pstmt =con.prepareStatement(sql);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					challenge = new ChallengeBean();
					
					challenge.setC_num(rs.getInt("c_num"));
					challenge.setC_id(rs.getString("c_id"));
					challenge.setC_subject(rs.getString("c_subject"));
					challenge.setC_text(rs.getString("c_text"));
					challenge.setStartday(rs.getString	("startday"));
					challenge.setEndday(rs.getString("endday"));
					challenge.setC_time(rs.getString("c_time"));
					challenge.setC_date(rs.getTimestamp("c_date"));
					challenge.setDay(rs.getString("day"));
					challenge.setC_success(rs.getInt("c_success"));
					challenge.setC_fail(rs.getInt("c_fail"));
					challenge.setReadcount(rs.getInt("readcount"));
					challenge.setParticip(rs.getInt("c_particip"));
					challenge.setC_condition(rs.getString("c_condition"));
					articleList.add(challenge);
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardLis error : "+ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	//글내용
	public ChallengeBean selectArticle(int c_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ChallengeBean challengeBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from c_challenge_view where c_num =?");
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				challengeBean = new ChallengeBean();
				challengeBean.setC_num(rs.getInt("c_num"));
				challengeBean.setC_id(rs.getString("c_id"));
				challengeBean.setC_subject(rs.getString("c_subject"));
				challengeBean.setC_text(rs.getString("c_text"));
				challengeBean.setStartday(rs.getString	("startday"));
				challengeBean.setEndday(rs.getString("endday"));
				challengeBean.setC_time(rs.getString("c_time"));
				challengeBean.setDay(rs.getString("day"));
				challengeBean.setC_date(rs.getTimestamp("c_date"));
				challengeBean.setReadcount(rs.getInt("readcount"));
				challengeBean.setParticip(rs.getInt("c_particip"));
			}
		}catch(Exception ex) {
			System.out.println("getDetail error : "+ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return challengeBean;
	}		
		//수정
		
		public int updateArticle(ChallengeBean article) {
			
			int updateCount =0;
			PreparedStatement pstmt=null;
			String sql="update c_challenge set c_subject = ? , c_text = ?, startday = ?, endday = ?, c_time = ?, day = ?  where c_num=?";
			String sql1="update c_proposer set c_subject = ? , startday = ?, endday = ?, c_time = ?, day = ?  where c_num=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getC_subject());
				pstmt.setString(2, article.getC_text());
				pstmt.setString(3, article.getStartday());
				pstmt.setString(4, article.getEndday());
				pstmt.setString(5, article.getC_time());
				pstmt.setString(6, article.getDay());
				pstmt.setInt(7, article.getC_num());
				updateCount = pstmt.executeUpdate();
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, article.getC_subject());
				pstmt.setString(2, article.getStartday());
				pstmt.setString(3, article.getEndday());
				pstmt.setString(4, article.getC_time());
				pstmt.setString(5, article.getDay());
				pstmt.setInt(6, article.getC_num());
				updateCount = pstmt.executeUpdate() + updateCount;
			}catch(Exception ex) {
				System.out.println("BoardModify error : "+ex);
			}finally {
				close(pstmt);
			}
			return updateCount;
		}
		//삭제
		
		public  int deleteArticle(int c_num) {
			PreparedStatement pstmt = null;
			String challenge_delete_sql = "delete from c_challenge where c_num=?";
			int deleteCount = 0;
			
			try {
				pstmt = con.prepareStatement(challenge_delete_sql);
				pstmt.setInt(1, c_num);
				deleteCount = pstmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println("BoardDelete error : "+ex);
			}finally {
				close(pstmt);
			}
			return deleteCount;
		}
		
		/*조회수*/
		public int updateReadCount(int c_num) {
			
			PreparedStatement pstmt = null;
			int updateCount = 1;
	//		ResultSet rs = null;
	//		ChallengeBean cb = new ChallengeBean();
			String sql = "update c_challenge set readcount = "+"readcount+1 where c_num = " + c_num;
			
			try {
				
				pstmt = con.prepareStatement(sql);
	//			rs = pstmt.executeUpdate();
			
				updateCount = pstmt.executeUpdate();
			
				
			}catch(Exception ex) {
				System.out.println("setReadCountUpdate error : "+ex);
			}finally {
				close(pstmt);
			}
			
			return updateCount;
		}
		
		//글쓴이 확인
		public boolean isArticleChallengeWriter(int c_num,String pw) {
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String challenge_sql = "select * from c_challenge_view where c_num = ?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(challenge_sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(pw.equals(rs.getString("c_pw"))) {
				isWriter =true;
			}
					
		}catch(Exception ex) {
			System.out.println("isBoardWriter error : "+ex);
		}finally {
			close(pstmt);
		}
		return isWriter;
		}

		public ChallengeBean selectMission(int c_num) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ChallengeBean cb = null;
			String sql = "select * from c_challenge_view where c_num = ?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, c_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cb.setC_num(rs.getInt("c_num"));
					cb.setC_condition(rs.getString("c_condition"));
					cb.setC_success(rs.getInt("c_success"));
					cb.setC_fail(rs.getInt("faile"));
					cb.setStartday(rs.getString("startday"));
					cb.setEndday(rs.getString("endday"));
					cb.setDay(rs.getString("day"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return cb;
		}

		public ChallengeBean participInfo(int c_num, String id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			System.out.println(c_num);
			String sql = "select c.c_num, u.u_id, u.grade, c.startday, c.endday, c.c_time, c.day, c.c_subject, c.c_condition, u.c_count from user_view u left outer join c_challenge_view c on u_id = c_id where (u.u_id = '" + id + "' and c.c_num = " + c_num+") and (c_condition = 'Y' or c_condition = 'ing')";
			ChallengeBean challengeBean = null;
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					challengeBean = new ChallengeBean();
					challengeBean.setC_num(rs.getInt("c_num"));
					challengeBean.setStartday(rs.getString("startday"));
					challengeBean.setEndday(rs.getString("endday"));
					challengeBean.setC_time(rs.getString("c_time"));
					challengeBean.setDay(rs.getString("day"));
					challengeBean.setC_subject(rs.getString("c_subject"));
					challengeBean.setU_id(rs.getString("u_id"));
					challengeBean.setGrade(rs.getString("grade"));
					challengeBean.setC_condition(rs.getString("c_condition"));
					challengeBean.setU_condition("Y");
					challengeBean.setC_count(rs.getInt("c_count"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return challengeBean;
		}

		public int participCondition(int c_num, int insertCount, ChallengeBean challengeBean, boolean attend, String id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null; 
			
			try {
				if(attend = true) {
					sql = "insert into c_proposer(c_num, u_id, startday, endday, c_time, day, u_condition, c_subject, c_condition) values(?,?,?,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, c_num);
					pstmt.setString(2, id);
					pstmt.setString	(3, challengeBean.getStartday());
					pstmt.setString	(4, challengeBean.getEndday());
					pstmt.setString(5, challengeBean.getC_time());
					pstmt.setString(6, challengeBean.getDay());
					pstmt.setString(7, challengeBean.getU_condition());
					pstmt.setString(8, challengeBean.getC_subject());
					pstmt.setString(9, challengeBean.getC_condition());
					insertCount = pstmt.executeUpdate();
				}				
				System.out.println(pstmt + "참여");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return insertCount;
		}

		public int plusParticipCount(boolean attend, ChallengeBean challengeBean) {
			int c_particip = 0;
			PreparedStatement pstmt = null;
			String sql = "update c_challenge set c_particip = c_particip+1 where c_num ="+challengeBean.getC_num();
			try {  
				pstmt = con.prepareStatement(sql);
				c_particip = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return c_particip;
		}

		public boolean indentification(int c_num, String id) {
			boolean identification = false;
			PreparedStatement pstmt = null;
			String sql = "delete from c_proposer where u_id = ? and c_num = ? ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				identification = pstmt.execute();
				System.out.println(pstmt);
				
				if(identification = true){
					commit(con);
				}else {
					System.out.println("identification is false");
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
				rollback(con);
			}finally {
				close(pstmt);
			}
			
		
			return identification;
		}

		public boolean attend(String id, int c_num, String c_time) {
			boolean attend = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		//	String sql = "select * from c_proposer where c_num = ? and u_id = ?";
			String sql = "select * from c_proposer p inner join c_challenge_view c on c.c_num = p.c_num where c.c_num= ? and u_id = ?";

			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, c_num);
				pstmt.setString(2, id);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if (rs.next()) {
					attend = true;
				} else {
					attend = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				rollback(con);
			} finally {
				close(pstmt);
			}
			return attend;
		}

		public int getParticip(int c_num) {
			int participCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(*) from c_proposer where c_num = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, c_num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					participCount = rs.getInt(1);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return participCount;
		}
		public int plusAttendCount(String id) {
			int attendCount = 0;
			PreparedStatement pstmt = null;
			String sql = "update user set count = count+1 where u_id = '"+ id +"'";
			try {  
				pstmt = con.prepareStatement(sql);
				attendCount = pstmt.executeUpdate();
				System.out.println(pstmt);
				commit(con);
			}catch(Exception e) {
				e.printStackTrace();
				rollback(con);
			}finally {
				close(pstmt);
			}
			
			return attendCount;
		}

		public int minusCount(String id) {
			int minusCount = 0;
			PreparedStatement pstmt = null;
			String sql = "update user set count = count-1 where u_id = '"+ id +"'";
			try {  
				pstmt = con.prepareStatement(sql);
				minusCount = pstmt.executeUpdate();
				commit(con);
			}catch(Exception e) {
				e.printStackTrace();
				rollback(con);
			}finally {
				close(pstmt);
			}
			
			return minusCount;
		}

		public int insertArticle(String c_id, String c_subject, String c_text, String startday, String endday, String c_time, String day, String c_mode) {
			PreparedStatement pstmt = null;
			String sql = "insert into c_challenge(c_id, c_subject, c_text, c_condition, c_date, c_success,c_fail, readcount, c_particip, startday,endday, c_time,day,s_count,c_mode) "
					+ "values(?,?,?,'Y',now(),0,0,0,0,?,?,?,?,0,?)";
			int insertCount = 0;
			
			try {
				pstmt = con.prepareStatement(sql);				
				pstmt.setString(1, c_id);
				pstmt.setString(2, c_subject);
				pstmt.setString(3, c_text);
				pstmt.setString(4, startday);
				pstmt.setString(5,endday);
				pstmt.setString(6, c_time);
				pstmt.setString(7, day);
				pstmt.setString(8, c_mode);
				System.out.println(pstmt);
				insertCount = pstmt.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println("BoardInsert error : "+ex);
			}finally {
				close(pstmt);
			}
			return insertCount;
		}

		public int userUpdate(String id, int c_num) {
			PreparedStatement pstmt = null;
			int userUpdate = 0;
			int proposerUpdate = 0; 
			String sql = "update user set success = success + 1, c_count = c_count + 1 where u_id = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				userUpdate = pstmt.executeUpdate();
				System.out.println(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return userUpdate;
		}

		public ChallengeBean resultMission(String id, int c_num) {
			PreparedStatement pstmt = null;
			boolean result = false; 
			ChallengeBean cb = null;
			ResultSet rs = null;
			String sql = "select * from c_proposer where u_id = ? and c_num=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					cb = new ChallengeBean();
					cb.setC_num(rs.getInt("c_num"));
					cb.setU_condition(rs.getString("u_condition"));
					cb.setStartday(rs.getString("startday"));
					cb.setEndday(rs.getString("endday"));
					cb.setC_time(rs.getString("c_time"));
					cb.setDay(rs.getString("day"));
					cb.setU_id(rs.getString("u_id"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return cb;
		}

		public int checkInfo(String id, int c_num) {
			PreparedStatement pstmt = null;
			int check = 0;
			boolean dataCheck = false;
			String sql = "select * from success where u_id=? and c_num=? and time = DATE_FORMAT(now(), '%Y-%m-%d')";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					check = 10;
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return check;
		}

		public int proposerCheck(String id, int c_num) {
			PreparedStatement pstmt = null;
			int procheck = 0;
			boolean dataCheck = false;
			String sql = "select * from c_proposer where u_id=? and c_num=?";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					procheck = 10;
				}			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return procheck;
		}

		public int checkFailInfo(String id, int c_num) {
			PreparedStatement pstmt = null;
			int check = 0;
			boolean dataCheck = false;
			String sql = "select * from fail where u_id=? and c_num=? and time = DATE_FORMAT(now(), '%Y-%m-%d')";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					check = 10;
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return check;
		}
		public ArrayList<ChallengeBean> getScore(String id) {
			PreparedStatement pstmt = null;
			ArrayList<ChallengeBean> acb = new ArrayList<ChallengeBean>();
			ChallengeBean cb = null;
			ResultSet rs = null;
			String sql = "select *from c_proposer where u_id = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					do {
						String day;
						cb = new ChallengeBean();
						cb.setC_num(rs.getInt("c_num"));
						cb.setU_id(rs.getString("u_id"));
						cb.setU_condition(rs.getString("u_condition"));
						cb.setC_subject(rs.getString("c_subject"));
						cb.setStartday(rs.getString("startday"));
						cb.setEndday(rs.getString("endday"));
						cb.setC_time(rs.getString("c_time"));
						cb.setDay(day=rs.getString("day"));
						day=day.substring(1);
						day=day.substring(0,day.length()-1);
						String[] dayArray = day.split(",");
						
						for(int i = 0; i < dayArray.length; i++) {
							cb.addDay1(dayArray[i].trim());
						}
//						System.out.println(day);
						acb.add(cb);
					}while(rs.next());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return acb;
		}

		public int getMissionCount(String id) {
			PreparedStatement pstmt = null;
			int count = 0;
			String sql = "select count(*) from success where u_id=? and (u_condition = 'Y' or u_condition is null)";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					count = rs.getInt(1);
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return count;
		}

		public int getUpdateScore(String id) {
			PreparedStatement pstmt = null;
			int updateSuccess = 0;
			String sql = "update success set u_condition = 'complete' where u_id = ? and (u_condition = 'success' or u_condition = 'Y')";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				updateSuccess = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return updateSuccess;
		}

		public boolean resultInsert(ChallengeBean cb) {
			PreparedStatement pstmt = null;
			boolean result = false; 
			ResultSet rs = null;
			String sql = "insert into success values(?,?,?,?,?,now(),?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cb.getC_num());
				pstmt.setString(2, cb.getU_condition());
				pstmt.setString(3, cb.getStartday());
				pstmt.setString(4, cb.getEndday());
				pstmt.setString(5, cb.getDay());
				pstmt.setString(6, cb.getU_id());
				result = pstmt.execute();
				System.out.println(result);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return result;
		}

		public ArrayList<MissionBean> getMissionList(String id) {
			ArrayList<MissionBean> mb = new ArrayList<MissionBean>();
			MissionBean missionBean = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select s.c_num, s.startday, s.endday, s.day, s.time, c.c_subject,s.u_id,s.u_condition from success s right join c_challenge c on s.c_num = c.c_num where s.u_id = ? order by s.time asc";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					missionBean = new MissionBean();
					do {
						missionBean.setC_num(rs.getInt("c_num"));
						missionBean.setU_id(rs.getString("u_id"));
						missionBean.setStartday(rs.getString("startday"));
						missionBean.setEndday(rs.getString("endday"));
						missionBean.setDay(rs.getString("day"));
						missionBean.setC_subject(rs.getString("c_subject"));
						mb.add(missionBean);
					}while(rs.next());
					System.out.println(mb);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return mb;
		}
		public int getUserUpdate(String id, int count) {
			PreparedStatement pstmt = null;
			int userUpdate = 0;
			String sql = "update user set c_score = c_score + (15 * " +count +") where u_id = ?";
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				userUpdate = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return userUpdate;
		}
		public int deleteProposer(int c_num) {
			PreparedStatement pstmt = null;
			String sql = "delete from c_proposer where c_num = ?";
			int deleteProposer = 0;
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, c_num);
				deleteProposer = pstmt.executeUpdate();
			}catch(Exception ex) {
				ex.printStackTrace();
				System.out.println(ex);
			}
			return deleteProposer;
		}

		public boolean checkIdNickname(String idCheck, String nickname) {
			boolean success = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from user where u_id = ? or nickname = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, idCheck);
				pstmt.setString(2, nickname);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					success = false;
				}else {
					success = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return success;
		}

		public int updateUser(String id) {
			int userUpdate = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "update user set count -= 1 where u_id = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				userUpdate = pstmt.executeUpdate();
				System.out.println(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return userUpdate;
		}

		public boolean getParticipDouble(int c_num, String id) {
			boolean particip = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from c_proposer where u_id = ? and c_num = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, c_num);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					particip = false;
				}else {
					particip = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return particip;
		}

		public ChallengeBean participInfomation(int c_num, String id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			System.out.println(c_num);
			String sql = "select c.c_num, u.u_id, u.grade, c.startday, c.endday, c.c_time, c.day, c.c_subject, c.c_condition, u.c_count from user_view u left outer join c_challenge_view c on u_id = c_id where (u.u_id = '" + id + "' or c.c_num = " + c_num+") and (c_condition = 'Y' or c_condition = 'ing')";
			ChallengeBean challengeBean = null;
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				if(rs.next()) {
					challengeBean = new ChallengeBean();
					challengeBean.setC_num(rs.getInt("c_num"));
					challengeBean.setStartday(rs.getString("startday"));
					challengeBean.setEndday(rs.getString("endday"));
					challengeBean.setC_time(rs.getString("c_time"));
					challengeBean.setDay(rs.getString("day"));
					challengeBean.setC_subject(rs.getString("c_subject"));
					challengeBean.setU_id(rs.getString("u_id"));
					challengeBean.setGrade(rs.getString("grade"));
					challengeBean.setC_condition(rs.getString("c_condition"));
					challengeBean.setU_condition("Y");
					challengeBean.setC_count(rs.getInt("c_count"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return challengeBean;
		}


}