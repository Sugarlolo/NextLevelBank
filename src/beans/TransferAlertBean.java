package beans;

import java.sql.Timestamp;

public class TransferAlertBean {
	public int ALERT_NO;
	public Timestamp ALERT_DATE;
	public String ALERT_CONTENT;
	public String ALERT_MEMBER_ID;
	public int TRANSFER_NO;
	
	public int getALERT_NO() {
		return ALERT_NO;
	}
	public void setALERT_NO(int aLERT_NO) {
		ALERT_NO = aLERT_NO;
	}
	public Timestamp getALERT_DATE() {
		return ALERT_DATE;
	}
	public void setALERT_DATE(Timestamp aLERT_DATE) {
		ALERT_DATE = aLERT_DATE;
	}
	public String getALERT_CONTENT() {
		return ALERT_CONTENT;
	}
	public void setALERT_CONTENT(String aLERT_CONTENT) {
		ALERT_CONTENT = aLERT_CONTENT;
	}
	public String getALERT_MEMBER_ID() {
		return ALERT_MEMBER_ID;
	}
	public void setALERT_MEMBER_ID(String aLERT_MEMBER_ID) {
		ALERT_MEMBER_ID = aLERT_MEMBER_ID;
	}
	public int getTRANSFER_NO() {
		return TRANSFER_NO;
	}
	public void setTRANSFER_NO(int tRANSFER_NO) {
		TRANSFER_NO = tRANSFER_NO;
	}
}
