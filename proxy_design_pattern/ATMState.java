package proxy_design_pattern;

public interface ATMState {
	
	void insertCard();
	void ejectCard();
	void insertPin(int pinEntered);
	void requestCash(int cashToWithdraw);
	
}