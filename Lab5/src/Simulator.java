import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import timers for use when turning on atm to get timestamps for transactions
import java.util.Date;

public class Simulator {

	// Keyboard input method
	public static void main(String[] args) throws IOException {
		Date time = new Date();
		Scanner stdIn = new Scanner(System.in);
		boolean flag = false;
		String input = "";
		ATM a = null;

		// do-while: Repeatedly ask for the user to begin the process
		do {
			System.out.print("Enter 'start' or provide <filepath/name> to use the ATM: ");
			input = stdIn.next();
			if (input.toLowerCase().equals("start")) {
				flag = true;
				a = new ATM();
			} else if (input.contains(".txt")){
				try (Scanner sc = new Scanner(new File(input));){
					simulate(sc);
					
				}catch(FileNotFoundException e){
					System.out.println(input + " - no such file found");
				}
			}
		} while (!flag);
		
		// Begin the process in class ATM; pass in stdIn so it can be carried throughout the program
		a.start(stdIn, time);
		
		stdIn.close();
	}
	
	
	// Text file reading method
	public static void simulate(Scanner sc) throws IOException{
		int accNum = 0;
		int pin;
		String trans;
		int amount;
		ATM atm = new ATM();
		Bank bank = new Bank();
		CardReader cr = new CardReader();
		CashDispenser cd = new CashDispenser();
		Printer p = new Printer();
		Account acc = null;
		Date time = new Date();
		
		while (sc.hasNextLine())
		{
			String entry = sc.next();
			System.out.print(entry + " ");
			if(entry.equals("CARDREAD"))
			{
				System.out.println();
				accNum = cr.acctNumber(sc);
				System.out.println(accNum);
			}
			if(entry.equals("NUM"))
			{
				System.out.println();
				pin = atm.enterPIN(sc);
				
				acc = new Account(accNum, pin, 0);
				
				while(!bank.validate(acc))
				{
					pin = atm.enterPIN(sc);
					acc = new Account(accNum, pin, 0);
				}
				System.out.println(pin);
				acc = bank.getAccount(acc);
			}
			if(entry.equals("DIS"))
			{
				
			}
			if(entry.equals("PRINT"))
			{
				System.out.println(entry);
				p.print(Long.toString(time.getTime()),sc.next(),sc.nextInt());
			}
			if(entry.equals("BUTTON"))
			{
				System.out.println("\nWould you like to withdraw, check balance, or cancel? (w/cb/cancel)");
				
				trans = sc.next();
				if(trans.equals("W"))
				{
					System.out.print(trans + "\nAmount: ");
					sc.next(); sc.next();
					amount = sc.nextInt();
					bank.withdraw(amount,acc);
					cd.dispense();
					System.out.println();
				}
				if(trans.equals("CB"))
				{	
					System.out.println(trans + "\nBalance: "+bank.getBalance(acc));
				}
				if(trans.equals("CANCEL"))
				{
					System.out.println(trans + "\nEJECT CARD");
					sc.close();
					System.exit(1);
				}
			}
		}		
	}
}