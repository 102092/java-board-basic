package lab.board.model;

import java.sql.Date;
import java.util.Vector;

public class BbsVO {
	private int bid; 
	private int num;
	private String subject;
	private String writer;
	private String password;
	private Date idate;
	private String contents;
	private String email;
	private String ip;
	private String fileYN;
	private int rcount;
	private int vcount;
	protected Vector<CommentVO> comments = new Vector<CommentVO>();
	
	public void addComment(CommentVO a) {
		comments.add(a);
	}
	
	
	public BbsVO() {
		super();
	}

	public BbsVO(int num, int bid, String subject, String writer, String password, Date idate, String contents, String email,
			String ip, String fileYN, int rcount, int vcount) {
		super();
		this.num = num;
		this.bid = bid;
		this.subject = subject;
		this.writer = writer;
		this.password = password;
		this.idate = idate;
		this.contents = contents;
		this.email = email;
		this.ip = ip;
		this.fileYN = fileYN;
		this.rcount = rcount;
		this.vcount = vcount;
	}
	
	


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFileYN() {
		return fileYN;
	}

	public void setFileYN(String fileYN) {
		this.fileYN = fileYN;
	}

	public int getrcount() {
		return rcount;
	}

	public void setrcount(int rcount) {
		this.rcount = rcount;
	}

	public int getvcount() {
		return vcount;
	}

	public void setvcount(int vcount) {
		this.vcount = vcount;
	}


	public Vector<CommentVO> getComments() {
		return comments;
	}


	public void setComments(Vector<CommentVO> comments) {
		this.comments = comments;
	}
	
	
	
	
}
