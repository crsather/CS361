
public class Bank {
	public Account a;
	public Account b;
	
	public Bank() {
		a = new Account(1234, 6789, 80);
		b = new Account(6789, 4321, 60);
	}
	
	public boolean validate(Account acc) {
		return (acc.validate(a) ^ acc.validate(b));		// ^ = XOR
	}
	
	public Account getAccount(Account acc) { 			//return account with proper balance for atm to use in transactions
		if(acc.validate(a)) {
			return a;
		}
		else return b;
	}
	
	public double getBalance(Account acc){
		return acc.getBalance();
	}
	
	private void setBalance(Account acc, Double amount){
		
		acc.setBalance(amount);
		
	}
	
	//checks to see if account has enough funds to make the withdrawal, withdraws amount if it does
	//prints proper responses given different error cases
	//adjusts accounts balance on proper input and prints a receipt after transaction.
	public boolean withdraw(double requested, Account acc) {
		
		if (this.getBalance(acc) == 0.0) {
			System.out.println("Error: insufficient funds");
			return false;
		}
		if (requested < 0) {
			System.out.println("Error: cannot withdraw negative amount");
			return false;
		}
		
		if (requested >= getBalance(acc)) { 
			setBalance(acc, 0.0); //just withdraw as much money as possible from account if request exceeds balance
		} else {
			setBalance(acc, getBalance(acc) - requested);
		}
		return true;
	}
	
	//method to deposit money into account, adds proper amount of money into the balance of the verified account
	/* this ATM does not support depositing
	public boolean deposit(double requested, Account acc) {
		if (requested < 0){
			System.out.println("Error: cannot deposit negative amount");
			return false;
		}
		else{
			
			double bal = acc.getBalance();
			setBalance(acc,bal + requested);
			return true;
		}	
		
	}
		*/
}