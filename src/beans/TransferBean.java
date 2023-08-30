package beans;

import java.sql.Timestamp;

public class TransferBean {
	public int transfer_No;
	public int transfer_Do_Account;
	public int transfer_Balance;
	public int transfer_Take_Account;
	public Timestamp transfer_Date;
	public String transfer_Category;
	public String transfer_Memo;
	public int getTransfer_No() {
		return transfer_No;
	}
	public void setTransfer_No(int transfer_No) {
		this.transfer_No = transfer_No;
	}
	public int getTransfer_Do_Account() {
		return transfer_Do_Account;
	}
	public void setTransfer_Do_Account(int transfer_Do_Account) {
		this.transfer_Do_Account = transfer_Do_Account;
	}
	public int getTransfer_Balance() {
		return transfer_Balance;
	}
	public void setTransfer_Balance(int transfer_Balance) {
		this.transfer_Balance = transfer_Balance;
	}
	public int getTransfer_Take_Account() {
		return transfer_Take_Account;
	}
	public void setTransfer_Take_Account(int transfer_Take_Account) {
		this.transfer_Take_Account = transfer_Take_Account;
	}
	public Timestamp getTransfer_Date() {
		return transfer_Date;
	}
	public void setTransfer_Date(Timestamp transfer_Date) {
		this.transfer_Date = transfer_Date;
	}
	public String getTransfer_Category() {
		return transfer_Category;
	}
	public void setTransfer_Category(String transfer_Category) {
		this.transfer_Category = transfer_Category;
	}
	public String getTransfer_Memo() {
		return transfer_Memo;
	}
	public void setTransfer_Memo(String transfer_Memo) {
		this.transfer_Memo = transfer_Memo;
	}

}
