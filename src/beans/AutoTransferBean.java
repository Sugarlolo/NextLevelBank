package beans;

public class AutoTransferBean {
	private int TakeccountNum;
	private int DoccountNum;
	private int transBalance;
	private int transferDate;
	private String MEMBER_NAME;
	
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public int getTransBalance() {
		return transBalance;
	}
	public int getTakeccountNum() {
		return TakeccountNum;
	}
	public void setTakeccountNum(int takeccountNum) {
		TakeccountNum = takeccountNum;
	}
	public int getDoccountNum() {
		return DoccountNum;
	}
	public void setDoccountNum(int doccountNum) {
		DoccountNum = doccountNum;
	}
	public void setTransBalance(int transBalance) {
		this.transBalance = transBalance;
	}
	public int getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(int transferDate) {
		this.transferDate = transferDate;
	}
	
}
