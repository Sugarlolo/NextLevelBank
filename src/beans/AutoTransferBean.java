package beans;

public class AutoTransferBean {
	private int transferAccountNum;
	private int withdrawAccountNum;
	private int transBalance;
	private int transferDate;
	
	public int getTransferAccountNum() {
		return transferAccountNum;
	}
	public void setTransferAccountNum(int transferAccountNum) {
		this.transferAccountNum = transferAccountNum;
	}
	public int getWithdrawAccountNum() {
		return withdrawAccountNum;
	}
	public void setWithdrawAccountNum(int withdrawAccountNum) {
		this.withdrawAccountNum = withdrawAccountNum;
	}
	public int getTransBalance() {
		return transBalance;
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
