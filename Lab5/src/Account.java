
public class Account {
	
	private int _accountNum;
	private int _pinNum;
	private double _balance;
	
	public Account(int accountNum, int pinNum, int balance)
	{
		this._accountNum = accountNum;
		this._pinNum = pinNum;
		this._balance = balance;
	}
	
	public boolean validate(Account other) {
		if (other.getAccount() == this.getAccount() && (other.getPIN() == this.getPIN())) return true;	
		return false;
	}
	
	private int getPIN() {
		return this._pinNum;
	}
	
	private int getAccount() {
		return this._accountNum;
	}
	
	public void setBalance(double balance) {
		this._balance = balance;
	}
	
	protected double getBalance() {
		return this._balance;
	}
}