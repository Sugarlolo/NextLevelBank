package beans;

import java.sql.Timestamp;

public class MemberBean {
	private String MEMBER_ID;
	private String MEMBER_PW;
	private String MEMBER_Name;
	private String TEL_Num;
	private String ADDR;
	private String SOCIAL_NUMBER;
	private String hashedPassword;
	private String Salt;
	private Timestamp REG_DATE;
	private String MEMBER_STATUS;
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_Name() {
		return MEMBER_Name;
	}
	public void setMEMBER_Name(String mEMBER_Name) {
		MEMBER_Name = mEMBER_Name;
	}
	public String getTEL_Num() {
		return TEL_Num;
	}
	public void setTEL_Num(String tEL_Num) {
		TEL_Num = tEL_Num;
	}
	public String getADDR() {
		return ADDR;
	}
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	public String getSOCIAL_NUMBER() {
		return SOCIAL_NUMBER;
	}
	public void setSOCIAL_NUMBER(String sOCIAL_NUMBER) {
		SOCIAL_NUMBER = sOCIAL_NUMBER;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	public Timestamp getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(Timestamp rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getMEMBER_STATUS() {
		return MEMBER_STATUS;
	}
	public void setMEMBER_STATUS(String mEMBER_STATUS) {
		MEMBER_STATUS = mEMBER_STATUS;
	}
	
}
