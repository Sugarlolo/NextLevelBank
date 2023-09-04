package database;

import beans.TransferAlertBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class TransferAlertMgr {

	DBConnectionMgr pool;
	public TransferAlertMgr() {
		pool = DBConnectionMgr.getInstance();
		
	}
	
	public TransferAlertBean getAlert(TransferAlertBean tAbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT * "
					+ "FROM TRANSFER_ALERT "
					+ "ORDER BY ALERT_NO";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				tAbean.setALERT_NO(rs.getInt(1));
				tAbean.setALERT_DATE(rs.getTimestamp(2));
				tAbean.setALERT_CONTENT(rs.getString(3));
				tAbean.setALERT_MEMBER_ID(rs.getString(4));
				tAbean.setTRANSFER_NO(rs.getInt(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return tAbean;
	}
}
