package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import beans.MemberBean;
import beans.TransferBean;
import beans.AccountsBean;
import database.DBConnectionMgr;

public class TransferMgr {

	private DBConnectionMgr pool;
	public AccountsBean aBean;
	public TransferBean tBean;
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
		AccountsBean ab = new AccountsBean();
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
	
	public boolean Transfer_Transaction(int transferCash, int takeAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sql2 = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			
			try {
				sql = ("UPDATE ACCOUNTS SET "
						+ "ACCOUNT_LAST_DATE, "
						+ "ACCOUNT_BALANCE VALUES "
						+ "(NOW(),?) WHERE MEMBER_ID = ?");
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,	aBean.getACCOUNT_BALANCE()-transferCash);
				pstmt.setInt(2, aBean.getACCOUNT_NUM());
				
				sql2 = ("INSERT INTO TRANSFER (null,?,?,?,now(),?,?,?,?");
				pstmt.setInt(1, aBean.getACCOUNT_NUM());
				pstmt.setInt(2, transferCash);
				pstmt.setInt(3, takeAccount);
				pstmt.setString(4, tBean.getTransfer_Category());
				pstmt.setString(5, tBean.getTransfer_Memo());
				pstmt.setInt(6, aBean.getACCOUNT_BALANCE() - transferCash);
//				pstmt.setInt(7, )
//				pstmt.executeUpdate(sql);
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
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public void Transfer_Refresh(int account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT ACCOUNT_LAST_DATE, ACCOUNT_BALANCE "
					+ "FROM ACCOUNTS "
					+ "WHERE ACCOUNT_NUM=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,account);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				aBean.setACCOUNT_LAST_DATE(rs.getTimestamp(1));
				aBean.setACCOUNT_BALANCE(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return;
	}

	public static void main(String[] args) {
		TransferMgr tMgr = new TransferMgr();
		AccountsBean ab = new AccountsBean();
		tMgr.Transfer_Refresh(185526101);
		System.out.println("최근 거래일자: "+ab.getACCOUNT_LAST_DATE());
		System.out.println("계좌 잔액: "+ab.getACCOUNT_BALANCE());
	}
}
