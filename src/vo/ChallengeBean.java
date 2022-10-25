
package vo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class ChallengeBean {
	// 필드
	private int c_num;
	private String c_id;
	private String u_id;
	private String c_pw;
	private String c_subject;
	private String c_text;
	private String c_condition;
	private String startday;
	private String endday;
	private String c_time;
	private String day;
	private String grade;
	private Timestamp c_date;
	private int c_success;
	private int c_fail;
	private int readcount;
	private int particip;
	private String u_condition;
	private String c_mode;
	private int c_count;
	private ArrayList<String> day1=new ArrayList<String>();
	
	public ArrayList<String> getDay1() {
		return day1;
	}
	public void addDay1(String day) {
		day1.add(day);
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_pw() {
		return c_pw;
	}
	public void setC_pw(String c_pw) {
		this.c_pw = c_pw;
	}
	public String getC_subject() {
		return c_subject;
	}
	public void setC_subject(String c_subject) {
		this.c_subject = c_subject;
	}
	public String getC_text() {
		return c_text;
	}
	public void setC_text(String c_text) {
		this.c_text = c_text;
	}
	public String getC_condition() {
		return c_condition;
	}
	public void setC_condition(String c_condition) {
		this.c_condition = c_condition;
	}
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}
	public int getC_success() {
		return c_success;
	}
	public void setC_success(int c_success) {
		this.c_success = c_success;
	}
	public int getC_fail() {
		return c_fail;
	}
	public void setC_fail(int c_fail) {
		this.c_fail = c_fail;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getParticip() {
		return particip;
	}
	public void setParticip(int particip) {
		this.particip = particip;
	}
	public String getU_condition() {
		return u_condition;
	}
	public void setU_condition(String u_condition) {
		this.u_condition = u_condition;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	public String getC_time() {
		return c_time;
	}
	public void setC_time(String string) {
		this.c_time = string;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getC_mode() {
		return c_mode;
	}
	public void setC_mode(String c_mode) {
		this.c_mode = c_mode;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	
	
	}