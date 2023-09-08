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
				sql = "SELECT t.*, " +
			        	  "   CASE WHEN t.TRANSFER_DO_ACCOUNT = ? THEN t.TRANSFER_DO_BALANCE " +
			              "   WHEN t.TRANSFER_TAKE_ACCOUNT = ? THEN t.TRANSFER_TAKE_BALANCE " +
			              "    ELSE 0 " +
			              "    END AS MY_ACCOUNT_BALANCE " +
			              "FROM TRANSFER t " +
			              "WHERE (t.TRANSFER_DO_ACCOUNT = ? OR t.TRANSFER_TAKE_ACCOUNT = ?) " +
			              "AND t.TRANSFER_DATE > DATE_SUB(NOW(), INTERVAL "+days+" DAY)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, account);
				pstmt.setInt(2, account);
				pstmt.setInt(3, account);
				pstmt.setInt(4, account);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					HistoryBean bean = new HistoryBean();
					bean.setTransfer_No(rs.getInt(1));
					bean.setTransfer_Date(rs.getString(5));
					bean.setTransfer_Take_Account(rs.getInt(4));
					bean.setTransfer_Memo(rs.getString(7));
					bean.setTransfer_Category(rs.getString(6));
					bean.setTransfer_Balance(rs.getInt(3));
					bean.setTransfer_Do_Balance(rs.getInt(10));
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