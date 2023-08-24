package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import beans.MemberBean;
import beans.TransferBean;
import database.DBConnectionMgr;

public class Transfer_Transaction {
	
	private DBConnectionMgr pool;
	
//	TransferBean tb = 
	public Transfer_Transaction () {
		pool = DBConnectionMgr.getInstance();
	}
	
	public void Transaction_Do(int balance) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		TransferBean tb = new TransferBean();
		String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		try {
			con = pool.getConnection();
			sql = "INSERT INTO TRANSFER (TRANSFER_DO_ACCOUNT,"
					+ "TRANSFER_BALANCE,"
					+ "TRANSFER_TAKE_ACCOUNT,"
					+ "TRANSFER_DATE,"
					+ "TRANSFER_CATEGORY,"
					+ "TRANSFER_MEMO,"
					+ "TRANSFER_DO_BALANCE,"
					+ "TRANSFER_TAKE_BALANCE "
					+ "values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,tb.getTransfer_Do_Account());
			pstmt.setInt(2,tb.getTransfer_Balance());
			pstmt.setInt(3,tb.getTransfer_Take_Account());
			pstmt.setString(4, currentTimestamp);
//			pstmt.setString(5, );
			
			int cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return;
	}
	
	public void Transaction_Take() {
		
	}
	
	public boolean Transaction_Check() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return false;
		
	}
}
