package database;

import java.lang.reflect.Member;
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
	
//	public boolean Transfer_Transaction(AccountsBean bean, TransferBean tBean, int d_account, int t_account, int transferCash) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = null;
//		String sql2 = null;
//		String sql3 = null;
//		String sql4 = null;
//		boolean flag = false;
//		TransferMgr tMgr = new TransferMgr();
//		int d_balance = 0, t_balance = 0;
//		try {
//			con = pool.getConnection();
//			con.setAutoCommit(false);
//			try {
//				
//				// 1. 보내는 사람의 계좌 정보를 불러온 후 계좌 잔액을 갱신
//				tMgr.Transfer_Refresh(d_account);
//				sql = ("UPDATE ACCOUNTS SET ACCOUNT_LAST_DATE = NOW(),"
//						+ "ACCOUNT_BALANCE = ? WHERE ACCOUNT_NUM = ?" );
//				pstmt = con.prepareStatement(sql);
//				pstmt.setInt(1,	bean.getACCOUNT_BALANCE()-transferCash);
//				pstmt.setInt(2, bean.getACCOUNT_NUM());
//				pstmt.executeUpdate(sql);
//				pstmt.close();
//				
//				// 2. 받는 사람의 계좌 정보를 불러온 후 계좌 잔액을 갱신
//				
//				tMgr.Transfer_Refresh(t_account);
//				sql2 = ("UPDATE ACCOUNTS SET "
//						+"ACCOUNT_LAST_DATE = NOW(), "
//						+ "ACCOUNT_BALANCE = ? "
//						+ "WHERE ACCOUNT_NUM= ?" );
//				pstmt = con.prepareStatement(sql2);
//				pstmt.setInt(1,	bean.getACCOUNT_BALANCE()+transferCash);
//				pstmt.setInt(2, bean.getACCOUNT_NUM());
//				pstmt.executeUpdate(sql2);
//				pstmt.close();
//				
//				// 3. 보내는 사람과 받는 사람의 계좌 잔액을 불러온 후 거래 내역 추가
//				
//				sql3 = ("SELECT  ACCOUNT_BALANCE "
//						+ "FROM ACCOUNTS "
//						+ "WHERE ACCOUNT_NUM = ? OR ACCOUNT_NUM = ?");
//				pstmt = con.prepareStatement(sql3);
//				pstmt.setInt(1, d_account);
//				pstmt.setInt(2, t_account);
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					d_balance = rs.getInt(1);
//					t_balance = rs.getInt(2);
//				}
//				pstmt.close();
//				rs.close();
//				
//				sql4 = ("INSERT INTO TRANSFER VALUES (NULL, ?, ?, ?, NOW(), ?, ?, ?, ?)");
//				pstmt = con.prepareStatement(sql4);
//				pstmt.setInt(1, d_account);
//				pstmt.setInt(2, transferCash);
//				pstmt.setInt(3, t_account);
//				pstmt.setString(4, tBean.transfer_Category);
//				pstmt.setString(5, tBean.transfer_Memo);
//				pstmt.setInt(6, d_balance);
//				pstmt.setInt(7, t_balance);
//				pstmt.close();
//				
//				con.commit();
//				
//			} catch (Exception e) {
//				con.rollback();
//				e.printStackTrace();
//			}
//			
//			int cnt = pstmt.executeUpdate();
//			if (cnt == 1) {
//				flag = true;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt, rs);
//			
//		}
//		return flag;
//	}
	
	public boolean Transfer_Transaction(TransferBean tBean, int d_account, int t_account, int transferCash) {
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
				sql = ("UPDATE ACCOUNTS SET ACCOUNT_LAST_DATE = NOW(), "
						+ "ACCOUNT_BALANCE = ? WHERE ACCOUNT_NUM = ?" );
				pstmt = con.prepareStatement(sql);
				int r1 = tMgr.Account_refresh(d_account).getACCOUNT_BALANCE() - transferCash;
				pstmt.setInt(1,	r1);
				System.out.println("1번 트랜잭션 - 잔고 값 :"+r1);
				pstmt.setInt(2, d_account);
				System.out.println("1번 트랜잭션 - 보내는 사람 계좌 번호 :"+d_account);
				System.out.println("1번 트랜잭션 - 받는 사람 계좌 번호 :"+t_account);
				pstmt.executeUpdate();
				pstmt.close();
				
				// 2. 받는 사람의 계좌 정보를 불러온 후 계좌 잔액을 갱신
				
				tMgr.Account_refresh(t_account);
				sql2 = ("UPDATE ACCOUNTS SET ACCOUNT_LAST_DATE = NOW(),"
						+ "ACCOUNT_BALANCE = ? WHERE ACCOUNT_NUM = ?" );
				pstmt = con.prepareStatement(sql2);
				int r2 = tMgr.Account_refresh(t_account).getACCOUNT_BALANCE()+transferCash;
				pstmt.setInt(1,	r2);
				System.out.println("2번 트랜잭션 - 받는 돈: "+r2);
				pstmt.setInt(2, t_account);
				System.out.println("2번 트랜잭션 - 받는 사람 계좌: "+t_account);
				pstmt.executeUpdate();
				pstmt.close();
				
				// 3. 보내는 사람과 받는 사람의 계좌 잔액을 불러온 후 거래 내역 추가
				
				sql3 = ("SELECT distinct"
						+ "(SELECT ACCOUNT_BALANCE FROM ACCOUNTS WHERE ACCOUNT_NUM = ?)"
						+ ","
						+ "(SELECT ACCOUNT_BALANCE FROM ACCOUNTS WHERE ACCOUNT_NUM = ?) "
						+ "FROM ACCOUNTS");
				pstmt = con.prepareStatement(sql3);
				pstmt.setInt(1, d_account);
				pstmt.setInt(2, t_account);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					d_balance = rs.getInt(1);
					System.out.println(d_balance);
					t_balance = rs.getInt(2);
					System.out.println(t_balance);
				}
				pstmt.close();
				rs.close();
				
				sql4 = "INSERT INTO TRANSFER VALUES (NULL, ?, ?, ?, NOW(), ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql4);
				pstmt.setInt(1, d_account);
				pstmt.setInt(2, transferCash);
				pstmt.setInt(3, t_account);
				pstmt.setString(4, "일반");
				pstmt.setString(5, tBean.getTransfer_Memo());
				pstmt.setInt(6, d_balance);
				pstmt.setInt(7, t_balance);
				pstmt.executeUpdate();
				pstmt.close();
				con.commit();
				flag= true;
			} catch (Exception e) {
				con.rollback();
				flag=false;
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
			
		}
		return flag;
	}
	
//	public AccountsBean Transfer_Refresh(int account) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = null;
//		AccountsBean aBean = null;
//		try {
//			con = pool.getConnection();
//			sql = "SELECT ACCOUNT_LAST_DATE, ACCOUNT_BALANCE "
//					+ "FROM ACCOUNTS "
//					+ "WHERE ACCOUNT_NUM=?"; 
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1,account);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				aBean = new AccountsBean();
//				aBean.setACCOUNT_LAST_DATE(rs.getTimestamp(1));
//				System.out.println("테스트 시간: "+rs.getTimestamp(1));
//				aBean.setACCOUNT_BALANCE(rs.getInt(2));
//				System.out.println("테스트 계좌잔고: "+rs.getInt(2));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt, rs);
//		}
//		return aBean;
//	}
	
	//리프레시 메소드
	public AccountsBean Account_refresh(int account) { // 계좌에 대한 정보를 갱신하는 메소드
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
	
	
	
	public boolean PayPassword_check(String member_ID , String payPassword) { //결제 비밀번호를 체크하는 메소드
		Connection con = null;
		CallableStatement cstmt = null;
		String sql = null;
		int ischecked = 0;
		try {
			con = pool.getConnection();
			// 저장 프로시저 호출
			sql = "{CALL CheckPayPW(?,?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, member_ID);
			System.out.println("입력된 아이디: "+member_ID);
			cstmt.setString(2, payPassword);
			System.out.println("입력된 비밀번호: "+payPassword);
			cstmt.registerOutParameter(3,java.sql.Types.INTEGER);
			cstmt.execute();
			
			ischecked = cstmt.getInt(3);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, cstmt);
		}
		if (ischecked!=0) {
			return true;
		} else
			return false;
	}
	
	//회원 정보의 결제비밀번호 틀린 횟수 카운트를 올리는 메소드.
	
	public boolean countPayPw(MemberBean bean, int count) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE MEMBER SET PAY_PW_COUNT = ? WHERE MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, bean.getMEMBER_ID());
			pstmt.executeUpdate();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			pool.freeConnection(con, pstmt);
			
		}return flag;
	}
	
	// 회원 정보 내의 결제비밀번호 카운트를 불러오는 메소드. 반환은 bean에 함
	public MemberBean iscountedPayPw(MemberBean bean) {
		boolean flag= false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT m.PAY_PW_COUNT FROM MEMBER m WHERE m.MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getMEMBER_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setPAYPW_COUNT(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//회원 상태를 일시 정지로 만드는 메소드.
	public void changeMemberStatus(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			bean = new MemberBean();
			con = pool.getConnection();
			sql = "UPDATE MEMBER SET MEMBER_STATUS = '일시정지' WHERE MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getMEMBER_ID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//이체한도 체크해서 이체 가능하면 true, 이체 불가능하면 false 반환함.
	
	public boolean checkTransferLimits (AccountsBean bean, int amount /*보내려는 금액*/) {
		int currentBalance = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int balanceLimits = getTransferLimits(bean);
		try {
			con = pool.getConnection();
			sql = "SELECT COALESCE(SUM(t.TRANSFER_BALANCE), 0) AS TOTAL_BALANCE "
					+ "FROM TRANSFER t "
					+ "LEFT OUTER JOIN ACCOUNT_LIMITS al ON t.TRANSFER_DO_ACCOUNT = al.ACCOUNT_NUM "
					+ "WHERE t.TRANSFER_DO_ACCOUNT = ? AND DATE(t.TRANSFER_DATE) = CURDATE()";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getACCOUNT_NUM());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				currentBalance = rs.getInt(1);
			}
			System.out.println("1 "+currentBalance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		if (currentBalance+amount > balanceLimits) {
			return false;
		} else
			System.out.println("일일 이체한도: "+balanceLimits);
			System.out.println("오늘 보낸 금액: "+currentBalance);
			return true;
	}
	
	//이체 한도 가져오는 메소드
	private int getTransferLimits(AccountsBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int limits = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT al.DAILY_TRANSFER_LIMITS "
					+ "FROM ACCOUNT_LIMITS al "
					+ "WHERE al.ACCOUNT_NUM = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getACCOUNT_NUM());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				limits = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		System.out.println("가져온 일일 이체 한도는 "+limits);
		return limits;
	}
	
	/*
	 * public static void main(String[] args) { TransferMgr tMgr = new
	 * TransferMgr(); AccountsBean aBean = new AccountsBean(); MemberBean mBean =
	 * new MemberBean(); mBean.setMEMBER_ID("test1");
	 * aBean.setACCOUNT_NUM(185526101);
	 * System.out.println(tMgr.checkTransferLimits(aBean, 1000));
	 * tMgr.iscountedPayPw(mBean); }
	 */
}
