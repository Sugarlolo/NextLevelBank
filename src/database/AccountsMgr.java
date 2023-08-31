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
					bean.setACCOUNT_NUM(rs.getInt("account_num"));
					bean.setMEMBER_ID(rs.getString("member_id"));
					bean.setACCOUNT_REG_DATE(rs.getTimestamp("account_reg_date"));
					bean.setACCOUNT_LAST_DATE(rs.getTimestamp("account_last_date"));
					bean.setACCOUNT_CATEGORY(rs.getString("account_category"));
					bean.setACCOUNT_BALANCE(rs.getInt("account_balance"));
					bean.setACCOUNT_PURPOSE(rs.getString("ACCOUNT_PURPOSE"));
					vlist.addElement(bean);
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
			public Vector<AccountsBean> getAccount_num(MemberBean bean1) {
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


		// 占쏙옙占승뱄옙호占쏙옙 占쌤액불뤄옙占쏙옙占쏙옙
		public AccountsBean getAccount_balance(AccountsBean bean)  {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;             
			try {
				con = pool.getConnection();
				sql = "select a.* from nlb_db.accounts a where a.account_num = ? ";
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
		
	// 占쏙옙占쏙옙 占쏙옙占쏙옙占싹깍옙
		public boolean InsertAccount(AccountsBean bean) {
			
			int account; //占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙호 占쏙옙占쏙옙
			String str="";
			str+= random.nextInt(1,10);
			for(int i =0;i<8;i++) {
				int code = random.nextInt(10);
				str+=String.valueOf(code);
			}
			account = Integer.valueOf(str);
			
			boolean flag = false;
		 	Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "INSERT INTO accounts \r\n"
						+ "VALUES( ? , ? , ? , ? , ? , 0 , ? );";
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
	
		
//		public static void main(String[] args) {
//			AccountsMgr mgr = new AccountsMgr(); 
//			//System.out.println(mgr.getAccount_num("test1","占싹뱄옙"));
//			//System.out.println(mgr.InsertAccount("test4","占쏙옙占쏙옙占쏙옙占쏙옙","占쌨울옙 占쏙옙 占싣몌옙占쏙옙占쏙옙트"));
//			//System.out.println(mgr.InsertAccountPublic("test1","test2"));
//		}
		
}
