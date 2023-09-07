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
	
	public boolean inserAutoTransfer(AccountsPublicBean aPBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "";
			pstmt = con.prepareStatement(sql);

			int cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}
