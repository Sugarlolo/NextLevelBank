package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import beans.AccountsBean;
import beans.AccountsPublicBean;
import beans.MemberBean;

public class AccountsPublicMgr {

	private DBConnectionMgr pool;
	

	public AccountsPublicMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// ACCOUNT_PUBLIC 테이블에 이미 있는 계좌인지 확인, 없으면 true 반환
	public boolean selectAccountPublic(AccountsPublicBean bean) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		AccountsPublicBean accountsPublicBean = new AccountsPublicBean();
		
		try {
			con = pool.getConnection();
			sql = "SELECT ap.ACCOUNT_NUM, ap.ACCOUNT_PUBLIC_MEMBER_NUM "
					+ "FROM ACCOUNT_PUBLIC ap "
					+ "WHERE ap.ACCOUNT_NUM = ? AND ap.ACCOUNT_PUBLIC_MEMBER_NUM = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,bean.getACCOUNT_NUM());
			pstmt.setInt(2,bean.getACCOUNT_PUBLIC_MEMBER_NUM());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				accountsPublicBean.setACCOUNT_NUM(rs.getInt(1));
				accountsPublicBean.setACCOUNT_PUBLIC_MEMBER_NUM(rs.getInt(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		if(accountsPublicBean.getACCOUNT_NUM()==0) {
			flag=true;
		}
		return flag;	
	}
	
	
	
	// 모임통장 계좌 연결하기 칼럼 : 공동계좌 계좌번호 , 공동계좌 참여하는 계좌번호
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
		
		
		// ������ �������� ���¹�ȣ �ҷ����� 
		public Vector<AccountsPublicBean> getPublicAccountNum(MemberBean mBean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<AccountsPublicBean> vlist = new Vector<AccountsPublicBean>();
			try {
				con = pool.getConnection();
				// �����ϴ� �������¹�ȣ, �����ϴ� ���� ���¹�ȣ
				sql = "SELECT m2.MEMBER_ID ,ap.ACCOUNT_NUM, a2.ACCOUNT_BALANCE, ap.ACCOUNT_PUBLIC_MEMBER_NUM\r\n"
						+ "FROM MEMBER m , MEMBER m2,ACCOUNT_PUBLIC ap, ACCOUNTS a, ACCOUNTS a2\r\n"
						+ "WHERE m.MEMBER_ID = ? AND m.MEMBER_ID = a.MEMBER_ID \r\n"
						+ "AND a.ACCOUNT_NUM = ap.ACCOUNT_PUBLIC_MEMBER_NUM \r\n"
						+ "AND a2.ACCOUNT_NUM = ap.ACCOUNT_NUM\r\n"
						+ "AND m2.MEMBER_ID = a2.MEMBER_ID ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mBean.getMEMBER_ID());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					AccountsPublicBean accountsPublicBean = new AccountsPublicBean();
					AccountsBean accountsBean = new AccountsBean();					
					accountsPublicBean.setMEMBER_ID(rs.getString(1));
					accountsPublicBean.setACCOUNT_NUM(rs.getInt(2));
					accountsPublicBean.setACCOUNT_BALANCE(rs.getInt(3));
					accountsPublicBean.setACCOUNT_PUBLIC_MEMBER_NUM(rs.getInt(4));
					vlist.addElement(accountsPublicBean);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}
		
		
		public static void main(String[] args) {
//			AccountsPublicMgr apMgr = new AccountsPublicMgr();
//			AccountsPublicBean apBean = new AccountsPublicBean();
//			Vector<AccountsPublicBean> vlist = new Vector<AccountsPublicBean>();
//			
//			
//			apBean.setACCOUNT_PUBLIC_MEMBER_NUM(430548459);
//			vlist = apMgr.getPublicAccountNum(apBean);
//
//			for(int i = 0 ; i<vlist.size();i++) {
//				System.out.println(i+":"+vlist.get(i).getACCOUNT_NUM());
//			}
			
			
		}
}
