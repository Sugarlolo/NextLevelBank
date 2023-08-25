package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void Transaction_Do(int balance) {

	}

	public void Transaction_Take() {

	}

	
	//이체 화면에서 이체 대상의 계좌번호가 일치하는지 검사하는 메소드
	
	public boolean Transaction_CheckAccount(int account) {
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
			System.out.println("계좌 여부 :"+result);
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
	
	public void Transfer_bean() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return;
	}
	
//	public static void main(String[] args) {
//		int account = 185526101;
//		TransferMgr tMgr = new TransferMgr();
//		System.out.println(tMgr.Transaction_CheckAccount(account));
//	}
}
