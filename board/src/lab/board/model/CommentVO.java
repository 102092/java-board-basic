package lab.board.model;

import java.sql.Date;

public class CommentVO {
	private int cmid; 
	private int rbid; 
	private String writer; 
	private Date date; 
	private String contents; 
	private String password; 
	private String ip;
	
	public CommentVO() {
		super();
	}

	public CommentVO(int cmid, int rbid, String writer, Date date, String contents, String password, String ip) {
		super();
		this.cmid = cmid;
		this.rbid = rbid;
		this.writer = writer;
		this.date = date;
		this.contents = contents;
		this.password = password;
		this.ip = ip;
	}

	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public int getRbid() {
		return rbid;
	}

	public void setRbid(int rbid) {
		this.rbid = rbid;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
	
}
