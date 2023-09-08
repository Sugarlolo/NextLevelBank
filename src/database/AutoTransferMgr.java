package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.AccountsBean;
import beans.AccountsPublicBean;
import beans.AutoTransferBean;

public class AutoTransferMgr {

	private DBConnectionMgr pool;
	

	public AutoTransferMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public boolean inserAutoTransfer(AutoTransferBean aTBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "INSERT INTO AUTO_TRANSFER (?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aTBean.getTakeccountNum());
			pstmt.setInt(2, aTBean.getDoccountNum());
			pstmt.setInt(3, aTBean.getTransBalance());
			pstmt.setInt(4, aTBean.getTransferDate());
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}
