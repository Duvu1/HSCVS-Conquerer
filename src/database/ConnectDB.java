package database;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
    public Connection getConnection() {
    	/* DB와 연결하는 메서드 */
        Connection conn = null;

        try {
            // 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // DB 연결
            String url = "jdbc:mysql://localhost/cvsdb";

            // getConnection이 연결 객체를 반환한다.
            conn = DriverManager.getConnection(url, "root", "1998train!");
            System.out.println("연결 성공");
        }
        catch(ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e) {
            System.out.println("에러: " + e);
        }
        
        return conn;
    }
    
    public ArrayList<ItemVO> exploreItem(String keyword) {
    	/* 전체 상품을 조회하는 메서드 */
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	// 조회한 아이템들을 저장 및 반환할 ArrayList
    	ArrayList<ItemVO> rsList = new ArrayList<ItemVO>();
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "select iname, "
    				+ "iprice, "
    				+ "istore, "
    				+ "imanufacturer, "
    				+ "iamount, "
    				+ "iquantity, "
    				+ "iregisterdate, "
    				+ "isbookmark "
    				+ "from itemlist "
    				+ "order by iname asc";		// 오름차순 조회
    		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
                    
            while(rs.next()) {
            	ItemVO itemResult = new ItemVO();
            	itemResult.setIname(rs.getString(1));
            	itemResult.setIprice(rs.getString(2));
            	itemResult.setIstore(rs.getString(3));
            	itemResult.setImanufacturer(rs.getString(4));
            	itemResult.setIamount(rs.getString(5));
            	itemResult.setIquantity(rs.getString(6));
            	itemResult.setIregisterdate(rs.getString(7));
            	itemResult.setIsbookmark(rs.getBoolean(8));
            	
                rsList.add(itemResult);
            }
    	}
        catch( SQLException e) {
            System.out.println("에러 " + e);
        }
        finally {
            try {
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
                if(rs != null && !rs.isClosed())
                    rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return rsList;
    }
    
    public ArrayList<ItemVO> searchItem(String keyword) {
    	/* 키워드가 들어간 상품명을 조회하는 메서드 */
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	// 조회한 아이템들을 저장 및 반환할 ArrayList
    	ArrayList<ItemVO> rsList = new ArrayList<ItemVO>();
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "select iname, "
    				+ "iprice, "
    				+ "istore, "
    				+ "imanufacturer, "
    				+ "iamount, "
    				+ "iquantity, "
    				+ "iregisterdate, "
    				+ "isbookmark "
    				+ "from itemlist "
    				+ "where iname "
    				+ "like '%"
    				+ keyword
    				+ "%'";
    		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
                    
            while(rs.next()) {
            	ItemVO itemResult = new ItemVO();
            	itemResult.setIname(rs.getString(1));
            	itemResult.setIprice(rs.getString(2));
            	itemResult.setIstore(rs.getString(3));
            	itemResult.setImanufacturer(rs.getString(4));
            	itemResult.setIamount(rs.getString(5));
            	itemResult.setIquantity(rs.getString(6));
            	itemResult.setIregisterdate(rs.getString(7));
            	itemResult.setIsbookmark(rs.getBoolean(8));
            	
                rsList.add(itemResult);
            }
    	}
        catch( SQLException e) {
            System.out.println("에러 " + e);
        }
        finally {
        	try {
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
                if(rs != null && !rs.isClosed())
                	rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return rsList;
    }
    
    public ArrayList<ItemVO> bookmarkedItem() {
    	/* 즐겨찾기된 아이템을 조회하는 메서드 */
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	// 조회한 아이템들을 저장 및 반환할 ArrayList
    	ArrayList<ItemVO> rsList = new ArrayList<ItemVO>();
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "select iname, "
    				+ "iprice, "
    				+ "istore, "
    				+ "imanufacturer, "
    				+ "iamount, "
    				+ "iquantity, "
    				+ "iregisterdate, "
    				+ "isbookmark "
    				+ "from itemlist "
    				+ "where isbookmark = true";
    		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
                    
            while(rs.next()) {
            	ItemVO itemResult = new ItemVO();
            	itemResult.setIname(rs.getString(1));
            	itemResult.setIprice(rs.getString(2));
            	itemResult.setIstore(rs.getString(3));
            	itemResult.setImanufacturer(rs.getString(4));
            	itemResult.setIamount(rs.getString(5));
            	itemResult.setIquantity(rs.getString(6));
            	itemResult.setIregisterdate(rs.getString(7));
            	itemResult.setIsbookmark(rs.getBoolean(8));
            	
                rsList.add(itemResult);
            }
    	}
        catch(SQLException e) {
            System.out.println("에러 " + e);
        }
        finally {
            try {
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
                if(rs != null && !rs.isClosed())
                    rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return rsList;
    }
    
    public ItemVO getItem(String iname, String istore) {
    	/* 키로 특정 아이템을 조회하는 메서드 */
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	// 조회한 아이템들을 저장 및 반환할 객체
    	ItemVO itemResult = new ItemVO();
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "select iname, "
    				+ "iprice, "
    				+ "istore, "
    				+ "imanufacturer, "
    				+ "iamount, "
    				+ "iquantity, "
    				+ "iregisterdate, "
    				+ "isbookmark "
    				+ "from itemlist "
    				+ "where iname = \""
    				+ iname
    				+ "\" and "
    				+ "istore = \""
    				+ istore
    				+ "\"";
    		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(!rs.next()) return null;
			itemResult.setIname(rs.getString(1));
        	itemResult.setIprice(rs.getString(2));
        	itemResult.setIstore(rs.getString(3));
        	itemResult.setImanufacturer(rs.getString(4));
        	itemResult.setIamount(rs.getString(5));
        	itemResult.setIquantity(rs.getString(6));
        	itemResult.setIregisterdate(rs.getString(7));
        	itemResult.setIsbookmark(rs.getBoolean(8));
    	}
        catch(SQLException e) {
            System.out.println("에러 " + e);
        }
        finally {
            try {
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
                if(rs != null && !rs.isClosed())
                    rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return itemResult;
    }
    
    public int insertItem(ItemVO itemInfo) {
    	/* 상품 데이터를 추가하는 메서드 */
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "insert into itemlist values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, itemInfo.getIname());
			pstmt.setString(2, itemInfo.getIprice());
			pstmt.setString(3, itemInfo.getIstore());
			pstmt.setString(4, itemInfo.getImanufacturer());
			pstmt.setString(5, itemInfo.getIamount());
			pstmt.setString(6, itemInfo.getIquantity());
			pstmt.setString(7, itemInfo.getIregisterdate());
			pstmt.setBoolean(8, itemInfo.getIsbookmark());
			
			pstmt.executeUpdate();
    	}
        catch(SQLException e) {
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(pstmt != null && !pstmt.isClosed()) 
                    pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return 0;
    }
    
    public int updateItem(ItemVO itemInfo) {
    	/* 상품 데이터를 수정하는 메서드 */
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "update itemlist "
    				+ "set "
    				+ "iprice = ?, "
    				+ "imanufacturer = ?, "
    				+ "iamount = ?, "
    				+ "iquantity = ?, "
    				+ "iregisterdate = ?, "
    				+ "isbookmark = ? "
    				+ "where iname = ?"
    				+ "and "
    				+ "istore = ?";
    		
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, itemInfo.getIprice());
			pstmt.setString(2, itemInfo.getImanufacturer());
			pstmt.setString(3, itemInfo.getIamount());
			pstmt.setString(4, itemInfo.getIquantity());
			pstmt.setString(5, itemInfo.getIregisterdate());
			pstmt.setBoolean(6, itemInfo.getIsbookmark());
			pstmt.setString(7, itemInfo.getIname());
			pstmt.setString(8, itemInfo.getIstore());
			
			pstmt.executeUpdate();
    	}
        catch(SQLException e) {
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(pstmt != null && !pstmt.isClosed()) 
                    pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    	
    	return 0;
    }
    
    public void deleteItem(String iname, String istore) {
    	/* 키워드가 들어간 상품명을 가진 데이터를 삭제하는 메서드 */
    	Connection conn = null;
    	Statement stmt = null;
    	
    	try {
    		conn = getConnection();
    		
    		String sql = "delete "
    				+ "from itemlist "
    				+ "where iname = \""
    				+ iname
    				+ "\" and "
    				+ "istore = \""
    				+ istore
    				+ "\"";
    		
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
    	}
        catch( SQLException e) {
            System.out.println("에러 " + e);
        }
        finally {
            try {
                if(conn != null && !conn.isClosed())
                    conn.close();
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}