package lab.board.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class BbsDAO {
	
	public Connection dbCon() {
		Connection con = null;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("C:/workspace2/web2/WebContent/WEB-INF/dbinfo.properties"));
			Class.forName(prop.getProperty(("driver")));
			con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pwd"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void dbClose(Connection con, Statement stat,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stat!=null) stat.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public int getPageCount(int numPerPage) {
		//전체 글 개수를 조회해서 페이지 개수 계산해서 리턴
		int total = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select count(*) from bbs ";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
				
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		int pageCount = (int) Math.ceil(total/(double)numPerPage); //0이 리턴될 수 도 있음
		pageCount = Math.max(pageCount, 1); 
		return pageCount;
		
		
	}
	
	public int insertBbs(BbsVO form) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "insert into bbs (bid, subject, writer, password, idate, contents,email, ip, fileYN) values ( ?,?,?,?, sysdate,?,?,?,?)";
		ResultSet rs = null;
		
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			
			stat.setInt(1, form.getBid());
			stat.setString(2, form.getSubject());
			stat.setString(3, form.getWriter());
			stat.setString(4, form.getPassword());
			stat.setString(5, form.getContents());			
			stat.setString(6, form.getEmail());
			stat.setString(7, form.getIp());
			stat.setString(8, form.getFileYN());
			
			rows = stat.executeUpdate();
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}

		return rows;
	}
	
	public int insertComment(CommentVO comm) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "insert into bbs_comment (cmid, rbid, writer, idate, contents, password, ip) values (bbs_seq.nextval,?,?,sysdate,?,?,?) ";
		ResultSet rs = null;		
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);					
			stat.setInt(1, comm.getRbid());
			stat.setString(2, comm.getWriter());
			stat.setString(3, comm.getContents());
			stat.setString(4, comm.getPassword());			
			stat.setString(5, comm.getIp());
			
			rows = stat.executeUpdate();
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}

		return rows;
	}
	
	public ArrayList<BbsVO> getBbsList(int page, int numPerPage){
		//페이지 번호에 해당하는 게시글 10개를 검색해서 리턴
		ArrayList<BbsVO> lists = new ArrayList<BbsVO>();
		Connection con = null;
		PreparedStatement stat = null;
		int start = (page-1) * numPerPage;
		int end = page * numPerPage;
		String sql = "select num, bid, subject, writer, idate, rcount"
				+ " from (select rownum num, bid, subject, writer, idate, rcount from bbs)"
				+ " where num > "+ start +" and num <=" + end +" order by bid desc ";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				BbsVO bbs = new BbsVO();
				bbs.setNum(rs.getInt("num"));
				bbs.setBid(rs.getInt("bid"));
				bbs.setSubject(rs.getString("subject"));
				bbs.setWriter(rs.getString("writer"));
				bbs.setIdate(rs.getDate("idate"));
				bbs.setrcount(rs.getInt("rcount"));						
				lists.add(bbs);				
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		return lists;
	}
	
	public ArrayList<BbsVO> getBbsList(String key, String word, int page, int numPerPage){
		//검색리스트 반환
		ArrayList<BbsVO> lists = new ArrayList<BbsVO>();
		Connection con = null;
		PreparedStatement stat = null;
		int start = (page-1) * numPerPage;
		int end = page * numPerPage;
		String sql = "select num, bid, subject, writer, idate, rcount"
				+ " from (select rownum num, bid, subject, writer, idate, rcount from bbs)"
				+ " where "+key+" like '"+"%"+word+"%"+"' and num > "+ start +" and num <=" + end +" order by bid desc ";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				BbsVO bbs = new BbsVO();
				bbs.setNum(rs.getInt("num"));
				bbs.setBid(rs.getInt("bid"));
				bbs.setSubject(rs.getString("subject"));
				bbs.setWriter(rs.getString("writer"));
				bbs.setIdate(rs.getDate("idate"));
				bbs.setrcount(rs.getInt("rcount"));						
				lists.add(bbs);				
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		return lists;
	}

	public BbsVO getArticle(int bid) {
		//글번호 pk로 게시글 내용 조회 리턴
		BbsVO bbs = null;
		Connection con = null;
		PreparedStatement stat = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select bid, subject, writer, password, idate, contents, email, fileyn, ip, rcount, vcount ");
		sql.append("from bbs where bid = ?" );
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql.toString(), 
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			stat.setInt(1, bid);
			rs = stat.executeQuery();
			while(rs.next()) {			
				bbs = new BbsVO();
				bbs.setBid(rs.getInt("bid"));
				bbs.setSubject(rs.getString("subject"));
				bbs.setWriter(rs.getString("writer"));
				bbs.setPassword(rs.getString("password"));
				bbs.setIdate(rs.getDate("idate"));
				
				bbs.setFileYN(rs.getString("fileyn"));
				bbs.setContents(rs.getString("contents"));
				bbs.setEmail(rs.getString("email"));
				
				bbs.setIp(rs.getString("ip"));
				int rcount = rs.getInt("rcount");
				rs.updateInt("rcount", rcount+1);
				rs.updateRow();
				bbs.setrcount(rcount+1);
				bbs.setvcount(rs.getInt("vcount"));
			    		
			}
			
			
			StringBuffer sql2 = new StringBuffer();
			sql2.append("select cmid, writer, idate, contents, password, ip from bbs_comment ");
			sql2.append(" where rbid = ? order by cmid desc ");
			//stat = con.prepareStatement(sql2.toString());
			stat.setInt(1, bid);
			//rs = stat.executeQuery();
			
			while(rs.next()) {
				CommentVO ba  = new CommentVO();
				ba.setCmid(rs.getInt("cmid"));
				ba.setRbid(bid);
				ba.setWriter(rs.getString("writer"));
				ba.setDate(rs.getDate("idate"));
				ba.setContents(rs.getString("contents"));
				ba.setPassword(rs.getString("password"));
				ba.setIp(rs.getString("ip"));
				bbs.addComment(ba);
			}
			
			
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
				
		}
		return bbs;

		
	}

	public int updateBbs(BbsVO bbs) {
		//글번호 조건으로 해서 제목, 내용 수정.
		int rows = -1;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "update bbs set subject = ?, contents = ? where bid = ? ";
		ResultSet rs = null;
		
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);

			stat.setString(1, bbs.getSubject());
			stat.setString(2, bbs.getContents());
			stat.setInt(3, bbs.getBid());
			
			rows = stat.executeUpdate();
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}

		return rows;

	}
	
	public int deleteBbs(int rbid) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		
		String sql = null;
		String sql_comment = null;
		sql = "delete from bbs where bid = ?";
		sql_comment = "delete from bbs_comment where rbid = ? ";
		ResultSet rs = null;
		
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql_comment);			
			stat.setInt(1, rbid);
			rows += stat.executeUpdate();
			
			stat = con.prepareStatement(sql);
			stat.setInt(1, rbid);
			rows += stat.executeUpdate();			
		
			
			
			
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}

		return rows;

	}
	
	public int deleteComm(int cmid) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		
		String sql_comment = null;
		sql_comment = "delete from bbs_comment where cmid = ? ";
		ResultSet rs = null;
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql_comment);			
			stat.setInt(1, cmid);
			rows = stat.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		return rows;
	}
	
	public String getBbsPassword(int bid) {
		Connection con = null;
		PreparedStatement stat =null;
		ResultSet rs = null;
		String pwd = null;
		String sql = "select password from bbs where bid = ? ";
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setInt(1, bid);
			rs = stat.executeQuery();
				if(rs.next()) {
					pwd = rs.getString("password");
					//System.out.println(pwd);
				}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
	return pwd;
	}
	
	public String getCommPassword(int cmid) {
		Connection con = null;
		PreparedStatement stat =null;
		ResultSet rs = null;
		String pwd = null;
		String sql = "select password from bbs_comment where cmid = ? ";
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setInt(1, cmid);
			rs = stat.executeQuery();
				if(rs.next()) {
					pwd = rs.getString("password");
					//System.out.println(pwd);
				}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
	return pwd;
	}

	public int getBid() {
		int bid = -1;
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "select bbs_seq.nextval from dual ";
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			if(rs.next()) {
				bid = rs.getInt(1);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose(con, stat, rs);
		}
		return bid;
	}
	
	public int saveFile(FileInfoVO fileinfo) {
		StringBuffer sql =  null;
		int cnt = -1;
		PreparedStatement stat =null;
		Connection con = null;
		ResultSet rs =null;
		sql = new StringBuffer();
		sql.append("insert into bbs_file (fid, rbid, filename, filetype, savedfile) ");
		sql.append(" values (bbsfile_seq.nextval,?, ?, ?, ?) ");
		
		try {
			con = dbCon();
			stat = con.prepareStatement(sql.toString());
			stat.setInt(1, fileinfo.getRbid());
			stat.setString(2, fileinfo.getFilename());
			stat.setString(3, fileinfo.getFiletype());
			stat.setString(4, fileinfo.getSavedfile());
			cnt = stat.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose(con, stat, rs);
		}
		return cnt;		
	}

}