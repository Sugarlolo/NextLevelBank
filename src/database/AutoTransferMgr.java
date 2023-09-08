package database;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import beans.AccountsBean;
import beans.AccountsPublicBean;
import beans.AutoTransferBean;
import beans.MemberBean;

public class AutoTransferMgr {

	private DBConnectionMgr pool;
	

	public AutoTransferMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public boolean inserAutoTransfer(AutoTransferBean aTBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "INSERT INTO AUTO_TRANSFER VALUE(?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aTBean.getTakeccountNum());
			pstmt.setInt(2, aTBean.getDoccountNum());
			pstmt.setInt(3, aTBean.getTransBalance());
			pstmt.setInt(4, aTBean.getTransferDate());
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	
	public Vector<AutoTransferBean> selectAutoTransfer(AutoTransferBean atBean){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;        
		Vector<AutoTransferBean> vlist = new Vector<AutoTransferBean>(); 
		try {
			con = pool.getConnection();
			sql = "SELECT att.TakeccountNum , m.MEMBER_NAME, att.DoccountNum, att.transBalance, att.transferDate "
					+ "FROM AUTO_TRANSFER att , MEMBER m, ACCOUNTS a ,MEMBER m2,ACCOUNTS a2 "
					+ "WHERE att.TakeccountNum = a.ACCOUNT_NUM AND m.MEMBER_ID = a.MEMBER_ID "
					+ "AND a2.ACCOUNT_NUM = att.DoccountNum AND m2.MEMBER_ID = a2.MEMBER_ID "
					+ "AND  a2.ACCOUNT_NUM=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, atBean.getDoccountNum());
			rs = pstmt.executeQuery();
			while(rs.next()) { 
				AutoTransferBean aTBean = new AutoTransferBean();
				aTBean.setTakeccountNum(rs.getInt(1));
				aTBean.setMEMBER_NAME(rs.getString(2));
				aTBean.setDoccountNum(rs.getInt(3));
				aTBean.setTransBalance(rs.getInt(4));
				aTBean.setTransferDate(rs.getInt(5));
				vlist.add(aTBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	public boolean deleteAutoTransfer(AutoTransferBean aTBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "DELETE FROM AUTO_TRANSFER att WHERE att.TakeccountNum= ? AND att.DoccountNum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aTBean.getTakeccountNum());
			pstmt.setInt(2, aTBean.getDoccountNum());
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
		AutoTransferMgr aTMgr = new AutoTransferMgr();
		AutoTransferBean aTBean = new AutoTransferBean();
		 Vector<AutoTransferBean> vlist = new Vector<AutoTransferBean>();
		 MemberBean mBean = new MemberBean();
		 
		 aTBean.setDoccountNum(430548459);
		 vlist = aTMgr.selectAutoTransfer(aTBean);
		 
		 for(int i = 0;i<vlist.size();i++) {
			 System.out.println(vlist.get(i).getTakeccountNum());
			 System.out.println(vlist.get(i).getMEMBER_NAME());
			 System.out.println(vlist.get(i).getDoccountNum());
			 System.out.println(vlist.get(i).getTransBalance());
			 System.out.println(vlist.get(i).getTransferDate());
		 }
	}
}
