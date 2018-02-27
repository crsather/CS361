import java.util.Scanner;

public class CardReader {
	
	public int acctNumber(Scanner stdIn) {
		String str = "Invalid account number, please try again";
		boolean flag = false;
		String s = "";
		int i = -1;
		
		do {
			System.out.println("Please enter your four-digit card number: ");
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