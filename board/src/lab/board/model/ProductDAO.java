package lab.board.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class ProductDAO {
	
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

	public ArrayList<ProductVO> getProductList(){
		//저장돤 상품정보 반환
		ArrayList<ProductVO> pros = new ArrayList<ProductVO>();
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select * from products";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				ProductVO pro = new ProductVO();
				pro.setProductId(rs.getString("productid"));
				pro.setPname(rs.getString("pname"));
				pro.setUnitPrice(rs.getInt("unitprice"));
				pro.setDescription(rs.getString("description"));
				pro.setManufacturer(rs.getString("manufacturer"));
				pro.setCatogory(rs.getString("category"));
				pro.setUnitsInStock(rs.getLong("unitsinstock"));
				pro.setCondition(rs.getString("condition"));
				pro.setFilename(rs.getString("filename"));
				pros.add(pro);
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		return pros;
	}
	
	public ProductVO getProduct(String productid){
		//저장돤 상품정보 반환
		ProductVO pro = null;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select * from products where productid = ?";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setString(1, productid);
			rs = stat.executeQuery();
			while(rs.next()) {
				pro = new ProductVO();
				pro.setProductId(rs.getString("productid"));
				pro.setPname(rs.getString("pname"));
				pro.setUnitPrice(rs.getInt("unitprice"));
				pro.setDescription(rs.getString("description"));
				pro.setManufacturer(rs.getString("manufacturer"));
				pro.setCatogory(rs.getString("category"));
				pro.setUnitsInStock(rs.getLong("unitsinstock"));
				pro.setCondition(rs.getString("condition"));
				pro.setFilename(rs.getString("filename"));

			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
				dbClose(con, stat, rs);
		}
		return pro;
	}


}
