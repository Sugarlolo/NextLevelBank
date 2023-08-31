package nlb_core; 

import nlb_core.AccountPlusFrame;
import nlb_core.MainFrame;
import nlb_core.PublicAccountFrame;

public class FrameManager {
	private static volatile FrameManager instance = null;
	
	private MainFrame mainFrame;
	private AccountPlusFrame accountPlusFrame;
	private PublicAccountFrame publicAccountFrame;
	
	private FrameManager() {
		
	}
		
	public static FrameManager getInstance() {
		if (instance == null) {
			synchronized (FrameManager.class) {
				if (instance == null) {
					instance = new FrameManager();
				}
			}
		}
		return instance;
	}
	
	public void CustomSetVisible(String name) {
		switch (name) {
			case "main" -> {
				mainFrame.getFrame().setVisible(true);
				accountPlusFrame.getFrame().setVisible(false);
				mainFrame.deleteAccountList();
				mainFrame.showAccountList();
			}
			case "account" -> {
				mainFrame.getFrame().setVisible(false);
				accountPlusFrame.getFrame().setVisible(true);
				
			}
			case "publicAccountFrame" -> {
				mainFrame.getFrame().setVisible(false);
				publicAccountFrame.getFrame().setVisible(true);
				
			}
		
		}
	}

	
	public PublicAccountFrame getPublicAccountFrame() {
		return publicAccountFrame;
	}

	public void setPublicAccountFrame(PublicAccountFrame publicAccountFrame) {
		this.publicAccountFrame = publicAccountFrame;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public AccountPlusFrame getAccountPlusFrame() {
		return accountPlusFrame;
	}

	public void setAccountPlusFrame(AccountPlusFrame accountPlusFrame) {
		this.accountPlusFrame = accountPlusFrame;
	}
}
