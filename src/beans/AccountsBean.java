package beans;

import java.sql.Timestamp;

public class AccountsBean {
	private int ACCOUNT_NUM;
	private String MEMBER_ID;
	private Timestamp ACCOUNT_REG_DATE;
	private Timestamp ACCOUNT_LAST_DATE;
	private String ACCOUNT_CATEGORY;
	private int ACCOUNT_BALANCE;
	private String ACCOUNT_PURPOSE;
	
	
	public int getACCOUNT_NUM() {
		return ACCOUNT_NUM;
	}
	public void setACCOUNT_NUM(int aCCOUNT_NUM) {
		ACCOUNT_NUM = aCCOUNT_NUM;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public Timestamp getACCOUNT_REG_DATE() {
		return ACCOUNT_REG_DATE;
	}
	public void setACCOUNT_REG_DATE(Timestamp aCCOUNT_REG_DATE) {
		ACCOUNT_REG_DATE = aCCOUNT_REG_DATE;
	}
	public Timestamp getACCOUNT_LAST_DATE() {
		return ACCOUNT_LAST_DATE;
	}
	public void setACCOUNT_LAST_DATE(Timestamp aCCOUNT_LAST_DATE) {
		ACCOUNT_LAST_DATE = aCCOUNT_LAST_DATE;
	}
	public String getACCOUNT_CATEGORY() {
		return ACCOUNT_CATEGORY;
	}
	public void setACCOUNT_CATEGORY(String aCCOUNT_CATEGORY) {
		ACCOUNT_CATEGORY = aCCOUNT_CATEGORY;
	}
	public int getACCOUNT_BALANCE() {
		return ACCOUNT_BALANCE;
	}
	public void setACCOUNT_BALANCE(int aCCOUNT_BALANCE) {
		ACCOUNT_BALANCE = aCCOUNT_BALANCE;
	}
	public String getACCOUNT_PURPOSE() {
		return ACCOUNT_PURPOSE;
	}
	public void setACCOUNT_PURPOSE(String aCCOUNT_PURPOSE) {
		ACCOUNT_PURPOSE = aCCOUNT_PURPOSE;
	}
}
