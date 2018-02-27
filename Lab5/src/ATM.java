import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class ATM {
	public CardReader cr;
	public Printer p;
	public CashDispenser cd;
	public Bank b;
	
	public ATM() throws FileNotFoundException {
		cr = new CardReader();
		p = new Printer();
		cd = new CashDispenser();
		b = new Bank();
	}
	
	public void start(Scanner stdIn, Date time) {
		boolean flag = false;
		double amount = 0.0;
		Account a;
		// do-while: Repeatedly ask for and attempt to verify an account
		do {
			
			// Entering the account and PIN
			int acctNum = cr.acctNumber(stdIn);		// Class CardReader: Asks for any 4-digit number; will accept nothing else
			int PIN = enterPIN(stdIn);
			
			// Validating the account and PIN
			a = new Account(acctNum, PIN, 0); // validate should only check this temp acc for acc num and PIN, NOT balance, since user does not input it beforehand
			if (b.validate(a)) {
				flag = true; 
				a = b.getAccount(a); // get updated account balance for use in transactions
			}
			else System.out.println("Invalid account credentials, please try again");
			
		} while (!flag);

		
		// Reaching this point means validation is complete; ask for the user's next desired action
		String action;
		
		do {
			System.out.print("Would you like to withdraw, check balance, or cancel? (w/cb/cancel): ");
			
			action = stdIn.next();
				
			switch (action.toLowerCase()) {
				case "w": System.out.println("Amount"); amount = stdIn.nextDouble(); b.withdraw(amount, a); cd.dispense(); break;
				case "cb": System.out.println("Balance " + b.getBalance(a)); break;
				case "cancel": System.out.println("EJECT"); break;
				default:
					System.out.println("Invalid input"); break;
			}
		} while (!action.equals("cancel"));
		
		System.out.println("Thank you for using the ATM.");
	}
	
	protected int enterPIN(Scanner stdIn) {		
		String str = "Invalid PIN, please try again";
		boolean flag = false;
		String s = "";
		int i = -1;
		
		do {
			System.out.println("Enter your PIN: ");
			s = stdIn.next();
			if (s.length() != 4) System.out.println(str);
			else {
				try {
					i = Integer.parseInt(s);
					flag = true;
				} catch (NumberFormatException e) {
					System.out.println(str);
				}
			}			
		} while (!flag); // while input for CARDREAD is incorrect
		
		return i;
	}
}