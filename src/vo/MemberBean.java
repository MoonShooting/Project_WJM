package vo;

import java.sql.Timestamp;

public class MemberBean {

	private int u_num;
	private String u_id;
	private String grade;
	private String u_pw;
	private String name;
	private String nickname;
	private String tel;	
	private int count;
	private int success;
	private int fail;
	private int log_count;
	private Timestamp log_time;
	private String birth;
	private int c_score;
	private int total_rank;
	private int c_count;
	
	
	public int getU_num() {
		return u_num;
	}
	public void setU_num(int u_num) {
		this.u_num = u_num;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getLog_count() {
		return log_count;
	}
	public void setLog_count(int log_count) {
		this.log_count = log_count;
	}
	public Timestamp getLog_time() {
		return log_time;
	}
	public void setLog_time(Timestamp log_time) {
		this.log_time = log_time;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
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
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	
	
}
