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
		public Vector<HistoryBean> getHistoryList(){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<HistoryBean> vlist = new Vector<HistoryBean>();
			try {
				con = pool.getConnection();
				sql ="SELECT T.*, A.Account_Balance FROM TRANSFER T " +
			              "JOIN ACCOUNTS A ON T.Transfer_Do_Account = A.Account_Num";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					HistoryBean bean = new HistoryBean();
					bean.setTransfer_Date(rs.getString("Transfer_Date"));
					bean.setTransfer_Take_Account(rs.getInt("Transfer_Take_Account"));
					bean.setTransfer_Memo(rs.getString("Transfer_Memo"));
					bean.setTransfer_Category(rs.getString("Transfer_Category"));
					bean.setTransfer_Balance(rs.getInt("Transfer_Balance"));
					bean.setAccount_Balance(rs.getInt("Account_Balance"));
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
		

			
