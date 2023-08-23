package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnectionMgr;

public class Transfer_Transaction {
	
	private DBConnectionMgr pool;

	public Transfer_Transaction () {
		pool = DBConnectionMgr.getInstance();
	}
	
	public void Transaction_Do() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "UPDATE ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			con.setAutoCommit(false);
			
			while(rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return;
	}
	
	public void Transaction_Take() {
		
	}
	
	public static void main(String[] args) {
		
	}
}
