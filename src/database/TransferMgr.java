package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import beans.MemberBean;
import beans.TransferBean;
import beans.AccountsBean;
import database.DBConnectionMgr;

public class TransferMgr {

	private DBConnectionMgr pool;
//	TransferBean tb = 
	public TransferMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	//이체 화면에서 이체 대상의 계좌번호가 일치하는지 검사하는 메소드
	public boolean Transfer_CheckAccount(int account) {
		//불러와야 할 정보 - 거래 대상의 계좌번호
		Connection con = null;
		CallableStatement cstmt = null;
		String sql = null;
		int flag = 0;
		try {
			con = pool.getConnection();
			// 저장 프로시저 호출
			sql = "{call CheckAccount(?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1,account);
			System.out.println("setInt :"+account);
			cstmt.registerOutParameter(2,java.sql.Types.INTEGER);
			cstmt.execute();
			
			int result = cstmt.getInt(2);
			System.out.println("result :" + result);
			flag = result;
//			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, cstmt);
		}
		if (flag!=0) {
			return true;
		} else
			return false;
	}

	// 보내려는 금액과 현재 내 계좌내 잔액을 비교해서 true, false를 반환하는 메소드 
	public boolean Transfer_CheckBalance(int account,int amount) {
		Connection con = null;
		CallableStatement cstmt = null;
		String sql = null;
//		int account = ab.getACCOUNT_NUM();
//		int balance = ab.getACCOUNT_BALANCE();
		int flag = 0;
		
		try {
			con = pool.getConnection();
			sql = "{call CheckAccountBalance(?,?,?)}";
			cstmt = con.prepareCall(sql);
//			cstmt.setInt(1,ab.getACCOUNT_NUM());
			cstmt.setInt(1,account);
			cstmt.setInt(2, amount);
			cstmt.registerOutParameter(3,java.sql.Types.INTEGER);
			cstmt.execute();
			
			int result = cstmt.getInt(3);
			flag = result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, cstmt);
		}
		if (flag!=0) {
			return true;
		} else
			return false;
	}
	
	public boolean Transfer_Transaction(AccountsBean bean, TransferBean tBean, int d_account, int t_account, int transferCash) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		boolean flag = false;
		TransferMgr tMgr = new TransferMgr();
		int d_balance = 0, t_balance = 0;
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			try {
				
				// 1. 보내는 사람의 계좌 정보를 불러온 후 계좌 잔액을 갱신
				tMgr.Transfer_Refresh(d_account);
				sql = ("UPDATE ACCOUNTS SET "
						+ "ACCOUNT_LAST_DATE, "
						+ "ACCOUNT_BALANCE VALUES "
						+ "(NOW(),?) WHERE MEMBER_ID = ?");
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,	bean.getACCOUNT_BALANCE()-transferCash);
				pstmt.setInt(2, bean.getACCOUNT_NUM());
				pstmt.executeUpdate(sql);
				pstmt.close();
				
				// 2. 받는 사람의 계좌 정보를 불러온 후 계좌 잔액을 갱신
				
				tMgr.Transfer_Refresh(t_account);
				sql2 = ("UPDATE ACCOUNTS SET "
						+ "ACCOUNT_LAST_DATE, "
						+ "ACCOUNT_BALANCE VALUES "
						+ "(NOW(),?) WHERE MEMBER_ID = ?");
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1,	bean.getACCOUNT_BALANCE()+transferCash);
				pstmt.setInt(2, bean.getACCOUNT_NUM());
				pstmt.executeUpdate(sql2);
				pstmt.close();
				
				// 3. 보내는 사람과 받는 사람의 계좌 잔액을 불러온 후 거래 내역 추가
				
				sql3 = ("SELECT  ACCOUNT_BALANCE "
						+ "FROM ACCOUNTS "
						+ "WHERE ACCOUNT_NUM = ? OR ACCOUNT_NUM = ?");
				pstmt = con.prepareStatement(sql3);
				pstmt.setInt(1, d_account);
				pstmt.setInt(2, t_account);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					d_balance = rs.getInt(1);
					t_balance = rs.getInt(2);
				}
				pstmt.close();
				rs.close();
				
				sql4 = ("INSERT INTO TRANSFER VALUES (NULL, ?, ?, ?, NOW(), ?, ?, ?, ?)");
				pstmt = con.prepareStatement(sql4);
				pstmt.setInt(1, d_account);
				pstmt.setInt(2, transferCash);
				pstmt.setInt(3, t_account);
				pstmt.setString(4, tBean.transfer_Category);
				pstmt.setString(5, tBean.transfer_Memo);
				pstmt.setInt(6, d_balance);
				pstmt.setInt(7, t_balance);
				pstmt.close();
				
				con.commit();
				
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
			
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
			
		}
		return flag;
	}
	
	public AccountsBean Transfer_Refresh(int account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		AccountsBean aBean = null;
		try {
			con = pool.getConnection();
			sql = "SELECT ACCOUNT_LAST_DATE, ACCOUNT_BALANCE "
					+ "FROM ACCOUNTS "
					+ "WHERE ACCOUNT_NUM=?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,account);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				aBean = new AccountsBean();
				aBean.setACCOUNT_LAST_DATE(rs.getTimestamp(1));
				System.out.println("테스트 시간: "+rs.getTimestamp(1));
				aBean.setACCOUNT_BALANCE(rs.getInt(2));
				System.out.println("테스트 계좌잔고: "+rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return aBean;
	}
	
	//리프레시 메소드
	public AccountsBean Account_refresh(int account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		AccountsBean bean = null;
		try {
			
			con = pool.getConnection();
			sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_NUM =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, account);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new AccountsBean();
				bean.setACCOUNT_NUM(rs.getInt(1));
				bean.setMEMBER_ID(rs.getString(2));
				bean.setACCOUNT_REG_DATE(rs.getTimestamp(3));
				bean.setACCOUNT_LAST_DATE(rs.getTimestamp(4));
				bean.setACCOUNT_CATEGORY(rs.getString(5));
				bean.setACCOUNT_BALANCE(rs.getInt(6));
				bean.setACCOUNT_PURPOSE(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	public static void main(String[] args) {
		TransferMgr tMgr = new TransferMgr();
		AccountsBean ab = tMgr.Transfer_Refresh(185526101);
		System.out.println("최근 거래일자: "+ab.getACCOUNT_LAST_DATE());
		System.out.println("계좌 잔액: "+ab.getACCOUNT_BALANCE());
	}
}
