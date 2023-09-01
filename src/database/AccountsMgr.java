package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Vector;

import beans.AccountsBean;
import beans.MemberBean;
import nlb_core.PublicAccountFrame;

public class AccountsMgr {
	private DBConnectionMgr pool;
	Random random = new Random();

	LocalDateTime Local_now = LocalDateTime.now(); 
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String now = Local_now.format(formatter);
	
	public AccountsMgr() {
		pool = DBConnectionMgr.getInstance();
	}


	// 占쏙옙占퐄d, 占쏙옙占쏙옙타占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙확占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쌀뤄옙占쏙옙占쏙옙
		public Vector<AccountsBean> getAccount_num(AccountsBean bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<AccountsBean> vlist = new Vector<AccountsBean>();
			try {
				con = pool.getConnection();
				sql = "SELECT a.* FROM MEMBER m, ACCOUNTS a WHERE a.MEMBER_ID = m.MEMBER_ID AND m.MEMBER_ID=? AND a.ACCOUNT_CATEGORY = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getMEMBER_ID()); 
				pstmt.setString(2, bean.getACCOUNT_CATEGORY()); 
				rs = pstmt.executeQuery();
				while(rs.next()) {
					AccountsBean bean2 = new AccountsBean();
					bean2.setACCOUNT_NUM(rs.getInt("account_num"));
					bean2.setMEMBER_ID(rs.getString("member_id"));
					bean2.setACCOUNT_REG_DATE(rs.getTimestamp("account_reg_date"));
					bean2.setACCOUNT_LAST_DATE(rs.getTimestamp("account_last_date"));
					bean2.setACCOUNT_CATEGORY(rs.getString("account_category"));
					bean2.setACCOUNT_BALANCE(rs.getInt("account_balance"));
					bean2.setACCOUNT_PURPOSE(rs.getString("ACCOUNT_PURPOSE"));
					vlist.addElement(bean2);
					//System.out.println(bean.getAccount_num());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}
		
		

		// 占쏙옙占� 占싱몌옙占쏙옙, 占쏙옙화占쏙옙호占쏙옙 占쏙옙占승뱄옙호 占쌀뤄옙占쏙옙占쏙옙
			public Vector<AccountsBean> getAccount_num2(MemberBean bean1) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				Vector<AccountsBean> vlist = new Vector<AccountsBean>();
				try {
					con = pool.getConnection();
					sql = "SELECT a.* "
							+ "FROM accounts a, member "
							+ "WHERE a.MEMBER_ID = m.MEMBER_ID AND m.MEMBER_NAME = ?  AND m.TEL_NUMBER = ? ";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bean1.getMEMBER_Name());
					pstmt.setString(2, bean1.getTEL_Num());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						AccountsBean bean2 = new AccountsBean(); 
						bean2.setACCOUNT_NUM(rs.getInt("account_num"));
						bean2.setMEMBER_ID(rs.getString("member_id"));
						bean2.setACCOUNT_REG_DATE(rs.getTimestamp("account_reg_date"));
						bean2.setACCOUNT_LAST_DATE(rs.getTimestamp("account_last_date"));
						bean2.setACCOUNT_CATEGORY(rs.getString("account_category"));
						bean2.setACCOUNT_BALANCE(rs.getInt("account_balance"));
						bean2.setACCOUNT_PURPOSE(rs.getString("ACCOUNT_PURPOSE"));
						vlist.addElement(bean2);
						//System.out.println(bean.getAccount_num());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return vlist;
			}


		// 계좌번호로 계좌주인의모든정보 All info account by account number
		public AccountsBean getAccount_balance(AccountsBean bean)  {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;             
			try {
				con = pool.getConnection();
				sql = "select a.* from accounts a where a.account_num = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bean.getACCOUNT_NUM());
				rs = pstmt.executeQuery();
				if(rs.next()) { 
					bean.setACCOUNT_NUM(rs.getInt("account_num"));
					bean.setMEMBER_ID(rs.getString("member_id"));
					bean.setACCOUNT_REG_DATE(rs.getTimestamp("account_reg_date"));
					bean.setACCOUNT_LAST_DATE(rs.getTimestamp("account_last_date"));
					bean.setACCOUNT_CATEGORY(rs.getString("account_category"));
					bean.setACCOUNT_BALANCE(rs.getInt("account_balance"));
					bean.setACCOUNT_PURPOSE(rs.getString("ACCOUNT_PURPOSE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return bean;
		} 
		
		//계좌번호 모두 불러오기 all account numbers
		public Vector<AccountsBean> getAccount_num3() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<AccountsBean> vlist = new Vector<AccountsBean>();
			try {
				con = pool.getConnection();
				sql = "select a.* from ACCOUNTS a";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					AccountsBean bean2 = new AccountsBean(); 
					bean2.setACCOUNT_NUM(rs.getInt("account_num"));
					bean2.setMEMBER_ID(rs.getString("member_id"));
					bean2.setACCOUNT_REG_DATE(rs.getTimestamp("account_reg_date"));
					bean2.setACCOUNT_LAST_DATE(rs.getTimestamp("account_last_date"));
					bean2.setACCOUNT_CATEGORY(rs.getString("account_category"));
					bean2.setACCOUNT_BALANCE(rs.getInt("account_balance"));
					bean2.setACCOUNT_PURPOSE(rs.getString("ACCOUNT_PURPOSE"));
					vlist.addElement(bean2);
					//System.out.println(bean.getAccount_num());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		
	// insert Account row
		public boolean InsertAccount(AccountsBean bean) {
			
			int account; // random account num
			String str="";
			str+= random.nextInt(1,10);
			for(int i =0;i<8;i++) {
				int code = random.nextInt(10);
				str+=String.valueOf(code);
			}
			account = Integer.valueOf(str);
			
			AccountsMgr mgr = new AccountsMgr(); //while(Existing account number) {random account} 
			Vector<AccountsBean> abcAccountsBeans = mgr.getAccount_num3();
			for(int i =0;i<abcAccountsBeans.size();i++) {
				while(account == abcAccountsBeans.get(i).getACCOUNT_NUM()) {
					str="";
					str+= random.nextInt(1,10);
					for(int j =0;j<8;j++) {
						int code = random.nextInt(10);
						str+=String.valueOf(code);
					}
					account = Integer.valueOf(str);
				}
			}
			
			
			
			boolean flag = false;
		 	Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "INSERT INTO ACCOUNTS "
						+ "VALUES( ? , ? , ? , ? , ? , 0 , ? )";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,account);
				pstmt.setString(2,bean.getMEMBER_ID());
				pstmt.setString(3,now);
				pstmt.setString(4,now);
				pstmt.setString(5,bean.getACCOUNT_CATEGORY());
				pstmt.setString(6,bean.getACCOUNT_PURPOSE());
				
				int cnt = pstmt.executeUpdate();
				if(cnt==1) flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			} 
			return flag;	
			 
			
		}
	
		
		public static void main(String[] args) {
			AccountsMgr mgr = new AccountsMgr();
			AccountsBean bean1 = new AccountsBean();
			//System.out.println(mgr.getAccount_num("test1","占싹뱄옙"));
			//System.out.println(mgr.InsertAccount("test4","占쏙옙占쏙옙占쏙옙占쏙옙","占쌨울옙 占쏙옙 占싣몌옙占쏙옙占쏙옙트"));
			//System.out.println(mgr.InsertAccountPublic("test1","test2"));
//			bean1.setMEMBER_ID("test1");
//			bean1.setACCOUNT_CATEGORY("�씪諛�");
//			System.out.println(mgr.getAccount_num(bean1).get(0).getACCOUNT_NUM());
//			System.out.println(mgr.getAccount_num(bean1).get(1).getACCOUNT_NUM());
//			System.out.println(mgr.getAccount_num(bean1).get(2).getACCOUNT_NUM());
//			
			//bean1.setACCOUNT_NUM(123456789);
//			System.out.println(mgr.getAccount_num3(bean1));
//			bean1.setACCOUNT_CATEGORY("공동계좌");
//			bean1.setACCOUNT_PURPOSE("급여");
//			bean1.setMEMBER_ID("test1");
//			
//			mgr.InsertAccount(bean1);	
			
//			Vector<AccountsBean> abcAccountsBeans = mgr.getAccount_num3();
//			for(int i =0;i<abcAccountsBeans.size();i++) {
//				System.out.println(abcAccountsBeans.get(i).getACCOUNT_NUM());
//			}
			
//			InsertAccount
//			bean1.setACCOUNT_CATEGORY("일반");
//			bean1.setACCOUNT_PURPOSE("급급여");
//			bean1.setMEMBER_ID("test1");
//			System.out.println(mgr.InsertAccount(bean1));
		}
		
}
