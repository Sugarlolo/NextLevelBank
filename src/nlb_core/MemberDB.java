package nlb_core;

import beans.MemberBean;
import database.DBConnectionMgr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class MemberDB {
	
	DBConnectionMgr pool;
	public MemberDB() {
		pool = DBConnectionMgr.getInstance();
		
	}
	public boolean logincheck(String id, String pw) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	public boolean joinCheck(String id, String pw, String name, String pn, String ad, String social, String pay) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PW, MEMBER_NAME, TEL_NUMBER, " + 
			"ADDRESS, SOCIAL_NUMBER, PAY_PW, REG_DATETIME, MEMBER_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, NOW(), 'È°¼º')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, pn);
			pstmt.setString(5, ad);
			pstmt.setString(6, social);
			pstmt.setString(7, pay);
			int rowslnserted = pstmt.executeUpdate();

			if(rowslnserted>0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean idduplicationCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		MemberBean bean = new MemberBean();
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	public MemberBean getFindId(String name, String pn) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ? AND TEL_NUMBER = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pn);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setMEMBER_ID(rs.getString("MEMBER_ID"));
				bean.setMEMBER_Name(rs.getString("MEMBER_NAME"));
				bean.setTEL_Num(rs.getString("TEL_NUMBER"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	public MemberBean getFindPw(String name, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ? AND MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setMEMBER_PW(rs.getString("MEMBER_PW"));
				bean.setMEMBER_Name(rs.getString("MEMBER_NAME"));
				bean.setMEMBER_ID(rs.getString("MEMBER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
}
