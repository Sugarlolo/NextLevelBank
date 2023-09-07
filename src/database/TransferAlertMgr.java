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
					+ "FROM TRANSFER_ALERT t "
					+ "WHERE t.ALERT_MEMBER_ID = ? "
					+ "ORDER BY ALERT_NO DESC LIMIT 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tAbean.getALERT_MEMBER_ID());
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
	
	public int getSubSeconds(TransferAlertBean taBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int subSecond = 0;
		
		try {
			con = pool.getConnection();
			sql = "SELECT TIMESTAMPDIFF(second, t.ALERT_DATE,DATE_ADD(NOW(), INTERVAL 9 HOUR)) AS time_difference_seconds "
					+ "FROM TRANSFER_ALERT t "
					+ "WHERE t.ALERT_MEMBER_ID = ? "
					+ "ORDER BY  t.ALERT_NO DESC LIMIT 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, taBean.ALERT_MEMBER_ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				subSecond = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return subSecond;
	}
	
	
	public static void main(String[] args) {
		TransferAlertMgr taMgr = new TransferAlertMgr();
		TransferAlertBean taBean = new TransferAlertBean();

		//getSubSeconds
		int seconds =0;
		taBean.setALERT_MEMBER_ID("test4");
		seconds =taMgr.getSubSeconds(taBean);
		System.out.println(seconds);
		
		//getAlert
//		taBean.setALERT_MEMBER_ID("test4");
//		taMgr.getAlert(taBean);
//		System.out.println(taBean.getALERT_CONTENT());
		
	}
}
