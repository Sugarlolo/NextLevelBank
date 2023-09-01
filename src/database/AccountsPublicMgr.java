package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import beans.AccountsBean;
import beans.AccountsPublicBean;

public class AccountsPublicMgr {

	private DBConnectionMgr pool;
	

	public AccountsPublicMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// �������� ���� �� ģ�� �߰�(�������� ���¹�ȣ, �������� ���̵�)
		public boolean InsertAccountPublic(AccountsPublicBean bean) { 
			boolean flag = false;
		 	Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "INSERT INTO ACCOUNT_PUBLIC VALUES( ? , ?) ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,bean.getACCOUNT_NUM());
				pstmt.setInt(2,bean.getACCOUNT_PUBLIC_MEMBER_NUM());
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
