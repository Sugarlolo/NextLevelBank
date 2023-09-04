package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import beans.HistoryBean;
import beans.MemberBean;

public class HistoryMgr {

		private DBConnectionMgr pool;
		
		public HistoryMgr() {
			pool = DBConnectionMgr.getInstance();
		}
		
		//내역 가져오기
		public Vector<HistoryBean> getHistoryList(int account, int days){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<HistoryBean> vlist = new Vector<HistoryBean>();
			try {
				con = pool.getConnection();
				sql ="SELECT t.*, a.ACCOUNT_BALANCE "
						+ "FROM TRANSFER t LEFT OUTER JOIN ACCOUNTS a "
						+ "ON t.TRANSFER_DO_ACCOUNT = a.ACCOUNT_NUM "
						+ "WHERE t.TRANSFER_DO_ACCOUNT ="
						+ "(SELECT ACCOUNT_NUM FROM ACCOUNTS WHERE ACCOUNT_NUM=?) " 
						+ "AND t.TRANSFER_DATE > DATE_SUB(NOW(),INTERVAL "+days+" DAY)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, account);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					HistoryBean bean = new HistoryBean();
					bean.setTransfer_Date(rs.getString("Transfer_Date"));
					bean.setTransfer_Take_Account(rs.getInt("Transfer_Take_Account"));
					bean.setTransfer_Memo(rs.getString("Transfer_Memo"));
					bean.setTransfer_Category(rs.getString("Transfer_Category"));
					bean.setTransfer_Balance(rs.getInt("Transfer_Balance"));
					bean.setTransfer_Do_Balance(rs.getInt("Transfer_Do_Balance"));
					vlist.addElement(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		return vlist;
		}
		public HistoryBean getHistoryList1(int account){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			HistoryBean bean = new HistoryBean();
			try {
				con = pool.getConnection();
				sql = "select Account_Balance from ACCOUNTS where ACCOUNT_NUM= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, account);;
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bean.setAccount_Balance(rs.getInt("Account_Balance"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		return bean;
		}

}